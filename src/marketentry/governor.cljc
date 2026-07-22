(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of jurisdictional procurement law, whether a Slovenian
  foreign-direct-investment screening notification has actually been
  filed for an engagement that requires one, whether a claimed
  engagement fee actually equals base + months x rate, or when a
  draft stops being a draft and becomes a real-world e-JN portal
  submission, so this MUST be a separate system able to *reject* a
  proposal and fall back to HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints; this fleet's Poland
  implementation was the first *running* instance of this governor
  for the iso3166 family, Serbia the second; this is Slovenia's).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual e-JN registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off'; 'a false or fabricated regulatory-requirement claim
  is a HARD hold') names exactly the checks below.

  Slovenia is an EU MEMBER STATE (and eurozone member) -- like Poland,
  NOT like Serbia (EU-candidate, non-member). But Slovenia's own
  distinguishing structural fact, independently WebFetch-verified this
  tick, is NOT the same as Poland's: Poland's flagship pair
  (`eu-establishment-missing` + `nip-unverified`) is grounded in EU
  freedom-of-establishment/authorized-representative practice for
  procurement plus Poland's own NIP/KRS numbers. Slovenia instead has
  adopted its OWN foreign-direct-investment screening mechanism
  implementing Regulation (EU) 2019/452 -- the Zakon o spodbujanju
  investicij (ZSInv), amended provisions -- which requires notifying
  Ministrstvo za gospodarstvo, delo in šport (Ministry of Economy,
  Labour and Sport) BEFORE completing a transaction where a foreign
  investor acquires >= 10% of the capital/voting rights of a Slovenian
  company AND that company's activity touches a listed risk factor
  (critical infrastructure, critical technologies, supply of critical
  resources, access to sensitive information, media pluralism, or
  EU-interest projects/programmes) -- see `marketentry.facts` docstring
  for the full citation trail. This is a REALISTIC overlap for THIS
  actor's own domain (public-sector market entry): public contracts
  commonly touch exactly those risk-factor categories, so an operator
  whose Slovenian market presence was established by ACQUIRING a stake
  in an existing Slovenian company (rather than a purely organic
  greenfield subsidiary) may well have triggered ZSInv notification --
  the governor independently verifies this was actually done, exactly
  CONDITIONAL on the engagement's own `:requires-fdi-notification?`
  ground truth (an engagement that entered organically, or whose
  activity does not touch a risk factor, does not need it -- never
  force every engagement through a check the source law does not
  impose on it).

  Unlike Serbia (EU-candidate, whose Zakon o ulaganjima explicitly
  gives foreign investors national treatment with NO screening
  threshold -- a genuine asymmetry, not an omission), Slovenia's
  EU-membership is EXACTLY why it has this mechanism: Regulation (EU)
  2019/452 is an EU instrument, so an EU member state screening
  regime is the structurally DIFFERENT fact from Serbia's absence of
  one, and also different in kind from Poland's establishment/NIP
  pair (which this Slovenia blueprint does NOT duplicate -- adding a
  fabricated eu-establishment-missing/nip-unverified pair here would
  copy Poland's shape without an independently verified Slovenia-
  specific hook for it; the FDI-notification check IS independently
  hooked to a citation actually fetched this session). A smaller
  honest check set beats a padded one copied from a sibling.

  Six checks, in priority order, ALL HARD violations: a human approver
  CANNOT override them. The confidence/actuation gate is SOFT: it asks
  a human to look (low confidence / actuation), and the human may
  approve -- but see `marketentry.phase`: for `:stake
  :actuation/draft-filing`/`:actuation/submit-filing` NO phase ever
  allows auto-commit either. Two independent layers agree that
  actuation is always a human call.

    1. Spec-basis                  -- did the jurisdiction proposal cite
                                       an OFFICIAL source
                                       (`marketentry.facts`), or invent
                                       one?
    2. Evidence incomplete         -- for `:filing/draft`/
                                       `:filing/submit`, has the
                                       jurisdiction actually been
                                       assessed with a full evidence
                                       checklist on file?
    3. FDI notification missing    -- for `:filing/submit`, when the
                                       engagement declares
                                       `:requires-fdi-notification?
                                       true`, INDEPENDENTLY verify
                                       `:fdi-notification-verified?`
                                       is true. FLAGSHIP check for this
                                       vertical, grounded in the Zakon
                                       o spodbujanju investicij
                                       (ZSInv) as amended to implement
                                       EU Regulation 2019/452.
    4. Engagement fee mismatch     -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own `:claimed-
                                       fee` equals `base-fee +
                                       monthly-rate x monitoring-
                                       months` -- honest reapplication
                                       of the ground-truth-recompute
                                       discipline sibling actors use.
    5/6. Double-draft / double-submit prevention -- enforced off
                                       dedicated `:drafted?`/
                                       `:submitted?` facts (never a
                                       `:status` value)."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real e-JN filing package and submitting a real portal
  registration are the two real-world actuation events this actor
  performs."
  #{:actuation/draft-filing :actuation/submit-filing})

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(AJPES登録/davčna številka/e-JN登録/署名権限者確認等)が充足していない状態での提案"}]))))

(defn- fdi-notification-missing-violations
  "For `:filing/submit`, when the engagement declares
  `:requires-fdi-notification? true`, INDEPENDENTLY verify
  `:fdi-notification-verified?` is true -- the flagship genuinely new
  check this vertical adds. CONDITIONAL on the engagement's own
  `:requires-fdi-notification?` ground truth. Grounded in the Zakon o
  spodbujanju investicij (ZSInv) as amended to implement EU Regulation
  2019/452 (Ministrstvo za gospodarstvo, delo in šport)."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (true? (:requires-fdi-notification? e))
                 (not (true? (:fdi-notification-verified? e))))
        [{:rule :fdi-notification-missing
          :detail (str subject " はFDI通知(ZSInv/EU規則2019/452)確認を要するが未確認 -- 提出提案は進められない")}]))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (fdi-notification-missing-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})

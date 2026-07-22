(ns marketentry.facts
  "Slovenia (Republic of Slovenia, EU member state, eurozone) market-entry
  catalog. Every SVN field is WebFetch-verified this tick against the
  official government sites named in `:provenance` -- an entry NOT in
  `catalog` has no spec-basis, full stop; extend `catalog`, never
  invent an authority/URL.

  Verified this tick:
  - https://ejn.gov.si/ -- 'Portal za elektronsko javno naročanje e-JN'
    (Portal for electronic public procurement e-JN); footer states the
    operating body: 'Ministrstvo za notranje zadeve in javno upravo'
    (Ministry of the Interior and Public Administration); the page
    exposes a bidder/economic-operator ('ponudniki') registration flow
    ('Registriraj se').
  - https://www.gov.si/en/topics/public-procurement/ -- confirms the
    e-JN platform in English: 'The e-JN platform enables electronic
    public procurement procedures, including the electronic submission
    of applications/tenders, an electronic catalogue, ... and
    electronic reverse auctions.'
  - https://www.gov.si/teme/javno-narocanje/ -- cites '23. člen Zakona
    o javnem naročanju' (Art. 23 of the Public Procurement Act),
    confirming the LAW NAME 'Zakon o javnem naročanju' is real and in
    force. NOTE: the exact version suffix commonly seen in secondary
    sources ('ZJN-3') and the precise Uradni list RS (Official
    Gazette) publication number were NOT independently confirmed this
    session from a source actually fetched -- every attempt either
    404'd (djn.mju.gov.si path guesses, dkom.si/zakonodaja) or refused
    the connection (https://www.djn.mju.gov.si/, ECONNREFUSED -- a
    genuine connectivity failure, not a CAPTCHA/bot-detection
    challenge; no bypass was attempted). `:legal-basis` below therefore
    states only the law name, disclosed as partial.
  - https://www.dkom.si/ -- Državna revizijska komisija (za revizijo
    postopkov oddaje javnih naročil): 'Državna revizijska komisija je
    poseben, neodvisen in samostojen državni organ nadzora, ki odloča
    o zakonitosti v vseh stopnjah postopka javnega naročanja.' (a
    special, independent, autonomous state oversight body deciding
    legality at every stage of the public-procurement procedure). Its
    own site nav references 'ZPVPJN' (Zakon o pravnem varstvu v
    postopkih javnega naročanja) as the legal-protection statute, but
    the exact gazette number for THAT law was likewise not rendered in
    what was fetched -- also disclosed as partial, not fabricated.
  - https://www.ajpes.si/ -- Agencija Republike Slovenije za
    javnopravne evidence in storitve (AJPES, Agency of the Republic of
    Slovenia for Public Legal Records and Related Services) -- runs
    Poslovni register Slovenije: 'Javna baza podatkov o vseh poslovnih
    subjektih s sedežem na območju republike Slovenije' (public
    database of all business entities headquartered in Slovenia).
  - https://www.fu.gov.si/ -- Finančna uprava Republike Slovenije
    (FURS, Financial Administration of the Republic of Slovenia) --
    'Državni portal Finančne uprave o storitvah in postopkih s
    področja davkov, carin, trošarin in drugih dajatev'.
  - https://www.fu.gov.si/poslovni_dogodki_podjetja/pridobitev_davcne_stevilke_za_tuje_podjetje/
    -- FLAGSHIP evidence source (see `nonresident-tax-number-spec-basis`
    below, feeds the `evidence-incomplete` check, NOT the flagship
    governor check -- see `statute.facts`/`marketentry.governor` for
    why the flagship is FDI-notification, not this): a foreign legal
    entity with no seat/registration in Slovenia must register in the
    tax register 'pred začetkom opravljanja dejavnosti na območju
    Republike Slovenije' (before commencing business activity in
    Slovenia's territory) using Form DR-04 (for the entity) plus
    DR-02/DR-04 (for the entity's responsible person); filed at any
    tax office except the General/Special offices, in person, via an
    authorized representative, or by mail; the assigned davčna
    številka (tax number) is confirmed within 8 days.
  - https://www.gov.si/en/topics/foreign-direct-investments/ and its
    Slovenian counterpart https://www.gov.si/teme/spodbujanje-investicij/
    -- FLAGSHIP spec-basis (see `fdi-notification-spec-basis` and
    `marketentry.governor`'s `fdi-notification-missing`): Slovenia HAS
    adopted an FDI-screening mechanism implementing EU Regulation
    2019/452 -- 'Regulation (EU) 2019/452 establishing a framework for
    the screening of foreign direct investment in the Union' / 'Uredba
    (EU) 2019/452 o vzpostavitvi okvira za pregled neposrednih tujih
    naložb v Uniji' -- via 'the amended provisions of the Law on
    Foreign Direct Investment', independently identified on the
    Slovenian-language page as 'Zakon o spodbujanju investicij'
    (ZSInv, Law on Investment Promotion). Trigger, quoted verbatim:
    'The transaction constitutes a foreign direct investment through
    which the investor acquires at least 10 % of the capital or voting
    rights in a Slovenian company. The activity of the target company
    relates to one of the risk factors (critical infrastructure,
    critical technologies, supply of critical resources, access to
    sensitive information, freedom and pluralism of the media and
    projects or programmes of interest to the European Union).' The
    responsible body is 'Ministrstvo, pristojno za gospodarstvo' (the
    Ministry responsible for the economy) -- Ministrstvo za
    gospodarstvo, delo in šport (Ministry of Economy, Labour and
    Sport) -- via its Internationalisation, Entrepreneurship and
    Technology Directorate and a Notification Commission that issues
    opinions on whether to open a review. THIS is what makes Slovenia
    structurally different from BOTH Poland (EU member, this fleet's
    flagship there is an eu-establishment/NIP pair, no FDI-screening
    check) and Serbia (EU-candidate, explicitly has NO FDI-screening
    regime per its own README) -- see `marketentry.governor` docstring
    for the full reasoning.")

(def catalog
  {"SVN" {:name "Slovenia"
          :owner-authority "Ministrstvo za notranje zadeve in javno upravo (Ministry of the Interior and Public Administration) / e-JN (Elektronsko javno naročanje)"
          :legal-basis "Zakon o javnem naročanju (exact gazette number not independently confirmed this session -- see catalog docstring)"
          :national-spec "e-JN (Elektronsko javno naročanje) portal -- bidder/economic-operator registration"
          :provenance "https://ejn.gov.si/"
          :required-evidence ["AJPES Poslovni register extract (business-registration record)"
                               "FURS davčna številka (tax number) assignment record"
                               "e-JN bidder/economic-operator registration record"
                               "Authorized-signatory record"]
          ;; Flagship check basis -- see marketentry.governor
          ;; `fdi-notification-missing-violations`. Grounded in the
          ;; Zakon o spodbujanju investicij (ZSInv) amended provisions
          ;; implementing Regulation (EU) 2019/452: notification is
          ;; required when a foreign investor acquires >= 10% of the
          ;; capital/voting rights of a Slovenian company AND that
          ;; company's activity touches a listed risk factor (a
          ;; realistic overlap for public-sector-adjacent market entry
          ;; -- critical infrastructure / critical technologies /
          ;; sensitive-information access are common public-contract
          ;; subject matter).
          :fdi-notification-owner-authority "Ministrstvo za gospodarstvo, delo in šport (Ministry of Economy, Labour and Sport) -- Internationalisation, Entrepreneurship and Technology Directorate / Notification Commission"
          :fdi-notification-legal-basis "Zakon o spodbujanju investicij (ZSInv), amended provisions implementing Regulation (EU) 2019/452"
          :fdi-notification-provenance "https://www.gov.si/en/topics/foreign-direct-investments/"
          ;; Secondary (non-flagship) nonresident evidence -- feeds
          ;; `evidence-incomplete`, not its own governor check, unlike
          ;; Serbia's PIB mechanism which IS the flagship there. See
          ;; docstring above for why FDI-notification, not this, is
          ;; SVN's flagship.
          :nonresident-tax-number-owner-authority "Finančna uprava Republike Slovenije (FURS)"
          :nonresident-tax-number-legal-basis "davčna register application (Obrazec DR-04) pred začetkom opravljanja dejavnosti -- FURS business-events guide"
          :nonresident-tax-number-provenance "https://www.fu.gov.si/poslovni_dogodki_podjetja/pridobitev_davcne_stevilke_za_tuje_podjetje/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "DEU" {:name "Germany" :owner-authority "e-Vergabe" :legal-basis "GWB/VgV"
          :national-spec "e-Vergabe" :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract" "e-Vergabe registration record" "USt-IdNr record" "Authorized-representative record"]}
   "NLD" {:name "Netherlands" :owner-authority "TenderNed" :legal-basis "Aanbestedingswet"
          :national-spec "TenderNed" :provenance "https://www.tenderned.nl/"
          :required-evidence ["KvK extract" "TenderNed registration" "BTW record" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))

(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))

(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))

(defn fdi-notification-spec-basis
  "Spec-basis for the flagship `fdi-notification-missing` governor
  check -- foreign-direct-investment screening notification under the
  Zakon o spodbujanju investicij (ZSInv), amended to implement EU
  Regulation 2019/452."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:fdi-notification-owner-authority sb)
      (select-keys sb [:fdi-notification-owner-authority :fdi-notification-legal-basis :fdi-notification-provenance]))))

(defn nonresident-tax-number-spec-basis
  "Spec-basis for the (non-flagship) Slovenian davčna številka
  registration requirement for a foreign legal entity -- feeds
  `evidence-incomplete`, not its own dedicated HARD governor check."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:nonresident-tax-number-owner-authority sb)
      (select-keys sb [:nonresident-tax-number-owner-authority :nonresident-tax-number-legal-basis :nonresident-tax-number-provenance]))))

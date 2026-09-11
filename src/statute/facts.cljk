(ns statute.facts
  "General-law compliance catalog for Slovenia (SVN) -- per ADR-2607141700
  (cloud-itonami-compliance-fact-federation), mirroring the shape of
  cloud-itonami-iso3166-pol/-srb/-... `statute.facts`. Slovenia was
  previously culture-catalog-only in this repo, with no statute.facts
  -- this entry closes that structural gap for the SVN entity.

  PROVENANCE DISCLOSURE (read before trusting any citation below):

  Two of the three entries below (ZDR-1, ZVOP-2) are corroborated by a
  government topic page (gov.si / spot.gov.si -- Slovenia's official
  business/citizen-services portal, itself a `.gov.si` government
  property) that names the law, its abbreviation and (for ZDR-1) its
  adoption/effective dates, but does NOT itself display an Uradni list
  RS (Official Gazette) publication number in the page text that was
  fetched -- so `:statute/law-number` below states the confirmed law
  name/abbreviation and honestly marks the gazette number as not
  independently confirmed this session, rather than inventing a
  plausible-looking number. This is a materially different (weaker)
  provenance shape than Poland's eli.gov.pl citations (which DO show
  exact Dz.U. numbers) -- disclosed explicitly, not papered over.

  Zakon o delovnih razmerjih (ZDR-1, Employment Relationships Act) --
  https://www.gov.si/teme/delovna-razmerja/ states verbatim: 'Zakon o
  delovnih razmerjih (ZDR-1) je osrednji zakon s področja delovnega
  prava, ki ureja individualna delovna razmerja.' (ZDR-1 is the
  central law in the field of labour law that regulates individual
  employment relationships -- own gloss, not an official translation)
  and 'ZDR-1 je bil sprejet 5. marca 2013 in se je uveljavil 12.
  aprila 2013' (ZDR-1 was adopted on 5 March 2013 and entered into
  force on 12 April 2013). The same page points to the official text
  at http://www.pisrs.si/Pis.web/pregledPredpisa?id=ZAKO5944, but
  PisRS itself renders as a JavaScript application -- WebFetch's
  HTML-to-markdown conversion only recovered the page chrome/title
  ('PisRS - Pravno informacijski sistem'), not the law text or gazette
  number, when that URL was fetched directly this session. The
  adoption/effective dates above are therefore sourced from the citing
  gov.si page, not from PisRS's own rendered text.

  Zakon o varstvu osebnih podatkov (ZVOP-2, Personal Data Protection
  Act) -- https://spot.gov.si/sl/teme/varstvo-osebnih-podatkov/
  (redirected from https://www.gov.si/teme/varstvo-osebnih-podatkov/,
  301, itself a Slovenian government portal) states ZVOP-2 'came into
  effect on January 26, 2023' and names the enforcing authority as the
  'Informacijski pooblaščenec' (Information Commissioner), operating
  alongside the EU GDPR. No Uradni list RS gazette number was present
  in the fetched page text.

  Zakon o gospodarskih družbah (ZGD-1, Companies Act) -- the
  ABBREVIATION 'ZGD-1' is independently confirmed directly on AJPES's
  own site, https://www.ajpes.si/Registri/Poslovni_register/Splosno,
  which references 'Objava sporočil po ZGD-1' (publication of notices
  under ZGD-1) in its navigation -- i.e. AJPES itself, the body that
  RUNS the Poslovni register Slovenije, cites this law by name. This
  is the WEAKEST-provenance entry of the three: repeated attempts to
  reach a page stating ZGD-1's exact Uradni list RS gazette number and
  adoption date all failed this session (spot.gov.si/sl/teme/ustanovitev-podjetja/
  and spot.gov.si/sl/zacetek-poslovanja/ustanovitev-podjetja/ and
  e-uprava.gov.si/podrocja/gospodarstvo/druzbe-in-samostojni-podjetniki.html
  all returned HTTP 404). `:statute/law-number` and
  `:statute/enacted-date` for this entry are left as explicit
  disclosed gaps rather than invented -- per this project's standing
  no-fabrication rule, a smaller honest catalog beats a padded one.

  An entry not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "ISO3166 alpha-3 -> vector of statute entries."
  {"SVN"
   [{:statute/id "svn.zgd-1-zakon-o-gospodarskih-druzbah"
     :statute/title "Zakon o gospodarskih družbah (Companies Act)"
     :statute/jurisdiction "SVN"
     :statute/kind :law
     :statute/law-number "abbreviation \"ZGD-1\" confirmed via ajpes.si (\"Objava sporočil po ZGD-1\"); exact Uradni list RS gazette number NOT independently confirmed this session -- see catalog docstring"
     :statute/url "https://www.ajpes.si/Registri/Poslovni_register/Splosno"
     :statute/url-provenance :official-ajpes-si-secondary-reference
     :statute/enacted-date nil
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "svn.zvop-2-zakon-o-varstvu-osebnih-podatkov"
     :statute/title "Zakon o varstvu osebnih podatkov (ZVOP-2, Personal Data Protection Act)"
     :statute/jurisdiction "SVN"
     :statute/kind :law
     :statute/law-number "ZVOP-2 (exact Uradni list RS gazette number not independently confirmed this session -- see catalog docstring)"
     :statute/url "https://spot.gov.si/sl/teme/varstvo-osebnih-podatkov/"
     :statute/url-provenance :official-gov-si-redirected-to-spot-gov-si
     :statute/enacted-date "2023-01-26"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "svn.zdr-1-zakon-o-delovnih-razmerjih"
     :statute/title "Zakon o delovnih razmerjih (ZDR-1, Employment Relationships Act)"
     :statute/jurisdiction "SVN"
     :statute/kind :law
     :statute/law-number "ZDR-1 (exact Uradni list RS gazette number not independently confirmed this session -- see catalog docstring)"
     :statute/url "https://www.gov.si/teme/delovna-razmerja/"
     :statute/url-provenance :official-gov-si
     :statute/enacted-date "2013-03-05"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis [jurisdiction] (get catalog jurisdiction))

(defn coverage
  ([] (coverage (keys catalog)))
  ([jurisdictions]
   (let [have (filter catalog jurisdictions)
         missing (remove catalog jurisdictions)]
     {:requested (count jurisdictions)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-svn statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "SVN")) " Slovenia entries seeded, "
                 "corroborated via gov.si/spot.gov.si and ajpes.si -- see "
                 "catalog docstring for the honest gap-disclosure on exact "
                 "Uradni list RS gazette numbers, which were not rendered in "
                 "any page fetched this session. Extend `statute.facts/catalog`, "
                 "never fabricate an id/url.")})))

(defn by-topic [jurisdiction topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis jurisdiction)))

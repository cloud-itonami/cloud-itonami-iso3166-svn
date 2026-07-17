(ns culture.facts
  "Country-level regional-culture catalog for Slovenia (SVN) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"SVN"
   [{:culture/id "svn.dish.potica"
     :culture/name "Potica"
     :culture/country "SVN"
     :culture/kind :dish
     :culture/summary "Traditional festive rolled pastry from Slovenia."
     :culture/url "https://en.wikipedia.org/wiki/Potica"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "svn.dish.prekmurska-gibanica"
     :culture/name "Prekmurska gibanica"
     :culture/country "SVN"
     :culture/kind :dish
     :culture/summary "Traditional Slovenian layered pastry with multiple fillings from the Prekmurje region; protected in the EU since March 2010 as a Traditional Speciality Guaranteed."
     :culture/url "https://en.wikipedia.org/wiki/Prekmurska_gibanica"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "svn.dish.idrijski-zlikrofi"
     :culture/name "Idrijski žlikrofi"
     :culture/country "SVN"
     :culture/kind :dish
     :culture/summary "Traditional Slovenian potato-filled dumplings originating from the town of Idrija; awarded EU protected geographical status in 2010 as Traditional Speciality Guaranteed."
     :culture/url "https://en.wikipedia.org/wiki/Idrijski_%C5%BElikrofi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "svn.product.kranjska-klobasa"
     :culture/name "Kranjska klobasa"
     :culture/name-local "Carniolan sausage"
     :culture/country "SVN"
     :culture/kind :product
     :culture/summary "Slovenian parboiled sausage originating from the region of Carniola; obtained EU protected geographical indication (PGI) status in January 2015."
     :culture/url "https://en.wikipedia.org/wiki/Carniolan_sausage"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "svn.beverage.cvicek"
     :culture/name "Cviček"
     :culture/country "SVN"
     :culture/kind :beverage
     :culture/summary "Slovenian wine from the Lower Carniola (Dolenjska) region, made of mixed grape varieties with low alcohol content (8.5-10%)."
     :culture/url "https://en.wikipedia.org/wiki/Cvi%C4%8Dek"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "svn.craft.idrija-lace"
     :culture/name "Idrija lace"
     :culture/country "SVN"
     :culture/kind :craft
     :culture/summary "Bobbin tape lace native to the Slovenian town of Idrija; inscribed on UNESCO's Representative List of the Intangible Cultural Heritage of Humanity in 2018."
     :culture/url "https://en.wikipedia.org/wiki/Idrija_lace"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "svn.festival.kurentovanje"
     :culture/name "Kurentovanje"
     :culture/country "SVN"
     :culture/kind :festival
     :culture/summary "Slovenia's most popular and ethnologically significant carnival event, first organised in 1960 in Ptuj and featuring masked Kurent figures; recognized by UNESCO in 2017 as intangible cultural heritage."
     :culture/url "https://en.wikipedia.org/wiki/Kurentovanje"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "svn.festival.skofja-loka-passion-play"
     :culture/name "Škofja Loka Passion Play"
     :culture/country "SVN"
     :culture/kind :festival
     :culture/summary "The oldest play in Slovene, written in 1715 by Father Romuald and performed in Škofja Loka; designated UNESCO Intangible Cultural Heritage in 2016."
     :culture/url "https://en.wikipedia.org/wiki/%C5%A0kofja_Loka_Passion_Play"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "svn.heritage.skocjan-caves"
     :culture/name "Škocjan Caves"
     :culture/country "SVN"
     :culture/kind :heritage
     :culture/summary "Karst cave system in Slovenia included on UNESCO's list of natural and cultural World Heritage Sites in 1986."
     :culture/url "https://en.wikipedia.org/wiki/%C5%A0kocjan_Caves"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-svn culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "SVN"))
                 " SVN entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))

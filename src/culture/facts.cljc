(ns culture.facts
  "Country-level regional-culture catalog for Cameroon (CMR) -- national
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
  {"CMR"
   [{:culture/id "cmr.dish.ndole"
     :culture/name "Ndolé"
     :culture/country "CMR"
     :culture/kind :dish
     :culture/summary "Stew of ndoleh leaves, ground peanuts and crayfish with fish or beef, originating in Douala and widely regarded as the national dish of Cameroon."
     :culture/url "https://en.wikipedia.org/wiki/Ndolé"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cmr.dish.achu"
     :culture/name "Achu soup"
     :culture/country "CMR"
     :culture/kind :dish
     :culture/summary "Yellow soup of cocoyam, palm oil and limestone, a traditional food originating in Cameroon's Northwest Region, served during cultural celebrations and family events."
     :culture/url "https://en.wikipedia.org/wiki/Achu_(soup)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cmr.dish.eru"
     :culture/name "Eru"
     :culture/name-local "Okok"
     :culture/country "CMR"
     :culture/kind :dish
     :culture/summary "Leaves of the tropical African vine Gnetum africanum, used as a vegetable for soups and stews (eru soup, okazi soup or afang soup); known in Cameroon as eru, okok, m'fumbua or fumbua."
     :culture/url "https://en.wikipedia.org/wiki/Gnetum_africanum"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cmr.beverage.palm-wine"
     :culture/name "Palm wine"
     :culture/name-local "Matango"
     :culture/country "CMR"
     :culture/kind :beverage
     :culture/summary "Alcoholic beverage from fermented palm sap, ceremonially important in Cameroon's North West Region and known locally by names including matango, mimbo and mbuh."
     :culture/url "https://en.wikipedia.org/wiki/Palm_wine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cmr.festival.ngondo"
     :culture/name "Ngondo"
     :culture/country "CMR"
     :culture/kind :festival
     :culture/summary "Annual water-centred festival held by the Sawa coastal peoples in Douala, Cameroon, during the first two weeks of December at Wouri Bay."
     :culture/url "https://en.wikipedia.org/wiki/Ngondo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cmr.heritage.dja-faunal-reserve"
     :culture/name "Dja Faunal Reserve"
     :culture/country "CMR"
     :culture/kind :heritage
     :culture/summary "Congo Basin rainforest reserve in southeastern Cameroon, inscribed as a UNESCO World Heritage Site in 1987, with around 90% of its area undisturbed."
     :culture/url "https://en.wikipedia.org/wiki/Dja_Faunal_Reserve"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cmr.heritage.sangha-trinational"
     :culture/name "Sangha Trinational"
     :culture/country "CMR"
     :culture/kind :heritage
     :culture/summary "Transnational Congo Basin rainforest protected area shared by Cameroon, the Central African Republic and the Republic of the Congo, added as a UNESCO World Heritage Site in 2012."
     :culture/url "https://en.wikipedia.org/wiki/Sangha_Trinational"
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
      :note (str "cloud-itonami-iso3166-cmr culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "CMR"))
                 " CMR entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))

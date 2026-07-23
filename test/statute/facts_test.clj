(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest cmr-has-spec-basis
  (let [sb (facts/spec-basis "CMR")]
    (is (= 4 (count sb)))
    (is (= (count sb) (count (set (map :statute/id sb)))))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))
    (is (every? #(= "CMR" (:statute/jurisdiction %)) sb))
    (is (every? #(seq (:statute/topic %)) sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["CMR" "NGA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "NGA"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["cmr.ohada-auscgie"]
         (mapv :statute/id (facts/by-topic "CMR" :corporate-governance))))
  (is (= ["cmr.code-du-travail-1992"]
         (mapv :statute/id (facts/by-topic "CMR" :labor))))
  (is (= ["cmr.loi-2013-investissement"]
         (mapv :statute/id (facts/by-topic "CMR" :investment))))
  (is (= ["cmr.dgi-organisation"]
         (mapv :statute/id (facts/by-topic "CMR" :tax))))
  (is (empty? (facts/by-topic "CMR" :environment)))
  (is (empty? (facts/by-topic "ATL" :investment))))

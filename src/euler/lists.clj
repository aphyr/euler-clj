(ns euler.lists)

(defn count-distinct
  "Returns a map of elements in a list to their multiplicities."
  [list]
  (reduce (fn [counts element]
            (assoc counts element (inc (get counts element 0))))
          {} list))

(defn list-intersection
  "The intersection of lists l1, l2, l3, ... is the list L for which all
  elements of l_k can be found in L, including duplicates. Does not preserve
  order."
  [& lists]
  (mapcat (fn [[element count]] (repeat count element))
    (apply merge-with max (map count-distinct lists))))

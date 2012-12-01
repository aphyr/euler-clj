(ns euler.grids
  (:use euler.sequences))

(defn drifts
  "Given an n-dimensional grid with the given dimensions, returns a list of
  drifts over that grid, where each drift is a list of l coordinates separated
  by v."
  [dimensions v l]
  (let [ranges (map (fn [size delta]
                      (range (max 0    (- 0    (* (dec l) delta)))
                             (min size (- size (* (dec l) delta)))))
                    dimensions v)
        starts (apply square-seq ranges)]
    (map (fn [start]
           (take l (iterate (fn [r] (mapv + r v)) start)))
         starts)))

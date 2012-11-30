(ns euler.math)

(defn divisible?
  "Is x divisible by divisor?"
  [divisor x]
  (zero? (mod x divisor)))

(defn sum
  "Sums numbers."
  [numbers]
  (reduce + numbers))

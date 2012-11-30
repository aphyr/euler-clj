(ns euler.sequences
  (:use euler.math))

(def N
  "The natural numbers"
  (iterate inc (bigint 0)))

(def ordinals
  "The ordinal numbers"
  (iterate inc (bigint 1)))

(defn fibonacci
  "A fibonacci sequence beginning with seeds a and b"
  ([] (fibonacci (bigint 1) (bigint 2)))
  ([a b]
   (lazy-seq (cons a (fibonacci b (+ a b))))))

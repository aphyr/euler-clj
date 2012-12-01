(ns euler.math
  (:use clojure.math.numeric-tower))

(defn divisible?
  "Is x divisible by divisor?"
  [divisor x]
  (zero? (mod x divisor)))

(defn divisible-by-any?
  "Is x divisible by any of divisors?"
  [divisors x]
  (some #(divisible? % x) divisors))

(defn sum
  "Sums numbers."
  [numbers]
  (reduce + numbers))

(defn square
  "x squared"
  [x]
  (expt x 2))

(defn cube
  "x cubed"
  [x]
  (expt x 3))

(defn inc-in-base
  "Increments a list of digits in a given base, least-significant-first. In
  base 2:
  
  (0 0 0) -> (1 0 0)
  (1 0 0) -> (0 1 0)
  (1 1 0) -> (0 0 1)
  (0 0 1) -> (1 0 0 1)"
  ([base digits]
   (let [f (inc (or (first digits) 0))]
     (if (<= base f)
       ; Carry
       (cons 0 (inc-in-base base (rest digits)))
       ; Increment
       (cons f (rest digits))))))

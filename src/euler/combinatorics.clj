(ns euler.combinatorics
  (:use euler.math
        euler.sequences
        clojure.math.numeric-tower
        [clojure.math.combinatorics :only [combinations subsets]]))

(defn binomial-coefficient
  "The binomial coefficient n choose k"
  [n k]
  (/ (factorial n)
     (factorial k)
     (factorial (- n k))))

(def choose binomial-coefficient)

(defn handshake-product-inc
  [base digits]
  (let [f (inc (or (first digits) 0))]
    (if (<= base f)
      ; Carry
      (let [res (handshake-product-inc base (rest digits))]
        (cons (first res) res))
      ; Increment
      (cons f (rest digits)))))

(defn handshake-product
  "Given a set of items, returns all n-tuples of elements from items,
  degenerate in order. Like cartesian product, but without duplicates: the
  cartesian product would include [1 1 2], [1 2 1], and [2 1 1], but the
  handshake product includes only [2 1 1].

  In some ways, this sequence is related to a generalization of the handshake
  problem and triangle numbers to higher dimensions, but I haven't figured out
  its name yet. I stopped looking when I got to the Riemann Zeta function. I
  think it might be multiset combinations. :-P

  (handshake-product [0 1 2] 3)
  [0 0 0]
  [1 0 0]
  [2 0 0]
  [1 1 0]
  [2 1 0]
  [2 2 0]

  [1 1 1]
  [2 1 1]
  [2 2 1]

  [2 2 2]
  "
  [items n]
  (let [base (count items)]
    (map (partial map (partial nth items))
         (take-while #(= (count %) n)
               (iterate (partial handshake-product-inc base)
                        (repeat n 0))))))

(defn cartesian-product
  [& lists]
  (apply square-seq lists))

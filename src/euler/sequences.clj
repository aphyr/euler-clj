(ns euler.sequences
  (:use euler.math))

(def N
  "The natural numbers"
  (iterate inc (bigint 0)))

(def ordinals
  "The ordinal numbers"
  (iterate inc (bigint 1)))

(def squares
  "The square numbers"
  (map square N))

(defn fibonacci
  "A fibonacci sequence beginning with seeds a and b"
  ([] (fibonacci (bigint 1) (bigint 2)))
  ([a b]
   (lazy-seq (cons a (fibonacci b (+ a b))))))

(defn square-seq
  "Given n seqs, returns a seq of tuples. Each tuple's first element comes from
  the first seq, the second element from the second seq, etc. Final seqs
  iterate fastest. 
  
  (square-seq [1 2] [3 4] [5 6])
  ((1 3 5) (1 3 6) (1 4 5) (1 4 6) (2 3 5) (2 3 6) (2 4 5) (2 4 6))

  Think of it like a generalized for comprehension."
  ([] '())
  ([s1] (map (fn [x] [x]) s1))
  ([s1 s2]
   (for [v1 s1 v2 s2] [v1 v2]))
  ([s1 s2 & more]
   (map flatten
        (reduce square-seq (apply list s1 s2 more)))))

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

(defn triangle-numbers
  "The triangle numbers, e.g.
  0, 1, 3, 6, 10, 15, 21, ..."
  ([] (triangle-numbers 0 1))
  ([sum i]
   (lazy-seq (cons sum (triangle-numbers (+ sum i) (inc i))))))

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

(declare collatz-sequence)
(defn collatz-sequence*
  "The Collatz sequence beginning with n, defined as:
  n -> n/2 (n even)
  n -> 3n + 1 (n odd)"
  [n]
  (if (= 1 n)
    (list 1)
    (cons n (lazy-seq (collatz-sequence
                        (if (even? n)
                          (/ n 2)
                          (inc (* n 3))))))))
(def collatz-sequence (memoize collatz-sequence*))

(defmacro defmemo
  [name & fdecl]
  `(def ~name
     (memoize (fn ~fdecl))))

(defn collatz-sequence-length*
  "Returns a memoized function computing the length of the given Collatz
  sequence."
  []
  (let [f (fn [memoized n]
            (if (= 1 n)
              1
              (inc (memoized memoized
                     (if (even? n)
                       (/ n 2)
                       (inc (* n 3)))))))
        memoized (memoize f)]
    (partial memoized memoized)))

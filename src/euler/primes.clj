(ns euler.primes
  (:use euler.math
        [euler.lists :only [count-distinct]]
        [clojure.math.combinatorics :only [subsets]]))

(defn primes*
  "A sieve-of-eratosthenes lazy sequence of prime numbers, beginning with candidate
  and sieve."
  ([] (primes* 2 '()))
  ([candidate sieve]
   (if (divisible-by-any? sieve candidate)
     ; Not prime
     (lazy-seq (primes* (inc candidate) sieve))
     ; Prime
     (cons candidate (primes* (inc candidate) (conj sieve candidate))))))

(defn primes2*
  ; A much faster variant than my naieve implementation, from
  ; http://clj-me.cgrand.net/index.php?s=Everybody%20loves%20the%20Sieve%20of%20Eratosthenes.
  ; Mutates the sieve forward instead of using modulo operations backward each
  ; time.
  []
  (letfn [(enqueue [sieve n step]
                   (let [m (+ n step)]
                     (if (sieve m)
                       (recur sieve m step)
                       (assoc sieve m step))))
          (next-sieve [sieve candidate]
                      (if-let [step (sieve candidate)]
                        (-> sieve
                          (dissoc candidate)
                          (enqueue candidate step))
                        (enqueue sieve candidate (+ candidate candidate))))
          (next-primes [sieve candidate]
                       (if (sieve candidate)
                         (recur (next-sieve sieve candidate) (+ candidate 2))
                         (cons candidate
                               (lazy-seq (next-primes 
                                           (next-sieve sieve candidate)
                                           (+ candidate 2))))))]
    (cons 2 (lazy-seq (next-primes {} 3)))))

(def primes
  "For fast re-use, caches the seq."
  (primes2*))

(defn prime?
  "Is n prime?"
  [n]
  (some (partial = n) (take-while >= n) primes))

(defn prime-factorization
  "Compute prime factorization of n."
  ([n]
   (prime-factorization n (take-while (partial >= n) primes) '()))
  ([n factors done]
   (if-let [factor (first factors)]
     (if (divisible? factor n)
       (recur (/ n factor)
              (take-while (partial >= (/ n factor)) factors)
              (conj done factor))
       (recur n (rest factors) done))

     ; No remaining factors.
     done)))

(defn count-factors
  "How many factors are in n?"
  [n]
  (product (map inc (vals (count-distinct (prime-factorization n))))))

(defn factors
  "All factors of n."
  [n]
  (distinct (map product (subsets (prime-factorization n)))))

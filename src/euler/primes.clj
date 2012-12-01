(ns euler.primes
  (:use euler.math))

(defn primes*
  "A sieve-of-eratosthenes sequence of prime numbers, beginning with candidate
  and sieve."
  ([] (primes* 2 '()))
  ([candidate sieve]
   (if (divisible-by-any? sieve candidate)
     ; Not prime
     (lazy-seq (primes* (inc candidate) sieve))
     ; Prime
     (cons candidate (primes* (inc candidate) (conj sieve candidate))))))

(def primes
  "For fast re-use, caches the seq."
  (primes*))

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

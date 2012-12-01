(ns euler.core-test
  (:use clojure.test
        euler.core))

(defmacro p
  [number value]
  (let [test (symbol (str "p" number "-test"))
        fun  (symbol (str "p" number))]
    (prn test fun)
    `(deftest ~test
              (is (= ~value (~fun))))))

(p 1 233168)
(p 2 4613732)
(p 3 6857)
(p 4 906609)
(p 5 232792560)
(p 6 25164150)
(p 7 104743)
(p 8 40824)


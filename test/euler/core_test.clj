(ns euler.core-test
  (:use clojure.test
        euler.core))

(defmacro p
  [number value]
  (let [test (symbol (str "p" number "-test"))
        fun  (symbol (str "p" number))]
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
(p 9 31875000)
(p 10 142913828922)
(p 11 70600674)
(p 12 76576500)
(p 13 "5537376230")
(p 14 837799)

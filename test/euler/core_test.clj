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

(p 1 233168N)
(p 2 4613732N)
(p 3 6857)
(p 4 906609)
(p 5 232792560N)

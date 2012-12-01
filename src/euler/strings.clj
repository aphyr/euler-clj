(ns euler.strings)

(defn palindrome?
  "Is x a palindrome?"
  [x]
  (let [s (str x)]
    (= (reverse s) (seq s))))

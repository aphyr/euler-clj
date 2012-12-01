(ns euler.strings)

(defn split-by 
  "Given a string, splits it into a sequence of strings separated by char c."
  [c string]
  (map (partial apply str)
       (remove empty?
               (reduce (fn [parts char]
                         (if (= c char)
                           (conj parts [])
                           (assoc parts 
                                  (max 0 (dec (count parts)))
                                  (conj (last parts) char))))
                       [[]]
                       string))))

(defn palindrome?
  "Is x a palindrome?"
  [x]
  (let [s (str x)]
    (= (reverse s) (seq s))))

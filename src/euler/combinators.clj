(ns euler.combinators)

(defn and*
  "Given functions f, g, h, ..., returns a function a(x, y, ...) which returns
  the first falsy result of (f x y ...), (g x y ...), etc, or the value of the final function evaluated, or true if there are no functions."
  [& fs]
  (fn [& args]
    (reduce (fn [passed? f] (and passed? (apply f args))) 
            true fs)))

(defn or*
  "Given functions f, g, h, ..., returns a function o(x, y, ...) which returns
  the first truthy result of (f x y ...), (g x y ...), etc, or the value of the final function evaluated, or nil if there are no functions."
  [& fs]
  (fn [& args]
    (reduce (fn [passed? f] (or passed? (apply f args))) 
            nil fs)))

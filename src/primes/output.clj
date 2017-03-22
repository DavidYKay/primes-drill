(ns primes.output
  [:require [clojure.string :as string]])

(def vertical-divider "|")
(def horizontal-divider "-")

(defn repeat-string [c n]
  (apply str (repeat n c)))

(defn header-divider [cell-width num-primes]
  (str
   (repeat-string " " (inc cell-width))
   (repeat-string horizontal-divider (inc (* num-primes (inc cell-width))))))

(defn pad-entry [cell-width e]
  (let [len (count (str e))]
    (if (< len cell-width)
      (str (reduce str (repeat (- cell-width len) " ")) e)
      e)))

(defn cell [cell-width product]
  (pad-entry cell-width product))

(defn row [cell-width prime product-row]
  (string/join " "
               (flatten [(cell cell-width prime)
                         vertical-divider
                         (for [product product-row]
                           (cell cell-width product))])))

(defn header [cell-width primes]
  (str
   (repeat-string " " (* 2 cell-width))
   (string/join " "
                (map #(cell cell-width %) primes))))

(defn format-prime-table [{:keys [primes products]}]
  (let [cell-width (->> products
                          flatten
                          (apply max)
                          str
                          count)]
    (loop [accum [(header cell-width primes)
                   (header-divider cell-width (count primes))]
           primes primes
           products products]
      (if (or (empty? primes)
              (empty? products))
        (string/join "\n" accum)
        (recur
         (conj accum (row cell-width (first primes) (first products)))
         (rest primes)
         (rest products))))))

"        2   3   5   7  11  13  17  19  23  29"
"    -----------------------------------------"
"  2 |   4   6  10  14  22  26  34  38  46  58"
"  3 |   6   9  15  21  33  39  51  57  69  87"
"  5 |  10  15  25  35  55  65  85  95 115 145"
"  7 |  14  21  35  49  77  91 119 133 161 203"
" 11 |  22  33  55  77 121 143 187 209 253 319"
" 13 |  26  39  65  91 143 169 221 247 299 377"
" 17 |  34  51  85 119 187 221 289 323 391 493"
" 19 |  38  57  95 133 209 247 323 361 437 551"
" 23 |  46  69 115 161 253 299 391 437 529 667"
" 29 |  58  87 145 203 319 377 493 551 667 841"

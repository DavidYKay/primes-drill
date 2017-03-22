(ns primes.output
  [:require [clojure.string :as string]])

(def vertical-divider "|")

(defn dividing-string [n]
  (apply str (repeat n "-")))

(defn pad-entry [e desired-len]
  (let [len (count (str e))]
    (if (< len desired-len)
      (str (reduce str (repeat (- desired-len len) " ")) e)
      e)))

(defn format-prime-table [{:keys [primes products]}]
  (let [column-width (->> products
                          flatten
                          (apply max)
                          str
                          count)
        header (string/join " " primes)]
    (loop [accum [header (dividing-string (count header))]
           primes primes
           products products]
      (if (or (empty? primes)
              (empty? products))
        (string/join "\n" accum)
        (recur
         (conj accum (string/join " " [(first primes) vertical-divider
                                       (string/join " " (first products))
                                       ]))
         (rest primes)
         (rest products))))))


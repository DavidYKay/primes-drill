(ns primes.core
  [:require [clojure.string :as string]])

(def sieve-bounds (for [i (range 1 (Math/log10 Integer/MAX_VALUE))]
                    (num-primes-below (Math/pow 10 (Math/ceil i)))))

(defn square [x]
  (* x x))

(defn avg
  "Finds the truncated integer mean"
  [& args]
  (/ (reduce + args)
     (count args)))

(defn num-primes-below
  "How many primes are below N?"
  [n]
  ;; FIXME: this is an APPROXIMATION, based on Gauss & Legendre
  ;; https://en.wikipedia.org/wiki/Prime-counting_function
  (int (/ n
          (Math/log n))))

(defn print-array [a]
  (println (->> a
                (map str)
                (string/join ", "))))

(defn find-sieve-upper-bound
  "Given a number of primes to find, K, return the upper bound we will need to sieve for."
  [k]
  (loop [bot 0
         top (* k 10)
         ;top Integer/MAX_VALUE
         ]
    (if (= top (inc bot))
      "DONE"
    (let [mid (int (avg bot top))
          n (num-primes-below mid)]
      (println (format "[%s - %s]" bot top))
      (if (and (> n k)
               ;(< neighbor k)
               )
        mid
        (if (< n k)
          (recur bot mid)
          (recur mid top)))))))

(defn prime-sieve
  "Use a sieve to find all primes up to N.
  Implemented using SIeve of Eratosthenes
  https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
  Very imperative style for now. Can come back later and make it more functional."
  [n]
  ;; Input: an integer n > 1.
  ;;  
  ;;  Let A be an array of Boolean values, indexed by integers 2 to n, initially all set to true.
  ;(let [array (make-array Boolean/TYPE n)]
  (let [array (into-array (repeat n true))]
    ;;  for i = 2, 3, 4, ..., not exceeding √n:
    (doseq [i (range 2 (Math/sqrt n))]
      ;; if A[i] is true:
      (when (aget array i)
        ;;      for j = i2, i2+i, i2+2i, i2+3i, ..., not exceeding n:
        (doseq [j (range (square i) n i)]
          ;;        A[j] := false.
          (aset array j false))))

    ;;  Output: all i such that A[i] is true.
    (->> array
         (map-indexed (fn [i x] [i x]))
         (drop 2)
         (filter (fn [[i x]] (true? x)))
         (map first))))

(defn get-primes [k]
  "Find k primes"
  (let [upper-bound (find-sieve-upper-bound k)]
    (take k (prime-sieve upper-bound))))

(defn print-prime-table []

  )

;; Notes 
;; ● Consider complexity. How fast does your code run? How does it scale? 
;; ● Consider cases where we want ​N ​ primes. 
;; ● Do not use the Prime class from stdlib (write your own code). 
;; ● Write tests. Try to demonstrate TDD/BDD. 
;; ● If you’re using external dependencies please specify those dependencies and how to install them. 
;; ● Please ​package your code​, OR include running instructions. 

(defn -main
  "Prints multiplication table of the first 10 prime numbers."
  [x]
  (print-prime-table))

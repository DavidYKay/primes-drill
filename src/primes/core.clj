(ns primes.core
  [:require [primes.output :refer [format-prime-table]]])

(defn num-primes-below
  "How many primes are below N?

  Note: this implementation is an APPROXIMATION, based on Gauss & Legendre
  The good news is that it under-counts, so we will never end up with too low of a ceiling / too few primes. We will, however, end up doing a bit of extra computation in prime-sieve.
  https://en.wikipedia.org/wiki/Prime-counting_function"
  [n]
  (int (/ n
          (Math/log n))))

(def sieve-bounds (for [i (range 1 (Math/log10 Integer/MAX_VALUE))]
                    (let [sieve-bound (int (Math/pow 10 (Math/ceil i)))
                          n-primes (num-primes-below sieve-bound)]
                      [n-primes sieve-bound])))

(defn square [x]
  (* x x))


(defn find-sieve-upper-bound
  "Given a number of primes to find, K, return the upper bound we will need to sieve for.
  Note: this could definitely be optimized. We use a lookup table that only has every power of 10 present."
  [k]
  (->> sieve-bounds
       (filter (fn [[n-primes sieve-bound]]
                 (> n-primes k)))
       first
       last))

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

(defn prime-table
  "Multiplication table of the first k prime numbers."
  [k]
  (let [primes (get-primes k)]
    {:primes primes
     :products (for [a primes]
                 (for [b primes]
                   (* a b)))}))
;; Notes 
;; ● Consider complexity. How fast does your code run? How does it scale? 
;; ● Consider cases where we want ​N ​ primes. 
;; ● Do not use the Prime class from stdlib (write your own code). 
;; ● Write tests. Try to demonstrate TDD/BDD. 
;; ● If you’re using external dependencies please specify those dependencies and how to install them. 
;; ● Please ​package your code​, OR include running instructions. 

(defn print-prime-table
  "Prints multiplication table of the first k prime numbers."
  [k]
  (println (format-prime-table (prime-table k))))

(defn -main
  "Prints multiplication table of the first 10 prime numbers."
  [& args]
  (print-prime-table 10))

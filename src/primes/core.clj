(ns primes.core)

(defn primes-below-n
  "How many primes are below N?"
  [n]
  ;; FIXME: this is an APPROXIMATION, based on Gauss & Legendre
  ;; https://en.wikipedia.org/wiki/Prime-counting_function
  (int (/ n
          (Math/log n))))

(defn prime-sieve
  "Use a sieve to find all primes up to N"
  [n]

  )

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

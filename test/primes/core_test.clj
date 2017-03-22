(ns primes.core-test
  (:require [midje.sweet :refer :all]
            [primes.core :refer :all]))

(facts "Prime Counting"
       (future-fact "Prime counting works 100% accurately"
             (num-primes-below 100) => 25)
       (fact "Prime counting works as described by Gauss"
             (num-primes-below 100) => 21))

(facts "Prime Sieve"
       (fact "Can find primes below 30"
             (prime-sieve 30) => [2 3 5 7 11 13 17 19 23 29])

       (fact "Can find primes below 100"
             (prime-sieve 100) => [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97]))

(facts "Sieve Upper Bound"
       (fact "Can find sieve upper bound, approximately"
             (find-sieve-upper-bound 20) => 100)
       (future-fact "Can find sieve upper bound, exactly"
             (find-sieve-upper-bound 25) => 100))

(facts "Basic Arithmetic"
       (fact "Can find average"
             (avg 0 100) => 50
             (avg 10 20) => 15
             (avg 10 11) => 21/2))

(facts "Prime Multiplication Table"
       (fact "Can find basic multiplication table"
             (prime-table 10) => '((4 6 10 14 22 26 34 38 46 58)
                                   (6 9 15 21 33 39 51 57 69 87)
                                   (10 15 25 35 55 65 85 95 115 145)
                                   (14 21 35 49 77 91 119 133 161 203)
                                   (22 33 55 77 121 143 187 209 253 319)
                                   (26 39 65 91 143 169 221 247 299 377)
                                   (34 51 85 119 187 221 289 323 391 493)
                                   (38 57 95 133 209 247 323 361 437 551)
                                   (46 69 115 161 253 299 391 437 529 667) (58 87 145 203 319 377 493 551 667 841))

             ))

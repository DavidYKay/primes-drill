(ns primes.core-test
  (:require [midje.sweet :refer :all]
            [primes.core :refer :all]))

(facts "Prime Counting"
       (fact "Prime counting works 100% accurately"
             (primes-below-n 100) => 25)
       (fact "Prime counting works as described by Gauss"
             (primes-below-n 100) => 21))

(facts "Prime Sieve"
       (fact "Can find primes below 30"
             (prime-sieve 30) => [2 3 5 7 11 13 17 19 23 29])

       (fact "Can find primes below 100"
             (prime-sieve 100) => [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97])
       
       )

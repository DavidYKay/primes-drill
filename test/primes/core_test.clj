(ns primes.core-test
  (:require [midje.sweet :refer :all]
            [primes.core :refer :all]))

(facts "Prime Counting"
       (fact "Prime counting works 100% accurately"
             (primes-below-n 100) => 25)
       (fact "Prime counting works as described by Gauss"
             (primes-below-n 100) => 21))

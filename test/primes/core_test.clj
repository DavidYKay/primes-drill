(ns primes.core-test
  (:require [midje.sweet :refer :all]
            [primes.core :refer :all]))


(fact "Addition works"
 (+ 1 1) => 2)

(fact "Failing test"
 (+ 3 2) => 4)

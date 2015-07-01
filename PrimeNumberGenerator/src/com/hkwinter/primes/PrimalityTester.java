package com.hkwinter.primes;

public class PrimalityTester {
	
	public Boolean isPrime(Integer inputNumber) {
		
		// Negative Numbers and 1 are not prime.
		// 2 and 3 are prime.
		// Multiples of primes (i.e. 2 and 3) are not prime.
		if (inputNumber <= 1) return false;  
		else if (inputNumber <= 3) return true; 
		else if (inputNumber % 2 == 0 || inputNumber % 3 == 0) return false; 
		
		// Integers less than 5 accounted for above.
		// When testing divisors, may stop at the Square Root due to redundancy
		//   e.g.: 36 -> 2,13  3,12  4,9  6,6  9,4  ...
		//  i < SQRT(n)  -->  i^2 < n
		// Skip even numbers - they are all divisible by 2
		int i = 5;
		while (i*i <= inputNumber) {
			if (inputNumber % i == 0) return false;
			i += 2; 
		}
		
		return true;
	}
}

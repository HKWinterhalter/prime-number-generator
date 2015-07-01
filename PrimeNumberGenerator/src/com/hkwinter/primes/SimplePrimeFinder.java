package com.hkwinter.primes;

import java.util.ArrayList;
import java.util.List;

public class SimplePrimeFinder implements PrimeNumberGenerator {
	
	public List<Integer> generate(int rangeStart, int rangeEnd) {
		
		if (rangeStart > rangeEnd) {
			int swap = rangeEnd;
			rangeEnd = rangeStart;
			rangeStart = swap;
		}
		
		PrimalityTester primalityTester = new PrimalityTester();
		List<Integer> primes = new ArrayList<Integer>();
		
		for (int i = rangeStart; i <= rangeEnd; i++) {
			if (primalityTester.isPrime(i)) {
				primes.add(i);
			}
		}
		
		return primes;
	}
}

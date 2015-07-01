package com.hkwinter.primes;

import java.util.ArrayList;
import java.util.List;

public class SieveOfSundaram implements PrimeNumberGenerator {
	
	public List<Integer> generate(int rangeStart, int rangeEnd) {
		
		if (rangeStart > rangeEnd) {
			int temp = rangeStart;
			rangeStart = rangeEnd;
			rangeEnd = temp;
		}
		if (rangeStart < 2) rangeStart = 1;
		if (rangeEnd < 2) rangeEnd = 1;
		
		int limit = (rangeEnd+1)/2;
		Boolean[] primes = new Boolean[rangeEnd+1];
		for (int i = 0; i < rangeEnd+1; i++) {
			primes[i] = true;
		}
		
		//https://en.wikipedia.org/wiki/Sieve_of_Sundaram
        for (int i = 1; i < limit; i++) {
        	for (int j = i; j <= (limit - i) / (2 * i + 1); j++){
        		primes[(i + j + 2 * i * j)] = false;
        	}
        }
		
		List<Integer> primesList = new ArrayList<Integer>();
		if (rangeStart <= 2 && rangeEnd >= 2) primesList.add(2);
		for (int i = 1; i < limit; i++) {
			if(2*i+1 >= rangeStart && primes[i] == true) {
				primesList.add(2*i+1);
			}
		}
		return primesList;
	}
}

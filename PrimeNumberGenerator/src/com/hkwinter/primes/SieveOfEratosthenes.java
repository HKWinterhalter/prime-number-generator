package com.hkwinter.primes;

import java.util.ArrayList;
import java.util.List;

public class SieveOfEratosthenes implements PrimeNumberGenerator {
	
	public List<Integer> generate(int rangeStart, int rangeEnd) {
		
		if (rangeStart > rangeEnd) {
			int temp = rangeStart;
			rangeStart = rangeEnd;
			rangeEnd = temp;
		}
		if (rangeStart < 2) rangeStart = 1;
		if (rangeEnd < 2) rangeEnd = 1;
		
		Boolean[] primes = new Boolean[rangeEnd+1];
		for (int i = 0; i < rangeEnd+1; i++) {
			primes[i] = true;
		}
		primes[0] = false;
		primes[1] = false;
		
		//https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
		for (int i = 2; i*i < rangeEnd+1; i++) {
			if (primes[i] == true){
				for (int j = i*i; j < rangeEnd+1; j += i) {
					primes[j] = false;
				}
			}
		}
		
		List<Integer> primesList = new ArrayList<Integer>();
		for (int i = 0; i < rangeEnd+1; i++) {
			if(i >= rangeStart && primes[i] == true) {
				primesList.add(i);
			}
		}
		return primesList;
	}
}

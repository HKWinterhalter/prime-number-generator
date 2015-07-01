package com.hkwinter.primes;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PrimeNumberGeneratorTest {

	private static Integer[] first26Primes = new Integer[]{ 
			2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,	97, 101};
	private static Integer[] primesIn7900to7920 = new Integer[] {7901, 7907, 7919};
	private static Integer[] noPrimes = new Integer[] {};
	private static Integer[] oneResult = new Integer[] {7};
	
	private Integer[] expectedPrimes;
	private Integer rangeStart;
	private Integer rangeEnd;
	private SimplePrimeFinder primeFinder;
	private SieveOfEratosthenes sieveOfE;
	private SieveOfSundaram sieveOfS;
	
	public PrimeNumberGeneratorTest(Integer[] expectedPrimes, Integer rangeStart, Integer rangeEnd){
		this.expectedPrimes = expectedPrimes;
		this.rangeStart = rangeStart;
		this.rangeEnd = rangeEnd;
	}
	
	@Parameterized.Parameters
	public static Collection testPrimesList() {
		return Arrays.asList(new Object[][] {
			{ first26Primes, -50, 101 },
			{ first26Primes, 101, -50 }, //backwards inputs
			{ primesIn7900to7920, 7900, 7920 },
			{ noPrimes, -200, 0}, //negatives
			{ noPrimes, 90, 96}, //no prime range
			{ oneResult, 7, 7},
			{ oneResult, 6, 8},
			{ noPrimes, Integer.MAX_VALUE+1, Integer.MAX_VALUE+1}
		});
	}
	
	@Before
	public void init() {
		primeFinder = new SimplePrimeFinder();
		sieveOfE = new SieveOfEratosthenes();
		sieveOfS = new SieveOfSundaram();
	}
	
	@Test
	public void testFindPrimes() {
		List<Integer> actualPrimeList;
		Integer[] actualPrimes;
		
		// All 3 implementations are tested
		actualPrimeList = primeFinder.generate(rangeStart,rangeEnd);
		actualPrimes = actualPrimeList.toArray(new Integer[actualPrimeList.size()]);
		assertArrayEquals(expectedPrimes, actualPrimes);
		
		actualPrimeList = sieveOfE.generate(rangeStart,rangeEnd);
		actualPrimes = actualPrimeList.toArray(new Integer[actualPrimeList.size()]);
		assertArrayEquals(expectedPrimes, actualPrimes);
		
		actualPrimeList = sieveOfS.generate(rangeStart,rangeEnd);
		actualPrimes = actualPrimeList.toArray(new Integer[actualPrimeList.size()]);
		assertArrayEquals(expectedPrimes, actualPrimes);
	}

}

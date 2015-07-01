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
public class PrimalityTesterTest {
	
	private static Integer[] first26Primes = new Integer[]{ 
			2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,	97, 101};
	
	private Boolean expectedPrimality;
	private Integer inputNumber;
	private PrimalityTester primalityTester;
	
	public PrimalityTesterTest(Integer inputNumber, Boolean expectedPrimality) {
		this.expectedPrimality = expectedPrimality;
		this.inputNumber = inputNumber;
	}
	
	// Creates a parameter list
		//  (e.g. {1, false}, {2, true}, {3, true} ... )
	@Parameterized.Parameters
	public static Collection testPrimesList() {
		int rows = 152;
		int columns = 2;
		int offset = 50; // Tests all integers between -50 and 101
		Object[][] integerArray = new Object[rows][columns];
		
		// Initialize all False
		for (int i = 0; i < rows; i++) {
			integerArray[i][0] = i - offset;
			integerArray[i][1] = false;
		}
		// Set all primes to True
		for (int i = 0; i < first26Primes.length; i++) {
			int prime = first26Primes[i];
			integerArray[prime+offset][1] = true;
		}
		
		return Arrays.asList(integerArray);
	}
	
	@Before
	public void init() {
		primalityTester = new PrimalityTester();
	}
	
	@Test
	public void testIsPrime() {
		assertEquals(expectedPrimality, primalityTester.isPrime(inputNumber));
	}

}

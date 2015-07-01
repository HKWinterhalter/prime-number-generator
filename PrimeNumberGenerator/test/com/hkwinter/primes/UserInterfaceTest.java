package com.hkwinter.primes;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UserInterfaceTest {
	

	@Test
	public void testGetIntegerInput() {
		
		int expectedValue = 5;
		
		//Test single integer input
		ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(expectedValue).getBytes());
		System.setIn(in);
		int actualInput = UserInterface.getIntegerInput(in,"");
		assertEquals(expectedValue, actualInput);
		
		//Test non-integer
		in = new ByteArrayInputStream("a".getBytes());
		System.setIn(in);
		actualInput = UserInterface.getIntegerInput(in,"");
		assertEquals(0, actualInput);
		
		System.setIn(System.in);
	}

	@Test
	public void testPrintPrimes() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String printPrimesPattern = ".*(\\.|:)(\\s*[0-9]*)*(.*\\s*)*";
		List<Integer> primes = new ArrayList<Integer>();
		
		//Test empty list
		UserInterface.printPrimes(primes);
		assertTrue(outContent.toString().matches(printPrimesPattern));
		
		//Test normal list
		primes.add(2);
		primes.add(3);
		primes.add(5);
		UserInterface.printPrimes(primes);
		assertTrue(outContent.toString().matches(printPrimesPattern));
		System.setOut(System.out);
	}

}

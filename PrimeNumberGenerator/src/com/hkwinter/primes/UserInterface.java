package com.hkwinter.primes;

import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface {

	private static final String WELCOME_MESSAGE = "Welcome to Prime Number Generator.";
	private static final String EXPLANATION = "This program will find all prime numbers in a given integer range.";
	private static final String PROMPT = "Please input each integer...";
	private static final String START_INT = "Start Integer: ";
	private static final String END_INT = "End Integer: ";
	private static final String EXIT_MESSAGE = "Goodbye.";
	private static final String NEGATIVE_WARNING = "A prime number can't be negative - setting range start to 1.";
	private static final String CONTINUE = "Any key to continue or 'q' to quit.";
	
	private static int rangeStart;
	private static int rangeEnd;
	private static double durationInMilliSeconds;
	private static Scanner scanner;
	
	public static void main(String[] args) {
		UserInterface.begin();
	}
	
	static public void begin() {
		
		System.out.println(WELCOME_MESSAGE);
		System.out.println(EXPLANATION);
		
		mainExecution();
		exit(); 
	}
	
	private static void mainExecution() {
		
		do {
			System.out.println();
			PrimeNumberGenerator primeNumberGenerator = chooseAlgorithm();
			
			scanner = new Scanner(System.in);
			promptIntegerRange();
			
			double startTime = System.nanoTime();
			List<Integer> primes = primeNumberGenerator.generate(rangeStart, rangeEnd);
			double endTime = System.nanoTime();
			durationInMilliSeconds = (endTime - startTime) / 1000000;
			
			printPrimes(primes);
			
			System.out.println(CONTINUE);
		} while (!scanner.hasNext("q"));
		
		scanner.close();
	}

	private static PrimeNumberGenerator chooseAlgorithm() {
		
		PrimeNumberGenerator primeNumberGenerator;
		System.out.println("Please select the algorithm you would like to use: ");
		System.out.println("1. Iterative Primality Checker");
		System.out.println("2. Sieve of Eratosthenes");
		System.out.println("3. Sieve of Sundaram");
		System.out.println("");
		int choice = getIntegerInput(System.in, "Option #: ");
		switch (choice) {
			case 1: primeNumberGenerator = new SimplePrimeFinder();
			case 2: primeNumberGenerator = new SieveOfEratosthenes();
			case 3: primeNumberGenerator = new SieveOfSundaram();
			default: primeNumberGenerator = new SimplePrimeFinder();
		}
		
		return primeNumberGenerator;
	}
	
	public static void promptIntegerRange() {
		System.out.println(PROMPT);
		rangeStart = getIntegerInput(System.in, START_INT);
		rangeEnd = getIntegerInput(System.in, END_INT);
		if (rangeStart > rangeEnd) {
			int temp = rangeStart;
			rangeStart = rangeEnd;
			rangeEnd = temp;
		}
		if (rangeStart < 1) {
			rangeStart = 1;
			System.out.println(NEGATIVE_WARNING);
		}
		System.out.println();
	}
	
	public static int getIntegerInput(InputStream in, String message) {
		System.out.println(message);
		scanner = new Scanner(in);
		try {
			while (!scanner.hasNext("-?[0-9]{1,7}")) {
				if (scanner.hasNext("-?[0-9]+")) System.out.println ("Please try an integer with 7 or less digits.");
				else System.out.println("That's not an integer!");
	            System.out.println();
	            System.out.println(message);
	            scanner.nextLine();
	        }
		} catch (NoSuchElementException e) {
			return 0;
		}
		
		return scanner.nextInt();
	}
	
	public static void printPrimes(List<Integer> primes) {
		
		if(primes.size() == 0) {
			System.out.println("No primes found between "+rangeStart+" and "+rangeEnd+".");
			return;
		}
		
		System.out.println("Primes found between "+rangeStart+" and "+rangeEnd+": ");
		int i = 1;
		int longestPrimeLength = String.valueOf(primes.get(primes.size()-1)).length();
		for (Integer prime : primes) {
			System.out.print(String.format("%"+(longestPrimeLength+1)+"d ",prime));
			if (i++ % 20 == 0) System.out.println();
		}
		System.out.println();
		System.out.println("Total found: "+primes.size());
		System.out.println("Duration (ms): "+durationInMilliSeconds);
		System.out.println();
	}
	
	private static void exit() {
		System.out.println();
		System.out.println(EXIT_MESSAGE);
	}
}

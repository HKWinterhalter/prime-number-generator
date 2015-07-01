package com.hkwinter.primes;

import java.util.List;

public interface PrimeNumberGenerator {
	List<Integer> generate(int rangeStart, int rangeEnd);
}

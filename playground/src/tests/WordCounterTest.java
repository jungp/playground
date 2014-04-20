package tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordCounterTest {

	private static long fac(long n){
		if (n == 1){
			return 1;
		}
		return n * fac(n - 1);
	}
	
	@Test
	public void testSomething(){
		assertEquals(24, fac(4));
	}

}

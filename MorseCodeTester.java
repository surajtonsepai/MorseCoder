package edu.metrostate.ics240.p5.STP059.morse;

import static org.junit.Assert.*;

import org.junit.Test;

public class MorseCodeTester {

	@Test
	public void testEncoder() {
		
		assertEquals("**** * *-** *-** --- --**--/*-- --- *-* *-** -** -*-*--", MorseCode.encode("Hello, World!"));
		
	}
	
	@Test
	public void testDecoder() {
		
		assertEquals("HELLO, WORLD!", MorseCode.decode("**** * *-** *-** --- --**--/"     + "*-- --- *-* *-** -** -*-*--"));
		
	}
	
	@Test
	public void testEncodeException() {
		try {
			MorseCode.encode("Hello, World$"); // illegal character
			fail("Expected exception");
		} catch (IllegalArgumentException iae) {
			// expected
		}
	}
	
	@Test
	public void testDecodeExcpetion() {
		try {
			MorseCode.decode("**** * *-** *-** --- --**--/"      + "*-- --- *-* *-** -** -*-*--*"); // illegal character
			fail("Expected exception");
		} catch (IllegalArgumentException iae) {
			// expected
		}
	}	
}

package com.milens3.utility.sourcemap.encoder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.milens3.utility.sourcemap.encoder.VlqEncoder;

/**
 * Unit tests for the {@link VlqEncoder} class based on known values.
 * 
 * @author Frederick John Milens III
 * 
 */
public class VlqEncoderTest {

	/**
	 * Tests a basic encoding from the VLQ encoder.
	 */
	@Test
	public void testEncoding() {
		assertEquals("0C", new VlqEncoder().encode(42));
	}

	/**
	 * Tests encoding of a a zero value.
	 */
	@Test
	public void testZeroEncoding() {
		assertEquals("A", new VlqEncoder().encode(0));
	}

	/**
	 * Tests encoding of a negative value.
	 */
	@Test
	public void testNegativeEncoding() {
		assertEquals("hgE", new VlqEncoder().encode(-2048));
	}

	/**
	 * Tests encoding of a positive value.
	 */
	@Test
	public void testPositiveEncoding() {
		assertEquals("ggE", new VlqEncoder().encode(2048));
	}

	/**
	 * Tests encoding of a (relatively) large negative value (-32768).
	 */
	@Test
	public void testLargeNegativeEncoding() {
		assertEquals("hggC", new VlqEncoder().encode(-32768));
	}

	/**
	 * Tests encoding of a (relatively) large positive value (32768).
	 */
	@Test
	public void testLargePositiveEncoding() {
		assertEquals("gggC", new VlqEncoder().encode(32768));
	}
}

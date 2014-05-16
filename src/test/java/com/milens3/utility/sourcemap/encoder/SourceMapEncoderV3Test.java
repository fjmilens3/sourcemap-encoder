package com.milens3.utility.sourcemap.encoder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.milens3.utility.sourcemap.encoder.Mapping;
import com.milens3.utility.sourcemap.encoder.Position;
import com.milens3.utility.sourcemap.encoder.SourceMapEncoderV3;

/**
 * Unit tests for the {@link SourceMapEncoderV3} class using known values.
 * 
 * @author Frederick John Milens III
 *  
 */
public class SourceMapEncoderV3Test {

	/**
	 * Tests encoding of a V3 sourcemap string based on a known result.
	 */
	@Test
	public void encodeSourceMapV3Mappings() {

		List<Mapping> mappings = new ArrayList<Mapping>();
		mappings.add(new Mapping("a", new Position(2, 2), new Position(4, 2)));
		mappings.add(new Mapping("a", new Position(2, 4), new Position(4, 4)));
		mappings.add(new Mapping("a", new Position(2, 5), new Position(4, 6)));
		mappings.add(new Mapping("a", new Position(2, 7), new Position(4, 7)));
		mappings.add(new Mapping("b", new Position(10, 19), new Position(5, 2)));
		mappings.add(new Mapping("b", new Position(11, 20), new Position(5, 12)));

		SourceMapEncoderV3 encoder = new SourceMapEncoderV3(mappings);
		SourceMapV3 result = encoder.encode();

		assertEquals(Arrays.asList("a", "b"), result.getSources());
		assertEquals(";;;;EAEE,EAAE,EAAC,CAAE;ECQY,UACC", result.getMappings());
	}
}

package com.milens3.utility.sourcemap.encoder;

import java.util.Collections;
import java.util.List;

/**
 * Basic representation of the encoded fields of a Javascript source map. The
 * information contained here can be combined with the remaining data in a
 * compile-to-JS implementation to produce a V3 sourcemap.
 * 
 * @author Frederick John Milens III
 * 
 */
public class SourceMapV3 {

	/**
	 * The list of source files referenced in the source mappings.
	 */
	private final List<String> sources;

	/**
	 * The string containing the encoded mappings for the source map.
	 */
	private final String mappings;

	/**
	 * Constructor.
	 * 
	 * @param sources
	 *            The list of source files referenced in the source mappings.
	 * @param mappings
	 *            The string containing the encoded mappings for the source map.
	 */
	public SourceMapV3(List<String> sources, String mappings) {
		this.sources = Collections.unmodifiableList(sources);
		this.mappings = mappings;
	}

	/**
	 * Returns the list of source files referenced in the source mappings.
	 * 
	 * @return the sources
	 */
	public List<String> getSources() {
		return sources;
	}

	/**
	 * Returns the string containing the encoded mappings for the source map.
	 * 
	 * @return the mappings
	 */
	public String getMappings() {
		return mappings;
	}

}

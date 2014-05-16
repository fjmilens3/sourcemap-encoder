package com.milens3.utility.sourcemap.encoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Encoder for generating a Javascript sourcemap for the V3 specification.
 * 
 * @author Frederick John Milens III
 * 
 */
public class SourceMapEncoderV3 {

	/**
	 * The mappings to use when generating the encoded source map.
	 */
	private final List<Mapping> mappings;

	/**
	 * The list of source files generated from the mapping information.
	 */
	private final List<String> sources = new ArrayList<String>();

	/**
	 * The {@link VlqEncoder} used to encode the variable length quantities.
	 */
	private final VlqEncoder encoder = new VlqEncoder();

	/**
	 * The {@link StringBuilder} used to build the mapping string.
	 */
	private final StringBuilder builder = new StringBuilder();

	/**
	 * The flag indicating if a comma should be written during the encoding.
	 */
	private boolean needsComma = false;

	/**
	 * The current line that is being written.
	 */
	private int writingLine = 0;

	/**
	 * The last mapped column that was generated (for VLQ generation).
	 */
	private int lastMappedColumn = 0;

	/**
	 * The last source column that was processed (for VLQ generation).
	 */
	private int lastSourceColumn = 0;

	/**
	 * The last source line that was processed (for VLQ generation).
	 */
	private int lastSourceLine = 0;

	/**
	 * The last source index that was processed (for VLQ generation).
	 */
	private int lastSourceIndex = 0;

	/**
	 * The source file currently being processed.
	 */
	private String sourceFile;

	/**
	 * The mapped position currently being processed.
	 */
	private Position mappedPosition;

	/**
	 * The source position currently being processed.
	 */
	private Position sourcePosition;

	/**
	 * The encoded source map (if generated).
	 */
	private SourceMapV3 encodedSourceMap;

	/**
	 * Constructor.
	 * 
	 * @param mappings
	 *            The mappings to use when generating the encoded source map.
	 */
	public SourceMapEncoderV3(List<Mapping> mappings) {
		this.mappings = mappings;
	}

	/**
	 * Encodes the provided mapping into an object representing the sourcemap
	 * information. If this method has already been called, the cached version
	 * of the mapping will be returned.
	 * 
	 * @return the {@link SourceMapV3}
	 */
	public SourceMapV3 encode() {
		if (encodedSourceMap == null) {
			for (Mapping mapping : mappings) {

				sourceFile = mapping.getSourceFile();
				mappedPosition = mapping.getMappedPosition();
				sourcePosition = mapping.getSourcePosition();

				processEmptyLines();
				processNeedsComma();
				processMappedColumn();
				processSourceFile();
				processSourceLine();
				processSourceColumn();

				needsComma = true;
			}
			encodedSourceMap = new SourceMapV3(sources, builder.toString());
		}
		return encodedSourceMap;
	}

	/**
	 * Processes any empty lines between the current mapping and current line.
	 */
	private void processEmptyLines() {
		while (writingLine < mappedPosition.getLine()) {
			lastMappedColumn = 0;
			needsComma = false;
			builder.append(";");
			writingLine++;
		}
	}

	/**
	 * Processes whether or not the encoding needs a comma inserted.
	 */
	private void processNeedsComma() {
		if (needsComma) {
			builder.append(",");
			needsComma = false;
		}
	}

	/**
	 * Processes the current mapped column into the mapped string.
	 */
	private void processMappedColumn() {
		encodeDifference(mappedPosition.getColumn(), lastMappedColumn);
		lastMappedColumn = mappedPosition.getColumn();
	}

	/**
	 * Processes the current source file into the mapped string.
	 */
	private void processSourceFile() {
		int sourceIndex = getSourceIndex(sourceFile);
		encodeDifference(sourceIndex, lastSourceIndex);
		lastSourceIndex = sourceIndex;
	}

	/**
	 * Processes the source line into the mapped string.
	 */
	private void processSourceLine() {
		encodeDifference(sourcePosition.getLine(), lastSourceLine);
		lastSourceLine = sourcePosition.getLine();
	}

	/**
	 * Processes the source column into the mapped string.
	 */
	private void processSourceColumn() {
		encodeDifference(sourcePosition.getColumn(), lastSourceColumn);
		lastSourceColumn = sourcePosition.getColumn();
	}

	/**
	 * Encodes a difference between two values into a variable-length quantity.
	 * 
	 * @param newValue
	 *            the new value
	 * @param oldValue
	 *            the old value
	 */
	private void encodeDifference(int newValue, int oldValue) {
		builder.append(encoder.encode(newValue - oldValue));
	}

	/**
	 * Returns the source index for the specified source file.
	 * 
	 * @param sourceFile
	 *            the source file
	 * @return the index
	 */
	private int getSourceIndex(String sourceFile) {
		int sourceIndex = sources.indexOf(sourceFile);
		if (sourceIndex == -1) {
			sources.add(sourceFile);
			sourceIndex = sources.size() - 1;
		}
		return sourceIndex;
	}
}

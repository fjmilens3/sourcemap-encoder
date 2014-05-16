package com.milens3.utility.sourcemap.encoder;

/**
 * A single mapping from a source file and position to another position in a
 * generated file.
 * 
 * @author Frederick John Milens III
 * 
 */
public final class Mapping {

	/**
	 * The source file that this mapping is generated from.
	 */
	private final String sourceFile;

	/**
	 * The source position in the source file.
	 */
	private final Position sourcePosition;

	/**
	 * The mapped position in the generated file.
	 */
	private final Position mappedPosition;

	/**
	 * Constructor.
	 * 
	 * @param sourceFile
	 *            The source file that this mapping is generated from.
	 * @param sourcePosition
	 *            The source position in the source file.
	 * @param mappedPosition
	 *            The mapped position in the generated file.
	 */
	public Mapping(String sourceFile, Position sourcePosition,
			Position mappedPosition) {
		this.sourceFile = sourceFile;
		this.sourcePosition = sourcePosition;
		this.mappedPosition = mappedPosition;
	}

	/**
	 * Returns the source file that this mapping is generated from.
	 * 
	 * @return the source file
	 */
	public String getSourceFile() {
		return sourceFile;
	}

	/**
	 * Returns the source position in the source file.
	 * 
	 * @return the source position
	 */
	public Position getSourcePosition() {
		return sourcePosition;
	}

	/**
	 * Returns the mapped position in the generated file.
	 * 
	 * @return the mapped position
	 */
	public Position getMappedPosition() {
		return mappedPosition;
	}
}

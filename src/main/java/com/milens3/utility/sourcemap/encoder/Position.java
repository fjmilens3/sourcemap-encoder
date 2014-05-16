package com.milens3.utility.sourcemap.encoder;

/**
 * Class representing a position in a source or generated file.
 * 
 * @author Frederick John Milens III
 * 
 */
public final class Position {

	/**
	 * The line number for the position.
	 */
	private final int line;

	/**
	 * The column number for the position.
	 */
	private final int column;

	/**
	 * Constructor.
	 * 
	 * @param line
	 *            The line number for the position.
	 * @param column
	 *            The column number for the position.
	 */
	public Position(int line, int column) {
		this.line = line;
		this.column = column;
	}

	/**
	 * Returns the line number for the position.
	 * 
	 * @return the line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * The column number for the position.
	 * 
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
}

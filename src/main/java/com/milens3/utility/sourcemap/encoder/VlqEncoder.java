package com.milens3.utility.sourcemap.encoder;

/**
 * A basic implementation of a base-64 variable-length quantity (VLQ) encoder
 * used when creating Javascript sourcemaps.
 * 
 * @author Frederick John Milens III
 * 
 */
final class VlqEncoder {

	/**
	 * The VLQ bit shift value.
	 */
	private final static int VLQ_SHIFT = 5;

	/**
	 * The VLQ continuation bit value used to indicate more data is pending.
	 */
	private final static int VLQ_CONTINUATION_BIT = 1 << VLQ_SHIFT;

	/**
	 * The VLQ value mask used to mask out non-value bits when encoding.
	 */
	private final static int VLQ_VALUE_MASK = VLQ_CONTINUATION_BIT - 1;

	/**
	 * The string of base64 characters to encode.
	 */
	private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	/**
	 * Encodes an integer into a base64-encoded VLQ string.
	 * 
	 * @param value
	 *            the value to encode
	 * @return the encoded string
	 */
	public String encode(int value) {
		int signBit = value < 0 ? 1 : 0;
		int valueToEncode = (Math.abs(value) << 1) + signBit;
		StringBuilder answer = new StringBuilder();
		while (valueToEncode != 0 || answer.length() == 0) {
			int nextChunk = valueToEncode & VLQ_VALUE_MASK;
			valueToEncode = valueToEncode >> VLQ_SHIFT;
			if (valueToEncode != 0) {
				nextChunk |= VLQ_CONTINUATION_BIT;
			}
			answer.append(BASE64_CHARS.charAt(nextChunk));
		}
		return answer.toString();
	}
}

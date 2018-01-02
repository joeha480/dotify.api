package org.daisy.dotify.api.writer;

/**
 * Provides internet media types for some types. 
 */
public enum WriterFormat {
	/**
	 * PEF - portable embosser format
	 */
	PEF("application/x-pef+xml"),
	/**
	 * Plain text
	 */
	TEXT("text/plain");

	private final String mimetype;

	WriterFormat(String mimetype) {
		this.mimetype = mimetype;
	}

	/**
	 * Gets the internet media type for the format.
	 * @return returns the internet media type
	 */
	public String getMediaType() {
		return mimetype;
	}

}

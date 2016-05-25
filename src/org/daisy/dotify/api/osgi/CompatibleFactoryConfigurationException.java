package org.daisy.dotify.api.osgi;

public class CompatibleFactoryConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3316477128332828379L;

	public CompatibleFactoryConfigurationException() {
	}

	public CompatibleFactoryConfigurationException(String message) {
		super(message);
	}

	public CompatibleFactoryConfigurationException(Throwable cause) {
		super(cause);
	}

	public CompatibleFactoryConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}

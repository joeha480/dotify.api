package org.daisy.dotify.api.translator;

/**
 * Provides a translator mode object. The purpose of this object isn't to limit the
 * possible values of this type, the purpose is make it clear that the type passed
 * is a translator mode. 
 * 
 * @author Joel Håkansson
 */
public class TranslatorMode {
	private final String mode;
	
	/**
	 * Provides a catalog of commonly used translator modes.
	 */
	public enum ModeCatalog {
		UNCONTRACTED(new TranslatorMode("uncontracted"));
		private final TranslatorMode mode;
		private ModeCatalog(TranslatorMode mode) {
			this.mode = mode;
		}
		
		/**
		 * Gets the translator mode.
		 * @return returns the translator mode
		 */
		public TranslatorMode getTranslatorMode() {
			return mode;
		}
	}

	/**
	 * Creates a new translator mode based on the supplied string
	 * @param name the name of this translator mode
	 */
	public TranslatorMode(String name) {
		this.mode = name;
	}
	
	/**
	 * Gets the string representing this translator mode.
	 * @return returns the string value corresponding to this translator mode
	 */
	public String getName() {
		return mode;
	}

}
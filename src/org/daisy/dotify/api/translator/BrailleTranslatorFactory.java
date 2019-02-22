package org.daisy.dotify.api.translator;

/**
 * Provides a factory for braille translation and hyphenation.
 * @author Joel Håkansson
 *
 */
public interface BrailleTranslatorFactory {
	/**
	 * Defines bypass mode
	 * @deprecated use {@link TranslatorType}
	 */
	@Deprecated
	public static final String MODE_BYPASS = "bypass";
	/**
	 * Defines uncontracted mode
	 * @deprecated use {@link TranslatorType}
	 */
	@Deprecated
	public static final String MODE_UNCONTRACTED = "uncontracted";
	
	
	/**
	 * Creates a new translator with the given specification
	 * @param locale the translator locale
	 * @param mode the translator grade
	 * @return returns a new translator
	 * @throws TranslatorConfigurationException if the specification is not supported
	 */
	public default BrailleTranslator newTranslator(String locale, String mode) throws TranslatorConfigurationException {
		return newTranslator(new TranslatorSpecification(locale, mode));
	}
	
	public BrailleTranslator newTranslator(TranslatorSpecification spec) throws TranslatorConfigurationException;

}

package org.daisy.dotify.api.translator;

/**
 * Provides a factory for text borders.
 * 
 * @author Joel Håkansson
 */
public interface TextBorderFactory {
	
	/**
	 * Mode feature. The corresponding value should be
	 * a string. It is recommended that an implementation
	 * supports at least one of the values provided by the
	 * StyleValues enum.
	 */
	public static final String FEATURE_MODE = "mode";
	
	/**
	 * <p>Border width feature. The corresponding value should be
	 * a string ("regular", "wide"). If this value is set to "wide",
	 * it supports creating text borders with more than one character
	 * in width.</p>
	 * 
	 * <p>Note that
	 * more than one character in height is not controlled by this
	 * feature (it's not supported by text border style).</p>
	 */
	public static final String FEATURE_WIDTH_RANGE = "width-range";
	
	/**
	 * Provides values for the mode feature.
	 * @author Joel Håkansson
	 *
	 */
	public enum ModeValues {
		SIX_DOT("six-dot-braille"),
		EIGHT_DOT("eight-dot-braille"),
		TEXT("text");
		
		private final String str;
		ModeValues(String str) {
			this.str = str;
		}
		
		public String getName() {
			return str;
		}
	}

	/**
	 * Sets a feature.
	 * @param key a feature key
	 * @param value a feature value
	 */
	public void setFeature(String key, Object value);

	/**
	 * Gets the value for the specified feature key.
	 * @param key the feature key
	 * @return returns the feature value, or null if not defined
	 */
	public Object getFeature(String key);

	/**
	 * Creates a new TextBorderStyle based on the current features.
	 * @return a new TextBorderStyle instance
	 * 
	 * @throws TextBorderConfigurationException if no text border could be created
	 * based on the currently set features.
	 */
	@Deprecated
	public TextBorderStyle newTextBorderStyle() throws TextBorderConfigurationException;
	
	public TextBorderStyle newTextBorderStyle(Border border) throws TextBorderConfigurationException;
}

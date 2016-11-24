package org.daisy.dotify.api.translator;

/**
 * Provides a factory for text borders.
 * 
 * @author Joel Håkansson
 */
public interface TextBorderFactory {
	
	/**
	 * Mode feature. The corresponding value should be
	 * a string. 
	 */
	public static final String FEATURE_MODE = "mode";
	
	/**
	 * Style feature. The corresponding value should be
	 * a string. See StyleValues enum for recommended values.
	 */
	public static final String FEATURE_STYLE = "style";
	
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
	
	enum StyleValues {
		SIX_DOT("six-dot-braille"),
		EIGHT_DOT("eight-dot-braille"),
		TEXT("text");
		
		private final String str;
		StyleValues(String str) {
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

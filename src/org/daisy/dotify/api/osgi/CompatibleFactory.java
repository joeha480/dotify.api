package org.daisy.dotify.api.osgi;

/**
 * Provides a compatible factory interface. This interface is used to retrieve
 * a compatible instance.
 * 
 * @author Joel Håkansson
 * 
 */
public interface CompatibleFactory {
	
	public <T> T newFactoryInstance(Class<T> api);
	
	/**
	 * Gets the value of a compatible feature.
	 * 
	 * @param key
	 *            the feature to get the value for
	 * @return returns the value, or null if not set
	 */
	public Object getFeature(String key);
	
	/**
	 * Sets the value of a compatible feature.
	 * 
	 * @param key
	 *            the feature to set the value for
	 * @param value
	 *            the value for the feature
	 * @throws CompatibleFactoryConfigurationException
	 *             if the feature is not supported
	 */
	public void setFeature(String key, Object value) throws CompatibleFactoryConfigurationException;
}

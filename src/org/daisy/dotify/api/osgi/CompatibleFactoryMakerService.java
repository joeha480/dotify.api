package org.daisy.dotify.api.osgi;

import java.util.Collection;

/**
 * <p>
 * Provides an interface for a CompatibleFactoryMaker service. The purpose of
 * this interface is to expose an implementation of a CompatibleFactoryMaker
 * as an OSGi service.
 * </p>
 * 
 * <p>
 * To comply with this interface, an implementation must be thread safe and
 * address both the possibility that only a single instance is created and used
 * throughout and that new instances are created as desired.
 * </p>
 * 
 * @author Joel Håkansson
 * 
 */
public interface CompatibleFactoryMakerService {

	/**
	 * Gets a CompatibleFactory that supports the specified locale
	 * 
	 * @param api the target api
	 * @return returns a compatible factory for the specified api
	 * @throws CompatibleFactoryConfigurationException if the api is not supported
	 */
	public CompatibleFactory getFactory(Class<?> api) throws CompatibleFactoryConfigurationException;

	/**
	 * Creates a new compatible. This is a convenience method for
	 * getFactory(target).newFactoryInstance(target).
	 * Using this method excludes the possibility of setting features of the
	 * compatible factory.
	 * 
	 * @param api the target api
	 * @return returns a new compatible
	 * @throws CompatibleFactoryConfigurationException if the api is not supported
	 */
	public <T> T newFactoryInstance(Class<T> api) throws CompatibleFactoryConfigurationException;
	
	/**
	 * Returns a list of supported classes.
	 * @return returns a list of classes
	 */
	public Collection<Class<?>> listFactories();

}

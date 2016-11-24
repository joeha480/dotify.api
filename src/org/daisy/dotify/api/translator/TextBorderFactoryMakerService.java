package org.daisy.dotify.api.translator;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * Provides an interface for a TextBorderFactoryMaker service. The purpose of
 * this interface is to expose an implementation of a TextBorderFactoryMaker as
 * an OSGi service.
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
public interface TextBorderFactoryMakerService {

	public TextBorderFactory newFactory(Map<String, Object> features) throws TextBorderConfigurationException;

	@Deprecated
	public TextBorderStyle newTextBorderStyle(Map<String, Object> features) throws TextBorderConfigurationException;
	
	public TextBorderStyle newTextBorderStyle(Border border) throws TextBorderConfigurationException;
	
	public TextBorderStyle newTextBorderStyle(Border border, Map<String, Object> features) throws TextBorderConfigurationException;

}

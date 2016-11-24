package org.daisy.dotify.consumer.translator;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import org.daisy.dotify.api.translator.Border;
import org.daisy.dotify.api.translator.TextBorderConfigurationException;
import org.daisy.dotify.api.translator.TextBorderFactory;
import org.daisy.dotify.api.translator.TextBorderFactoryMakerService;
import org.daisy.dotify.api.translator.TextBorderFactoryService;
import org.daisy.dotify.api.translator.TextBorderStyle;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

/**
 * Provides a text border factory maker. This class will look for
 * implementations of the TextBorderFactory interface using the
 * services API. It will return the first implementation that matches the
 * requested specification.
 * 
 * <p>
 * This class can be overridden by extending it and adding a reference to the
 * new implementation to the services API. This class will then choose the new
 * implementation when a new instance is requested.
 * </p>
 * 
 * @author Joel Håkansson
 * 
 */
@Component
public class TextBorderFactoryMaker implements TextBorderFactoryMakerService {
	private final List<TextBorderFactoryService> factories;
	private final Logger logger;

	public TextBorderFactoryMaker() {
		logger = Logger.getLogger(TextBorderFactoryMaker.class.getCanonicalName());
		factories = new CopyOnWriteArrayList<>();
	}

	/**
	 * <p>
	 * Creates a new TextBorderFactoryMaker and populates it using the SPI (java
	 * service provider interface).
	 * </p>
	 * 
	 * <p>
	 * In an OSGi context, an instance should be retrieved using the service
	 * registry. It will be registered under the TextBorderFactoryMakerService
	 * interface.
	 * </p>
	 * 
	 * @return returns a new marker processor factory maker.
	 */
	public static TextBorderFactoryMakerService newInstance() {
		TextBorderFactoryMaker ret = new TextBorderFactoryMaker();
		{
			Iterator<TextBorderFactoryService> i = ServiceLoader.load(TextBorderFactoryService.class).iterator();
			while (i.hasNext()) {
				TextBorderFactoryService f = i.next();
				f.setCreatedWithSPI();
				ret.addFactory(f);
			}
		}
		return ret;
	}
	
	@Reference(type = '*')
	public void addFactory(TextBorderFactoryService factory) {
		logger.finer("Adding factory: " + factory);
		factories.add(factory);
	}

	// Unbind reference added automatically from addFactory annotation
	public void removeFactory(TextBorderFactoryService factory) {
		logger.finer("Removing factory: " + factory);
		// this is to avoid adding items to the cache that were removed while
		// iterating
		factories.remove(factory);
	}
	
	@Override
	public TextBorderFactory newFactory(Map<String, Object> features) throws TextBorderConfigurationException {
		for (TextBorderFactoryService s : factories) {
			if (s.supportsSpecification(features)) {
				return s.newFactory();
			}
		}
		throw new TextBorderFactoryMakerException("No factory for " + features);
	}

	@Override
	@Deprecated
	public TextBorderStyle newTextBorderStyle(Map<String, Object> features) throws TextBorderConfigurationException {
		// TODO: this could be optimized
		TextBorderFactoryMakerException ex = new TextBorderFactoryMakerException();
		for (TextBorderFactoryService s : factories) {
			TextBorderFactory h = s.newFactory();
			for (String key : features.keySet()) {
				h.setFeature(key, features.get(key));
			}
			try {
				return h.newTextBorderStyle();
			} catch (TextBorderConfigurationException e) {
				ex.addSuppressed(e);
			}
		}
		throw ex;
	}
	
	@Override
	public TextBorderStyle newTextBorderStyle(Border border) throws TextBorderConfigurationException {
		return newTextBorderStyle(border, Collections.emptyMap());
	}
	
	@Override
	public TextBorderStyle newTextBorderStyle(Border border, Map<String, Object> features) throws TextBorderConfigurationException {
		TextBorderFactoryMakerException ex = new TextBorderFactoryMakerException("No factory for " + features);
		for (TextBorderFactoryService s : factories) {
			if (s.supportsSpecification(features)) {
				TextBorderFactory h = s.newFactory();
				for (Entry<String, Object> feature : features.entrySet()) {
					h.setFeature(feature.getKey(), feature.getValue());
				}
				try {
					return h.newTextBorderStyle(border);
				} catch (TextBorderConfigurationException e) {
					ex.addSuppressed(e);
				}
			}
		}
		throw ex;
	}
	
	private class TextBorderFactoryMakerException extends TextBorderConfigurationException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7241556330716217110L;

		/**
		 * 
		 */
		private TextBorderFactoryMakerException() {
			super();
		}

		/**
		 * @param message
		 */
		private TextBorderFactoryMakerException(String message) {
			super(message);
		}
		
	}

}

package org.daisy.dotify.consumer.osgi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import org.daisy.dotify.api.osgi.CompatibleFactory;
import org.daisy.dotify.api.osgi.CompatibleFactoryConfigurationException;
import org.daisy.dotify.api.osgi.CompatibleFactoryMakerService;
import org.daisy.dotify.api.osgi.CompatibleFactoryService;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

/**
 * Provides a compatible factory maker. This is the entry point for
 * creating compatible instances.
 * 
 * @author Joel Håkansson
 */
@Component
public class CompatibleFactoryMaker implements CompatibleFactoryMakerService {
	private final List<CompatibleFactoryService> filters;
	private final Map<String, CompatibleFactoryService> map;
	private final Logger logger;

	public CompatibleFactoryMaker() {
		logger = Logger.getLogger(this.getClass().getCanonicalName());
		filters = new CopyOnWriteArrayList<>();
		this.map = Collections.synchronizedMap(new HashMap<String, CompatibleFactoryService>());
	}
	
	/**
	 * <p>
	 * Creates a new CompatibleFactoryMaker and populates it using the SPI
	 * (java service provider interface).
	 * </p>
	 * 
	 * <p>
	 * In an OSGi context, an instance should be retrieved using the service
	 * registry. It will be registered under the CompatibleFactoryMakerService
	 * interface.
	 * </p>
	 * 
	 * @return returns a new CompatibleFactoryMaker
	 */
	public static CompatibleFactoryMaker newInstance() {
		CompatibleFactoryMaker ret = new CompatibleFactoryMaker();
		{
			Iterator<CompatibleFactoryService> i = ServiceLoader.load(CompatibleFactoryService.class).iterator();
			while (i.hasNext()) {
				CompatibleFactoryService f = i.next();
				f.setCreatedWithSPI();
				ret.addFactory(f);
			}
		}
		return ret;
	}
	
	@Reference(type = '*')
	public void addFactory(CompatibleFactoryService factory) {
		logger.finer("Adding factory: " + factory);
		filters.add(factory);
	}

	// Unbind reference added automatically from addFactory annotation
	public void removeFactory(CompatibleFactoryService factory) {
		logger.finer("Removing factory: " + factory);
		// this is to avoid adding items to the cache that were removed while
		// iterating
		synchronized (map) {
			filters.remove(factory);
			map.clear();
		}
	}

	@Override
	public CompatibleFactory getFactory(Class<?> target) throws CompatibleFactoryConfigurationException {
		CompatibleFactoryService template = map.get(target.getCanonicalName());
		if (template==null) {
			// this is to avoid adding items to the cache that were removed
			// while iterating
			synchronized (map) {
				for (CompatibleFactoryService h : filters) {
					if (h.supportsFactory(target)) {
						logger.fine("Found an CompatibleFactory factory for " + target + " (" + h.getClass() + ")");
						map.put(target.getCanonicalName(), h);
						template = h;
						break;
					}
				}
			}
		}
		if (template==null) {
			throw new CompatibleFactoryConfigurationException("Cannot find CompatibleFactory factory for " + target);
		}
		return template.newFactory();
	}

	@Override
	public <T> T newFactoryInstance(Class<T> target) throws CompatibleFactoryConfigurationException {
		return getFactory(target).newFactoryInstance(target);
	}
	

	@Override
	public Collection<Class<?>> listFactories() {
		ArrayList<Class<?>> ret = new ArrayList<>();
		for (CompatibleFactoryService s : filters) {
			ret.addAll(s.listFactories());
		}
		return ret;
	}

}

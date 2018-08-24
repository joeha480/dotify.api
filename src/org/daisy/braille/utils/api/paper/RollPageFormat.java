package org.daisy.braille.utils.api.paper;

public interface RollPageFormat {

	/**
	 * Gets the length of the paper perpendicular to the direction of the paper feed
	 * @return returns the length.
	 */
	public Length getLengthAcrossFeed();
	
	/**
	 * Gets the length of the paper along the direction of the paper feed
	 * @return returns the length.
	 */
	public Length getLengthAlongFeed();
}

package org.daisy.braille.utils.api.paper;

public interface SheetPageFormat {
	/**
	 * The width/height orientation of the page
	 */
	public enum Orientation {
		/**
		 *  Represents default orientation as defined by the Paper
		 */
		DEFAULT,
		/**
		 *  Represents reversed orientation as defined by the Paper
		 */
		REVERSED
	}
	
	/**
	 * Gets the orientation of this paper format.
	 * @return returns the orientation
	 */
	public Orientation getOrientation();
	
	/**
	 * Gets the page width with respect to the orientation of the paper format
	 * @return returns the width.
	 */
	public Length getPageWidth();
	
	/**
	 * Gets the page height with respect to the orientation of the paper format
	 * @return returns the height.
	 */
	public Length getPageHeight();
}

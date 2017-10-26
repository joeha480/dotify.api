package org.daisy.dotify.api.formatter;

/**
 * Provides properties for a {@link VolumeContentBuilder}.
 * @author Joel Håkansson
 *
 */
public final class VolumeContentBuilderProperties {
	private final boolean sharedNumbering;

	/**
	 * Provides a builder for {@link VolumeContentBuilderProperties}.
	 */
	public static class Builder {
		private boolean sharedNumbering = true;

		/**
		 * Creates a new builder.
		 */
		public Builder() {}

		/**
		 * Defines if this volume content should share numbering with the main flow. 
		 * @param value the value
		 * @return returns this builder
		 */
		public Builder sharePageNumbering(boolean value) {
			sharedNumbering = value;
			return this;
		}
		/**
		 * Creates a new instance using the current state of the builder.
		 * @return returns a new instance
		 */
		public VolumeContentBuilderProperties build() {
			return new VolumeContentBuilderProperties(this);
		}
	}

	private VolumeContentBuilderProperties(Builder builder) {
		this.sharedNumbering = builder.sharedNumbering;
	}

	/**
	 * Returns true if this volume content should share numbering with the main flow.
	 * @return returns true if the volume content should share numbering with the main flow, false otherwise
	 */
	public boolean shouldSharePageNumbering() {
		return sharedNumbering;
	}

}
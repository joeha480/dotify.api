package org.daisy.dotify.api.writer;

public final class MediaType {
	private final String mediaType;

	private MediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public static MediaType of(String mediaType) {
		return new MediaType(mediaType);
	}

	public String getStringValue() {
		return mediaType;
	}

}

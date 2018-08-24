package org.daisy.dotify.api.paper;


/**
 * Provides a paper format for tractor paper.
 * @author Joel Håkansson
 */
public class TractorPaperFormat implements PageFormat, TractorPageFormat {
	private final Length across, along;

	/**
	 * Creates a new tractor paper format.
	 * @param paper the paper to use
	 */
	public TractorPaperFormat(TractorPaper paper) {
		this.across = paper.getLengthAcrossFeed();
		this.along = paper.getLengthAlongFeed();
	}

	/**
	 * Creates a new tractor paper format.
	 * @param acrossPaperFeed the width of the paper
	 * @param alongPaperFeed the height of the paper
	 */
	public TractorPaperFormat(Length acrossPaperFeed, Length alongPaperFeed) {
		this.across = acrossPaperFeed;
		this.along = alongPaperFeed;
	}

	@Override
	public Length getLengthAcrossFeed() {
		return across;
	}

	@Override
	public Length getLengthAlongFeed() {
		return along;
	}

	@Override
	public Type getPageFormatType() {
		return Type.TRACTOR;
	}

	@Override
	public TractorPageFormat asTractorPaperFormat() {
		return this;
	}

	@Override
	public String toString() {
		return "TractorPaperFormat [across=" + across + ", along=" + along
				+ "]";
	}
}

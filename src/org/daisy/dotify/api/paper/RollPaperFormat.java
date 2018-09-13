package org.daisy.dotify.api.paper;


/**
 * Provides a paper format for paper in rolls.
 * @author Joel Håkansson
 */
public class RollPaperFormat implements PageFormat, RollPageFormat {
	private final Length across, along;

	/**
	 * Creates a new roll paper format
	 * @param paper the roll paper to use
	 * @param length the cut-length
	 */
	public RollPaperFormat(RollPaper paper, Length length) {
		this.across = paper.getLengthAcrossFeed();
		this.along = length;
	}

	/**
	 * Creates a new roll paper format
	 * @param acrossPaperFeed the height of the roll
	 * @param alongPaperFeed the cut-length
	 */
	public RollPaperFormat(Length acrossPaperFeed, Length alongPaperFeed) {
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
		return Type.ROLL;
	}

	@Override
	public RollPageFormat asRollPaperFormat() {
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RollPaperFormat [across=" + across + ", along=" + along + "]";
	}

}

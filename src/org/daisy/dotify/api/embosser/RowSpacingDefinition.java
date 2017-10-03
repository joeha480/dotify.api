package org.daisy.dotify.api.embosser;

import java.util.ArrayList;
import java.util.List;

public final class RowSpacingDefinition {
	public enum Mode {
		SIX_DOT,
		EIGHT_DOT,
		ALL
	}
	private final Mode mode;
	private final int offset;
	private final int multiplier;
	private final int limit;

	public RowSpacingDefinition(Mode mode, int offset, int multiplier) {
		this(mode, offset, multiplier, Integer.MAX_VALUE);
	}

	public RowSpacingDefinition(Mode mode, int offset, int multiplier, int limit) {
		this.mode = mode;
		if (offset<0) {
			throw new IllegalArgumentException("Offset must be positive: " + offset);
		}
		this.offset = offset;
		if (multiplier<1) {
			throw new IllegalArgumentException("Multiplier must be greater than or equal to 1: " + multiplier);
		}
		this.multiplier = multiplier;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public int getLimit() {
		return limit;
	}

	public List<Integer> getTopValues(int maxSize) {
		List<Integer> ret = new ArrayList<>();
		int i = offset;
		while (i<limit && ret.size()<maxSize) {
			ret.add(i);
			i += multiplier;
		}
		return ret;
	}
	
	public List<Integer> getValuesBelow(int limit) {
		List<Integer> ret = new ArrayList<>();
		int i = offset;
		while (i<this.limit && i<limit) {
			ret.add(i);
			i += multiplier;
		}
		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + limit;
		result = prime * result + ((mode == null) ? 0 : mode.hashCode());
		result = prime * result + multiplier;
		result = prime * result + offset;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RowSpacingDefinition other = (RowSpacingDefinition) obj;
		if (limit != other.limit)
			return false;
		if (mode != other.mode)
			return false;
		if (multiplier != other.multiplier)
			return false;
		if (offset != other.offset)
			return false;
		return true;
	}

}
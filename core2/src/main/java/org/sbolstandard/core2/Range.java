package org.sbolstandard.core2;

import java.net.URI;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class Range extends Location{
	
	private int start = 0;
	private int end = 0;
	
	Range(URI identity, int start, int end) {
		super(identity);
		setEnd(end);
		setStart(start);
	}
	
	private Range(Range range) {
		super(range);
		this.setEnd(range.getEnd());
		this.setStart(range.getStart());
	}

	/**
	 * Sets the start position of this Range object.
 	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param value
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws IllegalArgumentException if the given {@code value} is less or equal to 0.
	 * @throws IllegalArgumentException if the given {@code value} is greater than 
	 * the {@code end} value of this Range object.
	 */ 
	public void setStart(int value) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (value<=0) {
			throw new IllegalArgumentException("Range "+this.getIdentity()+" must have a start greater than zero.");
		}
		if (value > end) {
			throw new IllegalArgumentException("Range "+this.getIdentity()+" must have a start before the end.");
		}
		start = value;		
	}
	
	/**
	 * Returns the start position of this Range object.
	 * 
	 * @return the start position of this Range object.
	 */
	public int getStart() {		
		return start;
	}

	/**
	 * Returns the end position of this Range object.
	 * 
	 * @return the end position of this Range object.
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Sets the end position of this Range object.
 	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param value
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws IllegalArgumentException if the given {@code value} is less or equal to 0.
	 * @throws IllegalArgumentException if the given {@code value} is less than 
	 * the {@code start} value of this Range object.
	 */ 
	public void setEnd(int value) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (value<=0) {
			throw new IllegalArgumentException("Range "+this.getIdentity()+" must have an end greater than zero.");
		}
		if (value < start) {
			throw new IllegalArgumentException("Range "+this.getIdentity()+" must have a start before the end.");
		}
		end = value;
	}
	
	
	@Override
	protected Location deepCopy() {
		return new Range(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + end;
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + start;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Range other = (Range) obj;
		if (end != other.end)
			return false;
		if (orientation != other.orientation)
			return false;
		return start == other.start;
	}

}

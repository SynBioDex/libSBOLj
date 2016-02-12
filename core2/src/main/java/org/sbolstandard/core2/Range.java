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

public class Range extends Location {
	
	private int start = 0;
	private int end = 0;
	
	Range(URI identity, int start, int end) throws SBOLValidationException {
		super(identity);
		setEnd(end);
		setStart(start);
	}
	
	private Range(Range range) throws SBOLValidationException {
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
	 * @throws SBOLValidationException if the given {@code value} is less or equal to 0.
	 * @throws SBOLValidationException if the given {@code value} is greater than 
	 * the {@code end} value of this Range object.
	 */ 
	public void setStart(int value) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (value<=0) {
			//throw new SBOLValidationException("Range "+this.getIdentity()+" must have a start greater than zero.");
			throw new SBOLValidationException("sbol-11102", this);
		}
		if (value > end) {
			//throw new SBOLValidationException("Range "+this.getIdentity()+" must have a start before the end.");
			throw new SBOLValidationException("sbol-11104", this);
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
	 * @throws SBOLValidationException if the given {@code value} is less or equal to 0.
	 * @throws SBOLValidationException if the given {@code value} is less than 
	 * the {@code start} value of this Range object.
	 */ 
	public void setEnd(int value) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (value<=0) {
			// throw new SBOLValidationException("Range "+this.getIdentity()+" must have an end greater than zero.");
			throw new SBOLValidationException("sbol-11103", this);
		}
		if (value < start) {
			//throw new SBOLValidationException("Range "+this.getIdentity()+" must have a start before the end.");
			throw new SBOLValidationException("sbol-11104", this);
		}
		end = value;
	}
	
	
	@Override
	protected Location deepCopy() throws SBOLValidationException {
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

	@Override
	public String toString() {
		return "Range [start=" + start + ", end=" + end + ", orientation=" + orientation
				+ ", identity=" + identity + ", displayId=" + displayId + ", name=" + name
				+ ", description=" + description + "]";
	}
	
	@Override
	public int compareTo(Location location) {
		if (location instanceof Range) {
			int result = this.start - ((Range)location).getStart();
			if (result==0) {
				result = ((Range)location).getEnd() - this.end;
			}
			return result;
		} else if (location instanceof Cut) {
			int result = this.start - ((Cut)location).getAt();
			if (result==0) {
				result = ((Cut)location).getAt() - this.end;
			}
			return result;
		} 
		return this.start;
    }
}

package org.sbolstandard.core2;

import java.net.URI;

import javax.xml.namespace.QName;

/**
 * Represents the Range extension of the SBOL Location class.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
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
	 * @param value the start position of this Range
	 * @throws SBOLValidationException if the given {@code value} is less or equal to 0.
	 * @throws SBOLValidationException if the given {@code value} is greater than
	 * the {@code end} value of this Range object.
	 */
	public void setStart(int value) throws SBOLValidationException {
		if (value<=0) {
			throw new SBOLValidationException("sbol-11102", this);
		}
		if (value > end) {
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
	 * @param value the start position of this Range
	 * @throws SBOLValidationException if the given {@code value} is less or equal to 0.
	 * @throws SBOLValidationException if the given {@code value} is less than
	 * the {@code start} value of this Range object.
	 */
	public void setEnd(int value) throws SBOLValidationException {
		if (value<=0) {
			throw new SBOLValidationException("sbol-11103", this);
		}
		if (value < start) {
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
		return "Range ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ ", start=" + start 
				+ ", end=" + end
				+ (this.isSetOrientation()?", orientation=" + orientation:"") 
				+ "]";
	}

	@Override
	public int compareTo(Location location) {
		int thisPos = -1;
		Annotation annotation = this.getAnnotation(new QName(GenBank.GBNAMESPACE,GenBank.POSITION,GenBank.GBPREFIX));
		if (annotation!=null) {
			thisPos = Integer.parseInt(annotation.getStringValue().replace("position",""));
		}
		int otherPos = -1;
		annotation = location.getAnnotation(new QName(GenBank.GBNAMESPACE,GenBank.POSITION,GenBank.GBPREFIX));
		if (annotation!=null) {
			otherPos = Integer.parseInt(annotation.getStringValue().replace("position",""));
		}
		if (thisPos != -1 && otherPos != -1) {
			int result = thisPos - otherPos;
			return result;
		}
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

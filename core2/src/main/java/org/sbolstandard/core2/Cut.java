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

public class Cut extends Location{

	private int at;
	
	Cut(URI identity, int at) throws SBOLValidationException {
		super(identity);
		setAt(at);
	}

	private Cut(Cut cut) throws SBOLValidationException {
		super(cut);
		this.setAt(cut.getAt());
	}

	/**
	 * Returns the {@code at} property of this Cut object.
	 *
	 * @return the {@code at} property of this Cut object
	 */
	public int getAt() {
		return at;
	}

	/**
	 * Sets the {@code at} property of this Cut object to the given one.
	 * <p>
	 * If this Cut object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param at The discrete position that that corresponds to the index of a character in the elements String of a Sequence.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the given {@code at} value is less than 0
	 */
	public void setAt(int at) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (at<0) {
			throw new SBOLValidationException("Cut "+this.getIdentity()+" must have a value greater than or equal to zero.");
		}
		this.at = at;
	}

	@Override
	protected Cut deepCopy() throws SBOLValidationException {
		return new Cut(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + at;
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
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
		Cut other = (Cut) obj;
		if (at != other.at)
			return false;
		return orientation == other.orientation;
	}

	@Override
	public String toString() {
		return "Cut [at=" + at + ", orientation=" + orientation + ", identity=" + identity
				+ ", displayId=" + displayId + ", name=" + name + ", description=" + description
				+ "]";
	}

	@Override
	public int compareTo(Location location) {
		if (location instanceof Range) {
			int result = this.at - ((Range)location).getStart();
			if (result==0) {
				result = ((Range)location).getEnd() - this.at;
			}
			return result;
		} else if (location instanceof Cut) {
			return this.at - ((Cut)location).getAt();
		} 
		return this.at;
	}
}

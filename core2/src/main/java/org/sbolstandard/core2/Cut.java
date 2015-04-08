package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.Sbol2Terms.Orientation;
import org.sbolstandard.core2.abstract_classes.Location;

public class Cut extends Location{
	
	private Integer at;
	private OrientationType orientation;
	
	public Cut(URI identity, Integer at) {
		super(identity);
		setAt(at);
	}
	
	private Cut(Cut cut) {
		super(cut);
		this.setAt(cut.getAt().intValue());
		if (cut.isSetOrientation()) {
			this.setOrientation(cut.getOrientation());
		}
	}

	/**
	 * Returns field variable <code>at</code>.
	 * @return field variable <code>at</code>
	 */
	public Integer getAt() {
		return at;
	}

	/**
	 * Sets field variable <code>at</code> to the specified element.
	 * @param at
	 */
	public void setAt(Integer at) {
		this.at = at;
	}
	
	/**
	 * Test if optional field variable <code>orientation</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetOrientation() {
		if (orientation == null)
			return false;
		else 
			return true;
	}
	
	/**
	 * Returns field variable <code>orientation</code>.
	 * @return field variable <code>orientation</code>
	 */
	// @return the documented object's display ID
	public OrientationType getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Returns the orientation type in URI.
	 * @return orientation type in URI
	 */
	public URI getOrientationURI() {
		if (orientation != null) {
			if (orientation.equals(OrientationType.INlINE)) {
				return Orientation.inline;
			}
			else if (orientation.equals(OrientationType.REVERSECOMPLEMENT)) {
				return Orientation.reverseComplement;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
		
	/**
	 * Set field variable <code>orientation</code> to the specified element.
	 * @param orientation
	 */
	// Created for backward compatibility to 1.1. 
	public void setOrientation(OrientationType orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Sets field variable <code>orientation</code> to the element corresponding to the specified URI.
	 * @param orientation
	 */
	public void setOrientation(URI orientation) {
		if (orientation.equals(Orientation.inline)) {
			this.orientation = OrientationType.INlINE;
		} else if (orientation.equals(Orientation.reverseComplement)) {
			this.orientation = OrientationType.REVERSECOMPLEMENT;
		}
		else {
			// TODO: Validation?
			this.orientation = null;
		}
	}
	
	/**
	 * Set optional field variable <code>orientation</code> to <code>null</code>.
	 */
	public void unsetOrientation() {
		orientation = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((at == null) ? 0 : at.hashCode());
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
		if (at == null) {
			if (other.at != null)
				return false;
		} else if (!at.equals(other.at))
			return false;
		if (orientation != other.orientation)
			return false;
		return true;
	}

	@Override
	protected Cut deepCopy() {
		return new Cut(this);
	}
}

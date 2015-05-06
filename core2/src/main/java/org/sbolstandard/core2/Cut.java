package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

import org.sbolstandard.core2.Sbol2Terms.Orientation;

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
	private OrientationType orientation;
	
	Cut(URI identity, int at) {
		super(identity);
		setAt(at);
	}
	
	private Cut(Cut cut) {
		super(cut);
		this.setAt(cut.getAt());
		if (cut.isSetOrientation()) {
			this.setOrientation(cut.getOrientation());
		}
	}

	/**
	 * Returns field variable <code>at</code>.
	 * @return field variable <code>at</code>
	 */
	public int getAt() {
		return at;
	}

	/**
	 * Sets field variable <code>at</code> to the specified element.
	 */
	public void setAt(int at) {
		if (at<0) {
			throw new IllegalArgumentException("Cut "+this.getIdentity()+" must have a value greater than or equal to zero.");
		}
		this.at = at;
	}
	
	/**
	 * Test if optional field variable <code>orientation</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetOrientation() {
		return orientation != null;
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
	URI getOrientationURI() {
		if (orientation != null) {
			if (orientation.equals(OrientationType.INLINE)) {
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
	 */
	// Created for backward compatibility to 1.1. 
	public void setOrientation(OrientationType orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Sets field variable <code>orientation</code> to the element corresponding to the specified URI.
	 */
	 void setOrientation(URI orientation) {
		if (orientation.equals(Orientation.inline)) {
			this.orientation = OrientationType.INLINE;
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
	protected Cut deepCopy() {
		return new Cut(this);
	}

	/**
	 * Assume this Cut object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link SequenceAnnotation#updateCompliantURI(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String grandparentDisplayId,
			String parentDisplayId, String version) {
				String thisObjDisplayId = extractDisplayId(this.getIdentity()); // 2 indicates that this object is a grandchild of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + grandparentDisplayId + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);
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
}

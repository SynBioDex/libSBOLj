package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.Sbol2Terms.Orientation;
import org.sbolstandard.core2.abstract_classes.Location;

public class GenericLocation extends Location{

	public GenericLocation(URI identity) {
		super(identity);
	}

	private OrientationType orientation;

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

}

package org.sbolstandard.core2;

import java.net.URI;
import static org.sbolstandard.core2.util.UriCompliance.*;

import org.sbolstandard.core2.Sbol2Terms.Orientation;
import org.sbolstandard.core2.abstract_classes.Location;

public class Range extends Location{
	
	private Integer start;
	private Integer end;
	private OrientationType orientation;
	
	public Range(URI identity, Integer start, Integer end) {
		super(identity);
		setStart(start);
		setEnd(end);
	}
	
	private Range(Range range) {
		super(range);
		this.setStart(range.getStart().intValue());
		this.setEnd(range.getEnd().intValue());
		this.setOrientation(range.getOrientation());
	}


	/**
	 * Sets field variable <code>start</code> to the specified element.
	 * @param value
	 */
	public void setStart(Integer value) {
		start = value;		
	}
	
	/**
	 * Returns field variable <code>start</code>.
	 * @return field variable <code>start</code>
	 */
	public Integer getStart() {		
		return start;
	}

	/**
	 * Returns field variable <code>end</code>.
	 * @return field variable <code>end</code>
	 */
	public Integer getEnd() {
		return end;
	}

	/**
	 * Sets field variable <code>end</code> to the specified element.
	 * @param value
	 */
	public void setEnd(Integer value) {
		end = value;
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
	protected Range deepCopy() {
		return new Range(this);
	}

	/**
	 * Assume this Range object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link SequenceAnnotation#updateCompliantURI(String, String, String)}.
	 * @param URIprefix
	 * @param grandparentDisplayId
	 * @param parentDisplayId
	 * @param version
	 */
	void updateCompliantURI(String URIprefix, String grandparentDisplayId, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity(), 2); //2 indicates this object is a grandchild of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + grandparentDisplayId + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);
	}
	
	/**
	 * Assume this Range object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link MultiRange#updateCompliantURI(String, String, String, String)}.
	 * @param URIprefix
	 * @param greatGrandparentDisplayId
	 * @param grandparentDisplayId
	 * @param parentDisplayId
	 * @param version
	 */
	void updateCompliantURI(String URIprefix, String greatGrandparentDisplayId, 
			String grandparentDisplayId, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity(), 3); //3 indicates this object is a great grandchild of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + greatGrandparentDisplayId + '/' + grandparentDisplayId 
				+ '/' + parentDisplayId + '/' + thisObjDisplayId + '/' + version);
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);
	}

}

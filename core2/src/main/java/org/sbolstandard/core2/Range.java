package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

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
	 * Sets field variable <code>start</code> to the specified element.
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
	 * Returns field variable <code>start</code>.
	 * @return field variable <code>start</code>
	 */
	public int getStart() {		
		return start;
	}

	/**
	 * Returns field variable <code>end</code>.
	 * @return field variable <code>end</code>
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Sets field variable <code>end</code> to the specified element.
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

	/**
	 * Assume this Range object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link SequenceAnnotation#updateCompliantURI(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String grandparentDisplayId, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity()); //2 indicates this object is a grandchild of a top-level object.
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

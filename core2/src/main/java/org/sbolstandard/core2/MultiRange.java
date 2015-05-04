package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class MultiRange extends Location{
	
	private HashMap<URI, Range> ranges;

	MultiRange(URI identity,List<Range> ranges) {
		super(identity);
		this.ranges = new HashMap<>();
		setRanges(ranges);
	}
	
	
	private MultiRange(MultiRange multiRange) {
		super(multiRange);
		List<Range> ranges = new ArrayList<>();
		for (Range range : multiRange.getRanges()) {
			ranges.add(range.deepCopy());
		}
		setRanges(ranges);
	}


//	/**
//	 * Test if field variable <code>ranges</code> is set.
//	 * @return <code>true</code> if the field variable is not an empty list
//	 */
//	public boolean isSetRanges() {
//		return !ranges.isEmpty();
//	}
	
	
	/**
	 * Calls the Range constructor to create a new instance using the specified parameters, 
	 * then adds to the list of Range instances owned by this instance.
	 * @return the created Range instance.
	 */
	Range createRange(URI identity, Integer start, Integer end) {
		Range range = new Range(identity, start, end);
		addRange(range);
		return range;
	}
	
	public Range createRange(String displayId, Integer start, Integer end) {
		String parentPersistentIdStr = extractPersistentId(this.getIdentity());
		String version = this.getVersion();
		URI newMapsToURI = URI.create(parentPersistentIdStr + '/' + displayId + '/' + version);
		return createRange(newMapsToURI, start, end);
	}
	
	/**
	 * Adds the specified instance to the list of structuralAnnotations. 
	 */
	void addRange(Range range) {
		addChildSafely(range, ranges, "range");
	}

	/**
	 * Removes the instance matching the specified URI from the list of structuralAnnotations if present.
	 */
	public Range removeRange(URI rangeURI) {
		if (ranges.size()<5) {
			throw new IllegalArgumentException("MultiRange is required to have at least two ranges.");
		}
		Set<Range> rangesToRemove = new HashSet<>();
		Range range = ranges.get(rangeURI);
		rangesToRemove.add(range);
		ranges.values().removeAll(rangesToRemove);
		return range;
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Range getRange(URI rangeURI) {
		return ranges.get(rangeURI);
	}
	
	/**
	 * Returns the list of structuralAnnotation instances owned by this instance. 
	 * @return the list of structuralAnnotation instances owned by this instance.
	 */
	public Set<Range> getRanges() {
		Set<Range> ranges = new HashSet<>();
		ranges.addAll(this.ranges.values());
		return ranges; 
	}
	
	/**
	 * Removes all entries of the list of structuralAnnotation instances owned by this instance. The list will be empty after this call returns.
	 */
	void clearRanges() {
		Object[] keySetArray = ranges.keySet().toArray();
		for (Object key : keySetArray) {
			removeRange((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of structuralAnnotation instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setRanges(List<Range> ranges) {
		if (ranges.size()<2) {
			throw new IllegalArgumentException("MultiRange is required to have at least two ranges.");
		}
		clearRanges();		
		for (Range range : ranges) {
			addRange(range);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ranges == null) ? 0 : ranges.hashCode());
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
		MultiRange other = (MultiRange) obj;
		if (ranges == null) {
			if (other.ranges != null)
				return false;
		} else if (!ranges.equals(other.ranges))
			return false;
		return true;
	}

	@Override
	protected MultiRange deepCopy() {
		return new MultiRange(this);
	}

	/**
	 * Assume this MultiRange object has compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link SequenceAnnotation#updateCompliantURI(String, String, String)}.
	 */
	void updateCompliantURI(String URIprefix, String grandparentDisplayId, 
			String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity()); // 2 indicates that this object is a grandchild of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + grandparentDisplayId + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		if (!this.getRanges().isEmpty()) {
			// Update children's URIs
			for (Range range : this.getRanges()) {
				range.updateCompliantURI(URIprefix, grandparentDisplayId, parentDisplayId, thisObjDisplayId, version);
			}
		}			
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);		
	}
	
}

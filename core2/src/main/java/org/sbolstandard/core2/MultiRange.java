package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.sbolstandard.core2.URIcompliance.*;
import static org.sbolstandard.core2.Version.*;

public class MultiRange extends Location{
	
	private HashMap<URI, Range> ranges;

	public MultiRange(URI identity) {
		super(identity);
		this.ranges = new HashMap<URI, Range>();
	}
	
	
	private MultiRange(MultiRange multiRange) {
		super(multiRange);
		if (multiRange.isSetRanges()) {
			List<Range> ranges = new ArrayList<Range>();
			for (Range range : multiRange.getRanges()) {
				ranges.add(range.deepCopy());
			}
		}
	}


	/**
	 * Test if field variable <code>ranges</code> is set.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetRanges() {
		if (ranges.isEmpty())
			return false;
		else
			return true;					
	}
	
	
	/**
	 * Calls the Range constructor to create a new instance using the specified parameters, 
	 * then adds to the list of Range instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the created Range instance. 
	 */
	public Range createRange(URI identity, Integer start, Integer end) {
		Range range = new Range(identity, start, end);
		if (addRange(range)) {
			return range;	
		}
		else {
			return null;
		}
	}
	
	public Range createRange(String displayId, String version, Integer start, Integer end) {
		String parentPersistentIdStr = extractPersistentId(this.getIdentity());
		if (parentPersistentIdStr != null) {
			if (isDisplayIdCompliant(displayId)) {
				if (isVersionCompliant(version)) {
					URI newMapsToURI = URI.create(parentPersistentIdStr + '/' + displayId + '/' + version);
					return createRange(newMapsToURI, start, end);
				}
				else {
					// TODO: Warning: version not compliant
					return null;
				}
			}
			else {
				// TODO: Warning: display ID not compliant
				return null;
			}
		}
		else {
			// TODO: Warning: Parent persistent ID is not compliant.
			return null;
		}
	}
	
	/**
	 * Adds the specified instance to the list of structuralAnnotations. 
	 * @param range
	 */
	public boolean addRange(Range range) {
		if (isChildURIcompliant(this.getIdentity(), range.getIdentity())) {
			URI persistentId = URI.create(extractPersistentId(range.getIdentity()));
			// Check if URI exists in the ranges map.
			if (!ranges.containsKey(range.getIdentity())) {
				ranges.put(range.getIdentity(), range);
				Range latestRange = ranges.get(persistentId);
				if (latestRange == null) {
					ranges.put(persistentId, range);
				}
				else {						
					if (isFirstVersionNewer(extractVersion(range.getIdentity()), 
							extractVersion(latestRange.getIdentity()))) {								
						ranges.put(persistentId, range);
					}
				}
				return true;
			}
			else // key exists in ranges map
				return false;
		}
		else { // Only check if mapTo's URI exists in all maps.
			if (!ranges.containsKey(range.getIdentity())) {
				ranges.put(range.getIdentity(), range);					
				return true;
			}
			else // key exists in ranges map
				return false;
		}		
	}

	/**
	 * Removes the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @param rangeURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Range removeRange(URI rangeURI) {
		return ranges.remove(rangeURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @param rangeURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Range getRange(URI rangeURI) {
		return ranges.get(rangeURI);
	}
	
	/**
	 * Returns the list of structuralAnnotation instances owned by this instance. 
	 * @return the list of structuralAnnotation instances owned by this instance.
	 */
	public List<Range> getRanges() {
		List<Range> ranges = new ArrayList<Range>(); 
		ranges.addAll(this.ranges.values());
		return ranges; 
	}
	
	/**
	 * Removes all entries of the list of structuralAnnotation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearRanges() {
		Object[] keySetArray = ranges.keySet().toArray();
		for (Object key : keySetArray) {
			removeRange((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of structuralAnnotation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param ranges
	 */
	public void setRanges(
			List<Range> ranges) {
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
	 * @param URIprefix
	 * @param grandParentDisplayId
	 * @param parentDisplayId
	 * @param version
	 */
	void updateCompliantURI(String URIprefix, String grandparentDisplayId, 
			String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity(), 2); // 2 indicates that this object is a grandchild of a top-level object.
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

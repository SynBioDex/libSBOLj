package org.sbolstandard.core2;

import java.net.URI;
import java.util.Set;

import org.sbolstandard.core2.abstract_classes.Location;

public class MultiRange extends Location{
	
	private Set<URI> ranges;

	public MultiRange(URI identity, URI persistentIdentity, String version, 
			Set<URI> ranges) {
		super(identity);
		this.ranges = ranges;
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
	 * Adds the specified instance to the list of ranges. 
	 * @param rangeURI
	 */
	public void addRange(URI rangeURI) {
		// TODO: @addRange, Check for duplicated entries.
		ranges.add(rangeURI);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of ranges if present.
	 * @param rangeURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeRange(URI rangeURI) {
		// TODO: Need to check if the set of ranges' URIs is empty. 
		return ranges.remove(rangeURI);
	}
	
	/**
	 * Clears the existing list of range instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param ranges
	 */
	public void setRanges(Set<URI> ranges) {
		this.ranges = ranges;
	}
	
	/**
	 * Returns the list of range instances referenced by this component.
	 * @return the list of range instances referenced by this component
	 */
	public Set<URI> getRanges() {
		return ranges;
	}
	
	/**
	 * Returns true if the set <code>ranges</code> contains the specified element. 
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsRanges(URI rangesURI) {
		return ranges.contains(rangesURI);
	}

	/**
	 * Removes all entries of the list of range instances owned by this instance.
	 * The list will be empty after this call returns.
	 */
	public void clearRanges() {
		ranges.clear();
	}
		

	
	

	

}

package org.sbolstandard.core2;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class Collection extends TopLevel{
	
	private Set<URI> members;
	
	public Collection(URI identity) {
		super(identity);
		this.members = new HashSet<URI>();
	}

	/**
	 * Test if field variable <code>members</code> is set.
	 * @return <code>true</code> if it is not an empty list.
	 */
	public boolean isSetMembers() {
		if (members.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Adds the specified instance to the list of members. 
	 * @param memberURI
	 */
	public void addMember(URI memberURI) {
		// TODO: @addMember, Check for duplicated entries.
		members.add(memberURI);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of members if present.
	 * @param memberURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeMember(URI memberURI) {
		// TODO: Need to check if the set of members' URIs is empty. 
		return members.remove(memberURI);
	}
	
	/**
	 * Clears the existing list of member instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param members
	 */
	public void setMembers(Set<URI> members) {
		this.members = members;
	}
	
	/**
	 * Returns the list of member instances referenced by this component.
	 * @return the list of member instances referenced by this component
	 */
	public Set<URI> getMembers() {
		return members;
	}
	
	/**
	 * Returns true if the set <code>members</code> contains the specified element. 
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsMember(URI memberURI) {
		return members.contains(memberURI);
	}

	/**
	 * Removes all entries of the list of member instances owned by this instance. 
	 * The list will be empty after this call returns.
	 */
	public void clearMembers() {
		members.clear();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((members == null) ? 0 : members.hashCode());
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
		Collection other = (Collection) obj;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		return true;
	}
}

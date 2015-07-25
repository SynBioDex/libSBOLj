package org.sbolstandard.core2;

import java.net.URI;
import java.util.HashSet;
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

public class Collection extends TopLevel{
	
	private Set<URI> members;
	
	Collection(URI identity) {
		super(identity);
		this.members = new HashSet<>();
	}
	
	private Collection(Collection collection) {
		super(collection.getIdentity());
		this.members = new HashSet<>();
		Set<URI> newMembers = new HashSet<>();
		for (URI member : collection.getMemberURIs()) {
			newMembers.add(member);
		}	
		this.setMembers(newMembers);
	}

//	/**
//	 * Test if field variable <code>members</code> is set.
//	 * @return <code>true</code> if it is not an empty list.
//	 */
//	public boolean isSetMembers() {
//		if (members.isEmpty())
//			return false;
//		else
//			return true;					
//	}

	/**
	 * Adds the given member URI to this Collection object's
	 * set of reference member URIs.
	 * 
	 * @param memberURI
	 * @return {@code true} if this set did not already contain the given member URI.
 	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws IllegalArgumentException if the associated SBOLDocument instance already completely
	 *             specifies all URIs and the given {@code memberURI} is not found in them.
	 */
	public boolean addMember(URI memberURI) {
		if (sbolDocument != null) sbolDocument.checkReadOnly();
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getTopLevel(memberURI)==null) {
				throw new IllegalArgumentException("Top level '" + memberURI + "' does not exist.");
			}
		}
		return members.add(memberURI);
	}

	/**
	 * Removes the given member reference from the set of member references.
	 * <p>
	 * If this Collection object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param memberURI
	 * @return {@code true} if the matching member reference is removed successfully,
	 *         {@code false} otherwise.      
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */	
	public boolean removeMember(URI memberURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return members.remove(memberURI);
	}
	
	/**
	 * Clears the existing set of member references first, then adds the given
	 * set of the member references to this Collection object.
	 * <p>
	 * If this Collection object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param members
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setMembers(Set<URI> members) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		clearMembers();
		for (URI member : members) {
			addMember(member);
		}
	}
	
	/**
	 * Returns the set of member URIs referenced by this Collection object.
	 * 
	 * @return the set of member URIs referenced by this Collection object.
	 */
	public Set<URI> getMemberURIs() {
		Set<URI> result = new HashSet<>();
		result.addAll(members);
		return result;
	}
		
	/**
	 * Returns the set of Member instances referenced by this Collection object.
	 * 
	 * @return the set of Member instances referenced by this Collection object.
	 */
	public Set<TopLevel> getMembers() {
		Set<TopLevel> result = new HashSet<>();
		for (URI memberURI : members) {
			TopLevel member = sbolDocument.getTopLevel(memberURI);
			result.add(member);
		}
		return result;
	}
	
	/**
	 * Checks if the given member URI is included in this Collection
	 * object's set of reference member URIs.
	 * 
	 * @param memberURI
	 * @return {@code true} if this set contains the given URI.
	 */
	public boolean containsMember(URI memberURI) {
		return members.contains(memberURI);
	}

	/**
	 * Removes all entries of this Collection object's set of reference
	 * member URIs. The set will be empty after this call returns.
  	 * <p>
	 * If this Collection object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant 
	 */
	public void clearMembers() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		members.clear();
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.Documented#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.Documented#equals(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#deepCopy()
	 */
	@Override
	protected Collection deepCopy() {
		return new Collection(this);
	}
	
	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	Collection copy(String URIprefix, String displayId, String version) {
		Collection cloned = this.deepCopy();
		cloned.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		cloned.setDisplayId(displayId);
		cloned.setVersion(version);
		URI newIdentity = createCompliantURI(URIprefix,displayId,version);
		if (!this.getIdentity().equals(newIdentity)) {
			cloned.setWasDerivedFrom(this.getIdentity());
		} else {
			cloned.setWasDerivedFrom(this.getWasDerivedFrom());
		}
		cloned.setIdentity(newIdentity);
		return cloned;
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#checkDescendantsURIcompliance()
	 */
	@Override
	protected boolean checkDescendantsURIcompliance() {
		return isTopLevelURIformCompliant(this.getIdentity());
	}
	
	protected boolean isComplete() {
		if (sbolDocument==null) return false;
		for (URI member : members) {
			if (sbolDocument.getTopLevel(member)==null) return false;
		}
		return true;
	}
	
//	/**	
//	 * @param URIprefix
//	 * @param displayId
//	 * @param version
//	 * @return
//	 */
//	public Collection copy(String URIprefix, String displayId, String version) {
//		Collection cloned = (Collection) super.copy(displayId);
//		if (cloned.updateCompliantURI(displayId)) {
//			return cloned;
//		}
//		else {
//			return null;
//		}
//		
//	}

//	/* (non-Javadoc)
//	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateDisplayId(java.lang.String)
//	 */
//	protected void updateCompliantURI(String newDisplayId) {
//		super.updateCompliantURI(newDisplayId);
//		if (isTopLevelURIcompliant(this.getIdentity())) {			
//			
//		}
//	}
	
//	/* (non-Javadoc)
//	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#newVersion(java.lang.String)
//	 */
//	public Collection newVersion(String newVersion) {
//		Collection cloned = (Collection) super.copy(newVersion);
//		cloned.updateVersion(newVersion);
//		return cloned;
//	}
	
//	/* (non-Javadoc)
//	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateVersion(java.lang.String)
//	 */
//	protected void updateVersion(String newVersion) {
//		super.updateVersion(newVersion);
//		if (isTopLevelURIcompliant(this.getIdentity())) {
//		}
//	}
}

package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.sbolstandard.core2.URIcompliance.*;
import static org.sbolstandard.core2.Version.*;
/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Interaction extends Documented {

	private Set<URI> types;
	private HashMap<URI, Participation> participations;
	
	/**
	 * 
	 * @param identity an identity for the interaction	 
	 * @param type a type for the interaction
	 * @param participations a collection of participations for the interaction
	 */
	public Interaction(URI identity, Set<URI> type, List<Participation> participations) {
		super(identity);
		setTypes(type);
		this.participations = new HashMap<URI, Participation>(); 
		setParticipations(participations);
	}
	
	public Interaction(Interaction interaction) {
		super(interaction);
		Set<URI> type = new HashSet<URI>();
		for (URI typeElement : interaction.getTypes()) {
			type.add(URI.create(typeElement.toString()));
		}
		this.setTypes(type);
		List<Participation> participations = new ArrayList<Participation>();
		for (Participation participation : interaction.getParticipations()) {
			participations.add(participation.deepCopy());
		}
		this.setParticipations(participations);
	}


	/**
	 * Adds the specified element to the set <code>type</code> if it is not already present. 
	 * @param typeURI
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addType(URI typeURI) {
		return types.add(typeURI);
	}
	
	/**
	 * Removes the specified element from the set <code>type</code> if it is present.
	 * @param typeURI
	 * @return <code>true<code> if this set contained the specified element
	 */
	public boolean removeType(URI typeURI) {
		return types.remove(typeURI);
	}
	
	/**
	 * Sets the field variable <code>type</code> to the specified element.
	 * @param type
	 */
	public void setTypes(Set<URI> type) {
		this.types = type;
	}
	
	/**
	 * Returns the field variable <code>type</code>.
	 * @return
	 */
	public Set<URI> getTypes() {
		return types;
	}
	
	/**
	 * Returns true if the set <code>type</code> contains the specified element. 
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsType(URI typeURI) {
		return types.contains(typeURI);
	}
	
	/**
	 * Removes all entries of the list of <code>type</code> instances owned by this instance. 
	 * The list will be empty after this call returns.
	 */
	public void clearTypes() {
		types.clear();
	}
	
	/**
	 * Test if the optional field variable <code>participations</code> is set.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetParticipations() {
		if (participations == null || participations.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Calls the Participation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of Participation instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the  created Participation instance. 
	 */
	public Participation createParticipation(URI identity, Set<URI> role, URI participant) {
		Participation participation = new Participation(identity, role, participant);
		if (addParticipation(participation)) { 
			return participation;
		}
		else {
			return null;
		}
	}
	
	public Participation createParticipation(String displayId, Set<URI> role, URI participant) {
		String parentPersistentIdStr = extractPersistentId(this.getIdentity());
		if (parentPersistentIdStr != null) {
			if (isDisplayIdCompliant(displayId)) {
				URI newMapsToURI = URI.create(parentPersistentIdStr + '/' + displayId + '/' 
						+ extractVersion(this.getIdentity()));
				return createParticipation(newMapsToURI, role, participant);
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
	 * Adds the specified instance to the list of participations. 
	 * @param participation
	 */
	public boolean addParticipation(Participation participation) {
		if (isChildURIcompliant(this.getIdentity(), participation.getIdentity())) {
			// Check if persistent identity exists in other maps.
			URI persistentId = URI.create(extractPersistentId(participation.getIdentity()));
			// Check if URI exists in the participations map.
			if (!participations.containsKey(participation.getIdentity())) {
				participations.put(participation.getIdentity(), participation);
				Participation latestParticipation = participations.get(persistentId);
				if (latestParticipation == null) {
					participations.put(persistentId, participation);
				}
				else {						
					if (isFirstVersionNewer(extractVersion(participation.getIdentity()), 
							extractVersion(latestParticipation.getIdentity()))) {								
						participations.put(persistentId, participation);
					}
				}
				return true;
			}
			else // key exists in participations map
				return false;
		}
		else { // Only check if participation's URI exists in all maps.
			if (!participations.containsKey(participation.getIdentity())) {
				participations.put(participation.getIdentity(), participation);					
				return true;
			}
			else // key exists in participations map
				return false;
		}		
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of participations if present.
	 * @param participationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Participation removeParticipation(URI participationURI) {
		// TODO: Need to prevent removing all participations here?
		return participations.remove(participationURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of participations if present.
	 * @param participationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Participation getParticipation(URI participationURI) {
		return participations.get(participationURI);
	}
	
	/**
	 * Returns the list of participation instances owned by this instance. 
	 * @return the list of participation instances owned by this instance.
	 */
	public List<Participation> getParticipations() {
//		return (List<Participation>) participations.values();
		return new ArrayList<Participation>(participations.values());
	}
	
	/**
	 * Removes all entries of the list of participation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearParticipations() {
		Object[] keySetArray = participations.keySet().toArray();
		for (Object key : keySetArray) {
			removeParticipation((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of participation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param participations
	 */
	public void setParticipations(
			List<Participation> participations) {
		if(isSetParticipations())
			clearParticipations();	
		for (Participation participation : participations) {
			addParticipation(participation);
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((participations == null) ? 0 : participations.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
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
		Interaction other = (Interaction) obj;
		if (participations == null) {
			if (other.participations != null)
				return false;
		} else if (!participations.equals(other.participations))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		return true;
	}

	@Override
	protected Interaction deepCopy() {
		return new Interaction(this);
	}
	
	/**
	 * Assume this Component object and all its descendants (children, grand children, etc) have compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 * @param URIprefix
	 * @param parentDisplayId
	 * @param version
	 */
	void updateCompliantURI(String URIprefix, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity(), 1); // 1 indicates that this object is a child of a top-level object.
		URI newIdentity = URI.create(URIprefix + '/' + parentDisplayId + '/' 
				+ thisObjDisplayId + '/' + version);
		if (!this.getParticipations().isEmpty()) {
			// Update children's URIs
			for (Participation participation : this.getParticipations()) {
				participation.updateCompliantURI(URIprefix, parentDisplayId, thisObjDisplayId, version);
			}
		}
		// TODO: need to set wasDerivedFrom here?
		this.setWasDerivedFrom(this.getIdentity());
		this.setIdentity(newIdentity);		
	}
	
}

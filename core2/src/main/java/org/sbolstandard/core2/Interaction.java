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

public class Interaction extends Identified {

	private Set<URI> types;
	private HashMap<URI, Participation> participations;
	private ModuleDefinition moduleDefinition = null;
	
	/**
	 * 
	 * @param identity an identity for the interaction	 
	 * @param type a type for the interaction
	 */
	Interaction(URI identity, Set<URI> type) {
		super(identity);
		this.types = new HashSet<>();
		setTypes(type);
		this.participations = new HashMap<>();
	}
	
	Interaction(Interaction interaction) {
		super(interaction);
		Set<URI> type = new HashSet<>();
		for (URI typeElement : interaction.getTypes()) {
			type.add(URI.create(typeElement.toString()));
		}
		this.setTypes(type);
		List<Participation> participations = new ArrayList<>();
		for (Participation participation : interaction.getParticipations()) {
			participations.add(participation.deepCopy());
		}
		this.setParticipations(participations);
	}


	/**
	 * Adds the specified element to the set <code>type</code> if it is not already present. 
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addType(URI typeURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return types.add(typeURI);
	}
	
	/**
	 * Removes the specified element from the set <code>type</code> if it is present.
	 * @return <code>true</code> if this set contained the specified element
	 */
	public boolean removeType(URI typeURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (types.size()==1 && types.contains(typeURI)) {
			throw new IllegalArgumentException("Interaction " + this.getIdentity() + " must have at least one type.");
		}
		return types.remove(typeURI);
	}
	
	/**
	 * Sets the field variable <code>type</code> to the specified element.
	 */
	public void setTypes(Set<URI> types) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (types==null || types.size()==0) {
			throw new IllegalArgumentException("Interaction " + this.getIdentity() + " must have at least one type.");
		}
		clearTypes();
		for (URI type : types) {
			addType(type);
		}
	}
	
	/**
	 * Returns the field variable <code>type</code>.
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
	void clearTypes() {
		types.clear();
	}
	
//	/**
//	 * Test if the optional field variable <code>participations</code> is set.
//	 * @return <code>true</code> if the field variable is not an empty list
//	 */
//	public boolean isSetParticipations() {
//		return !(participations == null || participations.isEmpty());
//	}
	
	/**
	 * Calls the Participation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of Participation instances owned by this instance.
	 * @return the  created Participation instance.
	 */
	Participation createParticipation(URI identity, URI participant) {
		Participation participation = new Participation(identity, participant);
		addParticipation(participation);
		return participation;
	}
	
	public Participation createParticipation(String displayId, String participantId) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI participant = URIcompliance.createCompliantURI(moduleDefinition.getPersistentIdentity().toString(), 
				participantId, moduleDefinition.getVersion());
		return createParticipation(displayId,participant);
	}

	public Participation createParticipation(String displayId, URI participant) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (moduleDefinition != null) {
			if (moduleDefinition.getFunctionalComponent(participant)==null) {
				throw new IllegalArgumentException("Functional component '" + participant + "' does not exist.");
			}
		}
		String parentPersistentIdStr = extractPersistentId(this.getIdentity());
		String version = this.getVersion();
		if(parentPersistentIdStr == null) {
			throw new IllegalStateException(
					"Can not create a child on a parent that has the non-standard compliant identity " +
							this.getIdentity());
		}
		//validateIdVersion(displayId, version);
        Participation p = createParticipation(
				createCompliantURI(parentPersistentIdStr, displayId, version), participant);
		p.setPersistentIdentity(createCompliantURI(parentPersistentIdStr, displayId, ""));
		p.setDisplayId(displayId);
		p.setVersion(version);
		return p;
	}
	
	/**
	 * Adds the specified instance to the list of participations. 
	 */
	void addParticipation(Participation participation) {
        addChildSafely(participation, participations, "participation");
		participation.setSBOLDocument(this.sbolDocument);
        participation.setModuleDefinition(moduleDefinition);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of participations if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeParticipation(Participation participation) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(participation,participations);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of participations if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Participation getParticipation(URI participationURI) {
		return participations.get(participationURI);
	}
	
	/**
	 * Returns the list of participation instances owned by this instance. 
	 * @return the list of participation instances owned by this instance.
	 */
	public Set<Participation> getParticipations() {
		return new HashSet<>(participations.values());
	}
	
	/**
	 * Removes all entries of the list of participations owned by this instance. The list will be empty after this call returns.
	 */
	public void clearParticipations() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = participations.values().toArray();
		for (Object participation : valueSetArray) {
			removeParticipation((Participation)participation);
		}

	}
		
	/**
	 * Clears the existing list of participation instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setParticipations(
			List<Participation> participations) {
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
	 */
	void updateCompliantURI(String URIprefix, String parentDisplayId, String version) {
		String thisObjDisplayId = extractDisplayId(this.getIdentity()); // 1 indicates that this object is a child of a top-level object.
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

	/**
	 * @return the moduleDefinition
	 */
	ModuleDefinition getModuleDefinition() {
		return moduleDefinition;
	}

	/**
	 * @param moduleDefinition the moduleDefinition to set
	 */
	void setModuleDefinition(ModuleDefinition moduleDefinition) {
		this.moduleDefinition = moduleDefinition;
	}
	
}

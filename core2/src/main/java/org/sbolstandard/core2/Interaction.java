
package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.extractDisplayId;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
	 * @throws SBOLValidationException 
	 */
	Interaction(URI identity, Set<URI> type) throws SBOLValidationException {
		super(identity);
		this.types = new HashSet<>();
		this.participations = new HashMap<>();
		setTypes(type);
	}

	Interaction(Interaction interaction) throws SBOLValidationException {
		super(interaction);
		this.types = new HashSet<>();
		this.participations = new HashMap<>();
		Set<URI> type = new HashSet<>();
		for (URI typeElement : interaction.getTypes()) {
			type.add(URI.create(typeElement.toString()));
		}
		this.setTypes(type);
		Set<Participation> participations = new HashSet<>();
		for (Participation participation : interaction.getParticipations()) {
			participations.add(participation.deepCopy());
		}
		this.setParticipations(participations);
	}

	/**
	 * Adds the given type URI to this Interaction's set of reference type URIs.
	 * <p>
	 * If this Interaction object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param typeURI
	 * @return {@code true} if this set did not already contain the specified role.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public boolean addType(URI typeURI) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return types.add(typeURI);
	}

	/**
	 * Removes the given type reference from the set of type references.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param typeURI
	 * @return {@code true} if the matching type reference is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws SBOLValidationException if this Interaction object has only one element matching the given
	 * {@code typeURI} before removal.
	 */
	public boolean removeType(URI typeURI) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (types.size()==1 && types.contains(typeURI)) {
			//throw new SBOLValidationException("Interaction " + this.getIdentity() + " must have at least one type.");
			throw new SBOLValidationException("sbol-11902", this);
		}
		return types.remove(typeURI);
	}

	/**
	 * Clears the existing set of type references first, then adds the given
	 * set of the type references to this Interaction object.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param types
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws SBOLValidationException if the given {@code types} argument is either {@code null} or empty
	 */
	public void setTypes(Set<URI> types) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (types==null || types.size()==0) {
			//throw new SBOLValidationException("Interaction " + this.getIdentity() + " must have at least one type.");
			throw new SBOLValidationException("sbol-11902", this);
		}
		clearTypes();
		for (URI type : types) {
			addType(type);
		}
	}

	/**
	 * Returns the set of type URIs owned by this Interaction object.
	 *
	 * @return the set of type URIs owned by this Interaction object.
	 */
	public Set<URI> getTypes() {
		return types;
	}

	/**
	 * Checks if the given type URI is included in this Interaction
	 * object's set of reference type URIs.
	 *
	 * @param typeURI
	 * @return {@code true} if this set contains the given URI, {@code false} otherwise.
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
	 * @throws SBOLValidationException 
	 */
	Participation createParticipation(URI identity, URI participant) throws SBOLValidationException {
		Participation participation = new Participation(identity, participant);
		addParticipation(participation);
		return participation;
	}

	/**
	 * Creates a child Participation instance for this Interaction
	 * object with the given arguments, and then adds to this Interaction's list of Participation instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Participation URI with this Interaction object's
	 * persistent identity URI, the given {@code paricipantId}, and this Interaction object's version.
	 * It then calls {@link #createParticipation(String, URI)}
	 * with this component definition URI.
	 *
	 * @param displayId
	 * @param participantId
	 * @return a Participation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public Participation createParticipation(String displayId, String participantId) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI participantURI = URIcompliance.createCompliantURI(moduleDefinition.getPersistentIdentity().toString(),
				participantId, moduleDefinition.getVersion());
		if (sbolDocument!=null && sbolDocument.isCreateDefaults() && moduleDefinition!=null &&
				moduleDefinition.getFunctionalComponent(participantURI)==null) {
			moduleDefinition.createFunctionalComponent(participantId,AccessType.PUBLIC,participantId,"",
					DirectionType.INOUT);
		}
		return createParticipation(displayId,participantURI);
	}

	/**
	 * Creates a child Participation instance for this Interaction
	 * object with the given arguments, and then adds to this Interaction's list of Participation instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Participation URI with this Interaction object's
	 * persistent identity URI, the given {@code displayId}, and this Interaction object's version.
	 *
	 *
	 * @param displayId
	 * @param participant
	 * @return a Participation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the FunctionalComponent URI referenced by the Participation
	 * instance, i.e. {@code participant}, does not belong to the list of FunctionalComponent instances owned by
	 * this Interaction's parent ModuleDefinition instance.
	 * @throws IllegalStateException if this Interaction instance has non-standard compliant identity
	 */
	public Participation createParticipation(String displayId, URI participant) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (moduleDefinition != null) {
			if (moduleDefinition.getFunctionalComponent(participant)==null) {
				//throw new SBOLValidationException("Functional component '" + participant + "' does not exist.");
				throw new SBOLValidationException("sbol-12003");
				// TODO: (Validation) print participant URI?
			}
		}
		String parentPersistentIdStr = this.getPersistentIdentity().toString();
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
	 * @throws SBOLValidationException 
	 */
	void addParticipation(Participation participation) throws SBOLValidationException {
		if (moduleDefinition != null && moduleDefinition.getFunctionalComponent(participation.getParticipantURI())==null) {
			//throw new SBOLValidationException("Functional component '" + participation.getParticipantURI() + "' does not exist.");
			throw new SBOLValidationException("sbol-12003", participation);
		}
		addChildSafely(participation, participations, "participation");
		participation.setSBOLDocument(this.sbolDocument);
		participation.setModuleDefinition(moduleDefinition);
	}

	/**
	 * Removes the given Participation instance from this Interaction object's list of
	 * Participation instances.
	 * <p>
	 * If this ModuleDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param participation
	 * @return {@code true} if the matching Participation instance is removed successfully,
	 *         {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeParticipation(Participation participation) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(participation,participations);
	}

	/**
	 * Returns the Participation instance matching the given {@code displayId} from
	 * this Interaction object's list of Participation instances.
	 *
	 * @param displayId
	 * @return the matching instance if present, or {@code null} otherwise.
	 */
	public Participation getParticipation(String displayId) {
		try {
			return participations.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the Participation instance matching the given {@code participationURI} from this
	 * Interaction object's list of Participation instances.
	 *
	 * @param participationURI
	 * @return the matching Participation instance if present, or
	 *         {@code null} otherwise.
	 */
	public Participation getParticipation(URI participationURI) {
		return participations.get(participationURI);
	}

	/**
	 * Returns the set of Participation instances owned by this
	 * Interaction object.
	 *
	 * @return the set of the set of Participation instances owned by this
	 * Interaction object.
	 */
	public Set<Participation> getParticipations() {
		return new HashSet<>(participations.values());
	}

	/**
	 * Removes all entries of this Interaction object's list of SequenceAnnotation objects.
	 * The list will be empty after this call returns.
	 * <p>
	 * If this Interaction object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method calls {@link #removeParticipation(Participation)} to iteratively remove
	 * each Participation instance owned by this object.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearParticipations() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = participations.values().toArray();
		for (Object participation : valueSetArray) {
			removeParticipation((Participation)participation);
		}

	}

	/**
	 * Clears the existing list of participation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @throws SBOLValidationException 
	 */
	void setParticipations(Set<Participation> participations) throws SBOLValidationException {
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
	protected Interaction deepCopy() throws SBOLValidationException {
		return new Interaction(this);
	}

	/**
	 * Assume this Component object and all its descendants (children, grand children, etc) have compliant URI, and all given parameters have compliant forms.
	 * This method is called by {@link ComponentDefinition#copy(String, String, String)}.
	 * @throws SBOLValidationException 
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) throws SBOLValidationException {
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.setWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
		int count = 0;
		for (Participation participation : this.getParticipations()) {
			if (!participation.isSetDisplayId()) participation.setDisplayId("participation"+ ++count);
			participation.updateCompliantURI(this.getPersistentIdentity().toString(),
					participation.getDisplayId(), version);
			this.removeChildSafely(participation, this.participations);
			this.addParticipation(participation);
			String participantId = extractDisplayId(participation.getParticipantURI());
			participation.setParticipant(createCompliantURI(URIprefix,participantId,version));
		}
	}

	/**
	 * Returns this Interaction object's parent ModuleDefinition instance.
	 *
	 * @return this Interaction object's parent ModuleDefinition instance
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

	@Override
	public String toString() {
		return "Interaction [types=" + types + ", participations=" + participations + ", identity="
				+ identity + ", displayId=" + displayId + ", name=" + name + ", description="
				+ description + "]";
	}

}

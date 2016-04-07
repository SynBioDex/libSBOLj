
package org.sbolstandard.core2;
import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.isChildURIcompliant;
import static org.sbolstandard.core2.URIcompliance.isTopLevelURIformCompliant;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
public class ComponentDefinition extends TopLevel {

	private Set<URI> types;
	private Set<URI> roles;
	private Set<URI> sequences;
	private HashMap<URI, Component> components;
	private HashMap<URI, SequenceAnnotation> sequenceAnnotations;
	private HashMap<URI, SequenceConstraint> sequenceConstraints;

	/* Types */
	/**
	 * A physical entity consisting of a sequence of deoxyribonucleotide monophosphates; a deoxyribonucleic acid
	 * (<a href="http://www.biopax.org/release/biopax-level3-documentation.pdf">DNA</a>).
	 */
	public static final URI DNA = URI.create("http://www.biopax.org/release/biopax-level3.owl#DnaRegion");

	/**
	 * A physical entity consisting of a sequence of ribonucleotide monophosphates; a ribonucleic acid
	 * (<a href="http://www.biopax.org/release/biopax-level3-documentation.pdf">RNA</a>).
	 * Aspects of the state of the RNA molecule, including cellular location and features, are defined here,
	 * using properties inherited from PhysicalEntity.
	 */
	public static final URI RNA = URI.create("http://www.biopax.org/release/biopax-level3.owl#RnaRegion");

	/**
	 * A physical entity consisting of a sequence of amino acids; a protein monomer; a single polypeptide
	 * chain (<a href="http://www.biopax.org/release/biopax-level3-documentation.pdf">Protein</a>). Aspects of the state of the protein, including cellular location and features, are defined here,
	 * using properties inherited from PhysicalEntity.
	 */
	public static final URI PROTEIN = URI.create("http://www.biopax.org/release/biopax-level3.owl#Protein");

	/**
	 * A small bioactive molecule (<a href="http://www.biopax.org/release/biopax-level3-documentation.pdf">SmallMolecule</a>). Small is not precisely defined, but includes all metabolites and most drugs
	 * and does not include large polymers, including complex carbohydrates. Aspects of the state of the small
	 * molecule, including cellular location and binding features, are defined here, using properties
	 * inherited from PhysicalEntity.
	 */
	public static final URI SMALL_MOLECULE = URI.create("http://www.biopax.org/release/biopax-level3.owl#SmallMolecule");

	/**
	 * A physical entity whose structure is comprised of other physical entities bound to each other covalently or non-covalently,
	 * at least one of which is a macromolecule (e.g. protein, DNA, or RNA) and the Stoichiometry of the components are known.
	 * Comment: Complexes must be stable enough to function as a biological unit; in general, the temporary association of an enzyme
	 * with its substrate(s) should not be considered or represented as a complex. A complex is the physical product of an interaction
	 * (complexAssembly) and is not itself considered an interaction. The boundaries on the size of complexes described by this class
	 * are not defined here, although possible, elements of the cell such a mitochondria would typically not be described using this
	 * class (later versions of this ontology may include a cellularComponent class to represent these). The strength of binding cannot
	 * be described currently, but may be included in future versions of the ontology, depending on community need. Examples: Ribosome,
	 * RNA polymerase II. Other examples of this class include complexes of multiple protein monomers and complexes of proteins and small
	 * molecules.
	 */
	public static final URI COMPLEX = URI.create("http://www.biopax.org/release/biopax-level3.owl#Complex");

	/* Roles */
	//public static final URI TRANSCRIPTION_FACTOR = URI.create("http://identifiers.org/go/GO:0003700");
	/**
	 * A small molecule which increases (activator) or decreases (inhibitor) the activity of an
	 * (allosteric) enzyme by binding to the enzyme at the regulatory site
	 * (which is different from the substrate-binding catalytic site) (<a href="http://identifiers.org/chebi/CHEBI:35224">Effector</a>).
	 */
	public static final URI EFFECTOR = URI.create("http://identifiers.org/chebi/CHEBI:35224");

	ComponentDefinition(URI identity, Set<URI> types) throws SBOLValidationException {
		super(identity);
		this.types = new HashSet<>();
		this.roles = new HashSet<>();
		this.sequences = new HashSet<>();
		this.components = new HashMap<>();
		this.sequenceAnnotations = new HashMap<>();
		this.sequenceConstraints = new HashMap<>();
		setTypes(types);
	}

	private ComponentDefinition(ComponentDefinition componentDefinition) throws SBOLValidationException {
		super(componentDefinition);
		this.types = new HashSet<>();
		this.roles = new HashSet<>();
		this.sequences = new HashSet<>();
		this.components = new HashMap<>();
		this.sequenceAnnotations = new HashMap<>();
		this.sequenceConstraints = new HashMap<>();
		for (URI type : componentDefinition.getTypes()) {
			this.addType(URI.create(type.toString()));
		}
		for (URI role : componentDefinition.getRoles()) {
			this.addRole(URI.create(role.toString()));
		}
		for (Component subComponent : componentDefinition.getComponents()) {
			this.addComponent(subComponent.deepCopy());
		}
		for (SequenceConstraint sequenceConstraint : componentDefinition.getSequenceConstraints()) {
			this.addSequenceConstraint(sequenceConstraint.deepCopy());
		}
		for (SequenceAnnotation sequenceAnnotation : componentDefinition.getSequenceAnnotations()) {
			this.addSequenceAnnotation(sequenceAnnotation.deepCopy());
		}
		this.setSequences(componentDefinition.getSequenceURIs());
	}

	/**
	 * Adds the given type URI to this ComponentDefinition's set of reference type URIs.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param typeURI
	 * @return @return {@code true} if this set did not already contain the given Sequence instance URI.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public boolean addType(URI typeURI) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (typeURI.equals(DNA)||typeURI.equals(RNA)||typeURI.equals(PROTEIN)||typeURI.equals(SMALL_MOLECULE)) {
			if (this.containsType(DNA)||this.containsType(RNA)||this.containsType(PROTEIN)||this.containsType(SMALL_MOLECULE)) {
//				throw new SBOLValidationException("Component definition " + this.getIdentity() +
//						" must have only one type from Table 2 in the specification.");
				throw new SBOLValidationException("sbol-10503", this);
			}
		}
		return types.add(typeURI);
	}

	/**
	 * Removes the specified type reference from the set of type references.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param typeURI
	 * @return {@code true} if the matching type reference is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeType(URI typeURI) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (types.size()==1 && types.contains(typeURI)) {
			//throw new SBOLValidationException("Component definition " + this.getIdentity() + " must have at least one type.");
			throw new SBOLValidationException("sbol-10502", this);
		}
		return types.remove(typeURI);
	}

	/**
	 * Clears the existing set of role references first, then adds the specified
	 * set of the role references to this ComponentDefinition object.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param types
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws SBOLValidationException if {@code types} is {@code null} or its size is 0
	 */
	public void setTypes(Set<URI> types) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (types==null || types.size()==0) {
			//throw new SBOLValidationException("Component definition " + this.getIdentity() + " must have at least one type.");
			throw new SBOLValidationException("sbol-10502", this);
		}
		clearTypes();
		for (URI type : types) {
			addType(type);
		}
	}

	/**
	 * Returns the set of type URIs owned by this ComponentDefinition object.
	 *
	 * @return the set of type URIs owned by this ComponentDefinition object.
	 */
	public Set<URI> getTypes() {
		Set<URI> result = new HashSet<>();
		result.addAll(types);
		return result;
	}

	/**
	 * Checks if the given type URI is included in this ComponentDefinition
	 * object's set of reference type URIs.
	 *
	 * @param typeURI
	 * @return {@code true} if this set contains the given URI.
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

	/**
	 * Adds the given role URI to this ComponentDefinition object's set of role URIs.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roleURI
	 * @return {@code true} if this set did not already contain the specified role.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public boolean addRole(URI roleURI) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return roles.add(roleURI);
	}

	/**
	 * Removes the specified role reference from the set of role references.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roleURI
	 * @return {@code true} if the matching role reference is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeRole(URI roleURI) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return roles.remove(roleURI);
	}

	/**
	 * Clears the existing set of role references first, then adds the specified
	 * set of the role references to this ComponentDefinition object.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param roles
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setRoles(Set<URI> roles) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		clearRoles();
		if (roles==null) return;
		for (URI role : roles) {
			addRole(role);
		}
	}

	/**
	 * Returns the set of role URIs owned by this ComponentDefinition object.
	 *
	 * @return the set of role URIs owned by this ComponentDefinition object.
	 */
	public Set<URI> getRoles() {
		Set<URI> result = new HashSet<>();
		result.addAll(roles);
		return result;
	}

	/**
	 * Checks if the given role URI is included in this ComponentDefinition
	 * object's set of reference role URIs.
	 *
	 * @param roleURI
	 * @return {@code true} if this set contains the given URI.
	 */
	public boolean containsRole(URI roleURI) {
		return roles.contains(roleURI);
	}

	/**
	 * Removes all entries of this ComponentDefinition object's set of role URIs.
	 * The set will be empty after this call returns.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearRoles() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		roles.clear();
	}

	/**
	 * Adds the URI of the given Sequence instance to this ComponentDefinition object's
	 * set of reference Sequence URIs.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all its
	 * reference URIs and the given model's URI
	 * is not found in them, then an {@link SBOLValidationException} is thrown.
	 * <p>
	 * This method calls {@link #addSequence(URI)} with this Sequence URI.
	 *
	 * @param sequence
	 * @return {@code true} if this set did not already contain the given Sequence instance URI.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the associated SBOLDocument instance already completely specifies all URIs
	 *             	 and the given Sequence instance's URI is not found in them.
	 */
	public boolean addSequence(Sequence sequence) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getSequence(sequence.getIdentity())==null) {
				//throw new SBOLValidationException("Sequence '" + sequence.getIdentity() + "' does not exist.");
				throw new SBOLValidationException("sbol-10513", sequence);
			}
		}
		return this.addSequence(sequence.getIdentity());
	}

	/**
	 * Adds the given Sequence URI to this ComponentDefinition object's
	 * set of reference Sequence URIs.
	 *
	 * @param sequenceURI
	 * @return {@code true} if this set did not already contain the given Sequence instance URI.
	 * @throws SBOLValidationException 
	 */
	public boolean addSequence(URI sequenceURI) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getSequence(sequenceURI)==null) {
				throw new SBOLValidationException("sbol-10513",this);
			}
		}
		return sequences.add(sequenceURI);
	}

	/**
	 * Adds the URI of the given Sequence instance to this ComponentDefinition object's
	 * set of reference Sequence URIs.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Sequence URI with the default
	 * URI prefix for this SBOLDocument instance, and the given {@code sequenceId} and {@code version}.
	 * It then calls {@link #addSequence(URI)} with this Sequence URI.
	 *
	 * @param sequenceId
	 * @param version
	 * @return {@code true} if this set did not already contain the given Sequence instance URI.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public boolean addSequence(String sequenceId,String version) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI sequenceURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(),
				TopLevel.SEQUENCE, sequenceId, version, sbolDocument.isTypesInURIs());
		return addSequence(sequenceURI);
	}

	/**
	 * Adds the URI of the given Sequence instance to this ComponentDefinition object's
	 * set of reference Sequence URIs.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Sequence URI with the default
	 * URI prefix for this SBOLDocument instance, and the given {@code sequenceId}
	 * It then calls {@link #addSequence(URI)} with this Sequence URI.
	 *
	 * @param sequenceId
	 * @return {@code true} if this set did not already contain the given Sequence instance URI.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public boolean addSequence(String sequenceId) throws SBOLValidationException {
		return addSequence(sequenceId,"");
	}

	/**
	 * Returns the set of Sequence URIs referenced by this ComponentDefinition's object.
	 *
	 * @return the set of Sequence URIs referenced by this ComponentDefinition's object.
	 */
	public Set<URI> getSequenceURIs() {
		return sequences;
	}

	/**
	 * Returns the set of Sequence instances referenced by this ComponentDefinition object.
	 *
	 * @return the set of Sequence instances referenced by this ComponentDefinition object
	 */
	public Set<Sequence> getSequences() {
		if (sbolDocument==null) return null;
		Set<Sequence> resolved = new HashSet<>();
		for(URI su : sequences) {
			Sequence seq = sbolDocument.getSequence(su);
			if(seq != null) {
				resolved.add(seq);
			}
		}
		return resolved;
	}

	/**
	 * Clears the existing set of Sequence references first, then adds the given
	 * set of the Sequence references to this ComponentDefinition object.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param sequences
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setSequences(Set<URI> sequences) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		clearSequences();
		if (sequences==null) return;
		for (URI sequence : sequences) {
			addSequence(sequence);
		}
	}

	/**
	 * Removes the given Sequence reference from the set of Sequence references.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param sequenceURI
	 * @return {@code true} if the matching Sequence reference is removed successfully,
	 *         {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeSequence(URI sequenceURI) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return sequences.remove(sequenceURI);
	}

	/**
	 * Removes all entries of this ComponentDefinition object's set of reference
	 * Sequence URIs. The set will be empty after this call returns.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first.
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearSequences() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		sequences.clear();
	}

	//	public boolean containsSequence(Sequence sequence) {
	//		return containsSequenceURI(sequence.getIdentity());
	//	}

	/**
	 * Checks if the given Sequence URI is included in this ComponentDefinition
	 * object's set of reference Sequence URIs.
	 *
	 * @param sequenceURI
	 * @return {@code true} if this set contains the given URI.
	 */
	public boolean containsSequence(URI sequenceURI) {
		return sequences.contains(sequenceURI);
	}

	//	/**
	//	 * Test if any {@link SequenceAnnotation} instance exists.
	//	 * @return <code>true</code> if at least one such instance exists.
	//	 */
	//	public boolean isSetSequenceAnnotations() {
	//		if (sequenceAnnotations.isEmpty())
	//			return false;
	//		else
	//			return true;
	//	}

	/**
	 * Creates a child SequenceAnnotation instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceAnnotation instances.
	 *
	 * @param identity
	 * @param locations
	 * @return a SequenceAnnotation instance
	 * @throws SBOLValidationException 
	 */
	SequenceAnnotation createSequenceAnnotation(URI identity, Set<Location> locations) throws SBOLValidationException {
		SequenceAnnotation sequenceAnnotation = new SequenceAnnotation(identity, locations);
		addSequenceAnnotation(sequenceAnnotation);
		return sequenceAnnotation;
	}

	/**
	 * Creates a child SequenceAnnotation instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's list of SequenceAnnotation
	 * instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param displayId
	 * @param location
	 * @return a SequenceAnnotation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the created SequenceAnnotation URI is not compliant
	 * in this ComponentDefinition object's URI.
	 */
	SequenceAnnotation createSequenceAnnotation(String displayId, Location location) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI newSequenceAnnotationURI = createCompliantURI(URIprefix, displayId, version);
		//		if (!isChildURIcompliant(this.getIdentity(), newSequenceAnnotationURI))
		//			throw new SBOLValidationException("Child uri `" + newSequenceAnnotationURI +
		//					"'is not compliant in parent `" + this.getIdentity() +
		//					"' for " + URIprefix + " " + displayId + " " + version);
		Set<Location> locations = new HashSet<>();
		locations.add(location);
		SequenceAnnotation sa = createSequenceAnnotation(newSequenceAnnotationURI, locations);
		sa.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		sa.setDisplayId(displayId);
		sa.setVersion(version);
		return sa;
	}

	/**
	 * Creates a child SequenceAnnotation instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceAnnotation instances.
	 * <p>
	 * This method calls {@link #createSequenceAnnotation(String, String, OrientationType)} with
	 * a {@code null} OrientationType.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param displayId
	 * @param locationId
	 * @return a SequenceAnnotation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the created SequenceAnnotation URI is not compliant in this ComponentDefinition object's URI.
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, String locationId) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return createSequenceAnnotation(displayId,locationId,(OrientationType)null);
	}

	/**
	 * Creates a child SequenceAnnotation instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceAnnotation instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method first creates a GenericLocal instance with a compliant URI. This URI is composed
	 * of this ComponentDefinition object's persistent identity, the given SequenceAnnotation
	 * instance {@code displayId}, the given {@code locationId}, and this
	 * ComponentDefinition object's version.
	 * <p>
	 * I then creates a SequenceAnnotation instance with a compliant URI. This URI is composed of
	 * this ComponentDefinition object's persistent identity, the given SequenceAnnotation {@code displayId},
	 * and this ComponentDefinition object's version.
	 *
	 * @param displayId
	 * @param locationId
	 * @param orientation
	 * @return a SequenceAnnotation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the created SequenceAnnotation URI is not compliant in this ComponentDefinition object's URI.
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId,String locationId,OrientationType orientation) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		String URIprefix = this.getPersistentIdentity().toString()+"/"+displayId;
		String version = this.getVersion();
		GenericLocation location = new GenericLocation(createCompliantURI(URIprefix,locationId,version));
		if (orientation!=null) location.setOrientation(orientation);
		location.setPersistentIdentity(URI.create(URIprefix+"/"+locationId));
		location.setDisplayId(locationId);
		location.setVersion(this.getVersion());
		return createSequenceAnnotation(displayId, location);
	}

	/**
	 * Creates a child SequenceAnnotation instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceAnnotation instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method calls {@link #createSequenceAnnotation(String,String,int,OrientationType)} with
	 * a {@code null} OrientationType.
	 *
	 * @param displayId
	 * @param locationId
	 * @param at
	 * @return a SequenceAnnotation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the created SequenceAnnotation URI is not compliant in this ComponentDefinition object's URI.
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, String locationId, int at) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return createSequenceAnnotation(displayId,locationId,at,null);
	}

	/**
	 * Creates a child SequenceAnnotation instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceAnnotation instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method first creates a GenericLocal instance with a compliant URI. This URI is composed
	 * of this ComponentDefinition object's persistent identity, the given SequenceAnnotation
	 * instance {@code displayId}, the given {@code locationId}, and this
	 * ComponentDefinition object's version.
	 * <p>
	 * I then creates a SequenceAnnotation instance with a compliant URI. This URI is composed of
	 * this ComponentDefinition object's persistent identity, the given SequenceAnnotation {@code displayId},
	 * and this ComponentDefinition object's version.
	 *
	 * @param displayId
	 * @param locationId
	 * @param at
	 * @param orientation
	 * @return a SequenceAnnotation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the created SequenceAnnotation URI is not compliant
	 * in this ComponentDefinition object's URI.
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId,String locationId,int at,OrientationType orientation) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		String URIprefix = this.getPersistentIdentity().toString()+"/"+displayId;
		String version = this.getVersion();
		Cut location = new Cut(createCompliantURI(URIprefix,locationId,version),at);
		if (orientation!=null) location.setOrientation(orientation);
		location.setPersistentIdentity(URI.create(URIprefix+"/"+locationId));
		location.setDisplayId(locationId);
		location.setVersion(this.getVersion());
		return createSequenceAnnotation(displayId, location);
	}

	/**
	 * Creates a child SequenceAnnotation instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceAnnotation instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method calls {@link #createSequenceAnnotation(String, String, int, int, OrientationType)} with
	 * a {@code null} OrientationType.
	 *
	 * @param displayId
	 * @param locationId
	 * @param start
	 * @param end
	 * @return a SequenceAnnotation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the created SequenceAnnotation URI is not compliant
	 * in this ComponentDefinition object's URI.
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, String locationId, int start, int end) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return createSequenceAnnotation(displayId,locationId,start,end,null);
	}

	/**
	 * Creates a child SequenceAnnotation instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceAnnotation instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method first creates a Range Location instance with a compliant URI. This URI is composed
	 * of this ComponentDefinition object's persistent identity, the given SequenceAnnotation
	 * instance {@code displayId}, the given {@code locationId}, and this
	 * ComponentDefinition object's version.
	 * <p>
	 * I then creates a SequenceAnnotation instance with a compliant URI. This URI is composed of
	 * this ComponentDefinition object's persistent identity, the given SequenceAnnotation {@code displayId},
	 * and this ComponentDefinition object's version.
	 *
	 * @param displayId
	 * @param start
	 * @param end
	 * @param orientation
	 * @param componentDefinitionId
	 * @return a SequenceAnnotation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the created SequenceAnnotation URI is not compliant in this ComponentDefinition object's URI.
	 */
	//	public SequenceAnnotation createSequenceAnnotation(String displayId, int start, int end,OrientationType orientation,
	//			String componentDefinitionId) {
	//		if (sbolDocument!=null) sbolDocument.checkReadOnly();
	//		SequenceAnnotation sequenceAnnotation = createSequenceAnnotation(displayId,"range",start,end,orientation);
	//		if (this.getComponent(componentDefinitionId)==null) {
	//			createComponent(componentDefinitionId,AccessType.PUBLIC,componentDefinitionId,"");
	//		}
	//		sequenceAnnotation.setComponent(componentDefinitionId);
	//		return sequenceAnnotation;
	//	}

	/**
	 * Creates a child SequenceAnnotation instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceAnnotation instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method first creates a Range Location instance with a compliant URI. This URI is composed
	 * of this ComponentDefinition object's persistent identity, the given SequenceAnnotation
	 * instance {@code displayId}, the given {@code locationId}, and this
	 * ComponentDefinition object's version.
	 * <p>
	 * I then creates a SequenceAnnotation instance with a compliant URI. This URI is composed of
	 * this ComponentDefinition object's persistent identity, the given SequenceAnnotation {@code displayId},
	 * and this ComponentDefinition object's version.
	 *
	 * @param displayId
	 * @param locationId
	 * @param start
	 * @param end
	 * @param orientation
	 * @return a SequenceAnnotation instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the created SequenceAnnotation URI is not compliant in this ComponentDefinition object's URI.
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, String locationId, int start, int end,OrientationType orientation) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		String URIprefix = this.getPersistentIdentity().toString()+"/"+displayId;
		String version = this.getVersion();
		Location location = new Range(createCompliantURI(URIprefix,locationId,version),start,end);
		if (orientation!=null) location.setOrientation(orientation);
		location.setPersistentIdentity(URI.create(URIprefix+"/"+locationId));
		location.setDisplayId(locationId);
		location.setVersion(this.getVersion());
		return createSequenceAnnotation(displayId, location);
	}

	/**
	 * Adds the specified instance to the list of sequenceAnnotations.
	 * @throws SBOLValidationException 
	 */
	void addSequenceAnnotation(SequenceAnnotation sequenceAnnotation) throws SBOLValidationException {
		sequenceAnnotation.setSBOLDocument(this.sbolDocument);
		sequenceAnnotation.setComponentDefinition(this);
		if (sequenceAnnotation.isSetComponent()) {
			if (sequenceAnnotation.getComponent()==null) {
				//throw new SBOLValidationException("Component '" + sequenceAnnotation.getComponentURI() + "' does not exist.");
				throw new SBOLValidationException("sbol-10905", sequenceAnnotation);
			}
			for (SequenceAnnotation sa : this.getSequenceAnnotations()) {
				if (sa.isSetComponent() && sa.getComponentURI().equals(sequenceAnnotation.getComponentURI())) {
					//throw new SBOLValidationException("Multiple sequence annotations cannot refer to the same component.");
					throw new SBOLValidationException("sbol-10522", sa);
				}
			}
		}
		for (Location location : sequenceAnnotation.getLocations()) {
			location.setSBOLDocument(sbolDocument);
		}
		addChildSafely(sequenceAnnotation, sequenceAnnotations, "sequenceAnnotation",
				components, sequenceConstraints);
	}

	/**
	 * Removes the given SequenceAnnotation instance from the list of SequenceAnnotation
	 * instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param sequenceAnnotation
	 * @return {@code true} if the matching SequenceAnnotation instance is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeSequenceAnnotation(SequenceAnnotation sequenceAnnotation) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(sequenceAnnotation, sequenceAnnotations);
	}

	/**
	 * Returns the instance matching the given displayId from the list of SequenceAnnotation instances.
	 *
	 * @param displayId
	 * @return the matching SequenceAnnotation instance if present, or {@code null} otherwise.
	 */
	public SequenceAnnotation getSequenceAnnotation(String displayId) {
		try {
			return sequenceAnnotations.get(createCompliantURI(this.getPersistentIdentity().toString(),
					displayId,this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the instance matching the given SequenceAnnotation URI from the
	 * list of SequenceAnnotation instances.
	 *
	 * @param sequenceAnnotationURI
	 * @return the matching SequenceAnnotation instance if present, or
	 *         {@code null} otherwise.
	 */
	public SequenceAnnotation getSequenceAnnotation(URI sequenceAnnotationURI) {
		return sequenceAnnotations.get(sequenceAnnotationURI);
	}
	
	/**
	 * Returns the sequenceAnnotation for a given Component
	 *
	 * @param component
	 * @return the matching SequenceAnnotation instance if present, or
	 *         {@code null} otherwise.
	 */
	public SequenceAnnotation getSequenceAnnotation(Component component) {
		for (SequenceAnnotation sequenceAnnotation : this.getSequenceAnnotations()) {
			if (sequenceAnnotation.getComponent() != null &&
				sequenceAnnotation.getComponent().equals(component)) return sequenceAnnotation;
		}
		return null;
	}

	/**
	 * Returns the set of SequenceAnnotation instances owned by this
	 * ComponentDefinition object.
	 *
	 * @return the set of SequenceAnnotation instances owned by this
	 *         ComponentDefinition object.
	 */
	public Set<SequenceAnnotation> getSequenceAnnotations() {
		//		return (List<SequenceAnnotation>) structuralAnnotations.values();
		Set<SequenceAnnotation> sequenceAnnotations = new HashSet<>();
		sequenceAnnotations.addAll(this.sequenceAnnotations.values());
		return sequenceAnnotations;
	}
	
	private void getSuccessorComponents(HashMap<Component,Set<Component>> successorMap,
			Component component, Set<Component> visited) throws SBOLValidationException {
		if (visited.contains(component)) {
			//throw new SBOLValidationException("Cycle in sequence constraints");
			throw new SBOLValidationException("sbol-10605", component);
		}
		visited.add(component);
		for (SequenceConstraint sc : this.getSequenceConstraints()) {
			if (sc.getSubject().equals(component)) {
				successorMap.get(component).add(sc.getObject());
				getSuccessorComponents(successorMap,sc.getObject(),visited);
				successorMap.get(component).addAll(successorMap.get(sc.getObject()));
			}
		}
		visited.remove(component);
	}

	/**
	 * Returns a sorted list of Component instances owned by this
	 * ComponentDefinition object.
	 *
	 * @return a sorted list of Component instances owned by this
	 *         ComponentDefinition object.
	 * @throws SBOLValidationException 
	 */
	public List<Component> getSortedComponents() throws SBOLValidationException {
		List<Component> sortedComponents = new ArrayList<Component>();
		List<SequenceAnnotation> sortedSAs = new ArrayList<SequenceAnnotation>();
		sortedSAs.addAll(this.getSequenceAnnotations());
		Collections.sort(sortedSAs);
		HashMap<Component,Set<Component>> successorMap = new HashMap<Component,Set<Component>>();
		for (Component component : this.getComponents()) {
			successorMap.put(component, new HashSet<Component>());
		}
		for (int i = 0; i < sortedSAs.size(); i++) {
			SequenceAnnotation source = sortedSAs.get(i);
			if (source.getLocations().iterator().next()==null ||
					source.getLocations().iterator().next() instanceof GenericLocation) continue;
			if (source.isSetComponent()) {
				Component sourceComponent = source.getComponent();
				for (int j = i + 1; j < sortedSAs.size(); j++) {
					SequenceAnnotation target = sortedSAs.get(j);
					if (target.getLocations().iterator().next()==null ||
							target.getLocations().iterator().next() instanceof GenericLocation) continue;
					if (target.isSetComponent()) {
						Component targetComponent = target.getComponent();
						successorMap.get(sourceComponent).add(targetComponent);
					}
				}
			}
		}
		for (Component component : this.getComponents()) {
			getSuccessorComponents(successorMap,component,new HashSet<Component>());
		}
		while (true) {
			boolean change = false;
			for (Component component1 : this.getComponents()) {
				if (sortedComponents.contains(component1)) continue;
				boolean add = true;
				for (Component component2 : this.getComponents()) {
					if (component1 == component2) continue;
					if (sortedComponents.contains(component2)) continue;
					if (successorMap.get(component2).contains(component1)) {
						add = false;
						break;
					}
				}
				if (add) {
					sortedComponents.add(component1);
					change = true;
					break;
				}
			}
			if (!change) {
				break;
			}
		}
		return sortedComponents;
	}
	
	/**
	 * Returns a sorted list of SequenceAnnotation instances owned by this
	 * ComponentDefinition object.
	 *
	 * @return a sorted list of SequenceAnnotation instances owned by this
	 *         ComponentDefinition object.
	 */
	public List<SequenceAnnotation> getSortedSequenceAnnotations() {
		List<SequenceAnnotation> sortedSAs = new ArrayList<SequenceAnnotation>();
		sortedSAs.addAll(this.getSequenceAnnotations());
		Collections.sort(sortedSAs);
		return sortedSAs;
	}

	/**
	 * Removes all entries of this ComponentDefinition object's list of SequenceAnnotation objects.
	 * The list will be empty after this call returns.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method calls {@link #removeSequenceAnnotation(SequenceAnnotation)} to iteratively remove
	 * each SequenceAnnotation instance owned by this ComponentDefinition object.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearSequenceAnnotations() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = sequenceAnnotations.values().toArray();
		for (Object sequenceAnnotation : valueSetArray) {
			removeSequenceAnnotation((SequenceAnnotation)sequenceAnnotation);
		}
	}

	/**
	 * @param sequenceAnnotations
	 * @throws SBOLValidationException 
	 */
	void setSequenceAnnotations(Set<SequenceAnnotation> sequenceAnnotations) throws SBOLValidationException {
		clearSequenceAnnotations();
		for (SequenceAnnotation sequenceAnnotation : sequenceAnnotations) {
			addSequenceAnnotation(sequenceAnnotation);
		}
	}

	//	/**
	//	 * Test if the optional field variable <code>structuralInstantiations</code> is set.
	//	 * @return <code>true</code> if the field variable is not an empty list
	//	 */
	//	public boolean isSetSubComponents() {
	//		if (subComponents.isEmpty())
	//			return false;
	//		else
	//			return true;
	//	}


	/**
	 * Creates a child Component instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's list of Component
	 * instances.
	 *
	 * @param identity
	 * @param access
	 * @param componentDefinitionURI
	 * @return a Component instance
	 * @throws SBOLValidationException 
	 */
	Component createComponent(URI identity, AccessType access, URI componentDefinitionURI) throws SBOLValidationException {
		Component component = new Component(identity, access, componentDefinitionURI);
		addComponent(component);
		return component;
	}

	/**
	 * Creates a child Component instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's list of Component
	 * instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Component URI with the default
	 * URI prefix for this SBOLDocument instance, and the given {@code displayId} and {@code version}.
	 * It then calls {@link #createComponent(String, AccessType, URI)}
	 * with this component definition URI.
	 *
	 * @param displayId
	 * @param access
	 * @param componentDefinitionId
	 * @param version
	 * @return a Component instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public Component createComponent(String displayId, AccessType access,
			String componentDefinitionId, String version) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI componentDefinitionURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(),
				TopLevel.COMPONENT_DEFINITION, componentDefinitionId, version, sbolDocument.isTypesInURIs());
		return createComponent(displayId,access,componentDefinitionURI);
	}

	/**
	 * Creates a child Component instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's list of Component
	 * instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates a compliant Component URI with the default
	 * URI prefix for this SBOLDocument instance, and the given {@code displayId}.
	 * It then calls {@link #createComponent(String, AccessType, URI)}
	 * with this component definition URI.
	 *
	 * @param displayId
	 * @param access
	 * @param componentDefinitionId
	 * @return a Component instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public Component createComponent(String displayId, AccessType access, String componentDefinitionId) throws SBOLValidationException {
		return createComponent(displayId,access,componentDefinitionId,"");
	}

	/**
	 * Creates a child Component instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's list of Component
	 * instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * If the SBOLDocument instance already completely specifies all URIs and
	 * the given {@code componentDefinitionURI} is not found in them, then
	 * an {@link SBOLValidationException} is thrown.
	 * <p>
	 * This method creates a compliant Component URI with the default
	 * URI prefix for this SBOLDocument instance, the given {@code displayId}, and this ComponentDefinition
	 * object's version. It then calls {@link #createComponent(URI, AccessType, URI)}
	 * with this component definition URI.
	 *
	 * @param displayId
	 * @param access
	 * @param componentDefinitionURI
	 * @return a Component instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @throws SBOLValidationException if the associated SBOLDocument instance already completely
	 *         specifies all URIs and the given {@code componentDefinitionURI} is not found in them.
	 */
	public Component createComponent(String displayId, AccessType access, URI componentDefinitionURI) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getComponentDefinition(componentDefinitionURI)==null) {
				throw new SBOLValidationException("sbol-10604",this);
			}
		}
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		Component c = createComponent(createCompliantURI(URIprefix, displayId, version),
				access, componentDefinitionURI);
		c.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		c.setDisplayId(displayId);
		c.setVersion(version);
		return c;
	}

	/**
	 * Adds the specified instance to the list of components.
	 * @throws SBOLValidationException 
	 */
	void addComponent(Component component) throws SBOLValidationException {
		component.setSBOLDocument(this.sbolDocument);
		component.setComponentDefinition(this);
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (component.getDefinition()==null) {
				throw new SBOLValidationException("sbol-10604",component);
			}
		}
		if (this.getIdentity().equals(component.getDefinitionURI())) {
			throw new SBOLValidationException("sbol-10603",component);
		}
		Set<URI> visited = new HashSet<>();
		visited.add(this.getIdentity());
		SBOLValidate.checkComponentDefinitionCycle(sbolDocument, component.getDefinition(), visited);
		addChildSafely(component, components, "component",
				sequenceAnnotations, sequenceConstraints);
		for (MapsTo mapsTo : component.getMapsTos()) {
			if (this.getComponent(mapsTo.getLocalURI())==null) {
				throw new SBOLValidationException("sbol-10803", mapsTo);
			}
			mapsTo.setSBOLDocument(sbolDocument);
			mapsTo.setComponentDefinition(this);
			mapsTo.setComponentInstance(component);
		}
	}

	void addComponentNoCheck(Component component) throws SBOLValidationException {
		component.setSBOLDocument(this.sbolDocument);
		component.setComponentDefinition(this);
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (component.getDefinition()==null) {
				throw new SBOLValidationException("sbol-10604",component);
			}
		}
		if (this.getIdentity().equals(component.getDefinitionURI())) {
			throw new SBOLValidationException("sbol-10603",component);
		}
		Set<URI> visited = new HashSet<>();
		visited.add(this.getIdentity());
		SBOLValidate.checkComponentDefinitionCycle(sbolDocument, component.getDefinition(), visited);
		addChildSafely(component, components, "component",
				sequenceAnnotations, sequenceConstraints);
	}
	
	void checkMapsTosLocalURIs() throws SBOLValidationException {
		for (Component component : this.getComponents()) {
			for (MapsTo mapsTo : component.getMapsTos()) {
				if (this.getComponent(mapsTo.getLocalURI())==null) {
					throw new SBOLValidationException("sbol-10803", mapsTo);
				}
				mapsTo.setSBOLDocument(sbolDocument);
				mapsTo.setComponentDefinition(this);
				mapsTo.setComponentInstance(component);
			}
		}
	}
	/**
	 * Removes the given Component instance from the list of
	 * Component instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first.
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * <p>
	 * Before removing the given Component instance, this method
	 * checks if it is referenced by any children and grandchildren
	 * instances of this ComponentDefinition object.
	 *
	 * @param component
	 * @return {@code true} if the matching Component instance is removed successfully,
	 *         {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws SBOLValidationException the given Component instance is referenced.
	 */
	public boolean removeComponent(Component component) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		for (SequenceAnnotation sa : sequenceAnnotations.values()) {
			if (sa.getComponentURI().equals(component.getIdentity())) {
				throw new SBOLValidationException("sbol-10905",sa);
			}
		}
		for (SequenceConstraint sc : sequenceConstraints.values()) {
			if (sc.getSubjectURI().equals(component.getIdentity())) {
				throw new SBOLValidationException("sbol-11402", sc);
			}
			if (sc.getObjectURI().equals(component.getIdentity())) {
				throw new SBOLValidationException("sbol-11404", sc);
			}
		}
		for (Component c : components.values()) {
			for (MapsTo mt : c.getMapsTos()) {
				if (mt.getLocalURI().equals(component.getIdentity())) {
					throw new SBOLValidationException("sbol-10803", mt);
				}
			}
		}
		if (sbolDocument!=null) {
			for (ComponentDefinition cd : sbolDocument.getComponentDefinitions()) {
				for (Component c : cd.getComponents()) {
					for (MapsTo mt : c.getMapsTos()) {
						if (mt.getRemoteURI().equals(component.getIdentity())) {
							throw new SBOLValidationException("sbol-10808", mt);
						}
					}
				}
			}
		}
		return removeChildSafely(component, components);
	}

	/**
	 * Returns the instance matching the given displayId from the list of Component instances.
	 *
	 * @param displayId
	 * @return the matching Component instance if present, or {@code null} otherwise.
	 */
	public Component getComponent(String displayId) {
		try {
			return components.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the instance matching the given {@code componentURI} from the
	 * list of Component instances.
	 *
	 * @param componentURI
	 * @return the matching Component instance if present, or
	 *         {@code null} otherwise.
	 */
	public Component getComponent(URI componentURI) {
		return components.get(componentURI);
	}

	/**
	 * Returns the set of Component instances owned by this
	 * ComponentDefinition object.
	 *
	 * @return the set of Component instances owned by this
	 *         ComponentDefinition object.
	 */
	public Set<Component> getComponents() {
		Set<Component> components = new HashSet<>();
		components.addAll(this.components.values());
		return components;
	}

	/**
	 * Removes all entries of this ComponentDefinition object's list of Components objects.
	 * The list will be empty after this call returns.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method calls {@link #removeComponent(Component component)} to iteratively remove
	 * each Component instance owned by this ComponentDefinition object.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearComponents() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = components.values().toArray();
		for (Object component : valueSetArray) {
			removeComponent((Component)component);
		}
	}

	/**
	 * @param components
	 * @throws SBOLValidationException 
	 */
	void setComponents(Set<Component> components) throws SBOLValidationException {
		clearComponents();
		for (Component component : components) {
			addComponentNoCheck(component);
		}
		checkMapsTosLocalURIs();
	}

	//	/**
	//	 * Test if the optional field variable <code>sequenceConstraints</code> is set.
	//	 * @return <code>true</code> if the field variable is not an empty list
	//	 */
	//	public boolean isSetSequenceConstraints() {
	//		if (sequenceConstraints.isEmpty())
	//			return false;
	//		else
	//			return true;
	//	}

	/**
	 * Calls the StructuralConstraint constructor to create a new instance using the specified parameters,
	 * then adds to the list of StructuralConstraint instances owned by this instance.
	 * @return the created StructuralConstraint instance.
	 * @throws SBOLValidationException 
	 */
	SequenceConstraint createSequenceConstraint(URI identity, RestrictionType restriction, URI subject, URI object) throws SBOLValidationException {
		SequenceConstraint sequenceConstraint = new SequenceConstraint(identity, restriction, subject, object);
		addSequenceConstraint(sequenceConstraint);
		return sequenceConstraint;
	}

	/**
	 * Creates a child SequenceConstraint instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceConstraint instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * <p>
	 * This method creates one compliant URI for subject and object respectively,
	 * using this ComponentDefinition object's persistent ID and version, and the given {@code subject}
	 * and {@code object} arguments. These URIs are referenced by the created SequenceConstraint instance.
	 * This method calls {@link #createSequenceConstraint(String, RestrictionType, URI, URI)} to create
	 * the SequenceConstraint instance.
	 *
	 * @param displayId
	 * @param restriction
	 * @param subjectId
	 * @param objectId
	 * @return a SequenceConstraint instance
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public SequenceConstraint createSequenceConstraint(String displayId,
			RestrictionType restriction, String subjectId, String objectId) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI subjectURI = URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(),
				subjectId, this.getVersion());
		if (sbolDocument!=null && sbolDocument.isCreateDefaults() &&
				this.getComponent(subjectURI)==null) {
			this.createComponent(subjectId,AccessType.PUBLIC,subjectId,"");
		}
		URI objectURI = URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(),
				objectId, this.getVersion());
		if (sbolDocument!=null && sbolDocument.isCreateDefaults() &&
				this.getComponent(objectURI)==null) {
			this.createComponent(objectId,AccessType.PUBLIC,objectId,"");
		}
		return createSequenceConstraint(displayId,restriction,subjectURI,objectURI);
	}


	/**
	 * Creates a child SequenceConstraint instance for this ComponentDefinition
	 * object with the given arguments, and then adds to this ComponentDefinition's
	 * list of SequenceConstraint instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param displayId
	 * @param restriction
	 * @param subject
	 * @param object
	 * @return a SequenceConstraint instance
	 * @throws SBOLValidationException 
	 */
	public SequenceConstraint createSequenceConstraint(String displayId,
			RestrictionType restriction, URI subject, URI object) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		SequenceConstraint sc = createSequenceConstraint(createCompliantURI(URIprefix, displayId, version),
				restriction, subject, object);
		sc.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		sc.setDisplayId(displayId);
		sc.setVersion(version);
		return sc;
	}

	/**
	 * Adds the specified instance to the list of sequenceConstraints.
	 * @throws SBOLValidationException 
	 */
	void addSequenceConstraint(SequenceConstraint sequenceConstraint) throws SBOLValidationException {
		sequenceConstraint.setSBOLDocument(this.sbolDocument);
		sequenceConstraint.setComponentDefinition(this);
		if (sequenceConstraint.getSubject()==null) {
				throw new SBOLValidationException("sbol-11403", sequenceConstraint);
		}
		if (sequenceConstraint.getObject()==null) {
			throw new SBOLValidationException("sbol-11405", sequenceConstraint);
				}
		if (sequenceConstraint.getSubjectURI().equals(sequenceConstraint.getObjectURI())) {
			throw new SBOLValidationException("sbol-11406", sequenceConstraint);
		}
		addChildSafely(sequenceConstraint, sequenceConstraints, "sequenceConstraint",
				components, sequenceAnnotations);
	}

	/**
	 * Removes the given SequenceConstraint instance from the list of SequenceConstraint
	 * instances.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param sequenceConstraint
	 * @return {@code true} if the matching SequenceConstraint instance is removed successfully, {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeSequenceConstraint(SequenceConstraint sequenceConstraint) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(sequenceConstraint,sequenceConstraints);
	}

	/**
	 * Returns the instance matching the given displayId from the list of SequenceConstraint instances.
	 *
	 * @param displayId
	 * @return the matching SequenceConstraint instance if present, or {@code null} otherwise.
	 */
	public SequenceConstraint getSequenceConstraint(String displayId) {
		try {
			return sequenceConstraints.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,
					this.getVersion()));
		}
		catch (SBOLValidationException e) {
			return null;
		}
	}

	/**
	 * Returns the instance matching the given SequenceConstraint URI from the
	 * list of SequenceConstraint instances.
	 *
	 * @param sequenceConstraintURI
	 * @return the matching SequenceConstraint instance if present, or
	 *         {@code null} otherwise.
	 */
	public SequenceConstraint getSequenceConstraint(URI sequenceConstraintURI) {
		return sequenceConstraints.get(sequenceConstraintURI);
	}

	/**
	 * Returns the set of SequenceConstraint instances owned by this
	 * ComponentDefinition object.
	 *
	 * @return the set of SequenceConstraint instances owned by this
	 *         ComponentDefinition object.
	 */
	public Set<SequenceConstraint> getSequenceConstraints() {
		Set<SequenceConstraint> sequenceConstraints = new HashSet<>();
		sequenceConstraints.addAll(this.sequenceConstraints.values());
		return sequenceConstraints;
	}

	/**
	 * Removes all entries of this ComponentDefinition object's list of SequenceConstraints objects.
	 * The list will be empty after this call returns.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first.
	 * Only a compliant SBOLDocument instance is allowed to be edited.
	 * <p>
	 * This method calls {@link #removeSequenceConstraint(SequenceConstraint)} to iteratively remove
	 * each SequenceConstraint instance owned by this ComponentDefinition object.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearSequenceConstraints() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = sequenceConstraints.values().toArray();
		for (Object sequenceConstraint : valueSetArray) {
			removeSequenceConstraint((SequenceConstraint)sequenceConstraint);
		}
	}

	/**
	 * Clears the existing list of structuralConstraint instances, then appends all of the elements in the specified collection to the end of this list.
	 * @throws SBOLValidationException 
	 */
	void setSequenceConstraints(Set<SequenceConstraint> sequenceConstraints) throws SBOLValidationException {
		clearSequenceConstraints();
		for (SequenceConstraint sequenceConstraint : sequenceConstraints) {
			addSequenceConstraint(sequenceConstraint);
		}
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateCompliantURI(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	protected void checkDescendantsURIcompliance() throws SBOLValidationException {
		isTopLevelURIformCompliant(this.getIdentity());
		if (!this.getSequenceConstraints().isEmpty()) {
			for (SequenceConstraint sequenceConstraint : this.getSequenceConstraints()) {
				try {
					isChildURIcompliant(this, sequenceConstraint);
				}
				catch (SBOLValidationException e) {
					throw new SBOLValidationException(e.getRule(),sequenceConstraint);
				}
			}
		}
		if (!this.getComponents().isEmpty()) {
			for (Component component : this.getComponents()) {
				try {
					isChildURIcompliant(this, component);
				}
				catch (SBOLValidationException e) {
					throw new SBOLValidationException(e.getRule(),component);
				}
				if (!component.getMapsTos().isEmpty()) {
					// Check compliance of Component's children
					for (MapsTo mapsTo : component.getMapsTos()) {
						try {
							isChildURIcompliant(component, mapsTo);
						}
						catch (SBOLValidationException e) {
							throw new SBOLValidationException(e.getRule(),mapsTo);
						}
					}
				}
			}
		}
		if (!this.getSequenceAnnotations().isEmpty()) {
			for (SequenceAnnotation sequenceAnnotation : this.getSequenceAnnotations()) {
				try {
					isChildURIcompliant(this, sequenceAnnotation);
				}
				catch (SBOLValidationException e) {
					throw new SBOLValidationException(e.getRule(),sequenceAnnotation);
				}
				Set<Location> locations = sequenceAnnotation.getLocations();
				for (Location location : locations) {
					if (location instanceof Range) {
						try {
							isChildURIcompliant(sequenceAnnotation, location);
						}
						catch (SBOLValidationException e) {
							throw new SBOLValidationException(e.getRule(),location);
						}
					}
					if (location instanceof Cut) {
						try {
							isChildURIcompliant(sequenceAnnotation, location);
						}
						catch (SBOLValidationException e) {
							throw new SBOLValidationException(e.getRule(),location);
						}
					}
					if (location instanceof GenericLocation) {
						try {
							isChildURIcompliant(sequenceAnnotation, location);
						}
						catch (SBOLValidationException e) {
							throw new SBOLValidationException(e.getRule(),location);
						}
					}
				}
			}
		}
	}

	/**
	 * Provide a deep copy of this object.
	 * @throws SBOLValidationException 
	 */
	@Override
	protected ComponentDefinition deepCopy() throws SBOLValidationException {
		return new ComponentDefinition(this);
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	ComponentDefinition copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		ComponentDefinition cloned = this.deepCopy();
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
		int count = 0;
		for (Component component : cloned.getComponents()) {
			if (!component.isSetDisplayId()) component.setDisplayId("component"+ ++count);
			component.updateCompliantURI(cloned.getPersistentIdentity().toString(),
					component.getDisplayId(),version);
			cloned.removeChildSafely(component, cloned.components);
			cloned.addComponent(component);
		}
		count = 0;
		for (SequenceConstraint sequenceConstraint : cloned.getSequenceConstraints()) {
			if (!sequenceConstraint.isSetDisplayId()) sequenceConstraint.setDisplayId("sequenceConstraint"+ ++count);
			sequenceConstraint.updateCompliantURI(cloned.getPersistentIdentity().toString(),
					sequenceConstraint.getDisplayId(),version);
			cloned.removeChildSafely(sequenceConstraint, cloned.sequenceConstraints);
			cloned.addSequenceConstraint(sequenceConstraint);
		}
		count = 0;
		for (SequenceAnnotation sequenceAnnotation : cloned.getSequenceAnnotations()) {
			if (!sequenceAnnotation.isSetDisplayId()) sequenceAnnotation.setDisplayId("sequenceAnnotation"+ ++count);
			sequenceAnnotation.updateCompliantURI(cloned.getPersistentIdentity().toString(),
					sequenceAnnotation.getDisplayId(),version);
			cloned.removeChildSafely(sequenceAnnotation, cloned.sequenceAnnotations);
			cloned.addSequenceAnnotation(sequenceAnnotation);
		}
		return cloned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((sequences == null) ? 0 : sequences.hashCode());
		result = prime * result
				+ ((sequenceAnnotations == null) ? 0 : sequenceAnnotations.hashCode());
		result = prime * result
				+ ((sequenceConstraints == null) ? 0 : sequenceConstraints.hashCode());
		result = prime * result + ((components == null) ? 0 : components.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
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
		ComponentDefinition other = (ComponentDefinition) obj;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (sequences == null) {
			if (other.sequences != null)
				return false;
		} else if (!sequences.equals(other.sequences))
			return false;
		if (sequenceAnnotations == null) {
			if (other.sequenceAnnotations != null)
				return false;
		} else if (!sequenceAnnotations.equals(other.sequenceAnnotations))
			return false;
		if (sequenceConstraints == null) {
			if (other.sequenceConstraints != null)
				return false;
		} else if (!sequenceConstraints.equals(other.sequenceConstraints))
			return false;
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComponentDefinition ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ ", types=" + types 
				+ (roles.size()>0?", roles=" + roles:"")  
				+ (this.getSequenceURIs().size()>0?", sequences=" + this.getSequenceURIs():"") 
				+ (this.getComponents().size()>0?", components=" + this.getComponents():"") 
				+ (this.getSequenceAnnotations().size()>0?", sequenceAnnotations=" + this.getSequenceAnnotations():"")
				+ (this.getSequenceConstraints().size()>0?", sequenceConstraints=" + this.getSequenceConstraints():"")
				+ "]";
	}

}

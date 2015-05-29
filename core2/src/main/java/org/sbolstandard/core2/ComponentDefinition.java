package org.sbolstandard.core2;
import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
import static org.sbolstandard.core2.URIcompliance.isChildURIcompliant;
import static org.sbolstandard.core2.URIcompliance.isURIcompliant;

import java.net.URI;
import java.util.ArrayList;
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

	/* Roles */
	//public static final URI TRANSCRIPTION_FACTOR = URI.create("http://identifiers.org/go/GO:0003700");
	/**
	 * A small molecule which increases (activator) or decreases (inhibitor) the activity of an
	 * (allosteric) enzyme by binding to the enzyme at the regulatory site
	 * (which is different from the substrate-binding catalytic site) (<a href="http://identifiers.org/chebi/CHEBI:35224">Effector</a>).
	 */
	public static final URI EFFECTOR = URI.create("http://identifiers.org/chebi/CHEBI:35224");

	ComponentDefinition(URI identity, Set<URI> types) {
		super(identity);
		this.types = new HashSet<>();
		this.roles = new HashSet<>();
		this.sequences = new HashSet<>();
		this.components = new HashMap<>();
		this.sequenceAnnotations = new HashMap<>();
		this.sequenceConstraints = new HashMap<>();
		setTypes(types);
	}

	private ComponentDefinition(ComponentDefinition componentDefinition) {
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
			throw new IllegalArgumentException("Component definition " + this.getIdentity() + " must have at least one type.");
		}
		return types.remove(typeURI);
	}

	/**
	 * Sets the field variable <code>type</code> to the specified element.
	 */
	public void setTypes(Set<URI> types) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (types==null || types.size()==0) {
			throw new IllegalArgumentException("Component definition " + this.getIdentity() + " must have at least one type.");
		}
		clearTypes();
		for (URI type : types) {
			addType(type);
		}
	}

	/**
	 * Returns the field variable <code>type</code>.
	 * @return the set of URIs for <code>type</code>.
	 */
	public Set<URI> getTypes() {
		Set<URI> result = new HashSet<>();
		result.addAll(types);
		return result;
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

	/**
	 * Adds the specified element to the set <code>roles</code> if it is not already present.
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addRole(URI roleURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return roles.add(roleURI);
	}

	/**
	 * Removes the specified element from the set <code>roles</code> if it is present.
	 * @return <code>true</code> if this set contained the specified element
	 */
	public boolean removeRole(URI roleURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return roles.remove(roleURI);
	}

	/**
	 * Sets the field variable <code>roles</code> to the specified element.
	 */
	public void setRoles(Set<URI> roles) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		clearRoles();
		if (roles==null) return;
		for (URI role : roles) {
			addRole(role);
		}
	}

	/**
	 * Returns the field variable <code>roles</code>.
	 * @return the set of URIs for <code>roles</code>.
	 */
	public Set<URI> getRoles() {
		Set<URI> result = new HashSet<>();
		result.addAll(roles);
		return result;
	}

	/**
	 * Returns true if the set <code>roles</code> contains the specified element.
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsRole(URI rolesURI) {
		return roles.contains(rolesURI);
	}

	/**
	 * Removes all entries of the list of <code>roles</code> instances owned by this instance.
	 * The list will be empty after this call returns.
	 */
	public void clearRoles() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		roles.clear();
	}


	public boolean addSequence(Sequence sequence) {
		return this.addSequence(sequence.identity);
	}

	public boolean addSequence(URI sequenceURI) {
		return sequences.add(sequenceURI);
	}


	public void addSequence(String sequence,String version) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI sequenceURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(),
				TopLevel.SEQUENCE, sequence, version, sbolDocument.isTypesInURIs());
		addSequence(sequenceURI);
	}

	/**
	 * Returns the URIs of the referenced {@link Sequence} instance.
	 * @return the URIs of the referenced {@link Sequence} instance.
	 */
	public Set<URI> getSequenceURIs() {
		return sequences;
	}

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

	public void setSequences(Set<URI> sequences) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		clearSequences();
		if (sequences==null) return;
		for (URI sequence : sequences) {
			addSequence(sequence);
		}
	}

	/**
	 * Removes the instance matching the specified URI from the list of sequences if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public boolean removeSequence(URI sequenceURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return sequences.remove(sequenceURI);
	}

	public void clearSequences() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		sequences.clear();
	}

	//	public boolean containsSequence(Sequence sequence) {
	//		return containsSequenceURI(sequence.getIdentity());
	//	}

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
	 * Calls the SequenceAnnotation constructor to create a new instance using the specified parameters,
	 * then adds to the list of SequenceAnnotation instances owned by this instance.
	 * @return the created SequenceAnnotation instance.
	 */
	SequenceAnnotation createSequenceAnnotation(URI identity, List<Location> locations) {
		SequenceAnnotation sequenceAnnotation = new SequenceAnnotation(identity, locations);
		addSequenceAnnotation(sequenceAnnotation);
		return sequenceAnnotation;
	}

	/**
	 */
	SequenceAnnotation createSequenceAnnotation(String displayId, Location location) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI newSequenceAnnotationURI = createCompliantURI(URIprefix, displayId, version);
		if (!isChildURIcompliant(this.getIdentity(), newSequenceAnnotationURI))
			throw new IllegalArgumentException("Child uri `" + newSequenceAnnotationURI +
					"'is not compliant in parent `" + this.getIdentity() +
					"' for " + URIprefix + " " + displayId + " " + version);
		List<Location> locations = new ArrayList<>();
		locations.add(location);
		SequenceAnnotation sa = createSequenceAnnotation(newSequenceAnnotationURI, locations);
		sa.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		sa.setDisplayId(displayId);
		sa.setVersion(version);
		return sa;
	}

	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId,String locationId) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return createSequenceAnnotation(displayId,locationId,(OrientationType)null);
	}

	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId,String locationId,OrientationType orientation) {
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
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, String locationId, int at) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return createSequenceAnnotation(displayId,locationId,at,null);
	}

	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId,String locationId,int at,OrientationType orientation) {
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
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, String locationId, int start, int end) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return createSequenceAnnotation(displayId,locationId,start,end,null);
	}

	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, String locationId, int start, int end,OrientationType orientation) {
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
	 */
	void addSequenceAnnotation(SequenceAnnotation sequenceAnnotation) {
		addChildSafely(sequenceAnnotation, sequenceAnnotations, "sequenceAnnotation",
				components, sequenceConstraints);
		sequenceAnnotation.setSBOLDocument(this.sbolDocument);
		sequenceAnnotation.setComponentDefinition(this);
	}

	/**
	 * Removes the sequence annotation from the list of sequence annotations, if present.
	 * @param sequenceAnnotation object to be removed.
	 * @return true if the sequenceAnnotation is present and removed.
	 */
	public boolean removeSequenceAnnotation(SequenceAnnotation sequenceAnnotation) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(sequenceAnnotation, sequenceAnnotations);
	}

	/**
	 * Returns the instance matching the specified displayId from the list of sequence annotations, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceAnnotation getSequenceAnnotation(String displayId) {
		return sequenceAnnotations.get(createCompliantURI(this.getPersistentIdentity().toString(),
				displayId,this.getVersion()));
	}

	/**
	 * Returns the instance matching the specified URI from the list of sequence annotations, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceAnnotation getSequenceAnnotation(URI sequenceAnnotationURI) {
		return sequenceAnnotations.get(sequenceAnnotationURI);
	}

	/**
	 * Returns the list of structuralAnnotation instances owned by this instance.
	 * @return the list of structuralAnnotation instances owned by this instance.
	 */
	public Set<SequenceAnnotation> getSequenceAnnotations() {
		//		return (List<SequenceAnnotation>) structuralAnnotations.values();
		Set<SequenceAnnotation> sequenceAnnotations = new HashSet<>();
		sequenceAnnotations.addAll(this.sequenceAnnotations.values());
		return sequenceAnnotations;
	}

	/**
	 * Removes all entries of the list of sequence annotations owned by this instance. The list will be empty after this call returns.
	 */
	public void clearSequenceAnnotations() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = sequenceAnnotations.values().toArray();
		for (Object sequenceAnnotation : valueSetArray) {
			removeSequenceAnnotation((SequenceAnnotation)sequenceAnnotation);
		}
	}

	/**
	 * Clears the existing list of structuralAnnotation instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setSequenceAnnotations(
			List<SequenceAnnotation> sequenceAnnotations) {
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
	 * Calls the StructuralInstantiation constructor to create a new instance using the specified parameters,
	 * then adds to the list of StructuralInstantiation instances owned by this instance.
	 * @return the created StructuralInstantiation instance.
	 */
	Component createComponent(URI identity, AccessType access, URI componentDefinitionURI) {
		Component subComponent = new Component(identity, access, componentDefinitionURI);
		addComponent(subComponent);
		return subComponent;
	}

	public Component createComponent(String displayId, AccessType access,
			String componentDefinition, String version) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI componentDefinitionURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(),
				TopLevel.COMPONENT_DEFINITION, componentDefinition, version, sbolDocument.isTypesInURIs());
		return createComponent(displayId,access,componentDefinitionURI);
	}

	public Component createComponent(String displayId, AccessType access, URI componentDefinitionURI) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getComponentDefinition(componentDefinitionURI)==null) {
				throw new IllegalArgumentException("Component definition '" + componentDefinitionURI + "' does not exist.");
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
	 */
	void addComponent(Component component) {
		addChildSafely(component, components, "component",
				sequenceAnnotations, sequenceConstraints);
		component.setSBOLDocument(this.sbolDocument);
	}

	/**
	 * Removes the component from the list of components, if present.
	 * @param component object to be removed.
	 * @return true if the component is present and removed.
	 */
	public boolean removeComponent(Component component) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		for (SequenceAnnotation sa : sequenceAnnotations.values()) {
			if (sa.getComponentURI().equals(component.getIdentity())) {
				throw new SBOLException("Cannot remove " + component.getIdentity() +
						" since it is in use.");
			}
		}
		for (SequenceConstraint sc : sequenceConstraints.values()) {
			if (sc.getSubjectURI().equals(component.getIdentity())) {
				throw new SBOLException("Cannot remove " + component.getIdentity() +
						" since it is in use.");
			}
			if (sc.getObjectURI().equals(component.getIdentity())) {
				throw new SBOLException("Cannot remove " + component.getIdentity() +
						" since it is in use.");
			}
		}
		for (Component c : components.values()) {
			for (MapsTo mt : c.getMapsTos()) {
				if (mt.getLocalURI().equals(component.getIdentity())) {
					throw new SBOLException("Cannot remove " + component.getIdentity() +
							" since it is in use.");
				}
			}
		}
		if (sbolDocument!=null) {
			for (ComponentDefinition cd : sbolDocument.getComponentDefinitions()) {
				for (Component c : cd.getComponents()) {
					for (MapsTo mt : c.getMapsTos()) {
						if (mt.getRemoteURI().equals(component.getIdentity())) {
							throw new SBOLException("Cannot remove " + component.getIdentity() +
									" since it is in use.");
						}
					}
				}
			}
		}
		return removeChildSafely(component, components);
	}

	/**
	 * Returns the instance matching the specified displayId from the list of components, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component getComponent(String displayId) {
		return components.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,this.getVersion()));
	}

	/**
	 * Returns the instance matching the specified URI from the list of components, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component getComponent(URI componentURI) {
		return components.get(componentURI);
	}

	/**
	 * Returns the list of {@link Component} instances owned by this instance.
	 * @return the list of {@link Component} instances owned by this instance.
	 */
	public Set<Component> getComponents() {
		Set<Component> structuralInstantiations = new HashSet<>();
		structuralInstantiations.addAll(this.components.values());
		return structuralInstantiations;
	}

	/**
	 * Removes all entries of the list of components owned by this instance. The list will be empty after this call returns.
	 */
	public void clearComponents() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = components.values().toArray();
		for (Object component : valueSetArray) {
			removeComponent((Component)component);
		}
	}

	/**
	 * Clears the existing list of structuralInstantiation instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setComponents(List<Component> components) {
		clearComponents();
		for (Component component : components) {
			addComponent(component);
		}
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
	 */
	SequenceConstraint createSequenceConstraint(URI identity, RestrictionType restriction, URI subject, URI object) {
		SequenceConstraint sequenceConstraint = new SequenceConstraint(identity, restriction, subject, object);
		addSequenceConstraint(sequenceConstraint);
		return sequenceConstraint;
	}

	public SequenceConstraint createSequenceConstraint(String displayId,
			RestrictionType restriction, String subject, String object) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		URI subjectURI = URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(),
				subject, this.getVersion());
		URI objectURI = URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(),
				object, this.getVersion());
		return createSequenceConstraint(displayId,restriction,subjectURI,objectURI);
	}

	public SequenceConstraint createSequenceConstraint(String displayId,
			RestrictionType restriction, URI subject, URI object) {
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
	 */
	void addSequenceConstraint(SequenceConstraint sequenceConstraint) {
		sequenceConstraint.setSBOLDocument(this.sbolDocument);
		sequenceConstraint.setComponentDefinition(this);
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sequenceConstraint.getSubject()==null) {
				throw new IllegalArgumentException("Component '" + sequenceConstraint.getSubjectURI() + "' does not exist.");
			}
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sequenceConstraint.getObject()==null) {
				throw new IllegalArgumentException("Component '" + sequenceConstraint.getObjectURI() + "' does not exist.");
			}
		}
		addChildSafely(sequenceConstraint, sequenceConstraints, "sequenceConstraint",
				components, sequenceAnnotations);
	}

	/**
	 * Removes the sequence constraint from the list of sequence constraints, if present.
	 * @param sequenceConstraint object to be removed.
	 * @return true if the sequenceConstraint is present and removed.
	 */
	public boolean removeSequenceConstraint(SequenceConstraint sequenceConstraint) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return removeChildSafely(sequenceConstraint,sequenceConstraints);
	}

	/**
	 * Returns the instance matching the specified displayId from the list of sequence constraints, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceConstraint getSequenceConstraint(String displayId) {
		return sequenceConstraints.get(createCompliantURI(this.getPersistentIdentity().toString(),displayId,
				this.getVersion()));
	}

	/**
	 * Returns the instance matching the specified URI from the list of sequence constraints, if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceConstraint getSequenceConstraint(URI sequenceConstraintURI) {
		return sequenceConstraints.get(sequenceConstraintURI);
	}

	/**
	 * Returns the list of {@link SequenceConstraint} instances owned by this instance.
	 * @return the list of {@link SequenceConstraint} instances owned by this instance.
	 */
	public Set<SequenceConstraint> getSequenceConstraints() {
		Set<SequenceConstraint> sequenceConstraints = new HashSet<>();
		sequenceConstraints.addAll(this.sequenceConstraints.values());
		return sequenceConstraints;
	}

	/**
	 * Removes all entries of the list of sequence constraints owned by this instance. The list will be empty after this call returns.
	 */
	public void clearSequenceConstraints() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Object[] valueSetArray = sequenceConstraints.values().toArray();
		for (Object sequenceConstraint : valueSetArray) {
			removeSequenceConstraint((SequenceConstraint)sequenceConstraint);
		}
	}

	/**
	 * Clears the existing list of structuralConstraint instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setSequenceConstraints(
			List<SequenceConstraint> sequenceConstraints) {
		clearSequenceConstraints();
		for (SequenceConstraint sequenceConstraint : sequenceConstraints) {
			addSequenceConstraint(sequenceConstraint);
		}
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateCompliantURI(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	protected boolean checkDescendantsURIcompliance() {
		// codereview: this method is spagetti.
		if (!isURIcompliant(this.getIdentity(), 0)) { 	// ComponentDefinition to be copied has non-compliant URI.
			return false;
		}
		boolean allDescendantsCompliant = true;
		if (!this.getSequenceConstraints().isEmpty()) {
			for (SequenceConstraint sequenceConstraint : this.getSequenceConstraints()) {
				allDescendantsCompliant = allDescendantsCompliant
						&& isChildURIcompliant(this.getIdentity(), sequenceConstraint.getIdentity());
				// SequenceConstraint does not have any child classes. No need to check further.
				if (!allDescendantsCompliant) { // Current sequence constraint has non-compliant URI.
					return allDescendantsCompliant;
				}
			}
		}
		if (!this.getComponents().isEmpty()) {
			for (Component component : this.getComponents()) {
				allDescendantsCompliant = allDescendantsCompliant
						&& isChildURIcompliant(this.getIdentity(), component.getIdentity());
				if (!allDescendantsCompliant) { // Current component has non-compliant URI.
					return allDescendantsCompliant;
				}
				if (!component.getMapsTos().isEmpty()) {
					// Check compliance of Component's children
					for (MapsTo mapsTo : component.getMapsTos()) {
						allDescendantsCompliant = allDescendantsCompliant
								&& isChildURIcompliant(component.getIdentity(), mapsTo.getIdentity());
						if (!allDescendantsCompliant) { // Current mapsTo has non-compliant URI.
							return allDescendantsCompliant;
						}
					}
				}
			}
		}
		if (!this.getSequenceAnnotations().isEmpty()) {
			for (SequenceAnnotation sequenceAnnotation : this.getSequenceAnnotations()) {
				allDescendantsCompliant = allDescendantsCompliant
						&& isChildURIcompliant(this.getIdentity(), sequenceAnnotation.getIdentity());
				if (!allDescendantsCompliant) { // Current sequence annotation has non-compliant URI.
					return allDescendantsCompliant;
				}
				Set<Location> locations = sequenceAnnotation.getLocations();
				for (Location location : locations) {
					if (location instanceof Range) {
						allDescendantsCompliant = allDescendantsCompliant
								&& isChildURIcompliant(sequenceAnnotation.getIdentity(), location.getIdentity());
						if (!allDescendantsCompliant) { // Current range has non-compliant URI.
							return allDescendantsCompliant;
						}
					}
					if (location instanceof Cut) {
						allDescendantsCompliant = allDescendantsCompliant
								&& isChildURIcompliant(sequenceAnnotation.getIdentity(), location.getIdentity());
						if (!allDescendantsCompliant) { // Current cut has non-compliant URI.
							return allDescendantsCompliant;
						}
					}
					if (location instanceof GenericLocation) {
						allDescendantsCompliant = allDescendantsCompliant
								&& isChildURIcompliant(sequenceAnnotation.getIdentity(), location.getIdentity());
						if (!allDescendantsCompliant) { // Current generic location has non-compliant URI.
							return allDescendantsCompliant;
						}
					}
				}
			}
		}
		// All descendants of this ComponentDefinition object have compliant URIs.
		return allDescendantsCompliant;
	}

	protected boolean isComplete() {
		if (sbolDocument==null) return false;
		if (sequences.isEmpty()) {
			return false;
		}
		for (Component component : getComponents()) {
			if (component.getDefinition()==null) return false;
		}
		return true;
	}

	/**
	 * Provide a deep copy of this object.
	 */
	@Override
	protected ComponentDefinition deepCopy() {
		return new ComponentDefinition(this);
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	ComponentDefinition copy(String URIprefix, String displayId, String version) {
		ComponentDefinition cloned = this.deepCopy();
		cloned.setWasDerivedFrom(this.getIdentity());
		cloned.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		cloned.setDisplayId(displayId);
		cloned.setVersion(version);
		URI newIdentity = createCompliantURI(URIprefix,displayId,version);
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

	//	/**
	//	 * Get a deep copy of the object first, and set its major version to the specified value, and minor version to "0".
	//	 * @param newVersion
	//	 * @return the copied {@link ComponentDefinition} instance with the specified major version.
	//	 */
	//	public ComponentDefinition newVersion(String newVersion) {
	//		ComponentDefinition cloned = (ComponentDefinition) super.newVersion(newVersion);
	//		cloned.updateVersion(newVersion);
	//		return cloned;
	//	}
	//
	//	/* (non-Javadoc)
	//	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateVersion(java.lang.String)
	//	 */
	//	protected void updateVersion(String newVersion) {
	//		super.updateVersion(newVersion);
	//		if (isTopLevelURIcompliant(this.getIdentity())) {
	//			// TODO Change all of its children's versions in their URIs.
	//		}
	//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((SEQUENCE == null) ? 0 : SEQUENCE.hashCode());
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

}

package org.sbolstandard.core2;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sbolstandard.core2.ComponentInstance.AccessType;
import org.sbolstandard.core2.SequenceConstraint.RestrictionType;

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

public class ComponentDefinition extends TopLevel {

	private Set<URI> types;
	private Set<URI> roles;
	private URI sequence;
	private HashMap<URI, Component> components;
	private HashMap<URI, SequenceAnnotation> sequenceAnnotations;
	private HashMap<URI, SequenceConstraint> sequenceConstraints;
	
	public static final URI DNA = URI.create("http://www.biopax.org/release/biopax-level3.owl#DnaRegion");
	public static final URI RNA = URI.create("ttp://www.biopax.org/release/biopax-level3.owl#RnaRegion");
	public static final URI PROTEIN = URI.create("http://www.biopax.org/release/biopax-level3.owl#Protein");
	public static final URI SMALL_MOLECULE = URI.create("http://www.biopax.org/release/biopax-level3.owl#SmallMolecule");
	
	ComponentDefinition(URI identity, Set<URI> types) {
		super(identity);
		this.types = new HashSet<>();
		setTypes(types);
		this.roles = new HashSet<>();
		this.components = new HashMap<>();
		this.sequenceAnnotations = new HashMap<>();
		this.sequenceConstraints = new HashMap<>();
	}

	private ComponentDefinition(ComponentDefinition componentDefinition) {
		super(componentDefinition);
		Set<URI> types = new HashSet<>();
		for (URI type : componentDefinition.getTypes()) {
			types.add(URI.create(type.toString()));
		}
		setTypes(types);
		if (!componentDefinition.getRoles().isEmpty()) {
			Set<URI> roles = new HashSet<>();
			for (URI role : componentDefinition.getRoles()) {
				roles.add(URI.create(role.toString()));
			}
			this.setRoles(roles);
		}		
		if (!componentDefinition.getComponents().isEmpty()) {
			List<Component> subComponents = new ArrayList<>();
			for (Component subComponent : componentDefinition.getComponents()) {
				subComponents.add(subComponent.deepCopy());
			}
			this.setComponents(subComponents);
		}		
		if (!componentDefinition.getSequenceConstraints().isEmpty()) {
			List<SequenceConstraint> sequenceConstraints = new ArrayList<>();
			for (SequenceConstraint sequenceConstraint : componentDefinition.getSequenceConstraints()) {
				sequenceConstraints.add(sequenceConstraint.deepCopy());
			}
			this.setSequenceConstraints(sequenceConstraints);
		}
		if (!componentDefinition.getSequenceAnnotations().isEmpty()) {
			List<SequenceAnnotation> sequenceAnnotations = new ArrayList<>();
			for (SequenceAnnotation sequenceAnnotation : componentDefinition.getSequenceAnnotations()) {
				sequenceAnnotations.add(sequenceAnnotation.deepCopy());
			}
			this.setSequenceAnnotations(sequenceAnnotations);
		}
		if (componentDefinition.isSetSequence()) {
			this.setSequence(URI.create(componentDefinition.getSequence().toString()));
		}
	}
	
	
	/**
	 * Adds the specified element to the set <code>type</code> if it is not already present. 
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addType(URI typeURI) {
		return types.add(typeURI);
	}
	
	/**
	 * Removes the specified element from the set <code>type</code> if it is present.
	 * @return <code>true</code> if this set contained the specified element
	 */
	public boolean removeType(URI typeURI) {
		if (types.size()==1 && types.contains(typeURI)) {
			throw new IllegalArgumentException("Component definition " + this.getIdentity() + " must have at least one type.");
		}
		return types.remove(typeURI);
	}
	
	/**
	 * Sets the field variable <code>type</code> to the specified element.
	 */
	public void setTypes(Set<URI> types) {
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
		return roles.add(roleURI);
	}
	
	/**
	 * Removes the specified element from the set <code>roles</code> if it is present.
	 * @return <code>true</code> if this set contained the specified element
	 */
	public boolean removeRole(URI roleURI) {
		return roles.remove(roleURI);
	}
	
	/**
	 * Sets the field variable <code>roles</code> to the specified element.
	 */
	public void setRoles(Set<URI> roles) {
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
		roles.clear();
	}
	
	/**
	 * Test if the referenced {@link Sequence} instance is set.
	 * @return <code>true</code> if the field variable is not <code>null</code>
	 */
	public boolean isSetSequence() {
		return sequence != null;
	}

	/**
	 * Returns the URI of the referenced {@link Sequence} instance.
	 * @return the URI of the referenced {@link Sequence} instance.
	 */
	public URI getSequenceURI() {
		return sequence;
	}
	
	public Sequence getSequence() {
		if (sbolDocument==null) return null;
		return sbolDocument.getSequence(sequence);
	}

	public void setSequence(String sequence,String version) {
		URI sequenceURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(), 
				TopLevel.sequence, sequence, version);
		setSequence(sequenceURI);
	}
	/**
	 * Sets the {@link Sequence} reference to the specified element.
	 */
	public void setSequence(URI sequence) {
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getSequence(sequence)==null) {
				throw new IllegalArgumentException("Sequence '" + sequence + "' does not exist.");
			}
		}
		this.sequence = sequence;
	}
	
	/**
	 * Sets the {@link Sequence} reference to <code>null</code>.
	 */
	public void unsetSequence() {
		sequence = null;
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
	SequenceAnnotation createSequenceAnnotation(URI identity, Location location) {
		SequenceAnnotation sequenceAnnotation = new SequenceAnnotation(identity, location);
		addSequenceAnnotation(sequenceAnnotation);
		return sequenceAnnotation;
	}
	
	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, Location location) {
		String URIprefix = this.getPersistentIdentity().toString();
		String version = this.getVersion();
		URI newSequenceAnnotationURI = createCompliantURI(URIprefix, displayId, version);
		if (!isChildURIcompliant(this.getIdentity(), newSequenceAnnotationURI))
			throw new IllegalArgumentException("Child uri `" + newSequenceAnnotationURI +
					"'is not compliant in parent `" + this.getIdentity() +
			        "' for " + URIprefix + " " + displayId + " " + version);
		SequenceAnnotation sa = createSequenceAnnotation(newSequenceAnnotationURI, location);
		sa.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		sa.setDisplayId(displayId);
		sa.setVersion(version);
		return sa;
	}

	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId) {
		return createSequenceAnnotation(displayId,(OrientationType)null);
	}
	
	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId,OrientationType orientation) {
		String URIprefix = this.getPersistentIdentity().toString()+"/"+displayId;
		String version = this.getVersion();
		GenericLocation location = new GenericLocation(createCompliantURI(URIprefix,"generic",version));
		if (orientation!=null) location.setOrientation(orientation);
		location.setPersistentIdentity(URI.create(URIprefix+"/generic"));
		location.setDisplayId("generic");
		location.setVersion(this.getVersion());
		return createSequenceAnnotation(displayId, location);
	}
	
	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, int at) {
		return createSequenceAnnotation(displayId,at,null);
	}
		
	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, int at,OrientationType orientation) {
		String URIprefix = this.getPersistentIdentity().toString()+"/"+displayId;
		String version = this.getVersion();
		Cut location = new Cut(createCompliantURI(URIprefix,"cut",version),at);
		if (orientation!=null) location.setOrientation(orientation);
		location.setPersistentIdentity(URI.create(URIprefix+"/cut"));
		location.setDisplayId("cut");
		location.setVersion(this.getVersion());
		return createSequenceAnnotation(displayId, location);
	}
	
	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, int start, int end) {
		return createSequenceAnnotation(displayId,start,end,null);
	}
	
	/**
	 */
	public SequenceAnnotation createSequenceAnnotation(String displayId, int start, int end,OrientationType orientation) {
		String URIprefix = this.getPersistentIdentity().toString()+"/"+displayId;
		String version = this.getVersion();
		Range location = new Range(createCompliantURI(URIprefix,"range",version),start,end);
		if (orientation!=null) location.setOrientation(orientation);
		location.setPersistentIdentity(URI.create(URIprefix+"/range"));
		location.setDisplayId("range");
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
		return removeChildSafely(sequenceAnnotation,sequenceAnnotations);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralAnnotations if present.
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
		URI componentDefinitionURI = URIcompliance.createCompliantURI(sbolDocument.getDefaultURIprefix(), 
				TopLevel.componentDefinition, componentDefinition, version);
		return createComponent(displayId,access,componentDefinitionURI);
	}
	
	public Component createComponent(String displayId, AccessType access, URI componentDefinitionURI) {
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
		return removeChildSafely(component,components);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralInstantiations if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component getComponent(URI subComponentURI) {
		return components.get(subComponentURI);
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
		URI subjectURI = URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(), 
				subject, this.getVersion());
		URI objectURI = URIcompliance.createCompliantURI(this.getPersistentIdentity().toString(), 
				object, this.getVersion());
		return createSequenceConstraint(displayId,restriction,subjectURI,objectURI);
	}
		
	public SequenceConstraint createSequenceConstraint(String displayId,
			RestrictionType restriction, URI subject, URI object) {
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
		return removeChildSafely(sequenceConstraint,sequenceConstraints);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of sequence constraints if present.
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
				Location location = sequenceAnnotation.getLocation();
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
				if (location instanceof MultiRange) {
					allDescendantsCompliant = allDescendantsCompliant 
							&& isChildURIcompliant(sequenceAnnotation.getIdentity(), location.getIdentity());
					if (!allDescendantsCompliant) { // Current generic location has non-compliant URI. 
						return allDescendantsCompliant;
					}
					if (!((MultiRange) location).getRanges().isEmpty()) {
						for (Range range : ((MultiRange) location).getRanges()) {
							allDescendantsCompliant = allDescendantsCompliant 
									&& isChildURIcompliant(location.getIdentity(), range.getIdentity());
							if (!allDescendantsCompliant) { // Current location has non-compliant URI. 
								return allDescendantsCompliant;
							}
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
		if (sequence!=null) {
			if (getSequence()==null) return false;
		}
		for (Component component : getComponents()) {
			if (component.getDefinition()==null) return false;
		}
		return true;
	}
	
	/**
	 * Provide a deep copy of this object.
	 */
	protected ComponentDefinition deepCopy() {
		return new ComponentDefinition(this);
	}
	
	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	ComponentDefinition copy(String URIprefix, String displayId, String version) {				
		if (this.checkDescendantsURIcompliance() && isURIprefixCompliant(URIprefix)
				&& isDisplayIdCompliant(displayId) && isVersionCompliant(version)) {
			ComponentDefinition cloned = this.deepCopy();
			cloned.setWasDerivedFrom(this.getIdentity());
			cloned.setPersistentIdentity(URI.create(URIprefix + '/' + displayId));
			cloned.setDisplayId(displayId);
			cloned.setVersion(version);
			URI newIdentity = URI.create(URIprefix + '/' + displayId + '/' + version);			
			cloned.setIdentity(newIdentity);
			// Update all children's URIs
			if (!cloned.getSequenceConstraints().isEmpty()) {
				for (SequenceConstraint sequenceConstraint : cloned.getSequenceConstraints()) {
					sequenceConstraint.updateCompliantURI(URIprefix, displayId, version);
				}
			}
			if (!cloned.getSequenceAnnotations().isEmpty()) {
				for (SequenceAnnotation SequenceAnnotation : cloned.getSequenceAnnotations()) {
					SequenceAnnotation.updateCompliantURI(URIprefix, displayId, version);
				}	
			}
			if (!cloned.getComponents().isEmpty()) {
				for (Component component : cloned.getComponents()) {
					component.updateCompliantURI(URIprefix, displayId, version);
				}
			}
			return cloned;
		}
		else {
			return null; 	
		}
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
		result = prime * result + ((sequence == null) ? 0 : sequence.hashCode());
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
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
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

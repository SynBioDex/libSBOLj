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
import static org.sbolstandard.core2.Version.*;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */

public class ComponentDefinition extends TopLevel {

	private Set<URI> types;
	private Set<URI> roles;
	private URI sequence;
	private HashMap<URI, Component> components;
	private HashMap<URI, SequenceAnnotation> sequenceAnnotations;
	private HashMap<URI, SequenceConstraint> sequenceConstraints;
	
	public ComponentDefinition(URI identity, Set<URI> type, Set<URI> roles) {
		super(identity);
		setTypes(type);
		setRoles(roles);		
		this.components = new HashMap<URI, Component>(); 		
		this.sequenceAnnotations = new HashMap<URI, SequenceAnnotation>();
		this.sequenceConstraints = new HashMap<URI, SequenceConstraint>();
	}

	private ComponentDefinition(ComponentDefinition componentDefinition) {
		super(componentDefinition);
		Set<URI> types = new HashSet<URI>();
		for (URI type : componentDefinition.getTypes()) {
			types.add(URI.create(type.toString()));
		}
		setTypes(types);
		Set<URI> roles = new HashSet<URI>();
		for (URI role : componentDefinition.getRoles()) {
			roles.add(URI.create(role.toString()));
		}
		setRoles(roles);
		if (!componentDefinition.getComponents().isEmpty()) {
			List<Component> subComponents = new ArrayList<Component>();
			for (Component subComponent : componentDefinition.getComponents()) {
				subComponents.add(subComponent.deepCopy());
			}
			this.setComponents(subComponents);
		}		
		if (!componentDefinition.getSequenceConstraints().isEmpty()) {
			List<SequenceConstraint> sequenceConstraints = new ArrayList<SequenceConstraint>();
			for (SequenceConstraint sequenceConstraint : componentDefinition.getSequenceConstraints()) {
				sequenceConstraints.add((SequenceConstraint) sequenceConstraint.deepCopy());
			}
			this.setSequenceConstraints(sequenceConstraints);
		}
		if (!componentDefinition.getSequenceAnnotations().isEmpty()) {
			List<SequenceAnnotation> sequenceAnnotations = new ArrayList<SequenceAnnotation>();
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
	 * @return the set of URIs for <code>type</code>.
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
	 * Adds the specified element to the set <code>roles</code> if it is not already present. 
	 * @param roleURI
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addRole(URI roleURI) {
		return roles.add(roleURI);
	}
	
	/**
	 * Removes the specified element from the set <code>roles</code> if it is present.
	 * @param roleURI
	 * @return <code>true<code> if this set contained the specified element
	 */
	public boolean removeRole(URI roleURI) {
		return roles.remove(roleURI);
	}
	
	/**
	 * Sets the field variable <code>roles</code> to the specified element.
	 * @param roles
	 */
	public void setRoles(Set<URI> roles) {
		this.roles = roles;
	}
	
	/**
	 * Returns the field variable <code>roles</code>.
	 * @return the set of URIs for <code>roles</code>.
	 */
	public Set<URI> getRoles() {
		return roles;
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
		if (sequence == null)
			return false;
		else
			return true;
	}

	/**
	 * Returns the URI of the referenced {@link Sequence} instance.
	 * @return the URI of the referenced {@link Sequence} instance.
	 */
	public URI getSequence() {
		return sequence;
	}

	/**
	 * Sets the {@link Sequence} reference to the specified element.
	 * @param sequence
	 */
	public void setSequence(URI sequence) {
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
	 * @param identity
	 * @param location
	 * @return the created SequenceAnnotation instance. 
	 */
	public SequenceAnnotation createSequenceAnnotation(URI identity, Location location) {
		SequenceAnnotation sequenceAnnotation = new SequenceAnnotation(identity, location);
		if (addSequenceAnnotation(sequenceAnnotation)) {
			return sequenceAnnotation;
		}
		else {
			return null;
		}		
	}
	
	/**
	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @param location
	 * @return
	 */
	public SequenceAnnotation createSequenceAnnotation(String URIprefix, String displayId, String version, Location location) {
		URI newSequenceAnnotationURI = URI.create(URIprefix + '/' + displayId + '/' + version);
		if (isChildURIcompliant(this.getIdentity(), newSequenceAnnotationURI)) {
			return createSequenceAnnotation(newSequenceAnnotationURI, location);
		}
		else {
			// TODO: Generate warning message here.
			return null;
		}
	}
	
//	public SequenceAnnotation createSequenceAnnotation(String URIprefix, String id, 
//				String version, Location location) {		
//		URI newSequenceURI = URI.create(URIprefix + '/' + id + '/' + version);
//		if (Identified.isURIcompliant(newSequenceURI.toString())) {
//			Sequence newSequence = new Sequence(newSequenceURI, elements, encoding);
//			if (addSequence(newSequence)) {
//				return newSequence;
//			}
//			else
//				return null;
//		}
//		else {
//			// TODO: Non-compliant URI
//			return null;
//		}
//	}
	
	/**
	 * Adds the specified instance to the list of sequenceAnnotations. 
	 * @param sequenceAnnotation
	 * @return 
	 */
	public boolean addSequenceAnnotation(SequenceAnnotation sequenceAnnotation) {
		if (isChildURIcompliant(this.getIdentity(), sequenceAnnotation.getIdentity())) {
			// Check if persistent identity exists in other maps.	
			URI persistentId = URI.create(extractPersistentId(sequenceAnnotation.getIdentity()));
			if (!keyExistsInOtherMaps(sequenceAnnotations.keySet(), persistentId)) {
				// Check if URI exists in the sequenceAnnotations map.
				if (!sequenceAnnotations.containsKey(sequenceAnnotation.getIdentity())) {
					sequenceAnnotations.put(sequenceAnnotation.getIdentity(), sequenceAnnotation);
					SequenceAnnotation latestSequenceAnnotation = sequenceAnnotations.get(persistentId);
					if (latestSequenceAnnotation == null) {
						sequenceAnnotations.put(persistentId, sequenceAnnotation);
					}
					else {						
						if (isFirstVersionNewer(extractVersion(sequenceAnnotation.getIdentity()), 
								extractVersion(latestSequenceAnnotation.getIdentity()))) {
							sequenceAnnotations.put(persistentId, sequenceAnnotation);
						}
					}
					return true;
				}
				else // key exists in sequenceAnnotations map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if sequenceAnnotation's URI exists in all maps.
			if (!keyExistsInOtherMaps(sequenceAnnotations.keySet(), sequenceAnnotation.getIdentity())) {
				if (!sequenceAnnotations.containsKey(sequenceAnnotation.getIdentity())) {
					sequenceAnnotations.put(sequenceAnnotation.getIdentity(), sequenceAnnotation);					
					return true;
				}
				else // key exists in sequenceAnnotations map
					return false;
			}
			else // key exists in other maps
				return false;
		}		
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @param sequenceAnnotationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceAnnotation removeSequenceAnnotation(URI sequenceAnnotationURI) {
		//URI key = URI.create(sequenceAnnotationURI.toString().toLowerCase());
		return sequenceAnnotations.remove(sequenceAnnotationURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @param sequenceAnnotationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceAnnotation getSequenceAnnotation(URI sequenceAnnotationURI) {		
		return sequenceAnnotations.get(sequenceAnnotationURI);
	}
	
	/**
	 * Returns the list of structuralAnnotation instances owned by this instance. 
	 * @return the list of structuralAnnotation instances owned by this instance.
	 */
	public List<SequenceAnnotation> getSequenceAnnotations() {
//		return (List<SequenceAnnotation>) structuralAnnotations.values();
		List<SequenceAnnotation> sequenceAnnotations = new ArrayList<SequenceAnnotation>(); 
		sequenceAnnotations.addAll(this.sequenceAnnotations.values());
		return sequenceAnnotations; 
	}
	
	/**
	 * Removes all entries of the list of structuralAnnotation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearSequenceAnnotations() {
		Object[] keySetArray = sequenceAnnotations.keySet().toArray();
		for (Object key : keySetArray) {
			removeSequenceAnnotation((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of structuralAnnotation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param sequenceAnnotations
	 */
	public void setSequenceAnnotations(
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
	 * @param identity
	 * @param access
	 * @param componentDefinitionURI
	 * @return the created StructuralInstantiation instance. 
	 */
	public Component createComponent(URI identity, AccessType access, URI componentDefinitionURI) {
		Component subComponent = new Component(identity, access, componentDefinitionURI);
		if (addComponent(subComponent)) {
			return subComponent;
		}
		else {
			return null;
		}

	}
	
	/**
	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @param access
	 * @param componentDefinitionURI
	 * @return
	 */
	public Component createComponent(String URIprefix, String displayId, String version, 
			AccessType access, URI componentDefinitionURI) {
		URI newComponentURI = URI.create(URIprefix + '/' + displayId + '/' + version);
		if (isChildURIcompliant(this.getIdentity(), newComponentURI)) {
			return createComponent(newComponentURI, access, componentDefinitionURI);
		}
		else {
			// TODO: Generate warning message here.
			return null;
		}
	}
	
	/**
	 * Adds the specified instance to the list of components.
	 * @param subComponent
	 */
	public boolean addComponent(Component subComponent) {
		if (isChildURIcompliant(this.getIdentity(), subComponent.getIdentity())) {
			// Check if persistent identity exists in other maps.
			URI persistentId = URI.create(extractPersistentId(subComponent.getIdentity()));
			if (!keyExistsInOtherMaps(components.keySet(), persistentId)) {
				// Check if URI exists in the subComponents map.
				if (!components.containsKey(subComponent.getIdentity())) {
					components.put(subComponent.getIdentity(), subComponent);
					Component latestSubComponent = components.get(persistentId);
					if (latestSubComponent == null) {
						components.put(persistentId, subComponent);
					}
					else {						
						if (isFirstVersionNewer(extractVersion(subComponent.getIdentity()), 
								extractVersion(latestSubComponent.getIdentity()))) {								
							components.put(persistentId, subComponent);
						}
					}
					return true;
				}
				else // key exists in subComponents map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if subComponent's URI exists in all maps.
			if (!keyExistsInOtherMaps(components.keySet(), subComponent.getIdentity())) {
				if (!components.containsKey(subComponent.getIdentity())) {
					components.put(subComponent.getIdentity(), subComponent);					
					return true;
				}
				else // key exists in subComponents map
					return false;
			}
			else // key exists in other maps
				return false;
		}
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralInstantiations if present.
	 * @param subComponentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component removeComponent(URI subComponentURI) {
		//URI key = URI.create(subComponentURI.toString().toLowerCase());
		return components.remove(subComponentURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralInstantiations if present.
	 * @param subComponentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component getComponent(URI subComponentURI) {
		return components.get(subComponentURI);
	}
	
	/**
	 * Returns the list of {@link Component} instances owned by this instance. 
	 * @return the list of {@link Component} instances owned by this instance.
	 */
	public List<Component> getComponents() {
		List<Component> structuralInstantiations = new ArrayList<Component>(); 
		structuralInstantiations.addAll(this.components.values());
		return structuralInstantiations; 
	}
	
	/**
	 * Removes all entries of the list of structuralInstantiation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearComponents() {
		Object[] keySetArray = components.keySet().toArray();
		for (Object key : keySetArray) {
			removeComponent((URI) key);			
		}
	}
		
	/**
	 * Clears the existing list of structuralInstantiation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param components
	 */
	public void setComponents(
			List<Component> components) {
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
	 * @param identity
	 * @param restriction
	 * @param subject
	 * @param object
	 * @return the created StructuralConstraint instance. 
	 */
	public SequenceConstraint createSequenceConstraint(URI identity, RestrictionType restriction, URI subject, URI object) {
		SequenceConstraint sequenceConstraint = new SequenceConstraint(identity, restriction, subject, object);
		if (addSequenceConstraint(sequenceConstraint)) {
			return sequenceConstraint;
		}
		else {
			return null;
		}
	}
		
	/**
	 * @param URIprefix
	 * @param displayId
	 * @param version
	 * @param restriction
	 * @param subject
	 * @param object
	 * @return
	 */
	public SequenceConstraint createSequenceConstraint(String URIprefix, String displayId, String version, 
			RestrictionType restriction, URI subject, URI object) {
		URI newSequenceConstraintURI = URI.create(URIprefix + '/' + displayId + '/' + version);
		if (isChildURIcompliant(this.getIdentity(), newSequenceConstraintURI)) {
			return createSequenceConstraint(newSequenceConstraintURI, restriction, subject, object);
		}
		else {
			// TODO: Generate warning message here.
			return null;
		}
	}
	
	/**
	 * Adds the specified instance to the list of sequenceConstraints. 
	 * @param sequenceConstraint
	 */
	public boolean addSequenceConstraint(SequenceConstraint sequenceConstraint) {
		if (isChildURIcompliant(this.getIdentity(), sequenceConstraint.getIdentity())) {
			// Check if persistent identity exists in other maps.
			URI persistentId = URI.create(extractPersistentId(sequenceConstraint.getIdentity()));
			if (!keyExistsInOtherMaps(sequenceConstraints.keySet(), persistentId)) {
				// Check if URI exists in the sequenceConstraints map.
				if (!sequenceConstraints.containsKey(sequenceConstraint.getIdentity())) {
					sequenceConstraints.put(sequenceConstraint.getIdentity(), sequenceConstraint);
					SequenceConstraint latestSequenceConstraint = sequenceConstraints.get(persistentId);
					if (latestSequenceConstraint == null) {
						sequenceConstraints.put(persistentId, sequenceConstraint);
					}
					else {						
						if (isFirstVersionNewer(extractVersion(sequenceConstraint.getIdentity()), 
								extractVersion(latestSequenceConstraint.getIdentity()))) {
							sequenceConstraints.put(persistentId, sequenceConstraint);
						}
					}
					return true;
				}
				else // key exists in sequenceConstraints map
					return false;
			}
			else // key exists in other maps
				return false;
		}
		else { // Only check if sequenceConstraint's URI exists in all maps.
			if (!keyExistsInOtherMaps(sequenceConstraints.keySet(), sequenceConstraint.getIdentity())) {
				if (!sequenceConstraints.containsKey(sequenceConstraint.getIdentity())) {
					sequenceConstraints.put(sequenceConstraint.getIdentity(), sequenceConstraint);					
					return true;
				}
				else // key exists in sequenceConstraints map
					return false;
			}
			else // key exists in other maps
				return false;
		}
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param sequenceConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceConstraint removeSequenceConstraint(URI sequenceConstraintURI) {
		//URI key = URI.create(sequenceConstraintURI.toString().toLowerCase());
		return sequenceConstraints.remove(sequenceConstraintURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of sequence constraints if present.
	 * @param sequenceConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceConstraint getSequenceConstraint(URI sequenceConstraintURI) {
		return sequenceConstraints.get(sequenceConstraintURI);
	}
	
	/**
	 * Returns the list of {@link SequenceConstraint} instances owned by this instance. 
	 * @return the list of {@link SequenceConstraint} instances owned by this instance.
	 */
	public List<SequenceConstraint> getSequenceConstraints() {
//		return (List<StructuralConstraint>) structuralConstraints.values();
		List<SequenceConstraint> sequenceConstraints = new ArrayList<SequenceConstraint>(); 
		sequenceConstraints.addAll(this.sequenceConstraints.values());
		return sequenceConstraints; 
	}
	
	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearSequenceConstraints() {
		Object[] keySetArray = sequenceConstraints.keySet().toArray();
		for (Object key : keySetArray) {
			removeSequenceConstraint((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of structuralConstraint instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param sequenceConstraints
	 */
	public void setSequenceConstraints(
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
							&& isChildURIcompliant(sequenceAnnotation.getIdentity(), ((Range)location).getIdentity());
					if (!allDescendantsCompliant) { // Current range has non-compliant URI. 
						return allDescendantsCompliant;
					}
				}
				if (location instanceof Cut) {
					allDescendantsCompliant = allDescendantsCompliant 
							&& isChildURIcompliant(sequenceAnnotation.getIdentity(), ((Cut)location).getIdentity());
					if (!allDescendantsCompliant) { // Current cut has non-compliant URI. 
						return allDescendantsCompliant;
					}
				}
				if (location instanceof GenericLocation) {
					allDescendantsCompliant = allDescendantsCompliant 
							&& isChildURIcompliant(sequenceAnnotation.getIdentity(), ((GenericLocation)location).getIdentity());
					if (!allDescendantsCompliant) { // Current generic location has non-compliant URI. 
						return allDescendantsCompliant;
					}
				}
				if (location instanceof MultiRange) {
					allDescendantsCompliant = allDescendantsCompliant 
							&& isChildURIcompliant(sequenceAnnotation.getIdentity(), ((MultiRange)location).getIdentity());
					if (!allDescendantsCompliant) { // Current generic location has non-compliant URI. 
						return allDescendantsCompliant;
					}
					if (!((MultiRange) location).getRanges().isEmpty()) {
						for (Range range : ((MultiRange) location).getRanges()) {
							allDescendantsCompliant = allDescendantsCompliant 
									&& isChildURIcompliant(((MultiRange) location).getIdentity(), range.getIdentity());
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
	
	/**
	 * Provide a deep copy of this object.
	 */
	protected ComponentDefinition deepCopy() {
		return new ComponentDefinition(this);
	}
	
	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ComponentDefinition copy(String URIprefix, String displayId, String version) {				
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
	
	/**
	 * Check if the specified key exists in any hash maps in this class other than the one with the specified keySet. This method
	 * constructs a set of key sets for other hash maps first, and then checks if the key exists.
	 * @param keySet
	 * @param key
	 * @return <code>true</code> if the specified key exists in other hash maps.
	 */
	private boolean keyExistsInOtherMaps(Set<URI> keySet, URI key) {
		Set<Set<URI>> complementSet = new HashSet<Set<URI>>();
		complementSet.add(sequenceAnnotations.keySet());
		complementSet.add(sequenceConstraints.keySet());
		complementSet.add(components.keySet());
		complementSet.remove(keySet);
		for (Set<URI> otherKeySet : complementSet) {
			if (otherKeySet.contains(key)) {
				return true;
			}
		}
		return false;
	}
}

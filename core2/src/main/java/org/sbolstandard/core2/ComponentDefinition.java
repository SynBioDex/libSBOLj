package org.sbolstandard.core2;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.sbolstandard.core2.abstract_classes.Location;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */

public class ComponentDefinition extends TopLevel {

	private Set<URI> type;
	private Set<URI> roles;
	private URI sequence;
	private HashMap<URI, Component> subComponents;
	private HashMap<URI, SequenceAnnotation> sequenceAnnotations;
	private HashMap<URI, SequenceConstraint> sequenceConstraints;
	
	public ComponentDefinition(URI identity, Set<URI> type, Set<URI> roles) {
		super(identity);
		setType(type);
		setRoles(roles);		
		this.subComponents = new HashMap<URI, Component>(); 		
		this.sequenceAnnotations = new HashMap<URI, SequenceAnnotation>();
		this.sequenceConstraints = new HashMap<URI, SequenceConstraint>();
	}

	/**
	 * Adds the specified element to the set <code>type</code> if it is not already present. 
	 * @param typeURI
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addType(URI typeURI) {
		return type.add(typeURI);
	}
	
	/**
	 * Removes the specified element from the set <code>type</code> if it is present.
	 * @param typeURI
	 * @return <code>true<code> if this set contained the specified element
	 */
	public boolean removeType(URI typeURI) {
		return type.remove(typeURI);
	}
	
	/**
	 * Sets the field variable <code>type</code> to the specified element.
	 * @param type
	 */
	public void setType(Set<URI> type) {
		this.type = type;
	}
	
	/**
	 * Returns the field variable <code>type</code>.
	 * @return
	 */
	public Set<URI> getType() {
		return type;
	}
	
	/**
	 * Returns true if the set <code>type</code> contains the specified element. 
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsType(URI typeURI) {
		return type.contains(typeURI);
	}
	
	/**
	 * Removes all entries of the list of <code>type</code> instances owned by this instance. 
	 * The list will be empty after this call returns.
	 */
	public void clearType() {
		type.clear();
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
	 * @return
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
	 * @param structure
	 */
	public void setSequence(URI structure) {
		this.sequence = structure;
	}
	
	/**
	 * Sets the {@link Sequence} reference to <code>null</code>.
	 */
	public void unsetSequence() {
		sequence = null;
	}
	
	/**
	 * Test if any {@link SequenceAnnotation} instance exists.
	 * @return <code>true</code> if at least one such instance exists.
	 */
	public boolean isSetSequenceAnnotations() {
		if (sequenceAnnotations.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Calls the SequenceAnnotation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of SequenceAnnotation instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the created SequenceAnnotation instance. 
	 */
	public SequenceAnnotation createSequenceAnnotation(URI identity, Location location) {
		SequenceAnnotation sequenceAnnotation = new SequenceAnnotation(identity, location);
		addSequenceAnnotation(sequenceAnnotation);
		return sequenceAnnotation;
	}
	
	/**
	 * Adds the specified instance to the list of structuralAnnotations. 
	 * @param sequenceAnnotation
	 */
	public void addSequenceAnnotation(SequenceAnnotation sequenceAnnotation) {
		// TODO: @addSequenceAnnotation, Check for duplicated entries.
		sequenceAnnotations.put(sequenceAnnotation.getIdentity(), sequenceAnnotation);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @param sequenceAnnotationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceAnnotation removeSequenceAnnotation(URI sequenceAnnotationURI) {
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
	
	/**
	 * Test if the optional field variable <code>structuralInstantiations</code> is set.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetSubComponents() {
		if (subComponents.isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * Calls the StructuralInstantiation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of StructuralInstantiation instances owned by this instance.
	 * @param identity
	 * @param access
	 * @param subComponentURI
	 * @return the created StructuralInstantiation instance. 
	 */
	public Component createSubComponent(URI identity, URI access, URI subComponentURI) {
		Component subComponent = new Component(identity, access, subComponentURI);
		addSubComponent(subComponent);
		return subComponent;
	}
	
	/**
	 * Adds the specified instance to the list of structuralInstantiations. 
	 * @param subComponent
	 */
	public void addSubComponent(Component subComponent) {
		// TODO: @addSubComponent, Check for duplicated entries.
		subComponents.put(subComponent.getIdentity(), subComponent);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralInstantiations if present.
	 * @param subComponentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component removeSubComponent(URI subComponentURI) {
		return subComponents.remove(subComponentURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralInstantiations if present.
	 * @param subComponentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component getSubComponent(URI subComponentURI) {
		return subComponents.get(subComponentURI);
	}
	
	/**
	 * Returns the list of {@link Component} instances owned by this instance. 
	 * @return the list of {@link Component} instances owned by this instance.
	 */
	public List<Component> getSubComponents() {
		List<Component> structuralInstantiations = new ArrayList<Component>(); 
		structuralInstantiations.addAll(this.subComponents.values());
		return structuralInstantiations; 
	}
	
	/**
	 * Removes all entries of the list of structuralInstantiation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearSubComponents() {
		Object[] keySetArray = subComponents.keySet().toArray();
		for (Object key : keySetArray) {
			removeSubComponent((URI) key);			
		}
	}
		
	/**
	 * Clears the existing list of structuralInstantiation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param components
	 */
	public void setSubComponents(
			List<Component> components) {
		clearSubComponents();
		for (Component component : components) {
			addSubComponent(component);
		}
	}
	
	/**
	 * Test if the optional field variable <code>sequenceConstraints</code> is set.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetSequenceConstraints() {
		if (sequenceConstraints.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * Calls the StructuralConstraint constructor to create a new instance using the specified parameters, 
	 * then adds to the list of StructuralConstraint instances owned by this instance.
	 * @param identity
	 * @param restriction
	 * @param subject
	 * @param object
	 * @return the created StructuralConstraint instance. 
	 */
	public SequenceConstraint createSequenceConstraint(URI identity, URI restriction, URI subject, URI object) {
		SequenceConstraint sequenceConstraint = new SequenceConstraint(identity, restriction, subject, object);
		addSequenceConstraint(sequenceConstraint);
		return sequenceConstraint;
	}
	
	/**
	 * Adds the specified instance to the list of structuralConstraints. 
	 * @param sequenceConstraint
	 */
	public void addSequenceConstraint(SequenceConstraint sequenceConstraint) {
		// TODO: @addStructuralConstraint, Check for duplicated entries.
		sequenceConstraints.put(sequenceConstraint.getIdentity(), sequenceConstraint);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param sequenceConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceConstraint removeSequenceConstraint(URI sequenceConstraintURI) {
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
}

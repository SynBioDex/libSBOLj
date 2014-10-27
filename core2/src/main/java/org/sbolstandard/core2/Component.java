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

public class Component extends TopLevel {

	private Set<URI> type;
	private Set<URI> roles;
	private URI structure;
	private HashMap<URI, StructuralInstantiation> structuralInstantiations;
	private HashMap<URI, StructuralAnnotation> structuralAnnotations;
	private HashMap<URI, StructuralConstraint> structuralConstraints;
	
	public Component(URI identity, Set<URI> type, Set<URI> roles) {
		super(identity);
		setType(type);
		setRoles(roles);		
		this.structuralInstantiations = new HashMap<URI, StructuralInstantiation>(); 		
		this.structuralAnnotations = new HashMap<URI, StructuralAnnotation>();
		this.structuralConstraints = new HashMap<URI, StructuralConstraint>();
	}
	
//	/**
//	 * Set the optional field variable <code>structuralInstantiations</code> to an empty list.
//	 */
//	public void unsetStructuralInstantiations() {
//		clearStructuralInstantiations();
//	}
//	
//	/**
//	 * Set the optional field variable <code>structuralAnnotations</code> to an empty list.
//	 */
//	public void unsetStructuralAnnotations() {
//		clearStructuralAnnotations();
//	}
//	
//	/**
//	 * Set the optional field variable <code>structuralConstraints</code> to an empty list.
//	 */
//	public void unsetStructuralConstraints() {
//		clearStructuralConstraints();
//	}

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
	 * Test if the optional field variable <code>structure</code> is set.
	 * @return <code>true</code> if the field variable is not <code>null</code>
	 */
	public boolean isSetStructure() {
		if (structure == null)
			return false;
		else
			return true;
	}

	/**
	 * Returns the field variable <code>structure</code>.
	 * @return the field variable <code>structure</code>.
	 */
	public URI getStructure() {
		return structure;
	}

	/**
	 * Sets the field variable <code>structure</code> to the specified element.
	 * @param structure
	 */
	public void setStructure(URI structure) {
		this.structure = structure;
	}
	
	/**
	 * Sets the optional field variable <code>structure</code> to <code>null</code>.
	 */
	public void unsetStructure() {
		structure = null;
	}
	
	/**
	 * Test if the optional field variable <code>structuralAnnotations</code> is set.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetStructuralAnnotations() {
		if (structuralAnnotations.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Calls the StructuralAnnotation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of StructuralAnnotation instances owned by this instance.
	 * @param identity
	 * @param location
	 * @return the  created StructuralAnnotation instance. 
	 */
	public StructuralAnnotation createStructuralAnnotation(URI identity, Location location) {
		StructuralAnnotation structuralAnnotation = new StructuralAnnotation(identity, location);
		addStructuralAnnotation(structuralAnnotation);
		return structuralAnnotation;
	}
	
	/**
	 * Adds the specified instance to the list of structuralAnnotations. 
	 * @param structuralAnnotation
	 */
	public void addStructuralAnnotation(StructuralAnnotation structuralAnnotation) {
		// TODO: @addStructuralAnnotation, Check for duplicated entries.
		structuralAnnotations.put(structuralAnnotation.getIdentity(), structuralAnnotation);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @param structuralAnnotationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public StructuralAnnotation removeStructuralAnnotation(URI structuralAnnotationURI) {
		return structuralAnnotations.remove(structuralAnnotationURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @param structuralAnnotationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public StructuralAnnotation getStructuralAnnotation(URI structuralAnnotationURI) {
		return structuralAnnotations.get(structuralAnnotationURI);
	}
	
	/**
	 * Returns the list of structuralAnnotation instances owned by this instance. 
	 * @return the list of structuralAnnotation instances owned by this instance.
	 */
	public List<StructuralAnnotation> getStructuralAnnotations() {
//		return (List<StructuralAnnotation>) structuralAnnotations.values();
		List<StructuralAnnotation> structuralAnnotations = new ArrayList<StructuralAnnotation>(); 
		structuralAnnotations.addAll(this.structuralAnnotations.values());
		return structuralAnnotations; 
	}
	
	/**
	 * Removes all entries of the list of structuralAnnotation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearStructuralAnnotations() {
		Object[] keySetArray = structuralAnnotations.keySet().toArray();
		for (Object key : keySetArray) {
			removeStructuralAnnotation((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of structuralAnnotation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param structuralAnnotations
	 */
	public void setStructuralAnnotations(
			List<StructuralAnnotation> structuralAnnotations) {
		clearStructuralAnnotations();		
		for (StructuralAnnotation structuralAnnotation : structuralAnnotations) {
			addStructuralAnnotation(structuralAnnotation);
		}
	}
	
	/**
	 * Test if the optional field variable <code>structuralInstantiations</code> is set.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetStructuralInstantiations() {
		if (structuralInstantiations.isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * Calls the StructuralInstantiation constructor to create a new instance using the specified parameters, 
	 * then adds to the list of StructuralInstantiation instances owned by this instance.
	 * @param identity
	 * @param access
	 * @param instantiatedComponent
	 * @return the created StructuralInstantiation instance. 
	 */
	public StructuralInstantiation createStructuralInstantiation(URI identity, AccessType access, URI instantiatedComponent) {
		StructuralInstantiation structuralInstantiation = new StructuralInstantiation(identity, access, instantiatedComponent);
		addStructuralInstantiation(structuralInstantiation);
		return structuralInstantiation;
	}
	
	/**
	 * Adds the specified instance to the list of structuralInstantiations. 
	 * @param structuralInstantiation
	 */
	public void addStructuralInstantiation(StructuralInstantiation structuralInstantiation) {
		// TODO: @addStructuralInstantiation, Check for duplicated entries.
		structuralInstantiations.put(structuralInstantiation.getIdentity(), structuralInstantiation);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralInstantiations if present.
	 * @param structuralInstantiationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public StructuralInstantiation removeStructuralInstantiation(URI structuralInstantiationURI) {
		return structuralInstantiations.remove(structuralInstantiationURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralInstantiations if present.
	 * @param structuralInstantiationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public StructuralInstantiation getStructuralInstantiation(URI structuralInstantiationURI) {
		return structuralInstantiations.get(structuralInstantiationURI);
	}
	
	/**
	 * Returns the list of structuralInstantiation instances owned by this instance. 
	 * @return the list of structuralInstantiation instances owned by this instance.
	 */
	public List<StructuralInstantiation> getStructuralInstantiations() {
//		return (List<StructuralInstantiation>) structuralInstantiations.values();
		List<StructuralInstantiation> structuralInstantiations = new ArrayList<StructuralInstantiation>(); 
		structuralInstantiations.addAll(this.structuralInstantiations.values());
		return structuralInstantiations; 
	}
	
	/**
	 * Removes all entries of the list of structuralInstantiation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearStructuralInstantiations() {
		Object[] keySetArray = structuralInstantiations.keySet().toArray();
		for (Object key : keySetArray) {
			removeStructuralInstantiation((URI) key);			
		}
	}
		
	/**
	 * Clears the existing list of structuralInstantiation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param structuralInstantiations
	 */
	public void setStructuralInstantiations(
			List<StructuralInstantiation> structuralInstantiations) {
		clearStructuralInstantiations();
		for (StructuralInstantiation structuralInstantiation : structuralInstantiations) {
			addStructuralInstantiation(structuralInstantiation);
		}
	}
	
	/**
	 * Test if the optional field variable <code>structuralConstraints</code> is set.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetStructuralConstraints() {
		if (structuralConstraints.isEmpty())
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
	public StructuralConstraint createStructuralConstraint(URI identity, URI restriction, URI subject, URI object) {
		StructuralConstraint structuralConstraint = new StructuralConstraint(identity, restriction, subject, object);
		addStructuralConstraint(structuralConstraint);
		return structuralConstraint;
	}
	
	/**
	 * Adds the specified instance to the list of structuralConstraints. 
	 * @param structuralConstraint
	 */
	public void addStructuralConstraint(StructuralConstraint structuralConstraint) {
		// TODO: @addStructuralConstraint, Check for duplicated entries.
		structuralConstraints.put(structuralConstraint.getIdentity(), structuralConstraint);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public StructuralConstraint removeStructuralConstraint(URI structuralConstraintURI) {
		return structuralConstraints.remove(structuralConstraintURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param structuralConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public StructuralConstraint getStructuralConstraint(URI structuralConstraintURI) {
		return structuralConstraints.get(structuralConstraintURI);
	}
	
	/**
	 * Returns the list of structuralConstraint instances owned by this instance. 
	 * @return the list of structuralConstraint instances owned by this instance.
	 */
	public List<StructuralConstraint> getStructuralConstraints() {
//		return (List<StructuralConstraint>) structuralConstraints.values();
		List<StructuralConstraint> structuralConstraints = new ArrayList<StructuralConstraint>(); 
		structuralConstraints.addAll(this.structuralConstraints.values());
		return structuralConstraints; 
	}
	
	/**
	 * Removes all entries of the list of structuralConstraint instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearStructuralConstraints() {
		Object[] keySetArray = structuralConstraints.keySet().toArray();
		for (Object key : keySetArray) {
			removeStructuralConstraint((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of structuralConstraint instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param structuralConstraints
	 */
	public void setStructuralConstraints(
			List<StructuralConstraint> structuralConstraints) {
		clearStructuralConstraints();
		for (StructuralConstraint structuralConstraint : structuralConstraints) {
			addStructuralConstraint(structuralConstraint);
		}
	}
	
	
	
	
	

}

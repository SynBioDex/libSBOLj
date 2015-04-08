package org.sbolstandard.core2;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sbolstandard.core2.SequenceConstraint.RestrictionType;
import org.sbolstandard.core2.abstract_classes.ComponentInstance.AccessType;
import org.sbolstandard.core2.abstract_classes.Location;
import org.sbolstandard.core2.abstract_classes.TopLevel;

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
	private HashMap<URI, Component> subComponents;
	private HashMap<URI, SequenceAnnotation> sequenceAnnotations;
	private HashMap<URI, SequenceConstraint> sequenceConstraints;
	
	public ComponentDefinition(URI identity, Set<URI> type, Set<URI> roles) {
		super(identity);
		setTypes(type);
		setRoles(roles);		
		this.subComponents = new HashMap<URI, Component>(); 		
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
		if (!componentDefinition.getSubComponents().isEmpty()) {
			List<Component> subComponents = new ArrayList<Component>();
			for (Component subComponent : componentDefinition.getSubComponents()) {
				subComponents.add(subComponent.deepCopy());
			}
			this.setSubComponents(subComponents);
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
	 * Adds the specified instance to the list of structuralAnnotations. 
	 * @param sequenceAnnotation
	 * @return 
	 */
	public boolean addSequenceAnnotation(SequenceAnnotation sequenceAnnotation) {

		sequenceAnnotations.put(sequenceAnnotation.getIdentity(), sequenceAnnotation);
		// TODO: @addSequenceAnnotation, Check for duplicated entries. Hack here: returns true.
		return true;
//		if (sequenceAnnotation.isSetPersistentIdentity() && sequenceAnnotation.isSetVersion()) {
//			// Compliant URI should come in here.
//			// Check if persistent identity exists in other maps.
//			if (!keyExistsInOtherMaps(sequenceAnnotations.keySet(), sequenceAnnotation.getPersistentIdentity())) {
//				// Check if URI exists in the sequenceAnnotations map.
//				if (!sequenceAnnotations.containsKey(sequenceAnnotation.getIdentity())) {
//					sequenceAnnotations.put(sequenceAnnotation.getIdentity(), sequenceAnnotation);
//					SequenceAnnotation latestSequenceAnnotation = sequenceAnnotations.get(sequenceAnnotation.getPersistentIdentity());
//					if (latestSequenceAnnotation == null) {
//						sequenceAnnotations.put(sequenceAnnotation.getPersistentIdentity(), sequenceAnnotation);
//					}
//					else {
//						if (latestSequenceAnnotation.getMajorVersion() < sequenceAnnotation.getMajorVersion()) {
//							sequenceAnnotations.put(sequenceAnnotation.getPersistentIdentity(), sequenceAnnotation);
//						}
//						else if (latestSequenceAnnotation.getMajorVersion() == sequenceAnnotation.getMajorVersion()){
//							if (latestSequenceAnnotation.getMinorVersion() < sequenceAnnotation.getMinorVersion()) {
//								sequenceAnnotations.put(sequenceAnnotation.getPersistentIdentity(), sequenceAnnotation);
//							}
//						}
//					}
//					return true;
//				}
//				else // key exists in sequenceAnnotations map
//					return false;
//			}
//			else // key exists in other maps
//				return false;
//		}
//		else { // Only check if sequenceAnnotation's URI exists in all maps.
//			if (!keyExistsInOtherMaps(sequenceAnnotations.keySet(), sequenceAnnotation.getIdentity())) {
//				if (!sequenceAnnotations.containsKey(sequenceAnnotation.getIdentity())) {
//					sequenceAnnotations.put(sequenceAnnotation.getIdentity(), sequenceAnnotation);					
//					return true;
//				}
//				else // key exists in sequenceAnnotations map
//					return false;
//			}
//			else // key exists in other maps
//				return false;
//		}
//		
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @param sequenceAnnotationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceAnnotation removeSequenceAnnotation(URI sequenceAnnotationURI) {
		URI key = URI.create(sequenceAnnotationURI.toString().toLowerCase());
		return sequenceAnnotations.remove(key);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralAnnotations if present.
	 * @param sequenceAnnotationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceAnnotation getSequenceAnnotation(URI sequenceAnnotationURI) {
		URI key = URI.create(sequenceAnnotationURI.toString().toLowerCase());
		return sequenceAnnotations.get(key);
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
	 * @param subComponentURI
	 * @return the created StructuralInstantiation instance. 
	 */
	public Component createSubComponent(URI identity, AccessType access, URI subComponentURI) {
		Component subComponent = new Component(identity, access, subComponentURI);
		addSubComponent(subComponent);
		return subComponent;
	}
	
	/**
	 * Adds the specified instance to the list of structuralInstantiations. 
	 * @param subComponent
	 */
	public void addSubComponent(Component subComponent) {
		//URI key = URI.create(subComponent.getIdentity().toString().toLowerCase());
		// TODO: @addSubComponent, Check for duplicated entries.
		subComponents.put(subComponent.getIdentity(), subComponent);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralInstantiations if present.
	 * @param subComponentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component removeSubComponent(URI subComponentURI) {
		URI key = URI.create(subComponentURI.toString().toLowerCase());
		return subComponents.remove(key);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of structuralInstantiations if present.
	 * @param subComponentURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public Component getSubComponent(URI subComponentURI) {
		URI key = URI.create(subComponentURI.toString().toLowerCase());
		return subComponents.get(key);
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
		addSequenceConstraint(sequenceConstraint);
		return sequenceConstraint;
	}
	
	/**
	 * Adds the specified instance to the list of structuralConstraints. 
	 * @param sequenceConstraint
	 */
	public void addSequenceConstraint(SequenceConstraint sequenceConstraint) {
		// TODO: @addStructuralConstraint, Check for duplicated entries.
		URI key = URI.create(sequenceConstraint.getIdentity().toString().toLowerCase());
		sequenceConstraints.put(key, sequenceConstraint);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of structuralConstraints if present.
	 * @param sequenceConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceConstraint removeSequenceConstraint(URI sequenceConstraintURI) {
		URI key = URI.create(sequenceConstraintURI.toString().toLowerCase());
		return sequenceConstraints.remove(key);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of sequence constraints if present.
	 * @param sequenceConstraintURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public SequenceConstraint getSequenceConstraint(URI sequenceConstraintURI) {
		URI key = URI.create(sequenceConstraintURI.toString().toLowerCase());
		return sequenceConstraints.get(key);
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
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateDisplayId(java.lang.String)
	 */
	protected void updateDisplayId(String newDisplayId) {
		super.updateDisplayId(newDisplayId);
		if (isURIcompliant(this.getIdentity())) {			
			// TODO Change all of its children's displayIds in their URIs.
		}
	}


	/**
	 * Replace the display ID in the URI of the object's parent with the specified one.  
	 * @param id
	 */
	private void setParentDisplayId(String id) {
		// TODO fill in
	}
	
	/**
	 * Replace the display ID in the URI of the object's grand parent (2 levels up) with the specified one.
	 * @param id
	 */
	private void setGrandParentDisplayId(String id) {
		// TODO fill in
	}
	
//	/**
//	 * Replace the authority in the object's URI with the specified one, and make the same replacement for all of its children objects.
//	 * @param authority
//	 */
//	public void setAuthority(String authority) {
//		// TODO Need to change the parent's authority?
//	}
	
	/**
	 * Provide a deep copy of this object.
	 */
	protected ComponentDefinition deepCopy() {
		return new ComponentDefinition(this);
	}
	
	/**
	 * Clone the object first, set its display ID to the specified value, and set the major version to "1" and minor version to "0".
	 * @param newDisplayId
	 * @return the copied {@link ComponentDefinition} instance.
	 */
	public ComponentDefinition copy(String newDisplayId) {
		ComponentDefinition cloned = (ComponentDefinition) super.copy(newDisplayId);
		cloned.updateDisplayId(newDisplayId);
		return cloned;
	}
	
	/**
	 * Get a deep copy of the object first, and set its major version to the specified value, and minor version to "0". 
	 * @param newVersion
	 * @return the copied {@link ComponentDefinition} instance with the specified major version.
	 */
	public ComponentDefinition newVersion(String newVersion) {
		ComponentDefinition cloned = (ComponentDefinition) super.newVersion(newVersion);		
		cloned.updateVersion(newVersion);
		return cloned;
	}
	
	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateVersion(java.lang.String)
	 */
	protected void updateVersion(String newVersion) {
		super.updateVersion(newVersion);
		if (isURIcompliant(this.getIdentity())) {			
			// TODO Change all of its children's versions in their URIs.
		}
	}

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
		result = prime * result + ((subComponents == null) ? 0 : subComponents.hashCode());
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
		if (subComponents == null) {
			if (other.subComponents != null)
				return false;
		} else if (!subComponents.equals(other.subComponents))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		return true;
	}
}

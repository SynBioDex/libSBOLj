package org.sbolstandard.core2.abstract_classes;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.MapsTo;
import org.sbolstandard.core2.RefinementType;

public abstract class ComponentInstantiation extends Documented {
	
	private AccessType access;
	private URI instantiatedComponent;
	private HashMap<URI, MapsTo> references;
	
//	public ComponentInstantiation(URI identity, URI componentIdentity, AccessType access, List<URI> type, List<URI> roles) {
//		super(identity);
//		setAccess(access);
//		//setInstantiatedComponent(instantiatedComponent);
//		this.instantiatedComponent = new Component(componentIdentity, type, roles);
//		this.references = new ArrayList<MapsTo>();
//	}
	
	public ComponentInstantiation(URI identity, AccessType access, URI instantiatedComponent) {
		super(identity);
		setAccess(access);
		setInstantiatedComponent(instantiatedComponent);		
		this.references = new HashMap<URI, MapsTo>();
	}
	
	
	/**
	 * Returns field variable <code>access</code>.
	 * @return field variable <code>access</code>
	 */
	public AccessType getAccess() {
		return access;
	}

	/**
	 * Sets field variable <code>access</code> to the specified element.
	 * @param access
	 */
	public void setAccess(AccessType access) {
		this.access = access;
	}
	
	/**
	 * Test if optional field variable <code>references</code> is set.
	 * @return <code>true</code> if it is not an empty list
	 */
	public boolean isSetReferences() {
		if (references.isEmpty())
			return false;
		else
			return true;
	}	
	
	/**
	 * Calls the MapsTo constructor to create a new instance using the specified parameters, 
	 * then adds to the list of MapsTo instances owned by this component.
	 * @param identity
	 * @param refinement
	 * @param local
	 * @param remote
	 * @return the created MapsTo instance. 
	 */
	public MapsTo createReference(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		MapsTo reference = new MapsTo(identity, refinement, local, remote);
		addReference(reference);
		return reference;
	}
	
	/**
	 * Adds the specified instance to the list of references. 
	 * @param reference
	 */
	public void addReference(MapsTo reference) {
		// TODO: @addReference, Check for duplicated entries.
		references.put(reference.getIdentity(), reference);
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of references if present.
	 * @param referenceURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo removeReference(URI referenceURI) {
		return references.remove(referenceURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of references if present.
	 * @param referenceURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo getReference(URI referenceURI) {
		return references.get(referenceURI);
	}
	
	/**
	 * Returns the list of reference instances owned by this instance. 
	 * @return the list of reference instances owned by this instance.
	 */
	public List<MapsTo> getReferences() {
		return (List<MapsTo>) references.values();
	}
	
	/**
	 * Removes all entries of the list of reference instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearReferences() {
		Object[] keySetArray = references.keySet().toArray();
		for (Object key : keySetArray) {
			removeReference((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of reference instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param references
	 */
	public void setReferences(
			List<MapsTo> references) {
		clearReferences();		
		for (MapsTo reference : references) {
			addReference(reference);
		}
	}
	
//	/**
//	 * Set optional field variable <code>references</code> to an empty list.
//	 */
//	public void unsetReferences() {
//		references.clear();
//	}
	
	/**
	 * Returns field variable <code>instantiatedComponent</code>
	 * @return field variable <code>instantiatedComponent</code>
	 */
	public URI getInstantiatedComponent() {
		return instantiatedComponent;
	}

	/**
	 * Sets field variable <code>instantiatedComponent</code> to the specified element.
	 * @param instantiatedComponent
	 */
	public void setInstantiatedComponent(URI instantiatedComponent) {
		this.instantiatedComponent = instantiatedComponent;
	}



}

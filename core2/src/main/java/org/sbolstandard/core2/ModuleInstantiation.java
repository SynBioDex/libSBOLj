package org.sbolstandard.core2;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.Documented;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class ModuleInstantiation extends Documented {
	
	private HashMap<URI, MapsTo> references;
	private URI instantiatedModule;
	
	public ModuleInstantiation(URI identity, URI instantiatedModule) {
		super(identity);
		setInstantiatedModule(instantiatedModule);
		this.references = new HashMap<URI, MapsTo>();
	}

	
	/**
	 * Returns field variable <code>instantiatedModule</code>.
	 * @return field variable <code>instantiatedModule</code>
	 */
	public URI getInstantiatedModule() {
		return instantiatedModule;
	}

	/**
	 * Sets field variable <code>instantiatedModule</code> to the specified element.
	 * @param instantiatedModule
	 */
	public void setInstantiatedModule(URI instantiatedModule) {
		this.instantiatedModule = instantiatedModule;
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

}

package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.Documented;

/**
 * 
 * @author Ernst Oberortner
 * @author Nicholas Roehner
 * @version 2.0
 */
public class ModuleInstantiation extends Documented {
	
	private List<MapsTo> references;
	private URI instantiatedModule;
	
	public ModuleInstantiation(URI identity, URI instantiatedModule) {
		super(identity);
		setInstantiatedModule(instantiatedModule);
		this.references = new ArrayList<MapsTo>();
	}
	
	/**
	 * Check whether optional field variable <code>references</code> is set or not.
	 * @return <code>true</code> if it is not an empty list
	 */
	public boolean isSetReferences() {
		if (references.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * Set optional field variable <code>references</code> to an empty list.
	 */
	public void unsetReferences() {
		references.clear();
	}

	public URI getInstantiatedModule() {
		return instantiatedModule;
	}

	public void setInstantiatedModule(URI instantiatedModule) {
		this.instantiatedModule = instantiatedModule;
	}

	public List<MapsTo> getReferences() {
		return references;
	}

	public void setReferences(List<MapsTo> references) {
		this.references = references;
	}

	
	
	//	private Module instantiatedModule;
//	
//	/**
//	 * @param identity an identity for the module instantiation
//	 * @param displayId a display ID for the module instantiation
//	 * @param instantiatedModule the module to be instantiated
//	 */
//	public ModuleInstantiation(URI identity, String displayId, Module instantiatedModule) {
//		super(identity, displayId);
//		this.instantiatedModule = instantiatedModule;
//	}
//
//	/**
//	 * 
//	 * @return the instantiated module
//	 */
//	public Module getInstantiatedModule() {
//		return instantiatedModule;
//	}

}

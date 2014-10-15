package org.sbolstandard.core2.abstract_classes;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.Component;
import org.sbolstandard.core2.MapsTo;

public abstract class ComponentInstantiation extends Documented {
	
	private AccessType access;
	private URI instantiatedComponent;
	private List<MapsTo> references;
	
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
 	
	public AccessType getAccess() {
		return access;
	}

	public void setAccess(AccessType access) {
		this.access = access;
	}

	public URI getInstantiatedComponent() {
		return instantiatedComponent;
	}

	public void setInstantiatedComponent(URI instantiatedComponent) {
		this.instantiatedComponent = instantiatedComponent;
	}

	public List<MapsTo> getReferences() {
		return references;
	}

	public void setReferences(List<MapsTo> references) {
		this.references = references;
	}
	
//	private Component instantiatedComponent;
//	
//	/**
//	 * 
//	 * @param identity an identity for the component instantiation
//	 * @param displayId a display ID for the component instantiation
//	 * @param instantiatedComponent the component to be instantiated
//	 */
//	public ComponentInstantiation(URI identity, String displayId, 
//			Component instantiatedComponent) {
//		super(identity, displayId);
//		this.instantiatedComponent = instantiatedComponent;
//	}
//	
//	/**
//	 * 
//	 * @return the instantiated component
//	 */
//	public Component getInstantiatedComponent() {
//		return instantiatedComponent;
//	}
	
}

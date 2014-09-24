package org.sbolstandard.core2.abstract_classes;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.Component;
import org.sbolstandard.core2.RefersTo;

public abstract class ComponentInstantiation extends Documented {
	
	private AccessType access;
	private Component instantiatedComponent;
	private List<RefersTo> references;
	
	public ComponentInstantiation(URI identity, URI componentIdentity, AccessType access, List<URI> type, List<URI> roles) {
		super(identity);
		this.access = access;	
		this.instantiatedComponent = new Component(componentIdentity, type, roles);
		this.references = new ArrayList<RefersTo>();
	}
	
	public AccessType getAccess() {
		return access;
	}

	public void setAccess(AccessType access) {
		this.access = access;
	}

	public Component getInstantiatedComponent() {
		return instantiatedComponent;
	}

	public void setInstantiatedComponent(Component instantiatedComponent) {
		this.instantiatedComponent = instantiatedComponent;
	}

	public List<RefersTo> getReferences() {
		return references;
	}

	public void setReferences(List<RefersTo> references) {
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

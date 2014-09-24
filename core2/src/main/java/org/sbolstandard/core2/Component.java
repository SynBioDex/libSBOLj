package org.sbolstandard.core2;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ernst Oberortner
 * @author Nicholas Roehner
 * @version 2.0
 */

public class Component extends TopLevel {

	private List<URI> type;
	private List<URI> roles;
	private List<StructuralInstantiation> structuralInstantiations;
	private List<Structure> structure;
	private List<StructuralAnnotation> structuralAnnotations;
	private List<StructuralConstraint> structuralConstraints;
	
	public Component(URI identity, List<URI> type, List<URI> roles) {
		super(identity);
		this.type = type;
		this.roles = roles;
		this.structuralInstantiations = new ArrayList<StructuralInstantiation>(); 
		this.structure = new ArrayList<Structure>();
		this.structuralAnnotations = new ArrayList<StructuralAnnotation>();
		this.structuralConstraints = new ArrayList<StructuralConstraint>();
	}

	public List<URI> getType() {
		return type;
	}

	public void setType(List<URI> type) {
		this.type = type;
	}

	public List<URI> getRoles() {
		return roles;
	}

	public void setRoles(List<URI> roles) {
		this.roles = roles;
	}

	public List<StructuralInstantiation> getStructuralInstantiations() {
		return structuralInstantiations;
	}

	public void setStructuralInstantiations(
			List<StructuralInstantiation> structuralInstantiations) {
		this.structuralInstantiations = structuralInstantiations;
	}

	public List<Structure> getStructure() {
		return structure;
	}

	public void setStructure(List<Structure> structure) {
		this.structure = structure;
	}

	public List<StructuralAnnotation> getStructuralAnnotations() {
		return structuralAnnotations;
	}

	public void setStructuralAnnotations(
			List<StructuralAnnotation> structuralAnnotations) {
		this.structuralAnnotations = structuralAnnotations;
	}

	public List<StructuralConstraint> getStructuralConstraints() {
		return structuralConstraints;
	}

	public void setStructuralConstraints(
			List<StructuralConstraint> structuralConstraints) {
		this.structuralConstraints = structuralConstraints;
	}

	
//	private URI type;
//	private Collection<ComponentInstantiation> subComponentInstantiations;
//	private Collection<Port> ports;
//	
//	/**
//	 * 
//	 * @param identity an identity for the component
//	 * @param displayId a display ID for the component
//	 * @param type a type for the component
//	 */
//	public Component(URI identity, String displayId, URI type) {
//		super(identity, displayId);
//		this.type = type;
//	}
//	

//	/**
//	 * 
//	 * @return the component's type
//	 */
//	public URI getType() {
//		return type;
//	}
//	
//	/**
//	 * 
//	 * @return a collection of the component's subcomponent instantiations
//	 */
//	public Collection<ComponentInstantiation> getSubComponentInstantiations() {
//		return subComponentInstantiations;
//	}
//
//	/**
//	 * 
//	 * @param subComponentInstantiation a subcomponent instantiation for the component
//	 */
//	public void addSubComponentInstantiation(ComponentInstantiation subComponentInstantiation) {
//		subComponentInstantiations.add(subComponentInstantiation);
//	}
//	
//	/**
//	 * 
//	 * @return a collection of the component's ports
//	 */
//	public Collection<Port> getPorts() {
//		return ports;
//	}
//	
//	/**
//	 * 
//	 * @param port a port for the component
//	 */
//	public void addPort(Port port) {
//		ports.add(port);
//	}

}

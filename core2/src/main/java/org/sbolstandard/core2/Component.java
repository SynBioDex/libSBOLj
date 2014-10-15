package org.sbolstandard.core2;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */

/**
 * @author zhangz
 *
 */
public class Component extends TopLevel {

	private List<URI> type;
	private List<URI> roles;
	private URI structure;
	private List<StructuralInstantiation> structuralInstantiations;
	private List<StructuralAnnotation> structuralAnnotations;
	private List<StructuralConstraint> structuralConstraints;
	
	public Component(URI identity, List<URI> type, List<URI> roles) {
		super(identity);
		setType(type);
		setRoles(roles);		
		this.structuralInstantiations = new ArrayList<StructuralInstantiation>(); 		
		this.structuralAnnotations = new ArrayList<StructuralAnnotation>();
		this.structuralConstraints = new ArrayList<StructuralConstraint>();
	}
	
//	public boolean isSet_type() {
//		if (type == null || type.isEmpty()) {
//			return false;
//		}
//		return true;
//	}
//	
//	public boolean isSet_roles() {
//		if (roles == null || roles.isEmpty()) {
//			return false;
//		}
//		return true;
//	}
	
	/**
	 * Check whether the optional field variable <code>structure</code> is set or not.
	 * @return <code>true</code> if the field variable is not <code>null</code>
	 */
	public boolean isSetStructure() {
		if (structure == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Check whether the optional field variable <code>structuralInstantiations</code> is set or not.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetStructuralInstantiations() {
		if (structuralInstantiations.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * Check whether the optional field variable <code>structuralAnnotations</code> is set or not.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetStructuralAnnotations() {
		if (structuralAnnotations.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Check whether the optional field variable <code>structuralConstraints</code> is set or not.
	 * @return <code>true</code> if the field variable is not an empty list
	 */
	public boolean isSetStructuralConstraints() {
		if (structuralConstraints.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * Set the optional field variable <code>structure</code> to <code>null</code>.
	 */
	public void unsetStructure() {
		structure = null;
	}
	
	/**
	 * Set the optional field variable <code>structuralInstantiations</code> to an empty list.
	 */
	public void unsetStructuralInstantiations() {
		structuralInstantiations.clear();
	}
	
	/**
	 * Set the optional field variable <code>structuralAnnotations</code> to an empty list.
	 */
	public void unsetStructuralAnnotations() {
		structuralAnnotations.clear();
	}
	
	/**
	 * Set the optional field variable <code>structuralConstraints</code> to an empty list.
	 */
	public void unsetStructuralConstraints() {
		structuralConstraints.clear(); 
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

	public URI getStructure() {
		return structure;
	}

	public void setStructure(URI structure) {
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

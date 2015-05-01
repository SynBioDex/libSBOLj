package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sbolstandard.core2.MapsTo.RefinementType;

import static org.sbolstandard.core2.URIcompliance.*;


public abstract class ComponentInstance extends Documented {
	
	private AccessType access;
	protected URI definition;
	private HashMap<URI, MapsTo> mapsTos;
	
	public enum AccessType {
		PUBLIC("public"), PRIVATE("private");
		private final String accessType;

		AccessType(String accessType) {
			this.accessType = accessType;
		}

		/**
		 * Convert the specified URI to its corresponding AccessType instance.
		 * @return the corresponding AccessType instance
		 */
		public static AccessType convertToAccessType(URI access) {
			if (access.equals(Access.PUBLIC)) {
				return AccessType.PUBLIC;
			} else if (access.equals(Access.PRIVATE)) {
				return AccessType.PRIVATE;
			}
			else {
				throw new IllegalArgumentException("Unknown access URI `" + access + "'");
			}
		}
		
		/**
		 * Returns the access type in URI.
		 * @return access type in URI
		 */
		public static URI convertToURI(AccessType access) {
			if (access != null) {
				if (access.equals(AccessType.PUBLIC)) {
					return Access.PUBLIC;
				}
				else if (access.equals(AccessType.PRIVATE)) {
					return Access.PRIVATE;
				}
				else {
					return null;
				}
			}
			else {
				return null;
			}
		}

		/**
		 * Returns the access type.
		 * @return access type.
		 */
		public String getAccessType() {
			return accessType;
		}

		@Override
		public String toString() {
			return accessType;
		}
	}

	public ComponentInstance(URI identity, AccessType access, URI definition) {
		super(identity);
		setAccess(access);
		setDefinition(definition);		
		this.mapsTos = new HashMap<>();
		/*
		if (isURIcompliant(identity, 1)) {
			this.setVersion(extractVersion(identity));
			this.setDisplayId(extractDisplayId(identity, 0));
			this.setPersistentIdentity(URI.create(extractPersistentId(identity)));
		}
		*/
	}
	
	protected ComponentInstance(ComponentInstance component) {
		super(component);
		setAccess(component.getAccess());
		setDefinition(component.getDefinitionURI());	
		if (!component.getMapsTos().isEmpty()) {
			List<MapsTo> mapsTos = new ArrayList<>();
			for (MapsTo mapsTo : component.getMapsTos()) {
				mapsTos.add(mapsTo.deepCopy());
			}
			this.setMapsTo(mapsTos);
		}
	}

	/**
	 * Returns field variable <code>access</code>.
	 * @return field variable <code>access</code>
	 */
	public AccessType getAccess() {
		return access;
	}
	
	/**
	 * Returns the access type in URI.
	 * @return access type in URI
	 */
	public URI getAccessURI() {
		if (access != null) {
			if (access.equals(AccessType.PUBLIC)) {
				return Access.PUBLIC;
			}
			else if (access.equals(AccessType.PRIVATE)) {
				return Access.PRIVATE;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	/**
	 * Sets field variable <code>access</code> to the specified element.
	 */
	public void setAccess(AccessType access) {
		if (access==null) {
			throw new IllegalArgumentException("Not a valid access type.");
		}
		this.access = access;
	}
	
	/**
	 * Sets field variable <code>access</code> to the element corresponding to the specified URI.
	 */
	public void setAccess(URI access) {
		if (access != null && access.equals(Access.PUBLIC)) {
			this.access = AccessType.PUBLIC;
		} else if (access != null && access.equals(Access.PRIVATE)) {
			this.access = AccessType.PRIVATE;
		}
		else {
			throw new IllegalArgumentException("Not a valid access type.");
		}
	}

//	/**
//	 * Test if optional field variable <code>references</code> is set.
//	 * @return <code>true</code> if it is not an empty list
//	 */
//	public boolean isSetMappings() {
//		if (mapsTos.isEmpty())
//			return false;
//		else
//			return true;
//	}	
	
	/**
	 * Calls the MapsTo constructor to create a new instance using the specified parameters, 
	 * then adds to the list of MapsTo instances owned by this component.
	 *
	 * @return the created MapsTo instance.
	 */
	public MapsTo createMapsTo(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		MapsTo mapping = new MapsTo(identity, refinement, local, remote);
		addMapsTo(mapping);
		return mapping;
	}
	
	/**
	 * @return the newly created MapsTo object.
	 */
	public MapsTo createMapsTo(String displayId, RefinementType refinement, URI local, URI remote) {
		String parentPersistentIdStr = extractPersistentId(this.getIdentity());
		String version = this.getVersion();
		MapsTo m = createMapsTo(createCompliantURI(parentPersistentIdStr, displayId, version),
				refinement, local, remote);
		m.setPersistentIdentity(createCompliantURI(parentPersistentIdStr, displayId, ""));
		m.setDisplayId(displayId);
		m.setVersion(version);
		return m;
	}
	
	/**
	 * Adds the specified instance to the list of references. 
	 */
	public void addMapsTo(MapsTo mapsTo) {
		addChildSafely(mapsTo, mapsTos, "mapsTo");
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of references if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo removeMapsTo(URI mappingURI) {
		return mapsTos.remove(mappingURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of references if present.
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo getMapsTo(URI referenceURI) {
		return mapsTos.get(referenceURI);
	}
	
	/**
	 * Returns the list of reference instances owned by this instance. 
	 * @return the list of reference instances owned by this instance.
	 */
	public Set<MapsTo> getMapsTos() {
		return new HashSet<>(mapsTos.values());
	}
	
	/**
	 * Removes all entries of the list of reference instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearMapsTos() {
		Object[] keySetArray = mapsTos.keySet().toArray();
		for (Object key : keySetArray) {
			removeMapsTo((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of reference instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	public void setMapsTo(
			List<MapsTo> mapsTos) {
		clearMapsTos();		
		for (MapsTo reference : mapsTos) {
			addMapsTo(reference);
		}
	}
	
	/**
	 * Returns field variable <code>definition</code>
	 * @return field variable <code>definition</code>
	 */
	public URI getDefinitionURI() {
		return definition;
	}
	
	/**
	 * Returns the Component Definition referenced by this Component Instance
	 * @return the Component Definition referenced by this Component Instance
	 */	
	public ComponentDefinition getDefinition() {
		if (sbolDocument==null) return null;
		return sbolDocument.getComponentDefinition(definition);
	}

	/**
	 * Sets field variable <code>instantiatedComponent</code> to the specified element.
	 */
	public void setDefinition(URI definition) {
		if (definition==null) {
			throw new IllegalArgumentException("Component "+this.getIdentity()+" must have a definition.");
		}
		if (sbolDocument != null && sbolDocument.isComplete()) {
			if (sbolDocument.getComponentDefinition(definition)==null) {
				throw new IllegalArgumentException("Component definition '" + definition + "' does not exist.");
			}
		}
		this.definition = definition;
	}

	protected abstract ComponentInstance deepCopy();
	
	
	private static final class Access {
		public static final URI PUBLIC = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "public");
		public static final URI PRIVATE = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "private");
	}
	

}

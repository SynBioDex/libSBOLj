package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sbolstandard.core2.MapsTo.RefinementType;

import static org.sbolstandard.core2.URIcompliance.*;
import static org.sbolstandard.core2.Version.*;


public abstract class ComponentInstance extends Documented {
	
	private AccessType access;
	private URI definition;
	private HashMap<URI, MapsTo> mapsTos;
	
	public static enum AccessType {
		PUBLIC("public"), PRIVATE("private");
		private final String accessType;

		private AccessType(String accessType) {
			this.accessType = accessType;
		}

		/**
		 * Convert the specified URI to its corresponding AccessType instance.
		 * @param access
		 * @return the corresponding AccessType instance
		 */
		public static AccessType convertToAccessType(URI access) {
			if (access.equals(Access.PUBLIC)) {
				return AccessType.PUBLIC;
			} else if (access.equals(Access.PRIVATE)) {
				return AccessType.PRIVATE;
			}
			else {
				// TODO: Validation?
				return null;
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
		this.mapsTos = new HashMap<URI, MapsTo>();
		if (isURIcompliant(identity, 1)) {
			this.setVersion(extractVersion(identity));
			this.setDisplayId(extractDisplayId(identity, 0));
			this.setPersistentIdentity(URI.create(extractPersistentId(identity)));
		}
	}
	
	protected ComponentInstance(ComponentInstance component) {
		super(component);
		setAccess(component.getAccess());
		setDefinition(component.getDefinition());	
		if (!component.getMapsTos().isEmpty()) {
			List<MapsTo> mapsTos = new ArrayList<MapsTo>();
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
	 * @param access
	 */
	public void setAccess(AccessType access) {
		this.access = access;
	}
	
	/**
	 * Sets field variable <code>access</code> to the element corresponding to the specified URI.
	 * @param access
	 */
	public void setAccess(URI access) {
		if (access.equals(Access.PUBLIC)) {
			this.access = AccessType.PUBLIC;
		} else if (access.equals(Access.PRIVATE)) {
			this.access = AccessType.PRIVATE;
		}
		else {
			// TODO: Validation?
			this.access = null;
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
	 * @param identity
	 * @param refinement
	 * @param local
	 * @param remote
	 * @return the created MapsTo instance. 
	 */
	public MapsTo createMapsTo(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		MapsTo mapping = new MapsTo(identity, refinement, local, remote);
		if (addMapsTo(mapping)) {
			return mapping;	
		}
		else {
			return null;
		}		
	}
	
	public MapsTo createMapsTo(String displayId, String version, RefinementType refinement, URI local, URI remote) {
		String parentPersistentIdStr = extractPersistentId(this.getIdentity());
		if (parentPersistentIdStr != null) {
			if (isDisplayIdCompliant(displayId)) {
				if (isVersionCompliant(version)) {
					URI newMapsToURI = URI.create(parentPersistentIdStr + '/' + displayId + '/' + version);
					return createMapsTo(newMapsToURI, refinement, local, remote);
				}
				else {
					// TODO: Warning: version not compliant
					return null;
				}
			}
			else {
				// TODO: Warning: display ID not compliant
				return null;
			}
		}
		else {
			// TODO: Warning: Parent persistent ID is not compliant.
			return null;
		}
	}
	
	/**
	 * Adds the specified instance to the list of references. 
	 * @param reference
	 */
	public boolean addMapsTo(MapsTo mapsTo) {
		//mapsTos.put(mapTo.getIdentity(), mapTo);
		if (isChildURIcompliant(this.getIdentity(), mapsTo.getIdentity())) {
			URI persistentId = URI.create(extractPersistentId(mapsTo.getIdentity()));
			// Check if URI exists in the mapsTos map.
			if (!mapsTos.containsKey(mapsTo.getIdentity())) {
				mapsTos.put(mapsTo.getIdentity(), mapsTo);
				MapsTo latestMapsTo = mapsTos.get(persistentId);
				if (latestMapsTo == null) {
					mapsTos.put(persistentId, mapsTo);
				}
				else {						
					if (isFirstVersionNewer(extractVersion(mapsTo.getIdentity()), 
							extractVersion(latestMapsTo.getIdentity()))) {								
						mapsTos.put(persistentId, mapsTo);
					}
				}
				return true;
			}
			else // key exists in mapsTos map
				return false;
		}
		else { // Only check if mapTo's URI exists in all maps.
			if (!mapsTos.containsKey(mapsTo.getIdentity())) {
				mapsTos.put(mapsTo.getIdentity(), mapsTo);					
				return true;
			}
			else // key exists in mapsTos map
				return false;
		}
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of references if present.
	 * @param mappingURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo removeMapsTo(URI mappingURI) {
		return mapsTos.remove(mappingURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of references if present.
	 * @param referenceURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo getMapsTo(URI referenceURI) {
		return mapsTos.get(referenceURI);
	}
	
	/**
	 * Returns the list of reference instances owned by this instance. 
	 * @return the list of reference instances owned by this instance.
	 */
	public List<MapsTo> getMapsTos() {
		return new ArrayList<MapsTo>(mapsTos.values());
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
	 * @param mappings
	 */
	public void setMapsTo(
			List<MapsTo> mappings) {
		clearMapsTos();		
		for (MapsTo reference : mappings) {
			addMapsTo(reference);
		}
	}
	
	/**
	 * Returns field variable <code>definition</code>
	 * @return field variable <code>definition</code>
	 */
	public URI getDefinition() {
		return definition;
	}

	/**
	 * Sets field variable <code>instantiatedComponent</code> to the specified element.
	 * @param definitionURI
	 */
	public void setDefinition(URI definitionURI) {
		this.definition = definitionURI;
	}

	protected abstract ComponentInstance deepCopy();
	
	
	private static final class Access {
		public static final URI PUBLIC = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "public");
		public static final URI PRIVATE = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "private");
	}
	

}

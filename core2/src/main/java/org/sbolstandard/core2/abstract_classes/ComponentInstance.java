package org.sbolstandard.core2.abstract_classes;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sbolstandard.core2.MapsTo;
import org.sbolstandard.core2.MapsTo.RefinementType;
import org.sbolstandard.core2.Sbol2Terms;
import org.sbolstandard.core2.util.UriCompliance;
import org.sbolstandard.core2.util.Version;


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
	}
	
	protected ComponentInstance(ComponentInstance component) {
		super(component);
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

	/**
	 * Test if optional field variable <code>references</code> is set.
	 * @return <code>true</code> if it is not an empty list
	 */
	public boolean isSetMappings() {
		if (mapsTos.isEmpty())
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
	public MapsTo createMapping(URI identity, RefinementType refinement, 
			URI local, URI remote) {
		MapsTo mapping = new MapsTo(identity, refinement, local, remote);
		addMapping(mapping);
		return mapping;
	}
	
	/**
	 * Adds the specified instance to the list of references. 
	 * @param reference
	 */
	public void addMapping(MapsTo mapTo) {
		
		mapsTos.put(mapTo.getIdentity(), mapTo);
//		if (UriCompliance.isChildURIcompliant(this.getIdentity(), mapTo.getIdentity())) {
//			URI persistentId = URI.create(UriCompliance.extractPersistentId(mapTo.getIdentity()));
//			// Check if URI exists in the mapsTos map.
//			if (!mapsTos.containsKey(mapTo.getIdentity())) {
//				mapsTos.put(mapTo.getIdentity(), mapTo);
//				MapsTo latestSubComponent = mapsTos.get(persistentId);
//				if (latestSubComponent == null) {
//					mapsTos.put(persistentId, mapTo);
//				}
//				else {						
//					if (Version.isFirstVersionNewer(UriCompliance.extractVersion(mapTo.getIdentity()), 
//							UriCompliance.extractVersion(latestSubComponent.getIdentity()))) {								
//						mapsTos.put(persistentId, mapTo);
//					}
//				}
//				return true;
//			}
//			else // key exists in mapsTos map
//				return false;
//		}
//		else { // Only check if mapTo's URI exists in all maps.
//			if (!mapsTos.containsKey(mapTo.getIdentity())) {
//				mapsTos.put(mapTo.getIdentity(), mapTo);					
//				return true;
//			}
//			else // key exists in mapsTos map
//				return false;
//		}
	}
	
	/**
	 * Removes the instance matching the specified URI from the list of references if present.
	 * @param mappingURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo removeMapping(URI mappingURI) {
		return mapsTos.remove(mappingURI);
	}
	
	/**
	 * Returns the instance matching the specified URI from the list of references if present.
	 * @param referenceURI
	 * @return the matching instance if present, or <code>null</code> if not present.
	 */
	public MapsTo getMapping(URI referenceURI) {
		return mapsTos.get(referenceURI);
	}
	
	/**
	 * Returns the list of reference instances owned by this instance. 
	 * @return the list of reference instances owned by this instance.
	 */
	public List<MapsTo> getMappings() {
		return new ArrayList<MapsTo>(mapsTos.values());
	}
	
	/**
	 * Removes all entries of the list of reference instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearMappings() {
		Object[] keySetArray = mapsTos.keySet().toArray();
		for (Object key : keySetArray) {
			removeMapping((URI) key);
		}
	}
		
	/**
	 * Clears the existing list of reference instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param mappings
	 */
	public void setMappings(
			List<MapsTo> mappings) {
		clearMappings();		
		for (MapsTo reference : mappings) {
			addMapping(reference);
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
	
	
	public static final class Access {
		public static final URI PUBLIC = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "public");
		public static final URI PRIVATE = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "private");
	}
	

}

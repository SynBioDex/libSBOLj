package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import static org.sbolstandard.core2.URIcompliance.*;
import static org.sbolstandard.core2.Version.isFirstVersionNewer;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public abstract class Identified {

	protected URI identity;
	private URI persistentIdentity;
	private String version;
	private List<Annotation> annotations;
	private URI wasDerivedFrom;
	protected String displayId;
	protected SBOLDocument sbolDocument = null;
	protected String name;
	protected String description;

	Identified(URI identity) {
		setIdentity(identity);
		/*
		if (isURIcompliant(identity, 0)) {
			this.setVersion(extractVersion(identity));
			this.setDisplayId(extractDisplayId(identity));
			this.setPersistentIdentity(URI.create(extractPersistentId(identity)));
		}
		*/
		this.annotations = new ArrayList<>();
	}

	/**
	 * This copy constructor creates a new {@link Identified} class and copies all fields specified by the <code>identified</code> object.
	 */
	protected Identified(Identified identified) {
		this.setIdentity(identified.getIdentity());
		if (identified.hasAnnotations()) {
			List<Annotation> clonedAnnotations = new ArrayList<>();
			for (Annotation annotation : identified.getAnnotations()) {
				clonedAnnotations.add(annotation.copy());
			}
			this.setAnnotations(clonedAnnotations);
		}
		if (identified.isSetVersion()) {
			this.setVersion(identified.getVersion());
		}
		if (identified.isSetPersistentIdentity()) {
			this.setPersistentIdentity(URI.create(identified.getPersistentIdentity().toString()));
		}
		if (identified.isSetWasDerivedFrom()) {
			this.setWasDerivedFrom(URI.create(identified.getWasDerivedFrom().toString()));
		}
		if (identified.isSetName()) {
			this.setName(identified.getName());
		}
		if (identified.isSetDescription()) {
			this.setDescription(identified.getDescription());
		}	
	}

//	public Identified (String URIprefix, String displayId, String version) {
//		setIdentity(URI.create(URIprefix.trim() + '/' + displayId.trim() + '/' + version));
//		this.annotations = new ArrayList<Annotation>();
//		this.setPersistentIdentity(URI.create(URIprefix.trim() + '/' + displayId.trim()));
//		this.setVersion(version);
//	}

	/**
	 * Returns field variable <code>identity</code>.
	 * @return field variable <code>identity</code>
	 */
	public URI getIdentity() {
		return identity;
	}

	/**
	 * Sets field variable <code>identity</code> to the specified element.
	 * @param identity URI for the specified element.
	 * @throws IllegalArgumentException when identity URI is null.
	 */
	final void setIdentity(URI identity) {
		if (identity == null) {
			throw new IllegalArgumentException("Identity is a required field.");
		}
		this.identity = identity;
	}

	/**
	 * Test if optional field variable <code>persistentIdentity</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetPersistentIdentity() {
		return persistentIdentity != null;
	}

	/**
	 * Returns field variable <code>persistentIdentity</code>.
	 * @return field variable <code>persistentIdentity</code>
	 */
	public URI getPersistentIdentity() {
		return persistentIdentity;
	}

	/**
	 * Sets field variable <code>persistentIdentity</code> to the specified element.
	 */
	void setPersistentIdentity(URI persistentIdentity) {
		this.persistentIdentity = persistentIdentity;
	}

	/**
	 * Set optional field variable <code>persistentIdentity</code> to <code>null</code>.
	 */
	void unsetPersistentIdentity() {
		persistentIdentity = null;
	}

	/**
	 * Test if optional field variable <code>version</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetVersion() {
		return version != null;
	}

	public boolean isSetWasDerivedFrom() {
		return wasDerivedFrom != null;
	}

	/**
	 * Returns field variable <code>version</code>.
	 * @return field variable <code>version</code>.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets field variable <code>version</code> to the specified element.
	 */
	void setVersion(String version) {
		if (version==null || version.equals("")) return;
		if (!URIcompliance.isVersionCompliant(version)) {
			throw new IllegalArgumentException(
					"Version " + version + " is invalid for `" + identity + "'.");
		}
		this.version = version;
	}
	
	/**
	 * Test if optional field variable <code>displayId</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetDisplayId() {
		return displayId != null;
	}
	
	/**
	 * Returns field variable <code>displayId</code>.
	 * @return field variable <code>displayId</code>
	 */
	// @return the documented object's display ID
	public String getDisplayId() {
		return displayId;
	}
		
	/**
	 * Set field variable <code>displayId</code> to the specified element.
	 */
	void setDisplayId(String displayId) {
		if (!URIcompliance.isDisplayIdCompliant(displayId)) {
			throw new IllegalArgumentException(
					"Display id " + displayId + " is invalid for `" + identity + "'.");
		}
		this.displayId = displayId;
	}
	
	/**
	 * Set optional field variable <code>displayId</code> to <code>null</code>.
	 */
	void unsetDisplayId() {
		displayId = null;
	}

	public URI getWasDerivedFrom() {
		return wasDerivedFrom;
	}

	/**
	 * Sets field variable <code>wasDerivedFrom</code> to the specified value.
	 */
	public void setWasDerivedFrom(URI wasDerivedFrom) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		this.wasDerivedFrom = wasDerivedFrom;

	}

	/**
	 * Test if this instance has <code>annotations</code>.
	 * @return <code>true</code> if it is not an empty list
	 */
	public boolean hasAnnotations() {
		return !annotations.isEmpty();
	}
	
	/**
	 * Calls the Annotation constructor to create a new instance using the specified parameters,
	 * then adds to the list of Annotation instances owned by this component.
	 * @return the created Annotation instance.
	 */
	public Annotation createAnnotation(QName qName, String literal) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Calls the Annotation constructor to create a new instance using the specified parameters,
	 * then adds to the list of Annotation instances owned by this component.
	 * @return the created Annotation instance.
	 */
	public Annotation createAnnotation(QName qName, URI literal) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}
	
	/**
	 * Calls the Annotation constructor to create a new instance using the specified parameters,
	 * then adds to the list of Annotation instances owned by this component.
	 * @return the created Annotation instance.
	 */
	Annotation createAnnotation(NamedProperty<QName> namedProperty) {
		Annotation annotation = new Annotation(namedProperty);
		addAnnotation(annotation);
		return annotation;
	}
	
	
	/**
	 * Calls the Annotation constructor to create a new instance using the specified parameters,
	 * then adds to the list of Annotation instances owned by this component.
	 * @return the created Annotation instance.
	 */
	public Annotation createAnnotation(QName qName,QName nestedQName, URI nestedURI, List<Annotation> annotations) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Annotation annotation = new Annotation(qName, nestedQName, nestedURI, annotations);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Adds the specified instance to the list of structuralAnnotations.
	 */
	void addAnnotation(Annotation annotation) {
		// TODO: @addAnnotation, Check for duplicated entries.
		annotations.add(annotation);
	}

	/**
	 * Removes an annotation from the list of annotations for this object.
	 * @param annotation to remove.
	 * @return <code>true</code> if the matching instance is present.
	 */
	public boolean removeAnnotation(Annotation annotation) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return annotations.remove(annotation);
	}

	//	/**
	//	 * Returns the instance matching the specified URI from the list of annotations if present.
	//	 * @param structuralAnnotationURI
	//	 * @return the matching instance if present, or <code>null</code> if not present.
	//	 */
	//	public Annotation getAnnotation(URI structuralAnnotationURI) {
	//		return annotations.get(structuralAnnotationURI);
	//	}

	/**
	 * Returns the list of annotations owned by this instance.
	 * @return the list of annotations owned by this instance.
	 */
	public List<Annotation> getAnnotations() {
		// TODO: should likely copy the list rather than returning the list
		return annotations;
	}

	/**
	 * Removes all annotations from this object.
	 */
	public void clearAnnotations() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		annotations.clear();
	}

	/**
	 * Clears the existing list of structuralAnnotation instances, then appends all of the elements in the specified collection to the end of this list.
	 */
	void setAnnotations(
			List<Annotation> annotations) {
		clearAnnotations();
		for (Annotation structuralAnnotation : annotations) {
			addAnnotation(structuralAnnotation);
		}
	}

	public void unsetWasDerivedFrom() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		wasDerivedFrom = null;
	}
	
	protected void setSBOLDocument(SBOLDocument sbolDocument) {
		this.sbolDocument = sbolDocument;
	}

	protected SBOLDocument getSBOLDocument() {
		return sbolDocument;
	}
	
	/**
	 * Provide a deep copy of this instance.
	 */

	protected abstract Identified deepCopy();


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annotations == null) ? 0 : annotations.hashCode());
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		result = prime * result	+ ((persistentIdentity == null) ? 0 : persistentIdentity.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((wasDerivedFrom == null) ? 0 : wasDerivedFrom.hashCode());
		result = prime * result + ((displayId == null) ? 0 : displayId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identified other = (Identified) obj;
		if (annotations == null) {
			if (other.annotations != null)
				return false;
		} else if (!annotations.equals(other.annotations))
			return false;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		if (persistentIdentity == null) {
			if (other.persistentIdentity != null)
				return false;
		} else if (!persistentIdentity.equals(other.persistentIdentity))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (wasDerivedFrom == null) {
			if (other.wasDerivedFrom != null)
				return false;
		} else if (!wasDerivedFrom.equals(other.wasDerivedFrom))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (displayId == null) {
			if (other.displayId != null)
				return false;
		} else if (!displayId.equals(other.displayId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@SafeVarargs
	protected final <I extends Identified> void addChildSafely(I child, Map<URI, I> siblingsMap, String typeName, Map<URI, ? extends Identified> ... maps) {
		if (isChildURIcompliant(this.getIdentity(), child.getIdentity())) {
			URI persistentId = URI.create(extractPersistentId(child.getIdentity()));
			if(keyExistsInAnyMap(persistentId, maps))
				throw new IllegalArgumentException(
			"Instance for identity `" + child.identity +
					"' and persistent identity `" + persistentId + "' exists for a non-" + typeName);
			if(siblingsMap.containsKey(child.getIdentity()))
				throw new IllegalArgumentException(
						"Instance for identity `" + child.identity +
								"' and persistent identity `" + persistentId + "' exists for a " + typeName);
			siblingsMap.put(child.getIdentity(), child);
			I latest = siblingsMap.get(persistentId);
			if (latest == null) {
				siblingsMap.put(persistentId, child);
			}
			else {
				if (isFirstVersionNewer(extractVersion(child.getIdentity()),
						extractVersion(latest.getIdentity()))) {
					siblingsMap.put(persistentId, child);
				}
			}
		}
		else { // Only check if participation's URI exists in all maps.
            if(keyExistsInAnyMap(child.getIdentity(), maps))
                throw new IllegalArgumentException(
                        "Instance for identity `" + child.identity +
                                "' exists for a non-" + typeName);
            if(siblingsMap.containsKey(child.getIdentity()))
				throw new IllegalArgumentException(
						"Instance for identity `" + child.identity + "' exists for a " + typeName);
			siblingsMap.put(child.getIdentity(), child);
		}

	}
	
	protected final <I extends Identified> boolean removeChildSafely(Identified identified, Map<URI, I> siblingsMap) {
		Set<Identified> objectsToRemove = new HashSet<>();
		objectsToRemove.add(identified);
		return siblingsMap.values().removeAll(objectsToRemove);
	}

	/**
	 * Test if optional field variable <code>name</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetName() {
		return name != null;
	}

	/**
	 * Returns field variable <code>name</code>.
	 * @return field variable <code>name</code>
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets field variable <code>name</code> to the specified element.
	 */
	public void setName(String name) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		this.name = name;
	}

	/**
	 * Sets optional field variable <code>name</code> to <code>null</code>.
	 */
	public void unsetName() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		name = null;
	}

	/**
	 * Test if optional field variable <code>description</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetDescription() {
		return description != null;
	}

	/**
	 * Returns field variable <code>description</code>.
	 * @return field variable <code>description</code>
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets field variable <code>description</code> to the specified element.
	 */
	public void setDescription(String description) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		this.description = description;
	}

	/**
	 * Set optional field variable <code>description</code> to <code>null</code>.
	 */
	public void unsetDescription() {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		description = null;
	}

	//	/**
	//	 * @return
	//	 * @deprecated As of release 2.0, replaced by {@link #getIdentity()}
	//	 */
	//	public URI getURI() {
	//		return identity;
	//	}

	//	/**
	//	 *
	//	 * Sets the identifier for this object.
	//	 * @param value
	//	 * @deprecated As of release 2.0, URI can only be set when an Identified instance is created.
	//	 */
	//	public void setURI(URI value) {
	//		this.identity = value;
	//	}

}

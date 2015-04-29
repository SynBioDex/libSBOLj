package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamedProperty;
import static org.sbolstandard.core2.URIcompliance.*;
import static org.sbolstandard.core2.Version.isFirstVersionNewer;

/**
 *
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */

public abstract class Identified {

	protected URI identity;
	private URI persistentIdentity;
	private String version;
	private List<Annotation> annotations;
	private URI wasDerivedFrom;
	protected String displayId;
	protected SBOLDocument sbolDocument = null;

	public Identified(URI identity) {
		setIdentity(identity);
		if (isURIcompliant(identity, 0)) {
			this.setVersion(extractVersion(identity));
			this.setDisplayId(extractDisplayId(identity, 0));
			this.setPersistentIdentity(URI.create(extractPersistentId(identity)));
		}
		this.annotations = new ArrayList<Annotation>();
	}

	/**
	 * This copy constructor creates a new {@link Identified} class and copies all fields specified by the <code>identified</code> object.
	 * @param identified
	 */
	protected Identified(Identified identified) {
		this.setIdentity(identified.getIdentity());
		if (identified.isSetAnnotations()) {
			List<Annotation> clonedAnnotations = new ArrayList<Annotation>();
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
	public final void setIdentity(URI identity) {
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
		if (persistentIdentity == null)
			return false;
		else
			return true;
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
	 * @param persistentIdentity
	 */
	public void setPersistentIdentity(URI persistentIdentity) {
		this.persistentIdentity = persistentIdentity;
	}

	/**
	 * Set optional field variable <code>persistentIdentity</code> to <code>null</code>.
	 */
	public void unsetPersistentIdentity() {
		persistentIdentity = null;
	}

	/**
	 * Test if optional field variable <code>version</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetVersion() {
		if (version == null)
			return false;
		else
			return true;
	}

	public boolean isSetWasDerivedFrom() {
		if (wasDerivedFrom == null)
			return false;
		else
			return true;
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
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * Test if optional field variable <code>displayId</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetDisplayId() {
		if (displayId == null)
			return false;
		else 
			return true;
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
	 * @param displayId
	 */
	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}
	
	/**
	 * Set optional field variable <code>displayId</code> to <code>null</code>.
	 */
	public void unsetDisplayId() {
		displayId = null;
	}

	public URI getWasDerivedFrom() {
		return wasDerivedFrom;
	}

	/**
	 * Sets field variable <code>wasDerivedFrom</code> to the specified value.
	 * @param wasDerivedFrom
	 */
	public void setWasDerivedFrom(URI wasDerivedFrom) {
		this.wasDerivedFrom = wasDerivedFrom;

	}

	/**
	 * Test if optional field variable <code>annotations</code> is set.
	 * @return <code>true</code> if it is not an empty list
	 */
	public boolean isSetAnnotations() {
		if (annotations.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * Calls the Annotation constructor to create a new instance using the specified parameters,
	 * then adds to the list of Annotation instances owned by this component.
	 * @param relation
	 * @param literal
	 * @return the created Annotation instance.
	 */
	public Annotation createAnnotation(QName qName, String literal) {
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
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}
	
	/**
	 * Calls the Annotation constructor to create a new instance using the specified parameters,
	 * then adds to the list of Annotation instances owned by this component.
	 * @param namedProperty
	 * @return the created Annotation instance.
	 */
	public Annotation createAnnotation(NamedProperty<QName> namedProperty) {
		Annotation annotation = new Annotation(namedProperty);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Adds the specified instance to the list of structuralAnnotations.
	 * @param annotation
	 */
	public void addAnnotation(Annotation annotation) {
		// TODO: @addAnnotation, Check for duplicated entries.
		annotations.add(annotation);
	}

	/**
	 * Removes the instance matching the specified URI from the list of annotations if present.
	 * @param annotation
	 * @return <code>True</code> if the matching instance is present.
	 */
	public boolean removeAnnotation(Annotation annotation) {
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
	 * Returns the list of structuralAnnotation instances owned by this instance.
	 * @return the list of structuralAnnotation instances owned by this instance.
	 */
	public List<Annotation> getAnnotations() {
		return annotations;
	}

	/**
	 * Removes all entries of the list of structuralAnnotation instances owned by this instance. The list will be empty after this call returns.
	 */
	public void clearAnnotations() {
		//		Object[] keySetArray = annotations.keySet().toArray();
		//		for (Object key : keySetArray) {
		//			removeAnnotation((URI) key);
		//		}
		annotations.clear();
	}

	/**
	 * Clears the existing list of structuralAnnotation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @param annotations
	 */
	public void setAnnotations(
			List<Annotation> annotations) {
		clearAnnotations();
		for (Annotation structuralAnnotation : annotations) {
			addAnnotation(structuralAnnotation);
		}
	}

	/**
	 * Set optional field variable <code>annotations</code> to <code>null</code>.
	 */
	public void unsetAnnotations() {
		annotations = null;
	}

	//	public void unsetMajorVersion() {
	//		majorVersion = null;
	//	}
	//
	//	public void unsetMinorVersion() {
	//		minorVersion = null;
	//	}


	public void unsetWasDerivedFrom() {
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
		result = prime * result
				+ ((persistentIdentity == null) ? 0 : persistentIdentity.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((wasDerivedFrom == null) ? 0 : wasDerivedFrom.hashCode());
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

		return true;
	}

	protected <I extends Identified> void addChildSafely(I child, Map<URI, I> siblingsMap, String typeName, Map<URI, ? extends Identified> ... maps) {
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

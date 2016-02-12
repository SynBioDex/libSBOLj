package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.extractPersistentId;
import static org.sbolstandard.core2.URIcompliance.extractVersion;
import static org.sbolstandard.core2.URIcompliance.isChildURIformCompliant;
import static org.sbolstandard.core2.URIcompliance.keyExistsInAnyMap;
import static org.sbolstandard.core2.Version.isFirstVersionNewer;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import uk.ac.ncl.intbio.core.datatree.NamedProperty;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0.1-beta
 */

public abstract class Identified {

	protected URI identity;
	private URI persistentIdentity;
	private String version;
	// TODO: can this be a set instead?
	private List<Annotation> annotations;
	private URI wasDerivedFrom;
	protected String displayId;
	protected SBOLDocument sbolDocument = null;
	protected String name;
	protected String description;

	Identified(URI identity) throws SBOLValidationException {
		setIdentity(identity);
		this.annotations = new ArrayList<>();
	}

	/**
	 * This copy constructor creates a new {@link Identified} class and copies all fields specified by the <code>identified</code> object.
	 * @throws SBOLValidationException 
	 */
	protected Identified(Identified identified) throws SBOLValidationException {
		this.setIdentity(identified.getIdentity());
		this.annotations = new ArrayList<>();
		if (identified.hasAnnotations()) {
			List<Annotation> clonedAnnotations = new ArrayList<>();
			for (Annotation annotation : identified.getAnnotations()) {
				clonedAnnotations.add(annotation.copy());
			}
			this.setAnnotations(clonedAnnotations);
		}
		if (identified.isSetDisplayId()) {
			this.setDisplayId(identified.getDisplayId());
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
	 * Returns the identity URI of this object.
	 *
	 * @return the identity URI of this object.
	 */
	public URI getIdentity() {
		return identity;
	}

	/**
	 * Sets field variable <code>identity</code> to the specified element.
	 * @param identity URI for the specified element.
	 * @throws SBOLValidationException when identity URI is null.
	 */
	final void setIdentity(URI identity) throws SBOLValidationException {
		if (identity == null) {
			// throw new SBOLValidationException("Identity is a required field.");
			throw new SBOLValidationException("sbol-10201", this);
		}
		this.identity = identity;
	}

	/**
	 * Test if the persistent identity URI is set.
	 *
	 * @return {@code true} if it is not {@code null}
	 */
	public boolean isSetPersistentIdentity() {
		return persistentIdentity != null;
	}

	/**
	 * Returns the persistent identity URI of this object.
	 *
	 * @return the persistent identity URI of this object.
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
	 * Set optional field variable <code>persistentIdentity</code> to {@code null}.
	 */
	void unsetPersistentIdentity() {
		persistentIdentity = null;
	}

	/**
	 * Test if the version is set.
	 *
	 * @return {@code true} if it is not {@code null}
	 */
	public boolean isSetVersion() {
		return version != null;
	}

	/**
	 * Test if the {@code wasDerivedFrom} property is set.
	 *
	 * @return {@code true} if it is not {@code null}
	 */
	public boolean isSetWasDerivedFrom() {
		return wasDerivedFrom != null;
	}

	/**
	 * Returns this object's version.
	 *
	 * @return this object's version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets field variable <code>version</code> to the specified element.
	 * @throws SBOLValidationException 
	 */
	void setVersion(String version) throws SBOLValidationException {
		if (version==null || version.equals("")) {
			this.version=null;
			return;
		}
		if (!URIcompliance.isVersionCompliant(version)) {
//			throw new SBOLValidationException(
//					"Version " + version + " is invalid for `" + identity + "'.");
			throw new SBOLValidationException("sbol-10206");
			// TODO: (Validation) print String version?
		}
		this.version = version;
	}

	/**
	 * Test if the display ID is set.
	 *
	 * @return {@code true} if it is not {@code null}
	 */
	public boolean isSetDisplayId() {
		return displayId != null;
	}

	/**
	 * Returns the display ID of this object.
	 *
	 * @return the display ID of this object.
	 */
	public String getDisplayId() {
		return displayId;
	}

	/**
	 * Set field variable <code>displayId</code> to the specified element.
	 * @throws SBOLValidationException 
	 */
	void setDisplayId(String displayId) throws SBOLValidationException {
		if (!URIcompliance.isDisplayIdCompliant(displayId)) {
//			throw new SBOLValidationException(
//					"Display id " + displayId + " is invalid for `" + identity + "'.");
			throw new SBOLValidationException("sbol-10204");
		}
		this.displayId = displayId;
	}

	/**
	 * Set optional field variable <code>displayId</code> to {@code null}.
	 */
	void unsetDisplayId() {
		displayId = null;
	}

	/**
	 * Returns the {@code wasDerivedFrom} property of this object.
	 *
	 * @return the {@code wasDerivedFrom} property of this object.
	 */
	public URI getWasDerivedFrom() {
		return wasDerivedFrom;
	}

	/**
	 * Sets the {@code wasDerivedFrom} property of this object to the specified one.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param wasDerivedFrom The URI for with an SBOL object with this property refers to another SBOL object or non-SBOL resource from which this object was derived
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setWasDerivedFrom(URI wasDerivedFrom) throws SBOLValidationException {
		if (sbolDocument!=null) {
			sbolDocument.checkReadOnly();
			if (!SBOLValidate.checkWasDerivedFromVersion(sbolDocument, this, wasDerivedFrom)) {
//				throw new SBOLValidationException(getIdentity() + " is derived from " + wasDerivedFrom + 
//						" but has older version.");
				throw new SBOLValidationException("sbol-10211", this);
				// TODO: (Validation) print URI for this object?
			}
			if (SBOLValidate.checkWasDerivedFromCycle(sbolDocument, this, wasDerivedFrom, new HashSet<URI>())) {
				//throw new SBOLValidationException("Cycle found in '" + getIdentity() + "' was derived from link.");
				throw new SBOLValidationException("sbol-10210", this);
			}
		}
		this.wasDerivedFrom = wasDerivedFrom;
	}

	/**
	 * Test if this object has any Annotation instances.
	 *
	 * @return {@code true} if this object has any Annotation objects
	 */
	public boolean hasAnnotations() {
		return !annotations.isEmpty();
	}

	/**
	 * Creates an Annotation instance using the given parameters,
	 * then adds to this object's list of Annotation instances.
	 * <p>
	 * If this object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param qName Composed of a namespace, an OPTIONAL prefix, and a local name.
	 * @param literal the literal string
	 * @return the created Annotation instance.
	 * @throws SBOLValidationException 
	 */
	public Annotation createAnnotation(QName qName, String literal) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Creates an Annotation instance using the given parameters,
	 * then adds to this object's list of Annotation instances.
	 * <p>
	 * If this object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param qName Composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param literal literal the literal double
	 * @return the created Annotation instance.
	 * @throws SBOLValidationException 
	 */
	public Annotation createAnnotation(QName qName, double literal) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Creates an Annotation instance using the given parameters,
	 * then adds to this object's list of Annotation instances.
	 * <p>
	 * If this object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param qName Composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param literal literal the literal int
	 * @return the created Annotation instance.
	 * @throws SBOLValidationException 
	 */
	public Annotation createAnnotation(QName qName, int literal) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Creates an Annotation instance using the given parameters,
	 * then adds to this object's list of Annotation instances.
	 * <p>
	 * If this object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param qName Composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param literal the literal boolean
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @return the created Annotation instance.
	 */
	public Annotation createAnnotation(QName qName, boolean literal) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Creates an Annotation instance using the given parameters,
	 * then adds to this object's list of Annotation instances.
	 * <p>
	 * If this object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param qName Composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param literal the literal URI
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 * @return the created Annotation instance.
	 */
	public Annotation createAnnotation(QName qName, URI literal) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Calls the Annotation constructor to create a new instance using the specified parameters,
	 * then adds to the list of Annotation instances owned by this component.
	 * @return the created Annotation instance.
	 * @throws SBOLValidationException 
	 */
	Annotation createAnnotation(NamedProperty<QName> namedProperty) throws SBOLValidationException {
		Annotation annotation = new Annotation(namedProperty);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Creates an Annotation instance with nested annotations using the given parameters,
	 * then adds to this object's list of Annotation instances.
	 * <p>
	 * If this object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param qName Composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param nestedQName A part of NestedAnnotations composed of a namespace, an OPTIONAL prefix, and a local name
	 * @param nestedURI A part of NestedAnnotations used to denote an object that is of type URI
	 * @param annotations A property of NestedAnnotations that contains zero or more Annotation objects that store data in the form of name/value property pairs.
	 * @return the created Annotation instance.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public Annotation createAnnotation(QName qName,QName nestedQName, URI nestedURI, List<Annotation> annotations) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		Annotation annotation = new Annotation(qName, nestedQName, nestedURI, annotations);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Adds the specified instance to the list of structuralAnnotations.
	 * @throws SBOLValidationException 
	 */
	void addAnnotation(Annotation annotation) throws SBOLValidationException {
		if (annotations.contains(annotation)) {
			//throw new SBOLValidationException("Annotation already exists.");
			throw new SBOLValidationException("sbol-10214");
		}
		annotations.add(annotation);
	}

	/**
	 * Removes the given Annotation instance from the list of
	 * Annotation instances.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param annotation The annotation instance using the given {@code qName} and the {@code literal} to be removed
	 * @return {@code true} if the matching Annotation instance is removed successfully,
	 *         {@code false} otherwise.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public boolean removeAnnotation(Annotation annotation) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		return annotations.remove(annotation);
	}

	/**
	 * Returns the list of Annotation instances owned by this object.
	 *
	 * @return the list of Annotation instances owned by this object.
	 */
	public List<Annotation> getAnnotations() {
		List<Annotation> annotations = new ArrayList<>();
		annotations.addAll(this.annotations);
		return annotations;
	}
	
	/**
	 * Returns the Annotation for a given QName.
	 *
	 * @return the Annotation for a given QName.
	 */
	public Annotation getAnnotation(QName qName) {
		for (Annotation annotation : annotations) {
			if (annotation.getQName().equals(qName)) {
				return annotation;
			}
		}
		return null;
	}

	/**
	 * Removes all entries of this object's list of Annotation
	 * objects. The set will be empty after this call returns.
	 * <p>
	 * If this object belongs to an SBOLDocument instance,
	 * then the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant
	 */
	public void clearAnnotations() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		annotations.clear();
	}

	/**
	 * Clears the existing list of structuralAnnotation instances, then appends all of the elements in the specified collection to the end of this list.
	 * @throws SBOLValidationException 
	 */
	void setAnnotations(List<Annotation> annotations) throws SBOLValidationException {
		clearAnnotations();
		for (Annotation annotation : annotations) {
			addAnnotation(annotation);
		}
	}

	/**
	 * Sets the {@code wasDerivedFrom} property to {@code null}.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void unsetWasDerivedFrom() throws SBOLValidationException {
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
	 * @return An identical copy of the specified object
	 */
	protected abstract Identified deepCopy() throws SBOLValidationException;


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
		} else if (!annotations.containsAll(other.annotations))
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
		// TODO: taking this out for now because it causes problems with comparisons with 1.1 converted data
		/*
		if (wasDerivedFrom == null) {
			if (other.wasDerivedFrom != null)
				return false;
		} else if (!wasDerivedFrom.equals(other.wasDerivedFrom))
			return false;
		 */
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
	protected final <I extends Identified> void addChildSafely(I child, Map<URI, I> siblingsMap, String typeName, Map<URI, ? extends Identified> ... maps) throws SBOLValidationException {
		if (isChildURIformCompliant(this.getIdentity(), child.getIdentity())) {
			URI persistentId = URI.create(extractPersistentId(child.getIdentity()));
			if(keyExistsInAnyMap(persistentId, maps)) {
//				throw new SBOLValidationException(
//						"Instance for identity `" + child.identity +
//						"' and persistent identity `" + persistentId + "' exists for a non-" + typeName);
				throw new SBOLValidationException("sbol-10202", child);
				// TODO: (Validation) right rule?
			}
			if(siblingsMap.containsKey(child.getIdentity())) {
//				throw new SBOLValidationException(
//						"Instance for identity `" + child.identity +
//						"' and persistent identity `" + persistentId + "' exists for a " + typeName);
				throw new SBOLValidationException(
						"sbol-10202", child
						);
			}
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
		else { // Only check if URI exists in all maps.
			if(keyExistsInAnyMap(child.getIdentity(), maps))
//				throw new SBOLValidationException(
//						"Instance for identity `" + child.identity +
//						"' exists for a non-" + typeName);
				throw new SBOLValidationException(
						"sbol-10202", child);
			// TODO: (Validation) right rule?
			if(siblingsMap.containsKey(child.getIdentity()))
//				throw new SBOLValidationException(
//						"Instance for identity `" + child.identity + "' exists for a " + typeName);
				throw new SBOLValidationException(
						"sbol-10202", child);
			// TODO: (Validation) right rule?			
			siblingsMap.put(child.getIdentity(), child);
		}

	}

	protected final <I extends Identified> boolean removeChildSafely(Identified identified, Map<URI, I> siblingsMap) {
		Set<Identified> objectsToRemove = new HashSet<>();
		objectsToRemove.add(identified);
		return siblingsMap.values().removeAll(objectsToRemove);
	}

	/**
	 * Test if the name is set.
	 *
	 * @return {@code true} if it is not {@code null}
	 */
	public boolean isSetName() {
		return name != null;
	}

	/**
	 * Returns the name property of this object.
	 *
	 * @return the name property of this object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this object to the specified one.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param name Property is intended to be displayed to a human when visualizing an Identified object.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setName(String name) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		this.name = name;
	}

	/**
	 * Sets the name of this object to {@code null}.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void unsetName() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		name = null;
	}

	/**
	 * Test if the description property is set.
	 *
	 * @return {@code true} if it is not {@code null}
	 */
	public boolean isSetDescription() {
		return description != null;
	}

	/**
	 * Returns the description property of this object.
	 *
	 * @return the description property of this object.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description property of this object to the specified one.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDocument instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param description Property is intended to contain a more thorough text description of an Identified object.
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void setDescription(String description) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		this.description = description;
	}

	/**
	 * Sets the description property to {@code null}.
	 * <p>
	 * If this object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 */
	public void unsetDescription() throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		description = null;
	}

	@Override
	public String toString() {
		return "Identified [identity=" + identity + ", persistentIdentity=" + persistentIdentity
				+ ", version=" + version + ", annotations=" + annotations + ", wasDerivedFrom="
				+ wasDerivedFrom + ", displayId=" + displayId + ", name=" + name + ", description="
				+ description + "]";
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

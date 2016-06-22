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


/**
 * Represents an Identified object in the SBOL data model.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */

public abstract class Identified {

	private URI identity;
	private URI persistentIdentity;
	private String version;
	private List<Annotation> annotations;
	private URI wasDerivedFrom;
	private String displayId;
	private SBOLDocument sbolDocument = null;
	private String name;
	private String description;

	/**
	 * @param identity
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #setIdentity(URI)}
	 */
	Identified(URI identity) throws SBOLValidationException {
		setIdentity(identity);
		this.annotations = new ArrayList<>();
	}

	/**
	 * This copy constructor creates a new Identified instance and copies all of its fields.
	 * 
	 * @param identified the identified instance to be copied
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in any of the following
	 * methods:
	 * <ul>
	 * <li>{@link #setIdentity(URI)},</li>
	 * <li>{@link #setAnnotations(List)},</li>
	 * <li>{@link #setDisplayId(String)},</li>
	 * <li>{@link #setVersion(String)}, or </li>
	 * <li>{@link #setWasDerivedFrom(URI)}.</li>
	 * </ul>
	 */
	Identified(Identified identified) throws SBOLValidationException {
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
	 * Returns the identity URI of this instance.
	 *
	 * @return the identity URI of this instance.
	 */
	public URI getIdentity() {
		return identity;
	}

	/**
	 * Sets the identity URI of this instance. 
	 * 
	 * @param identity the identity URI to set to 
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 10201.
	 */
	final void setIdentity(URI identity) throws SBOLValidationException {
		if (identity == null) {
			throw new SBOLValidationException("sbol-10201", this);
		}
		this.identity = identity;
	}

	/**
	 * Test if the persistent identity URI is set.
	 *
	 * @return {@code true} if it is not {@code null}, or {@code false} otherwise
	 */
	public boolean isSetPersistentIdentity() {
		return persistentIdentity != null;
	}

	/**
	 * Returns the persistent identity URI of this instance.
	 * If its persistent identity is not set, this method returns the identity URI.
	 *
	 * @return the persistent identity URI of this instance if it is set, or the identity URI otherwise
	 */
	public URI getPersistentIdentity() {
		if (isSetPersistentIdentity()) {
			return persistentIdentity;
		}
		return identity;
	}

	/**
	 * Sets persistent identity for this instance. 
	 */
	void setPersistentIdentity(URI persistentIdentity) {
		this.persistentIdentity = persistentIdentity;
	}
	
	// TODO: unsetPersistentIdentity() currently not used, could be useful in future library releases.
//	/**
//	 * Set the persistent identity to {@code null}.
//	 */
//	private void unsetPersistentIdentity() {
//		persistentIdentity = null;
//	}

	/**
	 * Test if the version is set.
	 *
	 * @return {@code true} if it is not {@code null}, or {@code false} otherwise
	 */
	public boolean isSetVersion() {
		return version != null;
	}

	/**
	 * Test if the wasDerivedFrom property is set.
	 *
	 * @return {@code true} if it is not {@code null}, or {@code false} otherwise
	 */
	public boolean isSetWasDerivedFrom() {
		return wasDerivedFrom != null;
	}

	/**
	 * Returns this instance's version.
	 *
	 * @return this instance's version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets version to the given one.
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 10206
	 */
	void setVersion(String version) throws SBOLValidationException {
		if (version==null || version.equals("")) {
			this.version=null;
			return;
		}
		if (!URIcompliance.isVersionValid(version)) {
			throw new SBOLValidationException("sbol-10206",this);
		}
		this.version = version;
	}

	/**
	 * Test if the display ID is set.
	 *
	 * @return {@code true} if it is not {@code null}, or {@code false} otherwise
	 */
	public boolean isSetDisplayId() {
		return displayId != null;
	}

	/**
	 * Returns the display ID of this instance.
	 *
	 * @return the display ID of this instance.
	 */
	public String getDisplayId() {
		return displayId;
	}

	/**
	 * Sets the display ID for this instance.
	 * 
	 * @param displayId the display ID to set to
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 10204
	 */
	void setDisplayId(String displayId) throws SBOLValidationException {
		if (!URIcompliance.isDisplayIdValid(displayId)) {
			throw new SBOLValidationException("sbol-10204",this);
		}
		this.displayId = displayId;
	}

	// TODO: unsetDisplayId() currently not used, could be useful in future library releases.
//	/**
//	 * Set optional the display ID to {@code null}.
//	 */
//	private void unsetDisplayId() {
//		displayId = null;
//	}

	/**
	 * Returns the wasDerivedFrom property of this instance.
	 *
	 * @return the wasDerivedFrom property of this instance.
	 */
	public URI getWasDerivedFrom() {
		return wasDerivedFrom;
	}

	/**
	 * Sets the wasDerivedFrom property of this instance to the given one.
	 *
	 * @param wasDerivedFrom the wasDerivedFrom property to set to 
	 * @throws SBOLValidationException if the following SBOL validation rules was violated: 10303, 10304, 10305. 
	 */
	public void setWasDerivedFrom(URI wasDerivedFrom) throws SBOLValidationException {
		if (sbolDocument!=null) {
			if (!SBOLValidate.checkWasDerivedFromVersion(sbolDocument, this, wasDerivedFrom)) {
				throw new SBOLValidationException("sbol-10305", this);
			}
			SBOLValidate.checkWasDerivedFromCycle(sbolDocument, this, wasDerivedFrom, new HashSet<URI>());
		}
		this.wasDerivedFrom = wasDerivedFrom;
	}

	/**
	 * Test if this instance has any annotations.
	 *
	 * @return {@code true} if this instance has any annotations, or {@code false} otherwise
	 */
	public boolean hasAnnotations() {
		return !annotations.isEmpty();
	}

	/**
	 * Creates an annotation using the given arguments,
	 * then adds to this instance's list of annotations.
	 *
	 * @param qName the QName of the annotation to be created
	 * @param literal the literal string
	 * @return the created annotation
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10401, 10501, 10701, 10801, 10901, 11101, 11201, 11301, 11401, 11501, 11601, 11701, 11801, 11901, 12001, 12101, 12301.
	 */
	public Annotation createAnnotation(QName qName, String literal) throws SBOLValidationException {
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Creates an annotation using the given arguments, 
	 * then adds to this instance's list of annotations.
	 *
	 * @param qName the QName of the annotation to be created
	 * @param literal the double type literal
	 * @return the created annotation 
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10401, 10501, 10701, 10801, 10901, 11101, 11201, 11301, 11401, 11501, 11601, 11701, 11801, 11901, 12001, 12101, 12301.
	 */
	public Annotation createAnnotation(QName qName, double literal) throws SBOLValidationException {
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Creates an annotation using the given arguments,
	 * then adds to this instance's list of annotations.
	 *
	 * @param qName the QName of the annotation to be created
	 * @param literal the integer type literal
	 * @return the created annotation 
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10401, 10501, 10701, 10801, 10901, 11101, 11201, 11301, 11401, 11501, 11601, 11701, 11801, 11901, 12001, 12101, 12301.
	 */
	public Annotation createAnnotation(QName qName, int literal) throws SBOLValidationException {
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Creates an annotation using the given arguments, and
	 * then adds to the list of annotaions.
	 *
	 * @param qName the QName of the annotation to be created
	 * @param literal a boolean type literal
	 * @return the created annotation instance
	 * @throws SBOLValidationException if any of the following SBOL validation rule was violated: 
	 * 10401, 10501, 10701, 10801, 10901, 11101, 11201, 11301, 11401, 11501, 11601, 11701, 11801, 11901, 12001, 12101
	 * 
	 */
	public Annotation createAnnotation(QName qName, boolean literal) throws SBOLValidationException {
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	/**
	 * Creates an annotation using the given arguments,
	 * then adds to this instance's list of annotations.
	 *
	 * @param qName the QName of the annotation to be created
	 * @param literal the literal URI
	 * @return the created annotation
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10401, 10501, 10701, 10801, 10901, 11101, 11201, 11301, 11401, 11501, 11601, 11701, 11801, 11901, 12001, 12101, 12301.
	 * 
	 */
	public Annotation createAnnotation(QName qName, URI literal) throws SBOLValidationException {
		Annotation annotation = new Annotation(qName,literal);
		addAnnotation(annotation);
		return annotation;
	}

	// TODO: createAnnotation(NamedProperty) currently not used, could be useful in future library releases.
//	/**
//	 * Calls the Annotation constructor {@link Annotation#Annotation(NamedProperty)} to create a new annotation using the
//	 * give arguments, and then adds to the list of annotations.
//	 * 
//	 * @return the created annotation
//	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #addAnnotation(Annotation)}.
//	 */
//	private Annotation createAnnotation(NamedProperty<QName> namedProperty) throws SBOLValidationException {
//		Annotation annotation = new Annotation(namedProperty);
//		addAnnotation(annotation);
//		return annotation;
//	}

	/**
	 * Creates an annotation with nested annotations using the given arguments, and then adds to this instance's list of annotations.
	 * 
	 * @param qName the QName of the annotation to be created
	 * @param nestedQName the QName of the nested annotation
	 * @param nestedURI the identity URI for the nested annotation
	 * @param annotations the list of annotations used to construct the nested annotation
	 * @return the created annotation
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated: 
	 * 10401, 10501, 10701, 10801, 10901, 11101, 11201, 11301, 11401, 11501, 11601, 11701, 11801, 11901, 12001, 12101, 12301.
	 */
	public Annotation createAnnotation(QName qName,QName nestedQName, URI nestedURI, List<Annotation> annotations) throws SBOLValidationException {
		Annotation annotation = new Annotation(qName, nestedQName, nestedURI, annotations);
		addAnnotation(annotation);
		return annotation;
	}
	
	private void addNamespace(Annotation annotation) {
		if (sbolDocument==null) return;
		QName qName = annotation.getQName();
		QName qNameInNamespace = sbolDocument.getNamespace(URI.create(qName.getNamespaceURI()));
		if (qNameInNamespace==null || qName.getPrefix()!=qNameInNamespace.getPrefix()) {
			sbolDocument.addNamespace(URI.create(qName.getNamespaceURI()), qName.getPrefix());
		}
		if (annotation.isNestedAnnotations()) {
			qName = annotation.getNestedQName();
			qNameInNamespace = sbolDocument.getNamespace(URI.create(qName.getNamespaceURI()));
			if (qNameInNamespace==null || qName.getPrefix()!=qNameInNamespace.getPrefix()) {
				sbolDocument.addNamespace(URI.create(qName.getNamespaceURI()), qName.getPrefix());
			}
			for (Annotation nestedAnnotation : annotation.getAnnotations()) {
				addNamespace(nestedAnnotation);
			}
		}
	}

	/**
	 * Adds the given annotation to the list of annotations.
	 * 
	 * @throws SBOLValidationException if any of the following SBOL validation rules was violated:
	 * 10401, 10501, 10701, 10801, 10901, 11101, 11201, 11301, 11401, 11501, 11601, 11701, 11801, 11901,
	 * 12001, 12101, 12301.
	 */
	void addAnnotation(Annotation annotation) throws SBOLValidationException {
		if (annotations.contains(annotation)) {
			return;
		}
		addNamespace(annotation);
		if (annotation.getQName().getNamespaceURI().equals(Sbol2Terms.sbol2.getNamespaceURI())) {
			if (this instanceof Sequence) {
				throw new SBOLValidationException("sbol-10401");
			} else if (this instanceof ComponentDefinition) {
				throw new SBOLValidationException("sbol-10501");
			} else if (this instanceof Component) {
				throw new SBOLValidationException("sbol-10701");
			} else if (this instanceof MapsTo) {
				throw new SBOLValidationException("sbol-10801");
			} else if (this instanceof SequenceAnnotation) {
				throw new SBOLValidationException("sbol-10901");
			} else if (this instanceof Range) {
				throw new SBOLValidationException("sbol-11101");
			} else if (this instanceof Cut) {
				throw new SBOLValidationException("sbol-11201");
			} else if (this instanceof GenericLocation) {
				throw new SBOLValidationException("sbol-11301");
			} else if (this instanceof SequenceConstraint) {
				throw new SBOLValidationException("sbol-11401");
			} else if (this instanceof Model) {
				throw new SBOLValidationException("sbol-11501");
			} else if (this instanceof ModuleDefinition) {
				throw new SBOLValidationException("sbol-11601");
			} else if (this instanceof Module) {
				throw new SBOLValidationException("sbol-11701");
			} else if (this instanceof FunctionalComponent) {
				throw new SBOLValidationException("sbol-11801");
			} else if (this instanceof Interaction) {
				throw new SBOLValidationException("sbol-11901");
			} else if (this instanceof Participation) {
				throw new SBOLValidationException("sbol-12001");
			} else if (this instanceof Collection) {
				throw new SBOLValidationException("sbol-12101");
			} else if (this instanceof GenericTopLevel) {
				throw new SBOLValidationException("sbol-12301");
			}
		}
		annotations.add(annotation);
	}

	/**
	 * Removes the given annotation from the list of annotations owned by this instance.
	 *
	 * @param annotation the annotation to be removed
	 * @return {@code true} if the matching annotation was removed successfully,
	 *         or {@code false} otherwise.
	 */
	public boolean removeAnnotation(Annotation annotation) {
		return annotations.remove(annotation);
	}

	/**
	 * Returns the list of annotations owned by this instance.
	 *
	 * @return the list of annotations owned by this instance.
	 */
	public List<Annotation> getAnnotations() {
		List<Annotation> annotations = new ArrayList<>();
		annotations.addAll(this.annotations);
		return annotations;
	}

	/**
	 * Returns the annotation matching the given QName.
	 * @param qName the QName of the annotation to be retrieved
	 * @return the annotation matching the given QName
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
	 * Removes all entries of this instance's list of annotations.
	 * The set will be empty after this call returns.
	 */
	public void clearAnnotations() {
		annotations.clear();
	}

	/**
	 * Clears the existing list of annotations, then adds the given list of annotations.
	 * 
	 * @param annotations
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in {@link #addAnnotation(Annotation)}.
	 */
	void setAnnotations(List<Annotation> annotations) throws SBOLValidationException {
		clearAnnotations();
		for (Annotation annotation : annotations) {
			addAnnotation(annotation);
		}
	}

	/**
	 * Sets the wasDerivedFrom property to {@code null}.
	 *
	 */
	public void unsetWasDerivedFrom() {
		wasDerivedFrom = null;
	}

	/**
	 * Sets the associated SBOLDocument instance to the given one. 
	 * @param sbolDocument
	 */
	void setSBOLDocument(SBOLDocument sbolDocument) {
		this.sbolDocument = sbolDocument;
	}

	SBOLDocument getSBOLDocument() {
		return sbolDocument;
	}

	/**
	 * Provides a deep copy of this instance.
	 * @return An identical copy of the specified object.
	 * @throws SBOLValidationException if an SBOL validation rule was violated.
	 */
	abstract Identified deepCopy() throws SBOLValidationException;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annotations == null) ? 0 : annotations.hashCode());
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		result = prime * result	+ ((persistentIdentity == null) ? 0 : persistentIdentity.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		// wasDerivedFrom differences are not considered
		//result = prime * result + ((wasDerivedFrom == null) ? 0 : wasDerivedFrom.hashCode());
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
		// wasDerivedFrom differences are not considered differences
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

	/**
	 * @param child
	 * @param siblingsMap
	 * @param typeName
	 * @param maps
	 * @throws SBOLValidationException if the following SBOL validation rule is violated: 10202.
	 */
	@SafeVarargs
	final <I extends Identified> void addChildSafely(I child, Map<URI, I> siblingsMap, String typeName, Map<URI, ? extends Identified> ... maps) throws SBOLValidationException {
		if (isChildURIformCompliant(this.getIdentity(), child.getIdentity())) {
			URI persistentId = URI.create(extractPersistentId(child.getIdentity()));
			if(keyExistsInAnyMap(persistentId, maps)) {
				throw new SBOLValidationException("sbol-10202", child);
			}
			if(siblingsMap.containsKey(child.getIdentity())) {
				throw new SBOLValidationException("sbol-10202", child);
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
				throw new SBOLValidationException("sbol-10202", child);
			if(siblingsMap.containsKey(child.getIdentity()))
				throw new SBOLValidationException("sbol-10202", child);
			siblingsMap.put(child.getIdentity(), child);
		}

	}

	final <I extends Identified> boolean removeChildSafely(Identified identified, Map<URI, I> siblingsMap) {
		Set<Identified> objectsToRemove = new HashSet<>();
		objectsToRemove.add(identified);
		return siblingsMap.values().removeAll(objectsToRemove);
	}

	/**
	 * Test if the name property is set.
	 *
	 * @return {@code true} if it is not {@code null}, or {@code false} otherwise
	 */
	public boolean isSetName() {
		return name != null;
	}

	/**
	 * Returns the name property of this instance.
	 *
	 * @return the name property of this instance.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name property for this instance.
	 *
	 * @param name the name property to set to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the name of this instance to {@code null}.
	 */
	public void unsetName() {
		name = null;
	}

	/**
	 * Test if the description property is set.
	 *
	 * @return {@code true} if it is not {@code null}, or {@code false} otherwise
	 */
	public boolean isSetDescription() {
		return description != null;
	}

	/**
	 * Returns the description property of this instance.
	 *
	 * @return the description property of this instance.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description property for this instance.
	 *
	 * @param description the description to set to
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the description property to {@code null}.
	 *
	 */
	public void unsetDescription() {
		description = null;
	}

	@Override
	public String toString() {
		return  "identity=" + identity 
				+ (this.isSetPersistentIdentity()?", persistentIdentity=" + persistentIdentity:"")
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetVersion()?", version=" + version:"")
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ (annotations.size()>0?", annotations=" + annotations:"") 
				+ (this.isSetWasDerivedFrom()?", wasDerivedFrom=" + wasDerivedFrom:""); 
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
	//	 * Sets the identifier for this instance.
	//	 * @param value
	//	 * @deprecated As of release 2.0, URI can only be set when an Identified instance is created.
	//	 */
	//	public void setURI(URI value) {
	//		this.identity = value;
	//	}

}

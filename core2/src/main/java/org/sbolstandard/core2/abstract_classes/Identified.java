package org.sbolstandard.core2.abstract_classes;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.Annotation;
import org.sbolstandard.core2.Turtle;

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
	//	private Integer majorVersion;
	//	private Integer minorVersion;

	private List<Annotation> annotations;
	private URI wasDerivedFrom;

	public Identified(URI identity) {
		setIdentity(identity);
		//this.timeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		this.annotations = new ArrayList<Annotation>();
		String identityStr = identity.toString();
		if (isURIcompliant(identityStr)) {
			// URI = authority/id/majorVersion/minorVersion
			//String minorVersion = identityStr.substring(identityStr.lastIndexOf('/') + 1, identityStr.length());
			// TODO: extract major and minor versions
		}
		// else

	}

	/**
	 * This copy constructor creates a new {@link Identified} class and copies all fields specified by the <code>identified</code> object.
	 * @param identified
	 */
	protected Identified(Identified identified) {
		//this.setIdentity(URI.create(identified.getIdentity().toString()));
		this.setIdentity(identified.getIdentity());
		if (identified.isSetAnnotations()) {
			List<Annotation> clonedAnnotations = new ArrayList<Annotation>();
			for (Annotation annotation : identified.getAnnotations()) {
				clonedAnnotations.add(annotation.deepCopy());
			}
			this.setAnnotations(clonedAnnotations);
		}
		if (identified.isSetVersion()) {
			this.setVersion(identified.getVersion());
		}
		if (identified.isSetPersistentIdentity()) {
			this.setPersistentIdentity(URI.create(identified.getPersistentIdentity().toString()));
		}
		//this.setTimeStamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
		if (identified.isSetWasDerivedFrom()) {
			this.setWasDerivedFrom(URI.create(identified.getWasDerivedFrom().toString()));
		}
	}

	public Identified (String URIprefix, String id, String version) {
		setIdentity(URI.create(URIprefix.trim() + '/' + id.trim() + '/' + version));
		this.annotations = new ArrayList<Annotation>();
		this.setPersistentIdentity(URI.create(URIprefix.trim() + '/' + id.trim()));
		this.setVersion(version);
	}

	public static boolean isURIcompliant(String identity) {
		// TODO Check URI compliance
		return true;
	}


	/**
	 * Returns field variable <code>identity</code>.
	 * @return field variable <code>identity</code>
	 */
	public URI getIdentity() {
		return identity;
	}

	/**
	 * Sets field variable <code>identity</code> to the specified element.
	 * @param identity
	 */
	public final void setIdentity(URI identity) {
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


	//	public boolean isSetMajorVersion() {
	//		if (majorVersion == null)
	//			return false;
	//		else
	//			return true;
	//	}
	//
	//	public boolean isSetMinorVersion() {
	//		if (minorVersion == null)
	//			return false;
	//		else
	//			return true;
	//	}


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
		// TODO: Require version to be "[0-9]+.[0-9]+".
		this.version = version;
	}

	//	/**
	//	 * Returns the major version.
	// 	 * @return the major version
	//	 */
	//	public Integer getMajorVersion() {
	//		return majorVersion;
	//	}
	//
	//	/**
	//	 * Sets field variable <code>majorVersion</code> to the specified value.
	//	 * @param majorVersion
	//	 */
	//	public void setMajorVersion(Integer majorVersion) {
	//		this.majorVersion = majorVersion;
	//		// TODO: Update URI?
	//	}
	//
	//	/**
	//	 * Returns the minor version.
	//	 * @return the minor version
	//	 */
	//	public Integer getMinorVersion() {
	//		return minorVersion;
	//		// TODO: Update URI?
	//	}


	public URI getWasDerivedFrom() {
		return wasDerivedFrom;
	}


	//	/**
	//	 * Sets field variable <code>minorVersion</code> to the specified value.
	//	 * @param minorVersion
	//	 */
	//	public void setMinorVersion(Integer minorVersion) {
	//		this.minorVersion = minorVersion;
	//	}


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
	public Annotation createAnnotation(QName relation, Turtle literal) {
		Annotation annotation = new Annotation(relation, literal);
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


	//	@Override
	//	public int hashCode() {
	//		final int prime = 31;
	//		int result = 1;
	//		result = prime * result + ((annotations == null) ? 0 : annotations.hashCode());
	//		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
	//		result = prime * result + majorVersion;
	//		result = prime * result + minorVersion;
	//		result = prime * result
	//				+ ((persistentIdentity == null) ? 0 : persistentIdentity.hashCode());
	//		result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
	//		return result;
	//	}
	//
	//	@Override
	//	public boolean equals(Object obj) {
	//		if (this == obj)
	//			return true;
	//		if (obj == null)
	//			return false;
	//		if (getClass() != obj.getClass())
	//			return false;
	//		Identified other = (Identified) obj;
	//		if (annotations == null) {
	//			if (other.annotations != null)
	//				return false;
	//		} else if (!annotations.equals(other.annotations))
	//			return false;
	//		if (identity == null) {
	//			if (other.identity != null)
	//				return false;
	//		} else if (!identity.equals(other.identity))
	//			return false;
	//		if (majorVersion != other.majorVersion)
	//			return false;
	//		if (minorVersion != other.minorVersion)
	//			return false;
	//		if (persistentIdentity == null) {
	//			if (other.persistentIdentity != null)
	//				return false;
	//		} else if (!persistentIdentity.equals(other.persistentIdentity))
	//			return false;
	//		if (timeStamp == null) {
	//			if (other.timeStamp != null)
	//				return false;
	//		} else if (!timeStamp.equals(other.timeStamp))
	//			return false;
	//		return true;
	//	}

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

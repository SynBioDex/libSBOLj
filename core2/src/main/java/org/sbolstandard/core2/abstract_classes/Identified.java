package org.sbolstandard.core2.abstract_classes;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.sbolstandard.core2.Annotation;

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
	private Timestamp timeStamp;
	private List<Annotation> annotations;
	
	public Identified(URI identity) {
		setIdentity(identity);
		this.timeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		this.annotations = new ArrayList<Annotation>();
	}
	
	/**
	 * Check whether optional field variable <code>persistentIdentity</code> is set or not.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetPersistentIdentity() {
		if (persistentIdentity == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Check whether optional field variable <code>version</code> is set or not.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetVersion() {
		if (version == null)
			return false;
		else
			return true;					
	}
		
	/**
	 * Check whether optional field variable <code>timeStamp</code> is set or not.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetTimeStamp() {
		if (timeStamp == null)
			return false;
		else
			return true;
	}
 	
	/**
	 * Check whether optional field variable <code>annotations</code> is set or not.
	 * @return <code>true</code> if it is not an empty list
	 */
	public boolean isSetAnnotations() {
		if (annotations.isEmpty())
			return false;
		else
			return true;					
	}
	
	/**
	 * Set optional field variable <code>persistentIdentity</code> to null.
	 */
	public void unsetPersistentIdentity() {
		persistentIdentity = null;
	}
	
	/**
	 * Set optional field variable <code>version</code> to null.
	 */
	public void unsetVersion() {
		version = null;
	}
	
	/**
	 * Set optional field variable <code>timeStamp</code> to null.
	 */
	public void unsetTimeStamp() {
		timeStamp = null;
	}
	
	/**
	 * Set optional field variable <code>annotations</code> to null.
	 */
	public void unsetAnnotations() {
		annotations = null;
	}

	public URI getIdentity() {
		return identity;
	}

	public void setIdentity(URI identity) {
		this.identity = identity;
	}

	public URI getPersistentIdentity() {
		return persistentIdentity;
	}

	public void setPersistentIdentity(URI persistentIdentity) {
		this.persistentIdentity = persistentIdentity;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
	
	
//	public Identified() {
//		
//	}

//	private URI identity;
//	private String annotation;
//
//	/**
//	 * 
//	 * @param identity an identity for the identified object
//	 */
//	public Identified(URI identity) {
//		this.identity = identity;
//	}
//	
//	public Identified() {		
//	}
//
//	/**
//	 * 
//	 * @return the identified object's identity
//	 */
//	public URI getIdentity() {
//		return identity;
//	}
//	
//	/**
//	 * @return
//	 * @deprecated As of release 2.0, replaced by {@link #getIdentity()}
//	 */
//	public URI getURI() {
//		return identity;
//	}
//
//	/**
//	 * 
//	 * @return the identified object's annotation
//	 */
//	public String getAnnotation() {
//		return annotation;
//	}
//
//	/**
//	 * 
//	 * @param annotation an annotation for the identified object
//	 */
//	public void setAnnotation(String annotation) {
//		this.annotation = annotation;
//	}
//	
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

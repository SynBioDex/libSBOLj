package org.sbolstandard.core2.abstract_classes;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.Annotation;
import org.sbolstandard.core2.Module;
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
	private Timestamp timeStamp;
	private List<Annotation> annotations;
	
	public Identified(URI identity) {
		setIdentity(identity);
		this.timeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		this.annotations = new ArrayList<Annotation>();
		String identityStr = identity.toString();
		if (isURIcompliant(identityStr)) {
			// URI = authority/id/majorVersion/minorVersion
			String minorVersion = identityStr.substring(identityStr.lastIndexOf('/') + 1, identityStr.length());
			// TODO: extract major version
		}
		// else
			
		
		
	}
	
	private boolean isURIcompliant(String identity) {
		// TODO Check URI compliance
		return true;
	}

	public Identified(String authority, String id) {
		//this(URI.create(authority.trim() + '/' + id.trim() + "/1/0"));
		setIdentity(identity);
		this.timeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		this.annotations = new ArrayList<Annotation>();
		this.setPersistentIdentity(URI.create(authority.trim() + '/' + id.trim()));
		this.setVersion("1.0");
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
	
	/**
	 * Returns the major version if the <code>version</code> field not <code>null</code> and is compliant. 
	 * Otherwise returns -1.
	 * @return the major version
	 */
	public int getMajorVersion() {
		// TODO: Need to check version compliance first.
		if (version != null) {
			return Integer.parseInt(version.substring(0, version.indexOf('.')-1));
		}
		else
			return -1;
	}
	
	/**
	 * Returns the minor version if the <code>version</code> field not <code>null</code> and is compliant. 
	 * Otherwise returns -1.
	 * @return the minor version
	 */
	public int getMinorVersion() {
		// TODO: Need to check version compliance first.
		if (version != null) {
			return Integer.parseInt(version.substring(version.indexOf('.'), version.length()));
		}
		else
			return -1;
	}
	
	/**
	 * Set optional field variable <code>version</code> to <code>null</code>.
	 */
	public void unsetVersion() {
		version = null;
	}
		
	/**
	 * Test if optional field variable <code>timeStamp</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetTimeStamp() {
		if (timeStamp == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Returns field variable <code>timeStamp</code>.
	 * @return field variable <code>timeStamp</code>
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * Sets field variable <code>timeStamp</code> to the specified element. 
	 * @param timeStamp
	 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * Set optional field variable <code>timeStamp</code> to <code>null</code>.
	 */
	public void unsetTimeStamp() {
		timeStamp = null;
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
	 * @param structuralAnnotationURI
	 * @return the matching instance if present, or <code>null</code> if not present.
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
//	
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

package org.sbolstandard.core2;

import java.net.URI;

public class Sequence extends TopLevel{
		
	private String elements;
	private URI encoding;
	
	public Sequence(URI identity, String elements, URI encoding) {
		super(identity);
		setElements(elements);
		setEncoding(encoding);
	}
	
	public Sequence(String authority, String Id, String elements, URI encoding) {
		super(authority, Id);
		setElements(elements);
		setEncoding(encoding);
	}
	
	/**
	 * Returns field variable <code>elements</code>.
	 * @return field variable <code>elements</code>
	 */
	public String getElements() {
		return elements;
	}
	
	/**
	 * Sets field variable <code>elements</code> to the specified element.
	 * @param elements
	 */
	public void setElements(String elements) {
		this.elements = elements;
	}
	
	/**
	 * Returns field variable <code>encoding</code>.
	 * @return field variable <code>encoding</code>
	 */
	public URI getEncoding() {
		return encoding;
	}
	
	/**
	 * Sets field variable <code>encoding</code> to the specified element.
	 * @param encoding
	 */
	public void setEncoding(URI encoding) {
		this.encoding = encoding;
	}
	
	/**
	 * @param sequence
	 * @return
	 */
	public boolean equals(Sequence sequence) {
		// TODO fill in the content
		return false;
	}
	
	/**
	 * Replace the minor version in the object's URI with the specified one, and make the same replacement for all of its children objects.
	 * @param minorVersion
	 */
	public void setMinorVersion(int minorVersion) {
		// TODO fill in
		
	}
	
	/**
	 * Replace the minor version in the object's URI with the specified one, and make the same replacement for all of its children objects.
	 * @param minorVersion
	 */
	public void setMajorVersion(int majorVersion) {
		// TODO fill in
	}
	
	/**
	 * Replace the display ID in the object's URI with the specified one, and make the same replacement for all of its children objects.
	 * @param id
	 */
	public void setDisplayId(String id) {
		// TODO fill in
	}
	
	/**
	 * Replace the display ID in the URI of the object's parent with the specified one.  
	 * @param id
	 */
	public void setParentDisplayId(String id) {
		// TODO fill in
	}
	
	/**
	 * Replace the display ID in the URI of the object's grand parent (2 levels up) with the specified one.
	 * @param id
	 */
	public void setGrandParentDisplayId(String id) {
		// TODO fill in
	}
	
	/**
	 * Replace the authority in the object's URI with the specified one, and make the same replacement for all of its children objects.
	 * @param authority
	 */
	public void setAuthority(String authority) {
		// TODO Need to change the parent's authority?
	}
	
	/**
	 * Provide a deep copy of this object.
	 */
	public Sequence clone() {
		// TODO deal with visibility of this method. 
		return null;
	}
	
	/**
	 * Clone the object first, set its display ID to the specified value, and set the major version to "1" and minor version to "0".
	 * @param id
	 * @return the copied object
	 */
	public Sequence copy(String id) {
		// TODO fill in
		return null;
		
	}
	
	/**
	 * Clone the object first, and set its major version to the specified value, and minor version to "0". 
	 * @param majorVersion
	 * @return
	 */
	public Sequence newMajorVersion(int majorVersion) {
		// TODO fill in
		return null;
	}
	
	/**
	 * Clone the object first, and set its minor version to the specified value.
	 * @param minorVersion
	 * @return
	 */
	public Sequence newMinorVersion(int minorVersion) {
		// TODO fill in
		return null;
	}
	
}

package org.sbolstandard.core2;

import java.net.URI;
import static org.sbolstandard.core2.util.URIcompliance.*;

import org.sbolstandard.core2.abstract_classes.TopLevel;

public class Sequence extends TopLevel{
		
	private String elements;
	private URI encoding;
	
	public Sequence(URI identity, String elements, URI encoding) {
		super(identity);
		setElements(elements);
		setEncoding(encoding);
	}
	
	private Sequence(Sequence sequence) {
		super(sequence.getIdentity());
		this.setElements(sequence.getElements());
		this.setEncoding(sequence.getEncoding());
	}
	
//	public Sequence(String authority, String Id, String elements, URI encoding) {
//		super(authority, Id);
//		setElements(elements);
//		setEncoding(encoding);
//	}
	
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
	
//	/**
//	 * Replace the authority in the object's URI with the specified one, and make the same replacement for all of its children objects.
//	 * @param authority
//	 */
//	public void setAuthority(String authority) {
//		// TODO Need to change the parent's authority?
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((elements == null) ? 0 : elements.hashCode());
		result = prime * result + ((encoding == null) ? 0 : encoding.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sequence other = (Sequence) obj;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		if (encoding == null) {
			if (other.encoding != null)
				return false;
		} else if (!encoding.equals(other.encoding))
			return false;
		return true;
	}

	@Override
	protected Sequence deepCopy() {
		return new Sequence(this);
	}
	
	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Sequence copy(String URIprefix, String displayId, String version) {
		if (this.checkDescendantsURIcompliance() && isURIprefixCompliant(URIprefix)
				&& isDisplayIdCompliant(displayId) && isVersionCompliant(version)) {
			Sequence cloned = this.deepCopy();
			cloned.setWasDerivedFrom(this.getIdentity());		
			cloned.setDisplayId(displayId);
			cloned.setVersion(version);
			URI newIdentity = URI.create(URIprefix + '/' + displayId + '/' + version);			
			cloned.setIdentity(newIdentity);
			return cloned;
		}
		else {
			return null; 	
		}
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#checkDescendantsURIcompliance()
	 */
	@Override
	protected boolean checkDescendantsURIcompliance() {
		if (!isTopLevelURIcompliant(this.getIdentity())) {
			return false;
		}
		return true;
	}

	
//	/**
//	 * @param newDisplayId
//	 * @return
//	 */
//	public Sequence copy(String newDisplayId) {
//		Sequence cloned = (Sequence) super.copy(newDisplayId);
//		cloned.updateCompliantURI(newDisplayId);
//		return cloned;
//	}
//	
//	/**
//	 * Get a deep copy of the object first, and set its major version to the specified value, and minor version to "0". 
//	 * @param newVersion
//	 * @return the copied {@link ComponentDefinition} instance with the specified major version.
//	 */
//	public Sequence newVersion(String newVersion) {
//		Sequence cloned = (Sequence) super.newVersion(newVersion);		
//		cloned.updateVersion(newVersion);
//		return cloned;
//	}
	
//	/* (non-Javadoc)
//	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateVersion(java.lang.String)
//	 */
//	public void updateVersion(String newVersion) {
//		super.updateVersion(newVersion);
//		if (isURIcompliant(this.getIdentity())) {			
//		}
//	}
}

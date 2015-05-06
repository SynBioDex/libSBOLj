package org.sbolstandard.core2;

import java.net.URI;

import static org.sbolstandard.core2.URIcompliance.*;

import javax.xml.namespace.QName;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
 */

public class GenericTopLevel extends TopLevel{

	private QName rdfType;
	
	GenericTopLevel(URI identity, QName rdfType) {
		super(identity);
		this.rdfType = rdfType;		
	}
	
	private GenericTopLevel(GenericTopLevel genericTopLevel) {
		super(genericTopLevel);
		this.setRDFType(genericTopLevel.getRDFType());
	}
	
	public QName getRDFType() {
		return rdfType;
	}

	public void setRDFType(QName rdfType) {
		this.rdfType = rdfType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rdfType == null) ? 0 : rdfType.hashCode());
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
		GenericTopLevel other = (GenericTopLevel) obj;
		if (rdfType == null) {
			if (other.rdfType != null)
				return false;
		} else if (!rdfType.equals(other.rdfType))
			return false;
		return true;
	}

	@Override
	protected GenericTopLevel deepCopy() {
		return new GenericTopLevel(this);
	}
	
//	/**
//	 * @param newDisplayId
//	 * @return
//	 */
//	public GenericTopLevel copy(String newDisplayId) {
//		GenericTopLevel cloned = (GenericTopLevel) this.deepCopy();		
//		cloned.updateCompliantURI(newDisplayId);
//		return cloned;
//	}
//	
//	/**
//	 * Get a deep copy of the object first, and set its major version to the specified value, and minor version to "0". 
//	 * @param newVersion
//	 * @return the copied {@link ComponentDefinition} instance with the specified major version.
//	 */
//	public GenericTopLevel newVersion(String newVersion) {
//		GenericTopLevel cloned = (GenericTopLevel) super.newVersion(newVersion);		
//		cloned.updateVersion(newVersion);
//		return cloned;
//	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	GenericTopLevel copy(String URIprefix, String displayId, String version) {
		if (this.checkDescendantsURIcompliance() && isURIprefixCompliant(URIprefix)
				&& isDisplayIdCompliant(displayId) && isVersionCompliant(version)) {
			GenericTopLevel cloned = this.deepCopy();
			cloned.setWasDerivedFrom(this.getIdentity());
			cloned.setPersistentIdentity(URI.create(URIprefix + '/' + displayId));
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
		return isURIcompliant(this.getIdentity(), 0);
	}

}

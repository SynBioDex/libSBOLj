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
		if (rdfType.getPrefix().toString().equals("sbol")) {
			throw new SBOLValidationException(rdfType.getLocalPart()+" is not an SBOL object, so it cannot be in the SBOL namespace.");
		}
	}
	
	private GenericTopLevel(GenericTopLevel genericTopLevel) {
		super(genericTopLevel);
		this.setRDFType(genericTopLevel.getRDFType());
	}

	/**
	 * Returns the RDF type property of this GenericTopLevel object.
	 * 
	 * @return the RDF type property of this GenericTopLevel object.
	 */
	public QName getRDFType() {
		return rdfType;
	}

	/**
	 * Set the RDF type property of this GenericTopLevel object to the specified one.
	 * <p>
	 * If this ComponentDefinition object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param rdfType
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws IllegalArgumentException if the given {@code rdfType} argument is {@code null}
	 */
	public void setRDFType(QName rdfType) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (rdfType == null) {
			throw new IllegalArgumentException("RDF type is a required field.");
		}
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
		GenericTopLevel cloned = this.deepCopy();
		cloned.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		cloned.setDisplayId(displayId);
		cloned.setVersion(version);
		URI newIdentity = createCompliantURI(URIprefix,displayId,version);	
		if (!this.getIdentity().equals(newIdentity)) {
			cloned.setWasDerivedFrom(this.getIdentity());
		} else {
			cloned.setWasDerivedFrom(this.getWasDerivedFrom());
		}
		cloned.setIdentity(newIdentity);
		return cloned;
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#checkDescendantsURIcompliance()
	 */
	@Override
	protected boolean checkDescendantsURIcompliance() {
		return isTopLevelURIformCompliant(this.getIdentity());
	}

}

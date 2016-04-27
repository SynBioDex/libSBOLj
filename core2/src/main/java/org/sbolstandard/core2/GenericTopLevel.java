package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

import java.net.URI;

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

	GenericTopLevel(URI identity, QName rdfType) throws SBOLValidationException {
		super(identity);
		this.rdfType = rdfType;
		if (rdfType.getNamespaceURI().equals(Sbol2Terms.sbol2.getNamespaceURI())/* ||
				rdfType.getNamespaceURI().equals(Sbol1Terms.sbol1.getNamespaceURI())*/) {
			throw new SBOLValidationException(rdfType.getLocalPart()+" is not an SBOL object, so it cannot be in the SBOL namespace.");
			// TODO: (Validation) missing rule: QName is neither an SBOL 1 nor SBOL 2 namespace.
		}
	}

	private GenericTopLevel(GenericTopLevel genericTopLevel) throws SBOLValidationException {
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
	 * @param rdfType the RDF type property of this object
	 * @throws SBOLValidationException if the given {@code rdfType} argument is {@code null}
	 */
	public void setRDFType(QName rdfType) throws SBOLValidationException {
		if (rdfType == null) {
			throw new SBOLValidationException("sbol-12302", this);
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
	protected GenericTopLevel deepCopy() throws SBOLValidationException {
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
	GenericTopLevel copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
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
	protected void checkDescendantsURIcompliance() throws SBOLValidationException {
		URIcompliance.isTopLevelURIformCompliant(this.getIdentity());
	}

	@Override
	public String toString() {
		return "GenericTopLevel ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ ", rdfType=" + rdfType 
				+ "]";
	}

}

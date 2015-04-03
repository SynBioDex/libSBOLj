package org.sbolstandard.core2;

import java.net.URI;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.abstract_classes.Documented;
import org.sbolstandard.core2.abstract_classes.TopLevel;

public class GenericTopLevel extends TopLevel{

	private QName rdfType;
	
	public GenericTopLevel(URI identity, QName rdfType) {
		super(identity);
		this.rdfType = rdfType;		
	}
	
	private GenericTopLevel(GenericTopLevel genericTopLevel) {
		super(genericTopLevel);
		this.setRdfType(genericTopLevel.getRdfType());
	}
	
	public QName getRdfType() {
		return rdfType;
	}

	public void setRdfType(QName rdfType) {
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
	
	/**
	 * @param newDisplayId
	 * @return
	 */
	public GenericTopLevel copy(String newDisplayId) {
		GenericTopLevel cloned = (GenericTopLevel) this.deepCopy();		
		cloned.updateDisplayId(newDisplayId);
		return cloned;
	}
	
	/**
	 * Get a deep copy of the object first, and set its major version to the specified value, and minor version to "0". 
	 * @param newVersion
	 * @return the copied {@link ComponentDefinition} instance with the specified major version.
	 */
	public GenericTopLevel newVersion(String newVersion) {
		GenericTopLevel cloned = (GenericTopLevel) super.newVersion(newVersion);		
		cloned.updateVersion(newVersion);
		return cloned;
	}

}

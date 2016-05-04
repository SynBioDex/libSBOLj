package org.sbolstandard.core2;

import java.net.URI;

/**
 * @author Zhen Zhang
 * @author Chris Myers
 * @version 2.1
 */

public enum AccessType {
	/**
	 * The "public" access type indicates that the ComponentInstance MAY be referred to by remote references in MapsTo objects.
	 */
	PUBLIC("public"), 
	/**
	 * The "private" access type indicates that the ComponentInstance MUST NOT be referred to by remote references in MapsTo objects.
	 */
	PRIVATE("private");
	private final String accessType;

	AccessType(String accessType) {
		this.accessType = accessType;
	}

	/**
	 * Convert the specified URI to its corresponding AccessType instance.
	 * @return the corresponding AccessType instance
	 */
	static AccessType convertToAccessType(URI access) throws SBOLValidationException {
		if (access!=null) {
			if (access.equals(publicURI)) {
				return AccessType.PUBLIC;
			} else if (access.equals(privateURI)) {
				return AccessType.PRIVATE;
			}
			else {
				throw new SBOLValidationException("sbol-10607");
			}
		} else {
			throw new SBOLValidationException("sbol-10607");
		}
	}
	
	/**
	 * Returns the access type in URI.
	 * @return access type in URI
	 */
	static URI convertToURI(AccessType access) {
		if (access != null) {
			if (access.equals(AccessType.PUBLIC)) {
				return publicURI;
			}
			else if (access.equals(AccessType.PRIVATE)) {
				return privateURI;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the access type.
	 * @return access type.
	 */
	String getAccessType() {
		return accessType;
	}

	@Override
	public String toString() {
		return accessType;
	}

	static final URI publicURI = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "public");
	static final URI privateURI = URI.create(Sbol2Terms.sbol2.getNamespaceURI() + "private");
}
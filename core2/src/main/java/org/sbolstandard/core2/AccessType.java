package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.ComponentInstance.Access;

public enum AccessType {
	PUBLIC("public"), PRIVATE("private");
	private final String accessType;

	AccessType(String accessType) {
		this.accessType = accessType;
	}

	/**
	 * Convert the specified URI to its corresponding AccessType instance.
	 * @return the corresponding AccessType instance
	 */
	static AccessType convertToAccessType(URI access) {
		if (access.equals(Access.PUBLIC)) {
			return AccessType.PUBLIC;
		} else if (access.equals(Access.PRIVATE)) {
			return AccessType.PRIVATE;
		}
		else {
			throw new IllegalArgumentException("Unknown access URI `" + access + "'");
		}
	}
	
	/**
	 * Returns the access type in URI.
	 * @return access type in URI
	 */
	static URI convertToURI(AccessType access) {
		if (access != null) {
			if (access.equals(AccessType.PUBLIC)) {
				return Access.PUBLIC;
			}
			else if (access.equals(AccessType.PRIVATE)) {
				return Access.PRIVATE;
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
	
	private static final class Access {
		public static final URI PUBLIC = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "public");
		public static final URI PRIVATE = URI.create(Sbol2Terms.sbol2
				.getNamespaceURI() + "private");
	}
}
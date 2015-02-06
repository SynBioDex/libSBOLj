package org.sbolstandard.core2;

import java.net.URI;


public enum AccessType {

	PUBLIC("public"), PRIVATE("private");

	// private final String accessType;

	private String accessType;

	private AccessType(String accessType) {

		setAccessType(accessType);

	}

	private AccessType(URI accessType) {

		setAccessType(accessType);

	}

	/**
	 * 
	 * Sets the appropriate access type that matches the one in the specified
	 * 
	 * URI.
	 * 
	 * 
	 * 
	 * @param accessType
	 */

	private void setAccessType(URI accessType) {

		if (accessType.equals(Access.PUBLIC)) {

			setAccessType("public");

		} else if (accessType.equals(Access.PRIVATE)) {

			setAccessType("private");

		}

	}

	/**
	 * 
	 * Sets the access type to be the specified element.
	 * 
	 * 
	 * 
	 * @param accessType
	 */

	public void setAccessType(String accessType) {

		this.accessType = accessType;

	}

	/**
	 * 
	 * Returns the access type.
	 * 
	 * 
	 * 
	 * @return access type.
	 */

	public String getAccessType() {

		return accessType;

	}

	/**
	 * 
	 * Returns the access type in URI.
	 * 
	 * 
	 * 
	 * @return access type in URI
	 */

	public URI getAccessTypeURI() {

		if (accessType != null) {

			if (accessType == "public") {

				return Access.PUBLIC;

			}

			else if (accessType == "private") {

				return Access.PRIVATE;

			}

			else

				return null;

		}

		return null;

	}

	@Override
	public String toString() {

		return accessType;

	}

	public static final class Access {

		public static final URI PUBLIC = URI.create(Sbol2Terms.sbol2

		.getNamespaceURI() + "public");

		public static final URI PRIVATE = URI.create(Sbol2Terms.sbol2

		.getNamespaceURI() + "private");

	}

}

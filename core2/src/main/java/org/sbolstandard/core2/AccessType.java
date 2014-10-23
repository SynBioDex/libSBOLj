package org.sbolstandard.core2;

public enum AccessType {
	
	PUBLIC("public"),
	PRIVATE("private");
	
	private String accessTypeAlias;
	
	private AccessType(String accessTypeAlias) {
		this.accessTypeAlias = accessTypeAlias;
	}

	/**
	 * Returns the access type ("public" or "private").
	 * @return the access type ("public" or "private").
	 */
	public String getAccessTypeAlias() {
		return accessTypeAlias;
	}

	/**
	 * Sets the access type to be the specified element.
	 * @param accessTypeAlias
	 */
	public void setAccessTypeAlias(String accessTypeAlias) {
		this.accessTypeAlias = accessTypeAlias;
	}

}

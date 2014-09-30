package org.sbolstandard.core2;

public enum AccessType {
	
	PUBLIC("public"),
	PRIVATE("private");
	
	private String accessTypeAlias;
	
	private AccessType(String accessTypeAlias) {
		this.accessTypeAlias = accessTypeAlias;
	}

	public String getAccessTypeAlias() {
		return accessTypeAlias;
	}

	public void setAccessTypeAlias(String accessTypeAlias) {
		this.accessTypeAlias = accessTypeAlias;
	}

}

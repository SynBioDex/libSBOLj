package org.sbolstandard.core2;

import java.net.URI;

public class Implementation extends TopLevel {
	
	private URI built;

	Implementation(URI identity) throws SBOLValidationException {
		super(identity);
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Checks if the built property is set.
	 * 
	 * @return {@code true} if it is not {@code null}, {@code false} otherwise
	 */
	public boolean isSetBuilt() {
		return built != null;
	}

	@Override
	Identified deepCopy() throws SBOLValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Identified copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void checkDescendantsURIcompliance() throws SBOLValidationException {
		// TODO Auto-generated method stub
		
	}

}

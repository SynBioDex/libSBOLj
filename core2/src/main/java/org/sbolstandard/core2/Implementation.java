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
	
	/**
	 * Returns the URI of the component definition or module definition built
	 *
	 * @return the URI of built
	 */
	public URI getBuiltURI() {
		return this.built;
	}
	
	/**
	 * Returns the component definition or module definition referenced by the built field.
	 *
	 * @return {@code null} if the associated SBOLDocument instance is {@code null}
	 *         or no matching component definition or module definition referenced;
	 *         or the matching component definition or module definition otherwise.
	 */
	public TopLevel getBuilt() {
		if (this.getSBOLDocument() == null)
			return null;
		
		if(this.getSBOLDocument().getComponentDefinition(built) == null) {
			return this.getSBOLDocument().getModuleDefinition(built);
		}
		
		return this.getSBOLDocument().getComponentDefinition(built);
	}

	/**
	 * Sets the URI of the built property to the given one.
	 *
	 * @param builtURI
	 *            the given URI to set to
	 * @throws SBOLValidationException 
	 * 				on SBOL validation rule violation XXXXX.
	 */
	public void setBuiltURI(URI builtURI) throws SBOLValidationException {
		if(this.getSBOLDocument().getComponentDefinition(builtURI) == null &&
				this.getSBOLDocument().getModuleDefinition(builtURI) == null) {
			throw new SBOLValidationException("sbol-XXXXX", this);
		}
		
		this.built = builtURI;
	}
	
	/**
	 * Sets the the built property to the given one.
	 *
	 * @param built
	 *            the given component definition or module definition to set to
	 * @throws SBOLValidationException 
	 * 				on SBOL validation rule violation XXXXX.
	 */
	public void setBuilt(TopLevel built) throws SBOLValidationException {
		if(!(built instanceof ComponentDefinition) && !(built instanceof ModuleDefinition)) {
			throw new SBOLValidationException("sbol-XXXXX", this);
		}
		
		this.built = built.getIdentity();
	}

	/**
	 * Sets the built property of the Implementation to {@code null}.
	 */
	public void unsetBuilt() {
		this.built = null;
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

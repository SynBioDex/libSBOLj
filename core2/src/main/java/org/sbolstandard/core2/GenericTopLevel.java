package org.sbolstandard.core2;

import java.net.URI;

public class GenericTopLevel extends TopLevel{

	private URI rdfType;
	
	public URI getRdfType() {
		return rdfType;
	}

	public void setRdfType(URI rdfType) {
		this.rdfType = rdfType;
	}

	public GenericTopLevel(URI identity, URI rdfType) {
		super(identity);
		this.rdfType = rdfType;		
	}
}

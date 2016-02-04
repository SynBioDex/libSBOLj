package org.oboparser.obo;

public class OBOIndividual extends OBOStanza {
	public OBOIndividual() { 
		super("Individual");
	}
	
	public Object clone() { 
		return super.clone(OBOIndividual.class);
	}
}

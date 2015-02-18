package org.sbolstandard.core2;

import java.net.URI;

import javax.xml.namespace.QName;

import org.sbolstandard.core2.abstract_classes.TopLevel;

public class GenericTopLevel extends TopLevel{

	private QName rdfType;
	
	public QName getRdfType() {
		return rdfType;
	}

	public void setRdfType(QName rdfType) {
		this.rdfType = rdfType;
	}

	public GenericTopLevel(URI identity, QName rdfType) {
		super(identity);
		this.rdfType = rdfType;		
	}
}

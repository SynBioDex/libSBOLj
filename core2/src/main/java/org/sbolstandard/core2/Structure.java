package org.sbolstandard.core2;

import java.net.URI;

public class Structure extends TopLevel{
		
	private String elements;
	private URI encoding;
	
//	public Structure(String elements, URI encoding) {
//		this.elements = elements;
//		this.encoding = encoding;
//	}
	
	public Structure(URI identity, String elements, URI encoding) {
		super(identity);
		setElements(elements);
		setEncoding(encoding);
	}
	
	public String getElements() {
		return elements;
	}
	
	public void setElements(String elements) {
		this.elements = elements;
	}
	
	public URI getEncoding() {
		return encoding;
	}
	
	public void setEncoding(URI encoding) {
		this.encoding = encoding;
	}
}

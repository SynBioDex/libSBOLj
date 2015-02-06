package org.sbolstandard.core2;

import java.net.URI;

public class Sequence extends TopLevel{
		
	private String elements;
	private URI encoding;
	
	public Sequence(URI identity, String elements, URI encoding) {
		super(identity);
		setElements(elements);
		setEncoding(encoding);
	}
	
	/**
	 * Returns field variable <code>elements</code>.
	 * @return field variable <code>elements</code>
	 */
	public String getElements() {
		return elements;
	}
	
	/**
	 * Sets field variable <code>elements</code> to the specified element.
	 * @param elements
	 */
	public void setElements(String elements) {
		this.elements = elements;
	}
	
	/**
	 * Returns field variable <code>encoding</code>.
	 * @return field variable <code>encoding</code>
	 */
	public URI getEncoding() {
		return encoding;
	}
	
	/**
	 * Sets field variable <code>encoding</code> to the specified element.
	 * @param encoding
	 */
	public void setEncoding(URI encoding) {
		this.encoding = encoding;
	}
}

package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Location;

public class Cut extends Location{
	
	private Integer at;
	
	public Cut(URI identity, Integer at) {
		super(identity);
		setAt(at);
	}

	/**
	 * Returns field variable <code>at</code>.
	 * @return field variable <code>at</code>
	 */
	public Integer getAt() {
		return at;
	}

	/**
	 * Sets field variable <code>at</code> to the specified element.
	 * @param at
	 */
	public void setAt(Integer at) {
		this.at = at;
	}
}

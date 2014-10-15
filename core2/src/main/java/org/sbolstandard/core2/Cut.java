package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Location;

public class Cut extends Location{
	
	private Integer at;
	
	public Cut(URI identity, Integer at) {
		super(identity);
		setAt(at);
	}

	public Integer getAt() {
		return at;
	}

	public void setAt(Integer at) {
		this.at = at;
	}
}

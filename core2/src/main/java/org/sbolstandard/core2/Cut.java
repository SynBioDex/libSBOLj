package org.sbolstandard.core2;

import java.net.URI;

import org.sbolstandard.core2.abstract_classes.Location;

public class Cut extends Location{
	
	private Integer at;
	
	public Cut(URI identity, URI persistentIdentity, String version, Integer at) {
		super(identity, persistentIdentity, version);
		this.at = at;
	}

	@Override
	public <T extends Throwable> void accept(SBOLVisitor<T> visitor) throws T {
		// TODO Auto-generated method stub

	}

	public Integer getAt() {
		return at;
	}

	public void setAt(Integer at) {
		this.at = at;
	}
}

package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Collection extends TopLevel{
	
	private List<URI> members;
	
	public Collection(URI identity) {
		super(identity);
		this.members = new ArrayList<URI>();
	}

	public List<URI> getMembers() {
		return members;
	}

	public void setMembers(List<URI> members) {
		this.members = members;
	}
}

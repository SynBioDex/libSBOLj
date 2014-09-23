package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Collection extends TopLevel{
	
	private List<TopLevel> members;
	
	public Collection(URI identity, URI persistentIdentity, String version,
			String displayId, String name, String description) {
		super(identity, persistentIdentity, version, displayId, name, description);
		this.members = new ArrayList<TopLevel>();
	}

	public List<TopLevel> getMembers() {
		return members;
	}

	public void setMembers(List<TopLevel> members) {
		this.members = members;
	}
}

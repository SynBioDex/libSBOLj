package org.oboparser.pro.authoring;

import java.io.*;

public class OccupiedSite extends PROSite {
	
	private TermRequest part;

	public OccupiedSite(PROSite site, TermRequest part) {
		super(String.format("%s occupied by %s", site.getName(), part.toString()), site);
		this.part = part;
	}

	public OccupiedSite(String id, String n, TermRequest... isa) {
		super(id, n, isa);
		part = null;
	}
	
	@Override
	public void generateOBO(PrintStream ps) { 
		super.generateOBO(ps);
		if(part != null) { part.generateOBO(ps); }
	}
}

package org.oboparser.pro.authoring;

public class ModifiedSite extends PROSite {
	
	public ModifiedSite(String n, TermRequest... isa) {
		super(n, isa);
	}

	public ModifiedSite(String id, String n, TermRequest... isa) {
		super(id, n, isa);
	}
}

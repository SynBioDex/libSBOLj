package org.oboparser.pro.authoring;

import java.io.*;

public class ModifiedSite extends PROSite {
	
	public ModifiedSite(String n, TermRequest... isa) {
		super(n, isa);
	}

	public ModifiedSite(String id, String n, TermRequest... isa) {
		super(id, n, isa);
	}
}

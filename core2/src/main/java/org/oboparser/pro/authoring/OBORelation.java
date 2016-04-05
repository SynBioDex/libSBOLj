package org.oboparser.pro.authoring;

import java.io.PrintStream;

public interface OBORelation {
	public void generateOBO(PrintStream ps);
}

class NamedRelation implements OBORelation { 
	private String name;
	
	public NamedRelation(String n) { 
		name = n;
	}
	
	public String getName() { return name; }
	@Override
	public String toString() { return name; }

	@Override
	public void generateOBO(PrintStream ps) {
		ps.println(String.format("[Typedef]"));
		ps.println(String.format("id: %s", name));
		ps.println(String.format("name: %s", name));
		ps.println();
	}	
}
package org.oboparser.pro.authoring;

import java.io.PrintStream;

public class RelatedTerm implements OBOTerm {

	public OBORelation relation;
	public TermRequest term;
	private boolean generated;
	
	public RelatedTerm(OBORelation r, TermRequest req) { 
		relation = r;
		term = req;
		generated = false;
	}
	
	public String toString() { 
		return String.format("%s %s", relation, term.toString());
	}

	public void generateOBO(PrintStream ps) {
		if(generated) { return; }
		generated = true;
		relation.generateOBO(ps);
		term.generateOBO(ps);
	}

	public void clearInOBO() {
		generated = false;
	}

	public boolean inOBO() {
		return generated;
	}
}

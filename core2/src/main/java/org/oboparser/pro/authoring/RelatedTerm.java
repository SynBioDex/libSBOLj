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
	
	@Override
	public String toString() { 
		return String.format("%s %s", relation, term.toString());
	}

	@Override
	public void generateOBO(PrintStream ps) {
		if(generated) { return; }
		generated = true;
		relation.generateOBO(ps);
		term.generateOBO(ps);
	}

	@Override
	public void clearInOBO() {
		generated = false;
	}

	@Override
	public boolean inOBO() {
		return generated;
	}
}

package org.oboparser.pro.authoring;

public class ExonPart extends TermRequest {

	public ExonPart(String n, TermRequest... isa) {
		super(n, isa);
	}

	public ExonPart(String id, String n, TermRequest... isa) {
		super(id, n, isa);
	}
	
	public void lacksSite(PROSite site) { 
		addRelationship(new RelatedTerm(PROProtein.lacks_part, site));
	}
	
	public PROSite createSite(int pos, String value) { 
		PROSite s = new OccupiedSite(new PROSite("??", pos), new TermRequest(value));
		addRelationship(new RelatedTerm(PROProtein.has_part, s));
		return s;
	}
}

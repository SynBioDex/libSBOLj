package org.oboparser.pro.authoring;

public class PROProtein extends TermRequest {
	
	public static OBORelation lacks_part = new NamedRelation("lacks_part");
	public static OBORelation has_part = new NamedRelation("has_part");
	public static OBORelation only_in_taxon = new NamedRelation("only_in_taxon");

	public PROProtein(String n, TermRequest... isa) {
		super(n, isa);
	}

	public PROProtein(String id, String n, TermRequest... isa) {
		super(id, n, isa);
	}
	
	// Sub-class Creation /////

	public PROProtein createIsoform(String isoName) { 
		return new PROProtein(String.format("%s isoform %s", getName(), isoName), this);
	}
	
	public ExonPart createExon(String exName, int start, int end) { 
		ExonPart ep = new ExonPart(exName);
		addRelationship(new RelatedTerm(has_part, ep));
		return ep;
	}
	
	// Modifiers //// 
	
	public void lacksExonPart(ExonPart ep) { 
		addRelationship(new RelatedTerm(lacks_part, ep));
	}
	
	public void lacksSite(PROSite site) { 
		addRelationship(new RelatedTerm(PROProtein.lacks_part, site));
	}
	
	public void onlyInTaxon(Taxon taxon) { 
		addRelationship(new RelatedTerm(only_in_taxon, taxon));
	}

}

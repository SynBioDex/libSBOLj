package org.oboparser.pro.authoring;

public class PROSite extends TermRequest {
	
	public static String asName(String content, int pos) { 
		return String.format("%s-%d", content, pos);
	}
	
	private int position;
	private String contents;

	public PROSite(String c, int p, TermRequest... isa) {
		super(asName(c, p), isa);
	}

	public PROSite(String id, String c, int p, TermRequest... isa) {
		super(id, asName(c, p), isa);
	}

	public PROSite(String n, TermRequest... isa) {
		super(n, isa);
	}

	public PROSite(String id, String n, TermRequest... isa) {
		super(id, n, isa);
	}

	public int getPosition() { return position; }
	public String getContents() { return contents; }
	
}

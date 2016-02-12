package org.oboparser.pro.authoring;

import java.util.*;
import java.io.*;

import org.oboparser.obo.GenSym;

public class TermRequest implements OBOTerm {

	public static String PREFIX = "request_";
	public static GenSym gensym = new GenSym(PREFIX);

	private String id;
	private boolean generated;
	
	private String name;
	private String definition;
	private String comment;
	private Set<String> synonyms;
	
	private Set<TermRequest> is_a;
	private LinkedList<OBOTerm> intersection;
	private LinkedList<RelatedTerm> relationship;

	public TermRequest(String n, TermRequest... isa) { 
		id = gensym.nextSymbol();
		generated = false;
		name = n;
		definition = null;
		comment = null;
		synonyms = new TreeSet<String>();
		is_a = new HashSet<TermRequest>(Arrays.asList(isa));
		intersection = new LinkedList<OBOTerm>();
		relationship = new LinkedList<RelatedTerm>();
	}

	public TermRequest(String id, String n, TermRequest... isa) {
		this.id = id;
		name = n;
		generated = false;
		definition = null;
		comment = null;
		synonyms = new TreeSet<String>();
		is_a = new HashSet<TermRequest>(Arrays.asList(isa));
		intersection = new LinkedList<OBOTerm>();
		relationship = new LinkedList<RelatedTerm>();
	}
	
	public String getID() { return id; }
	public String getName() { return name; }
	
	public void addSynonym(String s) { 
		synonyms.add(s);
	}
	
	public void addIsA(TermRequest r) { 
		is_a.add(r);
	}
	
	public void addIntersection(TermRequest r) { 
		intersection.add(r);
	}
	
	public void addRelationship(RelatedTerm r) { 
		relationship.add(r);
	}
	
	public void setDefinition(String d) { definition = d; }

	public String getComment() { return comment; }
	
	public void setComment(String c) { comment = c; }
	
	@Override
	public String toString() { 
		return String.format("%s ! %s", id, name);
	}

	@Override
	public void generateOBO(PrintStream ps) { 
		if(generated || !id.startsWith(PREFIX)) { 
			return;
		}
		
		LinkedList<OBOTerm> toGenerate = new LinkedList<OBOTerm>();
		
		generated = true;
		
		ps.println("[Term]");
		ps.println(String.format("id: %s", id));
		ps.println(String.format("name: %s", name));
		if(definition != null) { ps.println(String.format("def: %s", definition)); } 
		if(comment != null) { ps.println(String.format("comment: %s", comment)); }
		if(!synonyms.isEmpty()) { 
			for(String syn : synonyms) { 
				ps.println(String.format("synonym: %s EXACT []", syn));
			}
		}
	
		for(TermRequest isa : is_a) { 
			ps.println(String.format("is_a: %s", isa.toString()));
			toGenerate.add(isa);
		}
		
		if(!intersection.isEmpty() && intersection.size() < 2) { 
			throw new IllegalArgumentException(String.format(
					"Can't be an intersection of just %d terms", intersection.size()));
		}
		
		for(OBOTerm inter : intersection) { 
			ps.println(String.format("intersection_of: %s", inter.toString()));
			toGenerate.add(inter);
		}
		
		for(RelatedTerm rt : relationship) { 
			ps.println(String.format("relationship: %s", rt.toString()));
			toGenerate.add(rt);
		}
		
		ps.println();
		
		for(OBOTerm ot : toGenerate) { 
			ot.generateOBO(ps);
		}
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

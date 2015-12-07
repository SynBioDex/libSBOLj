package org.oboparser.pro;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import org.oboparser.obo.*;

public class PROOBO {
	
	private static Pattern synonym = Pattern.compile("^\"([^\"]+)\" (EXACT|RELATED).*$");
	
	public static void main(String[] args) { 
		File f = new File(args[0]);
		String id = "PRO:000000220";
		Pattern uniprotPattern = Pattern.compile("UniProtKB:((?:P|Q)\\d+(?:-\\d+))");

		try {
			PROOBO obo = new PROOBO(f);
			String def = obo.getProteinDef(id);
			
			System.out.println(String.format("id: %s", id));
			System.out.println(String.format("name: %s", obo.getProteinName(id)));
			System.out.println(String.format("def: %s", def));
			Matcher umatcher = uniprotPattern.matcher(def);
			while(umatcher.find()) { 
				System.out.println(String.format("uniprot: %s", umatcher.group(1)));
			}
			for(String syn : obo.getProteinSynonyms(id)) { 
				System.out.println(String.format("syn: %s", syn));
			}
			for(String xref : obo.getProteinXRefs(id)) { 
				System.out.println(String.format("xref: %s", xref));				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected OBOOntology ontology;
	private Map<String,OBOStanza> proteinStanzas; 
	
	public PROOBO(File f) throws IOException { 
		OBOParser parser = new OBOParser();
		parser.parse(f);
		ontology = parser.getOntology();
		proteinStanzas = new TreeMap<String,OBOStanza>();
		
		for(OBOStanza stanza : ontology.getStanzas()) {
			if(stanza.getType().equals("Term")) { 
				String id = stanza.getId();
				proteinStanzas.put(id, stanza);
				//System.out.println(String.format("PRO: \"%s\"", id));
			}
		}
	}
	
	public Set<String> proteins() { return proteinStanzas.keySet(); }
	
	public Collection<String> getProteinSynonyms(String id) { 
		OBOStanza stanza = proteinStanzas.get(id);
		List<OBOValue> values = stanza.values("synonym");
		LinkedList<String> syns = new LinkedList<String>();
		if(values != null) { 
			for(OBOValue v : values) {
				String synstring = v.getValue();
				Matcher m = synonym.matcher(synstring);
				if(m.matches()) { 
					syns.add(m.group(1));
				}
			}
		}
		return syns;
	}

	public Collection<String> getProteinXRefs(String id) { 
		OBOStanza stanza = proteinStanzas.get(id);
		List<OBOValue> values = stanza.values("xref");
		LinkedList<String> syns = new LinkedList<String>();
		if(values != null) {  
			for(OBOValue v : values) {
				syns.add(v.getValue());
			}
		}
		return syns;
	}
	
	public String getProteinName(String id) { 
		OBOStanza stanza = proteinStanzas.get(id);
		List<OBOValue> values = stanza.values("name");
		return values != null && !values.isEmpty() ? values.get(0).getValue() : null;		
	}
	
	public String getProteinDef(String id) { 
		if(!proteinStanzas.containsKey(id)) { 
			return null;
		}
		OBOStanza stanza = proteinStanzas.get(id);
		List<OBOValue> values = stanza.values("def");
		return values != null && !values.isEmpty() ? values.get(0).getValue() : null;
	}

	public boolean containsID(String pro) {
		return proteinStanzas.containsKey(pro);
	}
}

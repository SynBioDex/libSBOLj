package org.oboparser.obo;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.*;
import org.oboparser.obo.OBOOntology;
import org.oboparser.obo.OBOParser;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.*;

public class OBOParserTest {
	
	@Test 
	public void testParseIndividual() throws IOException {
		OBOParser parser = new OBOParser();
		String PATH = "src/resources/ontologies/SystemsBiologyOntology";
		
		File f = new File(PATH + "sbo_full.obo");
		try {
			parser.parse(f);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//OBOOntology ontology = parser.getOntology();
		
		
//		System.out.println("id: " + ontology.getStanza("SO:0000017").id());
//		System.out.println("type: " + ontology.getStanza("SO:0000017").getType());
//		System.out.println("is_a: " + ontology.getStanza("SO:0000017").values("is_a"));
		//System.out.println(ontology.getStanza("SO:1000088").values("is_a")); // returns null
		
//		// Term SO:1001260 has two is_a relations.
//		OBOStanza child1 = ontology.getStanza("SO:1001260");
//		OBOStanza parent1_1 = ontology.getStanza("SO:0000243");
//		OBOStanza parent1_2 = ontology.getStanza("SO:1001268");
//		OBOStanza parent1_3 = ontology.getStanza("SO:0000001");
//		System.out.println(ontology.isDescendantOf(child1, parent1_1));
//		System.out.println(ontology.isDescendantOf(child1, parent1_2));
//		System.out.println(ontology.isDescendantOf(child1, parent1_3));
		
		// Test replaced_by
//		OBOStanza child2 = ontology.getStanza("SO:1000180");
//		OBOStanza parent2_1 = ontology.getStanza("SO:0001060");
//		System.out.println(!ontology.isDescendantOf(child2, parent2_1));
//		OBOStanza child3 = ontology.getStanza("SO:1000060");
//		//OBOStanza parent3_1 = ontology.getStanza("SO:0001576");
//		OBOStanza parent3_1 = ontology.getStanza("SO:0001968");
//		System.out.println(ontology.isDescendantOf(child3, parent3_1));
//		
//		
//		OBOStanza multiple_names = ontology.getStanza("SO:0000056");
//		System.out.println(multiple_names.getName());
//		OBOStanza multiple_names1 = ontology.getStanza("SO:0000057");
//		System.out.println(multiple_names1.getName());

		
//		List<OBOValue> values = ontology.values("SO:0000017");
//		
//		for (OBOValue t : values) {
//			System.out.println(t.getModifiers());
//		}
		
//		assertThat(ontology, is(not(nullValue())));
//		assertThat(ontology.getStanzas().size(), is(1));
//		
//		OBOStanza stanza = ontology.getStanza("foo");
//		
//		assertThat(stanza, is(not(nullValue())));
//		assertThat(stanza.getType(), is("Individual"));
//		//assertThat(stanza.getClass(), is(OBOIndividual.class));
//		assertThat(stanza.values("name").size(), is(1));
//		assertThat(stanza.values("name").get(0).getValue(), is("foo"));
	}
}

package org.cidarlab.pigeon;

import java.net.URI;
import java.util.Iterator;

import org.sbolstandard.core.DnaComponent;
import org.sbolstandard.core.SequenceAnnotation;
import org.sbolstandard.core.util.SequenceOntology;

public class PigeonGenerator {

	private static final String NEWLINE = System.getProperty("line.separator");

	public static String toPigeon(DnaComponent component) {
		
		StringBuilder sb = new StringBuilder();
		if(null == component.getAnnotations() || component.getAnnotations().isEmpty()) {
			Iterator<URI> it = component.getTypes().iterator();
			URI uri = it.next();
			sb.append(toPigeonType(uri)).append(" ").append(component.getDisplayId());
		} else {
			Iterator<SequenceAnnotation> it = component.getAnnotations().iterator();
			while(it.hasNext()) {
				SequenceAnnotation sa = it.next();
				sb.append(toPigeon(sa.getSubComponent())).append(NEWLINE);
			}
			
		}
		
		
		return sb.toString();
	}
	
	private static String toPigeonType(URI s) {
		if(SequenceOntology.PROMOTER.equals(s)) {
			return "P";
		} else if(SequenceOntology.CDS.equals(s)) {
			return "c";
		} else if(SequenceOntology.PRIMER_BINDING_SITE.equals(s)) {
			return "r";
		} else if(SequenceOntology.TERMINATOR.equals(s)) {
			return "T";
		}
		return (String)null;
	}
}

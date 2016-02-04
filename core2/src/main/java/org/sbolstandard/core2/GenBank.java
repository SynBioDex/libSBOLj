package org.sbolstandard.core2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

public class GenBank {
	
	private static SequenceOntology so = null;
	private static String gbPrefix = "gb";
	private static String gbNamespace = "http://www.ncbi.nlm.nih.gov";
	private static String URIPrefix = null;
	
	private static void writeGenBankLine(Writer w, String line, int margin, int indent) throws IOException {
		if (line.length() < margin) {
			w.write(line+"\n");
		} else {
			String spaces = "";
			for (int i = 0 ; i < indent ; i++) spaces += " ";
			int breakPos = line.substring(0,margin-1).lastIndexOf(" ")+1;
			if (breakPos==0 || breakPos < 0.75*margin) breakPos = margin-1;
			w.write(line.substring(0, breakPos)+"\n");
			int i = breakPos;
			while (i < line.length()) {
				if ((i+(margin-indent)) < line.length()) {
					breakPos = line.substring(i,i+(margin-indent)-1).lastIndexOf(" ")+1;
					if (breakPos==0 || breakPos < 0.65*margin) breakPos = (margin-indent)-1;
					w.write(spaces+line.substring(i,i+breakPos)+"\n");
				} else {
					w.write(spaces+line.substring(i)+"\n");
					breakPos = (margin-indent)-1;
				}
				i+=breakPos;
			}
		}
	}
	
	/**
	 * Serializes a given ComponentDefinition and outputs the data from the serialization to the given output
	 * file name in GenBank format
	 * @param componentDefinition
	 * @param filename
	 * @throws IOException
	 * @throws SBOLValidationException 
	 */
	public static void write(ComponentDefinition componentDefinition, String filename) throws IOException, SBOLValidationException
	{
		write(componentDefinition, new File(filename));
	}
	
	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given file
	 * in GenBank format.
	 * @param componentDefinition
	 * @param file
	 * @throws IOException
	 * @throws SBOLValidationException 
	 */
	public static void write(ComponentDefinition componentDefinition, File file) throws IOException, SBOLValidationException{
		FileOutputStream stream = new FileOutputStream(file);
		BufferedOutputStream buffer = new BufferedOutputStream(stream);
		write(componentDefinition, buffer);
		stream.close();
		buffer.close();
	}
	
	/**
	 * Serializes a given SBOLDocument and outputs the data from the serialization to the given output stream
	 * in GenBank format.
	 * @param componentDefinition
	 * @param out
	 * @throws IOException 
	 * @throws SBOLValidationException 
	 */	
	public static void write(ComponentDefinition componentDefinition, OutputStream out) throws IOException, SBOLValidationException {
		Writer w = new OutputStreamWriter(out, "UTF-8");
		so = new SequenceOntology();
		Sequence seq = null;
		for (Sequence sequence : componentDefinition.getSequences()) {
			if (sequence.getEncoding().equals(Sequence.IUPAC_DNA)||
				sequence.getEncoding().equals(Sequence.IUPAC_RNA)) {
				seq = sequence;
				break;
			}
		}
		if (seq == null) {
			throw new SBOLValidationException("ComponentDefintion " + componentDefinition.getIdentity() +
					" does not have an IUPAC sequence.");
		}
		int size = seq.getElements().length();
		writeHeader(w,componentDefinition,size);
		writeReferences(w,componentDefinition);
		writeComment(w,componentDefinition);
		w.write("FEATURES             Location/Qualifiers\n");
		recurseComponentDefinition(componentDefinition,w);
		w.write("ORIGIN\n");
		writeSequence(w,seq,size);
		w.write("//\n");
		w.close(); 
	}
	
	private static String convertSOtoGenBank(String soTerm) {
		if (soTerm.equals("SO:0001023")) {return String.format("%-15s", "allele");}
		if (soTerm.equals("SO:0000140")) {return String.format("%-15s", "attenuator");}
		if (soTerm.equals("SO:0001834")) {return String.format("%-15s", "C_region");}
		if (soTerm.equals("SO:0000172")) {return String.format("%-15s", "CAAT_signal");}
		if (soTerm.equals("SO:0000316")) {return String.format("%-15s", "CDS");}
		//if (soTerm.equals("SO:")) {return String.format("%-15s", "conflict");}
		if (soTerm.equals("SO:0000297")) {return String.format("%-15s", "D-loop");}
		if (soTerm.equals("SO:0000458")) {return String.format("%-15s", "D_segment");}
		if (soTerm.equals("SO:0000165")) {return String.format("%-15s", "enhancer");}
		if (soTerm.equals("SO:0000147")) {return String.format("%-15s", "exon");}
		if (soTerm.equals("SO:0000704")) {return String.format("%-15s", "gene");}
		if (soTerm.equals("SO:0000173")) {return String.format("%-15s", "GC_signal");}
		if (soTerm.equals("SO:0000723")) {return String.format("%-15s", "iDNA");}
		if (soTerm.equals("SO:0000188")) {return String.format("%-15s", "intron");}
		if (soTerm.equals("SO:0000470")) {return String.format("%-15s", "J_region");}
		if (soTerm.equals("SO:0000286")) {return String.format("%-15s", "LTR");}
		if (soTerm.equals("SO:0000419")) {return String.format("%-15s", "mat_peptide");}
		if (soTerm.equals("SO:0000409")) {return String.format("%-15s", "misc_binding");}
		if (soTerm.equals("SO:0000413")) {return String.format("%-15s", "misc_difference");}
		if (soTerm.equals("SO:0000001")) {return String.format("%-15s", "misc_feature");}
		if (soTerm.equals("SO:0001645")) {return String.format("%-15s", "misc_marker");}
		if (soTerm.equals("SO:0000298")) {return String.format("%-15s", "misc_recomb");}
		if (soTerm.equals("SO:0000233")) {return String.format("%-15s", "misc_RNA");}
		// TODO: CHECK THIS ONE
		//if (soTerm.equals("SO:0005836")) {return String.format("%-15s", "misc_signal");}
		if (soTerm.equals("SO:0005836")) {return String.format("%-15s", "regulatory");}
		if (soTerm.equals("SO:0000002")) {return String.format("%-15s", "misc_structure");}
		if (soTerm.equals("SO:0000305")) {return String.format("%-15s", "modified_base");}
		if (soTerm.equals("SO:0000234")) {return String.format("%-15s", "mRNA");}
		//if (soTerm.equals("SO:")) {return String.format("%-15s", "mutation");}
		if (soTerm.equals("SO:0001835")) {return String.format("%-15s", "N_region");}
		//if (soTerm.equals("SO:")) {return String.format("%-15s", "old_sequence");}
		if (soTerm.equals("SO:0000551")) {return String.format("%-15s", "polyA_signal");}
		if (soTerm.equals("SO:0000553")) {return String.format("%-15s", "polyA_site");}
		if (soTerm.equals("SO:0000185")) {return String.format("%-15s", "precursor_RNA");}
		if (soTerm.equals("SO:0000185")) {return String.format("%-15s", "prim_transcript");} 
		// NOTE: redundant with line above
		if (soTerm.equals("SO:0000112")) {return String.format("%-15s", "primer");}
		if (soTerm.equals("SO:0005850")) {return String.format("%-15s", "primer_bind");}
		if (soTerm.equals("SO:0000167")) {return String.format("%-15s", "promoter");}
		if (soTerm.equals("SO:0000410")) {return String.format("%-15s", "protein_bind");}
		if (soTerm.equals("SO:0000139") || soTerm.equals("SO:0000552")) {return String.format("%-15s", "RBS");}
		if (soTerm.equals("SO:0000296")) {return String.format("%-15s", "rep_origin");}
		if (soTerm.equals("SO:0000657")) {return String.format("%-15s", "repeat_region");}
		if (soTerm.equals("SO:0000726")) {return String.format("%-15s", "repeat_unit");}
		if (soTerm.equals("SO:0000252")) {return String.format("%-15s", "rRNA");}
		if (soTerm.equals("SO:0001836")) {return String.format("%-15s", "S_region");}
		if (soTerm.equals("SO:0000005")) {return String.format("%-15s", "satellite");}
		if (soTerm.equals("SO:0000013")) {return String.format("%-15s", "scRNA");}
		if (soTerm.equals("SO:0000418")) {return String.format("%-15s", "sig_peptide");}
		if (soTerm.equals("SO:0000274")) {return String.format("%-15s", "snRNA");}
		if (soTerm.equals("SO:0000149")) {return String.format("%-15s", "source");}
		if (soTerm.equals("SO:0000019")) {return String.format("%-15s", "stem_loop");}
		if (soTerm.equals("SO:0000331")) {return String.format("%-15s", "STS");}
		if (soTerm.equals("SO:0000174")) {return String.format("%-15s", "TATA_signal");}
		if (soTerm.equals("SO:0000141")) {return String.format("%-15s", "terminator");}
		if (soTerm.equals("SO:0000725")) {return String.format("%-15s", "transit_peptide");}
		if (soTerm.equals("SO:0001054")) {return String.format("%-15s", "transposon");}
		if (soTerm.equals("SO:0000253")) {return String.format("%-15s", "tRNA");}
		// if (soTerm.equals("SO:")) {return String.format("%-15s", "unsure");}
		if (soTerm.equals("SO:0001833")) {return String.format("%-15s", "V_region");}
		if (soTerm.equals("SO:0001060")) {return String.format("%-15s", "variation");}
		if (soTerm.equals("SO:0000175")) {return String.format("%-15s", "-10_signal");}
		if (soTerm.equals("SO:0000176")) {return String.format("%-15s", "-35_signal");}
		if (soTerm.equals("SO:0000557")) {return String.format("%-15s", "3'clip");}
		if (soTerm.equals("SO:0000205")) {return String.format("%-15s", "3'UTR");}
		if (soTerm.equals("SO:0000555")) {return String.format("%-15s", "5'clip");}
		if (soTerm.equals("SO:0000204")) {return String.format("%-15s", "5'UTR");}
		/*
		if (soTerm.equals("CDS") || soTerm.equals("promoter") || soTerm.equals("terminator")) 
			return String.format("%-15s", soTerm);
		else if (soTerm.equals("ribosome_entry_site"))
			return "RBS            ";
		 */
		return "misc_feature   ";
	}
	
	private static URI convertGenBanktoSO(String genBankTerm) {
		if (genBankTerm.equals("allele")) {
			return so.getURIbyId("SO:0001023");}
		if (genBankTerm.equals("attenuator")) {
			return so.getURIbyId("SO:0000140");}
		if (genBankTerm.equals("C_region")) {
			return so.getURIbyId("SO:0001834");}
		if (genBankTerm.equals("CAAT_signal")) {
			return so.getURIbyId("SO:0000172");}
		if (genBankTerm.equals("CDS")) {
			return so.getURIbyId("SO:0000316");}
		/* if (genBankTerm.equals("conflict")) {
		return so.getURIbyId("SO_");} */
		if (genBankTerm.equals("D-loop")) {
			return so.getURIbyId("SO:0000297");}
		if (genBankTerm.equals("D_segment")) {
			return so.getURIbyId("SO:0000458");}
		if (genBankTerm.equals("enhancer")) {
			return so.getURIbyId("SO:0000165");}
		if (genBankTerm.equals("exon")) {
			return so.getURIbyId("SO:0000147");}
		if (genBankTerm.equals("gene")) {
			return so.getURIbyId("SO:0000704");}
		if (genBankTerm.equals("GC_signal")) {
			return so.getURIbyId("SO:0000173");}
		if (genBankTerm.equals("iDNA")) {
			return so.getURIbyId("SO:0000723");}
		if (genBankTerm.equals("intron")) {
			return so.getURIbyId("SO:0000188");}
		if (genBankTerm.equals("J_region")) {
			return so.getURIbyId("SO:0000470");}
		if (genBankTerm.equals("LTR")) {
			return so.getURIbyId("SO:0000286");}
		if (genBankTerm.equals("mat_peptide")) {
			return so.getURIbyId("SO:0000419");}
		if (genBankTerm.equals("misc_binding")) {
			return so.getURIbyId("SO:0000409");}
		if (genBankTerm.equals("misc_difference")) {
			return so.getURIbyId("SO:0000413");}
		if (genBankTerm.equals("misc_feature")) {
			return so.getURIbyId("SO:0000001");}
		if (genBankTerm.equals("misc_marker")) {
			return so.getURIbyId("SO:0001645");}
		if (genBankTerm.equals("misc_recomb")) {
			return so.getURIbyId("SO:0000298");}
		if (genBankTerm.equals("misc_RNA")) {
			return so.getURIbyId("SO:0000233");}
		if (genBankTerm.equals("misc_signal")) {
			return so.getURIbyId("SO:0005836");}
		if (genBankTerm.equals("misc_structure")) {
			return so.getURIbyId("SO:0000002");}
		if (genBankTerm.equals("modified_base")) {
			return so.getURIbyId("SO:0000305");}
		if (genBankTerm.equals("mRNA")) {
			return so.getURIbyId("SO:0000234");}
		/* if (genBankTerm.equals("mutation")) {
		return so.getURIbyId("SO_");} */
		if (genBankTerm.equals("N_region")) {
			return so.getURIbyId("SO:0001835");}
		/* if (genBankTerm.equals("old_sequence")) {
		return so.getURIbyId("SO_");} */
		if (genBankTerm.equals("polyA_signal")) {
			return so.getURIbyId("SO:0000551");}
		if (genBankTerm.equals("polyA_site")) {
			return so.getURIbyId("SO:0000553");}
		if (genBankTerm.equals("precursor_RNA")) {
			return so.getURIbyId("SO:0000185");}
		if (genBankTerm.equals("prim_transcript")) {
			return so.getURIbyId("SO:0000185");}
		if (genBankTerm.equals("primer")) {
			return so.getURIbyId("SO:0000112");}
		if (genBankTerm.equals("primer_bind")) {
			return so.getURIbyId("SO:0005850");}
		if (genBankTerm.equals("promoter")) {
			return so.getURIbyId("SO:0000167");}
		if (genBankTerm.equals("protein_bind")) {
			return so.getURIbyId("SO:0000410");}
		if (genBankTerm.equals("RBS")) {
			return so.getURIbyId("SO:0000139");}
		if (genBankTerm.equals("rep_origin")) {
			return so.getURIbyId("SO:0000296");}
		if (genBankTerm.equals("repeat_region")) {
			return so.getURIbyId("SO:0000657");}
		if (genBankTerm.equals("repeat_unit")) {
			return so.getURIbyId("SO:0000726");}
		if (genBankTerm.equals("rRNA")) {
			return so.getURIbyId("SO:0000252");}
		if (genBankTerm.equals("S_region")) {
			return so.getURIbyId("SO:0001836");}
		if (genBankTerm.equals("satellite")) {
			return so.getURIbyId("SO:0000005");}
		if (genBankTerm.equals("scRNA")) {
			return so.getURIbyId("SO:0000013");}
		if (genBankTerm.equals("sig_peptide")) {
			return so.getURIbyId("SO:0000418");}
		if (genBankTerm.equals("snRNA")) {
			return so.getURIbyId("SO:0000274");}
		if (genBankTerm.equals("source")) {
			return so.getURIbyId("SO:0000149");}
		if (genBankTerm.equals("stem_loop")) {
			return so.getURIbyId("SO:0000019");}
		if (genBankTerm.equals("STS")) {
			return so.getURIbyId("SO:0000331");}
		if (genBankTerm.equals("TATA_signal")) {
			return so.getURIbyId("SO:0000174");}
		if (genBankTerm.equals("terminator")) {
			return so.getURIbyId("SO:0000141");}
		if (genBankTerm.equals("transit_peptide")) {
			return so.getURIbyId("SO:0000725");}
		if (genBankTerm.equals("transposon")) {
			return so.getURIbyId("SO:0001054");}
		if (genBankTerm.equals("tRNA")) {
			return so.getURIbyId("SO:0000253");}
		/* if (genBankTerm.equals("unsure")) {
		return so.getURIbyId("SO_");} */
		if (genBankTerm.equals("V_region")) {
			return so.getURIbyId("SO:0001833");}
		if (genBankTerm.equals("variation")) {
			return so.getURIbyId("SO:0001060");}
		if (genBankTerm.equals("-10_signal")) {
			return so.getURIbyId("SO:0000175");}
		if (genBankTerm.equals("-35_signal")) {
			return so.getURIbyId("SO:0000176");}
		if (genBankTerm.equals("3'clip")) {
			return so.getURIbyId("SO:0000557");}
		if (genBankTerm.equals("3'UTR")) {
			return so.getURIbyId("SO:0000205");}
		if (genBankTerm.equals("5'clip")) {
			return so.getURIbyId("SO:0000555");}
		if (genBankTerm.equals("5'UTR")) {
			return so.getURIbyId("SO:0000204");}
		// TODO: check this one
		if (genBankTerm.equals("regulatory")) {
			return so.getURIbyId("SO:0005836");}
		//return so.getURIbyId("SO:0000001");
		return null;
/*	
		URI soTerm = so.getURIbyName(genBankTerm);
		if (soTerm==null && genBankTerm.equals("misc_feature")) {
			soTerm = SequenceOntology.ENGINEERED_REGION;
		}
		return soTerm;
		*/
	}
	
	private static void writeHeader(Writer w,ComponentDefinition componentDefinition,int size) throws SBOLValidationException, IOException {
		String type = null;
		for (URI typeURI : componentDefinition.getTypes()) {
			if (typeURI.equals(ComponentDefinition.RNA)) {
				type = "RNA";
				break;
			} else if (typeURI.equals(ComponentDefinition.DNA)) {
				type = "DNA";
			}
		}
		if (type == null) {
			throw new SBOLValidationException("ComponentDefintion " + componentDefinition.getIdentity() +
					" is not DNA or RNA type.");
		}
		Annotation annotation = componentDefinition.getAnnotation(new QName(gbNamespace,"molecule",gbPrefix));
		if (annotation!=null) {
			type = annotation.getStringValue();
		}
		String linCirc = "linear";
		annotation = componentDefinition.getAnnotation(new QName(gbNamespace,"topology",gbPrefix));
		if (annotation!=null) {
			linCirc = annotation.getStringValue();
		}
		String division = "UNK";
		annotation = componentDefinition.getAnnotation(new QName(gbNamespace,"division",gbPrefix));
		if (annotation!=null) {
			division = annotation.getStringValue();
		}
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date dateobj = new Date();
		String date = df.format(dateobj);
		annotation = componentDefinition.getAnnotation(new QName(gbNamespace,"date",gbPrefix));
		if (annotation!=null) {
			date = annotation.getStringValue();
		}
		// TODO: assume LOCUS and ACCESSION are same
		String locus = "LOCUS       " + String.format("%-16s", componentDefinition.getDisplayId()) + " " +
				String.format("%11s", ""+size) + " bp " + "   " + String.format("%-6s", type) + "  " + 
				String.format("%-8s", linCirc) + " " + division + " " + date + "\n";
		w.write(locus);
		if (componentDefinition.isSetDescription()) {
			writeGenBankLine(w,"DEFINITION  " + componentDefinition.getDescription(),80,12);
		}
		w.write("ACCESSION   " + componentDefinition.getDisplayId() + "\n");
		if (componentDefinition.isSetVersion()) {
			String giNumber = "";
			annotation = componentDefinition.getAnnotation(new QName(gbNamespace,"GInumber",gbPrefix));
			if (annotation!=null) {
				giNumber = annotation.getStringValue();
			}
			w.write("VERSION     " + componentDefinition.getDisplayId() + "." + 
					componentDefinition.getVersion() + "  " + giNumber + "\n");
		}
		annotation = componentDefinition.getAnnotation(new QName(gbNamespace,"keywords",gbPrefix));
		if (annotation!=null) {
			w.write("KEYWORDS    " + annotation.getStringValue() + "\n");
		}
		annotation = componentDefinition.getAnnotation(new QName(gbNamespace,"source",gbPrefix));
		if (annotation!=null) {
			w.write("SOURCE      " + annotation.getStringValue() + "\n");
		}
		annotation = componentDefinition.getAnnotation(new QName(gbNamespace,"organism",gbPrefix));
		if (annotation!=null) {
			writeGenBankLine(w,"  ORGANISM  " + annotation.getStringValue(),80,12);
		}
	}
	
	private static void writeReferences(Writer w,ComponentDefinition componentDefinition) throws IOException {
		for (Annotation a : componentDefinition.getAnnotations()) {
			if (a.getQName().equals(new QName(gbNamespace,"reference",gbPrefix))) {
				String label = null;
				String authors = null;
				String title = null;
				String journal = null;
				String medline = null;
				String pubmed = null;
				for (Annotation ref : a.getAnnotations()) {
					if (ref.getQName().equals(new QName(gbNamespace,"label",gbPrefix))) {
						label = ref.getStringValue();
					} else if (ref.getQName().equals(new QName(gbNamespace,"authors",gbPrefix))) {
						authors = ref.getStringValue();
					} else if (ref.getQName().equals(new QName(gbNamespace,"title",gbPrefix))) {
						title = ref.getStringValue();
					} else if (ref.getQName().equals(new QName(gbNamespace,"journal",gbPrefix))) {
						journal = ref.getStringValue();
					} else if (ref.getQName().equals(new QName(gbNamespace,"medline",gbPrefix))) {
						medline = ref.getStringValue();
					} else if (ref.getQName().equals(new QName(gbNamespace,"pubmed",gbPrefix))) {
						pubmed = ref.getStringValue();
					}
				}
				if (label != null) {
					writeGenBankLine(w,"REFERENCE   " + label,80,12);
					if (authors != null) {
						writeGenBankLine(w,"  AUTHORS   " + authors,80,12);
					}
					if (title != null) {
						writeGenBankLine(w,"  TITLE     " + title,80,12);
					}
					if (journal != null) {
						writeGenBankLine(w,"  JOURNAL   " + journal,80,12);
					}
					if (medline != null) {
						writeGenBankLine(w,"   MEDLINE  " + medline,80,12);
					}
					if (pubmed != null) {
						writeGenBankLine(w,"   PUBMED   " + pubmed,80,12);
					}
				}
			}
		}		
	}
	
	private static void writeComment(Writer w,ComponentDefinition componentDefinition) throws IOException {
		Annotation annotation = componentDefinition.getAnnotation(new QName(gbNamespace,"comment",gbPrefix));
		if (annotation!=null) {
			writeGenBankLine(w,"COMMENT     " + annotation.getStringValue(),80,12);
		}		
	}
	
	@SuppressWarnings("unchecked")
	private static void writeFeature(Writer w,SequenceAnnotation sa,String role) throws IOException, SBOLValidationException {
		if (sa.getLocations().size()==1) {
			Location loc = sa.getLocations().iterator().next();
			if (loc instanceof Range) {
				Range range = (Range)loc;
				if (range.getOrientation().equals(OrientationType.REVERSECOMPLEMENT)) {
					w.write("     " + role + " " + "complement(" + range.getStart() + ".." + range.getEnd()+")\n");
				} else {
					w.write("     " + role + " " + range.getStart() + ".." + range.getEnd()+"\n");
				}
			} else if (loc instanceof Cut) {
				Cut cut = (Cut)loc;
				if (cut.getOrientation().equals(OrientationType.REVERSECOMPLEMENT)) {
					w.write("     " + role + " " + "complement(" + cut.getAt() + "^" + cut.getAt()+1+")\n");
				} else {
					w.write("     " + role + " " + cut.getAt() + "^" + cut.getAt()+1+"\n");
				}
			} else {
				throw new SBOLValidationException("SequenceAnnotation "+sa.getIdentity()+" is not range or cut.");
			}
		} else {
			String rangeStr = "     " + role + " " + "join(";
			boolean first = true;
			List<Location> sortedLocations = new ArrayList<Location>();
			sortedLocations.addAll(sa.getLocations());
			Collections.sort(sortedLocations);
			for (Location loc : sortedLocations) {
				if (loc instanceof Range) {
					Range range = (Range)loc;
					// TODO: can joins include complement?
					if (!first) rangeStr += ",";
					else first = false;
					rangeStr += range.getStart() + ".." + range.getEnd();
				} else if (loc instanceof Cut) {
					Cut cut = (Cut)loc;
					rangeStr += cut.getAt() + "^" + cut.getAt()+1;
				} else {
					throw new SBOLValidationException("SequenceAnnotation "+sa.getIdentity()+" is not range, only ranges supported for GenBank output");
				}
			}
			rangeStr += ")";
			writeGenBankLine(w,rangeStr,80,21);
		}
		for (Annotation a : sa.getAnnotations()) {
			writeGenBankLine(w,"                     /"+a.getQName().getLocalPart()+"="+
					a.getStringValue(),80,21);
		}
	}
	
	private static void writeSequence(Writer w,Sequence sequence,int size) throws IOException {
		for (int i = 0; i < size; i+=60) {
			String padded = String.format("%9s", "" + i);
			w.write(padded);
			for (int j = i; j < size && j < i + 60; j+=10) {
				if (j+10 < size) {
					w.write(" " + sequence.getElements().substring(j,j+10));
				} else {
					w.write(" " + sequence.getElements().substring(j));
				}
			}
			w.write("\n");
		}		
	}
	
	@SuppressWarnings("unchecked")
	private static void recurseComponentDefinition(ComponentDefinition componentDefinition, Writer w) throws IOException, SBOLValidationException {
		List<SequenceAnnotation> sortedSAs = new ArrayList<SequenceAnnotation>();
		sortedSAs.addAll(componentDefinition.getSequenceAnnotations());
		Collections.sort(sortedSAs);
		for (SequenceAnnotation sa : sortedSAs) {
			String role = "misc_feature   ";
			Component comp = sa.getComponent();
			if (comp != null) {
				ComponentDefinition compDef = comp.getDefinition();
				if (compDef != null) {
					for (URI roleURI : compDef.getRoles()) {
						String soRole = so.getId(roleURI);
						if (soRole != null) {
							role = convertSOtoGenBank(so.getId(roleURI));
							break;
						}
					}
					recurseComponentDefinition(compDef,w);
				}
			}
			// TODO: need to work out ranges correctly for multi-level
			writeFeature(w,sa,role);
		}
	}
	
	/**
	 * Takes in the given GenBank filename and converts the file to an SBOLDocument.
	 * <p>
	 * This method calls {@link #read(File)}.
	 *
	 * @param fileName
	 * @return the converted SBOLDocument
	 * @throws SBOLValidationException 
	 * @throws IOException 
	 */
	public static SBOLDocument read(String fileName) throws SBOLValidationException, IOException 
	{
		return read(new File(fileName));
	}
	
	/**
	 * Takes in the given GenBank file and converts the file to an SBOLDocument.
	 *
	 * @param file
	 * @return the converted SBOLDocument instance
	 * @throws SBOLValidationException 
	 * @throws IOException 
	 */
	public static SBOLDocument read(File file) throws SBOLValidationException, IOException
	{
		FileInputStream stream     = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		return read(buffer);
	}
	
	/**
	 * Takes in a given GenBank InputStream and converts the file to an SBOLDocument.
	 *
	 * @param in
	 * @return the converted SBOLDocument instance
	 * @throws SBOLValidationException 
	 * @throws IOException 
	 */
	public static SBOLDocument read(BufferedInputStream in) throws SBOLValidationException, IOException
	{
		SBOLDocument doc = new SBOLDocument();
		doc.setCreateDefaults(true);
		if (URIPrefix==null) {
			throw new SBOLValidationException("No URI prefix has been provided.");
		}
		doc.setDefaultURIprefix(URIPrefix);
		read(doc,in);
		return doc;
	}
	
	private static String nextLine = null;
	private static boolean featureMode = false;
	private static boolean originMode = false;
	
	private static String readGenBankLine(BufferedReader br) throws IOException {
		String newLine = "";
		if (nextLine == null) {
			newLine = br.readLine();
			if (newLine == null) return null;
			newLine = newLine.trim();
		} else {
			newLine = nextLine;
		}
		while (true) {
			nextLine = br.readLine();
			if (nextLine==null) return newLine;
			nextLine = nextLine.trim();
			if (featureMode) {
				if (nextLine.startsWith("/")) {
					return newLine;
				}
				String[] strSplit = nextLine.split("\\s+");
				URI role = convertGenBanktoSO(strSplit[0]);
				if (role!=null) return newLine;
			}
			if (originMode) return newLine;
			if (nextLine.startsWith("DEFINITION")) return newLine;
			if (nextLine.startsWith("ACCESSION")) return newLine;
			if (nextLine.startsWith("VERSION")) return newLine;
			if (nextLine.startsWith("KEYWORDS")) return newLine;
			if (nextLine.startsWith("SOURCE")) return newLine;
			if (nextLine.startsWith("ORGANISM")) return newLine;
			if (nextLine.startsWith("REFERENCE")) return newLine;
			if (nextLine.startsWith("COMMENT")) return newLine;
			if (nextLine.startsWith("AUTHORS")) return newLine;
			if (nextLine.startsWith("TITLE")) return newLine;
			if (nextLine.startsWith("JOURNAL")) return newLine;
			if (nextLine.startsWith("MEDLINE")) return newLine;
			if (nextLine.startsWith("PUBMED")) return newLine;
			if (nextLine.startsWith("FEATURES")) {
				featureMode = true;
				return newLine;
			}
			if (nextLine.startsWith("ORIGIN")) {
				originMode = true;
				return newLine;
			}
			if (featureMode) {
				newLine += nextLine;
			} else {
				newLine += " " + nextLine;
			}
		}
	}
	private static void createSubComponentDefinitions(SBOLDocument doc,ComponentDefinition topCD,URI type,String elements,String version) throws SBOLValidationException {
		for (SequenceAnnotation sa : topCD.getSequenceAnnotations()) {
			Range range = (Range)sa.getLocation("range");
			if (range!=null) {
				String subElements = elements.substring(range.getStart()-1,range.getEnd()-1).toLowerCase();
				if (range.getOrientation().equals(OrientationType.REVERSECOMPLEMENT)) {
					String reverse = "";
					for (int i = subElements.length()-1; i >= 0; i--) {
						if (subElements.charAt(i)=='a') {
							if (type.equals(ComponentDefinition.RNA)) {
								reverse += 't';
							} else {
								reverse += 'u';
							}
						} else if ((subElements.charAt(i)=='t')||(subElements.charAt(i)=='u')) {
							reverse += 'a';
						} else if (subElements.charAt(i)=='g') {
							reverse += 'c';
						} else if (subElements.charAt(i)=='c') {
							reverse += 'g';
						} else if (subElements.charAt(i)=='r') {
							reverse += 'y';
						} else if (subElements.charAt(i)=='y') {
							reverse += 'r';
						} else if (subElements.charAt(i)=='s') {
							reverse += 'w';
						} else if (subElements.charAt(i)=='w') {
							reverse += 's';
						} else if (subElements.charAt(i)=='k') {
							reverse += 'm';
						} else if (subElements.charAt(i)=='m') {
							reverse += 'k';
						} else if (subElements.charAt(i)=='b') {
							reverse += 'v';
						} else if (subElements.charAt(i)=='v') {
							reverse += 'b';
						} else if (subElements.charAt(i)=='d') {
							reverse += 'h';
						} else if (subElements.charAt(i)=='h') {
							reverse += 'd';
						} else if (subElements.charAt(i)=='n') {
							reverse += 'n';
						} else if (subElements.charAt(i)=='.') {
							reverse += '.';
						} else if (subElements.charAt(i)=='-') {
							reverse += '-';
						}
					}
					subElements = reverse;
				}
				ComponentDefinition subCompDef = sa.getComponent().getDefinition();
				String compDefId = subCompDef.getDisplayId();
				Sequence subSequence = doc.createSequence(compDefId+"_seq", version, subElements, Sequence.IUPAC_DNA);
				subCompDef.addSequence(subSequence);
			}
		}
	}
	
	private static void read(SBOLDocument doc,BufferedInputStream in) throws IOException, SBOLValidationException {
		so = new SequenceOntology();
		nextLine = null;
		featureMode = false;
		originMode = false;

		doc.addNamespace(URI.create(gbNamespace), gbPrefix);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		String id = "";
		String version = "";
		boolean featureMode = false;
		int featureCnt = 0;
		int refCnt = 0;
		boolean seqMode = false;
		String elements = "";
		String description = "";
		URI type = ComponentDefinition.DNA;
		ComponentDefinition topCD = null;
		List<Annotation> annotations = new ArrayList<Annotation>();
		List<Annotation> nestedAnnotations = null;
		Annotation annotation = null;
		while ((strLine = readGenBankLine(br)) != null)   {
			strLine = strLine.trim();
			if (strLine.startsWith("LOCUS")) {
				String[] strSplit = strLine.split("\\s+");
				id = strSplit[1];
				if (strSplit[4].toUpperCase().contains("RNA")) {
					type = ComponentDefinition.RNA;
				} 
				annotation = new Annotation(new QName(gbNamespace,"molecule",gbPrefix),strSplit[4]);
				annotations.add(annotation);
				annotation = new Annotation(new QName(gbNamespace,"topology",gbPrefix),strSplit[5]);
				annotations.add(annotation);
				annotation = new Annotation(new QName(gbNamespace,"division",gbPrefix),strSplit[6]);
				annotations.add(annotation);
				annotation = new Annotation(new QName(gbNamespace,"date",gbPrefix),strSplit[7]);
				annotations.add(annotation);
			} else if (strLine.startsWith("DEFINITION")) {
				description = strLine.replaceFirst("DEFINITION  ", "");
			} else if (strLine.startsWith("ACCESSION")) {
				String[] strSplit = strLine.split("\\s+");
				String accession = strSplit[1];
				// TODO: should use locus or accession id?
//				if (!accession.equals(id)) {
//					System.out.println("Warning: accession not equal to locus, using accession");
//				}
				id = accession;
			} else if (strLine.startsWith("VERSION")) {
				String[] strSplit = strLine.split("\\s+");
				version = strSplit[1].split("\\.")[1];
				if (!id.equals(strSplit[1].split("\\.")[0])) {
					throw new SBOLValidationException("Warning: id in version does not match id in accession");
				}
				if (strSplit.length > 2) {
					annotation = new Annotation(new QName(gbNamespace,"GInumber",gbPrefix),strSplit[2]);
					annotations.add(annotation);
				}
			} else if (strLine.startsWith("KEYWORDS")) {
				String annotationStr = strLine.replace("KEYWORDS", "").trim();
				annotation = new Annotation(new QName(gbNamespace,"keywords",gbPrefix),annotationStr);
				annotations.add(annotation);
			} else if (strLine.startsWith("SOURCE")) {
				String annotationStr = strLine.replace("SOURCE", "").trim();
				annotation = new Annotation(new QName(gbNamespace,"source",gbPrefix),annotationStr);
				annotations.add(annotation);
			} else if (strLine.startsWith("ORGANISM")) {
				String annotationStr = strLine.replace("ORGANISM", "").trim();
				annotation = new Annotation(new QName(gbNamespace,"organism",gbPrefix),annotationStr);
				annotations.add(annotation);
			} else if (strLine.startsWith("REFERENCE")) {
				String annotationStr = strLine.replace("REFERENCE", "").trim();
				nestedAnnotations = new ArrayList<Annotation>();
				Annotation labelAnnotation = new Annotation(new QName(gbNamespace,"label",gbPrefix),annotationStr);
				nestedAnnotations.add(labelAnnotation);
				URI nestedURI = URI.create(URIPrefix+"/"+id+"/reference"+refCnt);
				refCnt++;
				annotation = new Annotation(new QName(gbNamespace,"reference",gbPrefix),
						new QName(gbNamespace,"Reference",gbPrefix),nestedURI,nestedAnnotations);
				annotations.add(annotation);
			} else if (strLine.startsWith("AUTHORS")) {
				String annotationStr = strLine.replace("AUTHORS", "").trim();
				Annotation nestedAnnotation = new Annotation(new QName(gbNamespace,"authors",gbPrefix),annotationStr);
				nestedAnnotations.add(nestedAnnotation);
				annotation.setNestedAnnotations(nestedAnnotations);
			} else if (strLine.startsWith("TITLE")) {
				String annotationStr = strLine.replace("TITLE", "").trim();
				Annotation nestedAnnotation = new Annotation(new QName(gbNamespace,"title",gbPrefix),annotationStr);
				nestedAnnotations.add(nestedAnnotation);
				annotation.setNestedAnnotations(nestedAnnotations);
			} else if (strLine.startsWith("JOURNAL")) {
				String annotationStr = strLine.replace("JOURNAL", "").trim();
				Annotation nestedAnnotation = new Annotation(new QName(gbNamespace,"journal",gbPrefix),annotationStr);
				nestedAnnotations.add(nestedAnnotation);
				annotation.setNestedAnnotations(nestedAnnotations);
			} else if (strLine.startsWith("MEDLINE")) {
				String annotationStr = strLine.replace("MEDLINE", "").trim();
				Annotation nestedAnnotation = new Annotation(new QName(gbNamespace,"medline",gbPrefix),annotationStr);
				nestedAnnotations.add(nestedAnnotation);
				annotation.setNestedAnnotations(nestedAnnotations);
			} else if (strLine.startsWith("PUBMED")) {
				String annotationStr = strLine.replace("PUBMED", "").trim();
				Annotation nestedAnnotation = new Annotation(new QName(gbNamespace,"pubmed",gbPrefix),annotationStr);
				nestedAnnotations.add(nestedAnnotation);
				annotation.setNestedAnnotations(nestedAnnotations);
			} else if (strLine.startsWith("COMMENT")) {
				String annotationStr = strLine.replace("COMMENT", "").trim();
				annotation = new Annotation(new QName(gbNamespace,"comment",gbPrefix),annotationStr);
				annotations.add(annotation);
			} else if (strLine.startsWith("FEATURE")) {
				topCD = doc.createComponentDefinition(id, version, type);
				topCD.addRole(SequenceOntology.ENGINEERED_REGION);
				if (!description.equals("")) {
					topCD.setDescription(description);
				}
				topCD.setAnnotations(annotations);
				featureMode = true;
			} else if (strLine.startsWith("ORIGIN")) {
				seqMode = true;
				featureMode = false;
			} else {
				if (featureMode) {
					if (strLine.startsWith("/")) {
						String[] splitStr = strLine.split("=");
						String tag = splitStr[0].replace("/","");
						String value = splitStr[1];
						SequenceAnnotation sa = topCD.getSequenceAnnotation("annotation"+(featureCnt-1));
						annotation = new Annotation(new QName(gbNamespace,tag,gbPrefix),value);
						sa.addAnnotation(annotation);
						continue;
					}
					strLine = strLine.replace(", ",",");
					String[] strSplit = strLine.split("\\s+");
					URI role = convertGenBanktoSO(strSplit[0]);
					ComponentDefinition feature = 
							doc.createComponentDefinition("feature"+featureCnt, version, type);
					feature.addRole(role);
					String range = strSplit[1];
					OrientationType orientation = OrientationType.INLINE;
					if (range.startsWith("complement")) {
						orientation = OrientationType.REVERSECOMPLEMENT;
						range = range.replace("complement(", "").replace(")","");
					}
					if (range.startsWith("join")) {
						range = range.replace("join(", "").replace(")","");
						String[] ranges = range.split(",");
						int rangeCnt = 0;
						SequenceAnnotation sa =  null;
						for (String r : ranges) {
							// TODO: complements within join
							r = r.replace("<","").replace(">", ""); // TODO: need to handle these properly
							String[] rangeSplit = r.split("\\.\\.");
							int start = Integer.parseInt(rangeSplit[0]);
							int end = Integer.parseInt(rangeSplit[1]);
							if (rangeCnt==0) {
								sa = topCD.createSequenceAnnotation("annotation"+featureCnt,"range"+rangeCnt,
											start,end,orientation);
								sa.setComponent("feature"+featureCnt);
							} else if (sa != null) {
								sa.addRange("range"+rangeCnt, start, end, orientation);
							}
							rangeCnt++;							
						}
					} else if (range.contains("^")) {
						String[] rangeSplit = range.split("\\^");
						int at = Integer.parseInt(rangeSplit[0]);
						SequenceAnnotation sa = 
							topCD.createSequenceAnnotation("annotation"+featureCnt,"cut",at,orientation);
						sa.setComponent("feature"+featureCnt);
					} else {
						range = range.replace("<","").replace(">", ""); // TODO: need to handle these properly
						String[] rangeSplit = range.split("\\.\\.");
						int start = Integer.parseInt(rangeSplit[0]);
						int end = Integer.parseInt(rangeSplit[1]);
						SequenceAnnotation sa = 
							topCD.createSequenceAnnotation("annotation"+featureCnt,"range",start,end,orientation);
						sa.setComponent("feature"+featureCnt);
					}
					featureCnt++;
				}
				if (seqMode) {
					String[] strSplit = strLine.split(" ");
					for (int i = 1; i < strSplit.length; i++) {
						elements += strSplit[i];
					}
				}
			}
		}
		Sequence sequence = doc.createSequence(id+"_seq", version, elements, Sequence.IUPAC_DNA);
		topCD.addSequence(sequence);
		createSubComponentDefinitions(doc,topCD,type,elements,version);
		br.close();
	}

	/**
	 * Set the specified authority as the prefix to all member's identity
	 *
	 *  @param URIprefix
	 */
	public static void setURIPrefix(String uRIPrefix) {
		URIPrefix = uRIPrefix;
	}


}

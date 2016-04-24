package org.sbolstandard.core2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;

public class FASTA {

	// "look-ahead" line
	private static String nextLine = null;
	private static final int lineWidth = 80;

	//private static int lineCounter = 0;
	
	private static void writeFASTALine(Writer w, String line, int margin) throws IOException {
		if (line.length() < margin) {
			w.write(line+"\n");
		} else {
			String spaces = "";
			int breakPos = line.substring(0,margin-1).lastIndexOf(" ")+1;
			if (breakPos==0 || breakPos < 0.75*margin) breakPos = margin-1;
			w.write(line.substring(0, breakPos)+"\n");
			int i = breakPos;
			while (i < line.length()) {
				if ((i+(margin)) < line.length()) {
					breakPos = line.substring(i,i+(margin)-1).lastIndexOf(" ")+1;
					if (breakPos==0 || breakPos < 0.65*margin) breakPos = (margin)-1;
					w.write(spaces+line.substring(i,i+breakPos)+"\n");
				} else {
					w.write(spaces+line.substring(i)+"\n");
					breakPos = (margin)-1;
				}
				i+=breakPos;
			}
		}
	}

	/**
	 * Serializes a given Sequence and output the data the given output file name in FASTA format
	 * @param sequence a given Sequence
	 * @param filename the given output file name to serialize into
	 * @throws IOException input/output operation failed
	 * @throws SBOLConversionException violates conversion limitations
	 */
	public static void write(Sequence sequence, String filename) throws IOException, SBOLConversionException
	{
		write(sequence, new File(filename));
	}

	/**
	 * Serializes a given Sequence and output the data the given output file name in FASTA format
	 * @param document a given SBOLDocument
	 * @param filename the given output file name to serialize into
	 * @throws IOException input/output operation failed
	 * @throws SBOLConversionException violates conversion limitations
	 */
	public static void write(SBOLDocument document, String filename) throws IOException, SBOLConversionException
	{
		write(document, new File(filename));
	}

	/**
	 * Serializes a given Sequence and outputs the data to the given file in FASTA format.
	 * @param sequence a given Sequence
	 * @param file the output file to serialize into
	 * @throws IOException input/output operation failed
	 * @throws SBOLConversionException violates conversion limitations
	 */
	public static void write(Sequence sequence, File file) throws IOException, SBOLConversionException {
		FileOutputStream stream = new FileOutputStream(file);
		BufferedOutputStream buffer = new BufferedOutputStream(stream);
		write(sequence, buffer);
		stream.close();
		buffer.close();
	}
	
	/**
	 * Serializes all Sequences in an SBOLDocument and outputs the data to the given file in FASTA format.
	 * @param document a given SBOLDocument
	 * @param file the output file to serialize into
	 * @throws IOException input/output operation failed
	 * @throws SBOLConversionException violates conversion limitations
	 */
	public static void write(SBOLDocument document, File file) throws IOException, SBOLConversionException {
		FileOutputStream stream = new FileOutputStream(file);
		BufferedOutputStream buffer = new BufferedOutputStream(stream);
		write(document, buffer);
		stream.close();
		buffer.close();
	}

	/**
	 * Serializes a given Sequence and outputs the data from the serialization to the given output stream
	 * in FASTA format.
	 * @param sequence a given Sequence
	 * @param out the output stream to serialize into
	 * @throws IOException input/output operation failed
	 * @throws SBOLConversionException violates conversion limitations
	 */
	public static void write(Sequence sequence, OutputStream out) throws IOException, SBOLConversionException {
		Writer w = new OutputStreamWriter(out, "UTF-8");
		write(w,sequence);
		w.close();
	}
	
	/**
	 * Serializes all Sequence in an SBOLDocument to the given output stream in FASTA format.
	 * @param sequence a given Sequence
	 * @param out the output stream to serialize into
	 * @throws IOException input/output operation failed
	 * @throws SBOLConversionException violates conversion limitations
	 */
	public static void write(SBOLDocument document, OutputStream out) throws IOException, SBOLConversionException {
		Writer w = new OutputStreamWriter(out, "UTF-8");
		for (Sequence sequence : document.getSequences()) {
			write(w,sequence);
		}
		w.close();
	}
	
	private static void write(Writer w, Sequence sequence) throws IOException, SBOLConversionException {
		if (!sequence.getEncoding().equals(Sequence.IUPAC_DNA) &&
				!sequence.getEncoding().equals(Sequence.IUPAC_RNA) &&
				!sequence.getEncoding().equals(Sequence.IUPAC_PROTEIN)) {
			throw new SBOLConversionException("Sequence encoding is not in IUPAC DNA, RNA, or Protein formats.");
		}
		w.write("> " + sequence.getDisplayId() + " : " + sequence.getDescription() + "\n");
		writeFASTALine(w,sequence.getElements(),lineWidth);
	}

	private static String readFASTALine(BufferedReader br) throws IOException {
		String newLine = "";

		if (nextLine == null) {
			newLine = br.readLine();
			//lineCounter ++;

			if (newLine == null) return null;
			newLine = newLine.trim();
		} else {
			newLine = nextLine;
		}

		while (true) {
			nextLine = br.readLine();

			if (nextLine==null) return newLine;
			nextLine = nextLine.trim();
			return newLine;
		}
	}
	
	private static void read(SBOLDocument doc,InputStream in,String URIprefix,String displayId,String version,URI encoding)
	{

		// reset the global static variables needed for parsing
		nextLine = null;
		//lineCounter = 0;

		String strLine;
		StringBuilder sbSequence = new StringBuilder();
		String elements = null;
		String description = "";
		int count = 0;
		boolean sequenceMode = false;
		
		// using Java 7's try-with-resources statement
		try (
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
			) {
			
			while ((strLine = readFASTALine(br)) != null)   {
				strLine = strLine.trim();
	
				if (strLine.startsWith(">")) {
					if (sequenceMode) {
						sequenceMode = false;
						Sequence sequence = doc.createSequence(URIprefix,displayId+count,version,sbSequence.toString(),encoding);
						sequence.setDescription(description);
						description = "";
						sbSequence = new StringBuilder();
						count++;
					}
					description += strLine.replaceFirst(">", "").trim();
				} else if (strLine.startsWith(";")) {
					if (sequenceMode) {
						sequenceMode = false;
						Sequence sequence = doc.createSequence(URIprefix,displayId+count,version,sbSequence.toString(),encoding);
						sequence.setDescription(description);
						description = "";
						sbSequence = new StringBuilder();
						count++;
					}
					description += strLine.replaceFirst(";", "").trim();
				} else {
					sequenceMode = true;
					if(elements == null) { elements = new String(""); }
					String[] strSplit = strLine.split(" ");
					for (int i = 0; i < strSplit.length; i++) {
						sbSequence.append(strSplit[i]);
					}
				}
			}
			if (count!=0) displayId += count;
			Sequence sequence = doc.createSequence(URIprefix,displayId,version,sbSequence.toString(),encoding);
			sequence.setDescription(description);
		} catch(Exception e) {
			
		}
	}
	
	/**
	 * The read method imports all sequences (represented in FASTA format), stores 
	 * them in an SBOLDocument object, and returns the SBOLDocument object.
	 * 
	 * @param in  ... the input stream that contains the sequences in FASTA format
	 * @param URIprefix ... the URI prefix of the sequences
	 * @param displayId
	 * @param version ... the version of the sequences
	 * @param encoding ... the encoding of the sequences (i.e. DNA, RNA, or Protein)
	 * 
	 * @return an SBOLDocument object that contains the imported FASTA sequences as SBOL Sequence objects
	 * 
	 * @throws IOException
	 * @throws SBOLValidationException
	 */
	public static SBOLDocument read(InputStream in,String URIprefix,String displayId,String version,URI encoding) 
	{
		
		/*
		 * EO: it's unclear how we map the FASTA description to SBOL displayID/description? 
		 * Shouldn't we just use the FASTA description as both displayID and description?
		 */
		
		SBOLDocument doc = new SBOLDocument();
		doc.setCreateDefaults(true);
		
		/*---------------
		 * Update: 
		 * When using the doc.getSequence method later, then the following exception 
		 * was thrown: "The defaultURIprefix is not set. Please set it to a non-null value"
		 *
		 * Hence, we set the URIprefix of the document as provided by the user
		 *---------------*/

		// check that the caller provided a valid URIprefix
		if(null == URIprefix) {
			throw new IllegalArgumentException("Invalid URIprefix! Cannot be null.");
		} else if(URIprefix.isEmpty()) {
			throw new IllegalArgumentException("Invalid URIprefix! Cannot be empty.");
		}
		// TODO: add more URIprefix validations (e.g. well-formed HTTP)???
		
		// if the URIprefix is valid, than we set it in the document 
		doc.setDefaultURIprefix(URIprefix);
		
		// parse the stream's content
		read(doc,in,URIprefix,displayId,version,encoding);
		
		// lastly, return the SBOLDocument object that contains 
		// all sequences represented as SBOL objects
		return doc;
	}
	
	/**
	 * Takes in the given FASTA file and converts the file to an SBOLDocument.
	 *
	 * @param file the given FASTA filename
	 * @return the converted SBOLDocument instance
	 * @throws SBOLConversionException violates conversion limitations
	 * @throws SBOLValidationException violates sbol validation rule
	 * @throws IOException input/output operation failed
	 */
	public static SBOLDocument read(File file,String URIprefix,String displayId,String version,URI encoding) 
			throws IOException
	{
		FileInputStream stream     = new FileInputStream(file);
		BufferedInputStream buffer = new BufferedInputStream(stream);
		return read(buffer,URIprefix,displayId,version,encoding);
	}
	
	/**
	 * Takes in the given FASTA filename and converts the file to an SBOLDocument.
	 * <p>
	 * This method calls {@link #read(File)}.
	 *
	 * @param fileName the given FASTA filename
	 * @return the converted SBOLDocument
	 * @throws SBOLConversionException violates conversion limitations
	 * @throws SBOLValidationException violates sbol validation rule
	 * @throws IOException input/output operation failed
	 */
	public static SBOLDocument read(String fileName,String URIprefix,String displayId,String version,URI encoding) 
			throws IOException
	{
		return read(new File(fileName),URIprefix,displayId,version,encoding);
	}

	public static void main(String[] args) throws SBOLConversionException, IOException {
		SBOLDocument doc = read("/Users/myers/Downloads/sample.fasta","http://dummy.org","dummy","",Sequence.IUPAC_DNA);
		//doc.write(System.out);
		write(doc, System.out);
	}

}

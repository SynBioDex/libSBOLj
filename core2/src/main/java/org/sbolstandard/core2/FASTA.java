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
			throws IOException, SBOLValidationException {

		// reset the global static variables needed for parsing
		nextLine = null;
		//lineCounter = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		StringBuilder sbSequence = new StringBuilder();
		String elements = null;
		String description = "";
		int count = 0;
		boolean sequenceMode = false;
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
		br.close();
	}
	
	/**
	 * Takes in a given FASTA InputStream and converts the file to an SBOLDocument.
	 *
	 * @param in the given FASTA filename
	 * @return the converted SBOLDocument instance
	 * @throws SBOLConversionException violates conversion limitations
	 * @throws SBOLValidationException violates sbol validation rule
	 * @throws IOException input/output operation failed
	 */
	public static SBOLDocument read(InputStream in,String URIprefix,String displayId,String version,URI encoding) 
			throws IOException, SBOLValidationException
	{
		SBOLDocument doc = new SBOLDocument();
		doc.setCreateDefaults(true);
		read(doc,in,URIprefix,displayId,version,encoding);
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
			throws IOException, SBOLValidationException
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
			throws IOException, SBOLValidationException
	{
		return read(new File(fileName),URIprefix,displayId,version,encoding);
	}

	public static void main(String[] args) throws SBOLConversionException, IOException, SBOLValidationException {
		SBOLDocument doc = read("/Users/myers/Downloads/sample.fasta","http://dummy.org","dummy","",Sequence.IUPAC_DNA);
		//doc.write(System.out);
		write(doc, System.out);
	}

}

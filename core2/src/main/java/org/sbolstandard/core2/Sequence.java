package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.*;

import java.net.URI;

/**
 * Represents the SBOL Sequence data model.
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @author Chris Myers
 * @version 2.1
 */
public class Sequence extends TopLevel{

	private String elements;
	private URI encoding;

	/**
	 * Nomenclature for Incompletely Specified Bases in Nucleic Acid Sequences
	 * (<a href="http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html">IUPAC_DNA</a>).
	 */
	public static final URI IUPAC_DNA = URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html");

	/**
	 * Nomenclature for Incompletely Specified Bases in Nucleic Acid Sequences
	 * (<a href="http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html">IUPAC_RNA</a>).
	 */
	public static final URI IUPAC_RNA = URI.create("http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html");

	/**
	 * Nomenclature and Symbolism for Amino Acids and Peptides
	 * (<a href="http://www.chem.qmul.ac.uk/iupac/AminoAcid/">IUPAC_PROTEIN</a>).
	 */
	public static final URI IUPAC_PROTEIN = URI.create("http://www.chem.qmul.ac.uk/iupac/AminoAcid/");

	/**
	 * SMILES was originally developed as a proprietary specification by Daylight Chemical
	 * Information Systems Since the introduction of SMILES in the late 1980’s, it has become
	 * widely accepted as a defacto standard for exchange of molecular structures
	 * (<a href="http://www.opensmiles.org/opensmiles.html">SMILES</a>).
	 * Many independent SMILES software packages have been written in C, C++, Java, Python, LISP,
	 * and probably even FORTRAN.
	 */
	public static final URI SMILES = URI.create("http://www.opensmiles.org/opensmiles.html");

	Sequence(URI identity, String elements, URI encoding) throws SBOLValidationException {
		super(identity);
		setEncoding(encoding);
		setElements(elements);
	}

	private Sequence(Sequence sequence) throws SBOLValidationException {
		//super(sequence.getIdentity());
		super(sequence);
		this.setEncoding(sequence.getEncoding());
		this.setElements(sequence.getElements());
	}

	//	public Sequence(String authority, String Id, String elements, URI encoding) {
	//		super(authority, Id);
	//		setElements(elements);
	//		setEncoding(encoding);
	//	}

	/**
	 * Returns the elements property of this Sequence object.
	 * 
	 * @return the elements property of this Sequence object.
	 */
	public String getElements() {
		return elements;
	}
	
	/**
	 * Sets the elements property to the given argument.  
	 * <p>
	 * If this Sequence object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param elements the given elements property
	 * @throws SBOLValidationException see {@link SBOLValidationException}
	 */
	public void setElements(String elements) throws SBOLValidationException {
		if (elements == null) {
			throw new SBOLValidationException("sbol-10402",this);
		}
		this.elements = elements;
		if (!SBOLValidate.checkSequenceEncoding(this)) {
			throw new SBOLValidationException("sbol-10405", this);
		}
	}
	
	/**
	 * Returns the encoding property of this Sequence object.
	 * 
	 * @return the encoding property of this Sequence object.
	 */
	public URI getEncoding() {
		return encoding;
	}

	/**
	 * Sets the encoding property to the given argument.  
	 * <p>
	 * If this Sequence object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param encoding the given encoding property
	 * @throws SBOLValidationException see {@link SBOLValidationException} 
	 */
	public void setEncoding(URI encoding) throws SBOLValidationException {
		if (encoding == null) {
			throw new SBOLValidationException("sbol-10403",this);
		}
		this.encoding = encoding;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((elements == null) ? 0 : elements.hashCode());
		result = prime * result + ((encoding == null) ? 0 : encoding.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sequence other = (Sequence) obj;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		if (encoding == null) {
			if (other.encoding != null)
				return false;
		} else if (!encoding.equals(other.encoding))
			return false;
		return true;
	}

	@Override
	protected Sequence deepCopy() throws SBOLValidationException {
		return new Sequence(this);
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	protected Sequence copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		Sequence cloned = this.deepCopy();
		cloned.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		cloned.setDisplayId(displayId);
		cloned.setVersion(version);
		URI newIdentity = createCompliantURI(URIprefix,displayId,version);
		if (!this.getIdentity().equals(newIdentity)) {
			cloned.setWasDerivedFrom(this.getIdentity());
		} else {
			cloned.setWasDerivedFrom(this.getWasDerivedFrom());
		}
		cloned.setIdentity(newIdentity);
		return cloned;
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#checkDescendantsURIcompliance()
	 */
	@Override
	protected void checkDescendantsURIcompliance() throws SBOLValidationException {
		URIcompliance.isTopLevelURIformCompliant(this.getIdentity());
	}
	
	/**
	 * Perform the reverse complement of a sequence encoded using IUPAC_DNA
	 * @param elements sequence to reverse complement 
	 * @param type DNA or RNA type
	 * @return the reverse complement of a sequence encoded using IUPAC_DNA
	 */
	public static String reverseComplement(String elements,URI type) {
		String reverse = "";
		for (int i = elements.length()-1; i >= 0; i--) {
			if (elements.charAt(i)=='a') {
				if (type.equals(ComponentDefinition.DNA)) {
					reverse += 't';
				} else {
					reverse += 'u';
				}
			} else if ((elements.charAt(i)=='t')||(elements.charAt(i)=='u')) {
				reverse += 'a';
			} else if (elements.charAt(i)=='g') {
				reverse += 'c';
			} else if (elements.charAt(i)=='c') {
				reverse += 'g';
			} else if (elements.charAt(i)=='r') {
				reverse += 'y';
			} else if (elements.charAt(i)=='y') {
				reverse += 'r';
			} else if (elements.charAt(i)=='s') {
				reverse += 'w';
			} else if (elements.charAt(i)=='w') {
				reverse += 's';
			} else if (elements.charAt(i)=='k') {
				reverse += 'm';
			} else if (elements.charAt(i)=='m') {
				reverse += 'k';
			} else if (elements.charAt(i)=='b') {
				reverse += 'v';
			} else if (elements.charAt(i)=='v') {
				reverse += 'b';
			} else if (elements.charAt(i)=='d') {
				reverse += 'h';
			} else if (elements.charAt(i)=='h') {
				reverse += 'd';
			} else if (elements.charAt(i)=='n') {
				reverse += 'n';
			} else if (elements.charAt(i)=='.') {
				reverse += '.';
			} else if (elements.charAt(i)=='-') {
				reverse += '-';
			}
		}
		return reverse;
	}

	@Override
	public String toString() {
		return "Sequence ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ ", encoding=" + encoding 
				+ ", elements=" + elements  
				+ "]";
	}
	

}

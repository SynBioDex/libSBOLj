package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.*;

import java.net.URI;

/**
 * @author Zhen Zhang
 * @author Tramy Nguyen
 * @author Nicholas Roehner
 * @author Matthew Pocock
 * @author Goksel Misirli
 * @author Chris Myers
 * @version 2.0-beta
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
	
	/**
	 * Creates a Sequence instance with the given arguments.
	 * <p>
	 * If the given {@code prefix} does not end with one of the following delimiters: "/", ":", or "#", then
	 * "/" is appended to the end of it.
	 * <p>
	 * This method requires the given {@code prefix}, {@code displayId}, and {@code version} are not
	 * {@code null} and valid.
	 * <p>
	 * A Sequence instance is created with a compliant URI. This URI is composed from
	 * the given {@code prefix}, the given {@code displayId}, and {@code version}.
	 * The display ID, persistent identity, and version fields of this instance
	 * are then set accordingly.
	 *
	 * @param prefix
	 * @param displayId
	 * @param version
	 * @param elements
	 * @param encoding
	 * @throws SBOLValidationException if the defaultURIprefix is {@code null}
	 * @throws SBOLValidationException if the given {@code URIprefix} is {@code null}
	 * @throws SBOLValidationException if the given {@code URIprefix} is non-compliant
	 * @throws SBOLValidationException if the given {@code displayId} is invalid
	 * @throws SBOLValidationException if the given {@code version} is invalid
	 * @throws SBOLValidationException if the sequence {@code elements} invalid for specified {@code encoding}.
	 */
	Sequence(String prefix,String displayId,String version, String elements, URI encoding) throws SBOLValidationException {
		this(URIcompliance.createCompliantURI(prefix, displayId, version), elements, encoding);
		prefix = URIcompliance.checkURIprefix(prefix);
		validateIdVersion(displayId, version);
		setDisplayId(displayId);
		setPersistentIdentity(createCompliantURI(prefix, displayId, ""));
		setVersion(version);
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
	 * Returns the {@code elements} property of this Sequence object.
	 * 
	 * @return the {@code elements} property of this Sequence object.
	 */
	public String getElements() {
		return elements;
	}
	
	/**
	 * Sets the {@code elements} property to the given argument.  
	 * <p>
	 * If this Sequence object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param elements
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws SBOLValidationException if the given {@code elements} argument is {@code null}
	 */
	public void setElements(String elements) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (elements == null) {
			//throw new SBOLValidationException("Sequence is required to have elements.");
			throw new SBOLValidationException("sbol-10402",this);
		}
		this.elements = elements;
		if (!SBOLValidate.checkSequenceEncoding(this)) {
//			throw new SBOLValidationException("Sequence '" + this.getIdentity() + "' that uses encoding " + this.getEncoding() + 
//					" does not have a valid sequence.");
			throw new SBOLValidationException("sbol-10406", this);
			// TODO: (Validation) print this.getEncoding too 
		}
	}
	
	/**
	 * Returns the {@code encoding} property of this Sequence object.
	 * 
	 * @return the {@code encoding} property of this Sequence object.
	 */
	public URI getEncoding() {
		return encoding;
	}

	/**
	 * Sets the {@code encoding} property to the given argument.  
	 * <p>
	 * If this Sequence object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 * 
	 * @param encoding
	 * @throws SBOLValidationException if the associated SBOLDocument is not compliant.
	 * @throws SBOLValidationException if the given {@code encoding} argument is {@code null}
	 */
	public void setEncoding(URI encoding) throws SBOLValidationException {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (encoding == null) {
			// throw new SBOLValidationException("Sequence is required to have an encoding.");
			throw new SBOLValidationException("sbol-10403");
			// TODO: (Validation) print URI for encoding
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
	protected boolean checkDescendantsURIcompliance() {
		return isTopLevelURIformCompliant(this.getIdentity());
	}

	@Override
	public String toString() {
		return "Sequence [elements=" + elements + ", encoding=" + encoding + ", identity="
				+ identity + ", displayId=" + displayId + ", name=" + name + ", description="
				+ description + "]";
	}

}

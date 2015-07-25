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

	Sequence(URI identity, String elements, URI encoding) {
		super(identity);
		setElements(elements);
		setEncoding(encoding);
	}

	private Sequence(Sequence sequence) {
		super(sequence.getIdentity());
		this.setElements(sequence.getElements());
		this.setEncoding(sequence.getEncoding());
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
	 * @throws IllegalArgumentException if the given {@code elements} argument is {@code null}
	 */
	public void setElements(String elements) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (elements == null) {
			throw new IllegalArgumentException("Sequence is required to have elements.");
		}
		this.elements = elements;
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
	 * @throws IllegalArgumentException if the given {@code encoding} argument is {@code null}
	 */
	public void setEncoding(URI encoding) {
		if (sbolDocument!=null) sbolDocument.checkReadOnly();
		if (encoding == null) {
			throw new IllegalArgumentException("Sequence is required to have an encoding.");
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
	protected Sequence deepCopy() {
		return new Sequence(this);
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	protected Sequence copy(String URIprefix, String displayId, String version) {
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


	//	/**
	//	 * @param newDisplayId
	//	 * @return
	//	 */
	//	public Sequence copy(String newDisplayId) {
	//		Sequence cloned = (Sequence) super.copy(newDisplayId);
	//		cloned.updateCompliantURI(newDisplayId);
	//		return cloned;
	//	}
	//
	//	/**
	//	 * Get a deep copy of the object first, and set its major version to the specified value, and minor version to "0".
	//	 * @param newVersion
	//	 * @return the copied {@link ComponentDefinition} instance with the specified major version.
	//	 */
	//	public Sequence newVersion(String newVersion) {
	//		Sequence cloned = (Sequence) super.newVersion(newVersion);
	//		cloned.updateVersion(newVersion);
	//		return cloned;
	//	}

	//	/* (non-Javadoc)
	//	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateVersion(java.lang.String)
	//	 */
	//	public void updateVersion(String newVersion) {
	//		super.updateVersion(newVersion);
	//		if (isURIcompliant(this.getIdentity())) {
	//		}
	//	}
}

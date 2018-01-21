package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

import java.net.URI;

public class Attachment extends TopLevel {
	
	/**
	 * The source is a link to the external file and is REQUIRED.
	 */
	private URI source;
	
	/** 
	 * The format is an ontology term indicating the format of the file. 
	 * It is RECOMMENDED to use the EDAM ontology for file formats, which
	 * includes a variety of experimental data formats. The format is an 
	 * OPTIONAL field.
	 */
	private URI format;
	
	/**
	 * The size is a long integer indicating the file size in bytes. This
	 * may be used by client applications accessing files over RESTful APIs.
	 * This field is OPTIONAL.
	 * 
	 */
	private long size;
	
	/**
	 * The hash is a string used to retrieve files from a cache. This field
	 * is OPTIONAL.
	 */
	private String hash;

	Attachment(URI identity, URI source) throws SBOLValidationException {
		super(identity);
		
		this.source = source;
	}
	
	/**
	 * @param attachment
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in any of the
	 *             following constructors or methods:
	 *             <ul>
	 *             <li>{@link TopLevel#TopLevel(TopLevel)},</li>
	 *             </ul>
	 */
	private Attachment(Attachment attachment) throws SBOLValidationException {
		super(attachment);

		this.source = attachment.getSource();
		this.format = attachment.getFormat();
		this.size = attachment.getSize();
		this.hash = attachment.getHash();
	}
	
	/**
	 * Returns this attachment's source property
	 *
	 * @return the URI representing the source property
	 */
	public URI getSource() {
		return this.source;
	}
	
	/**
	 * Sets the source property to the given URI
	 */
	//TODO: validation?
	public void setSource(URI source) {
		this.source = source;
	}
	
	/**
	 * Checks if the format property is set.
	 * 
	 * @return {@code true} if it is not {@code null}, {@code false} otherwise
	 */
	public boolean isSetFormat() {
		return format != null;
	}
	
	/**
	 * Returns this attachment's format property
	 *
	 * @return the URI representing the format property
	 */
	public URI getFormat() {
		return this.format;
	}

	/**
	 * Sets the URI of this attachment's format property
	 *
	 * @param format
	 *            the given URI to set to
	 * @throws SBOLValidationException 
	 * 				on SBOL validation rule violation XXXXX.
	 */
	public void setFormat(URI format) throws SBOLValidationException {
		this.format = format;
	}
	
	/**
	 * Sets the format property of the attachment to {@code null}.
	 */
	public void unsetFormat() {
		this.format = null;
	}
	
	/**
	 * Checks if the size property is set.
	 * 
	 * @return {@code true} if it is not {@code null}, {@code false} otherwise
	 */
	public boolean isSetSize() {
		//TODO: what should the default value be?
		return size != -1;
	}
	
	/**
	 * Returns this attachment's size property
	 *
	 * @return the size property
	 */
	public long getSize() {
		return this.size;
	}

	/**
	 * Sets the value of this attachment's size property
	 *
	 * @param size
	 *            the given size to set to
	 * @throws SBOLValidationException 
	 * 				on SBOL validation rule violation XXXXX.
	 */
	public void setSize(long size) throws SBOLValidationException {
		this.size = size;
	}
	
	/**
	 * Sets the size property of the attachment to -1.
	 */
	public void unsetSize() {
		this.size = -1;
	}
	
	/**
	 * Checks if the hash property is set.
	 * 
	 * @return {@code true} if it is not {@code null}, {@code false} otherwise
	 */
	public boolean isSetHash() {
		return hash != null;
	}
	
	/**
	 * Returns this attachment's hash property
	 *
	 * @return the hash property
	 */
	public String getHash() {
		return this.hash;
	}

	/**
	 * sets this attachment's hash property
	 *
	 * @param hash
	 *            the given hash to set to
	 * @throws SBOLValidationException 
	 * 				on SBOL validation rule violation XXXXX.
	 */
	public void setHash(String hash) throws SBOLValidationException {
		this.hash = hash;
	}
	
	/**
	 * Sets the hash property of the attachment to {@code null}.
	 */
	public void unsetHash() {
		this.hash = null;
	}

	@Override
	Attachment deepCopy() throws SBOLValidationException {
		return new Attachment(this);
	}
	
	void copy(Attachment attachment) throws SBOLValidationException {
		((TopLevel) this).copy((TopLevel) attachment);
		
		if (attachment.isSetFormat()) {
			this.setFormat(attachment.getFormat());
		}
		if (attachment.isSetSize()) {
			this.setSize(attachment.getSize());
		}
		if (attachment.isSetHash()) {
			this.setHash(attachment.getHash());
		}
		
		this.setSource(attachment.getSource());
	}

	/**
	 * @throws SBOLValidationException
	 *             if an SBOL validation rule violation occurred in any of the
	 *             following constructors or methods:
	 *             <ul>
	 *             <li>{@link #deepCopy()},</li>
	 *             <li>{@link URIcompliance#createCompliantURI(String, String, String)},</li>
	 *             <li>{@link #setDisplayId(String)},</li>
	 *             <li>{@link #setVersion(String)},</li>
	 *             <li>{@link #setWasDerivedFrom(URI)},</li>
	 *             <li>{@link #setIdentity(URI)}</li>
	 *             </ul>
	 */
	@Override
	Attachment copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		Attachment cloned = this.deepCopy();
		cloned.setPersistentIdentity(createCompliantURI(URIprefix, displayId, ""));
		cloned.setDisplayId(displayId);
		cloned.setVersion(version);
		URI newIdentity = createCompliantURI(URIprefix, displayId, version);

		if (!this.getIdentity().equals(newIdentity)) {
			cloned.addWasDerivedFrom(this.getIdentity());
		} else {
			cloned.setWasDerivedFroms(this.getWasDerivedFroms());
		}

		cloned.setIdentity(newIdentity);

		return cloned;
	}
	
	@Override
	/**
	 * @throws SBOLValidationException
	 *             an SBOL validation rule violation occurred in either of the
	 *             following methods:
	 *             <ul>
	 *             <li>{@link URIcompliance#isChildURIcompliant(Identified, Identified)}.</li>
	 *             </ul>
	 */
	void checkDescendantsURIcompliance() throws SBOLValidationException {
		// TODO probably not needed but I'll leave it here in case I'm wrong
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode() * prime;

		result *= this.source.hashCode();
		result *= this.format != null ? this.format.hashCode() : 1;
		result *= this.size >= 0 ? new Long(this.size).hashCode() : 1;
		result *= this.hash != null ? this.hash.hashCode() : 1;

		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sbolstandard.core2.abstract_classes.Documented#equals(java.lang.Instance)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Attachment other = (Attachment) obj;
		if (!this.source.equals(other.getSource()))
			return false;
		if (!this.isSetFormat() || !this.format.equals(other.getFormat()))
			return false;
		if (this.size != other.size)
			return false;
		if (!this.isSetHash() || !this.hash.equals(other.getHash()))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return "Attachment [" + super.toString()
				+ ("source=" + this.getSource())
				+ (this.isSetFormat() ? ", format=" + this.getFormat() : "")
				+ (this.isSetSize() ? ", size=" + this.getSize() : "")
				+ (this.isSetHash() ? ", hash=" + this.getHash() : "")
				+ "]";
	}

}

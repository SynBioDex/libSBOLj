package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;
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

public class Model extends TopLevel {

	private URI source;
	private URI language;
	private URI framework;
	
	/**
	 * Systems Biology Markup Language (SBML), the standard XML format for models of biological
	 * processes such as for example metabolism, cell signaling, and gene regulation
	 * (<a href="http://identifiers.org/edam/format_2585">SBML</a>).
	 */
	public static final URI SBML = URI.create("http://identifiers.org/edam/format_2585");

	/**
	 * CellML, the format for mathematical models of biological and other networks
	 * (<a href="http://identifiers.org/edam/format_3240">CELLML</a>).
	 */
	public static final URI CELLML = URI.create("http://identifiers.org/edam/format_3240");

	/**
	 * BioPAX is an exchange format for pathway data, with its data model defined in OWL
	 * (<a href="http://identifiers.org/edam/format_3156">BIOPAX</a>).
	 */
	public static final URI BIOPAX = URI.create("http://identifiers.org/edam/format_3156");


	Model(URI identity,URI source, URI language, URI framework) throws SBOLValidationException {
		super(identity);
		setSource(source);
		setLanguage(language);
		setFramework(framework);
	}

	private Model(Model model) throws SBOLValidationException {
		super(model);
		this.setSource(model.getSource());
		this.setLanguage(model.getLanguage());
		this.setFramework(model.getFramework());
	}

	/**
	 * Returns the URI of the source property of this Model object.
	 *
	 * @return the URI of the source property of this Model object
	 */
	public URI getSource() {
		return source;
	}

	/**
	 * Sets the {@code source} property to the given argument.
	 * <p>
	 * If this Model object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param source a URI reference to the source file for a model.
	 * @throws SBOLValidationException if the given {@code source} argument is {@code null}
	 */
	public void setSource(URI source) throws SBOLValidationException {
		if (source==null) {
			throw new SBOLValidationException("sbol-11502", this);
		}
		this.source = source;
	}

	/**
	 * Returns the URI of the language property of this Model object.
	 *
	 * @return the URI of the language property of this Model object
	 */
	public URI getLanguage() {
		return language;
	}

	/**
	 * Sets the {@code language} property to the given argument.
	 * <p>
	 * If this Model object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param language a URI that specifies the language in which the model is implemented.
	 * @throws SBOLValidationException if the given {@code language} argument is {@code null}
	 */
	public void setLanguage(URI language) throws SBOLValidationException {
		if (language==null) {
			throw new SBOLValidationException("sbol-11504",this);
		}
		this.language = language;
	}

	/**
	 * Returns the URI of the framework property of this Model object.
	 *
	 * @return the URI of the framework property of this Model object
	 */
	public URI getFramework() {
		return framework;
	}

	/**
	 * Sets the {@code framework} property to the given argument.
	 * <p>
	 * If this Model object belongs to an SBOLDocument instance, then
	 * the SBOLDcouement instance
	 * is checked for compliance first. Only a compliant SBOLDocument instance
	 * is allowed to be edited.
	 *
	 * @param framework a URI that specifies the framework in which the model is implemented.
	 * @throws SBOLValidationException if the given {@code framework} argument is {@code null}
	 */
	public void setFramework(URI framework) throws SBOLValidationException {
		if (framework==null) {
			throw new SBOLValidationException("sbol-11508", this);
		}
		this.framework = framework;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((framework == null) ? 0 : framework.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		Model other = (Model) obj;
		if (framework == null) {
			if (other.framework != null)
				return false;
		} else if (!framework.equals(other.framework))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	@Override
	protected Model deepCopy() throws SBOLValidationException {
		return new Model(this);
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#copy(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	Model copy(String URIprefix, String displayId, String version) throws SBOLValidationException {
		Model cloned = this.deepCopy();
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

	@Override
	public String toString() {
		return "Model ["
				+ "identity=" + identity 
				+ (this.isSetDisplayId()?", displayId=" + displayId:"") 
				+ (this.isSetName()?", name=" + name:"")
				+ (this.isSetDescription()?", description=" + description:"") 
				+ ", source=" + source 
				+ ", language=" + language 
				+ ", framework=" + framework
				+ "]";
	}

}

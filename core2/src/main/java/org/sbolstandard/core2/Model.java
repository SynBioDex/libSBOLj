package org.sbolstandard.core2;

import java.net.URI;
import java.util.List;

/**
 * @author Ernst Oberortner
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Model extends TopLevel {
		
	private URI source;
	private URI language;
	private URI framework;
	private List<URI> roles;

	public Model(URI identity,URI source, URI language, URI framework, List<URI> roles) {
		super(identity);		
		setSource(source);
		setLanguage(language);
		setFramework(framework);
		setRoles(roles);
	}

	public URI getSource() {
		return source;
	}

	public void setSource(URI source) {
		this.source = source;
	}

	public URI getLanguage() {
		return language;
	}

	public void setLanguage(URI language) {
		this.language = language;
	}

	public URI getFramework() {
		return framework;
	}

	public void setFramework(URI framework) {
		this.framework = framework;
	}

	public List<URI> getRoles() {
		return roles;
	}

	public void setRoles(List<URI> roles) {
		this.roles = roles;
	}

}

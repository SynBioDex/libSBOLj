package org.sbolstandard.core2;

import java.net.URI;
import java.util.Set;

import org.sbolstandard.core2.abstract_classes.TopLevel;

/**
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Model extends TopLevel {
		
	private URI source;
	private URI language;
	private URI framework;
	private Set<URI> roles;

	public Model(URI identity,URI source, URI language, URI framework, Set<URI> roles) {
		super(identity);		
		setSource(source);
		setLanguage(language);
		setFramework(framework);
		setRoles(roles);
	}

	/**
	 * Returns the field variable <code>source</code> to the specified element.
	 * @return the field variable <code>source</code> to the specified element
	 */
	public URI getSource() {
		return source;
	}

	/**
	 * Sets the field variable <code>source</code> to the specified element.
	 * @param source
	 */
	public void setSource(URI source) {
		this.source = source;
	}

	/**
	 * Returns the field variable <code>language</code>.
	 * @return the field variable <code>language</code>
	 */
	public URI getLanguage() {
		return language;
	}

	/**
	 * Sets the field variable <code>language</code> to the specified element.
	 * @param language
	 */
	public void setLanguage(URI language) {
		this.language = language;
	}

	/**
	 * Returns the field variable <code>framework</code>.
	 * @return the field variable <code>framework</code>
	 */
	public URI getFramework() {
		return framework;
	}
	
	/**
	 * Sets the field variable <code>framework</code> to the specified element.
	 * @param framework
	 */
	public void setFramework(URI framework) {
		this.framework = framework;
	}
	
	/**
	 * Adds the specified element to the set <code>roles</code> if it is not already present. 
	 * @param roleURI
	 * @return <code>true</code> if this set did not already contain the specified element.
	 */
	public boolean addRole(URI roleURI) {
		return roles.add(roleURI);
	}
	
	/**
	 * Removes the specified element from the set <code>roles</code> if it is present.
	 * @param roleURI
	 * @return <code>true<code> if this set contained the specified element
	 */
	public boolean removeRole(URI roleURI) {
		return roles.remove(roleURI);
	}
	
	/**
	 * Sets the field variable <code>roles</code> to the specified element.
	 * @param roles
	 */
	public void setRoles(Set<URI> roles) {
		this.roles = roles;
	}
	
	/**
	 * Returns the field variable <code>roles</code>.
	 * @return
	 */
	public Set<URI> getRoles() {
		return roles;
	}
	
	/**
	 * Returns true if the set <code>roles</code> contains the specified element. 
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean containsRole(URI rolesURI) {
		return roles.contains(rolesURI);
	}
	
	/**
	 * Removes all entries of the list of <code>roles</code> instances owned by this instance. 
	 * The list will be empty after this call returns.
	 */
	public void clearRoles() {
		roles.clear();
	}

}

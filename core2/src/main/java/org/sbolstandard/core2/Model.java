package org.sbolstandard.core2;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.sbolstandard.core2.abstract_classes.TopLevel;
import org.sbolstandard.core2.util.UriCompliance;

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
	
	private Model(Model model) {
		super(model);
		this.setSource(model.getSource());
		this.setLanguage(model.getLanguage());
		this.setFramework(model.getFramework());
		Set<URI> roles = new HashSet<URI>();
		for (URI role : model.getRoles()) {
			roles.add(role);
		}
		this.setRoles(roles);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((framework == null) ? 0 : framework.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	@Override
	protected Model deepCopy() {
		return new Model(this);
	}
	
	/**
	 * @param newDisplayId
	 * @return
	 */
	public Model copy(String newDisplayId) {
		Model cloned = (Model) this.deepCopy();
		cloned.updateDisplayId(newDisplayId);
		return cloned;
	}

	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateDisplayId(java.lang.String)
	 */
	protected void updateDisplayId(String newDisplayId) {
		super.updateDisplayId(newDisplayId);
		if (UriCompliance.isTopLevelURIcompliant(this.getIdentity())) {				
			// TODO Change all of its children's displayIds in their URIs.
		}
	}
	
	/**
	 * Get a deep copy of the object first, and set its major version to the specified value, and minor version to "0". 
	 * @param newVersion
	 * @return the copied {@link ComponentDefinition} instance with the specified major version.
	 */
	public Model newVersion(String newVersion) {
		Model cloned = (Model) super.newVersion(newVersion);		
		cloned.updateVersion(newVersion);
		return cloned;
	}
	
	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.TopLevel#updateVersion(java.lang.String)
	 */
	protected void updateVersion(String newVersion) {
		super.updateVersion(newVersion);
		if (UriCompliance.isTopLevelURIcompliant(this.getIdentity())) {
			// TODO Change all of its children's versions in their URIs.
		}
	}
}

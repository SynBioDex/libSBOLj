package org.sbolstandard.core2;

import java.net.URI;
import java.util.Set;

import org.sbolstandard.core2.abstract_classes.Identified;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Participation extends Identified {
	
	private Set<URI> roles;
	private URI participant;
	
	public Participation(URI identity, Set<URI> roles, URI participant) {
		super(identity);
		setRoles(roles);
		setParticipant(participant);
	}
	
	/**
	 * Returns field variable <code>participant</code>.
	 * @return field variable <code>participant</code>
	 */
	public URI getParticipant() {
		return participant;
	}

	/**
	 * Sets field variable <code>participant</code> to the specified element.
	 * @param participant
	 */
	public void setParticipant(URI participant) {
		this.participant = participant;
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

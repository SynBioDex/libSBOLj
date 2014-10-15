package org.sbolstandard.core2;

import java.net.URI;
import java.util.List;

import org.sbolstandard.core2.abstract_classes.Identified;

/**
 * 
 * @author Ernst Oberortner
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Participation extends Identified {
	
	private List<URI> role;
	private URI participant;
	
	public Participation(URI identity, List<URI> role, URI participant) {
		super(identity);
		setRole(role);
		setParticipant(participant);
	}

	public List<URI> getRole() {
		return role;
	}

	public void setRole(List<URI> role) {
		this.role = role;
	}
	
	public void clearRole() {
		role.clear();
	}

	public URI getParticipant() {
		return participant;
	}

	public void setParticipant(URI participant) {
		this.participant = participant;
	}

//	private URI role;
//	private ComponentInstantiation participant;
//	
//	/**
//	 * 
//	 * @param identity an identity for the participation
//	 * @param role a role for the participation
//	 * @param participant a participant component instantiation for the participation
//	 */
//	public Participation(URI identity, URI role, ComponentInstantiation participant) {
//		super(identity);
//		this.role = role;
//		this.participant = participant;
//	}
//
//	/**
//	 * @return the participation's role
//	 */
//	public URI getRole() {
//		return role;
//	}
//
//	/**
//	 * 
//	 * @return the participation's participant component instantiation
//	 */
//	public ComponentInstantiation getParticipant() {
//		return participant;
//	}
	
}

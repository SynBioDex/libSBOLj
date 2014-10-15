package org.sbolstandard.core2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.sbolstandard.core2.Participation;
import org.sbolstandard.core2.abstract_classes.Documented;

/**
 * 
 * @author Ernst Oberortner
 * @author Nicholas Roehner
 * @version 2.0
 */
public class Interaction extends Documented {

	private List<URI> type;
	private List<Participation> participations;
	
	/**
	 * 
	 * @param identity an identity for the interaction	 
	 * @param type a type for the interaction
	 * @param participations a collection of participations for the interaction
	 */
	public Interaction(URI identity, List<URI> type, List<Participation> participations) {
		super(identity);
		setType(type);
		setParticipations(participations);
	}

	public List<URI> getType() {
		return type;
	}

	public void setType(List<URI> type) {
		this.type = type;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

}

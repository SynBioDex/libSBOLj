package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

import java.net.URI;

/**
 * Represents an Association object in the SBOL data model.
 * 
 * @author Chris Myers
 * @version 2.2
 */

public class Association extends Identified {

	private URI hadRole;
	private URI agent;
	private URI hadPlan;

	/**
	 * @param identity
	 * @param hadRole
	 * @param agent
	 * @throws SBOLValidationException if any of the following condition is satisfied:
	 * <ul>
	 * <li>an SBOL validation rule violation occurred in {@link #setHadRole(URI)}.</li>
	 * <li>an SBOL validation rule violation occurred in {@link #setAgent(URI)}.</li>
	 * </ul>
	 */
	Association(URI identity, URI hadRole, URI agent) throws SBOLValidationException {
		super(identity);
		setHadRole(hadRole);
		setAgent(agent);
		hadPlan = null;
	}

	/**
	 * @param association
	 * @throws SBOLValidationException
	 */
	private Association(Association association) throws SBOLValidationException {
		super(association);
		this.setHadRole(association.getHadRole());
		this.setAgent(association.getAgent());
		this.setHadPlan(association.getHadPlan());
	}

	void copy(Association association) throws SBOLValidationException {
		((Identified)this).copy((Identified)association);
	}

	/**
	 * @return the hadRole
	 */
	public URI getHadRole() {
		return hadRole;
	}

	/**
	 * @param hadRole the hadRole to set
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 12602.
	 */
	public void setHadRole(URI hadRole) throws SBOLValidationException {
		if (hadRole==null) {
			throw new SBOLValidationException("sbol-12602",this);
		}
		this.hadRole = hadRole;
	}
	

	/**
	 * @return the agent
	 */
	public URI getAgent() {
		return agent;
	}

	/**
	 * @param agent the agent to set
	 * @throws SBOLValidationException if either of the following SBOL validation rules was violated: 12605, 12606. 
	 */
	public void setAgent(URI agent) throws SBOLValidationException {
		if (agent==null) {
			throw new SBOLValidationException("sbol-12605",this);
		}
		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
			if (this.getSBOLDocument().getAgent(agent)==null) {
				throw new SBOLValidationException("sbol-12606",this);
			}
		}
		this.agent = agent;
	}
	
	/**
	 * Test if the hadPlan is set.
	 *
	 * @return {@code true} if it is not {@code null}, or {@code false} otherwise
	 */
	public boolean isSetHadPlan() {
		return hadPlan != null;
	}

	/**
	 * @return the hadPlan
	 */
	public URI getHadPlan() {
		return hadPlan;
	}

	/**
	 * @param hadPlan the hadPlan to set
	 * @throws SBOLValidationException if the following SBOL validation rule was violated: 12604.
	 */
	public void setHadPlan(URI hadPlan) throws SBOLValidationException {
		if (this.getSBOLDocument() != null && this.getSBOLDocument().isComplete()) {
			if (this.getSBOLDocument().getPlan(hadPlan)==null) {
				throw new SBOLValidationException("sbol-12604",this);
			}
		}
		this.hadPlan = hadPlan;
	}

	@Override
	Association deepCopy() throws SBOLValidationException {
		return new Association(this);
	}

	/**
	 * Updates this participation with a compliant URI.
	 * 
	 * @throws SBOLValidationException if an SBOL validation rule violation occurred in any of the following methods:
	 * <ul>
	 * <li>{@link URIcompliance#createCompliantURI(String, String, String)},</li>
	 * <li>{@link #setWasDerivedFrom(URI)},</li>
	 * <li>{@link #setIdentity(URI)},</li>
	 * <li>{@link #setDisplayId(String)}, or</li>
	 * <li>{@link #setVersion(String)}.</li>
	 * </ul>
	 */
	void updateCompliantURI(String URIprefix, String displayId, String version) throws SBOLValidationException {
		if (!this.getIdentity().equals(createCompliantURI(URIprefix,displayId,version))) {
			this.addWasDerivedFrom(this.getIdentity());
		}
		this.setIdentity(createCompliantURI(URIprefix,displayId,version));
		this.setPersistentIdentity(createCompliantURI(URIprefix,displayId,""));
		this.setDisplayId(displayId);
		this.setVersion(version);
	}

	@Override
	public String toString() {
		return "Association ["
				+ super.toString()
				+ ", hadRole=" + hadRole 
				+ ", agent=" + agent
				+ ", hadPlan=" + hadPlan
				+ "]";
	}

}

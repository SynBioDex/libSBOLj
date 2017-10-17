package org.sbolstandard.core2;

import static org.sbolstandard.core2.URIcompliance.createCompliantURI;

import java.net.URI;

/**
 * Represents an Association object in the SBOL data model.
 * 
 * @author Chris Myers
 * @version 2.2
 */

public class Usage extends Identified {

	private URI entity;
	private URI hadRole;

	/**
	 * @param identity
	 * @param entity
	 * @param hadRole
	 * @throws SBOLValidationException if any of the following condition is satisfied:
	 * <ul>
	 * <li>an SBOL validation rule violation occurred in {@link #setHadRole(URI)}.</li>
	 * </ul>
	 */
	Usage(URI identity, URI entity, URI hadRole) throws SBOLValidationException {
		super(identity);
		setEntity(entity);
		setHadRole(hadRole);
	}

	/**
	 * @param usage
	 * @throws SBOLValidationException
	 */
	private Usage(Usage usage) throws SBOLValidationException {
		super(usage);
		this.setEntity(usage.getEntity());
		this.setHadRole(usage.getHadRole());
	}

	void copy(Usage association) throws SBOLValidationException {
		((Identified)this).copy((Identified)association);
	}

	/**
	 * @return the entity
	 */
	public URI getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(URI entity) {
		this.entity = entity;
	}

	/**
	 * @return the hadRole
	 */
	public URI getHadRole() {
		return hadRole;
	}

	/**
	 * @param hadRole the hadRole to set
	 */
	public void setHadRole(URI hadRole) {
		this.hadRole = hadRole;
	}

	@Override
	Usage deepCopy() throws SBOLValidationException {
		return new Usage(this);
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
		return "Usage ["
				+ super.toString()
				+ ", entity=" + entity
				+ ", hadRole=" + hadRole 
				+ "]";
	}

}

package org.sbolstandard.core2.abstract_classes;

import java.net.URI;

import org.sbolstandard.core2.ComponentDefinition;

/**
 * 
 * @author Zhen Zhang
 * @author Nicholas Roehner
 * @version 2.0
 */
public abstract class Documented extends Identified {

	private String displayId;
	private String name;
	private String description;
	
	/**
	 * @param identity an identity for the documented object
	 */
	public Documented(URI identity) {
		super(identity);
		if (isURIcompliant(identity)) {
			// TODO extract display ID
		}

	}
	
	public Documented(String URIprefix, String displayId, String version) {
		super(URIprefix, displayId, version);
		setDisplayId(displayId);
	}
	
	protected Documented(Documented documented) {
		super(documented);
		if (documented.isSetDisplayId()) {
			this.setDisplayId(documented.getDisplayId());
		}
		if (documented.isSetName()) {
			this.setName(documented.getName());
		}
		if (documented.isSetDescription()) {
			this.setDescription(documented.getDescription());
		}		
	}
	
	
	/**
	 * Test if optional field variable <code>displayId</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetDisplayId() {
		if (displayId == null)
			return false;
		else 
			return true;
	}
	
	/**
	 * Returns field variable <code>displayId</code>.
	 * @return field variable <code>displayId</code>
	 */
	// @return the documented object's display ID
	public String getDisplayId() {
		return displayId;
	}
		
	/**
	 * Set field variable <code>displayId</code> to the specified element.
	 * @param displayId
	 */
	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}
	
	/**
	 * Set optional field variable <code>displayId</code> to <code>null</code>.
	 */
	public void unsetDisplayId() {
		displayId = null;
	}
	
	/**
	 * Test if optional field variable <code>name</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetName() {
		if (name == null)
			return false;
		else 
			return true;
	}
		
	/**
	 * Returns field variable <code>name</code>.
	 * @return field variable <code>name</code>
	 */
	//@return the documented object's name
	public String getName() {
		return name;
	}

	/**
	 * Sets field variable <code>name</code> to the specified element.
	 */
	// @param name a name for the documented object
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets optional field variable <code>name</code> to <code>null</code>.
	 */
	public void unsetName() {
		name = null;
	}
	
	/**
	 * Test if optional field variable <code>description</code> is set.
	 * @return <code>true</code> if it is not <code>null</code>
	 */
	public boolean isSetDescription() {
		if (description == null)
			return false;
		else 
			return true;
	}
	
	/**
	 * Returns field variable <code>description</code>.
	 * @return field variable <code>description</code>
	 */
	// @return the documented object's description
	public String getDescription() {
		return description;
	}

	/**
	 * Sets field variable <code>description</code> to the specified element.
	 * @param description
	 */
	// @param a description for the documented object
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set optional field variable <code>description</code> to <code>null</code>.
	 */
	public void unsetDescription() {
		description = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((displayId == null) ? 0 : displayId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Documented other = (Documented) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (displayId == null) {
			if (other.displayId != null)
				return false;
		} else if (!displayId.equals(other.displayId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.sbolstandard.core2.abstract_classes.Identified#deepCopy()
	 */
	protected abstract Documented deepCopy();
}

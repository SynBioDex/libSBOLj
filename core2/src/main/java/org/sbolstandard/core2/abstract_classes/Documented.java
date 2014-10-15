package org.sbolstandard.core2.abstract_classes;

import java.net.URI;

/**
 * 
 * @author Ernst Oberortner
 * @author Nicholas Roehner
 * @version 2.0
 */
public abstract class Documented extends Identified {

	private String displayId;
	private String name;
	private String description;
	
	/**
	 * 
	 * @param identity an identity for the documented object
	 * @param displayID a display ID for the documented object
	 */
	public Documented(URI identity) {
		super(identity);
	}
	
	/**
	 * Check whether optional field variable <code>displayId</code> is set or not.
	 * @return <code>true</code> if it is not null
	 */
	public boolean isSetDisplayId() {
		if (displayId == null)
			return false;
		else 
			return true;
	}
	
	/**
	 * Check whether optional field variable <code>name</code> is set or not
	 * @return <code>true</code> if it is not null
	 */
	public boolean isSetName() {
		if (name == null)
			return false;
		else 
			return true;
	}
	
	/**
	 * Check whether optional field variable <code>description</code> is set or not
	 * @return <code>true</code> if it is not null
	 */
	public boolean isSetDescription() {
		if (description == null)
			return false;
		else 
			return true;
	}
	
	/**
	 * Set optional field variable <code>displayId</code> to <code>null</code>.
	 */
	public void unsetDisplayId() {
		displayId = null;
	}
	
	/**
	 * Set optional field variable <code>name</code> to <code>null</code>.
	 */
	public void unsetName() {
		name = null;
	}
	
	/**
	 * Set optional field variable <code>description</code> to <code>null</code>.
	 */
	public void unsetDescription() {
		description = null;
	}
	
	/**
	 * 
	 * @return the documented object's display ID
	 */
	public String getDisplayId() {
		return displayId;
	}

	/**
	 * 
	 * @return the documented object's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name a name for the documented object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the documented object's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description a description for the documented object
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Created for backward compatibility to 1.1. 
	 * @param value
	 */
	public void setDisplayId(String value) {
		this.displayId = value;
	}

}

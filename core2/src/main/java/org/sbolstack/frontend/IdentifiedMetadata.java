package org.sbolstack.frontend;

/**
 * The metadata common to all SBOL data objects
 * @author James McLaughlin
 *
 */
public class IdentifiedMetadata
{
	// TODO: should we change these to private variables with getters/setters?
	public String uri;
	public String name;
	public String displayId;
	public String description;
    public String version;
}


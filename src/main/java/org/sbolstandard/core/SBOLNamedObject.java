package org.sbolstandard.core;

/**
 * Base for SBOL things that have names, display IDs and descriptions.
 *
 * @author Matthew Pocock
 */
public interface SBOLNamedObject extends SBOLObject {
    /**
     * Get the text which is for users to read and interpret what this represents.
     *
     * @return  human readable text describing this SBOL object
     */
    String getDescription();

    /**
     * Set the text which is for users to read and interpret what this represents.
     *
     * @param description Human readable text describing this SBOL object
     */
    void setDescription(String description);

    /**
     * Get the alphanumeric identifier to display to users.
     *
     * @return a human readable identifier
     */
    String getDisplayId();

    /**
     * Set the alphanumeric identifier to display to users.
     *
     * @param displayId a human readable identifier
     */
    void setDisplayId(String displayId);

    /**
     * Get the recognizable human-readable name of this SBOL object.
     *
     * @return a human-readable name
     */
    String getName();

    /**
     * Set the recognizable human-readable name of this SBOL object.
     *
     * @param name commonly used to refer to this SBOL object.
     */
    void setName(String name);
}

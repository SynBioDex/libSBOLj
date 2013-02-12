package org.sbolstandard.core;

public interface Device extends SBOLNamedObject, SBOLRootObject  {
    /**
     * Text which is for users to read and interpret what this Collection is.
     * (eg "Collecting parts which could be used to build honey production directly into
     * mouse-ear cress"; "T9002 and I7101 variants from Sleight 2010, designs aim to
     * improve stability over evolutionary time"; "features useful when working with
     * BBF RFC 10"; "Totally sick Parts I found browsing the web -- SBOL rules
     * -- organize these later").
     *
     * Informative description which allows human users to interpret the likely
     * members of this Collection.
     * @return Human readable text describing the Collection
     */
    String getDescription();

    /**
     * Text with an informative statement about the reason for grouping the Collection members.
     * (eg "Collecting parts which could be used to build honey production directly into
     * mouse-ear cress"; "T9002 and I7101 variants from Sleight 2010, designs aim to
     * improve stability over evolutionary time"; "features useful when working with
     * BBF RFC 10"; "Totally sick Parts I found browsing the web -- SBOL rules
     * -- organize these later").
     *
     * @param description Human readable text describing the Collection
     */
    void setDescription(String description);

    /**
     * Identifier to display to users.
     * @return a human readable identifier
     */
    String getDisplayId();

    /**
     * Identifier to display to users.
     * @param displayId a human readable identifier
     */
    void setDisplayId(String displayId);

    /**
     * Recognizable human identifier, it is often ambiguous.(eg. Mike's Arabidopsis Project A;
     * Sleight, et al. (2010) J.Bioeng; BBF RFC 10 features; Bookmarked Parts).
     * @return its name, commonly used to refer to this Collection
     */
    String getName();

    /**
     * Common name of Collection should confer what is contained in the Collection.
     *(eg. Mike's Arabidopsis Project A;
     * Sleight, et al. (2010) J.Bioeng; BBF RFC 10 features; Bookmarked Parts).
     * @param name commonly used to refer to this Collection (eg. Project A)
     */
    void setName(String name);

}

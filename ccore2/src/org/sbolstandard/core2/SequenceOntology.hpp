// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SequenceOntology.java

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::SequenceOntology
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::java::lang::String* URI_PREFIX_;
    static ::java::net::URI* NAMESPACE_;

public: /* package */
    static ::java::net::URI* convertSeqOntologyV1(::java::lang::String* term);

public:
    static ::java::net::URI* type(::java::lang::String* localName);

private:
    static ::java::net::URI* PROMOTER_;
    static ::java::net::URI* OPERATOR_;
    static ::java::net::URI* CDS_;
    static ::java::net::URI* FIVE_PRIME_UTR_;
    static ::java::net::URI* TERMINATOR_;
    static ::java::net::URI* INSULATOR_;
    static ::java::net::URI* ORIGIN_OF_REPLICATION_;
    static ::java::net::URI* PRIMER_BINDING_SITE_;
    static ::java::net::URI* RIBOSOME_ENTRY_SITE_;
    static ::java::net::URI* GENE_;
    static ::java::net::URI* MRNA_;
    static ::java::net::URI* RESTRICTION_ENZYME_RECOGNITION_SITE_;
    static ::java::net::URI* ENGINEERED_GENE_;
    static ::java::net::URI* ENGINEERED_REGION_;

    // Generated

public:
    SequenceOntology();
protected:
    SequenceOntology(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

private:
    static ::java::lang::String*& URI_PREFIX();

public:
    static ::java::net::URI*& NAMESPACE();
    static ::java::net::URI*& PROMOTER();
    static ::java::net::URI*& OPERATOR();
    static ::java::net::URI*& CDS();
    static ::java::net::URI*& FIVE_PRIME_UTR();
    static ::java::net::URI*& TERMINATOR();
    static ::java::net::URI*& INSULATOR();
    static ::java::net::URI*& ORIGIN_OF_REPLICATION();
    static ::java::net::URI*& PRIMER_BINDING_SITE();
    static ::java::net::URI*& RIBOSOME_ENTRY_SITE();
    static ::java::net::URI*& GENE();
    static ::java::net::URI*& MRNA();
    static ::java::net::URI*& RESTRICTION_ENZYME_RECOGNITION_SITE();
    static ::java::net::URI*& ENGINEERED_GENE();
    static ::java::net::URI*& ENGINEERED_REGION();

private:
    virtual ::java::lang::Class* getClass0();
};

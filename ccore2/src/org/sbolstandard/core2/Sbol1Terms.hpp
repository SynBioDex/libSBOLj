// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol1Terms.java

#pragma once

#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Sbol1Terms
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* sbol1_;
    static ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* rdf_;

    // Generated

public:
    Sbol1Terms();
protected:
    Sbol1Terms(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding*& sbol1();
    static ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding*& rdf();

private:
    virtual ::java::lang::Class* getClass0();
    friend class Sbol1Terms_Collection;
    friend class Sbol1Terms_DNAComponent;
    friend class Sbol1Terms_DNASequence;
    friend class Sbol1Terms_SequenceAnnotations;
};

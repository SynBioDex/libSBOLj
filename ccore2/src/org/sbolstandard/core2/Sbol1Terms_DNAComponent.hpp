// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol1Terms.java

#pragma once

#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Sbol1Terms_DNAComponent final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::javax::xml::namespace_::QName* DNAComponent__;
    static ::javax::xml::namespace_::QName* uri_;
    static ::javax::xml::namespace_::QName* displayId_;
    static ::javax::xml::namespace_::QName* name_;
    static ::javax::xml::namespace_::QName* description_;
    static ::javax::xml::namespace_::QName* type_;
    static ::javax::xml::namespace_::QName* annotations_;
    static ::javax::xml::namespace_::QName* dnaSequence_;

    // Generated

public:
    Sbol1Terms_DNAComponent();
protected:
    Sbol1Terms_DNAComponent(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::javax::xml::namespace_::QName*& DNAComponent_();
    static ::javax::xml::namespace_::QName*& uri();
    static ::javax::xml::namespace_::QName*& displayId();
    static ::javax::xml::namespace_::QName*& name();
    static ::javax::xml::namespace_::QName*& description();
    static ::javax::xml::namespace_::QName*& type();
    static ::javax::xml::namespace_::QName*& annotations();
    static ::javax::xml::namespace_::QName*& dnaSequence();

private:
    virtual ::java::lang::Class* getClass0();
    friend class Sbol1Terms;
    friend class Sbol1Terms_Collection;
    friend class Sbol1Terms_DNASequence;
    friend class Sbol1Terms_SequenceAnnotations;
};

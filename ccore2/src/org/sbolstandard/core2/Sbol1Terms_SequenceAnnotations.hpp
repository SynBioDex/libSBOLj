// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol1Terms.java

#pragma once

#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::javax::xml::namespace_::QName* SequenceAnnotation_;
    static ::javax::xml::namespace_::QName* uri_;
    static ::javax::xml::namespace_::QName* bioStart_;
    static ::javax::xml::namespace_::QName* bioEnd_;
    static ::javax::xml::namespace_::QName* strand_;
    static ::javax::xml::namespace_::QName* subComponent_;
    static ::javax::xml::namespace_::QName* precedes_;

    // Generated

public:
    Sbol1Terms_SequenceAnnotations();
protected:
    Sbol1Terms_SequenceAnnotations(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::javax::xml::namespace_::QName*& SequenceAnnotation();
    static ::javax::xml::namespace_::QName*& uri();
    static ::javax::xml::namespace_::QName*& bioStart();
    static ::javax::xml::namespace_::QName*& bioEnd();
    static ::javax::xml::namespace_::QName*& strand();
    static ::javax::xml::namespace_::QName*& subComponent();
    static ::javax::xml::namespace_::QName*& precedes();

private:
    virtual ::java::lang::Class* getClass0();
    friend class Sbol1Terms;
    friend class Sbol1Terms_Collection;
    friend class Sbol1Terms_DNAComponent;
    friend class Sbol1Terms_DNASequence;
};

// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/GenericTopLevel.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>

struct default_init_tag;

class org::sbolstandard::core2::GenericTopLevel
    : public TopLevel
{

public:
    typedef TopLevel super;

private:
    ::javax::xml::namespace_::QName* rdfType {  };
protected:
    void ctor(::java::net::URI* identity, ::javax::xml::namespace_::QName* rdfType);
    void ctor(GenericTopLevel* genericTopLevel);

public:
    virtual ::javax::xml::namespace_::QName* getRDFType();
    virtual void setRDFType(::javax::xml::namespace_::QName* rdfType);
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    GenericTopLevel* deepCopy() override;

public: /* package */
    GenericTopLevel* copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version) override;

public: /* protected */
    bool checkDescendantsURIcompliance() override;

    // Generated

public: /* package */
    GenericTopLevel(::java::net::URI* identity, ::javax::xml::namespace_::QName* rdfType);

private:
    GenericTopLevel(GenericTopLevel* genericTopLevel);
protected:
    GenericTopLevel(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

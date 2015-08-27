// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Annotation.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Annotation
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    ::uk::ac::ncl::intbio::core::datatree::NamedProperty* value {  };
protected:
    void ctor(::javax::xml::namespace_::QName* qName, ::java::lang::String* literal);
    void ctor(::javax::xml::namespace_::QName* qName, int32_t literal);
    void ctor(::javax::xml::namespace_::QName* qName, double literal);
    void ctor(::javax::xml::namespace_::QName* qName, bool literal);
    void ctor(::javax::xml::namespace_::QName* qName, ::java::net::URI* literal);
    void ctor(::javax::xml::namespace_::QName* qName, ::javax::xml::namespace_::QName* nestedQName, ::java::net::URI* nestedURI, ::java::util::List* annotations);
    void ctor(::uk::ac::ncl::intbio::core::datatree::NamedProperty* value);
    void ctor(Annotation* annotation);

public:
    virtual ::javax::xml::namespace_::QName* getQName();
    virtual bool isBooleanValue();
    virtual ::java::lang::Boolean* getBooleanValue();
    virtual bool isDoubleValue();
    virtual ::java::lang::Double* getDoubleValue();
    virtual bool isIntegerValue();
    virtual ::java::lang::Integer* getIntegerValue();
    virtual bool isStringValue();
    virtual ::java::lang::String* getStringValue();
    virtual ::java::net::URI* getURIValue();
    virtual ::javax::xml::namespace_::QName* getNestedQName();
    virtual ::java::net::URI* getNestedIdentity();
    virtual bool isNestedAnnotations();
    virtual ::java::util::List* getAnnotations();

public: /* package */
    virtual ::uk::ac::ncl::intbio::core::datatree::NamedProperty* getValue();
    virtual void setValue(::uk::ac::ncl::intbio::core::datatree::NamedProperty* value);

private:
    Annotation* deepCopy();

public: /* package */
    virtual Annotation* copy();

public:
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

    // Generated
    Annotation(::javax::xml::namespace_::QName* qName, ::java::lang::String* literal);
    Annotation(::javax::xml::namespace_::QName* qName, int32_t literal);
    Annotation(::javax::xml::namespace_::QName* qName, double literal);
    Annotation(::javax::xml::namespace_::QName* qName, bool literal);
    Annotation(::javax::xml::namespace_::QName* qName, ::java::net::URI* literal);
    Annotation(::javax::xml::namespace_::QName* qName, ::javax::xml::namespace_::QName* nestedQName, ::java::net::URI* nestedURI, ::java::util::List* annotations);

public: /* package */
    Annotation(::uk::ac::ncl::intbio::core::datatree::NamedProperty* value);

private:
    Annotation(Annotation* annotation);
protected:
    Annotation(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class uk::ac::ncl::intbio::core::datatree::NamespaceBinding final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    ::java::lang::String* namespaceURI {  };
    ::java::lang::String* prefix {  };

protected:
    void ctor(::java::lang::String* namespaceURI, ::java::lang::String* prefix);

public:
    bool equals(::java::lang::Object* o) override;
    ::java::lang::String* getNamespaceURI();
    ::java::lang::String* getPrefix();
    int32_t hashCode() override;
    ::java::net::URI* namespacedUri(::java::lang::String* localPart);
    ::javax::xml::namespace_::QName* withLocalPart(::java::lang::String* localPart);

    // Generated

public: /* package */
    NamespaceBinding(::java::lang::String* namespaceURI, ::java::lang::String* prefix);
protected:
    NamespaceBinding(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace uk
{
    namespace ac
    {
        namespace ncl
        {
            namespace intbio
            {
                namespace core
                {
                    namespace datatree
                    {
typedef ::SubArray< ::uk::ac::ncl::intbio::core::datatree::Document, ::java::lang::ObjectArray > DocumentArray;
typedef ::SubArray< ::uk::ac::ncl::intbio::core::datatree::IdentifiableDocument, ::java::lang::ObjectArray, DocumentArray > IdentifiableDocumentArray;
typedef ::SubArray< ::uk::ac::ncl::intbio::core::datatree::NamedProperty, ::java::lang::ObjectArray > NamedPropertyArray;
typedef ::SubArray< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding, ::java::lang::ObjectArray > NamespaceBindingArray;
typedef ::SubArray< ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument, ::java::lang::ObjectArray, IdentifiableDocumentArray > TopLevelDocumentArray;
                    } // datatree
                } // core
            } // intbio
        } // ncl
    } // ac
} // uk

struct default_init_tag;

class uk::ac::ncl::intbio::core::datatree::Datatree final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

    /*void ctor(); (private) */
    static ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* DocumentRoot(Datatree_TopLevelDocuments* documents);
    static ::uk::ac::ncl::intbio::core::datatree::DocumentRoot* DocumentRoot(Datatree_NamespaceBindings* bindings, Datatree_TopLevelDocuments* documents);
    static Literal_StringLiteral* Literal(::java::lang::String* value);
    static Literal_IntegerLiteral* Literal(int32_t value);
    static Literal_DoubleLiteral* Literal(double value);
    static Literal_UriLiteral* Literal(::java::net::URI* value);
    static Literal_BooleanLiteral* Literal(bool value);
    static Literal_TypedLiteral* Literal(::java::lang::String* value, ::javax::xml::namespace_::QName* type);
    static Datatree_NamedProperties* NamedProperties(NamedPropertyArray* properties);
    static Datatree_NamedProperties* NamedProperties(::java::util::List* properties);
    static ::uk::ac::ncl::intbio::core::datatree::NamedProperty* NamedProperty(::java::lang::Object* name, PropertyValue* value);
    static ::uk::ac::ncl::intbio::core::datatree::NamedProperty* NamedProperty(::java::lang::Object* name, ::java::lang::String* value);
    static ::uk::ac::ncl::intbio::core::datatree::NamedProperty* NamedProperty(::java::lang::Object* name, int32_t value);
    static ::uk::ac::ncl::intbio::core::datatree::NamedProperty* NamedProperty(::java::lang::Object* name, ::java::net::URI* value);
    static ::uk::ac::ncl::intbio::core::datatree::NamedProperty* NamedProperty(::java::lang::Object* name, ::java::lang::Double* value);
    static ::uk::ac::ncl::intbio::core::datatree::NamedProperty* NamedProperty(::java::lang::Object* name, ::java::lang::Boolean* value);
    static ::uk::ac::ncl::intbio::core::datatree::NamedProperty* NamedProperty(::java::lang::Object* name, ::uk::ac::ncl::intbio::core::datatree::NestedDocument* value);
    static ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* NamespaceBinding(::java::lang::String* namespaceUri, ::java::lang::String* prefix);
    static Datatree_NamespaceBindings* NamespaceBindings(::java::util::List* bindings);
    static Datatree_NamespaceBindings* NamespaceBindings(NamespaceBindingArray* bindings);
    static ::uk::ac::ncl::intbio::core::datatree::NestedDocument* NestedDocument(::java::lang::Object* type, ::java::net::URI* identity, Datatree_NamedProperties* properties);
    static ::uk::ac::ncl::intbio::core::datatree::NestedDocument* NestedDocument(Datatree_NamespaceBindings* bindings, ::java::lang::Object* type, ::java::net::URI* identity, Datatree_NamedProperties* properties);
    static ::javax::xml::namespace_::QName* QName(::java::lang::String* localPart);
    static ::javax::xml::namespace_::QName* QName(::java::lang::String* namespaceURI, ::java::lang::String* localPart);
    static ::javax::xml::namespace_::QName* QName(::java::lang::String* namespaceURI, ::java::lang::String* localPart, ::java::lang::String* prefix);
    static ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* TopLevelDocument(::java::lang::Object* type, ::java::net::URI* identity, Datatree_NamedProperties* properties);
    static ::uk::ac::ncl::intbio::core::datatree::TopLevelDocument* TopLevelDocument(Datatree_NamespaceBindings* bindings, ::java::lang::Object* type, ::java::net::URI* identity, Datatree_NamedProperties* properties);
    static Datatree_TopLevelDocuments* TopLevelDocuments(TopLevelDocumentArray* documents);
    static Datatree_TopLevelDocuments* TopLevelDocuments(::java::util::List* documents);

    // Generated
    Datatree();
protected:
    Datatree(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

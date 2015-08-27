// Generated from /Users/myers/.m2/repository/net/java/dev/stax-utils/stax-utils/20070216/stax-utils-20070216.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <javanet/staxutils/helpers/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <javax/xml/stream/XMLStreamWriter.hpp>

struct default_init_tag;

class javanet::staxutils::helpers::StreamWriterDelegate
    : public virtual ::java::lang::Object
    , public virtual ::javax::xml::stream::XMLStreamWriter
{

public:
    typedef ::java::lang::Object super;

public: /* protected */
    ::javax::xml::stream::XMLStreamWriter* out {  };

protected:
    void ctor(::javax::xml::stream::XMLStreamWriter* out);

public:
    void close() override;
    void flush() override;
    ::javax::xml::namespace_::NamespaceContext* getNamespaceContext() override;
    ::java::lang::String* getPrefix(::java::lang::String* uri) override;
    ::java::lang::Object* getProperty(::java::lang::String* name) override;
    void setDefaultNamespace(::java::lang::String* uri) override;
    void setNamespaceContext(::javax::xml::namespace_::NamespaceContext* context) override;
    void setPrefix(::java::lang::String* prefix, ::java::lang::String* uri) override;
    void writeAttribute(::java::lang::String* localName, ::java::lang::String* value) override;
    void writeAttribute(::java::lang::String* namespaceURI, ::java::lang::String* localName, ::java::lang::String* value) override;
    void writeAttribute(::java::lang::String* prefix, ::java::lang::String* namespaceURI, ::java::lang::String* localName, ::java::lang::String* value) override;
    void writeCData(::java::lang::String* data) override;
    void writeCharacters(::java::lang::String* text) override;
    void writeCharacters(::char16_tArray* text, int32_t start, int32_t len) override;
    void writeComment(::java::lang::String* data) override;
    void writeDTD(::java::lang::String* dtd) override;
    void writeDefaultNamespace(::java::lang::String* namespaceURI) override;
    void writeEmptyElement(::java::lang::String* localName) override;
    void writeEmptyElement(::java::lang::String* namespaceURI, ::java::lang::String* localName) override;
    void writeEmptyElement(::java::lang::String* prefix, ::java::lang::String* localName, ::java::lang::String* namespaceURI) override;
    void writeEndDocument() override;
    void writeEndElement() override;
    void writeEntityRef(::java::lang::String* name) override;
    void writeNamespace(::java::lang::String* prefix, ::java::lang::String* namespaceURI) override;
    void writeProcessingInstruction(::java::lang::String* target) override;
    void writeProcessingInstruction(::java::lang::String* target, ::java::lang::String* data) override;
    void writeStartDocument() override;
    void writeStartDocument(::java::lang::String* version) override;
    void writeStartDocument(::java::lang::String* encoding, ::java::lang::String* version) override;
    void writeStartElement(::java::lang::String* localName) override;
    void writeStartElement(::java::lang::String* namespaceURI, ::java::lang::String* localName) override;
    void writeStartElement(::java::lang::String* prefix, ::java::lang::String* localName, ::java::lang::String* namespaceURI) override;

    // Generated

public: /* protected */
    StreamWriterDelegate(::javax::xml::stream::XMLStreamWriter* out);
protected:
    StreamWriterDelegate(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

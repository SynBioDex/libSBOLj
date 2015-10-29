// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct javax::xml::stream::XMLStreamWriter
    : public virtual ::java::lang::Object
{

    virtual void close() = 0;
    virtual void flush() = 0;
    virtual ::javax::xml::namespace_::NamespaceContext* getNamespaceContext() = 0;
    virtual ::java::lang::String* getPrefix(::java::lang::String* uri) = 0;
    virtual ::java::lang::Object* getProperty(::java::lang::String* name) = 0;
    virtual void setDefaultNamespace(::java::lang::String* uri) = 0;
    virtual void setNamespaceContext(::javax::xml::namespace_::NamespaceContext* context) = 0;
    virtual void setPrefix(::java::lang::String* prefix, ::java::lang::String* uri) = 0;
    virtual void writeAttribute(::java::lang::String* localName, ::java::lang::String* value) = 0;
    virtual void writeAttribute(::java::lang::String* namespaceURI, ::java::lang::String* localName, ::java::lang::String* value) = 0;
    virtual void writeAttribute(::java::lang::String* prefix, ::java::lang::String* namespaceURI, ::java::lang::String* localName, ::java::lang::String* value) = 0;
    virtual void writeCData(::java::lang::String* data) = 0;
    virtual void writeCharacters(::java::lang::String* text) = 0;
    virtual void writeCharacters(::char16_tArray* text, int32_t start, int32_t len) = 0;
    virtual void writeComment(::java::lang::String* data) = 0;
    virtual void writeDTD(::java::lang::String* dtd) = 0;
    virtual void writeDefaultNamespace(::java::lang::String* namespaceURI) = 0;
    virtual void writeEmptyElement(::java::lang::String* localName) = 0;
    virtual void writeEmptyElement(::java::lang::String* namespaceURI, ::java::lang::String* localName) = 0;
    virtual void writeEmptyElement(::java::lang::String* prefix, ::java::lang::String* localName, ::java::lang::String* namespaceURI) = 0;
    virtual void writeEndDocument() = 0;
    virtual void writeEndElement() = 0;
    virtual void writeEntityRef(::java::lang::String* name) = 0;
    virtual void writeNamespace(::java::lang::String* prefix, ::java::lang::String* namespaceURI) = 0;
    virtual void writeProcessingInstruction(::java::lang::String* target) = 0;
    virtual void writeProcessingInstruction(::java::lang::String* target, ::java::lang::String* data) = 0;
    virtual void writeStartDocument() = 0;
    virtual void writeStartDocument(::java::lang::String* version) = 0;
    virtual void writeStartDocument(::java::lang::String* encoding, ::java::lang::String* version) = 0;
    virtual void writeStartElement(::java::lang::String* localName) = 0;
    virtual void writeStartElement(::java::lang::String* namespaceURI, ::java::lang::String* localName) = 0;
    virtual void writeStartElement(::java::lang::String* prefix, ::java::lang::String* localName, ::java::lang::String* namespaceURI) = 0;

    // Generated
    static ::java::lang::Class *class_();
};

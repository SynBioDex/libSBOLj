// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/stream/XMLStreamConstants.hpp>

struct javax::xml::stream::XMLStreamReader
    : public virtual XMLStreamConstants
{

    virtual void close() = 0;
    virtual int32_t getAttributeCount() = 0;
    virtual ::java::lang::String* getAttributeLocalName(int32_t index) = 0;
    virtual ::javax::xml::namespace_::QName* getAttributeName(int32_t index) = 0;
    virtual ::java::lang::String* getAttributeNamespace(int32_t index) = 0;
    virtual ::java::lang::String* getAttributePrefix(int32_t index) = 0;
    virtual ::java::lang::String* getAttributeType(int32_t index) = 0;
    virtual ::java::lang::String* getAttributeValue(int32_t index) = 0;
    virtual ::java::lang::String* getAttributeValue(::java::lang::String* namespaceURI, ::java::lang::String* localName) = 0;
    virtual ::java::lang::String* getCharacterEncodingScheme() = 0;
    virtual ::java::lang::String* getElementText() = 0;
    virtual ::java::lang::String* getEncoding() = 0;
    virtual int32_t getEventType() = 0;
    virtual ::java::lang::String* getLocalName() = 0;
    virtual Location* getLocation() = 0;
    virtual ::javax::xml::namespace_::QName* getName() = 0;
    virtual ::javax::xml::namespace_::NamespaceContext* getNamespaceContext() = 0;
    virtual int32_t getNamespaceCount() = 0;
    virtual ::java::lang::String* getNamespacePrefix(int32_t index) = 0;
    virtual ::java::lang::String* getNamespaceURI() = 0;
    virtual ::java::lang::String* getNamespaceURI(::java::lang::String* prefix) = 0;
    virtual ::java::lang::String* getNamespaceURI(int32_t index) = 0;
    virtual ::java::lang::String* getPIData() = 0;
    virtual ::java::lang::String* getPITarget() = 0;
    virtual ::java::lang::String* getPrefix() = 0;
    virtual ::java::lang::Object* getProperty(::java::lang::String* name) = 0;
    virtual ::java::lang::String* getText() = 0;
    virtual ::char16_tArray* getTextCharacters() = 0;
    virtual int32_t getTextCharacters(int32_t sourceStart, ::char16_tArray* target, int32_t targetStart, int32_t length) = 0;
    virtual int32_t getTextLength() = 0;
    virtual int32_t getTextStart() = 0;
    virtual ::java::lang::String* getVersion() = 0;
    virtual bool hasName() = 0;
    virtual bool hasNext() = 0;
    virtual bool hasText() = 0;
    virtual bool isAttributeSpecified(int32_t index) = 0;
    virtual bool isCharacters() = 0;
    virtual bool isEndElement() = 0;
    virtual bool isStandalone() = 0;
    virtual bool isStartElement() = 0;
    virtual bool isWhiteSpace() = 0;
    virtual int32_t next() = 0;
    virtual int32_t nextTag() = 0;
    virtual void require(int32_t type, ::java::lang::String* namespaceURI, ::java::lang::String* localName) = 0;
    virtual bool standaloneSet() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

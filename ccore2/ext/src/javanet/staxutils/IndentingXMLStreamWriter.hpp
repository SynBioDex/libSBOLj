// Generated from /Users/myers/.m2/repository/net/java/dev/stax-utils/stax-utils/20070216/stax-utils-20070216.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <javanet/staxutils/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <javanet/staxutils/helpers/StreamWriterDelegate.hpp>
#include <javanet/staxutils/Indentation.hpp>

struct default_init_tag;

class javanet::staxutils::IndentingXMLStreamWriter
    : public ::javanet::staxutils::helpers::StreamWriterDelegate
    , public virtual Indentation
{

public:
    typedef ::javanet::staxutils::helpers::StreamWriterDelegate super;

private:
    static constexpr int32_t WROTE_DATA { int32_t(2) };
    static constexpr int32_t WROTE_MARKUP { int32_t(1) };
    int32_t depth {  };
    ::java::lang::String* indent {  };
    ::char16_tArray* linePrefix {  };
    ::java::lang::String* newLine {  };
    ::int32_tArray* stack {  };

protected:
    void ctor(::javax::xml::stream::XMLStreamWriter* out);

public: /* protected */
    virtual void afterData();
    virtual void afterEndDocument();
    virtual void afterEndElement();
    virtual void afterMarkup();
    virtual void afterStartElement();
    virtual void beforeEndElement();
    virtual void beforeMarkup();
    virtual void beforeStartElement();

public:
    ::java::lang::String* getIndent() override;
    static ::java::lang::String* getLineSeparator();
    ::java::lang::String* getNewLine() override;
    void setIndent(::java::lang::String* indent) override;
    void setNewLine(::java::lang::String* newLine) override;
    void writeCData(::java::lang::String* data) override;
    void writeCharacters(::java::lang::String* text) override;
    void writeCharacters(::char16_tArray* text, int32_t start, int32_t len) override;
    void writeComment(::java::lang::String* data) override;
    void writeDTD(::java::lang::String* dtd) override;
    void writeEmptyElement(::java::lang::String* localName) override;
    void writeEmptyElement(::java::lang::String* namespaceURI, ::java::lang::String* localName) override;
    void writeEmptyElement(::java::lang::String* prefix, ::java::lang::String* localName, ::java::lang::String* namespaceURI) override;
    void writeEndDocument() override;
    void writeEndElement() override;
    void writeEntityRef(::java::lang::String* name) override;

public: /* protected */
    virtual void writeNewLine(int32_t indentation);

public:
    void writeProcessingInstruction(::java::lang::String* target) override;
    void writeProcessingInstruction(::java::lang::String* target, ::java::lang::String* data) override;
    void writeStartDocument() override;
    void writeStartDocument(::java::lang::String* version) override;
    void writeStartDocument(::java::lang::String* encoding, ::java::lang::String* version) override;
    void writeStartElement(::java::lang::String* localName) override;
    void writeStartElement(::java::lang::String* namespaceURI, ::java::lang::String* localName) override;
    void writeStartElement(::java::lang::String* prefix, ::java::lang::String* localName, ::java::lang::String* namespaceURI) override;

    // Generated
    IndentingXMLStreamWriter(::javax::xml::stream::XMLStreamWriter* out);
protected:
    IndentingXMLStreamWriter(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

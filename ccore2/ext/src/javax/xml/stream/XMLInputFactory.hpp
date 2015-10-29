// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/stream/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/transform/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class javax::xml::stream::XMLInputFactory
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::java::lang::String* ALLOCATOR_;
    static ::java::lang::String* DEFAULIMPL_;
    static ::java::lang::String* IS_COALESCING_;
    static ::java::lang::String* IS_NAMESPACE_AWARE_;
    static ::java::lang::String* IS_REPLACING_ENTITY_REFERENCES_;
    static ::java::lang::String* IS_SUPPORTING_EXTERNAL_ENTITIES_;
    static ::java::lang::String* IS_VALIDATING_;
    static ::java::lang::String* JAXPFACTORYID_;
    static ::java::lang::String* REPORTER_;
    static ::java::lang::String* RESOLVER_;
    static ::java::lang::String* SUPPORT_DTD_;

protected:
    void ctor();

public:
    virtual XMLStreamReader* createFilteredReader(XMLStreamReader* reader, StreamFilter* filter) = 0;
    virtual XMLEventReader* createFilteredReader(XMLEventReader* reader, EventFilter* filter) = 0;
    virtual XMLEventReader* createXMLEventReader(::java::io::Reader* reader) = 0;
    virtual XMLEventReader* createXMLEventReader(XMLStreamReader* reader) = 0;
    virtual XMLEventReader* createXMLEventReader(::javax::xml::transform::Source* source) = 0;
    virtual XMLEventReader* createXMLEventReader(::java::io::InputStream* stream) = 0;
    virtual XMLEventReader* createXMLEventReader(::java::lang::String* systemId, ::java::io::Reader* reader) = 0;
    virtual XMLEventReader* createXMLEventReader(::java::io::InputStream* stream, ::java::lang::String* encoding) = 0;
    virtual XMLEventReader* createXMLEventReader(::java::lang::String* systemId, ::java::io::InputStream* stream) = 0;
    virtual XMLStreamReader* createXMLStreamReader(::java::io::Reader* reader) = 0;
    virtual XMLStreamReader* createXMLStreamReader(::javax::xml::transform::Source* source) = 0;
    virtual XMLStreamReader* createXMLStreamReader(::java::io::InputStream* stream) = 0;
    virtual XMLStreamReader* createXMLStreamReader(::java::io::InputStream* stream, ::java::lang::String* encoding) = 0;
    virtual XMLStreamReader* createXMLStreamReader(::java::lang::String* systemId, ::java::io::InputStream* stream) = 0;
    virtual XMLStreamReader* createXMLStreamReader(::java::lang::String* systemId, ::java::io::Reader* reader) = 0;
    virtual ::javax::xml::stream::util::XMLEventAllocator* getEventAllocator() = 0;
    virtual ::java::lang::Object* getProperty(::java::lang::String* name) = 0;
    virtual XMLReporter* getXMLReporter() = 0;
    virtual XMLResolver* getXMLResolver() = 0;
    virtual bool isPropertySupported(::java::lang::String* name) = 0;
    static XMLInputFactory* newFactory();
    static XMLInputFactory* newFactory(::java::lang::String* factoryId, ::java::lang::ClassLoader* classLoader);
    static XMLInputFactory* newInstance();
    static XMLInputFactory* newInstance(::java::lang::String* factoryId, ::java::lang::ClassLoader* classLoader);
    virtual void setEventAllocator(::javax::xml::stream::util::XMLEventAllocator* allocator) = 0;
    virtual void setProperty(::java::lang::String* name, ::java::lang::Object* value) = 0;
    virtual void setXMLReporter(XMLReporter* reporter) = 0;
    virtual void setXMLResolver(XMLResolver* resolver) = 0;

    // Generated

public: /* protected */
    XMLInputFactory();
protected:
    XMLInputFactory(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static ::java::lang::String*& ALLOCATOR();

public: /* package */
    static ::java::lang::String*& DEFAULIMPL();

public:
    static ::java::lang::String*& IS_COALESCING();
    static ::java::lang::String*& IS_NAMESPACE_AWARE();
    static ::java::lang::String*& IS_REPLACING_ENTITY_REFERENCES();
    static ::java::lang::String*& IS_SUPPORTING_EXTERNAL_ENTITIES();
    static ::java::lang::String*& IS_VALIDATING();

public: /* package */
    static ::java::lang::String*& JAXPFACTORYID();

public:
    static ::java::lang::String*& REPORTER();
    static ::java::lang::String*& RESOLVER();
    static ::java::lang::String*& SUPPORT_DTD();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Exception.hpp>

struct default_init_tag;

class javax::xml::stream::XMLStreamException
    : public ::java::lang::Exception
{

public:
    typedef ::java::lang::Exception super;

public: /* protected */
    Location* location {  };
    ::java::lang::Throwable* nested {  };

protected:
    void ctor();
    void ctor(::java::lang::String* msg);
    void ctor(::java::lang::Throwable* th);
    void ctor(::java::lang::String* msg, ::java::lang::Throwable* th);
    void ctor(::java::lang::String* msg, Location* location);
    void ctor(::java::lang::String* msg, Location* location, ::java::lang::Throwable* th);

public:
    virtual Location* getLocation();
    virtual ::java::lang::Throwable* getNestedException();

    // Generated
    XMLStreamException();
    XMLStreamException(::java::lang::String* msg);
    XMLStreamException(::java::lang::Throwable* th);
    XMLStreamException(::java::lang::String* msg, ::java::lang::Throwable* th);
    XMLStreamException(::java::lang::String* msg, Location* location);
    XMLStreamException(::java::lang::String* msg, Location* location, ::java::lang::Throwable* th);
protected:
    XMLStreamException(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

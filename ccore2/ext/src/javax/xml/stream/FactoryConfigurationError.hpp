// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Error.hpp>

struct default_init_tag;

class javax::xml::stream::FactoryConfigurationError
    : public ::java::lang::Error
{

public:
    typedef ::java::lang::Error super;

public: /* package */
    ::java::lang::Exception* nested {  };

private:
    static constexpr int64_t serialVersionUID { int64_t(-2994412584589975744LL) };

protected:
    void ctor();
    void ctor(::java::lang::Exception* e);
    void ctor(::java::lang::String* msg);
    void ctor(::java::lang::Exception* e, ::java::lang::String* msg);
    void ctor(::java::lang::String* msg, ::java::lang::Exception* e);

public:
    ::java::lang::Throwable* getCause() override;
    virtual ::java::lang::Exception* getException();
    ::java::lang::String* getMessage() override;

    // Generated
    FactoryConfigurationError();
    FactoryConfigurationError(::java::lang::Exception* e);
    FactoryConfigurationError(::java::lang::String* msg);
    FactoryConfigurationError(::java::lang::Exception* e, ::java::lang::String* msg);
    FactoryConfigurationError(::java::lang::String* msg, ::java::lang::Exception* e);
protected:
    FactoryConfigurationError(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

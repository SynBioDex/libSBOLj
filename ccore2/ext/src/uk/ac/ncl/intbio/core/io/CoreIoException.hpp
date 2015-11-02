// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Exception.hpp>

struct default_init_tag;

class uk::ac::ncl::intbio::core::io::CoreIoException
    : public ::java::lang::Exception
{

public:
    typedef ::java::lang::Exception super;

protected:
    void ctor();
    void ctor(::java::lang::String* message);
    void ctor(::java::lang::Throwable* cause);
    void ctor(::java::lang::String* message, ::java::lang::Throwable* cause);
    void ctor(::java::lang::String* message, ::java::lang::Throwable* cause, bool enableSuppression, bool writableStackTrace);

    // Generated

public:
    CoreIoException();
    CoreIoException(::java::lang::String* message);
    CoreIoException(::java::lang::Throwable* cause);
    CoreIoException(::java::lang::String* message, ::java::lang::Throwable* cause);
    CoreIoException(::java::lang::String* message, ::java::lang::Throwable* cause, bool enableSuppression, bool writableStackTrace);
protected:
    CoreIoException(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

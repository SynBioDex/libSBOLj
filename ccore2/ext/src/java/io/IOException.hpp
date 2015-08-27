// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Exception.hpp>

struct default_init_tag;

class java::io::IOException
    : public ::java::lang::Exception
{

public:
    typedef ::java::lang::Exception super;

public: /* package */
    static constexpr int64_t serialVersionUID { int64_t(7818375828146090155LL) };

protected:
    void ctor();
    void ctor(::java::lang::String* message);
    void ctor(::java::lang::Throwable* cause);
    void ctor(::java::lang::String* message, ::java::lang::Throwable* cause);

    // Generated

public:
    IOException();
    IOException(::java::lang::String* message);
    IOException(::java::lang::Throwable* cause);
    IOException(::java::lang::String* message, ::java::lang::Throwable* cause);
protected:
    IOException(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

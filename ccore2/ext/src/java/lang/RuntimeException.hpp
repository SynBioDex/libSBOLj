// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Exception.hpp>

struct default_init_tag;

class java::lang::RuntimeException
    : public Exception
{

public:
    typedef Exception super;

public: /* package */
    static constexpr int64_t serialVersionUID { int64_t(-7034897190745766939LL) };

protected:
    void ctor();
    void ctor(String* message);
    void ctor(Throwable* cause);
    void ctor(String* message, Throwable* cause);
    void ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace);

    // Generated

public:
    RuntimeException();
    RuntimeException(String* message);
    RuntimeException(Throwable* cause);
    RuntimeException(String* message, Throwable* cause);

public: /* protected */
    RuntimeException(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace);
protected:
    RuntimeException(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

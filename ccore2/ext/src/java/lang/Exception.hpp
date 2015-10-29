// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Throwable.hpp>

struct default_init_tag;

class java::lang::Exception
    : public Throwable
{

public:
    typedef Throwable super;

public: /* package */
    static constexpr int64_t serialVersionUID { int64_t(-3387516993124229948LL) };

protected:
    void ctor();
    void ctor(String* message);
    void ctor(Throwable* cause);
    void ctor(String* message, Throwable* cause);
    void ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace);

    // Generated

public:
    Exception();
    Exception(String* message);
    Exception(Throwable* cause);
    Exception(String* message, Throwable* cause);

public: /* protected */
    Exception(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace);
protected:
    Exception(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::lang::Throwable_PrintStreamOrWriter
    : public virtual Object
{

public:
    typedef Object super;

    /*void ctor(); (private) */

public: /* package */
    virtual Object* lock() = 0;
    virtual void println(Object* o) = 0;

    // Generated

public:
    Throwable_PrintStreamOrWriter();
protected:
    Throwable_PrintStreamOrWriter(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

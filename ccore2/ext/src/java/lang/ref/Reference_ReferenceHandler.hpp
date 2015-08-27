// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/ref/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Thread.hpp>

struct default_init_tag;

class java::lang::ref::Reference_ReferenceHandler
    : public ::java::lang::Thread
{

public:
    typedef ::java::lang::Thread super;

protected:
    void ctor(::java::lang::ThreadGroup* g, ::java::lang::String* name);

public:
    void run() override;

    // Generated

public: /* package */
    Reference_ReferenceHandler(::java::lang::ThreadGroup* g, ::java::lang::String* name);
protected:
    Reference_ReferenceHandler(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

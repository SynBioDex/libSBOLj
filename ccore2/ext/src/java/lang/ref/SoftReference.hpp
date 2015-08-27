// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/ref/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/ref/Reference.hpp>

struct default_init_tag;

class java::lang::ref::SoftReference
    : public Reference
{

public:
    typedef Reference super;

private:
    static int64_t clock_;
    int64_t timestamp {  };

protected:
    void ctor(::java::lang::Object* referent);
    void ctor(::java::lang::Object* referent, ReferenceQueue* q);

public:
    ::java::lang::Object* get() override;

    // Generated
    SoftReference(::java::lang::Object* referent);
    SoftReference(::java::lang::Object* referent, ReferenceQueue* q);
protected:
    SoftReference(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static int64_t& clock();
    virtual ::java::lang::Class* getClass0();
};

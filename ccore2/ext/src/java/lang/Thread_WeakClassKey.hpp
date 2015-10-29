// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/ref/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/ref/WeakReference.hpp>

struct default_init_tag;

class java::lang::Thread_WeakClassKey
    : public ::java::lang::ref::WeakReference
{

public:
    typedef ::java::lang::ref::WeakReference super;

private:
    int32_t hash {  };

protected:
    void ctor(Class* cl, ::java::lang::ref::ReferenceQueue* refQueue);

public:
    bool equals(Object* obj) override;
    int32_t hashCode() override;

    // Generated

public: /* package */
    Thread_WeakClassKey(Class* cl, ::java::lang::ref::ReferenceQueue* refQueue);
protected:
    Thread_WeakClassKey(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::Dictionary
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

protected:
    void ctor();

public:
    virtual Enumeration* elements() = 0;
    virtual ::java::lang::Object* get(::java::lang::Object* key) = 0;
    virtual bool isEmpty() = 0;
    virtual Enumeration* keys() = 0;
    virtual ::java::lang::Object* put(::java::lang::Object* key, ::java::lang::Object* value) = 0;
    virtual ::java::lang::Object* remove(::java::lang::Object* key) = 0;
    virtual int32_t size() = 0;

    // Generated
    Dictionary();
protected:
    Dictionary(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

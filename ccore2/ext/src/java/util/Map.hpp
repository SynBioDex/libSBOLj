// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct java::util::Map
    : public virtual ::java::lang::Object
{

    virtual void clear() = 0;
    virtual bool containsKey(::java::lang::Object* key) = 0;
    virtual bool containsValue(::java::lang::Object* value) = 0;
    virtual Set* entrySet() = 0;
    /*virtual bool equals(::java::lang::Object* o); (already declared) */
    virtual ::java::lang::Object* get(::java::lang::Object* key) = 0;
    /*virtual int32_t hashCode(); (already declared) */
    virtual bool isEmpty() = 0;
    virtual Set* keySet() = 0;
    virtual ::java::lang::Object* put(::java::lang::Object* key, ::java::lang::Object* value) = 0;
    virtual void putAll(Map* m) = 0;
    virtual ::java::lang::Object* remove(::java::lang::Object* key) = 0;
    virtual int32_t size() = 0;
    virtual Collection* values() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Iterable.hpp>

struct java::util::Collection
    : public virtual ::java::lang::Iterable
{

    virtual bool add(::java::lang::Object* e) = 0;
    virtual bool addAll(Collection* c) = 0;
    virtual void clear() = 0;
    virtual bool contains(::java::lang::Object* o) = 0;
    virtual bool containsAll(Collection* c) = 0;
    /*virtual bool equals(::java::lang::Object* o); (already declared) */
    /*virtual int32_t hashCode(); (already declared) */
    virtual bool isEmpty() = 0;
    /*Iterator* iterator(); (already declared) */
    virtual bool remove(::java::lang::Object* o) = 0;
    virtual bool removeAll(Collection* c) = 0;
    virtual bool retainAll(Collection* c) = 0;
    virtual int32_t size() = 0;
    virtual ::java::lang::ObjectArray* toArray_() = 0;
    virtual ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) = 0;

    // Generated
    static ::java::lang::Class *class_();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Collection.hpp>

struct java::util::Queue
    : public virtual Collection
{

    /*bool add(::java::lang::Object* e); (already declared) */
    virtual ::java::lang::Object* element() = 0;
    virtual bool offer(::java::lang::Object* e) = 0;
    virtual ::java::lang::Object* peek() = 0;
    virtual ::java::lang::Object* poll() = 0;
    virtual ::java::lang::Object* remove() = 0;

    // Generated
    static ::java::lang::Class *class_();
    virtual bool remove(::java::lang::Object* o) = 0;
};

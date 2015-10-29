// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct java::util::Iterator
    : public virtual ::java::lang::Object
{

    virtual bool hasNext() = 0;
    virtual ::java::lang::Object* next() = 0;
    virtual void remove() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct java::lang::Comparable
    : public virtual Object
{

    virtual int32_t compareTo(Object* o) = 0;

    // Generated
    static ::java::lang::Class *class_();
};

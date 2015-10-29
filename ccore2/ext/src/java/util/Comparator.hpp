// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct java::util::Comparator
    : public virtual ::java::lang::Object
{

    virtual int32_t compare(::java::lang::Object* o1, ::java::lang::Object* o2) = 0;
    /*virtual bool equals(::java::lang::Object* obj); (already declared) */

    // Generated
    static ::java::lang::Class *class_();
};

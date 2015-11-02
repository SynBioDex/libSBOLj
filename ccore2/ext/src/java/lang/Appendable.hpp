// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct java::lang::Appendable
    : public virtual Object
{

    virtual Appendable* append(CharSequence* csq) = 0;
    virtual Appendable* append(char16_t c) = 0;
    virtual Appendable* append(CharSequence* csq, int32_t start, int32_t end) = 0;

    // Generated
    static ::java::lang::Class *class_();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct java::lang::CharSequence
    : public virtual Object
{

    virtual char16_t charAt(int32_t index) = 0;
    virtual int32_t length() = 0;
    virtual CharSequence* subSequence(int32_t start, int32_t end) = 0;
    /*virtual String* toString(); (already declared) */

    // Generated
    static ::java::lang::Class *class_();
};

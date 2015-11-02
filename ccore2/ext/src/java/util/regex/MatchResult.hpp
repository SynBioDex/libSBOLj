// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct java::util::regex::MatchResult
    : public virtual ::java::lang::Object
{

    virtual int32_t end() = 0;
    virtual int32_t end(int32_t group) = 0;
    virtual ::java::lang::String* group() = 0;
    virtual ::java::lang::String* group(int32_t group) = 0;
    virtual int32_t groupCount() = 0;
    virtual int32_t start() = 0;
    virtual int32_t start(int32_t group) = 0;

    // Generated
    static ::java::lang::Class *class_();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <sun/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct sun::util::LocaleServiceProviderPool_LocalizedObjectGetter
    : public virtual ::java::lang::Object
{

    virtual ::java::lang::Object* getObject(::java::lang::Object* arg0, ::java::util::Locale* arg1, ::java::lang::String* arg2, ::java::lang::ObjectArray* arg3) = 0;

    // Generated
    static ::java::lang::Class *class_();
};

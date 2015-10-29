// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/annotation/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/reflect/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace lang
    {
        namespace annotation
        {
typedef ::SubArray< ::java::lang::annotation::Annotation, ::java::lang::ObjectArray > AnnotationArray;
        } // annotation
    } // lang
} // java

struct java::lang::reflect::AnnotatedElement
    : public virtual ::java::lang::Object
{

    virtual ::java::lang::annotation::Annotation* getAnnotation(::java::lang::Class* annotationClass) = 0;
    virtual ::java::lang::annotation::AnnotationArray* getAnnotations() = 0;
    virtual ::java::lang::annotation::AnnotationArray* getDeclaredAnnotations() = 0;
    virtual bool isAnnotationPresent(::java::lang::Class* annotationClass) = 0;

    // Generated
    static ::java::lang::Class *class_();
};

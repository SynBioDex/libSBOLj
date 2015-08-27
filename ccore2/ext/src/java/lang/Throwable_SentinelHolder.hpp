// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::StackTraceElement, ObjectArray, ::java::io::SerializableArray > StackTraceElementArray;
    } // lang
} // java

struct default_init_tag;

class java::lang::Throwable_SentinelHolder
    : public virtual Object
{

public:
    typedef Object super;

private:
    static StackTraceElement* STACK_TRACE_ELEMENT_SENTINEL_;
    static StackTraceElementArray* STACK_TRACE_SENTINEL_;

    /*void ctor(); (private) */

    // Generated

public:
    Throwable_SentinelHolder();
protected:
    Throwable_SentinelHolder(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static StackTraceElement*& STACK_TRACE_ELEMENT_SENTINEL();
    static StackTraceElementArray*& STACK_TRACE_SENTINEL();

private:
    virtual ::java::lang::Class* getClass0();
};

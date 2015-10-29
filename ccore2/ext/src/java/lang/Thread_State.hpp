// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Enum.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::Enum, ObjectArray, ComparableArray, ::java::io::SerializableArray > EnumArray;
typedef ::SubArray< ::java::lang::Thread_State, EnumArray > Thread_StateArray;
    } // lang
} // java

struct default_init_tag;

class java::lang::Thread_State final
    : public Enum
{

public:
    typedef Enum super;

private:
    static Thread_StateArray* $VALUES_;

public:
    static Thread_State* BLOCKED;
    static Thread_State* NEW;
    static Thread_State* RUNNABLE;
    static Thread_State* TERMINATED;
    static Thread_State* TIMED_WAITING;
    static Thread_State* WAITING;

    /*void ctor(::java::lang::String* name, int ordinal); (private) */
    static Thread_State* valueOf(String* arg0);
    static Thread_StateArray* values();

    // Generated
    Thread_State(::java::lang::String* name, int ordinal);
protected:
    Thread_State(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static Enum* valueOf(Class* enumType, String* name);

private:
    static Thread_StateArray*& $VALUES();
    virtual ::java::lang::Class* getClass0();
};

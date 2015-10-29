// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Enum.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace lang
    {
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
    } // lang

    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::Enum, ObjectArray, ComparableArray, ::java::io::SerializableArray > EnumArray;
    } // lang

    namespace io
    {
typedef ::SubArray< ::java::io::File_PathStatus, ::java::lang::EnumArray > File_PathStatusArray;
    } // io
} // java

struct default_init_tag;

class java::io::File_PathStatus final
    : public ::java::lang::Enum
{

public:
    typedef ::java::lang::Enum super;

private:
    static File_PathStatusArray* $VALUES_;

public:
    static File_PathStatus* CHECKED;
    static File_PathStatus* INVALID;

    /*void ctor(::java::lang::String* name, int ordinal); (private) */
    static File_PathStatus* valueOf(::java::lang::String* arg0);
    static File_PathStatusArray* values();

    // Generated
    File_PathStatus(::java::lang::String* name, int ordinal);
protected:
    File_PathStatus(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static ::java::lang::Enum* valueOf(::java::lang::Class* enumType, ::java::lang::String* name);

private:
    static File_PathStatusArray*& $VALUES();
    virtual ::java::lang::Class* getClass0();
};

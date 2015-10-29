// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
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
    } // lang

    namespace util
    {
typedef ::SubArray< ::java::util::Locale_Category, ::java::lang::EnumArray > Locale_CategoryArray;
    } // util
} // java

struct default_init_tag;

class java::util::Locale_Category final
    : public ::java::lang::Enum
{

public:
    typedef ::java::lang::Enum super;

private:
    static Locale_CategoryArray* $VALUES_;

public:
    static Locale_Category* DISPLAY;
    static Locale_Category* FORMAT;

public: /* package */
    ::java::lang::String* countryKey {  };
    ::java::lang::String* languageKey {  };
    ::java::lang::String* scriptKey {  };
    ::java::lang::String* variantKey {  };

    /*void ctor(::java::lang::String* name, int ordinal, ::java::lang::String* arg0, ::java::lang::String* arg1, ::java::lang::String* arg2, ::java::lang::String* arg3); (private) */

public:
    static Locale_Category* valueOf(::java::lang::String* arg0);
    static Locale_CategoryArray* values();

    // Generated
    Locale_Category(::java::lang::String* name, int ordinal);
protected:
    Locale_Category(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static ::java::lang::Enum* valueOf(::java::lang::Class* enumType, ::java::lang::String* name);

private:
    static Locale_CategoryArray*& $VALUES();
    virtual ::java::lang::Class* getClass0();
};

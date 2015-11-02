// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/OrientationType.java

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
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
} // java

namespace org
{
    namespace sbolstandard
    {
        namespace core2
        {
typedef ::SubArray< ::org::sbolstandard::core2::OrientationType, ::java::lang::EnumArray > OrientationTypeArray;
        } // core2
    } // sbolstandard
} // org

struct default_init_tag;

class org::sbolstandard::core2::OrientationType final
    : public ::java::lang::Enum
{

public:
    typedef ::java::lang::Enum super;

public: /* package */
    static OrientationType *INLINE;
    static OrientationType *REVERSECOMPLEMENT;

private:
    ::java::lang::String* orientationType {  };
protected:
    void ctor(::java::lang::String* name, int ordinal, ::java::lang::String* orientationType);

public: /* package */
    ::java::lang::String* getOrientationType();

public:
    ::java::lang::String* toString() override;

public: /* package */
    static OrientationType* convertToOrientationType(::java::net::URI* orientation);
    static ::java::net::URI* convertToURI(OrientationType* orientation);

private:
    static ::java::net::URI* inline__;
    static ::java::net::URI* reverseComplement_;

    // Generated
    OrientationType(::java::lang::String* name, int ordinal, ::java::lang::String* orientationType);
protected:
    OrientationType(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::java::net::URI*& inline_();
    static ::java::net::URI*& reverseComplement();

public:
    static OrientationType* valueOf(::java::lang::String* a0);
    static OrientationTypeArray* values();

private:
    virtual ::java::lang::Class* getClass0();
};

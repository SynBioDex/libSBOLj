// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/RestrictionType.java

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
typedef ::SubArray< ::org::sbolstandard::core2::RestrictionType, ::java::lang::EnumArray > RestrictionTypeArray;
        } // core2
    } // sbolstandard
} // org

struct default_init_tag;

class org::sbolstandard::core2::RestrictionType final
    : public ::java::lang::Enum
{

public:
    typedef ::java::lang::Enum super;

public: /* package */
    static RestrictionType *PRECEDES;
    static RestrictionType *SAME_ORIENTATION_AS;
    static RestrictionType *OPPOSITE_ORIENTATION_AS;

private:
    ::java::lang::String* restrictionType {  };
protected:
    void ctor(::java::lang::String* name, int ordinal, ::java::lang::String* restrictionType);

public: /* package */
    ::java::lang::String* getRestrictionType();

public:
    ::java::lang::String* toString() override;

public: /* package */
    static RestrictionType* convertToRestrictionType(::java::net::URI* restriction);
    static ::java::net::URI* convertToURI(RestrictionType* restriction);

private:
    static ::java::net::URI* precedes_;
    static ::java::net::URI* sameOrientationAs_;
    static ::java::net::URI* oppositeOrientationAs_;

    // Generated
    RestrictionType(::java::lang::String* name, int ordinal, ::java::lang::String* restrictionType);
protected:
    RestrictionType(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::java::net::URI*& precedes();
    static ::java::net::URI*& sameOrientationAs();
    static ::java::net::URI*& oppositeOrientationAs();

public:
    static RestrictionType* valueOf(::java::lang::String* a0);
    static RestrictionTypeArray* values();

private:
    virtual ::java::lang::Class* getClass0();
};

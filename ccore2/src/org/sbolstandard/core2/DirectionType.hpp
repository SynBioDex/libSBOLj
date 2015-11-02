// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/DirectionType.java

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
typedef ::SubArray< ::org::sbolstandard::core2::DirectionType, ::java::lang::EnumArray > DirectionTypeArray;
        } // core2
    } // sbolstandard
} // org

struct default_init_tag;

class org::sbolstandard::core2::DirectionType final
    : public ::java::lang::Enum
{

public:
    typedef ::java::lang::Enum super;

public: /* package */
    static DirectionType *IN;
    static DirectionType *OUT;
    static DirectionType *INOUT;
    static DirectionType *NONE;

private:
    ::java::lang::String* directionType {  };
protected:
    void ctor(::java::lang::String* name, int ordinal, ::java::lang::String* directionType);

public: /* package */
    ::java::lang::String* getDirectionType();

public:
    ::java::lang::String* toString() override;

public: /* package */
    static DirectionType* convertToDirectionType(::java::net::URI* direction);
    static ::java::net::URI* convertToURI(DirectionType* direction);

private:
    static ::java::net::URI* in_;
    static ::java::net::URI* out_;
    static ::java::net::URI* inout_;
    static ::java::net::URI* none_;

    // Generated
    DirectionType(::java::lang::String* name, int ordinal, ::java::lang::String* directionType);
protected:
    DirectionType(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::java::net::URI*& in();
    static ::java::net::URI*& out();
    static ::java::net::URI*& inout();
    static ::java::net::URI*& none();

public:
    static DirectionType* valueOf(::java::lang::String* a0);
    static DirectionTypeArray* values();

private:
    virtual ::java::lang::Class* getClass0();
};

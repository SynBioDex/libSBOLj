// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/AccessType.java

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
typedef ::SubArray< ::org::sbolstandard::core2::AccessType, ::java::lang::EnumArray > AccessTypeArray;
        } // core2
    } // sbolstandard
} // org

struct default_init_tag;

class org::sbolstandard::core2::AccessType final
    : public ::java::lang::Enum
{

public:
    typedef ::java::lang::Enum super;

public: /* package */
    static AccessType *PUBLIC;
    static AccessType *PRIVATE;

private:
    ::java::lang::String* accessType {  };
protected:
    void ctor(::java::lang::String* name, int ordinal, ::java::lang::String* accessType);

public: /* package */
    static AccessType* convertToAccessType(::java::net::URI* access);
    static ::java::net::URI* convertToURI(AccessType* access);
    ::java::lang::String* getAccessType();

public:
    ::java::lang::String* toString() override;

private:
    static ::java::net::URI* publicURI_;
    static ::java::net::URI* privateURI_;

    // Generated
    AccessType(::java::lang::String* name, int ordinal, ::java::lang::String* accessType);
protected:
    AccessType(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::java::net::URI*& publicURI();
    static ::java::net::URI*& privateURI();

public:
    static AccessType* valueOf(::java::lang::String* a0);
    static AccessTypeArray* values();

private:
    virtual ::java::lang::Class* getClass0();
};

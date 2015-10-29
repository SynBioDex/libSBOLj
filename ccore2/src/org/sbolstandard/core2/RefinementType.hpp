// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/RefinementType.java

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
typedef ::SubArray< ::org::sbolstandard::core2::RefinementType, ::java::lang::EnumArray > RefinementTypeArray;
        } // core2
    } // sbolstandard
} // org

struct default_init_tag;

class org::sbolstandard::core2::RefinementType final
    : public ::java::lang::Enum
{

public:
    typedef ::java::lang::Enum super;

public: /* package */
    static RefinementType *VERIFYIDENTICAL;
    static RefinementType *USELOCAL;
    static RefinementType *USEREMOTE;
    static RefinementType *MERGE;

private:
    ::java::lang::String* refinementType {  };
protected:
    void ctor(::java::lang::String* name, int ordinal, ::java::lang::String* refinementType);

public: /* package */
    ::java::lang::String* getRefinementType();

public:
    ::java::lang::String* toString() override;

public: /* package */
    static RefinementType* convertToRefinementType(::java::net::URI* refinement);
    static ::java::net::URI* convertToURI(RefinementType* refinement);

private:
    static ::java::net::URI* merge_;
    static ::java::net::URI* useLocal_;
    static ::java::net::URI* useRemote_;
    static ::java::net::URI* verifyIdentical_;

    // Generated
    RefinementType(::java::lang::String* name, int ordinal, ::java::lang::String* refinementType);
protected:
    RefinementType(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::java::net::URI*& merge();
    static ::java::net::URI*& useLocal();
    static ::java::net::URI*& useRemote();
    static ::java::net::URI*& verifyIdentical();

public:
    static RefinementType* valueOf(::java::lang::String* a0);
    static RefinementTypeArray* values();

private:
    virtual ::java::lang::Class* getClass0();
};

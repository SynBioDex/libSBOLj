// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLValidationException.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/RuntimeException.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace org
{
    namespace sbolstandard
    {
        namespace core2
        {
typedef ::SubArray< ::org::sbolstandard::core2::Identified, ::java::lang::ObjectArray > IdentifiedArray;
        } // core2
    } // sbolstandard
} // org

struct default_init_tag;

class org::sbolstandard::core2::SBOLValidationException
    : public ::java::lang::RuntimeException
{

public:
    typedef ::java::lang::RuntimeException super;

private:
    static constexpr int64_t serialVersionUID { int64_t(1LL) };
    ::java::util::List* objects {  };
protected:
    void ctor(::java::lang::String* message, IdentifiedArray*/*...*/ objects);
    void ctor(::java::lang::String* message, ::java::util::Collection* objects);
    void ctor(::java::lang::String* message, ::java::lang::Throwable* cause, IdentifiedArray*/*...*/ objects);
    void ctor(::java::lang::Throwable* cause);

public: /* package */
    virtual ::java::util::Collection* getObjects();

private:
    static ::java::lang::String* formatMessage(::java::lang::String* message, ::java::util::Collection* objects);

    // Generated

public: /* package */
    SBOLValidationException(::java::lang::String* message, IdentifiedArray* objects);
    SBOLValidationException(::java::lang::String* message, ::java::util::Collection* objects);
    SBOLValidationException(::java::lang::String* message, ::java::lang::Throwable* cause, IdentifiedArray* objects);
    SBOLValidationException(::java::lang::Throwable* cause);
protected:
    SBOLValidationException(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLValidate.java

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
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
typedef ::SubArray< ::java::lang::CharSequence, ObjectArray > CharSequenceArray;
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::String, ObjectArray, ::java::io::SerializableArray, ComparableArray, CharSequenceArray > StringArray;
    } // lang
} // java

struct default_init_tag;

class org::sbolstandard::core2::SBOLValidate
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::java::lang::String* SBOLVersion_;
    static void usage();

public:
    static void validateCompliance(SBOLDocument* sbolDocument);
    static void validateCompleteness(SBOLDocument* sbolDocument);
    static void main(::java::lang::StringArray* args);

    // Generated
    SBOLValidate();
protected:
    SBOLValidate(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();
    static ::java::lang::String*& SBOLVersion();

private:
    virtual ::java::lang::Class* getClass0();
};

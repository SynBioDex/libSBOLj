// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComparableVersion.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <org/sbolstandard/core2/ComparableVersion_Item.hpp>

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

class org::sbolstandard::core2::ComparableVersion_StringItem
    : public virtual ::java::lang::Object
    , public virtual ComparableVersion_Item
{

public:
    typedef ::java::lang::Object super;

private:
    static ::java::lang::StringArray* QUALIFIERS_;
    static ::java::util::List* _QUALIFIERS_;
    static ::java::util::Properties* ALIASES_;
    static ::java::lang::String* RELEASE_VERSION_INDEX_;
    ::java::lang::String* value {  };
protected:
    void ctor(::java::lang::String* value, bool followedByDigit);

public:
    int32_t getType() override;
    bool isNull() override;
    static ::java::lang::String* comparableQualifier(::java::lang::String* qualifier);
    int32_t compareTo(ComparableVersion_Item* item) override;
    ::java::lang::String* toString() override;

    // Generated
    ComparableVersion_StringItem(::java::lang::String* value, bool followedByDigit);
protected:
    ComparableVersion_StringItem(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

private:
    static ::java::lang::StringArray*& QUALIFIERS();
    static ::java::util::List*& _QUALIFIERS();
    static ::java::util::Properties*& ALIASES();
    static ::java::lang::String*& RELEASE_VERSION_INDEX();
    virtual ::java::lang::Class* getClass0();
    friend class ComparableVersion;
    friend class ComparableVersion_Item;
    friend class ComparableVersion_IntegerItem;
    friend class ComparableVersion_ListItem;
};

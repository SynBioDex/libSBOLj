// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComparableVersion.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/Comparable.hpp>

struct default_init_tag;

class org::sbolstandard::core2::ComparableVersion
    : public virtual ::java::lang::Object
    , public virtual ::java::lang::Comparable
{

public:
    typedef ::java::lang::Object super;

private:
    ::java::lang::String* value {  };
    ::java::lang::String* canonical {  };
    ComparableVersion_ListItem* items {  };
protected:
    void ctor(::java::lang::String* version);

public:
    void parseVersion(::java::lang::String* version);

private:
    static ComparableVersion_Item* parseItem(bool isDigit, ::java::lang::String* buf);

public:
    virtual int32_t compareTo(ComparableVersion* o);
    ::java::lang::String* toString() override;
    bool equals(::java::lang::Object* o) override;
    int32_t hashCode() override;

    // Generated
    ComparableVersion(::java::lang::String* version);
protected:
    ComparableVersion(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual int32_t compareTo(::java::lang::Object* o) override;

private:
    virtual ::java::lang::Class* getClass0();
    friend class ComparableVersion_Item;
    friend class ComparableVersion_IntegerItem;
    friend class ComparableVersion_StringItem;
    friend class ComparableVersion_ListItem;
};

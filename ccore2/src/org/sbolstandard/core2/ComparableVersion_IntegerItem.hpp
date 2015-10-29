// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComparableVersion.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/math/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <org/sbolstandard/core2/ComparableVersion_Item.hpp>

struct default_init_tag;

class org::sbolstandard::core2::ComparableVersion_IntegerItem
    : public virtual ::java::lang::Object
    , public virtual ComparableVersion_Item
{

public:
    typedef ::java::lang::Object super;

private:
    static ::java::math::BigInteger* BIG_INTEGER_ZERO_;
    ::java::math::BigInteger* value {  };
    static ComparableVersion_IntegerItem* ZERO_;
protected:
    void ctor();
    void ctor(::java::lang::String* str);

public:
    int32_t getType() override;
    bool isNull() override;
    int32_t compareTo(ComparableVersion_Item* item) override;
    ::java::lang::String* toString() override;

    // Generated

private:
    ComparableVersion_IntegerItem();

public:
    ComparableVersion_IntegerItem(::java::lang::String* str);
protected:
    ComparableVersion_IntegerItem(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

private:
    static ::java::math::BigInteger*& BIG_INTEGER_ZERO();

public:
    static ComparableVersion_IntegerItem*& ZERO();

private:
    virtual ::java::lang::Class* getClass0();
    friend class ComparableVersion;
    friend class ComparableVersion_Item;
    friend class ComparableVersion_StringItem;
    friend class ComparableVersion_ListItem;
};

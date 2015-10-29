// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComparableVersion.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/ArrayList.hpp>
#include <org/sbolstandard/core2/ComparableVersion_Item.hpp>

struct default_init_tag;

class org::sbolstandard::core2::ComparableVersion_ListItem
    : public ::java::util::ArrayList
    , public virtual ComparableVersion_Item
{

public:
    typedef ::java::util::ArrayList super;
    int32_t getType() override;
    bool isNull() override;

public: /* package */
    virtual void normalize();

public:
    int32_t compareTo(ComparableVersion_Item* item) override;
    ::java::lang::String* toString() override;

    // Generated
    ComparableVersion_ListItem();
protected:
    ComparableVersion_ListItem(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
    friend class ComparableVersion;
    friend class ComparableVersion_Item;
    friend class ComparableVersion_IntegerItem;
    friend class ComparableVersion_StringItem;
};

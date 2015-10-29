// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComparableVersion.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct org::sbolstandard::core2::ComparableVersion_Item
    : public virtual ::java::lang::Object
{
    static constexpr int32_t INTEGER_ITEM { int32_t(0) };
    static constexpr int32_t STRING_ITEM { int32_t(1) };
    static constexpr int32_t LIST_ITEM { int32_t(2) };
    virtual int32_t compareTo(ComparableVersion_Item* item) = 0;
    virtual int32_t getType() = 0;
    virtual bool isNull() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

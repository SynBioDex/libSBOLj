// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/PropertyValue.hpp>

struct uk::ac::ncl::intbio::core::datatree::Literal
    : public virtual PropertyValue
{

    virtual ::java::lang::Object* getValue() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

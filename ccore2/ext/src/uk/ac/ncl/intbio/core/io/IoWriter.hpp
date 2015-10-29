// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct uk::ac::ncl::intbio::core::io::IoWriter
    : public virtual ::java::lang::Object
{

    virtual void write(::uk::ac::ncl::intbio::core::datatree::DocumentRoot* document) = 0;

    // Generated
    static ::java::lang::Class *class_();
};

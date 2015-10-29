// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Document.hpp>

struct uk::ac::ncl::intbio::core::datatree::DocumentRoot
    : public virtual Document
{

    virtual ::java::util::List* getTopLevelDocuments() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

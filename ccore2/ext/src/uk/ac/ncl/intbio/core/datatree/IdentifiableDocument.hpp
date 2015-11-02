// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Document.hpp>

struct uk::ac::ncl::intbio::core::datatree::IdentifiableDocument
    : public virtual Document
{

    virtual ::java::net::URI* getIdentity() = 0;
    virtual ::java::util::List* getProperties() = 0;
    virtual ::java::util::List* getPropertyValues(::java::lang::Object* propertyName) = 0;
    virtual ::java::lang::Object* getType() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Literal.hpp>
#include <java/net/URI.hpp>

struct uk::ac::ncl::intbio::core::datatree::Literal_UriLiteral
    : public virtual Literal
{

    ::java::net::URI* getValue() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

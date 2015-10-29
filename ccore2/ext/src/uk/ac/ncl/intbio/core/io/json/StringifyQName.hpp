// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-io-json/0.1.2/sbol-data-io-json-0.1.2.jar

#pragma once

#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/io/json/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class uk::ac::ncl::intbio::core::io::json::StringifyQName
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::uk::ac::ncl::intbio::core::datatree::NameTransformer* qname2string_;
    static ::uk::ac::ncl::intbio::core::datatree::NameTransformer* string2qname_;

protected:
    void ctor();

    // Generated

public:
    StringifyQName();
protected:
    StringifyQName(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static ::uk::ac::ncl::intbio::core::datatree::NameTransformer*& qname2string();
    static ::uk::ac::ncl::intbio::core::datatree::NameTransformer*& string2qname();

private:
    virtual ::java::lang::Class* getClass0();
};

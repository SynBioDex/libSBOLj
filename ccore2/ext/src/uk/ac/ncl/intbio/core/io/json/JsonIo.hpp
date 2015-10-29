// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-io-json/0.1.2/sbol-data-io-json-0.1.2.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/json/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/json/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/io/json/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class uk::ac::ncl::intbio::core::io::json::JsonIo
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    ::java::lang::String* rdfAbout {  };
    ::java::lang::String* rdfResource {  };

protected:
    void ctor();
    /*::java::util::Map* cholate(::java::util::List* ps); (private) */

public:
    virtual ::uk::ac::ncl::intbio::core::io::IoReader* createIoReader(::javax::json::JsonStructure* json);
    virtual ::uk::ac::ncl::intbio::core::io::IoWriter* createIoWriter(::javax::json::stream::JsonGenerator* writer);
    virtual ::java::lang::String* getRdfAbout();
    virtual ::java::lang::String* getRdfResource();
    virtual void setRdfAbout(::java::lang::String* rdfAbout);
    virtual void setRdfResource(::java::lang::String* rdfResource);

    // Generated
    JsonIo();
protected:
    JsonIo(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

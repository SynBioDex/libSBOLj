// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-io-RDF/0.1.2/sbol-data-io-RDF-0.1.2.jar

#pragma once

#include <javax/xml/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/io/rdf/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class uk::ac::ncl::intbio::core::io::rdf::RdfIo
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

protected:
    void ctor();

public:
    virtual ::uk::ac::ncl::intbio::core::io::IoReader* createIoReader(::javax::xml::stream::XMLStreamReader* xmlReader);
    virtual ::uk::ac::ncl::intbio::core::io::IoWriter* createIoWriter(::javax::xml::stream::XMLStreamWriter* writer);

    // Generated
    RdfIo();
protected:
    RdfIo(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-io-turtle/0.1.2/sbol-data-io-turtle-0.1.2.jar

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/intbio/core/io/turtle/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class uk::ac::intbio::core::io::turtle::TurtleIo
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

protected:
    void ctor();

public:
    virtual ::uk::ac::ncl::intbio::core::io::IoReader* createIoReader(::java::io::Reader* reader);
    virtual ::uk::ac::ncl::intbio::core::io::IoWriter* createIoWriter(::java::io::PrintWriter* writer);

    // Generated
    TurtleIo();
protected:
    TurtleIo(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

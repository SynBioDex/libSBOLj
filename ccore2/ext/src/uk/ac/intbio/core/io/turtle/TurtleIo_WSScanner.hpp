// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-io-turtle/0.1.2/sbol-data-io-turtle-0.1.2.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/intbio/core/io/turtle/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class uk::ac::intbio::core::io::turtle::TurtleIo_WSScanner final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    ::java::lang::String* nextToken {  };
    ::java::io::Reader* reader {  };
    ::java::lang::StringBuilder* tokenBuilder {  };

protected:
    void ctor(::java::io::Reader* reader);

public:
    bool hasNext();
    bool hasNext(::java::util::regex::Pattern* p);
    /*bool isWhitespace(char16_t c); (private) */
    ::java::lang::String* next();
    ::java::lang::String* next(::java::util::regex::Pattern* p);
    /*bool readNextIfNeeded(); (private) */
    /*bool readNextToken(); (private) */

    // Generated
    TurtleIo_WSScanner(::java::io::Reader* reader);
protected:
    TurtleIo_WSScanner(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

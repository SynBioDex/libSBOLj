// Generated from /Users/myers/.m2/repository/org/glassfish/javax.json/1.0.2/javax.json-1.0.2.jar

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/charset/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/json/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct javax::json::stream::JsonGeneratorFactory
    : public virtual ::java::lang::Object
{

    virtual JsonGenerator* createGenerator(::java::io::Writer* writer) = 0;
    virtual JsonGenerator* createGenerator(::java::io::OutputStream* out) = 0;
    virtual JsonGenerator* createGenerator(::java::io::OutputStream* out, ::java::nio::charset::Charset* charset) = 0;
    virtual ::java::util::Map* getConfigInUse() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

// Generated from /Users/myers/.m2/repository/org/glassfish/javax.json/1.0.2/javax.json-1.0.2.jar

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/charset/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/json/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct javax::json::JsonReaderFactory
    : public virtual ::java::lang::Object
{

    virtual JsonReader* createReader(::java::io::Reader* reader) = 0;
    virtual JsonReader* createReader(::java::io::InputStream* in) = 0;
    virtual JsonReader* createReader(::java::io::InputStream* in, ::java::nio::charset::Charset* charset) = 0;
    virtual ::java::util::Map* getConfigInUse() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

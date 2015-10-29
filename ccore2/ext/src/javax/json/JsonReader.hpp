// Generated from /Users/myers/.m2/repository/org/glassfish/javax.json/1.0.2/javax.json-1.0.2.jar

#pragma once

#include <javax/json/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/Closeable.hpp>

struct javax::json::JsonReader
    : public virtual ::java::io::Closeable
{

    /*void close(); (already declared) */
    virtual JsonStructure* read() = 0;
    virtual JsonArray_* readArray_() = 0;
    virtual JsonObject* readObject() = 0;

    // Generated
    static ::java::lang::Class *class_();
};

// Generated from /Users/myers/.m2/repository/org/glassfish/javax.json/1.0.2/javax.json-1.0.2.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/math/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/json/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/json/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/Flushable.hpp>
#include <java/io/Closeable.hpp>

struct javax::json::stream::JsonGenerator
    : public virtual ::java::io::Flushable
    , public virtual ::java::io::Closeable
{

private:
    static ::java::lang::String* PRETTY_PRINTING_;

    /*void close(); (already declared) */
    /*void flush(); (already declared) */

public:
    virtual JsonGenerator* write(::javax::json::JsonValue* value) = 0;
    virtual JsonGenerator* write(::java::lang::String* value) = 0;
    virtual JsonGenerator* write(::java::math::BigDecimal* value) = 0;
    virtual JsonGenerator* write(::java::math::BigInteger* value) = 0;
    virtual JsonGenerator* write(int32_t value) = 0;
    virtual JsonGenerator* write(int64_t value) = 0;
    virtual JsonGenerator* write(double value) = 0;
    virtual JsonGenerator* write(bool value) = 0;
    virtual JsonGenerator* write(::java::lang::String* name, ::javax::json::JsonValue* value) = 0;
    virtual JsonGenerator* write(::java::lang::String* name, ::java::lang::String* value) = 0;
    virtual JsonGenerator* write(::java::lang::String* name, ::java::math::BigInteger* value) = 0;
    virtual JsonGenerator* write(::java::lang::String* name, ::java::math::BigDecimal* value) = 0;
    virtual JsonGenerator* write(::java::lang::String* name, int32_t value) = 0;
    virtual JsonGenerator* write(::java::lang::String* name, int64_t value) = 0;
    virtual JsonGenerator* write(::java::lang::String* name, double value) = 0;
    virtual JsonGenerator* write(::java::lang::String* name, bool value) = 0;
    virtual JsonGenerator* writeEnd() = 0;
    virtual JsonGenerator* writeNull() = 0;
    virtual JsonGenerator* writeNull(::java::lang::String* name) = 0;
    virtual JsonGenerator* writeStartArray_() = 0;
    virtual JsonGenerator* writeStartArray_(::java::lang::String* name) = 0;
    virtual JsonGenerator* writeStartObject() = 0;
    virtual JsonGenerator* writeStartObject(::java::lang::String* name) = 0;

    // Generated
    static ::java::lang::Class *class_();
    static ::java::lang::String*& PRETTY_PRINTING();
};

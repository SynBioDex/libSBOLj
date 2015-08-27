// Generated from /Users/myers/.m2/repository/org/glassfish/javax.json/1.0.2/javax.json-1.0.2.jar

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/json/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/json/stream/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class javax::json::Json
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

    /*void ctor(); (private) */
    static JsonArrayBuilder* createArrayBuilder();
    static JsonBuilderFactory* createBuilderFactory(::java::util::Map* config);
    static ::javax::json::stream::JsonGenerator* createGenerator(::java::io::Writer* writer);
    static ::javax::json::stream::JsonGenerator* createGenerator(::java::io::OutputStream* out);
    static ::javax::json::stream::JsonGeneratorFactory* createGeneratorFactory(::java::util::Map* config);
    static JsonObjectBuilder* createObjectBuilder();
    static ::javax::json::stream::JsonParser* createParser(::java::io::Reader* reader);
    static ::javax::json::stream::JsonParser* createParser(::java::io::InputStream* in);
    static ::javax::json::stream::JsonParserFactory* createParserFactory(::java::util::Map* config);
    static JsonReader* createReader(::java::io::Reader* reader);
    static JsonReader* createReader(::java::io::InputStream* in);
    static JsonReaderFactory* createReaderFactory(::java::util::Map* config);
    static JsonWriter* createWriter(::java::io::Writer* writer);
    static JsonWriter* createWriter(::java::io::OutputStream* out);
    static JsonWriterFactory* createWriterFactory(::java::util::Map* config);

    // Generated
    Json();
protected:
    Json(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Hashtable.hpp>

struct default_init_tag;

class java::util::Properties
    : public Hashtable
{

public:
    typedef Hashtable super;

public: /* protected */
    Properties* defaults {  };

private:
    static ::char16_tArray* hexDigit_;
    static constexpr int64_t serialVersionUID { int64_t(4112578634029874840LL) };

protected:
    void ctor();
    void ctor(Properties* defaults);
    /*void enumerate(Hashtable* h); (private) */
    /*void enumerateStringProperties(Hashtable* h); (private) */

public:
    virtual ::java::lang::String* getProperty(::java::lang::String* key);
    virtual ::java::lang::String* getProperty(::java::lang::String* key, ::java::lang::String* defaultValue);
    virtual void list(::java::io::PrintStream* out);
    virtual void list(::java::io::PrintWriter* out);
    virtual void load(::java::io::Reader* reader);
    virtual void load(::java::io::InputStream* inStream);
    /*void load0(Properties_LineReader* lr); (private) */
    /*::java::lang::String* loadConvert(::char16_tArray* in, int32_t off, int32_t len, ::char16_tArray* convtBuf); (private) */
    virtual void loadFromXML(::java::io::InputStream* in);
    virtual Enumeration* propertyNames();
    virtual void save(::java::io::OutputStream* out, ::java::lang::String* comments);
    /*::java::lang::String* saveConvert(::java::lang::String* theString, bool escapeSpace, bool escapeUnicode); (private) */
    virtual ::java::lang::Object* setProperty(::java::lang::String* key, ::java::lang::String* value);
    virtual void store(::java::io::Writer* writer, ::java::lang::String* comments);
    virtual void store(::java::io::OutputStream* out, ::java::lang::String* comments);
    /*void store0(::java::io::BufferedWriter* bw, ::java::lang::String* comments, bool escUnicode); (private) */
    virtual void storeToXML(::java::io::OutputStream* os, ::java::lang::String* comment);
    virtual void storeToXML(::java::io::OutputStream* os, ::java::lang::String* comment, ::java::lang::String* encoding);
    virtual Set* stringPropertyNames();
    /*static char16_t toHex(int32_t nibble); (private) */
    /*static void writeComments(::java::io::BufferedWriter* bw, ::java::lang::String* comments); (private) */

    // Generated
    Properties();
    Properties(Properties* defaults);
protected:
    Properties(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static ::char16_tArray*& hexDigit();
    virtual ::java::lang::Class* getClass0();
};

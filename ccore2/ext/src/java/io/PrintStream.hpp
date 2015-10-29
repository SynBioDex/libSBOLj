// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/charset/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/FilterOutputStream.hpp>
#include <java/lang/Appendable.hpp>
#include <java/io/Closeable.hpp>

struct default_init_tag;

class java::io::PrintStream
    : public FilterOutputStream
    , public virtual ::java::lang::Appendable
    , public virtual Closeable
{

public:
    typedef FilterOutputStream super;

private:
    bool autoFlush {  };
    OutputStreamWriter* charOut {  };
    bool closing {  };
    ::java::util::Formatter* formatter {  };
    BufferedWriter* textOut {  };
    bool trouble {  };

protected:
    void ctor(OutputStream* out);
    void ctor(::java::lang::String* fileName);
    void ctor(File* file);
    /*void ctor(bool autoFlush, OutputStream* out); (private) */
    void ctor(OutputStream* out, bool autoFlush);
    void ctor(::java::lang::String* fileName, ::java::lang::String* csn);
    void ctor(File* file, ::java::lang::String* csn);
    /*void ctor(bool autoFlush, OutputStream* out, ::java::nio::charset::Charset* charset); (private) */
    /*void ctor(bool autoFlush, ::java::nio::charset::Charset* charset, OutputStream* out); (private) */
    void ctor(OutputStream* out, bool autoFlush, ::java::lang::String* encoding);

public:
    PrintStream* append(::java::lang::CharSequence* csq) override;
    PrintStream* append(char16_t c) override;
    PrintStream* append(::java::lang::CharSequence* csq, int32_t start, int32_t end) override;
    virtual bool checkError();

public: /* protected */
    virtual void clearError();

public:
    void close() override;
    /*void ensureOpen(); (private) */
    void flush() override;
    virtual PrintStream* format(::java::lang::String* format, ::java::lang::ObjectArray* args);
    virtual PrintStream* format(::java::util::Locale* l, ::java::lang::String* format, ::java::lang::ObjectArray* args);
    /*void newLine(); (private) */
    virtual void print(bool b);
    virtual void print(char16_t c);
    virtual void print(int32_t i);
    virtual void print(int64_t l);
    virtual void print(float f);
    virtual void print(double d);
    virtual void print(::char16_tArray* s);
    virtual void print(::java::lang::String* s);
    virtual void print(::java::lang::Object* obj);
    virtual PrintStream* printf(::java::lang::String* format, ::java::lang::ObjectArray* args);
    virtual PrintStream* printf(::java::util::Locale* l, ::java::lang::String* format, ::java::lang::ObjectArray* args);
    virtual void println();
    virtual void println(bool x);
    virtual void println(char16_t x);
    virtual void println(int32_t x);
    virtual void println(int64_t x);
    virtual void println(float x);
    virtual void println(double x);
    virtual void println(::char16_tArray* x);
    virtual void println(::java::lang::String* x);
    virtual void println(::java::lang::Object* x);
    /*static ::java::lang::Object* requireNonNull(::java::lang::Object* obj, ::java::lang::String* message); (private) */

public: /* protected */
    virtual void setError();
    /*static ::java::nio::charset::Charset* toCharset(::java::lang::String* csn); (private) */

public:
    void write(int32_t b) override;
    /*void write(::char16_tArray* buf); (private) */
    /*void write(::java::lang::String* s); (private) */
    void write(::int8_tArray* buf, int32_t off, int32_t len) override;

    // Generated
    PrintStream(OutputStream* out);
    PrintStream(::java::lang::String* fileName);
    PrintStream(File* file);
    PrintStream(OutputStream* out, bool autoFlush);
    PrintStream(::java::lang::String* fileName, ::java::lang::String* csn);
    PrintStream(File* file, ::java::lang::String* csn);
    PrintStream(OutputStream* out, bool autoFlush, ::java::lang::String* encoding);
protected:
    PrintStream(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    void write(::int8_tArray* b);

private:
    virtual ::java::lang::Class* getClass0();
};

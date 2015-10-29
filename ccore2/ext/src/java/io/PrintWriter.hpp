// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/charset/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/Writer.hpp>

struct default_init_tag;

class java::io::PrintWriter
    : public Writer
{

public:
    typedef Writer super;

private:
    bool autoFlush {  };
    ::java::util::Formatter* formatter {  };
    ::java::lang::String* lineSeparator {  };

public: /* protected */
    Writer* out {  };

private:
    PrintStream* psOut {  };
    bool trouble {  };

protected:
    void ctor(Writer* out);
    void ctor(OutputStream* out);
    void ctor(::java::lang::String* fileName);
    void ctor(File* file);
    void ctor(Writer* out, bool autoFlush);
    void ctor(OutputStream* out, bool autoFlush);
    /*void ctor(::java::nio::charset::Charset* charset, File* file); (private) */
    void ctor(::java::lang::String* fileName, ::java::lang::String* csn);
    void ctor(File* file, ::java::lang::String* csn);

public:
    PrintWriter* append(::java::lang::CharSequence* csq) override;
    PrintWriter* append(char16_t c) override;
    PrintWriter* append(::java::lang::CharSequence* csq, int32_t start, int32_t end) override;
    virtual bool checkError();

public: /* protected */
    virtual void clearError();

public:
    void close() override;
    /*void ensureOpen(); (private) */
    void flush() override;
    virtual PrintWriter* format(::java::lang::String* format, ::java::lang::ObjectArray* args);
    virtual PrintWriter* format(::java::util::Locale* l, ::java::lang::String* format, ::java::lang::ObjectArray* args);
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
    virtual PrintWriter* printf(::java::lang::String* format, ::java::lang::ObjectArray* args);
    virtual PrintWriter* printf(::java::util::Locale* l, ::java::lang::String* format, ::java::lang::ObjectArray* args);
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

public: /* protected */
    virtual void setError();
    /*static ::java::nio::charset::Charset* toCharset(::java::lang::String* csn); (private) */

public:
    void write(int32_t c) override;
    void write(::char16_tArray* buf) override;
    void write(::java::lang::String* s) override;
    void write(::char16_tArray* buf, int32_t off, int32_t len) override;
    void write(::java::lang::String* s, int32_t off, int32_t len) override;

    // Generated
    PrintWriter(Writer* out);
    PrintWriter(OutputStream* out);
    PrintWriter(::java::lang::String* fileName);
    PrintWriter(File* file);
    PrintWriter(Writer* out, bool autoFlush);
    PrintWriter(OutputStream* out, bool autoFlush);
    PrintWriter(::java::lang::String* fileName, ::java::lang::String* csn);
    PrintWriter(File* file, ::java::lang::String* csn);
protected:
    PrintWriter(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/Readable.hpp>
#include <java/io/Closeable.hpp>

struct default_init_tag;

class java::io::Reader
    : public virtual ::java::lang::Object
    , public virtual ::java::lang::Readable
    , public virtual Closeable
{

public:
    typedef ::java::lang::Object super;

public: /* protected */
    ::java::lang::Object* lock {  };

private:
    static constexpr int32_t maxSkipBufferSize { int32_t(8192) };
    ::char16_tArray* skipBuffer {  };

protected:
    void ctor();
    void ctor(::java::lang::Object* lock);
    /*void close(); (already declared) */

public:
    virtual void mark(int32_t readAheadLimit);
    virtual bool markSupported();
    virtual int32_t read();
    int32_t read(::java::nio::CharBuffer* target) override;
    virtual int32_t read(::char16_tArray* cbuf);
    virtual int32_t read(::char16_tArray* cbuf, int32_t off, int32_t len) = 0;
    virtual bool ready();
    virtual void reset();
    virtual int64_t skip(int64_t n);

    // Generated

public: /* protected */
    Reader();
    Reader(::java::lang::Object* lock);
protected:
    Reader(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

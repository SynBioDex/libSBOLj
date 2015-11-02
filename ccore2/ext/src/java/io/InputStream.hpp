// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/io/Closeable.hpp>

struct default_init_tag;

class java::io::InputStream
    : public virtual ::java::lang::Object
    , public virtual Closeable
{

public:
    typedef ::java::lang::Object super;

private:
    static constexpr int32_t MAX_SKIP_BUFFER_SIZE { int32_t(2048) };

protected:
    void ctor();

public:
    virtual int32_t available();
    void close() override;
    virtual void mark(int32_t readlimit);
    virtual bool markSupported();
    virtual int32_t read() = 0;
    virtual int32_t read(::int8_tArray* b);
    virtual int32_t read(::int8_tArray* b, int32_t off, int32_t len);
    virtual void reset();
    virtual int64_t skip(int64_t n);

    // Generated
    InputStream();
protected:
    InputStream(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

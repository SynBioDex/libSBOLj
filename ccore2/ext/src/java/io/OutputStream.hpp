// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/io/Closeable.hpp>
#include <java/io/Flushable.hpp>

struct default_init_tag;

class java::io::OutputStream
    : public virtual ::java::lang::Object
    , public virtual Closeable
    , public virtual Flushable
{

public:
    typedef ::java::lang::Object super;

protected:
    void ctor();

public:
    void close() override;
    void flush() override;
    virtual void write(int32_t b) = 0;
    virtual void write(::int8_tArray* b);
    virtual void write(::int8_tArray* b, int32_t off, int32_t len);

    // Generated
    OutputStream();
protected:
    OutputStream(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/Appendable.hpp>
#include <java/io/Closeable.hpp>
#include <java/io/Flushable.hpp>

struct default_init_tag;

class java::io::Writer
    : public virtual ::java::lang::Object
    , public virtual ::java::lang::Appendable
    , public virtual Closeable
    , public virtual Flushable
{

public:
    typedef ::java::lang::Object super;

public: /* protected */
    ::java::lang::Object* lock {  };

private:
    ::char16_tArray* writeBuffer {  };
    static constexpr int32_t writeBufferSize { int32_t(1024) };

protected:
    void ctor();
    void ctor(::java::lang::Object* lock);

public:
    Writer* append(::java::lang::CharSequence* csq) override;
    Writer* append(char16_t c) override;
    Writer* append(::java::lang::CharSequence* csq, int32_t start, int32_t end) override;
    /*void close(); (already declared) */
    /*void flush(); (already declared) */
    virtual void write(int32_t c);
    virtual void write(::char16_tArray* cbuf);
    virtual void write(::java::lang::String* str);
    virtual void write(::char16_tArray* cbuf, int32_t off, int32_t len) = 0;
    virtual void write(::java::lang::String* str, int32_t off, int32_t len);

    // Generated

public: /* protected */
    Writer();
    Writer(::java::lang::Object* lock);
protected:
    Writer(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

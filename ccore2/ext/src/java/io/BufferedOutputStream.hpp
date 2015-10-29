// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/FilterOutputStream.hpp>

struct default_init_tag;

class java::io::BufferedOutputStream
    : public FilterOutputStream
{

public:
    typedef FilterOutputStream super;

public: /* protected */
    ::int8_tArray* buf {  };
    int32_t count {  };

protected:
    void ctor(OutputStream* out);
    void ctor(OutputStream* out, int32_t size);

public:
    void flush() override;
    /*void flushBuffer(); (private) */
    void write(int32_t b) override;
    void write(::int8_tArray* b, int32_t off, int32_t len) override;

    // Generated
    BufferedOutputStream(OutputStream* out);
    BufferedOutputStream(OutputStream* out, int32_t size);
protected:
    BufferedOutputStream(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    void write(::int8_tArray* b);

private:
    virtual ::java::lang::Class* getClass0();
};

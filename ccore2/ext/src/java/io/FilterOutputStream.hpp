// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/OutputStream.hpp>

struct default_init_tag;

class java::io::FilterOutputStream
    : public OutputStream
{

public:
    typedef OutputStream super;

public: /* protected */
    OutputStream* out {  };

protected:
    void ctor(OutputStream* out);

public:
    void close() override;
    void flush() override;
    void write(int32_t b) override;
    void write(::int8_tArray* b) override;
    void write(::int8_tArray* b, int32_t off, int32_t len) override;

    // Generated
    FilterOutputStream(OutputStream* out);
protected:
    FilterOutputStream(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

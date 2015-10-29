// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/InputStream.hpp>

struct default_init_tag;

class java::io::FilterInputStream
    : public InputStream
{

public:
    typedef InputStream super;

public: /* protected */
    std::atomic< InputStream* > in {  };

protected:
    void ctor(InputStream* in);

public:
    int32_t available() override;
    void close() override;
    void mark(int32_t readlimit) override;
    bool markSupported() override;
    int32_t read() override;
    int32_t read(::int8_tArray* b) override;
    int32_t read(::int8_tArray* b, int32_t off, int32_t len) override;
    void reset() override;
    int64_t skip(int64_t n) override;

    // Generated

public: /* protected */
    FilterInputStream(InputStream* in);
protected:
    FilterInputStream(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

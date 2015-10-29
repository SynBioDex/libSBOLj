// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/concurrent/atomic/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/FilterInputStream.hpp>

struct default_init_tag;

class java::io::BufferedInputStream
    : public FilterInputStream
{

public:
    typedef FilterInputStream super;

public: /* protected */
    std::atomic< ::int8_tArray* > buf {  };

private:
    static ::java::util::concurrent::atomic::AtomicReferenceFieldUpdater* bufUpdater_;

public: /* protected */
    int32_t count {  };

private:
    static int32_t defaultBufferSize_;

public: /* protected */
    int32_t marklimit {  };
    int32_t markpos {  };
    int32_t pos {  };

protected:
    void ctor(InputStream* in);
    void ctor(InputStream* in, int32_t size);

public:
    int32_t available() override;
    void close() override;
    /*void fill(); (private) */
    /*::int8_tArray* getBufIfOpen(); (private) */
    /*InputStream* getInIfOpen(); (private) */
    void mark(int32_t readlimit) override;
    bool markSupported() override;
    int32_t read() override;
    int32_t read(::int8_tArray* b, int32_t off, int32_t len) override;
    /*int32_t read1(::int8_tArray* b, int32_t off, int32_t len); (private) */
    void reset() override;
    int64_t skip(int64_t n) override;

    // Generated
    BufferedInputStream(InputStream* in);
    BufferedInputStream(InputStream* in, int32_t size);
protected:
    BufferedInputStream(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    int32_t read(::int8_tArray* b);

private:
    static ::java::util::concurrent::atomic::AtomicReferenceFieldUpdater*& bufUpdater();
    static int32_t& defaultBufferSize();
    virtual ::java::lang::Class* getClass0();
};

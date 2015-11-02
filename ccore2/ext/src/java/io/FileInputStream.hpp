// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/channels/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/InputStream.hpp>

struct default_init_tag;

class java::io::FileInputStream
    : public InputStream
{

public:
    typedef InputStream super;

private:
    ::java::nio::channels::FileChannel* channel {  };
    ::java::lang::Object* closeLock {  };
    std::atomic< bool > closed {  };
    FileDescriptor* fd {  };
    ::java::lang::String* path {  };
    static ::java::lang::ThreadLocal* runningFinalize_;

protected:
    void ctor(::java::lang::String* name);
    void ctor(File* file);
    void ctor(FileDescriptor* fdObj);

public:
    int32_t available() override;
    void close() override;
    /*void close0(); (private) */

public: /* protected */
    void finalize() override;

public:
    virtual ::java::nio::channels::FileChannel* getChannel();
    FileDescriptor* getFD();
    /*static void initIDs(); (private) */
    /*static bool isRunningFinalize(); (private) */
    /*void open(::java::lang::String* name); (private) */
    int32_t read() override;
    int32_t read(::int8_tArray* b) override;
    int32_t read(::int8_tArray* b, int32_t off, int32_t len) override;
    /*int32_t read0(); (private) */
    /*int32_t readBytes(::int8_tArray* b, int32_t off, int32_t len); (private) */
    int64_t skip(int64_t n) override;

    // Generated
    FileInputStream(::java::lang::String* name);
    FileInputStream(File* file);
    FileInputStream(FileDescriptor* fdObj);
protected:
    FileInputStream(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static ::java::lang::ThreadLocal*& runningFinalize();
    virtual ::java::lang::Class* getClass0();
};

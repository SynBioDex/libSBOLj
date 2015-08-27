// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/channels/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/OutputStream.hpp>

struct default_init_tag;

class java::io::FileOutputStream
    : public OutputStream
{

public:
    typedef OutputStream super;

private:
    bool append {  };
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
    void ctor(::java::lang::String* name, bool append);
    void ctor(File* file, bool append);

public:
    void close() override;
    /*void close0(); (private) */

public: /* protected */
    void finalize() override;

public:
    virtual ::java::nio::channels::FileChannel* getChannel();
    FileDescriptor* getFD();
    /*static void initIDs(); (private) */
    /*static bool isRunningFinalize(); (private) */
    /*void open(::java::lang::String* name, bool append); (private) */
    void write(int32_t b) override;
    void write(::int8_tArray* b) override;
    /*void write(int32_t b, bool append); (private) */
    void write(::int8_tArray* b, int32_t off, int32_t len) override;
    /*void writeBytes(::int8_tArray* b, int32_t off, int32_t len, bool append); (private) */

    // Generated
    FileOutputStream(::java::lang::String* name);
    FileOutputStream(File* file);
    FileOutputStream(FileDescriptor* fdObj);
    FileOutputStream(::java::lang::String* name, bool append);
    FileOutputStream(File* file, bool append);
protected:
    FileOutputStream(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static ::java::lang::ThreadLocal*& runningFinalize();
    virtual ::java::lang::Class* getClass0();
};

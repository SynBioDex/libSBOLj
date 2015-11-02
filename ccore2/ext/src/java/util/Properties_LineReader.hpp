// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::Properties_LineReader
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    ::int8_tArray* inByteBuf {  };
    ::char16_tArray* inCharBuf {  };
    int32_t inLimit {  };
    int32_t inOff {  };
    ::java::io::InputStream* inStream {  };
    ::char16_tArray* lineBuf {  };
    ::java::io::Reader* reader {  };
    Properties* this$0 {  };

protected:
    void ctor(::java::io::InputStream* inStream);
    void ctor(::java::io::Reader* reader);

public: /* package */
    virtual int32_t readLine();

    // Generated

public:
    Properties_LineReader(Properties *Properties_this, ::java::io::InputStream* inStream);
    Properties_LineReader(Properties *Properties_this, ::java::io::Reader* reader);
protected:
    Properties_LineReader(Properties *Properties_this, const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    Properties *Properties_this;

private:
    virtual ::java::lang::Class* getClass0();
};

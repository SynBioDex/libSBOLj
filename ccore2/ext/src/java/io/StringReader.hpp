// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/Reader.hpp>

struct default_init_tag;

class java::io::StringReader
    : public Reader
{

public:
    typedef Reader super;

private:
    int32_t length {  };
    int32_t mark_ {  };
    int32_t next {  };
    ::java::lang::String* str {  };

protected:
    void ctor(::java::lang::String* s);

public:
    void close() override;
    /*void ensureOpen(); (private) */
    void mark(int32_t readAheadLimit) override;
    bool markSupported() override;
    int32_t read() override;
    int32_t read(::char16_tArray* cbuf, int32_t off, int32_t len) override;
    bool ready() override;
    void reset() override;
    int64_t skip(int64_t ns) override;

    // Generated
    StringReader(::java::lang::String* s);
protected:
    StringReader(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    int32_t read(::java::nio::CharBuffer* target);
    virtual int32_t read(::char16_tArray* cbuf);

private:
    virtual ::java::lang::Class* getClass0();
};

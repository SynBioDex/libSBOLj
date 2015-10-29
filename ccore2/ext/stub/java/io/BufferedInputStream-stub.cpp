// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/BufferedInputStream.hpp>

extern void unimplemented_(const char16_t* name);
java::io::BufferedInputStream::BufferedInputStream(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::BufferedInputStream::BufferedInputStream(InputStream* in)
    : BufferedInputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(in);
}

java::io::BufferedInputStream::BufferedInputStream(InputStream* in, int32_t size)
    : BufferedInputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(in, size);
}

java::util::concurrent::atomic::AtomicReferenceFieldUpdater*& java::io::BufferedInputStream::bufUpdater()
{
    clinit();
    return bufUpdater_;
}
java::util::concurrent::atomic::AtomicReferenceFieldUpdater* java::io::BufferedInputStream::bufUpdater_;
int32_t& java::io::BufferedInputStream::defaultBufferSize()
{
    clinit();
    return defaultBufferSize_;
}
int32_t java::io::BufferedInputStream::defaultBufferSize_;

void ::java::io::BufferedInputStream::ctor(InputStream* in)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::BufferedInputStream::ctor(InputStream* in)");
}

void ::java::io::BufferedInputStream::ctor(InputStream* in, int32_t size)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::BufferedInputStream::ctor(InputStream* in, int32_t size)");
}

int32_t java::io::BufferedInputStream::available()
{ /* stub */
    unimplemented_(u"int32_t java::io::BufferedInputStream::available()");
    return 0;
}

void java::io::BufferedInputStream::close()
{ /* stub */
    unimplemented_(u"void java::io::BufferedInputStream::close()");
}

/* private: void java::io::BufferedInputStream::fill() */
/* private: int8_tArray* java::io::BufferedInputStream::getBufIfOpen() */
/* private: java::io::InputStream* java::io::BufferedInputStream::getInIfOpen() */
void java::io::BufferedInputStream::mark(int32_t readlimit)
{ /* stub */
    unimplemented_(u"void java::io::BufferedInputStream::mark(int32_t readlimit)");
}

bool java::io::BufferedInputStream::markSupported()
{ /* stub */
    unimplemented_(u"bool java::io::BufferedInputStream::markSupported()");
    return 0;
}

int32_t java::io::BufferedInputStream::read()
{ /* stub */
    unimplemented_(u"int32_t java::io::BufferedInputStream::read()");
    return 0;
}

int32_t java::io::BufferedInputStream::read(::int8_tArray* b, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"int32_t java::io::BufferedInputStream::read(::int8_tArray* b, int32_t off, int32_t len)");
    return 0;
}

/* private: int32_t java::io::BufferedInputStream::read1(::int8_tArray* b, int32_t off, int32_t len) */
void java::io::BufferedInputStream::reset()
{ /* stub */
    unimplemented_(u"void java::io::BufferedInputStream::reset()");
}

int64_t java::io::BufferedInputStream::skip(int64_t n)
{ /* stub */
    unimplemented_(u"int64_t java::io::BufferedInputStream::skip(int64_t n)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::BufferedInputStream::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.BufferedInputStream", 27);
    return c;
}

int32_t java::io::BufferedInputStream::read(::int8_tArray* b)
{
    return super::read(b);
}

java::lang::Class* java::io::BufferedInputStream::getClass0()
{
    return class_();
}


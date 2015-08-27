// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/FilterInputStream.hpp>

extern void unimplemented_(const char16_t* name);
java::io::FilterInputStream::FilterInputStream(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::FilterInputStream::FilterInputStream(InputStream* in)
    : FilterInputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(in);
}


void ::java::io::FilterInputStream::ctor(InputStream* in)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FilterInputStream::ctor(InputStream* in)");
}

int32_t java::io::FilterInputStream::available()
{ /* stub */
    unimplemented_(u"int32_t java::io::FilterInputStream::available()");
    return 0;
}

void java::io::FilterInputStream::close()
{ /* stub */
    unimplemented_(u"void java::io::FilterInputStream::close()");
}

void java::io::FilterInputStream::mark(int32_t readlimit)
{ /* stub */
    unimplemented_(u"void java::io::FilterInputStream::mark(int32_t readlimit)");
}

bool java::io::FilterInputStream::markSupported()
{ /* stub */
    unimplemented_(u"bool java::io::FilterInputStream::markSupported()");
    return 0;
}

int32_t java::io::FilterInputStream::read()
{ /* stub */
    unimplemented_(u"int32_t java::io::FilterInputStream::read()");
    return 0;
}

int32_t java::io::FilterInputStream::read(::int8_tArray* b)
{ /* stub */
    unimplemented_(u"int32_t java::io::FilterInputStream::read(::int8_tArray* b)");
    return 0;
}

int32_t java::io::FilterInputStream::read(::int8_tArray* b, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"int32_t java::io::FilterInputStream::read(::int8_tArray* b, int32_t off, int32_t len)");
    return 0;
}

void java::io::FilterInputStream::reset()
{ /* stub */
    unimplemented_(u"void java::io::FilterInputStream::reset()");
}

int64_t java::io::FilterInputStream::skip(int64_t n)
{ /* stub */
    unimplemented_(u"int64_t java::io::FilterInputStream::skip(int64_t n)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::FilterInputStream::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.FilterInputStream", 25);
    return c;
}

java::lang::Class* java::io::FilterInputStream::getClass0()
{
    return class_();
}


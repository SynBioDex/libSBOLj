// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/BufferedOutputStream.hpp>

extern void unimplemented_(const char16_t* name);
java::io::BufferedOutputStream::BufferedOutputStream(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::BufferedOutputStream::BufferedOutputStream(OutputStream* out)
    : BufferedOutputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(out);
}

java::io::BufferedOutputStream::BufferedOutputStream(OutputStream* out, int32_t size)
    : BufferedOutputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(out, size);
}


void ::java::io::BufferedOutputStream::ctor(OutputStream* out)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::BufferedOutputStream::ctor(OutputStream* out)");
}

void ::java::io::BufferedOutputStream::ctor(OutputStream* out, int32_t size)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::BufferedOutputStream::ctor(OutputStream* out, int32_t size)");
}

void java::io::BufferedOutputStream::flush()
{ /* stub */
    unimplemented_(u"void java::io::BufferedOutputStream::flush()");
}

/* private: void java::io::BufferedOutputStream::flushBuffer() */
void java::io::BufferedOutputStream::write(int32_t b)
{ /* stub */
    unimplemented_(u"void java::io::BufferedOutputStream::write(int32_t b)");
}

void java::io::BufferedOutputStream::write(::int8_tArray* b, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"void java::io::BufferedOutputStream::write(::int8_tArray* b, int32_t off, int32_t len)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::BufferedOutputStream::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.BufferedOutputStream", 28);
    return c;
}

void java::io::BufferedOutputStream::write(::int8_tArray* b)
{
    super::write(b);
}

java::lang::Class* java::io::BufferedOutputStream::getClass0()
{
    return class_();
}


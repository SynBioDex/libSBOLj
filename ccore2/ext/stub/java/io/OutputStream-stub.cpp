// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/OutputStream.hpp>

extern void unimplemented_(const char16_t* name);
java::io::OutputStream::OutputStream(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::OutputStream::OutputStream()
    : OutputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::java::io::OutputStream::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::OutputStream::ctor()");
}

void java::io::OutputStream::close()
{ /* stub */
    unimplemented_(u"void java::io::OutputStream::close()");
}

void java::io::OutputStream::flush()
{ /* stub */
    unimplemented_(u"void java::io::OutputStream::flush()");
}

void java::io::OutputStream::write(::int8_tArray* b)
{ /* stub */
    unimplemented_(u"void java::io::OutputStream::write(::int8_tArray* b)");
}

void java::io::OutputStream::write(::int8_tArray* b, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"void java::io::OutputStream::write(::int8_tArray* b, int32_t off, int32_t len)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::OutputStream::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.OutputStream", 20);
    return c;
}

java::lang::Class* java::io::OutputStream::getClass0()
{
    return class_();
}


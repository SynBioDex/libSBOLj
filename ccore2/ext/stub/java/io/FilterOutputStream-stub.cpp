// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/FilterOutputStream.hpp>

extern void unimplemented_(const char16_t* name);
java::io::FilterOutputStream::FilterOutputStream(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::FilterOutputStream::FilterOutputStream(OutputStream* out)
    : FilterOutputStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(out);
}


void ::java::io::FilterOutputStream::ctor(OutputStream* out)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::FilterOutputStream::ctor(OutputStream* out)");
}

void java::io::FilterOutputStream::close()
{ /* stub */
    unimplemented_(u"void java::io::FilterOutputStream::close()");
}

void java::io::FilterOutputStream::flush()
{ /* stub */
    unimplemented_(u"void java::io::FilterOutputStream::flush()");
}

void java::io::FilterOutputStream::write(int32_t b)
{ /* stub */
    unimplemented_(u"void java::io::FilterOutputStream::write(int32_t b)");
}

void java::io::FilterOutputStream::write(::int8_tArray* b)
{ /* stub */
    unimplemented_(u"void java::io::FilterOutputStream::write(::int8_tArray* b)");
}

void java::io::FilterOutputStream::write(::int8_tArray* b, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"void java::io::FilterOutputStream::write(::int8_tArray* b, int32_t off, int32_t len)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::FilterOutputStream::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.FilterOutputStream", 26);
    return c;
}

java::lang::Class* java::io::FilterOutputStream::getClass0()
{
    return class_();
}


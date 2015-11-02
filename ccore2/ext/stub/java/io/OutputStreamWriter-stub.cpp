// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/OutputStreamWriter.hpp>

extern void unimplemented_(const char16_t* name);
java::io::OutputStreamWriter::OutputStreamWriter(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::OutputStreamWriter::OutputStreamWriter(OutputStream* out)
    : OutputStreamWriter(*static_cast< ::default_init_tag* >(0))
{
    ctor(out);
}

java::io::OutputStreamWriter::OutputStreamWriter(OutputStream* out, ::java::lang::String* charsetName)
    : OutputStreamWriter(*static_cast< ::default_init_tag* >(0))
{
    ctor(out, charsetName);
}

java::io::OutputStreamWriter::OutputStreamWriter(OutputStream* out, ::java::nio::charset::Charset* cs)
    : OutputStreamWriter(*static_cast< ::default_init_tag* >(0))
{
    ctor(out, cs);
}

java::io::OutputStreamWriter::OutputStreamWriter(OutputStream* out, ::java::nio::charset::CharsetEncoder* enc)
    : OutputStreamWriter(*static_cast< ::default_init_tag* >(0))
{
    ctor(out, enc);
}


void ::java::io::OutputStreamWriter::ctor(OutputStream* out)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::OutputStreamWriter::ctor(OutputStream* out)");
}

void ::java::io::OutputStreamWriter::ctor(OutputStream* out, ::java::lang::String* charsetName)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::OutputStreamWriter::ctor(OutputStream* out, ::java::lang::String* charsetName)");
}

void ::java::io::OutputStreamWriter::ctor(OutputStream* out, ::java::nio::charset::Charset* cs)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::OutputStreamWriter::ctor(OutputStream* out, ::java::nio::charset::Charset* cs)");
}

void ::java::io::OutputStreamWriter::ctor(OutputStream* out, ::java::nio::charset::CharsetEncoder* enc)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::OutputStreamWriter::ctor(OutputStream* out, ::java::nio::charset::CharsetEncoder* enc)");
}

void java::io::OutputStreamWriter::close()
{ /* stub */
    unimplemented_(u"void java::io::OutputStreamWriter::close()");
}

void java::io::OutputStreamWriter::flush()
{ /* stub */
    unimplemented_(u"void java::io::OutputStreamWriter::flush()");
}

void java::io::OutputStreamWriter::flushBuffer()
{ /* stub */
    unimplemented_(u"void java::io::OutputStreamWriter::flushBuffer()");
}

java::lang::String* java::io::OutputStreamWriter::getEncoding()
{ /* stub */
    unimplemented_(u"java::lang::String* java::io::OutputStreamWriter::getEncoding()");
    return 0;
}

void java::io::OutputStreamWriter::write(int32_t c)
{ /* stub */
    unimplemented_(u"void java::io::OutputStreamWriter::write(int32_t c)");
}

void java::io::OutputStreamWriter::write(::char16_tArray* cbuf, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"void java::io::OutputStreamWriter::write(::char16_tArray* cbuf, int32_t off, int32_t len)");
}

void java::io::OutputStreamWriter::write(::java::lang::String* str, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"void java::io::OutputStreamWriter::write(::java::lang::String* str, int32_t off, int32_t len)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::OutputStreamWriter::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.OutputStreamWriter", 26);
    return c;
}

void java::io::OutputStreamWriter::write(::char16_tArray* cbuf)
{
    super::write(cbuf);
}

void java::io::OutputStreamWriter::write(::java::lang::String* str)
{
    super::write(str);
}

java::lang::Class* java::io::OutputStreamWriter::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/StringReader.hpp>

extern void unimplemented_(const char16_t* name);
java::io::StringReader::StringReader(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::StringReader::StringReader(::java::lang::String* s)
    : StringReader(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}


void ::java::io::StringReader::ctor(::java::lang::String* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::StringReader::ctor(::java::lang::String* s)");
}

void java::io::StringReader::close()
{ /* stub */
    unimplemented_(u"void java::io::StringReader::close()");
}

/* private: void java::io::StringReader::ensureOpen() */
void java::io::StringReader::mark(int32_t readAheadLimit)
{ /* stub */
    unimplemented_(u"void java::io::StringReader::mark(int32_t readAheadLimit)");
}

bool java::io::StringReader::markSupported()
{ /* stub */
    unimplemented_(u"bool java::io::StringReader::markSupported()");
    return 0;
}

int32_t java::io::StringReader::read()
{ /* stub */
    unimplemented_(u"int32_t java::io::StringReader::read()");
    return 0;
}

int32_t java::io::StringReader::read(::char16_tArray* cbuf, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"int32_t java::io::StringReader::read(::char16_tArray* cbuf, int32_t off, int32_t len)");
    return 0;
}

bool java::io::StringReader::ready()
{ /* stub */
    unimplemented_(u"bool java::io::StringReader::ready()");
    return 0;
}

void java::io::StringReader::reset()
{ /* stub */
    unimplemented_(u"void java::io::StringReader::reset()");
}

int64_t java::io::StringReader::skip(int64_t ns)
{ /* stub */
    unimplemented_(u"int64_t java::io::StringReader::skip(int64_t ns)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::StringReader::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.StringReader", 20);
    return c;
}

int32_t java::io::StringReader::read(::java::nio::CharBuffer* target)
{
    return super::read(target);
}

int32_t java::io::StringReader::read(::char16_tArray* cbuf)
{
    return super::read(cbuf);
}

java::lang::Class* java::io::StringReader::getClass0()
{
    return class_();
}


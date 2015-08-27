// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/Writer.hpp>

extern void unimplemented_(const char16_t* name);
java::io::Writer::Writer(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::Writer::Writer()
    : Writer(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::io::Writer::Writer(::java::lang::Object* lock)
    : Writer(*static_cast< ::default_init_tag* >(0))
{
    ctor(lock);
}


void ::java::io::Writer::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::Writer::ctor()");
}

void ::java::io::Writer::ctor(::java::lang::Object* lock)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::Writer::ctor(::java::lang::Object* lock)");
}

java::io::Writer* java::io::Writer::append(::java::lang::CharSequence* csq)
{ /* stub */
    unimplemented_(u"java::io::Writer* java::io::Writer::append(::java::lang::CharSequence* csq)");
    return 0;
}

java::io::Writer* java::io::Writer::append(char16_t c)
{ /* stub */
    unimplemented_(u"java::io::Writer* java::io::Writer::append(char16_t c)");
    return 0;
}

java::io::Writer* java::io::Writer::append(::java::lang::CharSequence* csq, int32_t start, int32_t end)
{ /* stub */
    unimplemented_(u"java::io::Writer* java::io::Writer::append(::java::lang::CharSequence* csq, int32_t start, int32_t end)");
    return 0;
}

void java::io::Writer::write(int32_t c)
{ /* stub */
    unimplemented_(u"void java::io::Writer::write(int32_t c)");
}

void java::io::Writer::write(::char16_tArray* cbuf)
{ /* stub */
    unimplemented_(u"void java::io::Writer::write(::char16_tArray* cbuf)");
}

void java::io::Writer::write(::java::lang::String* str)
{ /* stub */
    unimplemented_(u"void java::io::Writer::write(::java::lang::String* str)");
}

void java::io::Writer::write(::java::lang::String* str, int32_t off, int32_t len)
{ /* stub */
    unimplemented_(u"void java::io::Writer::write(::java::lang::String* str, int32_t off, int32_t len)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::Writer::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.Writer", 14);
    return c;
}

java::lang::Class* java::io::Writer::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/io/IOException.hpp>

extern void unimplemented_(const char16_t* name);
java::io::IOException::IOException(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::io::IOException::IOException()
    : IOException(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::io::IOException::IOException(::java::lang::String* message)
    : IOException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message);
}

java::io::IOException::IOException(::java::lang::Throwable* cause)
    : IOException(*static_cast< ::default_init_tag* >(0))
{
    ctor(cause);
}

java::io::IOException::IOException(::java::lang::String* message, ::java::lang::Throwable* cause)
    : IOException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause);
}

constexpr int64_t java::io::IOException::serialVersionUID;

void ::java::io::IOException::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::IOException::ctor()");
}

void ::java::io::IOException::ctor(::java::lang::String* message)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::IOException::ctor(::java::lang::String* message)");
}

void ::java::io::IOException::ctor(::java::lang::Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::IOException::ctor(::java::lang::Throwable* cause)");
}

void ::java::io::IOException::ctor(::java::lang::String* message, ::java::lang::Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::io::IOException::ctor(::java::lang::String* message, ::java::lang::Throwable* cause)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::io::IOException::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.io.IOException", 19);
    return c;
}

java::lang::Class* java::io::IOException::getClass0()
{
    return class_();
}


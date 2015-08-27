// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/IllegalArgumentException.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::IllegalArgumentException::IllegalArgumentException(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::IllegalArgumentException::IllegalArgumentException()
    : IllegalArgumentException(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::IllegalArgumentException::IllegalArgumentException(String* s)
    : IllegalArgumentException(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}

java::lang::IllegalArgumentException::IllegalArgumentException(Throwable* cause)
    : IllegalArgumentException(*static_cast< ::default_init_tag* >(0))
{
    ctor(cause);
}

java::lang::IllegalArgumentException::IllegalArgumentException(String* message, Throwable* cause)
    : IllegalArgumentException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause);
}

constexpr int64_t java::lang::IllegalArgumentException::serialVersionUID;

void ::java::lang::IllegalArgumentException::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::IllegalArgumentException::ctor()");
}

void ::java::lang::IllegalArgumentException::ctor(String* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::IllegalArgumentException::ctor(String* s)");
}

void ::java::lang::IllegalArgumentException::ctor(Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::IllegalArgumentException::ctor(Throwable* cause)");
}

void ::java::lang::IllegalArgumentException::ctor(String* message, Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::IllegalArgumentException::ctor(String* message, Throwable* cause)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::IllegalArgumentException::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.IllegalArgumentException", 34);
    return c;
}

java::lang::Class* java::lang::IllegalArgumentException::getClass0()
{
    return class_();
}


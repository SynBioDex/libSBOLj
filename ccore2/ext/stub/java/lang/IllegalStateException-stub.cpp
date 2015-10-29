// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/IllegalStateException.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::IllegalStateException::IllegalStateException(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::IllegalStateException::IllegalStateException()
    : IllegalStateException(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::IllegalStateException::IllegalStateException(String* s)
    : IllegalStateException(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}

java::lang::IllegalStateException::IllegalStateException(Throwable* cause)
    : IllegalStateException(*static_cast< ::default_init_tag* >(0))
{
    ctor(cause);
}

java::lang::IllegalStateException::IllegalStateException(String* message, Throwable* cause)
    : IllegalStateException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause);
}

constexpr int64_t java::lang::IllegalStateException::serialVersionUID;

void ::java::lang::IllegalStateException::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::IllegalStateException::ctor()");
}

void ::java::lang::IllegalStateException::ctor(String* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::IllegalStateException::ctor(String* s)");
}

void ::java::lang::IllegalStateException::ctor(Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::IllegalStateException::ctor(Throwable* cause)");
}

void ::java::lang::IllegalStateException::ctor(String* message, Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::IllegalStateException::ctor(String* message, Throwable* cause)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::IllegalStateException::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.IllegalStateException", 31);
    return c;
}

java::lang::Class* java::lang::IllegalStateException::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/RuntimeException.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::RuntimeException::RuntimeException(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::RuntimeException::RuntimeException()
    : RuntimeException(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::RuntimeException::RuntimeException(String* message)
    : RuntimeException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message);
}

java::lang::RuntimeException::RuntimeException(Throwable* cause)
    : RuntimeException(*static_cast< ::default_init_tag* >(0))
{
    ctor(cause);
}

java::lang::RuntimeException::RuntimeException(String* message, Throwable* cause)
    : RuntimeException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause);
}

java::lang::RuntimeException::RuntimeException(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)
    : RuntimeException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause, enableSuppression, writableStackTrace);
}

constexpr int64_t java::lang::RuntimeException::serialVersionUID;

void ::java::lang::RuntimeException::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::RuntimeException::ctor()");
}

void ::java::lang::RuntimeException::ctor(String* message)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::RuntimeException::ctor(String* message)");
}

void ::java::lang::RuntimeException::ctor(Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::RuntimeException::ctor(Throwable* cause)");
}

void ::java::lang::RuntimeException::ctor(String* message, Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::RuntimeException::ctor(String* message, Throwable* cause)");
}

void ::java::lang::RuntimeException::ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::RuntimeException::ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::RuntimeException::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.RuntimeException", 26);
    return c;
}

java::lang::Class* java::lang::RuntimeException::getClass0()
{
    return class_();
}


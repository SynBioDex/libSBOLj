// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Error.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Error::Error(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Error::Error()
    : Error(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::Error::Error(String* message)
    : Error(*static_cast< ::default_init_tag* >(0))
{
    ctor(message);
}

java::lang::Error::Error(Throwable* cause)
    : Error(*static_cast< ::default_init_tag* >(0))
{
    ctor(cause);
}

java::lang::Error::Error(String* message, Throwable* cause)
    : Error(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause);
}

java::lang::Error::Error(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)
    : Error(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause, enableSuppression, writableStackTrace);
}

constexpr int64_t java::lang::Error::serialVersionUID;

void ::java::lang::Error::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Error::ctor()");
}

void ::java::lang::Error::ctor(String* message)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Error::ctor(String* message)");
}

void ::java::lang::Error::ctor(Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Error::ctor(Throwable* cause)");
}

void ::java::lang::Error::ctor(String* message, Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Error::ctor(String* message, Throwable* cause)");
}

void ::java::lang::Error::ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Error::ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Error::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Error", 15);
    return c;
}

java::lang::Class* java::lang::Error::getClass0()
{
    return class_();
}


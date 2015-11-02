// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Exception.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Exception::Exception(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Exception::Exception()
    : Exception(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::Exception::Exception(String* message)
    : Exception(*static_cast< ::default_init_tag* >(0))
{
    ctor(message);
}

java::lang::Exception::Exception(Throwable* cause)
    : Exception(*static_cast< ::default_init_tag* >(0))
{
    ctor(cause);
}

java::lang::Exception::Exception(String* message, Throwable* cause)
    : Exception(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause);
}

java::lang::Exception::Exception(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)
    : Exception(*static_cast< ::default_init_tag* >(0))
{
    ctor(message, cause, enableSuppression, writableStackTrace);
}

constexpr int64_t java::lang::Exception::serialVersionUID;

void ::java::lang::Exception::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Exception::ctor()");
}

void ::java::lang::Exception::ctor(String* message)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Exception::ctor(String* message)");
}

void ::java::lang::Exception::ctor(Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Exception::ctor(Throwable* cause)");
}

void ::java::lang::Exception::ctor(String* message, Throwable* cause)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Exception::ctor(String* message, Throwable* cause)");
}

void ::java::lang::Exception::ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Exception::ctor(String* message, Throwable* cause, bool enableSuppression, bool writableStackTrace)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Exception::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Exception", 19);
    return c;
}

java::lang::Class* java::lang::Exception::getClass0()
{
    return class_();
}


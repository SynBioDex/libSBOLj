// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/NullPointerException.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::NullPointerException::NullPointerException(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::NullPointerException::NullPointerException()
    : NullPointerException(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::NullPointerException::NullPointerException(String* s)
    : NullPointerException(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}

constexpr int64_t java::lang::NullPointerException::serialVersionUID;

void ::java::lang::NullPointerException::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::NullPointerException::ctor()");
}

void ::java::lang::NullPointerException::ctor(String* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::NullPointerException::ctor(String* s)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::NullPointerException::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.NullPointerException", 30);
    return c;
}

java::lang::Class* java::lang::NullPointerException::getClass0()
{
    return class_();
}


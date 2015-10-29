// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/ClassCastException.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::ClassCastException::ClassCastException(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::ClassCastException::ClassCastException()
    : ClassCastException(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::ClassCastException::ClassCastException(String* s)
    : ClassCastException(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}

constexpr int64_t java::lang::ClassCastException::serialVersionUID;

void ::java::lang::ClassCastException::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ClassCastException::ctor()");
}

void ::java::lang::ClassCastException::ctor(String* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ClassCastException::ctor(String* s)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::ClassCastException::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.ClassCastException", 28);
    return c;
}

java::lang::Class* java::lang::ClassCastException::getClass0()
{
    return class_();
}


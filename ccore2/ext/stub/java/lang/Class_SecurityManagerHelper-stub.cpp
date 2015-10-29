// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Class_SecurityManagerHelper.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Class_SecurityManagerHelper::Class_SecurityManagerHelper(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Class_SecurityManagerHelper::Class_SecurityManagerHelper(SecurityManager* sm)
    : Class_SecurityManagerHelper(*static_cast< ::default_init_tag* >(0))
{
    ctor(sm);
}


void ::java::lang::Class_SecurityManagerHelper::ctor(SecurityManager* sm)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Class_SecurityManagerHelper::ctor(SecurityManager* sm)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Class_SecurityManagerHelper::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Class.SecurityManagerHelper", 37);
    return c;
}

java::lang::Class* java::lang::Class_SecurityManagerHelper::getClass0()
{
    return class_();
}


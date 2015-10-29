// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Thread_WeakClassKey.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Thread_WeakClassKey::Thread_WeakClassKey(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Thread_WeakClassKey::Thread_WeakClassKey(Class* cl, ::java::lang::ref::ReferenceQueue* refQueue)
    : Thread_WeakClassKey(*static_cast< ::default_init_tag* >(0))
{
    ctor(cl, refQueue);
}


void ::java::lang::Thread_WeakClassKey::ctor(Class* cl, ::java::lang::ref::ReferenceQueue* refQueue)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Thread_WeakClassKey::ctor(Class* cl, ::java::lang::ref::ReferenceQueue* refQueue)");
}

bool java::lang::Thread_WeakClassKey::equals(Object* obj)
{ /* stub */
    unimplemented_(u"bool java::lang::Thread_WeakClassKey::equals(Object* obj)");
    return 0;
}

int32_t java::lang::Thread_WeakClassKey::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::lang::Thread_WeakClassKey::hashCode()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Thread_WeakClassKey::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Thread.WeakClassKey", 29);
    return c;
}

java::lang::Class* java::lang::Thread_WeakClassKey::getClass0()
{
    return class_();
}


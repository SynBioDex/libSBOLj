// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/ref/WeakReference.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::ref::WeakReference::WeakReference(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::ref::WeakReference::WeakReference(::java::lang::Object* referent)
    : WeakReference(*static_cast< ::default_init_tag* >(0))
{
    ctor(referent);
}

java::lang::ref::WeakReference::WeakReference(::java::lang::Object* referent, ReferenceQueue* q)
    : WeakReference(*static_cast< ::default_init_tag* >(0))
{
    ctor(referent, q);
}


void ::java::lang::ref::WeakReference::ctor(::java::lang::Object* referent)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ref::WeakReference::ctor(::java::lang::Object* referent)");
}

void ::java::lang::ref::WeakReference::ctor(::java::lang::Object* referent, ReferenceQueue* q)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ref::WeakReference::ctor(::java::lang::Object* referent, ReferenceQueue* q)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::ref::WeakReference::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.ref.WeakReference", 27);
    return c;
}

java::lang::Class* java::lang::ref::WeakReference::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/ref/SoftReference.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::ref::SoftReference::SoftReference(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::ref::SoftReference::SoftReference(::java::lang::Object* referent)
    : SoftReference(*static_cast< ::default_init_tag* >(0))
{
    ctor(referent);
}

java::lang::ref::SoftReference::SoftReference(::java::lang::Object* referent, ReferenceQueue* q)
    : SoftReference(*static_cast< ::default_init_tag* >(0))
{
    ctor(referent, q);
}

int64_t& java::lang::ref::SoftReference::clock()
{
    clinit();
    return clock_;
}
int64_t java::lang::ref::SoftReference::clock_;

void ::java::lang::ref::SoftReference::ctor(::java::lang::Object* referent)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ref::SoftReference::ctor(::java::lang::Object* referent)");
}

void ::java::lang::ref::SoftReference::ctor(::java::lang::Object* referent, ReferenceQueue* q)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ref::SoftReference::ctor(::java::lang::Object* referent, ReferenceQueue* q)");
}

java::lang::Object* java::lang::ref::SoftReference::get()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::lang::ref::SoftReference::get()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::ref::SoftReference::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.ref.SoftReference", 27);
    return c;
}

java::lang::Class* java::lang::ref::SoftReference::getClass0()
{
    return class_();
}


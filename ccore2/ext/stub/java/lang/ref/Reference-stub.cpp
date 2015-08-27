// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/ref/Reference.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::ref::Reference::Reference(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::ref::Reference::Reference(::java::lang::Object* referent)
    : Reference(*static_cast< ::default_init_tag* >(0))
{
    ctor(referent);
}

java::lang::ref::Reference::Reference(::java::lang::Object* referent, ReferenceQueue* queue)
    : Reference(*static_cast< ::default_init_tag* >(0))
{
    ctor(referent, queue);
}

java::lang::ref::Reference_Lock*& java::lang::ref::Reference::lock()
{
    clinit();
    return lock_;
}
java::lang::ref::Reference_Lock* java::lang::ref::Reference::lock_;
java::lang::ref::Reference*& java::lang::ref::Reference::pending()
{
    clinit();
    return pending_;
}
java::lang::ref::Reference* java::lang::ref::Reference::pending_;

void ::java::lang::ref::Reference::ctor(::java::lang::Object* referent)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ref::Reference::ctor(::java::lang::Object* referent)");
}

void ::java::lang::ref::Reference::ctor(::java::lang::Object* referent, ReferenceQueue* queue)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ref::Reference::ctor(::java::lang::Object* referent, ReferenceQueue* queue)");
}

void java::lang::ref::Reference::clear()
{ /* stub */
    unimplemented_(u"void java::lang::ref::Reference::clear()");
}

bool java::lang::ref::Reference::enqueue()
{ /* stub */
    unimplemented_(u"bool java::lang::ref::Reference::enqueue()");
    return 0;
}

java::lang::Object* java::lang::ref::Reference::get()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::lang::ref::Reference::get()");
    return 0;
}

bool java::lang::ref::Reference::isEnqueued()
{ /* stub */
    unimplemented_(u"bool java::lang::ref::Reference::isEnqueued()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::ref::Reference::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.ref.Reference", 23);
    return c;
}

java::lang::Class* java::lang::ref::Reference::getClass0()
{
    return class_();
}


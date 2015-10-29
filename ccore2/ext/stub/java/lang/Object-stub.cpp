// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Object.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Object::Object(const ::default_init_tag&)
{
    clinit();
}

java::lang::Object::Object()
    : Object(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::java::lang::Object::ctor()
{ /* stub */
    unimplemented_(u"void ::java::lang::Object::ctor()");
}

bool java::lang::Object::equals(Object* obj)
{ /* stub */
    unimplemented_(u"bool java::lang::Object::equals(Object* obj)");
    return 0;
}

void java::lang::Object::finalize()
{ /* stub */
    unimplemented_(u"void java::lang::Object::finalize()");
}

java::lang::String* java::lang::Object::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Object::toString()");
    return 0;
}

void java::lang::Object::wait()
{ /* stub */
    unimplemented_(u"void java::lang::Object::wait()");
}

void java::lang::Object::wait(int64_t timeout, int32_t nanos)
{ /* stub */
    unimplemented_(u"void java::lang::Object::wait(int64_t timeout, int32_t nanos)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Object::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Object", 16);
    return c;
}

void java::lang::Object::clinit()
{
}

java::lang::Object::~Object()
{
}

java::lang::Class* java::lang::Object::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/ref/Reference_ReferenceHandler.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::ref::Reference_ReferenceHandler::Reference_ReferenceHandler(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::ref::Reference_ReferenceHandler::Reference_ReferenceHandler(::java::lang::ThreadGroup* g, ::java::lang::String* name)
    : Reference_ReferenceHandler(*static_cast< ::default_init_tag* >(0))
{
    ctor(g, name);
}


void ::java::lang::ref::Reference_ReferenceHandler::ctor(::java::lang::ThreadGroup* g, ::java::lang::String* name)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::ref::Reference_ReferenceHandler::ctor(::java::lang::ThreadGroup* g, ::java::lang::String* name)");
}

void java::lang::ref::Reference_ReferenceHandler::run()
{ /* stub */
    unimplemented_(u"void java::lang::ref::Reference_ReferenceHandler::run()");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::ref::Reference_ReferenceHandler::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.ref.Reference.ReferenceHandler", 40);
    return c;
}

java::lang::Class* java::lang::ref::Reference_ReferenceHandler::getClass0()
{
    return class_();
}


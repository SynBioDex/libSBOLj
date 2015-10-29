// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Throwable_WrappedPrintStream.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Throwable_WrappedPrintStream::Throwable_WrappedPrintStream(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Throwable_WrappedPrintStream::Throwable_WrappedPrintStream(::java::io::PrintStream* printStream)
    : Throwable_WrappedPrintStream(*static_cast< ::default_init_tag* >(0))
{
    ctor(printStream);
}


void ::java::lang::Throwable_WrappedPrintStream::ctor(::java::io::PrintStream* printStream)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Throwable_WrappedPrintStream::ctor(::java::io::PrintStream* printStream)");
}

java::lang::Object* java::lang::Throwable_WrappedPrintStream::lock()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::lang::Throwable_WrappedPrintStream::lock()");
    return 0;
}

void java::lang::Throwable_WrappedPrintStream::println(Object* o)
{ /* stub */
    unimplemented_(u"void java::lang::Throwable_WrappedPrintStream::println(Object* o)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Throwable_WrappedPrintStream::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Throwable.WrappedPrintStream", 38);
    return c;
}

java::lang::Class* java::lang::Throwable_WrappedPrintStream::getClass0()
{
    return class_();
}


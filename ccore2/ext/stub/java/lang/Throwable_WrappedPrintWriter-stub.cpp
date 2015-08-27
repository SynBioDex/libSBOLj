// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Throwable_WrappedPrintWriter.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Throwable_WrappedPrintWriter::Throwable_WrappedPrintWriter(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Throwable_WrappedPrintWriter::Throwable_WrappedPrintWriter(::java::io::PrintWriter* printWriter)
    : Throwable_WrappedPrintWriter(*static_cast< ::default_init_tag* >(0))
{
    ctor(printWriter);
}


void ::java::lang::Throwable_WrappedPrintWriter::ctor(::java::io::PrintWriter* printWriter)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Throwable_WrappedPrintWriter::ctor(::java::io::PrintWriter* printWriter)");
}

java::lang::Object* java::lang::Throwable_WrappedPrintWriter::lock()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::lang::Throwable_WrappedPrintWriter::lock()");
    return 0;
}

void java::lang::Throwable_WrappedPrintWriter::println(Object* o)
{ /* stub */
    unimplemented_(u"void java::lang::Throwable_WrappedPrintWriter::println(Object* o)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Throwable_WrappedPrintWriter::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Throwable.WrappedPrintWriter", 38);
    return c;
}

java::lang::Class* java::lang::Throwable_WrappedPrintWriter::getClass0()
{
    return class_();
}


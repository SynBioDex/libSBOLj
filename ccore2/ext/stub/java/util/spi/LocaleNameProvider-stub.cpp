// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/spi/LocaleNameProvider.hpp>

extern void unimplemented_(const char16_t* name);
java::util::spi::LocaleNameProvider::LocaleNameProvider(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::spi::LocaleNameProvider::LocaleNameProvider()
    : LocaleNameProvider(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::java::util::spi::LocaleNameProvider::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::spi::LocaleNameProvider::ctor()");
}

java::lang::String* java::util::spi::LocaleNameProvider::getDisplayScript(::java::lang::String* scriptCode, ::java::util::Locale* locale)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::spi::LocaleNameProvider::getDisplayScript(::java::lang::String* scriptCode, ::java::util::Locale* locale)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::spi::LocaleNameProvider::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.spi.LocaleNameProvider", 32);
    return c;
}

java::lang::Class* java::util::spi::LocaleNameProvider::getClass0()
{
    return class_();
}


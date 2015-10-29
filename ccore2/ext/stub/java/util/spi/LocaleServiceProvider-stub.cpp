// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/spi/LocaleServiceProvider.hpp>

extern void unimplemented_(const char16_t* name);
java::util::spi::LocaleServiceProvider::LocaleServiceProvider(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::spi::LocaleServiceProvider::LocaleServiceProvider()
    : LocaleServiceProvider(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::java::util::spi::LocaleServiceProvider::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::spi::LocaleServiceProvider::ctor()");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::spi::LocaleServiceProvider::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.spi.LocaleServiceProvider", 35);
    return c;
}

java::lang::Class* java::util::spi::LocaleServiceProvider::getClass0()
{
    return class_();
}


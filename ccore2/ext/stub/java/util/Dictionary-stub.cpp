// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Dictionary.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Dictionary::Dictionary(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Dictionary::Dictionary()
    : Dictionary(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::java::util::Dictionary::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Dictionary::ctor()");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Dictionary::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Dictionary", 20);
    return c;
}

java::lang::Class* java::util::Dictionary::getClass0()
{
    return class_();
}


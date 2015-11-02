// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_SingleU.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_SingleU::Pattern_SingleU(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_SingleU::Pattern_SingleU(int32_t lower)
    : Pattern_SingleU(*static_cast< ::default_init_tag* >(0))
{
    ctor(lower);
}


void ::java::util::regex::Pattern_SingleU::ctor(int32_t lower)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_SingleU::ctor(int32_t lower)");
}

bool java::util::regex::Pattern_SingleU::isSatisfiedBy(int32_t ch)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_SingleU::isSatisfiedBy(int32_t ch)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_SingleU::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.SingleU", 31);
    return c;
}

java::lang::Class* java::util::regex::Pattern_SingleU::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_SingleI.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_SingleI::Pattern_SingleI(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_SingleI::Pattern_SingleI(int32_t lower, int32_t upper)
    : Pattern_SingleI(*static_cast< ::default_init_tag* >(0))
{
    ctor(lower, upper);
}


void ::java::util::regex::Pattern_SingleI::ctor(int32_t lower, int32_t upper)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_SingleI::ctor(int32_t lower, int32_t upper)");
}

bool java::util::regex::Pattern_SingleI::isSatisfiedBy(int32_t ch)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_SingleI::isSatisfiedBy(int32_t ch)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_SingleI::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.SingleI", 31);
    return c;
}

java::lang::Class* java::util::regex::Pattern_SingleI::getClass0()
{
    return class_();
}


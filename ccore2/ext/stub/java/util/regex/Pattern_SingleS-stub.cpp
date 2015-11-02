// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_SingleS.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_SingleS::Pattern_SingleS(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_SingleS::Pattern_SingleS(int32_t c)
    : Pattern_SingleS(*static_cast< ::default_init_tag* >(0))
{
    ctor(c);
}


void ::java::util::regex::Pattern_SingleS::ctor(int32_t c)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_SingleS::ctor(int32_t c)");
}

bool java::util::regex::Pattern_SingleS::isSatisfiedBy(int32_t ch)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_SingleS::isSatisfiedBy(int32_t ch)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_SingleS::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.SingleS", 31);
    return c;
}

java::lang::Class* java::util::regex::Pattern_SingleS::getClass0()
{
    return class_();
}


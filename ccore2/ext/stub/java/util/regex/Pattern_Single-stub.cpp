// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Single.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Single::Pattern_Single(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Single::Pattern_Single(int32_t c)
    : Pattern_Single(*static_cast< ::default_init_tag* >(0))
{
    ctor(c);
}


void ::java::util::regex::Pattern_Single::ctor(int32_t c)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Single::ctor(int32_t c)");
}

bool java::util::regex::Pattern_Single::isSatisfiedBy(int32_t ch)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Single::isSatisfiedBy(int32_t ch)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Single::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Single", 30);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Single::getClass0()
{
    return class_();
}


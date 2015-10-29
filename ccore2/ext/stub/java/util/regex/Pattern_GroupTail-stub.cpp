// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_GroupTail.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_GroupTail::Pattern_GroupTail(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_GroupTail::Pattern_GroupTail(int32_t localCount, int32_t groupCount)
    : Pattern_GroupTail(*static_cast< ::default_init_tag* >(0))
{
    ctor(localCount, groupCount);
}


void ::java::util::regex::Pattern_GroupTail::ctor(int32_t localCount, int32_t groupCount)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_GroupTail::ctor(int32_t localCount, int32_t groupCount)");
}

bool java::util::regex::Pattern_GroupTail::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_GroupTail::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_GroupTail::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.GroupTail", 33);
    return c;
}

java::lang::Class* java::util::regex::Pattern_GroupTail::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Loop.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Loop::Pattern_Loop(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Loop::Pattern_Loop(int32_t countIndex, int32_t beginIndex)
    : Pattern_Loop(*static_cast< ::default_init_tag* >(0))
{
    ctor(countIndex, beginIndex);
}


void ::java::util::regex::Pattern_Loop::ctor(int32_t countIndex, int32_t beginIndex)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Loop::ctor(int32_t countIndex, int32_t beginIndex)");
}

bool java::util::regex::Pattern_Loop::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Loop::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_Loop::matchInit(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Loop::matchInit(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_Loop::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Loop::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Loop::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Loop", 28);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Loop::getClass0()
{
    return class_();
}


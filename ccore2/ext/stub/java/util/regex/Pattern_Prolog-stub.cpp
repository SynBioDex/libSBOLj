// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Prolog.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Prolog::Pattern_Prolog(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Prolog::Pattern_Prolog(Pattern_Loop* loop)
    : Pattern_Prolog(*static_cast< ::default_init_tag* >(0))
{
    ctor(loop);
}


void ::java::util::regex::Pattern_Prolog::ctor(Pattern_Loop* loop)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Prolog::ctor(Pattern_Loop* loop)");
}

bool java::util::regex::Pattern_Prolog::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Prolog::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_Prolog::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Prolog::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Prolog::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Prolog", 30);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Prolog::getClass0()
{
    return class_();
}


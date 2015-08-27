// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_CIBackRef.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_CIBackRef::Pattern_CIBackRef(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_CIBackRef::Pattern_CIBackRef(int32_t groupCount, bool doUnicodeCase)
    : Pattern_CIBackRef(*static_cast< ::default_init_tag* >(0))
{
    ctor(groupCount, doUnicodeCase);
}


void ::java::util::regex::Pattern_CIBackRef::ctor(int32_t groupCount, bool doUnicodeCase)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_CIBackRef::ctor(int32_t groupCount, bool doUnicodeCase)");
}

bool java::util::regex::Pattern_CIBackRef::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_CIBackRef::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_CIBackRef::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_CIBackRef::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_CIBackRef::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.CIBackRef", 33);
    return c;
}

java::lang::Class* java::util::regex::Pattern_CIBackRef::getClass0()
{
    return class_();
}


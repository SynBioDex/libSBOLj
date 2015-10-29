// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Neg.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Neg::Pattern_Neg(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Neg::Pattern_Neg(Pattern_Node* cond)
    : Pattern_Neg(*static_cast< ::default_init_tag* >(0))
{
    ctor(cond);
}


void ::java::util::regex::Pattern_Neg::ctor(Pattern_Node* cond)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Neg::ctor(Pattern_Node* cond)");
}

bool java::util::regex::Pattern_Neg::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Neg::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Neg::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Neg", 27);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Neg::getClass0()
{
    return class_();
}


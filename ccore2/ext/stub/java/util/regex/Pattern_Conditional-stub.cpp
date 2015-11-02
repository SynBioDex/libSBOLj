// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Conditional.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Conditional::Pattern_Conditional(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Conditional::Pattern_Conditional(Pattern_Node* cond, Pattern_Node* yes, Pattern_Node* not_)
    : Pattern_Conditional(*static_cast< ::default_init_tag* >(0))
{
    ctor(cond, yes, not_);
}


void ::java::util::regex::Pattern_Conditional::ctor(Pattern_Node* cond, Pattern_Node* yes, Pattern_Node* not_)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Conditional::ctor(Pattern_Node* cond, Pattern_Node* yes, Pattern_Node* not_)");
}

bool java::util::regex::Pattern_Conditional::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Conditional::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_Conditional::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Conditional::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Conditional::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Conditional", 35);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Conditional::getClass0()
{
    return class_();
}


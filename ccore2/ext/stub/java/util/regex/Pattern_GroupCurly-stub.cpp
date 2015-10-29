// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_GroupCurly.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_GroupCurly::Pattern_GroupCurly(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_GroupCurly::Pattern_GroupCurly(Pattern_Node* node, int32_t cmin, int32_t cmax, int32_t type, int32_t local, int32_t group, bool capture)
    : Pattern_GroupCurly(*static_cast< ::default_init_tag* >(0))
{
    ctor(node, cmin, cmax, type, local, group, capture);
}


void ::java::util::regex::Pattern_GroupCurly::ctor(Pattern_Node* node, int32_t cmin, int32_t cmax, int32_t type, int32_t local, int32_t group, bool capture)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_GroupCurly::ctor(Pattern_Node* node, int32_t cmin, int32_t cmax, int32_t type, int32_t local, int32_t group, bool capture)");
}

bool java::util::regex::Pattern_GroupCurly::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_GroupCurly::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_GroupCurly::match0(Matcher* matcher, int32_t i, int32_t j, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_GroupCurly::match0(Matcher* matcher, int32_t i, int32_t j, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_GroupCurly::match1(Matcher* matcher, int32_t i, int32_t j, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_GroupCurly::match1(Matcher* matcher, int32_t i, int32_t j, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_GroupCurly::match2(Matcher* matcher, int32_t i, int32_t j, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_GroupCurly::match2(Matcher* matcher, int32_t i, int32_t j, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_GroupCurly::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_GroupCurly::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_GroupCurly::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.GroupCurly", 34);
    return c;
}

java::lang::Class* java::util::regex::Pattern_GroupCurly::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Start.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Start::Pattern_Start(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Start::Pattern_Start(Pattern_Node* node)
    : Pattern_Start(*static_cast< ::default_init_tag* >(0))
{
    ctor(node);
}


void ::java::util::regex::Pattern_Start::ctor(Pattern_Node* node)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Start::ctor(Pattern_Node* node)");
}

bool java::util::regex::Pattern_Start::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Start::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_Start::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Start::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Start::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Start", 29);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Start::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_First.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_First::Pattern_First(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_First::Pattern_First(Pattern_Node* node)
    : Pattern_First(*static_cast< ::default_init_tag* >(0))
{
    ctor(node);
}


void ::java::util::regex::Pattern_First::ctor(Pattern_Node* node)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_First::ctor(Pattern_Node* node)");
}

bool java::util::regex::Pattern_First::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_First::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_First::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_First::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_First::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.First", 29);
    return c;
}

java::lang::Class* java::util::regex::Pattern_First::getClass0()
{
    return class_();
}


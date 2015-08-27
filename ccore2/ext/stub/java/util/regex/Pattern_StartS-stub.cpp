// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_StartS.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_StartS::Pattern_StartS(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_StartS::Pattern_StartS(Pattern_Node* node)
    : Pattern_StartS(*static_cast< ::default_init_tag* >(0))
{
    ctor(node);
}


void ::java::util::regex::Pattern_StartS::ctor(Pattern_Node* node)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_StartS::ctor(Pattern_Node* node)");
}

bool java::util::regex::Pattern_StartS::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_StartS::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_StartS::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.StartS", 30);
    return c;
}

java::lang::Class* java::util::regex::Pattern_StartS::getClass0()
{
    return class_();
}


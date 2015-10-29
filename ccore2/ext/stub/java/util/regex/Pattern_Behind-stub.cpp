// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Behind.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Behind::Pattern_Behind(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Behind::Pattern_Behind(Pattern_Node* cond, int32_t rmax, int32_t rmin)
    : Pattern_Behind(*static_cast< ::default_init_tag* >(0))
{
    ctor(cond, rmax, rmin);
}


void ::java::util::regex::Pattern_Behind::ctor(Pattern_Node* cond, int32_t rmax, int32_t rmin)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Behind::ctor(Pattern_Node* cond, int32_t rmax, int32_t rmin)");
}

bool java::util::regex::Pattern_Behind::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Behind::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Behind::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Behind", 30);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Behind::getClass0()
{
    return class_();
}


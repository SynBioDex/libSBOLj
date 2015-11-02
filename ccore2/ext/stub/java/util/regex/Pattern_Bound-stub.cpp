// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Bound.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Bound::Pattern_Bound(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Bound::Pattern_Bound(int32_t n, bool useUWORD)
    : Pattern_Bound(*static_cast< ::default_init_tag* >(0))
{
    ctor(n, useUWORD);
}

int32_t& java::util::regex::Pattern_Bound::BOTH()
{
    clinit();
    return BOTH_;
}
int32_t java::util::regex::Pattern_Bound::BOTH_;
int32_t& java::util::regex::Pattern_Bound::LEFT()
{
    clinit();
    return LEFT_;
}
int32_t java::util::regex::Pattern_Bound::LEFT_;
int32_t& java::util::regex::Pattern_Bound::NONE()
{
    clinit();
    return NONE_;
}
int32_t java::util::regex::Pattern_Bound::NONE_;
int32_t& java::util::regex::Pattern_Bound::RIGHT()
{
    clinit();
    return RIGHT_;
}
int32_t java::util::regex::Pattern_Bound::RIGHT_;

void ::java::util::regex::Pattern_Bound::ctor(int32_t n, bool useUWORD)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Bound::ctor(int32_t n, bool useUWORD)");
}

int32_t java::util::regex::Pattern_Bound::check(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"int32_t java::util::regex::Pattern_Bound::check(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_Bound::isWord(int32_t ch)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Bound::isWord(int32_t ch)");
    return 0;
}

bool java::util::regex::Pattern_Bound::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Bound::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Bound::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Bound", 29);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Bound::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_SliceIS.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_SliceIS::Pattern_SliceIS(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_SliceIS::Pattern_SliceIS(::int32_tArray* buf)
    : Pattern_SliceIS(*static_cast< ::default_init_tag* >(0))
{
    ctor(buf);
}


void ::java::util::regex::Pattern_SliceIS::ctor(::int32_tArray* buf)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_SliceIS::ctor(::int32_tArray* buf)");
}

bool java::util::regex::Pattern_SliceIS::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_SliceIS::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

int32_t java::util::regex::Pattern_SliceIS::toLower(int32_t c)
{ /* stub */
    unimplemented_(u"int32_t java::util::regex::Pattern_SliceIS::toLower(int32_t c)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_SliceIS::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.SliceIS", 31);
    return c;
}

java::lang::Class* java::util::regex::Pattern_SliceIS::getClass0()
{
    return class_();
}


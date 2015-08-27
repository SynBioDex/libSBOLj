// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_SliceS.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_SliceS::Pattern_SliceS(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_SliceS::Pattern_SliceS(::int32_tArray* buf)
    : Pattern_SliceS(*static_cast< ::default_init_tag* >(0))
{
    ctor(buf);
}


void ::java::util::regex::Pattern_SliceS::ctor(::int32_tArray* buf)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_SliceS::ctor(::int32_tArray* buf)");
}

bool java::util::regex::Pattern_SliceS::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_SliceS::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_SliceS::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.SliceS", 30);
    return c;
}

java::lang::Class* java::util::regex::Pattern_SliceS::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_BnMS.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_BnMS::Pattern_BnMS(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_BnMS::Pattern_BnMS(::int32_tArray* src, ::int32_tArray* lastOcc, ::int32_tArray* optoSft, Pattern_Node* next)
    : Pattern_BnMS(*static_cast< ::default_init_tag* >(0))
{
    ctor(src, lastOcc, optoSft, next);
}


void ::java::util::regex::Pattern_BnMS::ctor(::int32_tArray* src, ::int32_tArray* lastOcc, ::int32_tArray* optoSft, Pattern_Node* next)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_BnMS::ctor(::int32_tArray* src, ::int32_tArray* lastOcc, ::int32_tArray* optoSft, Pattern_Node* next)");
}

bool java::util::regex::Pattern_BnMS::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_BnMS::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_BnMS::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.BnMS", 28);
    return c;
}

java::lang::Class* java::util::regex::Pattern_BnMS::getClass0()
{
    return class_();
}


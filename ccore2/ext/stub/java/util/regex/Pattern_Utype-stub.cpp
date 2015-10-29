// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Utype.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Utype::Pattern_Utype(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Utype::Pattern_Utype(UnicodeProp* uprop)
    : Pattern_Utype(*static_cast< ::default_init_tag* >(0))
{
    ctor(uprop);
}


void ::java::util::regex::Pattern_Utype::ctor(UnicodeProp* uprop)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Utype::ctor(UnicodeProp* uprop)");
}

bool java::util::regex::Pattern_Utype::isSatisfiedBy(int32_t ch)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Utype::isSatisfiedBy(int32_t ch)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Utype::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Utype", 29);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Utype::getClass0()
{
    return class_();
}


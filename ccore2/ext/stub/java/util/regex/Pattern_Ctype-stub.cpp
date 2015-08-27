// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Ctype.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Ctype::Pattern_Ctype(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Ctype::Pattern_Ctype(int32_t ctype)
    : Pattern_Ctype(*static_cast< ::default_init_tag* >(0))
{
    ctor(ctype);
}


void ::java::util::regex::Pattern_Ctype::ctor(int32_t ctype)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Ctype::ctor(int32_t ctype)");
}

bool java::util::regex::Pattern_Ctype::isSatisfiedBy(int32_t ch)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Ctype::isSatisfiedBy(int32_t ch)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Ctype::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Ctype", 29);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Ctype::getClass0()
{
    return class_();
}


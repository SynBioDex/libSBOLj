// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Category.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Category::Pattern_Category(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Category::Pattern_Category(int32_t typeMask)
    : Pattern_Category(*static_cast< ::default_init_tag* >(0))
{
    ctor(typeMask);
}


void ::java::util::regex::Pattern_Category::ctor(int32_t typeMask)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Category::ctor(int32_t typeMask)");
}

bool java::util::regex::Pattern_Category::isSatisfiedBy(int32_t ch)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Category::isSatisfiedBy(int32_t ch)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Category::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Category", 32);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Category::getClass0()
{
    return class_();
}


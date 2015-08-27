// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_LazyLoop.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_LazyLoop::Pattern_LazyLoop(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_LazyLoop::Pattern_LazyLoop(int32_t countIndex, int32_t beginIndex)
    : Pattern_LazyLoop(*static_cast< ::default_init_tag* >(0))
{
    ctor(countIndex, beginIndex);
}


void ::java::util::regex::Pattern_LazyLoop::ctor(int32_t countIndex, int32_t beginIndex)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_LazyLoop::ctor(int32_t countIndex, int32_t beginIndex)");
}

bool java::util::regex::Pattern_LazyLoop::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_LazyLoop::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_LazyLoop::matchInit(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_LazyLoop::matchInit(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_LazyLoop::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_LazyLoop::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_LazyLoop::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.LazyLoop", 32);
    return c;
}

java::lang::Class* java::util::regex::Pattern_LazyLoop::getClass0()
{
    return class_();
}


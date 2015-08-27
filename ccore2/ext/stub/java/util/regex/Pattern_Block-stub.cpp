// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Block.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Block::Pattern_Block(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Block::Pattern_Block(::java::lang::Character_UnicodeBlock* block)
    : Pattern_Block(*static_cast< ::default_init_tag* >(0))
{
    ctor(block);
}


void ::java::util::regex::Pattern_Block::ctor(::java::lang::Character_UnicodeBlock* block)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Block::ctor(::java::lang::Character_UnicodeBlock* block)");
}

bool java::util::regex::Pattern_Block::isSatisfiedBy(int32_t ch)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Block::isSatisfiedBy(int32_t ch)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Block::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Block", 29);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Block::getClass0()
{
    return class_();
}


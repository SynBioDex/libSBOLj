// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_SliceNode.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_SliceNode::Pattern_SliceNode(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_SliceNode::Pattern_SliceNode(::int32_tArray* buf)
    : Pattern_SliceNode(*static_cast< ::default_init_tag* >(0))
{
    ctor(buf);
}


void ::java::util::regex::Pattern_SliceNode::ctor(::int32_tArray* buf)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_SliceNode::ctor(::int32_tArray* buf)");
}

bool java::util::regex::Pattern_SliceNode::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_SliceNode::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_SliceNode::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.SliceNode", 33);
    return c;
}

java::lang::Class* java::util::regex::Pattern_SliceNode::getClass0()
{
    return class_();
}


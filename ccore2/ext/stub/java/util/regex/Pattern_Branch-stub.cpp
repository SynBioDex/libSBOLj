// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_Branch.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_Branch::Pattern_Branch(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::regex::Pattern_Branch::Pattern_Branch(Pattern_Node* first, Pattern_Node* second, Pattern_Node* branchConn)
    : Pattern_Branch(*static_cast< ::default_init_tag* >(0))
{
    ctor(first, second, branchConn);
}


void ::java::util::regex::Pattern_Branch::ctor(Pattern_Node* first, Pattern_Node* second, Pattern_Node* branchConn)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::regex::Pattern_Branch::ctor(Pattern_Node* first, Pattern_Node* second, Pattern_Node* branchConn)");
}

void java::util::regex::Pattern_Branch::add(Pattern_Node* node)
{ /* stub */
    unimplemented_(u"void java::util::regex::Pattern_Branch::add(Pattern_Node* node)");
}

bool java::util::regex::Pattern_Branch::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Branch::match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq)");
    return 0;
}

bool java::util::regex::Pattern_Branch::study(Pattern_TreeInfo* info)
{ /* stub */
    unimplemented_(u"bool java::util::regex::Pattern_Branch::study(Pattern_TreeInfo* info)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_Branch::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.Branch", 30);
    return c;
}

java::lang::Class* java::util::regex::Pattern_Branch::getClass0()
{
    return class_();
}


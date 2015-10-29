// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_EmptyEnumeration.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_EmptyEnumeration::Collections_EmptyEnumeration(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_EmptyEnumeration*& java::util::Collections_EmptyEnumeration::EMPTY_ENUMERATION()
{
    clinit();
    return EMPTY_ENUMERATION_;
}
java::util::Collections_EmptyEnumeration* java::util::Collections_EmptyEnumeration::EMPTY_ENUMERATION_;

/* private: void ::java::util::Collections_EmptyEnumeration::ctor() */
bool java::util::Collections_EmptyEnumeration::hasMoreElements()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_EmptyEnumeration::hasMoreElements()");
    return 0;
}

java::lang::Object* java::util::Collections_EmptyEnumeration::nextElement()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_EmptyEnumeration::nextElement()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_EmptyEnumeration::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.EmptyEnumeration", 38);
    return c;
}

java::lang::Class* java::util::Collections_EmptyEnumeration::getClass0()
{
    return class_();
}


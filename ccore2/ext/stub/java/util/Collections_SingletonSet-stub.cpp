// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_SingletonSet.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_SingletonSet::Collections_SingletonSet(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_SingletonSet::Collections_SingletonSet(::java::lang::Object* e)
    : Collections_SingletonSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(e);
}

constexpr int64_t java::util::Collections_SingletonSet::serialVersionUID;

void ::java::util::Collections_SingletonSet::ctor(::java::lang::Object* e)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SingletonSet::ctor(::java::lang::Object* e)");
}

bool java::util::Collections_SingletonSet::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SingletonSet::contains(::java::lang::Object* o)");
    return 0;
}

java::util::Iterator* java::util::Collections_SingletonSet::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Collections_SingletonSet::iterator()");
    return 0;
}

int32_t java::util::Collections_SingletonSet::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SingletonSet::size()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_SingletonSet::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.SingletonSet", 34);
    return c;
}

java::lang::Class* java::util::Collections_SingletonSet::getClass0()
{
    return class_();
}


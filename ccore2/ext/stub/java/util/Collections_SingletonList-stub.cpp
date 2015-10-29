// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_SingletonList.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_SingletonList::Collections_SingletonList(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_SingletonList::Collections_SingletonList(::java::lang::Object* obj)
    : Collections_SingletonList(*static_cast< ::default_init_tag* >(0))
{
    ctor(obj);
}

constexpr int64_t java::util::Collections_SingletonList::serialVersionUID;

void ::java::util::Collections_SingletonList::ctor(::java::lang::Object* obj)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SingletonList::ctor(::java::lang::Object* obj)");
}

bool java::util::Collections_SingletonList::contains(::java::lang::Object* obj)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SingletonList::contains(::java::lang::Object* obj)");
    return 0;
}

java::lang::Object* java::util::Collections_SingletonList::get(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SingletonList::get(int32_t index)");
    return 0;
}

java::util::Iterator* java::util::Collections_SingletonList::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Collections_SingletonList::iterator()");
    return 0;
}

int32_t java::util::Collections_SingletonList::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SingletonList::size()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_SingletonList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.SingletonList", 35);
    return c;
}

java::lang::Class* java::util::Collections_SingletonList::getClass0()
{
    return class_();
}


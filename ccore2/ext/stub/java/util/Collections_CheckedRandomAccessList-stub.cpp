// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_CheckedRandomAccessList.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_CheckedRandomAccessList::Collections_CheckedRandomAccessList(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_CheckedRandomAccessList::Collections_CheckedRandomAccessList(List* list, ::java::lang::Class* type)
    : Collections_CheckedRandomAccessList(*static_cast< ::default_init_tag* >(0))
{
    ctor(list, type);
}

constexpr int64_t java::util::Collections_CheckedRandomAccessList::serialVersionUID;

void ::java::util::Collections_CheckedRandomAccessList::ctor(List* list, ::java::lang::Class* type)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_CheckedRandomAccessList::ctor(List* list, ::java::lang::Class* type)");
}

java::util::List* java::util::Collections_CheckedRandomAccessList::subList(int32_t fromIndex, int32_t toIndex)
{ /* stub */
    unimplemented_(u"java::util::List* java::util::Collections_CheckedRandomAccessList::subList(int32_t fromIndex, int32_t toIndex)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_CheckedRandomAccessList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.CheckedRandomAccessList", 45);
    return c;
}

java::lang::Class* java::util::Collections_CheckedRandomAccessList::getClass0()
{
    return class_();
}


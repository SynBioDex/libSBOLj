// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_SynchronizedRandomAccessList.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_SynchronizedRandomAccessList::Collections_SynchronizedRandomAccessList(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_SynchronizedRandomAccessList::Collections_SynchronizedRandomAccessList(List* list)
    : Collections_SynchronizedRandomAccessList(*static_cast< ::default_init_tag* >(0))
{
    ctor(list);
}

java::util::Collections_SynchronizedRandomAccessList::Collections_SynchronizedRandomAccessList(List* list, ::java::lang::Object* mutex)
    : Collections_SynchronizedRandomAccessList(*static_cast< ::default_init_tag* >(0))
{
    ctor(list, mutex);
}

constexpr int64_t java::util::Collections_SynchronizedRandomAccessList::serialVersionUID;

void ::java::util::Collections_SynchronizedRandomAccessList::ctor(List* list)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedRandomAccessList::ctor(List* list)");
}

void ::java::util::Collections_SynchronizedRandomAccessList::ctor(List* list, ::java::lang::Object* mutex)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedRandomAccessList::ctor(List* list, ::java::lang::Object* mutex)");
}

java::util::List* java::util::Collections_SynchronizedRandomAccessList::subList(int32_t fromIndex, int32_t toIndex)
{ /* stub */
    unimplemented_(u"java::util::List* java::util::Collections_SynchronizedRandomAccessList::subList(int32_t fromIndex, int32_t toIndex)");
    return 0;
}

/* private: java::lang::Object* java::util::Collections_SynchronizedRandomAccessList::writeReplace() */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_SynchronizedRandomAccessList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.SynchronizedRandomAccessList", 50);
    return c;
}

java::lang::Class* java::util::Collections_SynchronizedRandomAccessList::getClass0()
{
    return class_();
}


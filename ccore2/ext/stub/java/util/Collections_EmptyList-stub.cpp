// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_EmptyList.hpp>

#include <java/util/ListIterator.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_EmptyList::Collections_EmptyList(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

constexpr int64_t java::util::Collections_EmptyList::serialVersionUID;

/* private: void ::java::util::Collections_EmptyList::ctor() */
bool java::util::Collections_EmptyList::contains(::java::lang::Object* obj)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_EmptyList::contains(::java::lang::Object* obj)");
    return 0;
}

bool java::util::Collections_EmptyList::containsAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_EmptyList::containsAll(Collection* c)");
    return 0;
}

bool java::util::Collections_EmptyList::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_EmptyList::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::Collections_EmptyList::get(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_EmptyList::get(int32_t index)");
    return 0;
}

int32_t java::util::Collections_EmptyList::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_EmptyList::hashCode()");
    return 0;
}

bool java::util::Collections_EmptyList::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_EmptyList::isEmpty()");
    return 0;
}

java::util::Iterator* java::util::Collections_EmptyList::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Collections_EmptyList::iterator()");
    return 0;
}

java::util::ListIterator* java::util::Collections_EmptyList::listIterator()
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::Collections_EmptyList::listIterator()");
    return 0;
}

/* private: java::lang::Object* java::util::Collections_EmptyList::readResolve() */
int32_t java::util::Collections_EmptyList::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_EmptyList::size()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_EmptyList::toArray_()
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_EmptyList::toArray_()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_EmptyList::toArray_(::java::lang::ObjectArray* a)
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_EmptyList::toArray_(::java::lang::ObjectArray* a)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_EmptyList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.EmptyList", 31);
    return c;
}

java::util::ListIterator* java::util::Collections_EmptyList::listIterator(int32_t index)
{
    return super::listIterator(index);
}

java::lang::Class* java::util::Collections_EmptyList::getClass0()
{
    return class_();
}


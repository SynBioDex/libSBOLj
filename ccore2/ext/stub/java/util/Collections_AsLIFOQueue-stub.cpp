// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_AsLIFOQueue.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_AsLIFOQueue::Collections_AsLIFOQueue(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_AsLIFOQueue::Collections_AsLIFOQueue(Deque* q)
    : Collections_AsLIFOQueue(*static_cast< ::default_init_tag* >(0))
{
    ctor(q);
}

constexpr int64_t java::util::Collections_AsLIFOQueue::serialVersionUID;

void ::java::util::Collections_AsLIFOQueue::ctor(Deque* q)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_AsLIFOQueue::ctor(Deque* q)");
}

bool java::util::Collections_AsLIFOQueue::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_AsLIFOQueue::add(::java::lang::Object* e)");
    return 0;
}

void java::util::Collections_AsLIFOQueue::clear()
{ /* stub */
    unimplemented_(u"void java::util::Collections_AsLIFOQueue::clear()");
}

bool java::util::Collections_AsLIFOQueue::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_AsLIFOQueue::contains(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_AsLIFOQueue::containsAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_AsLIFOQueue::containsAll(Collection* c)");
    return 0;
}

java::lang::Object* java::util::Collections_AsLIFOQueue::element()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_AsLIFOQueue::element()");
    return 0;
}

bool java::util::Collections_AsLIFOQueue::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_AsLIFOQueue::isEmpty()");
    return 0;
}

java::util::Iterator* java::util::Collections_AsLIFOQueue::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Collections_AsLIFOQueue::iterator()");
    return 0;
}

bool java::util::Collections_AsLIFOQueue::offer(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_AsLIFOQueue::offer(::java::lang::Object* e)");
    return 0;
}

java::lang::Object* java::util::Collections_AsLIFOQueue::peek()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_AsLIFOQueue::peek()");
    return 0;
}

java::lang::Object* java::util::Collections_AsLIFOQueue::poll()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_AsLIFOQueue::poll()");
    return 0;
}

java::lang::Object* java::util::Collections_AsLIFOQueue::remove()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_AsLIFOQueue::remove()");
    return 0;
}

bool java::util::Collections_AsLIFOQueue::remove(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_AsLIFOQueue::remove(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_AsLIFOQueue::removeAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_AsLIFOQueue::removeAll(Collection* c)");
    return 0;
}

bool java::util::Collections_AsLIFOQueue::retainAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_AsLIFOQueue::retainAll(Collection* c)");
    return 0;
}

int32_t java::util::Collections_AsLIFOQueue::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_AsLIFOQueue::size()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_AsLIFOQueue::toArray_()
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_AsLIFOQueue::toArray_()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_AsLIFOQueue::toArray_(::java::lang::ObjectArray* a)
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_AsLIFOQueue::toArray_(::java::lang::ObjectArray* a)");
    return 0;
}

java::lang::String* java::util::Collections_AsLIFOQueue::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Collections_AsLIFOQueue::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_AsLIFOQueue::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.AsLIFOQueue", 33);
    return c;
}

bool java::util::Collections_AsLIFOQueue::addAll(Collection* c)
{
    return AbstractQueue::addAll(c);
}

bool java::util::Collections_AsLIFOQueue::equals(::java::lang::Object* o)
{
    return Object::equals(o);
}

int32_t java::util::Collections_AsLIFOQueue::hashCode()
{
    return Object::hashCode();
}

java::lang::Class* java::util::Collections_AsLIFOQueue::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/AbstractQueue.hpp>

#include <java/lang/ClassCastException.hpp>
#include <ObjectArray.hpp>

template<typename T, typename U>
static T java_cast(U* u)
{
    if(!u) return static_cast<T>(nullptr);
    auto t = dynamic_cast<T>(u);
    if(!t) throw new ::java::lang::ClassCastException();
    return t;
}

extern void unimplemented_(const char16_t* name);
java::util::AbstractQueue::AbstractQueue(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::AbstractQueue::AbstractQueue()
    : AbstractQueue(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::java::util::AbstractQueue::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::AbstractQueue::ctor()");
}

bool java::util::AbstractQueue::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractQueue::add(::java::lang::Object* e)");
    return 0;
}

bool java::util::AbstractQueue::addAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractQueue::addAll(Collection* c)");
    return 0;
}

void java::util::AbstractQueue::clear()
{ /* stub */
    unimplemented_(u"void java::util::AbstractQueue::clear()");
}

java::lang::Object* java::util::AbstractQueue::element()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::AbstractQueue::element()");
    return 0;
}

java::lang::Object* java::util::AbstractQueue::remove()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::AbstractQueue::remove()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::AbstractQueue::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.AbstractQueue", 23);
    return c;
}

bool java::util::AbstractQueue::contains(::java::lang::Object* o)
{
    return AbstractCollection::contains(o);
}

bool java::util::AbstractQueue::containsAll(Collection* c)
{
    return AbstractCollection::containsAll(c);
}

bool java::util::AbstractQueue::equals(::java::lang::Object* o)
{
    return Object::equals(o);
}

int32_t java::util::AbstractQueue::hashCode()
{
    return Object::hashCode();
}

bool java::util::AbstractQueue::isEmpty()
{
    return AbstractCollection::isEmpty();
}

bool java::util::AbstractQueue::remove(::java::lang::Object* o)
{
    return AbstractCollection::remove(o);
}

bool java::util::AbstractQueue::removeAll(Collection* c)
{
    return AbstractCollection::removeAll(c);
}

bool java::util::AbstractQueue::retainAll(Collection* c)
{
    return AbstractCollection::retainAll(c);
}

java::lang::ObjectArray* java::util::AbstractQueue::toArray_()
{
    return AbstractCollection::toArray_();
}

java::lang::ObjectArray* java::util::AbstractQueue::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(AbstractCollection::toArray_(a));
}

java::lang::Class* java::util::AbstractQueue::getClass0()
{
    return class_();
}


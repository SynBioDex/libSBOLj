// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/AbstractList.hpp>

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
java::util::AbstractList::AbstractList(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::AbstractList::AbstractList()
    : AbstractList(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::java::util::AbstractList::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::AbstractList::ctor()");
}

bool java::util::AbstractList::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractList::add(::java::lang::Object* e)");
    return 0;
}

void java::util::AbstractList::add(int32_t index, ::java::lang::Object* element)
{ /* stub */
    unimplemented_(u"void java::util::AbstractList::add(int32_t index, ::java::lang::Object* element)");
}

bool java::util::AbstractList::addAll(int32_t index, Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractList::addAll(int32_t index, Collection* c)");
    return 0;
}

void java::util::AbstractList::clear()
{ /* stub */
    unimplemented_(u"void java::util::AbstractList::clear()");
}

bool java::util::AbstractList::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractList::equals(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::AbstractList::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::AbstractList::hashCode()");
    return 0;
}

int32_t java::util::AbstractList::indexOf(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::AbstractList::indexOf(::java::lang::Object* o)");
    return 0;
}

java::util::Iterator* java::util::AbstractList::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::AbstractList::iterator()");
    return 0;
}

int32_t java::util::AbstractList::lastIndexOf(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::AbstractList::lastIndexOf(::java::lang::Object* o)");
    return 0;
}

java::util::ListIterator* java::util::AbstractList::listIterator()
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::AbstractList::listIterator()");
    return 0;
}

java::util::ListIterator* java::util::AbstractList::listIterator(int32_t index)
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::AbstractList::listIterator(int32_t index)");
    return 0;
}

/* private: java::lang::String* java::util::AbstractList::outOfBoundsMsg(int32_t index) */
/* private: void java::util::AbstractList::rangeCheckForAdd(int32_t index) */
java::lang::Object* java::util::AbstractList::remove(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::AbstractList::remove(int32_t index)");
    return 0;
}

void java::util::AbstractList::removeRange(int32_t fromIndex, int32_t toIndex)
{ /* stub */
    unimplemented_(u"void java::util::AbstractList::removeRange(int32_t fromIndex, int32_t toIndex)");
}

java::lang::Object* java::util::AbstractList::set(int32_t index, ::java::lang::Object* element)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::AbstractList::set(int32_t index, ::java::lang::Object* element)");
    return 0;
}

java::util::List* java::util::AbstractList::subList(int32_t fromIndex, int32_t toIndex)
{ /* stub */
    unimplemented_(u"java::util::List* java::util::AbstractList::subList(int32_t fromIndex, int32_t toIndex)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::AbstractList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.AbstractList", 22);
    return c;
}

bool java::util::AbstractList::addAll(Collection* c)
{
    return AbstractCollection::addAll(c);
}

bool java::util::AbstractList::contains(::java::lang::Object* o)
{
    return AbstractCollection::contains(o);
}

bool java::util::AbstractList::containsAll(Collection* c)
{
    return AbstractCollection::containsAll(c);
}

bool java::util::AbstractList::isEmpty()
{
    return AbstractCollection::isEmpty();
}

bool java::util::AbstractList::remove(::java::lang::Object* o)
{
    return AbstractCollection::remove(o);
}

bool java::util::AbstractList::removeAll(Collection* c)
{
    return AbstractCollection::removeAll(c);
}

bool java::util::AbstractList::retainAll(Collection* c)
{
    return AbstractCollection::retainAll(c);
}

java::lang::ObjectArray* java::util::AbstractList::toArray_()
{
    return AbstractCollection::toArray_();
}

java::lang::ObjectArray* java::util::AbstractList::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(AbstractCollection::toArray_(a));
}

java::lang::Class* java::util::AbstractList::getClass0()
{
    return class_();
}


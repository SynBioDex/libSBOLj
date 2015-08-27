// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_SynchronizedList.hpp>

#include <java/lang/ClassCastException.hpp>
#include <java/util/Iterator.hpp>
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
java::util::Collections_SynchronizedList::Collections_SynchronizedList(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_SynchronizedList::Collections_SynchronizedList(List* list)
    : Collections_SynchronizedList(*static_cast< ::default_init_tag* >(0))
{
    ctor(list);
}

java::util::Collections_SynchronizedList::Collections_SynchronizedList(List* list, ::java::lang::Object* mutex)
    : Collections_SynchronizedList(*static_cast< ::default_init_tag* >(0))
{
    ctor(list, mutex);
}

constexpr int64_t java::util::Collections_SynchronizedList::serialVersionUID;

void ::java::util::Collections_SynchronizedList::ctor(List* list)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedList::ctor(List* list)");
}

void ::java::util::Collections_SynchronizedList::ctor(List* list, ::java::lang::Object* mutex)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedList::ctor(List* list, ::java::lang::Object* mutex)");
}

void java::util::Collections_SynchronizedList::add(int32_t index, ::java::lang::Object* element)
{ /* stub */
    unimplemented_(u"void java::util::Collections_SynchronizedList::add(int32_t index, ::java::lang::Object* element)");
}

bool java::util::Collections_SynchronizedList::addAll(int32_t index, Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedList::addAll(int32_t index, Collection* c)");
    return 0;
}

bool java::util::Collections_SynchronizedList::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedList::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::Collections_SynchronizedList::get(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedList::get(int32_t index)");
    return 0;
}

int32_t java::util::Collections_SynchronizedList::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SynchronizedList::hashCode()");
    return 0;
}

int32_t java::util::Collections_SynchronizedList::indexOf(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SynchronizedList::indexOf(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::Collections_SynchronizedList::lastIndexOf(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SynchronizedList::lastIndexOf(::java::lang::Object* o)");
    return 0;
}

java::util::ListIterator* java::util::Collections_SynchronizedList::listIterator()
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::Collections_SynchronizedList::listIterator()");
    return 0;
}

java::util::ListIterator* java::util::Collections_SynchronizedList::listIterator(int32_t index)
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::Collections_SynchronizedList::listIterator(int32_t index)");
    return 0;
}

/* private: java::lang::Object* java::util::Collections_SynchronizedList::readResolve() */
java::lang::Object* java::util::Collections_SynchronizedList::remove(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedList::remove(int32_t index)");
    return 0;
}

java::lang::Object* java::util::Collections_SynchronizedList::set(int32_t index, ::java::lang::Object* element)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedList::set(int32_t index, ::java::lang::Object* element)");
    return 0;
}

java::util::List* java::util::Collections_SynchronizedList::subList(int32_t fromIndex, int32_t toIndex)
{ /* stub */
    unimplemented_(u"java::util::List* java::util::Collections_SynchronizedList::subList(int32_t fromIndex, int32_t toIndex)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_SynchronizedList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.SynchronizedList", 38);
    return c;
}

bool java::util::Collections_SynchronizedList::add(::java::lang::Object* e)
{
    return Collections_SynchronizedCollection::add(e);
}

bool java::util::Collections_SynchronizedList::addAll(Collection* c)
{
    return Collections_SynchronizedCollection::addAll(c);
}

void java::util::Collections_SynchronizedList::clear()
{
    Collections_SynchronizedCollection::clear();
}

bool java::util::Collections_SynchronizedList::contains(::java::lang::Object* o)
{
    return Collections_SynchronizedCollection::contains(o);
}

bool java::util::Collections_SynchronizedList::containsAll(Collection* c)
{
    return Collections_SynchronizedCollection::containsAll(c);
}

bool java::util::Collections_SynchronizedList::isEmpty()
{
    return Collections_SynchronizedCollection::isEmpty();
}

java::util::Iterator* java::util::Collections_SynchronizedList::iterator()
{
    return java_cast< Iterator* >(Collections_SynchronizedCollection::iterator());
}

bool java::util::Collections_SynchronizedList::remove(::java::lang::Object* o)
{
    return Collections_SynchronizedCollection::remove(o);
}

bool java::util::Collections_SynchronizedList::removeAll(Collection* c)
{
    return Collections_SynchronizedCollection::removeAll(c);
}

bool java::util::Collections_SynchronizedList::retainAll(Collection* c)
{
    return Collections_SynchronizedCollection::retainAll(c);
}

int32_t java::util::Collections_SynchronizedList::size()
{
    return Collections_SynchronizedCollection::size();
}

java::lang::ObjectArray* java::util::Collections_SynchronizedList::toArray_()
{
    return Collections_SynchronizedCollection::toArray_();
}

java::lang::ObjectArray* java::util::Collections_SynchronizedList::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(Collections_SynchronizedCollection::toArray_(a));
}

java::lang::Class* java::util::Collections_SynchronizedList::getClass0()
{
    return class_();
}


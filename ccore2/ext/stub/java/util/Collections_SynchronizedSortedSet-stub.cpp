// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_SynchronizedSortedSet.hpp>

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
java::util::Collections_SynchronizedSortedSet::Collections_SynchronizedSortedSet(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_SynchronizedSortedSet::Collections_SynchronizedSortedSet(SortedSet* s)
    : Collections_SynchronizedSortedSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}

java::util::Collections_SynchronizedSortedSet::Collections_SynchronizedSortedSet(SortedSet* s, ::java::lang::Object* mutex)
    : Collections_SynchronizedSortedSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(s, mutex);
}

constexpr int64_t java::util::Collections_SynchronizedSortedSet::serialVersionUID;

void ::java::util::Collections_SynchronizedSortedSet::ctor(SortedSet* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedSortedSet::ctor(SortedSet* s)");
}

void ::java::util::Collections_SynchronizedSortedSet::ctor(SortedSet* s, ::java::lang::Object* mutex)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedSortedSet::ctor(SortedSet* s, ::java::lang::Object* mutex)");
}

java::util::Comparator* java::util::Collections_SynchronizedSortedSet::comparator()
{ /* stub */
    unimplemented_(u"java::util::Comparator* java::util::Collections_SynchronizedSortedSet::comparator()");
    return 0;
}

java::lang::Object* java::util::Collections_SynchronizedSortedSet::first()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedSortedSet::first()");
    return 0;
}

java::util::SortedSet* java::util::Collections_SynchronizedSortedSet::headSet(::java::lang::Object* toElement)
{ /* stub */
    unimplemented_(u"java::util::SortedSet* java::util::Collections_SynchronizedSortedSet::headSet(::java::lang::Object* toElement)");
    return 0;
}

java::lang::Object* java::util::Collections_SynchronizedSortedSet::last()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedSortedSet::last()");
    return 0;
}

java::util::SortedSet* java::util::Collections_SynchronizedSortedSet::subSet(::java::lang::Object* fromElement, ::java::lang::Object* toElement)
{ /* stub */
    unimplemented_(u"java::util::SortedSet* java::util::Collections_SynchronizedSortedSet::subSet(::java::lang::Object* fromElement, ::java::lang::Object* toElement)");
    return 0;
}

java::util::SortedSet* java::util::Collections_SynchronizedSortedSet::tailSet(::java::lang::Object* fromElement)
{ /* stub */
    unimplemented_(u"java::util::SortedSet* java::util::Collections_SynchronizedSortedSet::tailSet(::java::lang::Object* fromElement)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_SynchronizedSortedSet::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.SynchronizedSortedSet", 43);
    return c;
}

bool java::util::Collections_SynchronizedSortedSet::add(::java::lang::Object* e)
{
    return Collections_SynchronizedCollection::add(e);
}

bool java::util::Collections_SynchronizedSortedSet::addAll(Collection* c)
{
    return Collections_SynchronizedCollection::addAll(c);
}

void java::util::Collections_SynchronizedSortedSet::clear()
{
    Collections_SynchronizedCollection::clear();
}

bool java::util::Collections_SynchronizedSortedSet::contains(::java::lang::Object* o)
{
    return Collections_SynchronizedCollection::contains(o);
}

bool java::util::Collections_SynchronizedSortedSet::containsAll(Collection* c)
{
    return Collections_SynchronizedCollection::containsAll(c);
}

bool java::util::Collections_SynchronizedSortedSet::equals(::java::lang::Object* o)
{
    return Collections_SynchronizedSet::equals(o);
}

int32_t java::util::Collections_SynchronizedSortedSet::hashCode()
{
    return Collections_SynchronizedSet::hashCode();
}

bool java::util::Collections_SynchronizedSortedSet::isEmpty()
{
    return Collections_SynchronizedCollection::isEmpty();
}

java::util::Iterator* java::util::Collections_SynchronizedSortedSet::iterator()
{
    return java_cast< Iterator* >(Collections_SynchronizedCollection::iterator());
}

bool java::util::Collections_SynchronizedSortedSet::remove(::java::lang::Object* o)
{
    return Collections_SynchronizedCollection::remove(o);
}

bool java::util::Collections_SynchronizedSortedSet::removeAll(Collection* c)
{
    return Collections_SynchronizedCollection::removeAll(c);
}

bool java::util::Collections_SynchronizedSortedSet::retainAll(Collection* c)
{
    return Collections_SynchronizedCollection::retainAll(c);
}

int32_t java::util::Collections_SynchronizedSortedSet::size()
{
    return Collections_SynchronizedCollection::size();
}

java::lang::ObjectArray* java::util::Collections_SynchronizedSortedSet::toArray_()
{
    return Collections_SynchronizedCollection::toArray_();
}

java::lang::ObjectArray* java::util::Collections_SynchronizedSortedSet::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(Collections_SynchronizedCollection::toArray_(a));
}

java::lang::Class* java::util::Collections_SynchronizedSortedSet::getClass0()
{
    return class_();
}


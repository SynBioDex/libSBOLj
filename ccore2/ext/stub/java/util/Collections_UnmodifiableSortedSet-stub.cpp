// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_UnmodifiableSortedSet.hpp>

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
java::util::Collections_UnmodifiableSortedSet::Collections_UnmodifiableSortedSet(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_UnmodifiableSortedSet::Collections_UnmodifiableSortedSet(SortedSet* s)
    : Collections_UnmodifiableSortedSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}

constexpr int64_t java::util::Collections_UnmodifiableSortedSet::serialVersionUID;

void ::java::util::Collections_UnmodifiableSortedSet::ctor(SortedSet* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_UnmodifiableSortedSet::ctor(SortedSet* s)");
}

java::util::Comparator* java::util::Collections_UnmodifiableSortedSet::comparator()
{ /* stub */
    unimplemented_(u"java::util::Comparator* java::util::Collections_UnmodifiableSortedSet::comparator()");
    return 0;
}

java::lang::Object* java::util::Collections_UnmodifiableSortedSet::first()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_UnmodifiableSortedSet::first()");
    return 0;
}

java::util::SortedSet* java::util::Collections_UnmodifiableSortedSet::headSet(::java::lang::Object* toElement)
{ /* stub */
    unimplemented_(u"java::util::SortedSet* java::util::Collections_UnmodifiableSortedSet::headSet(::java::lang::Object* toElement)");
    return 0;
}

java::lang::Object* java::util::Collections_UnmodifiableSortedSet::last()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_UnmodifiableSortedSet::last()");
    return 0;
}

java::util::SortedSet* java::util::Collections_UnmodifiableSortedSet::subSet(::java::lang::Object* fromElement, ::java::lang::Object* toElement)
{ /* stub */
    unimplemented_(u"java::util::SortedSet* java::util::Collections_UnmodifiableSortedSet::subSet(::java::lang::Object* fromElement, ::java::lang::Object* toElement)");
    return 0;
}

java::util::SortedSet* java::util::Collections_UnmodifiableSortedSet::tailSet(::java::lang::Object* fromElement)
{ /* stub */
    unimplemented_(u"java::util::SortedSet* java::util::Collections_UnmodifiableSortedSet::tailSet(::java::lang::Object* fromElement)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_UnmodifiableSortedSet::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.UnmodifiableSortedSet", 43);
    return c;
}

bool java::util::Collections_UnmodifiableSortedSet::add(::java::lang::Object* e)
{
    return Collections_UnmodifiableCollection::add(e);
}

bool java::util::Collections_UnmodifiableSortedSet::addAll(Collection* c)
{
    return Collections_UnmodifiableCollection::addAll(c);
}

void java::util::Collections_UnmodifiableSortedSet::clear()
{
    Collections_UnmodifiableCollection::clear();
}

bool java::util::Collections_UnmodifiableSortedSet::contains(::java::lang::Object* o)
{
    return Collections_UnmodifiableCollection::contains(o);
}

bool java::util::Collections_UnmodifiableSortedSet::containsAll(Collection* c)
{
    return Collections_UnmodifiableCollection::containsAll(c);
}

bool java::util::Collections_UnmodifiableSortedSet::equals(::java::lang::Object* o)
{
    return Collections_UnmodifiableSet::equals(o);
}

int32_t java::util::Collections_UnmodifiableSortedSet::hashCode()
{
    return Collections_UnmodifiableSet::hashCode();
}

bool java::util::Collections_UnmodifiableSortedSet::isEmpty()
{
    return Collections_UnmodifiableCollection::isEmpty();
}

java::util::Iterator* java::util::Collections_UnmodifiableSortedSet::iterator()
{
    return java_cast< Iterator* >(Collections_UnmodifiableCollection::iterator());
}

bool java::util::Collections_UnmodifiableSortedSet::remove(::java::lang::Object* o)
{
    return Collections_UnmodifiableCollection::remove(o);
}

bool java::util::Collections_UnmodifiableSortedSet::removeAll(Collection* c)
{
    return Collections_UnmodifiableCollection::removeAll(c);
}

bool java::util::Collections_UnmodifiableSortedSet::retainAll(Collection* c)
{
    return Collections_UnmodifiableCollection::retainAll(c);
}

int32_t java::util::Collections_UnmodifiableSortedSet::size()
{
    return Collections_UnmodifiableCollection::size();
}

java::lang::ObjectArray* java::util::Collections_UnmodifiableSortedSet::toArray_()
{
    return Collections_UnmodifiableCollection::toArray_();
}

java::lang::ObjectArray* java::util::Collections_UnmodifiableSortedSet::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(Collections_UnmodifiableCollection::toArray_(a));
}

java::lang::Class* java::util::Collections_UnmodifiableSortedSet::getClass0()
{
    return class_();
}


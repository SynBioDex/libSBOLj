// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_CheckedSet.hpp>

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
java::util::Collections_CheckedSet::Collections_CheckedSet(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_CheckedSet::Collections_CheckedSet(Set* s, ::java::lang::Class* elementType)
    : Collections_CheckedSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(s, elementType);
}

constexpr int64_t java::util::Collections_CheckedSet::serialVersionUID;

void ::java::util::Collections_CheckedSet::ctor(Set* s, ::java::lang::Class* elementType)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_CheckedSet::ctor(Set* s, ::java::lang::Class* elementType)");
}

bool java::util::Collections_CheckedSet::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedSet::equals(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::Collections_CheckedSet::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_CheckedSet::hashCode()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_CheckedSet::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.CheckedSet", 32);
    return c;
}

bool java::util::Collections_CheckedSet::add(::java::lang::Object* e)
{
    return Collections_CheckedCollection::add(e);
}

bool java::util::Collections_CheckedSet::addAll(Collection* c)
{
    return Collections_CheckedCollection::addAll(c);
}

void java::util::Collections_CheckedSet::clear()
{
    Collections_CheckedCollection::clear();
}

bool java::util::Collections_CheckedSet::contains(::java::lang::Object* o)
{
    return Collections_CheckedCollection::contains(o);
}

bool java::util::Collections_CheckedSet::containsAll(Collection* c)
{
    return Collections_CheckedCollection::containsAll(c);
}

bool java::util::Collections_CheckedSet::isEmpty()
{
    return Collections_CheckedCollection::isEmpty();
}

java::util::Iterator* java::util::Collections_CheckedSet::iterator()
{
    return java_cast< Iterator* >(Collections_CheckedCollection::iterator());
}

bool java::util::Collections_CheckedSet::remove(::java::lang::Object* o)
{
    return Collections_CheckedCollection::remove(o);
}

bool java::util::Collections_CheckedSet::removeAll(Collection* c)
{
    return Collections_CheckedCollection::removeAll(c);
}

bool java::util::Collections_CheckedSet::retainAll(Collection* c)
{
    return Collections_CheckedCollection::retainAll(c);
}

int32_t java::util::Collections_CheckedSet::size()
{
    return Collections_CheckedCollection::size();
}

java::lang::ObjectArray* java::util::Collections_CheckedSet::toArray_()
{
    return Collections_CheckedCollection::toArray_();
}

java::lang::ObjectArray* java::util::Collections_CheckedSet::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(Collections_CheckedCollection::toArray_(a));
}

java::lang::Class* java::util::Collections_CheckedSet::getClass0()
{
    return class_();
}


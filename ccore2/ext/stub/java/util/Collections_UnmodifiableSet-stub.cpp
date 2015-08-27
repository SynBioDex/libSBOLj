// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_UnmodifiableSet.hpp>

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
java::util::Collections_UnmodifiableSet::Collections_UnmodifiableSet(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_UnmodifiableSet::Collections_UnmodifiableSet(Set* s)
    : Collections_UnmodifiableSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}

constexpr int64_t java::util::Collections_UnmodifiableSet::serialVersionUID;

void ::java::util::Collections_UnmodifiableSet::ctor(Set* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_UnmodifiableSet::ctor(Set* s)");
}

bool java::util::Collections_UnmodifiableSet::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableSet::equals(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::Collections_UnmodifiableSet::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_UnmodifiableSet::hashCode()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_UnmodifiableSet::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.UnmodifiableSet", 37);
    return c;
}

bool java::util::Collections_UnmodifiableSet::add(::java::lang::Object* e)
{
    return Collections_UnmodifiableCollection::add(e);
}

bool java::util::Collections_UnmodifiableSet::addAll(Collection* c)
{
    return Collections_UnmodifiableCollection::addAll(c);
}

void java::util::Collections_UnmodifiableSet::clear()
{
    Collections_UnmodifiableCollection::clear();
}

bool java::util::Collections_UnmodifiableSet::contains(::java::lang::Object* o)
{
    return Collections_UnmodifiableCollection::contains(o);
}

bool java::util::Collections_UnmodifiableSet::containsAll(Collection* c)
{
    return Collections_UnmodifiableCollection::containsAll(c);
}

bool java::util::Collections_UnmodifiableSet::isEmpty()
{
    return Collections_UnmodifiableCollection::isEmpty();
}

java::util::Iterator* java::util::Collections_UnmodifiableSet::iterator()
{
    return java_cast< Iterator* >(Collections_UnmodifiableCollection::iterator());
}

bool java::util::Collections_UnmodifiableSet::remove(::java::lang::Object* o)
{
    return Collections_UnmodifiableCollection::remove(o);
}

bool java::util::Collections_UnmodifiableSet::removeAll(Collection* c)
{
    return Collections_UnmodifiableCollection::removeAll(c);
}

bool java::util::Collections_UnmodifiableSet::retainAll(Collection* c)
{
    return Collections_UnmodifiableCollection::retainAll(c);
}

int32_t java::util::Collections_UnmodifiableSet::size()
{
    return Collections_UnmodifiableCollection::size();
}

java::lang::ObjectArray* java::util::Collections_UnmodifiableSet::toArray_()
{
    return Collections_UnmodifiableCollection::toArray_();
}

java::lang::ObjectArray* java::util::Collections_UnmodifiableSet::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(Collections_UnmodifiableCollection::toArray_(a));
}

java::lang::Class* java::util::Collections_UnmodifiableSet::getClass0()
{
    return class_();
}


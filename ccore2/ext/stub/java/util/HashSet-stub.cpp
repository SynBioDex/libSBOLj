// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/HashSet.hpp>

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
java::util::HashSet::HashSet(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::HashSet::HashSet()
    : HashSet(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::util::HashSet::HashSet(Collection* c)
    : HashSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(c);
}

java::util::HashSet::HashSet(int32_t initialCapacity)
    : HashSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(initialCapacity);
}

java::util::HashSet::HashSet(int32_t initialCapacity, float loadFactor)
    : HashSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(initialCapacity, loadFactor);
}

java::util::HashSet::HashSet(int32_t initialCapacity, float loadFactor, bool dummy)
    : HashSet(*static_cast< ::default_init_tag* >(0))
{
    ctor(initialCapacity, loadFactor, dummy);
}

java::lang::Object*& java::util::HashSet::PRESENT()
{
    clinit();
    return PRESENT_;
}
java::lang::Object* java::util::HashSet::PRESENT_;
constexpr int64_t java::util::HashSet::serialVersionUID;

void ::java::util::HashSet::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashSet::ctor()");
}

void ::java::util::HashSet::ctor(Collection* c)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashSet::ctor(Collection* c)");
}

void ::java::util::HashSet::ctor(int32_t initialCapacity)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashSet::ctor(int32_t initialCapacity)");
}

void ::java::util::HashSet::ctor(int32_t initialCapacity, float loadFactor)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashSet::ctor(int32_t initialCapacity, float loadFactor)");
}

void ::java::util::HashSet::ctor(int32_t initialCapacity, float loadFactor, bool dummy)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashSet::ctor(int32_t initialCapacity, float loadFactor, bool dummy)");
}

bool java::util::HashSet::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::HashSet::add(::java::lang::Object* e)");
    return 0;
}

void java::util::HashSet::clear()
{ /* stub */
    unimplemented_(u"void java::util::HashSet::clear()");
}

java::lang::Object* java::util::HashSet::clone()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::HashSet::clone()");
    return 0;
}

bool java::util::HashSet::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::HashSet::contains(::java::lang::Object* o)");
    return 0;
}

bool java::util::HashSet::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::HashSet::isEmpty()");
    return 0;
}

java::util::Iterator* java::util::HashSet::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::HashSet::iterator()");
    return 0;
}

/* private: void java::util::HashSet::readObject(::java::io::ObjectInputStream* s) */
bool java::util::HashSet::remove(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::HashSet::remove(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::HashSet::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::HashSet::size()");
    return 0;
}

/* private: void java::util::HashSet::writeObject(::java::io::ObjectOutputStream* s) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::HashSet::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.HashSet", 17);
    return c;
}

bool java::util::HashSet::addAll(Collection* c)
{
    return AbstractCollection::addAll(c);
}

bool java::util::HashSet::containsAll(Collection* c)
{
    return AbstractCollection::containsAll(c);
}

bool java::util::HashSet::equals(::java::lang::Object* o)
{
    return AbstractSet::equals(o);
}

int32_t java::util::HashSet::hashCode()
{
    return AbstractSet::hashCode();
}

bool java::util::HashSet::removeAll(Collection* c)
{
    return AbstractSet::removeAll(c);
}

bool java::util::HashSet::retainAll(Collection* c)
{
    return AbstractCollection::retainAll(c);
}

java::lang::ObjectArray* java::util::HashSet::toArray_()
{
    return AbstractCollection::toArray_();
}

java::lang::ObjectArray* java::util::HashSet::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(AbstractCollection::toArray_(a));
}

java::lang::Class* java::util::HashSet::getClass0()
{
    return class_();
}


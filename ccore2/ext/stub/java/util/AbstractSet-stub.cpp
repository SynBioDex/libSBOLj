// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/AbstractSet.hpp>

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
java::util::AbstractSet::AbstractSet(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::AbstractSet::AbstractSet()
    : AbstractSet(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::java::util::AbstractSet::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::AbstractSet::ctor()");
}

bool java::util::AbstractSet::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractSet::equals(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::AbstractSet::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::AbstractSet::hashCode()");
    return 0;
}

bool java::util::AbstractSet::removeAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractSet::removeAll(Collection* c)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::AbstractSet::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.AbstractSet", 21);
    return c;
}

bool java::util::AbstractSet::add(::java::lang::Object* e)
{
    return AbstractCollection::add(e);
}

bool java::util::AbstractSet::addAll(Collection* c)
{
    return AbstractCollection::addAll(c);
}

void java::util::AbstractSet::clear()
{
    AbstractCollection::clear();
}

bool java::util::AbstractSet::contains(::java::lang::Object* o)
{
    return AbstractCollection::contains(o);
}

bool java::util::AbstractSet::containsAll(Collection* c)
{
    return AbstractCollection::containsAll(c);
}

bool java::util::AbstractSet::isEmpty()
{
    return AbstractCollection::isEmpty();
}

bool java::util::AbstractSet::remove(::java::lang::Object* o)
{
    return AbstractCollection::remove(o);
}

bool java::util::AbstractSet::retainAll(Collection* c)
{
    return AbstractCollection::retainAll(c);
}

java::lang::ObjectArray* java::util::AbstractSet::toArray_()
{
    return AbstractCollection::toArray_();
}

java::lang::ObjectArray* java::util::AbstractSet::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(AbstractCollection::toArray_(a));
}

java::lang::Class* java::util::AbstractSet::getClass0()
{
    return class_();
}


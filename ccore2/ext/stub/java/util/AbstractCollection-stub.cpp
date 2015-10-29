// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/AbstractCollection.hpp>

extern void unimplemented_(const char16_t* name);
java::util::AbstractCollection::AbstractCollection(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::AbstractCollection::AbstractCollection()
    : AbstractCollection(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

constexpr int32_t java::util::AbstractCollection::MAX_ARRAY_SIZE;

void ::java::util::AbstractCollection::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::AbstractCollection::ctor()");
}

bool java::util::AbstractCollection::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractCollection::add(::java::lang::Object* e)");
    return 0;
}

bool java::util::AbstractCollection::addAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractCollection::addAll(Collection* c)");
    return 0;
}

void java::util::AbstractCollection::clear()
{ /* stub */
    unimplemented_(u"void java::util::AbstractCollection::clear()");
}

bool java::util::AbstractCollection::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractCollection::contains(::java::lang::Object* o)");
    return 0;
}

bool java::util::AbstractCollection::containsAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractCollection::containsAll(Collection* c)");
    return 0;
}

/* private: java::lang::ObjectArray* java::util::AbstractCollection::finishToArray_(::java::lang::ObjectArray* r, Iterator* it) */
/* private: int32_t java::util::AbstractCollection::hugeCapacity(int32_t minCapacity) */
bool java::util::AbstractCollection::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::AbstractCollection::isEmpty()");
    return 0;
}

bool java::util::AbstractCollection::remove(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractCollection::remove(::java::lang::Object* o)");
    return 0;
}

bool java::util::AbstractCollection::removeAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractCollection::removeAll(Collection* c)");
    return 0;
}

bool java::util::AbstractCollection::retainAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractCollection::retainAll(Collection* c)");
    return 0;
}

java::lang::ObjectArray* java::util::AbstractCollection::toArray_()
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::AbstractCollection::toArray_()");
    return 0;
}

java::lang::ObjectArray* java::util::AbstractCollection::toArray_(::java::lang::ObjectArray* a)
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::AbstractCollection::toArray_(::java::lang::ObjectArray* a)");
    return 0;
}

java::lang::String* java::util::AbstractCollection::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::AbstractCollection::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::AbstractCollection::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.AbstractCollection", 28);
    return c;
}

bool java::util::AbstractCollection::equals(::java::lang::Object* o)
{
    return Object::equals(o);
}

int32_t java::util::AbstractCollection::hashCode()
{
    return Object::hashCode();
}

java::lang::Class* java::util::AbstractCollection::getClass0()
{
    return class_();
}


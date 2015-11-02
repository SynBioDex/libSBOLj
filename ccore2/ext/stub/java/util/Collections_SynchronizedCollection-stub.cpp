// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_SynchronizedCollection.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_SynchronizedCollection::Collections_SynchronizedCollection(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_SynchronizedCollection::Collections_SynchronizedCollection(Collection* c)
    : Collections_SynchronizedCollection(*static_cast< ::default_init_tag* >(0))
{
    ctor(c);
}

java::util::Collections_SynchronizedCollection::Collections_SynchronizedCollection(Collection* c, ::java::lang::Object* mutex)
    : Collections_SynchronizedCollection(*static_cast< ::default_init_tag* >(0))
{
    ctor(c, mutex);
}

constexpr int64_t java::util::Collections_SynchronizedCollection::serialVersionUID;

void ::java::util::Collections_SynchronizedCollection::ctor(Collection* c)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedCollection::ctor(Collection* c)");
}

void ::java::util::Collections_SynchronizedCollection::ctor(Collection* c, ::java::lang::Object* mutex)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedCollection::ctor(Collection* c, ::java::lang::Object* mutex)");
}

bool java::util::Collections_SynchronizedCollection::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedCollection::add(::java::lang::Object* e)");
    return 0;
}

bool java::util::Collections_SynchronizedCollection::addAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedCollection::addAll(Collection* coll)");
    return 0;
}

void java::util::Collections_SynchronizedCollection::clear()
{ /* stub */
    unimplemented_(u"void java::util::Collections_SynchronizedCollection::clear()");
}

bool java::util::Collections_SynchronizedCollection::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedCollection::contains(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_SynchronizedCollection::containsAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedCollection::containsAll(Collection* coll)");
    return 0;
}

bool java::util::Collections_SynchronizedCollection::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedCollection::isEmpty()");
    return 0;
}

java::util::Iterator* java::util::Collections_SynchronizedCollection::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Collections_SynchronizedCollection::iterator()");
    return 0;
}

bool java::util::Collections_SynchronizedCollection::remove(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedCollection::remove(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_SynchronizedCollection::removeAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedCollection::removeAll(Collection* coll)");
    return 0;
}

bool java::util::Collections_SynchronizedCollection::retainAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedCollection::retainAll(Collection* coll)");
    return 0;
}

int32_t java::util::Collections_SynchronizedCollection::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SynchronizedCollection::size()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_SynchronizedCollection::toArray_()
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_SynchronizedCollection::toArray_()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_SynchronizedCollection::toArray_(::java::lang::ObjectArray* a)
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_SynchronizedCollection::toArray_(::java::lang::ObjectArray* a)");
    return 0;
}

java::lang::String* java::util::Collections_SynchronizedCollection::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Collections_SynchronizedCollection::toString()");
    return 0;
}

/* private: void java::util::Collections_SynchronizedCollection::writeObject(::java::io::ObjectOutputStream* s) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_SynchronizedCollection::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.SynchronizedCollection", 44);
    return c;
}

bool java::util::Collections_SynchronizedCollection::equals(::java::lang::Object* o)
{
    return Object::equals(o);
}

int32_t java::util::Collections_SynchronizedCollection::hashCode()
{
    return Object::hashCode();
}

java::lang::Class* java::util::Collections_SynchronizedCollection::getClass0()
{
    return class_();
}


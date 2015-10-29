// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_SetFromMap.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_SetFromMap::Collections_SetFromMap(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_SetFromMap::Collections_SetFromMap(Map* map)
    : Collections_SetFromMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(map);
}

constexpr int64_t java::util::Collections_SetFromMap::serialVersionUID;

void ::java::util::Collections_SetFromMap::ctor(Map* map)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SetFromMap::ctor(Map* map)");
}

bool java::util::Collections_SetFromMap::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SetFromMap::add(::java::lang::Object* e)");
    return 0;
}

void java::util::Collections_SetFromMap::clear()
{ /* stub */
    unimplemented_(u"void java::util::Collections_SetFromMap::clear()");
}

bool java::util::Collections_SetFromMap::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SetFromMap::contains(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_SetFromMap::containsAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SetFromMap::containsAll(Collection* c)");
    return 0;
}

bool java::util::Collections_SetFromMap::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SetFromMap::equals(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::Collections_SetFromMap::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SetFromMap::hashCode()");
    return 0;
}

bool java::util::Collections_SetFromMap::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SetFromMap::isEmpty()");
    return 0;
}

java::util::Iterator* java::util::Collections_SetFromMap::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Collections_SetFromMap::iterator()");
    return 0;
}

/* private: void java::util::Collections_SetFromMap::readObject(::java::io::ObjectInputStream* stream) */
bool java::util::Collections_SetFromMap::remove(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SetFromMap::remove(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_SetFromMap::removeAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SetFromMap::removeAll(Collection* c)");
    return 0;
}

bool java::util::Collections_SetFromMap::retainAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SetFromMap::retainAll(Collection* c)");
    return 0;
}

int32_t java::util::Collections_SetFromMap::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SetFromMap::size()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_SetFromMap::toArray_()
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_SetFromMap::toArray_()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_SetFromMap::toArray_(::java::lang::ObjectArray* a)
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_SetFromMap::toArray_(::java::lang::ObjectArray* a)");
    return 0;
}

java::lang::String* java::util::Collections_SetFromMap::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Collections_SetFromMap::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_SetFromMap::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.SetFromMap", 32);
    return c;
}

bool java::util::Collections_SetFromMap::addAll(Collection* c)
{
    return AbstractCollection::addAll(c);
}

java::lang::Class* java::util::Collections_SetFromMap::getClass0()
{
    return class_();
}


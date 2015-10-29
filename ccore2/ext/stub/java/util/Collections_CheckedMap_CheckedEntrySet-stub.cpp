// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_CheckedMap_CheckedEntrySet.hpp>

#include <java/util/Map_Entry.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_CheckedMap_CheckedEntrySet::Collections_CheckedMap_CheckedEntrySet(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_CheckedMap_CheckedEntrySet::Collections_CheckedMap_CheckedEntrySet(Set* s, ::java::lang::Class* valueType)
    : Collections_CheckedMap_CheckedEntrySet(*static_cast< ::default_init_tag* >(0))
{
    ctor(s, valueType);
}


void ::java::util::Collections_CheckedMap_CheckedEntrySet::ctor(Set* s, ::java::lang::Class* valueType)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_CheckedMap_CheckedEntrySet::ctor(Set* s, ::java::lang::Class* valueType)");
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::add(Map_Entry* e)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedMap_CheckedEntrySet::add(Map_Entry* e)");
    return 0;
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::add(::java::lang::Object* e)
{ 
    return add(dynamic_cast< Map_Entry* >(e));
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::addAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedMap_CheckedEntrySet::addAll(Collection* coll)");
    return 0;
}

/* private: bool java::util::Collections_CheckedMap_CheckedEntrySet::batchRemove(Collection* c, bool complement) */
java::util::Collections_CheckedMap_CheckedEntrySet_CheckedEntry* java::util::Collections_CheckedMap_CheckedEntrySet::checkedEntry(Map_Entry* e, ::java::lang::Class* valueType)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Collections_CheckedMap_CheckedEntrySet_CheckedEntry* java::util::Collections_CheckedMap_CheckedEntrySet::checkedEntry(Map_Entry* e, ::java::lang::Class* valueType)");
    return 0;
}

void java::util::Collections_CheckedMap_CheckedEntrySet::clear()
{ /* stub */
    unimplemented_(u"void java::util::Collections_CheckedMap_CheckedEntrySet::clear()");
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedMap_CheckedEntrySet::contains(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::containsAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedMap_CheckedEntrySet::containsAll(Collection* c)");
    return 0;
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedMap_CheckedEntrySet::equals(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::Collections_CheckedMap_CheckedEntrySet::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_CheckedMap_CheckedEntrySet::hashCode()");
    return 0;
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedMap_CheckedEntrySet::isEmpty()");
    return 0;
}

java::util::Iterator* java::util::Collections_CheckedMap_CheckedEntrySet::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Collections_CheckedMap_CheckedEntrySet::iterator()");
    return 0;
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::remove(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedMap_CheckedEntrySet::remove(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::removeAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedMap_CheckedEntrySet::removeAll(Collection* c)");
    return 0;
}

bool java::util::Collections_CheckedMap_CheckedEntrySet::retainAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedMap_CheckedEntrySet::retainAll(Collection* c)");
    return 0;
}

int32_t java::util::Collections_CheckedMap_CheckedEntrySet::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_CheckedMap_CheckedEntrySet::size()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_CheckedMap_CheckedEntrySet::toArray_()
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_CheckedMap_CheckedEntrySet::toArray_()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_CheckedMap_CheckedEntrySet::toArray_(::java::lang::ObjectArray* a)
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_CheckedMap_CheckedEntrySet::toArray_(::java::lang::ObjectArray* a)");
    return 0;
}

java::lang::String* java::util::Collections_CheckedMap_CheckedEntrySet::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Collections_CheckedMap_CheckedEntrySet::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_CheckedMap_CheckedEntrySet::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.CheckedMap.CheckedEntrySet", 48);
    return c;
}

java::lang::Class* java::util::Collections_CheckedMap_CheckedEntrySet::getClass0()
{
    return class_();
}


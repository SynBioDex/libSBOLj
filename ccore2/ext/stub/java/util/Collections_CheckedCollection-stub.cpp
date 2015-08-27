// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_CheckedCollection.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_CheckedCollection::Collections_CheckedCollection(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_CheckedCollection::Collections_CheckedCollection(Collection* c, ::java::lang::Class* type)
    : Collections_CheckedCollection(*static_cast< ::default_init_tag* >(0))
{
    ctor(c, type);
}

constexpr int64_t java::util::Collections_CheckedCollection::serialVersionUID;

void ::java::util::Collections_CheckedCollection::ctor(Collection* c, ::java::lang::Class* type)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_CheckedCollection::ctor(Collection* c, ::java::lang::Class* type)");
}

bool java::util::Collections_CheckedCollection::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedCollection::add(::java::lang::Object* e)");
    return 0;
}

bool java::util::Collections_CheckedCollection::addAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedCollection::addAll(Collection* coll)");
    return 0;
}

/* private: java::lang::String* java::util::Collections_CheckedCollection::badElementMsg(::java::lang::Object* o) */
java::util::Collection* java::util::Collections_CheckedCollection::checkedCopyOf(Collection* coll)
{ /* stub */
    unimplemented_(u"java::util::Collection* java::util::Collections_CheckedCollection::checkedCopyOf(Collection* coll)");
    return 0;
}

void java::util::Collections_CheckedCollection::clear()
{ /* stub */
    unimplemented_(u"void java::util::Collections_CheckedCollection::clear()");
}

bool java::util::Collections_CheckedCollection::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedCollection::contains(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_CheckedCollection::containsAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedCollection::containsAll(Collection* coll)");
    return 0;
}

bool java::util::Collections_CheckedCollection::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedCollection::isEmpty()");
    return 0;
}

java::util::Iterator* java::util::Collections_CheckedCollection::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Collections_CheckedCollection::iterator()");
    return 0;
}

bool java::util::Collections_CheckedCollection::remove(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedCollection::remove(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_CheckedCollection::removeAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedCollection::removeAll(Collection* coll)");
    return 0;
}

bool java::util::Collections_CheckedCollection::retainAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedCollection::retainAll(Collection* coll)");
    return 0;
}

int32_t java::util::Collections_CheckedCollection::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_CheckedCollection::size()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_CheckedCollection::toArray_()
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_CheckedCollection::toArray_()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_CheckedCollection::toArray_(::java::lang::ObjectArray* a)
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_CheckedCollection::toArray_(::java::lang::ObjectArray* a)");
    return 0;
}

java::lang::String* java::util::Collections_CheckedCollection::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Collections_CheckedCollection::toString()");
    return 0;
}

void java::util::Collections_CheckedCollection::typeCheck(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"void java::util::Collections_CheckedCollection::typeCheck(::java::lang::Object* o)");
}

/* private: java::lang::ObjectArray* java::util::Collections_CheckedCollection::zeroLengthElementArray_() */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_CheckedCollection::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.CheckedCollection", 39);
    return c;
}

bool java::util::Collections_CheckedCollection::equals(::java::lang::Object* o)
{
    return Object::equals(o);
}

int32_t java::util::Collections_CheckedCollection::hashCode()
{
    return Object::hashCode();
}

java::lang::Class* java::util::Collections_CheckedCollection::getClass0()
{
    return class_();
}


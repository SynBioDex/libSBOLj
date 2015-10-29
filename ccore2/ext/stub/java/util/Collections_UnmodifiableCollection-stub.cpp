// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_UnmodifiableCollection.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_UnmodifiableCollection::Collections_UnmodifiableCollection(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_UnmodifiableCollection::Collections_UnmodifiableCollection(Collection* c)
    : Collections_UnmodifiableCollection(*static_cast< ::default_init_tag* >(0))
{
    ctor(c);
}

constexpr int64_t java::util::Collections_UnmodifiableCollection::serialVersionUID;

void ::java::util::Collections_UnmodifiableCollection::ctor(Collection* c)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_UnmodifiableCollection::ctor(Collection* c)");
}

bool java::util::Collections_UnmodifiableCollection::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableCollection::add(::java::lang::Object* e)");
    return 0;
}

bool java::util::Collections_UnmodifiableCollection::addAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableCollection::addAll(Collection* coll)");
    return 0;
}

void java::util::Collections_UnmodifiableCollection::clear()
{ /* stub */
    unimplemented_(u"void java::util::Collections_UnmodifiableCollection::clear()");
}

bool java::util::Collections_UnmodifiableCollection::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableCollection::contains(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_UnmodifiableCollection::containsAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableCollection::containsAll(Collection* coll)");
    return 0;
}

bool java::util::Collections_UnmodifiableCollection::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableCollection::isEmpty()");
    return 0;
}

java::util::Iterator* java::util::Collections_UnmodifiableCollection::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Collections_UnmodifiableCollection::iterator()");
    return 0;
}

bool java::util::Collections_UnmodifiableCollection::remove(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableCollection::remove(::java::lang::Object* o)");
    return 0;
}

bool java::util::Collections_UnmodifiableCollection::removeAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableCollection::removeAll(Collection* coll)");
    return 0;
}

bool java::util::Collections_UnmodifiableCollection::retainAll(Collection* coll)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableCollection::retainAll(Collection* coll)");
    return 0;
}

int32_t java::util::Collections_UnmodifiableCollection::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_UnmodifiableCollection::size()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_UnmodifiableCollection::toArray_()
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_UnmodifiableCollection::toArray_()");
    return 0;
}

java::lang::ObjectArray* java::util::Collections_UnmodifiableCollection::toArray_(::java::lang::ObjectArray* a)
{ /* stub */
    unimplemented_(u"java::lang::ObjectArray* java::util::Collections_UnmodifiableCollection::toArray_(::java::lang::ObjectArray* a)");
    return 0;
}

java::lang::String* java::util::Collections_UnmodifiableCollection::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Collections_UnmodifiableCollection::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_UnmodifiableCollection::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.UnmodifiableCollection", 44);
    return c;
}

bool java::util::Collections_UnmodifiableCollection::equals(::java::lang::Object* o)
{
    return Object::equals(o);
}

int32_t java::util::Collections_UnmodifiableCollection::hashCode()
{
    return Object::hashCode();
}

java::lang::Class* java::util::Collections_UnmodifiableCollection::getClass0()
{
    return class_();
}


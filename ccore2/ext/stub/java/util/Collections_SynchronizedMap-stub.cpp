// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_SynchronizedMap.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Collections_SynchronizedMap::Collections_SynchronizedMap(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_SynchronizedMap::Collections_SynchronizedMap(Map* m)
    : Collections_SynchronizedMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(m);
}

java::util::Collections_SynchronizedMap::Collections_SynchronizedMap(Map* m, ::java::lang::Object* mutex)
    : Collections_SynchronizedMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(m, mutex);
}

constexpr int64_t java::util::Collections_SynchronizedMap::serialVersionUID;

void ::java::util::Collections_SynchronizedMap::ctor(Map* m)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedMap::ctor(Map* m)");
}

void ::java::util::Collections_SynchronizedMap::ctor(Map* m, ::java::lang::Object* mutex)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedMap::ctor(Map* m, ::java::lang::Object* mutex)");
}

void java::util::Collections_SynchronizedMap::clear()
{ /* stub */
    unimplemented_(u"void java::util::Collections_SynchronizedMap::clear()");
}

bool java::util::Collections_SynchronizedMap::containsKey(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedMap::containsKey(::java::lang::Object* key)");
    return 0;
}

bool java::util::Collections_SynchronizedMap::containsValue(::java::lang::Object* value)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedMap::containsValue(::java::lang::Object* value)");
    return 0;
}

java::util::Set* java::util::Collections_SynchronizedMap::entrySet()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::Collections_SynchronizedMap::entrySet()");
    return 0;
}

bool java::util::Collections_SynchronizedMap::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedMap::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::Collections_SynchronizedMap::get(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedMap::get(::java::lang::Object* key)");
    return 0;
}

int32_t java::util::Collections_SynchronizedMap::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SynchronizedMap::hashCode()");
    return 0;
}

bool java::util::Collections_SynchronizedMap::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::Collections_SynchronizedMap::isEmpty()");
    return 0;
}

java::util::Set* java::util::Collections_SynchronizedMap::keySet()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::Collections_SynchronizedMap::keySet()");
    return 0;
}

java::lang::Object* java::util::Collections_SynchronizedMap::put(::java::lang::Object* key, ::java::lang::Object* value)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedMap::put(::java::lang::Object* key, ::java::lang::Object* value)");
    return 0;
}

void java::util::Collections_SynchronizedMap::putAll(Map* map)
{ /* stub */
    unimplemented_(u"void java::util::Collections_SynchronizedMap::putAll(Map* map)");
}

java::lang::Object* java::util::Collections_SynchronizedMap::remove(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedMap::remove(::java::lang::Object* key)");
    return 0;
}

int32_t java::util::Collections_SynchronizedMap::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_SynchronizedMap::size()");
    return 0;
}

java::lang::String* java::util::Collections_SynchronizedMap::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Collections_SynchronizedMap::toString()");
    return 0;
}

java::util::Collection* java::util::Collections_SynchronizedMap::values()
{ /* stub */
    unimplemented_(u"java::util::Collection* java::util::Collections_SynchronizedMap::values()");
    return 0;
}

/* private: void java::util::Collections_SynchronizedMap::writeObject(::java::io::ObjectOutputStream* s) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_SynchronizedMap::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.SynchronizedMap", 37);
    return c;
}

java::lang::Class* java::util::Collections_SynchronizedMap::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Hashtable.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
typedef ::SubArray< ::java::util::Map_Entry, ::java::lang::ObjectArray > Map_EntryArray;
typedef ::SubArray< ::java::util::Hashtable_Entry, ::java::lang::ObjectArray, Map_EntryArray > Hashtable_EntryArray;
    } // util
} // java

extern void unimplemented_(const char16_t* name);
java::util::Hashtable::Hashtable(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Hashtable::Hashtable()
    : Hashtable(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::util::Hashtable::Hashtable(int32_t initialCapacity)
    : Hashtable(*static_cast< ::default_init_tag* >(0))
{
    ctor(initialCapacity);
}

java::util::Hashtable::Hashtable(Map* t)
    : Hashtable(*static_cast< ::default_init_tag* >(0))
{
    ctor(t);
}

java::util::Hashtable::Hashtable(int32_t initialCapacity, float loadFactor)
    : Hashtable(*static_cast< ::default_init_tag* >(0))
{
    ctor(initialCapacity, loadFactor);
}

constexpr int32_t java::util::Hashtable::ALTERNATIVE_HASHING_THRESHOLD_DEFAULT;
constexpr int32_t java::util::Hashtable::ENTRIES;
constexpr int32_t java::util::Hashtable::KEYS;
constexpr int32_t java::util::Hashtable::MAX_ARRAY_SIZE;
constexpr int32_t java::util::Hashtable::VALUES;
constexpr int64_t java::util::Hashtable::serialVersionUID;

void ::java::util::Hashtable::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Hashtable::ctor()");
}

void ::java::util::Hashtable::ctor(int32_t initialCapacity)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Hashtable::ctor(int32_t initialCapacity)");
}

void ::java::util::Hashtable::ctor(Map* t)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Hashtable::ctor(Map* t)");
}

void ::java::util::Hashtable::ctor(int32_t initialCapacity, float loadFactor)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Hashtable::ctor(int32_t initialCapacity, float loadFactor)");
}

void java::util::Hashtable::clear()
{ /* stub */
    unimplemented_(u"void java::util::Hashtable::clear()");
}

java::lang::Object* java::util::Hashtable::clone()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Hashtable::clone()");
    return 0;
}

bool java::util::Hashtable::contains(::java::lang::Object* value)
{ /* stub */
    unimplemented_(u"bool java::util::Hashtable::contains(::java::lang::Object* value)");
    return 0;
}

bool java::util::Hashtable::containsKey(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"bool java::util::Hashtable::containsKey(::java::lang::Object* key)");
    return 0;
}

bool java::util::Hashtable::containsValue(::java::lang::Object* value)
{ /* stub */
    unimplemented_(u"bool java::util::Hashtable::containsValue(::java::lang::Object* value)");
    return 0;
}

java::util::Enumeration* java::util::Hashtable::elements()
{ /* stub */
    unimplemented_(u"java::util::Enumeration* java::util::Hashtable::elements()");
    return 0;
}

java::util::Set* java::util::Hashtable::entrySet()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::Hashtable::entrySet()");
    return 0;
}

bool java::util::Hashtable::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Hashtable::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::Hashtable::get(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Hashtable::get(::java::lang::Object* key)");
    return 0;
}

/* private: java::util::Enumeration* java::util::Hashtable::getEnumeration(int32_t type) */
/* private: java::util::Iterator* java::util::Hashtable::getIterator(int32_t type) */
/* private: int32_t java::util::Hashtable::hash(::java::lang::Object* k) */
int32_t java::util::Hashtable::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Hashtable::hashCode()");
    return 0;
}

bool java::util::Hashtable::initHashSeedAsNeeded(int32_t capacity)
{ /* stub */
    unimplemented_(u"bool java::util::Hashtable::initHashSeedAsNeeded(int32_t capacity)");
    return 0;
}

bool java::util::Hashtable::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::Hashtable::isEmpty()");
    return 0;
}

java::util::Set* java::util::Hashtable::keySet()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::Hashtable::keySet()");
    return 0;
}

java::util::Enumeration* java::util::Hashtable::keys()
{ /* stub */
    unimplemented_(u"java::util::Enumeration* java::util::Hashtable::keys()");
    return 0;
}

java::lang::Object* java::util::Hashtable::put(::java::lang::Object* key, ::java::lang::Object* value)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Hashtable::put(::java::lang::Object* key, ::java::lang::Object* value)");
    return 0;
}

void java::util::Hashtable::putAll(Map* t)
{ /* stub */
    unimplemented_(u"void java::util::Hashtable::putAll(Map* t)");
}

/* private: void java::util::Hashtable::readObject(::java::io::ObjectInputStream* s) */
/* private: void java::util::Hashtable::reconstitutionPut(Hashtable_EntryArray* tab, ::java::lang::Object* key, ::java::lang::Object* value) */
void java::util::Hashtable::rehash()
{ /* stub */
    unimplemented_(u"void java::util::Hashtable::rehash()");
}

java::lang::Object* java::util::Hashtable::remove(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Hashtable::remove(::java::lang::Object* key)");
    return 0;
}

int32_t java::util::Hashtable::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Hashtable::size()");
    return 0;
}

java::lang::String* java::util::Hashtable::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Hashtable::toString()");
    return 0;
}

java::util::Collection* java::util::Hashtable::values()
{ /* stub */
    unimplemented_(u"java::util::Collection* java::util::Hashtable::values()");
    return 0;
}

/* private: void java::util::Hashtable::writeObject(::java::io::ObjectOutputStream* s) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Hashtable::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Hashtable", 19);
    return c;
}

java::lang::Class* java::util::Hashtable::getClass0()
{
    return class_();
}


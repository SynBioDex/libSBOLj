// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/HashMap.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
typedef ::SubArray< ::java::util::Map_Entry, ::java::lang::ObjectArray > Map_EntryArray;
typedef ::SubArray< ::java::util::HashMap_Entry, ::java::lang::ObjectArray, Map_EntryArray > HashMap_EntryArray;
    } // util
} // java

extern void unimplemented_(const char16_t* name);
java::util::HashMap::HashMap(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::HashMap::HashMap()
    : HashMap(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::util::HashMap::HashMap(int32_t initialCapacity)
    : HashMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(initialCapacity);
}

java::util::HashMap::HashMap(Map* m)
    : HashMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(m);
}

java::util::HashMap::HashMap(int32_t initialCapacity, float loadFactor)
    : HashMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(initialCapacity, loadFactor);
}

constexpr int32_t java::util::HashMap::ALTERNATIVE_HASHING_THRESHOLD_DEFAULT;
constexpr int32_t java::util::HashMap::DEFAULT_INITIAL_CAPACITY;
constexpr float java::util::HashMap::DEFAULT_LOAD_FACTOR;
java::util::HashMap_EntryArray*& java::util::HashMap::EMPTY_TABLE()
{
    clinit();
    return EMPTY_TABLE_;
}
java::util::HashMap_EntryArray* java::util::HashMap::EMPTY_TABLE_;
constexpr int32_t java::util::HashMap::MAXIMUM_CAPACITY;
constexpr int64_t java::util::HashMap::serialVersionUID;

void ::java::util::HashMap::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashMap::ctor()");
}

void ::java::util::HashMap::ctor(int32_t initialCapacity)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashMap::ctor(int32_t initialCapacity)");
}

void ::java::util::HashMap::ctor(Map* m)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashMap::ctor(Map* m)");
}

void ::java::util::HashMap::ctor(int32_t initialCapacity, float loadFactor)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashMap::ctor(int32_t initialCapacity, float loadFactor)");
}

void java::util::HashMap::addEntry(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, int32_t bucketIndex)
{ /* stub */
    unimplemented_(u"void java::util::HashMap::addEntry(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, int32_t bucketIndex)");
}

int32_t java::util::HashMap::capacity()
{ /* stub */
    unimplemented_(u"int32_t java::util::HashMap::capacity()");
    return 0;
}

void java::util::HashMap::clear()
{ /* stub */
    unimplemented_(u"void java::util::HashMap::clear()");
}

java::lang::Object* java::util::HashMap::clone()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::HashMap::clone()");
    return 0;
}

bool java::util::HashMap::containsKey(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"bool java::util::HashMap::containsKey(::java::lang::Object* key)");
    return 0;
}

/* private: bool java::util::HashMap::containsNullValue() */
bool java::util::HashMap::containsValue(::java::lang::Object* value)
{ /* stub */
    unimplemented_(u"bool java::util::HashMap::containsValue(::java::lang::Object* value)");
    return 0;
}

void java::util::HashMap::createEntry(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, int32_t bucketIndex)
{ /* stub */
    unimplemented_(u"void java::util::HashMap::createEntry(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, int32_t bucketIndex)");
}

java::util::Set* java::util::HashMap::entrySet()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::HashMap::entrySet()");
    return 0;
}

/* private: java::util::Set* java::util::HashMap::entrySet0() */
java::lang::Object* java::util::HashMap::get(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::HashMap::get(::java::lang::Object* key)");
    return 0;
}

java::util::HashMap_Entry* java::util::HashMap::getEntry(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"java::util::HashMap_Entry* java::util::HashMap::getEntry(::java::lang::Object* key)");
    return 0;
}

/* private: java::lang::Object* java::util::HashMap::getForNullKey() */
int32_t java::util::HashMap::hash(::java::lang::Object* k)
{ /* stub */
    unimplemented_(u"int32_t java::util::HashMap::hash(::java::lang::Object* k)");
    return 0;
}

int32_t java::util::HashMap::indexFor(int32_t h, int32_t length)
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::util::HashMap::indexFor(int32_t h, int32_t length)");
    return 0;
}

/* private: void java::util::HashMap::inflateTable(int32_t toSize) */
void java::util::HashMap::init_()
{ /* stub */
    unimplemented_(u"void java::util::HashMap::init_()");
}

bool java::util::HashMap::initHashSeedAsNeeded(int32_t capacity)
{ /* stub */
    unimplemented_(u"bool java::util::HashMap::initHashSeedAsNeeded(int32_t capacity)");
    return 0;
}

bool java::util::HashMap::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::util::HashMap::isEmpty()");
    return 0;
}

java::util::Set* java::util::HashMap::keySet()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::HashMap::keySet()");
    return 0;
}

float java::util::HashMap::loadFactor()
{ /* stub */
    unimplemented_(u"float java::util::HashMap::loadFactor()");
    return 0;
}

java::util::Iterator* java::util::HashMap::newEntryIterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::HashMap::newEntryIterator()");
    return 0;
}

java::util::Iterator* java::util::HashMap::newKeyIterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::HashMap::newKeyIterator()");
    return 0;
}

java::util::Iterator* java::util::HashMap::newValueIterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::HashMap::newValueIterator()");
    return 0;
}

java::lang::Object* java::util::HashMap::put(::java::lang::Object* key, ::java::lang::Object* value)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::HashMap::put(::java::lang::Object* key, ::java::lang::Object* value)");
    return 0;
}

void java::util::HashMap::putAll(Map* m)
{ /* stub */
    unimplemented_(u"void java::util::HashMap::putAll(Map* m)");
}

/* private: void java::util::HashMap::putAllForCreate(Map* m) */
/* private: void java::util::HashMap::putForCreate(::java::lang::Object* key, ::java::lang::Object* value) */
/* private: java::lang::Object* java::util::HashMap::putForNullKey(::java::lang::Object* value) */
/* private: void java::util::HashMap::readObject(::java::io::ObjectInputStream* s) */
java::lang::Object* java::util::HashMap::remove(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::HashMap::remove(::java::lang::Object* key)");
    return 0;
}

java::util::HashMap_Entry* java::util::HashMap::removeEntryForKey(::java::lang::Object* key)
{ /* stub */
    unimplemented_(u"java::util::HashMap_Entry* java::util::HashMap::removeEntryForKey(::java::lang::Object* key)");
    return 0;
}

java::util::HashMap_Entry* java::util::HashMap::removeMapping(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"java::util::HashMap_Entry* java::util::HashMap::removeMapping(::java::lang::Object* o)");
    return 0;
}

void java::util::HashMap::resize(int32_t newCapacity)
{ /* stub */
    unimplemented_(u"void java::util::HashMap::resize(int32_t newCapacity)");
}

/* private: int32_t java::util::HashMap::roundUpToPowerOf2(int32_t number) */
int32_t java::util::HashMap::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::HashMap::size()");
    return 0;
}

void java::util::HashMap::transfer(HashMap_EntryArray* newTable, bool rehash)
{ /* stub */
    unimplemented_(u"void java::util::HashMap::transfer(HashMap_EntryArray* newTable, bool rehash)");
}

java::util::Collection* java::util::HashMap::values()
{ /* stub */
    unimplemented_(u"java::util::Collection* java::util::HashMap::values()");
    return 0;
}

/* private: void java::util::HashMap::writeObject(::java::io::ObjectOutputStream* s) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::HashMap::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.HashMap", 17);
    return c;
}

bool java::util::HashMap::equals(::java::lang::Object* o)
{
    return AbstractMap::equals(o);
}

int32_t java::util::HashMap::hashCode()
{
    return AbstractMap::hashCode();
}

java::lang::Class* java::util::HashMap::getClass0()
{
    return class_();
}


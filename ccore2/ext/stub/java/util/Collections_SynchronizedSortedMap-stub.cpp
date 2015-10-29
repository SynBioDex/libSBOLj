// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_SynchronizedSortedMap.hpp>

#include <java/lang/ClassCastException.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Collection.hpp>
#include <java/util/Set.hpp>

template<typename T, typename U>
static T java_cast(U* u)
{
    if(!u) return static_cast<T>(nullptr);
    auto t = dynamic_cast<T>(u);
    if(!t) throw new ::java::lang::ClassCastException();
    return t;
}

extern void unimplemented_(const char16_t* name);
java::util::Collections_SynchronizedSortedMap::Collections_SynchronizedSortedMap(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_SynchronizedSortedMap::Collections_SynchronizedSortedMap(SortedMap* m)
    : Collections_SynchronizedSortedMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(m);
}

java::util::Collections_SynchronizedSortedMap::Collections_SynchronizedSortedMap(SortedMap* m, ::java::lang::Object* mutex)
    : Collections_SynchronizedSortedMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(m, mutex);
}

constexpr int64_t java::util::Collections_SynchronizedSortedMap::serialVersionUID;

void ::java::util::Collections_SynchronizedSortedMap::ctor(SortedMap* m)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedSortedMap::ctor(SortedMap* m)");
}

void ::java::util::Collections_SynchronizedSortedMap::ctor(SortedMap* m, ::java::lang::Object* mutex)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_SynchronizedSortedMap::ctor(SortedMap* m, ::java::lang::Object* mutex)");
}

java::util::Comparator* java::util::Collections_SynchronizedSortedMap::comparator()
{ /* stub */
    unimplemented_(u"java::util::Comparator* java::util::Collections_SynchronizedSortedMap::comparator()");
    return 0;
}

java::lang::Object* java::util::Collections_SynchronizedSortedMap::firstKey()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedSortedMap::firstKey()");
    return 0;
}

java::util::SortedMap* java::util::Collections_SynchronizedSortedMap::headMap(::java::lang::Object* toKey)
{ /* stub */
    unimplemented_(u"java::util::SortedMap* java::util::Collections_SynchronizedSortedMap::headMap(::java::lang::Object* toKey)");
    return 0;
}

java::lang::Object* java::util::Collections_SynchronizedSortedMap::lastKey()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_SynchronizedSortedMap::lastKey()");
    return 0;
}

java::util::SortedMap* java::util::Collections_SynchronizedSortedMap::subMap(::java::lang::Object* fromKey, ::java::lang::Object* toKey)
{ /* stub */
    unimplemented_(u"java::util::SortedMap* java::util::Collections_SynchronizedSortedMap::subMap(::java::lang::Object* fromKey, ::java::lang::Object* toKey)");
    return 0;
}

java::util::SortedMap* java::util::Collections_SynchronizedSortedMap::tailMap(::java::lang::Object* fromKey)
{ /* stub */
    unimplemented_(u"java::util::SortedMap* java::util::Collections_SynchronizedSortedMap::tailMap(::java::lang::Object* fromKey)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_SynchronizedSortedMap::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.SynchronizedSortedMap", 43);
    return c;
}

void java::util::Collections_SynchronizedSortedMap::clear()
{
    Collections_SynchronizedMap::clear();
}

bool java::util::Collections_SynchronizedSortedMap::containsKey(::java::lang::Object* key)
{
    return Collections_SynchronizedMap::containsKey(key);
}

bool java::util::Collections_SynchronizedSortedMap::containsValue(::java::lang::Object* value)
{
    return Collections_SynchronizedMap::containsValue(value);
}

bool java::util::Collections_SynchronizedSortedMap::equals(::java::lang::Object* o)
{
    return Collections_SynchronizedMap::equals(o);
}

java::lang::Object* java::util::Collections_SynchronizedSortedMap::get(::java::lang::Object* key)
{
    return java_cast< ::java::lang::Object* >(Collections_SynchronizedMap::get(key));
}

int32_t java::util::Collections_SynchronizedSortedMap::hashCode()
{
    return Collections_SynchronizedMap::hashCode();
}

bool java::util::Collections_SynchronizedSortedMap::isEmpty()
{
    return Collections_SynchronizedMap::isEmpty();
}

java::lang::Object* java::util::Collections_SynchronizedSortedMap::put(::java::lang::Object* key, ::java::lang::Object* value)
{
    return java_cast< ::java::lang::Object* >(Collections_SynchronizedMap::put(key, value));
}

void java::util::Collections_SynchronizedSortedMap::putAll(Map* m)
{
    Collections_SynchronizedMap::putAll(m);
}

java::lang::Object* java::util::Collections_SynchronizedSortedMap::remove(::java::lang::Object* key)
{
    return java_cast< ::java::lang::Object* >(Collections_SynchronizedMap::remove(key));
}

int32_t java::util::Collections_SynchronizedSortedMap::size()
{
    return Collections_SynchronizedMap::size();
}

java::util::Set* java::util::Collections_SynchronizedSortedMap::entrySet()
{
    return java_cast< Set* >(Collections_SynchronizedMap::entrySet());
}

java::util::Set* java::util::Collections_SynchronizedSortedMap::keySet()
{
    return java_cast< Set* >(Collections_SynchronizedMap::keySet());
}

java::util::Collection* java::util::Collections_SynchronizedSortedMap::values()
{
    return java_cast< Collection* >(Collections_SynchronizedMap::values());
}

java::lang::Class* java::util::Collections_SynchronizedSortedMap::getClass0()
{
    return class_();
}


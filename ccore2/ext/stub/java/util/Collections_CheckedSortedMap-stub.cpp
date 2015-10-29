// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_CheckedSortedMap.hpp>

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
java::util::Collections_CheckedSortedMap::Collections_CheckedSortedMap(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_CheckedSortedMap::Collections_CheckedSortedMap(SortedMap* m, ::java::lang::Class* keyType, ::java::lang::Class* valueType)
    : Collections_CheckedSortedMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(m, keyType, valueType);
}

constexpr int64_t java::util::Collections_CheckedSortedMap::serialVersionUID;

void ::java::util::Collections_CheckedSortedMap::ctor(SortedMap* m, ::java::lang::Class* keyType, ::java::lang::Class* valueType)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_CheckedSortedMap::ctor(SortedMap* m, ::java::lang::Class* keyType, ::java::lang::Class* valueType)");
}

java::util::Comparator* java::util::Collections_CheckedSortedMap::comparator()
{ /* stub */
    unimplemented_(u"java::util::Comparator* java::util::Collections_CheckedSortedMap::comparator()");
    return 0;
}

java::lang::Object* java::util::Collections_CheckedSortedMap::firstKey()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_CheckedSortedMap::firstKey()");
    return 0;
}

java::util::SortedMap* java::util::Collections_CheckedSortedMap::headMap(::java::lang::Object* toKey)
{ /* stub */
    unimplemented_(u"java::util::SortedMap* java::util::Collections_CheckedSortedMap::headMap(::java::lang::Object* toKey)");
    return 0;
}

java::lang::Object* java::util::Collections_CheckedSortedMap::lastKey()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_CheckedSortedMap::lastKey()");
    return 0;
}

java::util::SortedMap* java::util::Collections_CheckedSortedMap::subMap(::java::lang::Object* fromKey, ::java::lang::Object* toKey)
{ /* stub */
    unimplemented_(u"java::util::SortedMap* java::util::Collections_CheckedSortedMap::subMap(::java::lang::Object* fromKey, ::java::lang::Object* toKey)");
    return 0;
}

java::util::SortedMap* java::util::Collections_CheckedSortedMap::tailMap(::java::lang::Object* fromKey)
{ /* stub */
    unimplemented_(u"java::util::SortedMap* java::util::Collections_CheckedSortedMap::tailMap(::java::lang::Object* fromKey)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_CheckedSortedMap::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.CheckedSortedMap", 38);
    return c;
}

void java::util::Collections_CheckedSortedMap::clear()
{
    Collections_CheckedMap::clear();
}

bool java::util::Collections_CheckedSortedMap::containsKey(::java::lang::Object* key)
{
    return Collections_CheckedMap::containsKey(key);
}

bool java::util::Collections_CheckedSortedMap::containsValue(::java::lang::Object* value)
{
    return Collections_CheckedMap::containsValue(value);
}

bool java::util::Collections_CheckedSortedMap::equals(::java::lang::Object* o)
{
    return Collections_CheckedMap::equals(o);
}

java::lang::Object* java::util::Collections_CheckedSortedMap::get(::java::lang::Object* key)
{
    return java_cast< ::java::lang::Object* >(Collections_CheckedMap::get(key));
}

int32_t java::util::Collections_CheckedSortedMap::hashCode()
{
    return Collections_CheckedMap::hashCode();
}

bool java::util::Collections_CheckedSortedMap::isEmpty()
{
    return Collections_CheckedMap::isEmpty();
}

java::lang::Object* java::util::Collections_CheckedSortedMap::put(::java::lang::Object* key, ::java::lang::Object* value)
{
    return java_cast< ::java::lang::Object* >(Collections_CheckedMap::put(key, value));
}

void java::util::Collections_CheckedSortedMap::putAll(Map* m)
{
    Collections_CheckedMap::putAll(m);
}

java::lang::Object* java::util::Collections_CheckedSortedMap::remove(::java::lang::Object* key)
{
    return java_cast< ::java::lang::Object* >(Collections_CheckedMap::remove(key));
}

int32_t java::util::Collections_CheckedSortedMap::size()
{
    return Collections_CheckedMap::size();
}

java::util::Set* java::util::Collections_CheckedSortedMap::entrySet()
{
    return java_cast< Set* >(Collections_CheckedMap::entrySet());
}

java::util::Set* java::util::Collections_CheckedSortedMap::keySet()
{
    return java_cast< Set* >(Collections_CheckedMap::keySet());
}

java::util::Collection* java::util::Collections_CheckedSortedMap::values()
{
    return java_cast< Collection* >(Collections_CheckedMap::values());
}

java::lang::Class* java::util::Collections_CheckedSortedMap::getClass0()
{
    return class_();
}


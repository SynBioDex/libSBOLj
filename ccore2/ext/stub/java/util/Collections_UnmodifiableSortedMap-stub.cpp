// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_UnmodifiableSortedMap.hpp>

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
java::util::Collections_UnmodifiableSortedMap::Collections_UnmodifiableSortedMap(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_UnmodifiableSortedMap::Collections_UnmodifiableSortedMap(SortedMap* m)
    : Collections_UnmodifiableSortedMap(*static_cast< ::default_init_tag* >(0))
{
    ctor(m);
}

constexpr int64_t java::util::Collections_UnmodifiableSortedMap::serialVersionUID;

void ::java::util::Collections_UnmodifiableSortedMap::ctor(SortedMap* m)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_UnmodifiableSortedMap::ctor(SortedMap* m)");
}

java::util::Comparator* java::util::Collections_UnmodifiableSortedMap::comparator()
{ /* stub */
    unimplemented_(u"java::util::Comparator* java::util::Collections_UnmodifiableSortedMap::comparator()");
    return 0;
}

java::lang::Object* java::util::Collections_UnmodifiableSortedMap::firstKey()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_UnmodifiableSortedMap::firstKey()");
    return 0;
}

java::util::SortedMap* java::util::Collections_UnmodifiableSortedMap::headMap(::java::lang::Object* toKey)
{ /* stub */
    unimplemented_(u"java::util::SortedMap* java::util::Collections_UnmodifiableSortedMap::headMap(::java::lang::Object* toKey)");
    return 0;
}

java::lang::Object* java::util::Collections_UnmodifiableSortedMap::lastKey()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_UnmodifiableSortedMap::lastKey()");
    return 0;
}

java::util::SortedMap* java::util::Collections_UnmodifiableSortedMap::subMap(::java::lang::Object* fromKey, ::java::lang::Object* toKey)
{ /* stub */
    unimplemented_(u"java::util::SortedMap* java::util::Collections_UnmodifiableSortedMap::subMap(::java::lang::Object* fromKey, ::java::lang::Object* toKey)");
    return 0;
}

java::util::SortedMap* java::util::Collections_UnmodifiableSortedMap::tailMap(::java::lang::Object* fromKey)
{ /* stub */
    unimplemented_(u"java::util::SortedMap* java::util::Collections_UnmodifiableSortedMap::tailMap(::java::lang::Object* fromKey)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_UnmodifiableSortedMap::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.UnmodifiableSortedMap", 43);
    return c;
}

void java::util::Collections_UnmodifiableSortedMap::clear()
{
    Collections_UnmodifiableMap::clear();
}

bool java::util::Collections_UnmodifiableSortedMap::containsKey(::java::lang::Object* key)
{
    return Collections_UnmodifiableMap::containsKey(key);
}

bool java::util::Collections_UnmodifiableSortedMap::containsValue(::java::lang::Object* value)
{
    return Collections_UnmodifiableMap::containsValue(value);
}

bool java::util::Collections_UnmodifiableSortedMap::equals(::java::lang::Object* o)
{
    return Collections_UnmodifiableMap::equals(o);
}

java::lang::Object* java::util::Collections_UnmodifiableSortedMap::get(::java::lang::Object* key)
{
    return java_cast< ::java::lang::Object* >(Collections_UnmodifiableMap::get(key));
}

int32_t java::util::Collections_UnmodifiableSortedMap::hashCode()
{
    return Collections_UnmodifiableMap::hashCode();
}

bool java::util::Collections_UnmodifiableSortedMap::isEmpty()
{
    return Collections_UnmodifiableMap::isEmpty();
}

java::lang::Object* java::util::Collections_UnmodifiableSortedMap::put(::java::lang::Object* key, ::java::lang::Object* value)
{
    return java_cast< ::java::lang::Object* >(Collections_UnmodifiableMap::put(key, value));
}

void java::util::Collections_UnmodifiableSortedMap::putAll(Map* m)
{
    Collections_UnmodifiableMap::putAll(m);
}

java::lang::Object* java::util::Collections_UnmodifiableSortedMap::remove(::java::lang::Object* key)
{
    return java_cast< ::java::lang::Object* >(Collections_UnmodifiableMap::remove(key));
}

int32_t java::util::Collections_UnmodifiableSortedMap::size()
{
    return Collections_UnmodifiableMap::size();
}

java::util::Set* java::util::Collections_UnmodifiableSortedMap::entrySet()
{
    return java_cast< Set* >(Collections_UnmodifiableMap::entrySet());
}

java::util::Set* java::util::Collections_UnmodifiableSortedMap::keySet()
{
    return java_cast< Set* >(Collections_UnmodifiableMap::keySet());
}

java::util::Collection* java::util::Collections_UnmodifiableSortedMap::values()
{
    return java_cast< Collection* >(Collections_UnmodifiableMap::values());
}

java::lang::Class* java::util::Collections_UnmodifiableSortedMap::getClass0()
{
    return class_();
}


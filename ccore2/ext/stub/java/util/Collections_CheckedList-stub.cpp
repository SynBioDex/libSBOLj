// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_CheckedList.hpp>

#include <java/lang/ClassCastException.hpp>
#include <java/util/Iterator.hpp>
#include <ObjectArray.hpp>

template<typename T, typename U>
static T java_cast(U* u)
{
    if(!u) return static_cast<T>(nullptr);
    auto t = dynamic_cast<T>(u);
    if(!t) throw new ::java::lang::ClassCastException();
    return t;
}

extern void unimplemented_(const char16_t* name);
java::util::Collections_CheckedList::Collections_CheckedList(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_CheckedList::Collections_CheckedList(List* list, ::java::lang::Class* type)
    : Collections_CheckedList(*static_cast< ::default_init_tag* >(0))
{
    ctor(list, type);
}

constexpr int64_t java::util::Collections_CheckedList::serialVersionUID;

void ::java::util::Collections_CheckedList::ctor(List* list, ::java::lang::Class* type)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_CheckedList::ctor(List* list, ::java::lang::Class* type)");
}

void java::util::Collections_CheckedList::add(int32_t index, ::java::lang::Object* element)
{ /* stub */
    unimplemented_(u"void java::util::Collections_CheckedList::add(int32_t index, ::java::lang::Object* element)");
}

bool java::util::Collections_CheckedList::addAll(int32_t index, Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedList::addAll(int32_t index, Collection* c)");
    return 0;
}

bool java::util::Collections_CheckedList::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_CheckedList::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::Collections_CheckedList::get(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_CheckedList::get(int32_t index)");
    return 0;
}

int32_t java::util::Collections_CheckedList::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_CheckedList::hashCode()");
    return 0;
}

int32_t java::util::Collections_CheckedList::indexOf(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_CheckedList::indexOf(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::Collections_CheckedList::lastIndexOf(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_CheckedList::lastIndexOf(::java::lang::Object* o)");
    return 0;
}

java::util::ListIterator* java::util::Collections_CheckedList::listIterator()
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::Collections_CheckedList::listIterator()");
    return 0;
}

java::util::ListIterator* java::util::Collections_CheckedList::listIterator(int32_t index)
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::Collections_CheckedList::listIterator(int32_t index)");
    return 0;
}

java::lang::Object* java::util::Collections_CheckedList::remove(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_CheckedList::remove(int32_t index)");
    return 0;
}

java::lang::Object* java::util::Collections_CheckedList::set(int32_t index, ::java::lang::Object* element)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_CheckedList::set(int32_t index, ::java::lang::Object* element)");
    return 0;
}

java::util::List* java::util::Collections_CheckedList::subList(int32_t fromIndex, int32_t toIndex)
{ /* stub */
    unimplemented_(u"java::util::List* java::util::Collections_CheckedList::subList(int32_t fromIndex, int32_t toIndex)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_CheckedList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.CheckedList", 33);
    return c;
}

bool java::util::Collections_CheckedList::add(::java::lang::Object* e)
{
    return Collections_CheckedCollection::add(e);
}

bool java::util::Collections_CheckedList::addAll(Collection* c)
{
    return Collections_CheckedCollection::addAll(c);
}

void java::util::Collections_CheckedList::clear()
{
    Collections_CheckedCollection::clear();
}

bool java::util::Collections_CheckedList::contains(::java::lang::Object* o)
{
    return Collections_CheckedCollection::contains(o);
}

bool java::util::Collections_CheckedList::containsAll(Collection* c)
{
    return Collections_CheckedCollection::containsAll(c);
}

bool java::util::Collections_CheckedList::isEmpty()
{
    return Collections_CheckedCollection::isEmpty();
}

java::util::Iterator* java::util::Collections_CheckedList::iterator()
{
    return java_cast< Iterator* >(Collections_CheckedCollection::iterator());
}

bool java::util::Collections_CheckedList::remove(::java::lang::Object* o)
{
    return Collections_CheckedCollection::remove(o);
}

bool java::util::Collections_CheckedList::removeAll(Collection* c)
{
    return Collections_CheckedCollection::removeAll(c);
}

bool java::util::Collections_CheckedList::retainAll(Collection* c)
{
    return Collections_CheckedCollection::retainAll(c);
}

int32_t java::util::Collections_CheckedList::size()
{
    return Collections_CheckedCollection::size();
}

java::lang::ObjectArray* java::util::Collections_CheckedList::toArray_()
{
    return Collections_CheckedCollection::toArray_();
}

java::lang::ObjectArray* java::util::Collections_CheckedList::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(Collections_CheckedCollection::toArray_(a));
}

java::lang::Class* java::util::Collections_CheckedList::getClass0()
{
    return class_();
}


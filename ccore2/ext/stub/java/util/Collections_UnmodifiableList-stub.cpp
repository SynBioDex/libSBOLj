// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Collections_UnmodifiableList.hpp>

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
java::util::Collections_UnmodifiableList::Collections_UnmodifiableList(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Collections_UnmodifiableList::Collections_UnmodifiableList(List* list)
    : Collections_UnmodifiableList(*static_cast< ::default_init_tag* >(0))
{
    ctor(list);
}

constexpr int64_t java::util::Collections_UnmodifiableList::serialVersionUID;

void ::java::util::Collections_UnmodifiableList::ctor(List* list)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Collections_UnmodifiableList::ctor(List* list)");
}

void java::util::Collections_UnmodifiableList::add(int32_t index, ::java::lang::Object* element)
{ /* stub */
    unimplemented_(u"void java::util::Collections_UnmodifiableList::add(int32_t index, ::java::lang::Object* element)");
}

bool java::util::Collections_UnmodifiableList::addAll(int32_t index, Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableList::addAll(int32_t index, Collection* c)");
    return 0;
}

bool java::util::Collections_UnmodifiableList::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Collections_UnmodifiableList::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::Collections_UnmodifiableList::get(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_UnmodifiableList::get(int32_t index)");
    return 0;
}

int32_t java::util::Collections_UnmodifiableList::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_UnmodifiableList::hashCode()");
    return 0;
}

int32_t java::util::Collections_UnmodifiableList::indexOf(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_UnmodifiableList::indexOf(::java::lang::Object* o)");
    return 0;
}

int32_t java::util::Collections_UnmodifiableList::lastIndexOf(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"int32_t java::util::Collections_UnmodifiableList::lastIndexOf(::java::lang::Object* o)");
    return 0;
}

java::util::ListIterator* java::util::Collections_UnmodifiableList::listIterator()
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::Collections_UnmodifiableList::listIterator()");
    return 0;
}

java::util::ListIterator* java::util::Collections_UnmodifiableList::listIterator(int32_t index)
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::Collections_UnmodifiableList::listIterator(int32_t index)");
    return 0;
}

/* private: java::lang::Object* java::util::Collections_UnmodifiableList::readResolve() */
java::lang::Object* java::util::Collections_UnmodifiableList::remove(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_UnmodifiableList::remove(int32_t index)");
    return 0;
}

java::lang::Object* java::util::Collections_UnmodifiableList::set(int32_t index, ::java::lang::Object* element)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Collections_UnmodifiableList::set(int32_t index, ::java::lang::Object* element)");
    return 0;
}

java::util::List* java::util::Collections_UnmodifiableList::subList(int32_t fromIndex, int32_t toIndex)
{ /* stub */
    unimplemented_(u"java::util::List* java::util::Collections_UnmodifiableList::subList(int32_t fromIndex, int32_t toIndex)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Collections_UnmodifiableList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Collections.UnmodifiableList", 38);
    return c;
}

bool java::util::Collections_UnmodifiableList::add(::java::lang::Object* e)
{
    return Collections_UnmodifiableCollection::add(e);
}

bool java::util::Collections_UnmodifiableList::addAll(Collection* c)
{
    return Collections_UnmodifiableCollection::addAll(c);
}

void java::util::Collections_UnmodifiableList::clear()
{
    Collections_UnmodifiableCollection::clear();
}

bool java::util::Collections_UnmodifiableList::contains(::java::lang::Object* o)
{
    return Collections_UnmodifiableCollection::contains(o);
}

bool java::util::Collections_UnmodifiableList::containsAll(Collection* c)
{
    return Collections_UnmodifiableCollection::containsAll(c);
}

bool java::util::Collections_UnmodifiableList::isEmpty()
{
    return Collections_UnmodifiableCollection::isEmpty();
}

java::util::Iterator* java::util::Collections_UnmodifiableList::iterator()
{
    return java_cast< Iterator* >(Collections_UnmodifiableCollection::iterator());
}

bool java::util::Collections_UnmodifiableList::remove(::java::lang::Object* o)
{
    return Collections_UnmodifiableCollection::remove(o);
}

bool java::util::Collections_UnmodifiableList::removeAll(Collection* c)
{
    return Collections_UnmodifiableCollection::removeAll(c);
}

bool java::util::Collections_UnmodifiableList::retainAll(Collection* c)
{
    return Collections_UnmodifiableCollection::retainAll(c);
}

int32_t java::util::Collections_UnmodifiableList::size()
{
    return Collections_UnmodifiableCollection::size();
}

java::lang::ObjectArray* java::util::Collections_UnmodifiableList::toArray_()
{
    return Collections_UnmodifiableCollection::toArray_();
}

java::lang::ObjectArray* java::util::Collections_UnmodifiableList::toArray_(::java::lang::ObjectArray* a)
{
    return java_cast< ::java::lang::ObjectArray* >(Collections_UnmodifiableCollection::toArray_(a));
}

java::lang::Class* java::util::Collections_UnmodifiableList::getClass0()
{
    return class_();
}


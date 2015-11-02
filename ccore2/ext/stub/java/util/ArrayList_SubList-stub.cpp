// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/ArrayList_SubList.hpp>

#include <java/util/ArrayList.hpp>
#include <java/util/ListIterator.hpp>

extern void unimplemented_(const char16_t* name);
java::util::ArrayList_SubList::ArrayList_SubList(ArrayList *ArrayList_this, const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
    , ArrayList_this(ArrayList_this)
{
    clinit();
}

java::util::ArrayList_SubList::ArrayList_SubList(ArrayList *ArrayList_this, AbstractList* parent, int32_t offset, int32_t fromIndex, int32_t toIndex)
    : ArrayList_SubList(ArrayList_this, *static_cast< ::default_init_tag* >(0))
{
    ctor(parent, offset, fromIndex, toIndex);
}


void ::java::util::ArrayList_SubList::ctor(AbstractList* parent, int32_t offset, int32_t fromIndex, int32_t toIndex)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::ArrayList_SubList::ctor(AbstractList* parent, int32_t offset, int32_t fromIndex, int32_t toIndex)");
}

void java::util::ArrayList_SubList::add(int32_t index, ::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"void java::util::ArrayList_SubList::add(int32_t index, ::java::lang::Object* e)");
}

bool java::util::ArrayList_SubList::addAll(Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::ArrayList_SubList::addAll(Collection* c)");
    return 0;
}

bool java::util::ArrayList_SubList::addAll(int32_t index, Collection* c)
{ /* stub */
    unimplemented_(u"bool java::util::ArrayList_SubList::addAll(int32_t index, Collection* c)");
    return 0;
}

/* private: void java::util::ArrayList_SubList::checkForComodification() */
java::lang::Object* java::util::ArrayList_SubList::get(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::ArrayList_SubList::get(int32_t index)");
    return 0;
}

java::util::Iterator* java::util::ArrayList_SubList::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::ArrayList_SubList::iterator()");
    return 0;
}

java::util::ListIterator* java::util::ArrayList_SubList::listIterator(int32_t index)
{ /* stub */
    unimplemented_(u"java::util::ListIterator* java::util::ArrayList_SubList::listIterator(int32_t index)");
    return 0;
}

/* private: java::lang::String* java::util::ArrayList_SubList::outOfBoundsMsg(int32_t index) */
/* private: void java::util::ArrayList_SubList::rangeCheck(int32_t index) */
/* private: void java::util::ArrayList_SubList::rangeCheckForAdd(int32_t index) */
java::lang::Object* java::util::ArrayList_SubList::remove(int32_t index)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::ArrayList_SubList::remove(int32_t index)");
    return 0;
}

void java::util::ArrayList_SubList::removeRange(int32_t fromIndex, int32_t toIndex)
{ /* stub */
    unimplemented_(u"void java::util::ArrayList_SubList::removeRange(int32_t fromIndex, int32_t toIndex)");
}

java::lang::Object* java::util::ArrayList_SubList::set(int32_t index, ::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::ArrayList_SubList::set(int32_t index, ::java::lang::Object* e)");
    return 0;
}

int32_t java::util::ArrayList_SubList::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::ArrayList_SubList::size()");
    return 0;
}

java::util::List* java::util::ArrayList_SubList::subList(int32_t fromIndex, int32_t toIndex)
{ /* stub */
    unimplemented_(u"java::util::List* java::util::ArrayList_SubList::subList(int32_t fromIndex, int32_t toIndex)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::ArrayList_SubList::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.ArrayList.SubList", 27);
    return c;
}

bool java::util::ArrayList_SubList::add(::java::lang::Object* e)
{
    return super::add(e);
}

java::util::ListIterator* java::util::ArrayList_SubList::listIterator()
{
    return super::listIterator();
}

bool java::util::ArrayList_SubList::remove(::java::lang::Object* o)
{
    return super::remove(o);
}

java::lang::Class* java::util::ArrayList_SubList::getClass0()
{
    return class_();
}


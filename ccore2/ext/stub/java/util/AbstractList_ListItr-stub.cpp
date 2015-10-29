// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/AbstractList_ListItr.hpp>

#include <java/lang/ClassCastException.hpp>
#include <java/lang/Object.hpp>
#include <java/util/AbstractList.hpp>

template<typename T, typename U>
static T java_cast(U* u)
{
    if(!u) return static_cast<T>(nullptr);
    auto t = dynamic_cast<T>(u);
    if(!t) throw new ::java::lang::ClassCastException();
    return t;
}

extern void unimplemented_(const char16_t* name);
java::util::AbstractList_ListItr::AbstractList_ListItr(AbstractList *AbstractList_this, const ::default_init_tag&)
    : super(AbstractList_this, *static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::AbstractList_ListItr::AbstractList_ListItr(AbstractList *AbstractList_this, int32_t index)
    : AbstractList_ListItr(AbstractList_this, *static_cast< ::default_init_tag* >(0))
{
    ctor(index);
}


void ::java::util::AbstractList_ListItr::ctor(int32_t index)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::AbstractList_ListItr::ctor(int32_t index)");
}

void java::util::AbstractList_ListItr::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"void java::util::AbstractList_ListItr::add(::java::lang::Object* e)");
}

bool java::util::AbstractList_ListItr::hasPrevious()
{ /* stub */
    unimplemented_(u"bool java::util::AbstractList_ListItr::hasPrevious()");
    return 0;
}

int32_t java::util::AbstractList_ListItr::nextIndex()
{ /* stub */
    unimplemented_(u"int32_t java::util::AbstractList_ListItr::nextIndex()");
    return 0;
}

java::lang::Object* java::util::AbstractList_ListItr::previous()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::AbstractList_ListItr::previous()");
    return 0;
}

int32_t java::util::AbstractList_ListItr::previousIndex()
{ /* stub */
    unimplemented_(u"int32_t java::util::AbstractList_ListItr::previousIndex()");
    return 0;
}

void java::util::AbstractList_ListItr::set(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"void java::util::AbstractList_ListItr::set(::java::lang::Object* e)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::AbstractList_ListItr::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.AbstractList.ListItr", 30);
    return c;
}

bool java::util::AbstractList_ListItr::hasNext()
{
    return AbstractList_Itr::hasNext();
}

java::lang::Object* java::util::AbstractList_ListItr::next()
{
    return java_cast< ::java::lang::Object* >(AbstractList_Itr::next());
}

void java::util::AbstractList_ListItr::remove()
{
    AbstractList_Itr::remove();
}

java::lang::Class* java::util::AbstractList_ListItr::getClass0()
{
    return class_();
}


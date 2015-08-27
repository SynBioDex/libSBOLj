// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/ArrayList_ListItr.hpp>

#include <java/lang/ClassCastException.hpp>
#include <java/lang/Object.hpp>
#include <java/util/ArrayList.hpp>

template<typename T, typename U>
static T java_cast(U* u)
{
    if(!u) return static_cast<T>(nullptr);
    auto t = dynamic_cast<T>(u);
    if(!t) throw new ::java::lang::ClassCastException();
    return t;
}

extern void unimplemented_(const char16_t* name);
java::util::ArrayList_ListItr::ArrayList_ListItr(ArrayList *ArrayList_this, const ::default_init_tag&)
    : super(ArrayList_this, *static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::ArrayList_ListItr::ArrayList_ListItr(ArrayList *ArrayList_this, int32_t index)
    : ArrayList_ListItr(ArrayList_this, *static_cast< ::default_init_tag* >(0))
{
    ctor(index);
}


void ::java::util::ArrayList_ListItr::ctor(int32_t index)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::ArrayList_ListItr::ctor(int32_t index)");
}

void java::util::ArrayList_ListItr::add(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"void java::util::ArrayList_ListItr::add(::java::lang::Object* e)");
}

bool java::util::ArrayList_ListItr::hasPrevious()
{ /* stub */
    unimplemented_(u"bool java::util::ArrayList_ListItr::hasPrevious()");
    return 0;
}

int32_t java::util::ArrayList_ListItr::nextIndex()
{ /* stub */
    unimplemented_(u"int32_t java::util::ArrayList_ListItr::nextIndex()");
    return 0;
}

java::lang::Object* java::util::ArrayList_ListItr::previous()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::ArrayList_ListItr::previous()");
    return 0;
}

int32_t java::util::ArrayList_ListItr::previousIndex()
{ /* stub */
    unimplemented_(u"int32_t java::util::ArrayList_ListItr::previousIndex()");
    return 0;
}

void java::util::ArrayList_ListItr::set(::java::lang::Object* e)
{ /* stub */
    unimplemented_(u"void java::util::ArrayList_ListItr::set(::java::lang::Object* e)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::ArrayList_ListItr::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.ArrayList.ListItr", 27);
    return c;
}

bool java::util::ArrayList_ListItr::hasNext()
{
    return ArrayList_Itr::hasNext();
}

java::lang::Object* java::util::ArrayList_ListItr::next()
{
    return java_cast< ::java::lang::Object* >(ArrayList_Itr::next());
}

void java::util::ArrayList_ListItr::remove()
{
    ArrayList_Itr::remove();
}

java::lang::Class* java::util::ArrayList_ListItr::getClass0()
{
    return class_();
}


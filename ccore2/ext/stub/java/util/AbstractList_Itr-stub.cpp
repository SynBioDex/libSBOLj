// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/AbstractList_Itr.hpp>

#include <java/util/AbstractList.hpp>

extern void unimplemented_(const char16_t* name);
java::util::AbstractList_Itr::AbstractList_Itr(AbstractList *AbstractList_this, const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
    , AbstractList_this(AbstractList_this)
{
    clinit();
}


/* private: void ::java::util::AbstractList_Itr::ctor() */
void java::util::AbstractList_Itr::checkForComodification()
{ /* stub */
    unimplemented_(u"void java::util::AbstractList_Itr::checkForComodification()");
}

bool java::util::AbstractList_Itr::hasNext()
{ /* stub */
    unimplemented_(u"bool java::util::AbstractList_Itr::hasNext()");
    return 0;
}

java::lang::Object* java::util::AbstractList_Itr::next()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::AbstractList_Itr::next()");
    return 0;
}

void java::util::AbstractList_Itr::remove()
{ /* stub */
    unimplemented_(u"void java::util::AbstractList_Itr::remove()");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::AbstractList_Itr::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.AbstractList.Itr", 26);
    return c;
}

java::lang::Class* java::util::AbstractList_Itr::getClass0()
{
    return class_();
}


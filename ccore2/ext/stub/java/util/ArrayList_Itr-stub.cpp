// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/ArrayList_Itr.hpp>

#include <java/util/ArrayList.hpp>

extern void unimplemented_(const char16_t* name);
java::util::ArrayList_Itr::ArrayList_Itr(ArrayList *ArrayList_this, const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
    , ArrayList_this(ArrayList_this)
{
    clinit();
}


/* private: void ::java::util::ArrayList_Itr::ctor() */
void java::util::ArrayList_Itr::checkForComodification()
{ /* stub */
    unimplemented_(u"void java::util::ArrayList_Itr::checkForComodification()");
}

bool java::util::ArrayList_Itr::hasNext()
{ /* stub */
    unimplemented_(u"bool java::util::ArrayList_Itr::hasNext()");
    return 0;
}

java::lang::Object* java::util::ArrayList_Itr::next()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::ArrayList_Itr::next()");
    return 0;
}

void java::util::ArrayList_Itr::remove()
{ /* stub */
    unimplemented_(u"void java::util::ArrayList_Itr::remove()");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::ArrayList_Itr::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.ArrayList.Itr", 23);
    return c;
}

java::lang::Class* java::util::ArrayList_Itr::getClass0()
{
    return class_();
}


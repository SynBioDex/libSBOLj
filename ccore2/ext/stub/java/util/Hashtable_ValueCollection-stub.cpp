// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Hashtable_ValueCollection.hpp>

#include <java/util/Hashtable.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Hashtable_ValueCollection::Hashtable_ValueCollection(Hashtable *Hashtable_this, const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
    , Hashtable_this(Hashtable_this)
{
    clinit();
}


/* private: void ::java::util::Hashtable_ValueCollection::ctor() */
void java::util::Hashtable_ValueCollection::clear()
{ /* stub */
    unimplemented_(u"void java::util::Hashtable_ValueCollection::clear()");
}

bool java::util::Hashtable_ValueCollection::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Hashtable_ValueCollection::contains(::java::lang::Object* o)");
    return 0;
}

java::util::Iterator* java::util::Hashtable_ValueCollection::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::Hashtable_ValueCollection::iterator()");
    return 0;
}

int32_t java::util::Hashtable_ValueCollection::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::Hashtable_ValueCollection::size()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Hashtable_ValueCollection::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Hashtable.ValueCollection", 35);
    return c;
}

java::lang::Class* java::util::Hashtable_ValueCollection::getClass0()
{
    return class_();
}


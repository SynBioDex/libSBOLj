// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/HashMap_Values.hpp>

#include <java/util/HashMap.hpp>

extern void unimplemented_(const char16_t* name);
java::util::HashMap_Values::HashMap_Values(HashMap *HashMap_this, const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
    , HashMap_this(HashMap_this)
{
    clinit();
}


/* private: void ::java::util::HashMap_Values::ctor() */
void java::util::HashMap_Values::clear()
{ /* stub */
    unimplemented_(u"void java::util::HashMap_Values::clear()");
}

bool java::util::HashMap_Values::contains(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::HashMap_Values::contains(::java::lang::Object* o)");
    return 0;
}

java::util::Iterator* java::util::HashMap_Values::iterator()
{ /* stub */
    unimplemented_(u"java::util::Iterator* java::util::HashMap_Values::iterator()");
    return 0;
}

int32_t java::util::HashMap_Values::size()
{ /* stub */
    unimplemented_(u"int32_t java::util::HashMap_Values::size()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::HashMap_Values::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.HashMap.Values", 24);
    return c;
}

java::lang::Class* java::util::HashMap_Values::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Character_Subset.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Character_Subset::Character_Subset(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Character_Subset::Character_Subset(String* name)
    : Character_Subset(*static_cast< ::default_init_tag* >(0))
{
    ctor(name);
}


void ::java::lang::Character_Subset::ctor(String* name)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Character_Subset::ctor(String* name)");
}

bool java::lang::Character_Subset::equals(Object* obj)
{ /* stub */
    unimplemented_(u"bool java::lang::Character_Subset::equals(Object* obj)");
    return 0;
}

int32_t java::lang::Character_Subset::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::lang::Character_Subset::hashCode()");
    return 0;
}

java::lang::String* java::lang::Character_Subset::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Character_Subset::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Character_Subset::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Character.Subset", 26);
    return c;
}

java::lang::Class* java::lang::Character_Subset::getClass0()
{
    return class_();
}


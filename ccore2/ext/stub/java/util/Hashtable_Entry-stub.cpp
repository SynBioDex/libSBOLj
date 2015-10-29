// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Hashtable_Entry.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Hashtable_Entry::Hashtable_Entry(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Hashtable_Entry::Hashtable_Entry(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, Hashtable_Entry* next)
    : Hashtable_Entry(*static_cast< ::default_init_tag* >(0))
{
    ctor(hash, key, value, next);
}


void ::java::util::Hashtable_Entry::ctor(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, Hashtable_Entry* next)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Hashtable_Entry::ctor(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, Hashtable_Entry* next)");
}

java::lang::Object* java::util::Hashtable_Entry::clone()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Hashtable_Entry::clone()");
    return 0;
}

bool java::util::Hashtable_Entry::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::Hashtable_Entry::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::Hashtable_Entry::getKey()
{ /* stub */
return key ; /* getter */
}

java::lang::Object* java::util::Hashtable_Entry::getValue()
{ /* stub */
return value ; /* getter */
}

int32_t java::util::Hashtable_Entry::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Hashtable_Entry::hashCode()");
    return 0;
}

java::lang::Object* java::util::Hashtable_Entry::setValue(::java::lang::Object* value)
{ /* stub */
}

java::lang::String* java::util::Hashtable_Entry::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Hashtable_Entry::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Hashtable_Entry::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Hashtable.Entry", 25);
    return c;
}

java::lang::Class* java::util::Hashtable_Entry::getClass0()
{
    return class_();
}


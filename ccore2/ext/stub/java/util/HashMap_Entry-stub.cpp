// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/HashMap_Entry.hpp>

extern void unimplemented_(const char16_t* name);
java::util::HashMap_Entry::HashMap_Entry(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::HashMap_Entry::HashMap_Entry(int32_t h, ::java::lang::Object* k, ::java::lang::Object* v, HashMap_Entry* n)
    : HashMap_Entry(*static_cast< ::default_init_tag* >(0))
{
    ctor(h, k, v, n);
}


void ::java::util::HashMap_Entry::ctor(int32_t h, ::java::lang::Object* k, ::java::lang::Object* v, HashMap_Entry* n)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::HashMap_Entry::ctor(int32_t h, ::java::lang::Object* k, ::java::lang::Object* v, HashMap_Entry* n)");
}

bool java::util::HashMap_Entry::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::HashMap_Entry::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::HashMap_Entry::getKey()
{ /* stub */
return key ; /* getter */
}

java::lang::Object* java::util::HashMap_Entry::getValue()
{ /* stub */
return value ; /* getter */
}

int32_t java::util::HashMap_Entry::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::HashMap_Entry::hashCode()");
    return 0;
}

void java::util::HashMap_Entry::recordAccess(HashMap* m)
{ /* stub */
    unimplemented_(u"void java::util::HashMap_Entry::recordAccess(HashMap* m)");
}

void java::util::HashMap_Entry::recordRemoval(HashMap* m)
{ /* stub */
    unimplemented_(u"void java::util::HashMap_Entry::recordRemoval(HashMap* m)");
}

java::lang::Object* java::util::HashMap_Entry::setValue(::java::lang::Object* newValue)
{ /* stub */
}

java::lang::String* java::util::HashMap_Entry::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::HashMap_Entry::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::HashMap_Entry::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.HashMap.Entry", 23);
    return c;
}

java::lang::Class* java::util::HashMap_Entry::getClass0()
{
    return class_();
}


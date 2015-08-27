// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/AbstractMap_SimpleEntry.hpp>

extern void unimplemented_(const char16_t* name);
java::util::AbstractMap_SimpleEntry::AbstractMap_SimpleEntry(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::AbstractMap_SimpleEntry::AbstractMap_SimpleEntry(Map_Entry* entry)
    : AbstractMap_SimpleEntry(*static_cast< ::default_init_tag* >(0))
{
    ctor(entry);
}

java::util::AbstractMap_SimpleEntry::AbstractMap_SimpleEntry(::java::lang::Object* key, ::java::lang::Object* value)
    : AbstractMap_SimpleEntry(*static_cast< ::default_init_tag* >(0))
{
    ctor(key, value);
}

constexpr int64_t java::util::AbstractMap_SimpleEntry::serialVersionUID;

void ::java::util::AbstractMap_SimpleEntry::ctor(Map_Entry* entry)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::AbstractMap_SimpleEntry::ctor(Map_Entry* entry)");
}

void ::java::util::AbstractMap_SimpleEntry::ctor(::java::lang::Object* key, ::java::lang::Object* value)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::AbstractMap_SimpleEntry::ctor(::java::lang::Object* key, ::java::lang::Object* value)");
}

bool java::util::AbstractMap_SimpleEntry::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractMap_SimpleEntry::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::AbstractMap_SimpleEntry::getKey()
{ /* stub */
return key ; /* getter */
}

java::lang::Object* java::util::AbstractMap_SimpleEntry::getValue()
{ /* stub */
return value ; /* getter */
}

int32_t java::util::AbstractMap_SimpleEntry::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::AbstractMap_SimpleEntry::hashCode()");
    return 0;
}

java::lang::Object* java::util::AbstractMap_SimpleEntry::setValue(::java::lang::Object* value)
{ /* stub */
}

java::lang::String* java::util::AbstractMap_SimpleEntry::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::AbstractMap_SimpleEntry::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::AbstractMap_SimpleEntry::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.AbstractMap.SimpleEntry", 33);
    return c;
}

java::lang::Class* java::util::AbstractMap_SimpleEntry::getClass0()
{
    return class_();
}


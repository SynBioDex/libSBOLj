// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/AbstractMap_SimpleImmutableEntry.hpp>

extern void unimplemented_(const char16_t* name);
java::util::AbstractMap_SimpleImmutableEntry::AbstractMap_SimpleImmutableEntry(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::AbstractMap_SimpleImmutableEntry::AbstractMap_SimpleImmutableEntry(Map_Entry* entry)
    : AbstractMap_SimpleImmutableEntry(*static_cast< ::default_init_tag* >(0))
{
    ctor(entry);
}

java::util::AbstractMap_SimpleImmutableEntry::AbstractMap_SimpleImmutableEntry(::java::lang::Object* key, ::java::lang::Object* value)
    : AbstractMap_SimpleImmutableEntry(*static_cast< ::default_init_tag* >(0))
{
    ctor(key, value);
}

constexpr int64_t java::util::AbstractMap_SimpleImmutableEntry::serialVersionUID;

void ::java::util::AbstractMap_SimpleImmutableEntry::ctor(Map_Entry* entry)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::AbstractMap_SimpleImmutableEntry::ctor(Map_Entry* entry)");
}

void ::java::util::AbstractMap_SimpleImmutableEntry::ctor(::java::lang::Object* key, ::java::lang::Object* value)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::AbstractMap_SimpleImmutableEntry::ctor(::java::lang::Object* key, ::java::lang::Object* value)");
}

bool java::util::AbstractMap_SimpleImmutableEntry::equals(::java::lang::Object* o)
{ /* stub */
    unimplemented_(u"bool java::util::AbstractMap_SimpleImmutableEntry::equals(::java::lang::Object* o)");
    return 0;
}

java::lang::Object* java::util::AbstractMap_SimpleImmutableEntry::getKey()
{ /* stub */
return key ; /* getter */
}

java::lang::Object* java::util::AbstractMap_SimpleImmutableEntry::getValue()
{ /* stub */
return value ; /* getter */
}

int32_t java::util::AbstractMap_SimpleImmutableEntry::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::AbstractMap_SimpleImmutableEntry::hashCode()");
    return 0;
}

java::lang::Object* java::util::AbstractMap_SimpleImmutableEntry::setValue(::java::lang::Object* value)
{ /* stub */
}

java::lang::String* java::util::AbstractMap_SimpleImmutableEntry::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::AbstractMap_SimpleImmutableEntry::toString()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::AbstractMap_SimpleImmutableEntry::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.AbstractMap.SimpleImmutableEntry", 42);
    return c;
}

java::lang::Class* java::util::AbstractMap_SimpleImmutableEntry::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Enum.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Enum::Enum(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Enum::Enum(String* name, int32_t ordinal)
    : Enum(*static_cast< ::default_init_tag* >(0))
{
    ctor(name, ordinal);
}


void ::java::lang::Enum::ctor(String* name, int32_t ordinal)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Enum::ctor(String* name, int32_t ordinal)");
}

java::lang::Object* java::lang::Enum::clone()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::lang::Enum::clone()");
    return 0;
}

int32_t java::lang::Enum::compareTo(Enum* o)
{ /* stub */
    unimplemented_(u"int32_t java::lang::Enum::compareTo(Enum* o)");
    return 0;
}

int32_t java::lang::Enum::compareTo(Object* o)
{ 
    return compareTo(dynamic_cast< Enum* >(o));
}

bool java::lang::Enum::equals(Object* other)
{ /* stub */
    unimplemented_(u"bool java::lang::Enum::equals(Object* other)");
    return 0;
}

void java::lang::Enum::finalize()
{ /* stub */
    unimplemented_(u"void java::lang::Enum::finalize()");
}

java::lang::Class* java::lang::Enum::getDeclaringClass()
{ /* stub */
    unimplemented_(u"java::lang::Class* java::lang::Enum::getDeclaringClass()");
    return 0;
}

int32_t java::lang::Enum::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::lang::Enum::hashCode()");
    return 0;
}

java::lang::String* java::lang::Enum::name()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Enum::name()");
    return 0;
}

int32_t java::lang::Enum::ordinal()
{ /* stub */
    unimplemented_(u"int32_t java::lang::Enum::ordinal()");
    return 0;
}

/* private: void java::lang::Enum::readObject(::java::io::ObjectInputStream* in) */
/* private: void java::lang::Enum::readObjectNoData() */
java::lang::String* java::lang::Enum::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Enum::toString()");
    return 0;
}

java::lang::Enum* java::lang::Enum::valueOf(Class* enumType, String* name)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::Enum* java::lang::Enum::valueOf(Class* enumType, String* name)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Enum::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Enum", 14);
    return c;
}

java::lang::Class* java::lang::Enum::getClass0()
{
    return class_();
}


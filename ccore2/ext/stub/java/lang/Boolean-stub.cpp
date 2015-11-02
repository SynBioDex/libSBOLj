// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Boolean.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Boolean::Boolean(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Boolean::Boolean(bool value)
    : Boolean(*static_cast< ::default_init_tag* >(0))
{
    ctor(value);
}

java::lang::Boolean::Boolean(String* s)
    : Boolean(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}

java::lang::Boolean*& java::lang::Boolean::FALSE()
{
    clinit();
    return FALSE_;
}
java::lang::Boolean* java::lang::Boolean::FALSE_;
java::lang::Boolean*& java::lang::Boolean::TRUE()
{
    clinit();
    return TRUE_;
}
java::lang::Boolean* java::lang::Boolean::TRUE_;
java::lang::Class*& java::lang::Boolean::TYPE()
{
    clinit();
    return TYPE_;
}
java::lang::Class* java::lang::Boolean::TYPE_;
constexpr int64_t java::lang::Boolean::serialVersionUID;

void ::java::lang::Boolean::ctor(bool value)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Boolean::ctor(bool value)");
}

void ::java::lang::Boolean::ctor(String* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Boolean::ctor(String* s)");
}

bool java::lang::Boolean::booleanValue()
{ /* stub */
    unimplemented_(u"bool java::lang::Boolean::booleanValue()");
    return 0;
}

int32_t java::lang::Boolean::compare(bool x, bool y)
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::lang::Boolean::compare(bool x, bool y)");
    return 0;
}

int32_t java::lang::Boolean::compareTo(Boolean* b)
{ /* stub */
    unimplemented_(u"int32_t java::lang::Boolean::compareTo(Boolean* b)");
    return 0;
}

int32_t java::lang::Boolean::compareTo(Object* o)
{ 
    return compareTo(dynamic_cast< Boolean* >(o));
}

bool java::lang::Boolean::equals(Object* obj)
{ /* stub */
    unimplemented_(u"bool java::lang::Boolean::equals(Object* obj)");
    return 0;
}

bool java::lang::Boolean::getBoolean(String* name)
{ /* stub */
    clinit();
    unimplemented_(u"bool java::lang::Boolean::getBoolean(String* name)");
    return 0;
}

int32_t java::lang::Boolean::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::lang::Boolean::hashCode()");
    return 0;
}

bool java::lang::Boolean::parseBoolean(String* s)
{ /* stub */
    clinit();
    unimplemented_(u"bool java::lang::Boolean::parseBoolean(String* s)");
    return 0;
}

/* private: bool java::lang::Boolean::toBoolean(String* name) */
java::lang::String* java::lang::Boolean::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Boolean::toString()");
    return 0;
}

java::lang::String* java::lang::Boolean::toString(bool b)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::Boolean::toString(bool b)");
    return 0;
}

java::lang::Boolean* java::lang::Boolean::valueOf(bool b)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::Boolean* java::lang::Boolean::valueOf(bool b)");
    return 0;
}

java::lang::Boolean* java::lang::Boolean::valueOf(String* s)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::Boolean* java::lang::Boolean::valueOf(String* s)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Boolean::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Boolean", 17);
    return c;
}

java::lang::Class* java::lang::Boolean::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Double.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Double::Double(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Double::Double(double value)
    : Double(*static_cast< ::default_init_tag* >(0))
{
    ctor(value);
}

java::lang::Double::Double(String* s)
    : Double(*static_cast< ::default_init_tag* >(0))
{
    ctor(s);
}

constexpr int32_t java::lang::Double::MAX_EXPONENT;
constexpr double java::lang::Double::MAX_VALUE;
constexpr int32_t java::lang::Double::MIN_EXPONENT;
constexpr double java::lang::Double::MIN_NORMAL;
constexpr double java::lang::Double::MIN_VALUE;
constexpr double java::lang::Double::NEGATIVE_INFINITY;
constexpr double java::lang::Double::NaN;
constexpr double java::lang::Double::POSITIVE_INFINITY;
constexpr int32_t java::lang::Double::SIZE;
java::lang::Class*& java::lang::Double::TYPE()
{
    clinit();
    return TYPE_;
}
java::lang::Class* java::lang::Double::TYPE_;
constexpr int64_t java::lang::Double::serialVersionUID;

void ::java::lang::Double::ctor(double value)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Double::ctor(double value)");
}

void ::java::lang::Double::ctor(String* s)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Double::ctor(String* s)");
}

int8_t java::lang::Double::byteValue()
{ /* stub */
    unimplemented_(u"int8_t java::lang::Double::byteValue()");
    return 0;
}

int32_t java::lang::Double::compare(double d1, double d2)
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::lang::Double::compare(double d1, double d2)");
    return 0;
}

int32_t java::lang::Double::compareTo(Double* anotherDouble)
{ /* stub */
    unimplemented_(u"int32_t java::lang::Double::compareTo(Double* anotherDouble)");
    return 0;
}

int32_t java::lang::Double::compareTo(Object* o)
{ 
    return compareTo(dynamic_cast< Double* >(o));
}

int64_t java::lang::Double::doubleToLongBits(double value)
{ /* stub */
    clinit();
    unimplemented_(u"int64_t java::lang::Double::doubleToLongBits(double value)");
    return 0;
}

double java::lang::Double::doubleValue()
{ /* stub */
    unimplemented_(u"double java::lang::Double::doubleValue()");
    return 0;
}

bool java::lang::Double::equals(Object* obj)
{ /* stub */
    unimplemented_(u"bool java::lang::Double::equals(Object* obj)");
    return 0;
}

float java::lang::Double::floatValue()
{ /* stub */
    unimplemented_(u"float java::lang::Double::floatValue()");
    return 0;
}

int32_t java::lang::Double::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::lang::Double::hashCode()");
    return 0;
}

int32_t java::lang::Double::intValue()
{ /* stub */
    unimplemented_(u"int32_t java::lang::Double::intValue()");
    return 0;
}

bool java::lang::Double::isInfinite()
{ /* stub */
    unimplemented_(u"bool java::lang::Double::isInfinite()");
    return 0;
}

bool java::lang::Double::isInfinite(double v)
{ /* stub */
    clinit();
    unimplemented_(u"bool java::lang::Double::isInfinite(double v)");
    return 0;
}

bool java::lang::Double::isNaN()
{ /* stub */
    unimplemented_(u"bool java::lang::Double::isNaN()");
    return 0;
}

bool java::lang::Double::isNaN(double v)
{ /* stub */
    clinit();
    unimplemented_(u"bool java::lang::Double::isNaN(double v)");
    return 0;
}

int64_t java::lang::Double::longValue()
{ /* stub */
    unimplemented_(u"int64_t java::lang::Double::longValue()");
    return 0;
}

double java::lang::Double::parseDouble(String* s)
{ /* stub */
    clinit();
    unimplemented_(u"double java::lang::Double::parseDouble(String* s)");
    return 0;
}

int16_t java::lang::Double::shortValue()
{ /* stub */
    unimplemented_(u"int16_t java::lang::Double::shortValue()");
    return 0;
}

java::lang::String* java::lang::Double::toHexString(double d)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::Double::toHexString(double d)");
    return 0;
}

java::lang::String* java::lang::Double::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::Double::toString()");
    return 0;
}

java::lang::String* java::lang::Double::toString(double d)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::Double::toString(double d)");
    return 0;
}

java::lang::Double* java::lang::Double::valueOf(String* s)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::Double* java::lang::Double::valueOf(String* s)");
    return 0;
}

java::lang::Double* java::lang::Double::valueOf(double d)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::Double* java::lang::Double::valueOf(double d)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Double::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Double", 16);
    return c;
}

java::lang::Class* java::lang::Double::getClass0()
{
    return class_();
}


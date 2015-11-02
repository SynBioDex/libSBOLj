// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/String.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::CharSequence, ObjectArray > CharSequenceArray;
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::String, ObjectArray, ::java::io::SerializableArray, ComparableArray, CharSequenceArray > StringArray;
    } // lang
} // java

extern void unimplemented_(const char16_t* name);
java::lang::String::String(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::String::String()
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::String::String(String* original)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(original);
}

java::lang::String::String(::char16_tArray* value)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(value);
}

java::lang::String::String(::int8_tArray* bytes)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(bytes);
}

java::lang::String::String(StringBuffer* buffer)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(buffer);
}

java::lang::String::String(StringBuilder* builder)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(builder);
}

java::lang::String::String(::int8_tArray* ascii, int32_t hibyte)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(ascii, hibyte);
}

java::lang::String::String(::int8_tArray* bytes, String* charsetName)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(bytes, charsetName);
}

java::lang::String::String(::int8_tArray* bytes, ::java::nio::charset::Charset* charset)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(bytes, charset);
}

java::lang::String::String(::char16_tArray* value, bool share)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(value, share);
}

java::lang::String::String(::char16_tArray* value, int32_t offset, int32_t count)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(value, offset, count);
}

java::lang::String::String(::int32_tArray* codePoints, int32_t offset, int32_t count)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(codePoints, offset, count);
}

java::lang::String::String(::int8_tArray* bytes, int32_t offset, int32_t length)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(bytes, offset, length);
}

java::lang::String::String(int32_t offset, int32_t count, ::char16_tArray* value)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(offset, count, value);
}

java::lang::String::String(::int8_tArray* ascii, int32_t hibyte, int32_t offset, int32_t count)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(ascii, hibyte, offset, count);
}

java::lang::String::String(::int8_tArray* bytes, int32_t offset, int32_t length, String* charsetName)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(bytes, offset, length, charsetName);
}

java::lang::String::String(::int8_tArray* bytes, int32_t offset, int32_t length, ::java::nio::charset::Charset* charset)
    : String(*static_cast< ::default_init_tag* >(0))
{
    ctor(bytes, offset, length, charset);
}

java::util::Comparator*& java::lang::String::CASE_INSENSITIVE_ORDER()
{
    clinit();
    return CASE_INSENSITIVE_ORDER_;
}
java::util::Comparator* java::lang::String::CASE_INSENSITIVE_ORDER_;
int32_t& java::lang::String::HASHING_SEED()
{
    clinit();
    return HASHING_SEED_;
}
int32_t java::lang::String::HASHING_SEED_;
java::io::ObjectStreamFieldArray*& java::lang::String::serialPersistentFields()
{
    clinit();
    return serialPersistentFields_;
}
java::io::ObjectStreamFieldArray* java::lang::String::serialPersistentFields_;
constexpr int64_t java::lang::String::serialVersionUID;

void ::java::lang::String::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor()");
}

void ::java::lang::String::ctor(String* original)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(String* original)");
}

void ::java::lang::String::ctor(::char16_tArray* value)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::char16_tArray* value)");
}

void ::java::lang::String::ctor(::int8_tArray* bytes)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::int8_tArray* bytes)");
}

void ::java::lang::String::ctor(StringBuffer* buffer)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(StringBuffer* buffer)");
}

void ::java::lang::String::ctor(StringBuilder* builder)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(StringBuilder* builder)");
}

void ::java::lang::String::ctor(::int8_tArray* ascii, int32_t hibyte)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::int8_tArray* ascii, int32_t hibyte)");
}

void ::java::lang::String::ctor(::int8_tArray* bytes, String* charsetName)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::int8_tArray* bytes, String* charsetName)");
}

void ::java::lang::String::ctor(::int8_tArray* bytes, ::java::nio::charset::Charset* charset)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::int8_tArray* bytes, ::java::nio::charset::Charset* charset)");
}

void ::java::lang::String::ctor(::char16_tArray* value, bool share)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::char16_tArray* value, bool share)");
}

void ::java::lang::String::ctor(::char16_tArray* value, int32_t offset, int32_t count)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::char16_tArray* value, int32_t offset, int32_t count)");
}

void ::java::lang::String::ctor(::int32_tArray* codePoints, int32_t offset, int32_t count)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::int32_tArray* codePoints, int32_t offset, int32_t count)");
}

void ::java::lang::String::ctor(::int8_tArray* bytes, int32_t offset, int32_t length)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::int8_tArray* bytes, int32_t offset, int32_t length)");
}

void ::java::lang::String::ctor(int32_t offset, int32_t count, ::char16_tArray* value)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(int32_t offset, int32_t count, ::char16_tArray* value)");
}

void ::java::lang::String::ctor(::int8_tArray* ascii, int32_t hibyte, int32_t offset, int32_t count)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::int8_tArray* ascii, int32_t hibyte, int32_t offset, int32_t count)");
}

void ::java::lang::String::ctor(::int8_tArray* bytes, int32_t offset, int32_t length, String* charsetName)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::int8_tArray* bytes, int32_t offset, int32_t length, String* charsetName)");
}

void ::java::lang::String::ctor(::int8_tArray* bytes, int32_t offset, int32_t length, ::java::nio::charset::Charset* charset)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::String::ctor(::int8_tArray* bytes, int32_t offset, int32_t length, ::java::nio::charset::Charset* charset)");
}

char16_t java::lang::String::charAt(int32_t index)
{ /* stub */
    unimplemented_(u"char16_t java::lang::String::charAt(int32_t index)");
    return 0;
}

/* private: void java::lang::String::checkBounds(::int8_tArray* bytes, int32_t offset, int32_t length) */
int32_t java::lang::String::codePointAt(int32_t index)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::codePointAt(int32_t index)");
    return 0;
}

int32_t java::lang::String::codePointBefore(int32_t index)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::codePointBefore(int32_t index)");
    return 0;
}

int32_t java::lang::String::codePointCount(int32_t beginIndex, int32_t endIndex)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::codePointCount(int32_t beginIndex, int32_t endIndex)");
    return 0;
}

int32_t java::lang::String::compareTo(String* anotherString)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::compareTo(String* anotherString)");
    return 0;
}

int32_t java::lang::String::compareTo(Object* o)
{ 
    return compareTo(dynamic_cast< String* >(o));
}

int32_t java::lang::String::compareToIgnoreCase(String* str)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::compareToIgnoreCase(String* str)");
    return 0;
}

java::lang::String* java::lang::String::concat(String* str)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::concat(String* str)");
    return 0;
}

bool java::lang::String::contains(CharSequence* s)
{ /* stub */
    unimplemented_(u"bool java::lang::String::contains(CharSequence* s)");
    return 0;
}

bool java::lang::String::contentEquals(StringBuffer* sb)
{ /* stub */
    unimplemented_(u"bool java::lang::String::contentEquals(StringBuffer* sb)");
    return 0;
}

bool java::lang::String::contentEquals(CharSequence* cs)
{ /* stub */
    unimplemented_(u"bool java::lang::String::contentEquals(CharSequence* cs)");
    return 0;
}

java::lang::String* java::lang::String::copyValueOf(::char16_tArray* data)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::copyValueOf(::char16_tArray* data)");
    return 0;
}

java::lang::String* java::lang::String::copyValueOf(::char16_tArray* data, int32_t offset, int32_t count)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::copyValueOf(::char16_tArray* data, int32_t offset, int32_t count)");
    return 0;
}

bool java::lang::String::endsWith(String* suffix)
{ /* stub */
    unimplemented_(u"bool java::lang::String::endsWith(String* suffix)");
    return 0;
}

bool java::lang::String::equals(Object* anObject)
{ /* stub */
    unimplemented_(u"bool java::lang::String::equals(Object* anObject)");
    return 0;
}

bool java::lang::String::equalsIgnoreCase(String* anotherString)
{ /* stub */
    unimplemented_(u"bool java::lang::String::equalsIgnoreCase(String* anotherString)");
    return 0;
}

java::lang::String* java::lang::String::format(String* format, ObjectArray* args)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::format(String* format, ObjectArray* args)");
    return 0;
}

java::lang::String* java::lang::String::format(::java::util::Locale* l, String* format, ObjectArray* args)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::format(::java::util::Locale* l, String* format, ObjectArray* args)");
    return 0;
}

int8_tArray* java::lang::String::getBytes()
{ /* stub */
    unimplemented_(u"int8_tArray* java::lang::String::getBytes()");
    return 0;
}

int8_tArray* java::lang::String::getBytes(String* charsetName)
{ /* stub */
    unimplemented_(u"int8_tArray* java::lang::String::getBytes(String* charsetName)");
    return 0;
}

int8_tArray* java::lang::String::getBytes(::java::nio::charset::Charset* charset)
{ /* stub */
    unimplemented_(u"int8_tArray* java::lang::String::getBytes(::java::nio::charset::Charset* charset)");
    return 0;
}

void java::lang::String::getBytes(int32_t srcBegin, int32_t srcEnd, ::int8_tArray* dst, int32_t dstBegin)
{ /* stub */
    unimplemented_(u"void java::lang::String::getBytes(int32_t srcBegin, int32_t srcEnd, ::int8_tArray* dst, int32_t dstBegin)");
}

void java::lang::String::getChars(::char16_tArray* dst, int32_t dstBegin)
{ /* stub */
    unimplemented_(u"void java::lang::String::getChars(::char16_tArray* dst, int32_t dstBegin)");
}

void java::lang::String::getChars(int32_t srcBegin, int32_t srcEnd, ::char16_tArray* dst, int32_t dstBegin)
{ /* stub */
    unimplemented_(u"void java::lang::String::getChars(int32_t srcBegin, int32_t srcEnd, ::char16_tArray* dst, int32_t dstBegin)");
}

int32_t java::lang::String::hash32()
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::hash32()");
    return 0;
}

int32_t java::lang::String::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::hashCode()");
    return 0;
}

int32_t java::lang::String::indexOf(int32_t ch)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::indexOf(int32_t ch)");
    return 0;
}

int32_t java::lang::String::indexOf(String* str)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::indexOf(String* str)");
    return 0;
}

int32_t java::lang::String::indexOf(int32_t ch, int32_t fromIndex)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::indexOf(int32_t ch, int32_t fromIndex)");
    return 0;
}

int32_t java::lang::String::indexOf(String* str, int32_t fromIndex)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::indexOf(String* str, int32_t fromIndex)");
    return 0;
}

int32_t java::lang::String::indexOf(::char16_tArray* source, int32_t sourceOffset, int32_t sourceCount, ::char16_tArray* target, int32_t targetOffset, int32_t targetCount, int32_t fromIndex)
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::lang::String::indexOf(::char16_tArray* source, int32_t sourceOffset, int32_t sourceCount, ::char16_tArray* target, int32_t targetOffset, int32_t targetCount, int32_t fromIndex)");
    return 0;
}

/* private: int32_t java::lang::String::indexOfSupplementary(int32_t ch, int32_t fromIndex) */
bool java::lang::String::isEmpty()
{ /* stub */
    unimplemented_(u"bool java::lang::String::isEmpty()");
    return 0;
}

int32_t java::lang::String::lastIndexOf(int32_t ch)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::lastIndexOf(int32_t ch)");
    return 0;
}

int32_t java::lang::String::lastIndexOf(String* str)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::lastIndexOf(String* str)");
    return 0;
}

int32_t java::lang::String::lastIndexOf(int32_t ch, int32_t fromIndex)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::lastIndexOf(int32_t ch, int32_t fromIndex)");
    return 0;
}

int32_t java::lang::String::lastIndexOf(String* str, int32_t fromIndex)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::lastIndexOf(String* str, int32_t fromIndex)");
    return 0;
}

int32_t java::lang::String::lastIndexOf(::char16_tArray* source, int32_t sourceOffset, int32_t sourceCount, ::char16_tArray* target, int32_t targetOffset, int32_t targetCount, int32_t fromIndex)
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::lang::String::lastIndexOf(::char16_tArray* source, int32_t sourceOffset, int32_t sourceCount, ::char16_tArray* target, int32_t targetOffset, int32_t targetCount, int32_t fromIndex)");
    return 0;
}

/* private: int32_t java::lang::String::lastIndexOfSupplementary(int32_t ch, int32_t fromIndex) */
int32_t java::lang::String::length()
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::length()");
    return 0;
}

bool java::lang::String::matches(String* regex)
{ /* stub */
    unimplemented_(u"bool java::lang::String::matches(String* regex)");
    return 0;
}

int32_t java::lang::String::offsetByCodePoints(int32_t index, int32_t codePointOffset)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String::offsetByCodePoints(int32_t index, int32_t codePointOffset)");
    return 0;
}

bool java::lang::String::regionMatches(int32_t toffset, String* other, int32_t ooffset, int32_t len)
{ /* stub */
    unimplemented_(u"bool java::lang::String::regionMatches(int32_t toffset, String* other, int32_t ooffset, int32_t len)");
    return 0;
}

bool java::lang::String::regionMatches(bool ignoreCase, int32_t toffset, String* other, int32_t ooffset, int32_t len)
{ /* stub */
    unimplemented_(u"bool java::lang::String::regionMatches(bool ignoreCase, int32_t toffset, String* other, int32_t ooffset, int32_t len)");
    return 0;
}

java::lang::String* java::lang::String::replace(char16_t oldChar, char16_t newChar)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::replace(char16_t oldChar, char16_t newChar)");
    return 0;
}

java::lang::String* java::lang::String::replace(CharSequence* target, CharSequence* replacement)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::replace(CharSequence* target, CharSequence* replacement)");
    return 0;
}

java::lang::String* java::lang::String::replaceAll(String* regex, String* replacement)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::replaceAll(String* regex, String* replacement)");
    return 0;
}

java::lang::String* java::lang::String::replaceFirst(String* regex, String* replacement)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::replaceFirst(String* regex, String* replacement)");
    return 0;
}

java::lang::StringArray* java::lang::String::split(String* regex)
{ /* stub */
    unimplemented_(u"java::lang::StringArray* java::lang::String::split(String* regex)");
    return 0;
}

java::lang::StringArray* java::lang::String::split(String* regex, int32_t limit)
{ /* stub */
    unimplemented_(u"java::lang::StringArray* java::lang::String::split(String* regex, int32_t limit)");
    return 0;
}

bool java::lang::String::startsWith(String* prefix)
{ /* stub */
    unimplemented_(u"bool java::lang::String::startsWith(String* prefix)");
    return 0;
}

bool java::lang::String::startsWith(String* prefix, int32_t toffset)
{ /* stub */
    unimplemented_(u"bool java::lang::String::startsWith(String* prefix, int32_t toffset)");
    return 0;
}

java::lang::CharSequence* java::lang::String::subSequence(int32_t beginIndex, int32_t endIndex)
{ /* stub */
    unimplemented_(u"java::lang::CharSequence* java::lang::String::subSequence(int32_t beginIndex, int32_t endIndex)");
    return 0;
}

java::lang::String* java::lang::String::substring(int32_t beginIndex)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::substring(int32_t beginIndex)");
    return 0;
}

java::lang::String* java::lang::String::substring(int32_t beginIndex, int32_t endIndex)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::substring(int32_t beginIndex, int32_t endIndex)");
    return 0;
}

char16_tArray* java::lang::String::toCharArray_()
{ /* stub */
    unimplemented_(u"char16_tArray* java::lang::String::toCharArray_()");
    return 0;
}

java::lang::String* java::lang::String::toLowerCase()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::toLowerCase()");
    return 0;
}

java::lang::String* java::lang::String::toLowerCase(::java::util::Locale* locale)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::toLowerCase(::java::util::Locale* locale)");
    return 0;
}

java::lang::String* java::lang::String::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::toString()");
    return 0;
}

java::lang::String* java::lang::String::toUpperCase()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::toUpperCase()");
    return 0;
}

java::lang::String* java::lang::String::toUpperCase(::java::util::Locale* locale)
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::toUpperCase(::java::util::Locale* locale)");
    return 0;
}

java::lang::String* java::lang::String::trim()
{ /* stub */
    unimplemented_(u"java::lang::String* java::lang::String::trim()");
    return 0;
}

java::lang::String* java::lang::String::valueOf(Object* obj)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::valueOf(Object* obj)");
    return 0;
}

java::lang::String* java::lang::String::valueOf(::char16_tArray* data)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::valueOf(::char16_tArray* data)");
    return 0;
}

java::lang::String* java::lang::String::valueOf(bool b)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::valueOf(bool b)");
    return 0;
}

java::lang::String* java::lang::String::valueOf(char16_t c)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::valueOf(char16_t c)");
    return 0;
}

java::lang::String* java::lang::String::valueOf(int32_t i)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::valueOf(int32_t i)");
    return 0;
}

java::lang::String* java::lang::String::valueOf(int64_t l)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::valueOf(int64_t l)");
    return 0;
}

java::lang::String* java::lang::String::valueOf(float f)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::valueOf(float f)");
    return 0;
}

java::lang::String* java::lang::String::valueOf(double d)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::valueOf(double d)");
    return 0;
}

java::lang::String* java::lang::String::valueOf(::char16_tArray* data, int32_t offset, int32_t count)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::lang::String::valueOf(::char16_tArray* data, int32_t offset, int32_t count)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::String::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.String", 16);
    return c;
}

java::lang::Class* java::lang::String::getClass0()
{
    return class_();
}


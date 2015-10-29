// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <stddef.h>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/charset/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/io/Serializable.hpp>
#include <java/lang/Comparable.hpp>
#include <java/lang/CharSequence.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace lang
    {
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
    } // lang

    namespace io
    {
typedef ::SubArray< ::java::io::ObjectStreamField, ::java::lang::ObjectArray, ::java::lang::ComparableArray > ObjectStreamFieldArray;
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::CharSequence, ObjectArray > CharSequenceArray;
typedef ::SubArray< ::java::lang::String, ObjectArray, ::java::io::SerializableArray, ComparableArray, CharSequenceArray > StringArray;
    } // lang
} // java

struct default_init_tag;

class java::lang::String final
    : public virtual Object
    , public ::java::io::Serializable
    , public Comparable
    , public CharSequence
{

public:
    typedef Object super;

private:
    static ::java::util::Comparator* CASE_INSENSITIVE_ORDER_;
    static int32_t HASHING_SEED_;
    int32_t hash {  };
    int32_t hash32_ {  };
    static ::java::io::ObjectStreamFieldArray* serialPersistentFields_;
    static constexpr int64_t serialVersionUID { int64_t(-6849794470754667710LL) };
    ::char16_tArray* value {  };

protected:
    void ctor();
    void ctor(String* original);
    void ctor(::char16_tArray* value);
    void ctor(::int8_tArray* bytes);
    void ctor(StringBuffer* buffer);
    void ctor(StringBuilder* builder);
    void ctor(::int8_tArray* ascii, int32_t hibyte);
    void ctor(::int8_tArray* bytes, String* charsetName);
    void ctor(::int8_tArray* bytes, ::java::nio::charset::Charset* charset);
    void ctor(::char16_tArray* value, bool share);
    void ctor(::char16_tArray* value, int32_t offset, int32_t count);
    void ctor(::int32_tArray* codePoints, int32_t offset, int32_t count);
    void ctor(::int8_tArray* bytes, int32_t offset, int32_t length);
    void ctor(int32_t offset, int32_t count, ::char16_tArray* value);
    void ctor(::int8_tArray* ascii, int32_t hibyte, int32_t offset, int32_t count);
    void ctor(::int8_tArray* bytes, int32_t offset, int32_t length, String* charsetName);
    void ctor(::int8_tArray* bytes, int32_t offset, int32_t length, ::java::nio::charset::Charset* charset);

public:
    char16_t charAt(int32_t index) override;
    /*static void checkBounds(::int8_tArray* bytes, int32_t offset, int32_t length); (private) */
    int32_t codePointAt(int32_t index);
    int32_t codePointBefore(int32_t index);
    int32_t codePointCount(int32_t beginIndex, int32_t endIndex);
    int32_t compareTo(String* anotherString);
    int32_t compareToIgnoreCase(String* str);
    String* concat(String* str);
    bool contains(CharSequence* s);
    bool contentEquals(StringBuffer* sb);
    bool contentEquals(CharSequence* cs);
    static String* copyValueOf(::char16_tArray* data);
    static String* copyValueOf(::char16_tArray* data, int32_t offset, int32_t count);
    bool endsWith(String* suffix);
    bool equals(Object* anObject) override;
    bool equalsIgnoreCase(String* anotherString);
    static String* format(String* format, ObjectArray* args);
    static String* format(::java::util::Locale* l, String* format, ObjectArray* args);
    ::int8_tArray* getBytes();
    ::int8_tArray* getBytes(String* charsetName);
    ::int8_tArray* getBytes(::java::nio::charset::Charset* charset);
    void getBytes(int32_t srcBegin, int32_t srcEnd, ::int8_tArray* dst, int32_t dstBegin);

public: /* package */
    void getChars(::char16_tArray* dst, int32_t dstBegin);

public:
    void getChars(int32_t srcBegin, int32_t srcEnd, ::char16_tArray* dst, int32_t dstBegin);

public: /* package */
    int32_t hash32();

public:
    int32_t hashCode() override;
    int32_t indexOf(int32_t ch);
    int32_t indexOf(String* str);
    int32_t indexOf(int32_t ch, int32_t fromIndex);
    int32_t indexOf(String* str, int32_t fromIndex);

public: /* package */
    static int32_t indexOf(::char16_tArray* source, int32_t sourceOffset, int32_t sourceCount, ::char16_tArray* target, int32_t targetOffset, int32_t targetCount, int32_t fromIndex);
    /*int32_t indexOfSupplementary(int32_t ch, int32_t fromIndex); (private) */

public:
    String* intern();
    bool isEmpty();
    int32_t lastIndexOf(int32_t ch);
    int32_t lastIndexOf(String* str);
    int32_t lastIndexOf(int32_t ch, int32_t fromIndex);
    int32_t lastIndexOf(String* str, int32_t fromIndex);

public: /* package */
    static int32_t lastIndexOf(::char16_tArray* source, int32_t sourceOffset, int32_t sourceCount, ::char16_tArray* target, int32_t targetOffset, int32_t targetCount, int32_t fromIndex);
    /*int32_t lastIndexOfSupplementary(int32_t ch, int32_t fromIndex); (private) */

public:
    int32_t length() override;
    bool matches(String* regex);
    int32_t offsetByCodePoints(int32_t index, int32_t codePointOffset);
    bool regionMatches(int32_t toffset, String* other, int32_t ooffset, int32_t len);
    bool regionMatches(bool ignoreCase, int32_t toffset, String* other, int32_t ooffset, int32_t len);
    String* replace(char16_t oldChar, char16_t newChar);
    String* replace(CharSequence* target, CharSequence* replacement);
    String* replaceAll(String* regex, String* replacement);
    String* replaceFirst(String* regex, String* replacement);
    StringArray* split(String* regex);
    StringArray* split(String* regex, int32_t limit);
    bool startsWith(String* prefix);
    bool startsWith(String* prefix, int32_t toffset);
    CharSequence* subSequence(int32_t beginIndex, int32_t endIndex) override;
    String* substring(int32_t beginIndex);
    String* substring(int32_t beginIndex, int32_t endIndex);
    ::char16_tArray* toCharArray_();
    String* toLowerCase();
    String* toLowerCase(::java::util::Locale* locale);
    String* toString() override;
    String* toUpperCase();
    String* toUpperCase(::java::util::Locale* locale);
    String* trim();
    static String* valueOf(Object* obj);
    static String* valueOf(::char16_tArray* data);
    static String* valueOf(bool b);
    static String* valueOf(char16_t c);
    static String* valueOf(int32_t i);
    static String* valueOf(int64_t l);
    static String* valueOf(float f);
    static String* valueOf(double d);
    static String* valueOf(::char16_tArray* data, int32_t offset, int32_t count);

    // Generated
    String();
    String(String* original);
    String(::char16_tArray* value);
    String(::int8_tArray* bytes);
    String(StringBuffer* buffer);
    String(StringBuilder* builder);
    String(::int8_tArray* ascii, int32_t hibyte);
    String(::int8_tArray* bytes, String* charsetName);
    String(::int8_tArray* bytes, ::java::nio::charset::Charset* charset);

public: /* package */
    String(::char16_tArray* value, bool share);

public:
    String(::char16_tArray* value, int32_t offset, int32_t count);
    String(::int32_tArray* codePoints, int32_t offset, int32_t count);
    String(::int8_tArray* bytes, int32_t offset, int32_t length);

public: /* package */
    String(int32_t offset, int32_t count, ::char16_tArray* value);

public:
    String(::int8_tArray* ascii, int32_t hibyte, int32_t offset, int32_t count);
    String(::int8_tArray* bytes, int32_t offset, int32_t length, String* charsetName);
    String(::int8_tArray* bytes, int32_t offset, int32_t length, ::java::nio::charset::Charset* charset);
protected:
    String(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual int32_t compareTo(Object* o) override;
    static ::java::util::Comparator*& CASE_INSENSITIVE_ORDER();

private:
    static int32_t& HASHING_SEED();
    static ::java::io::ObjectStreamFieldArray*& serialPersistentFields();
    virtual ::java::lang::Class* getClass0();
    friend String *operator"" _j(const char16_t *s, size_t n);
};
namespace java { namespace lang { String *operator "" _j(const char16_t *p, size_t n); } }
using java::lang::operator "" _j;


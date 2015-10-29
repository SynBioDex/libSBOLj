// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/Appendable.hpp>
#include <java/lang/CharSequence.hpp>

struct default_init_tag;

class java::lang::AbstractStringBuilder
    : public virtual Object
    , public virtual Appendable
    , public virtual CharSequence
{

public:
    typedef Object super;

public: /* package */
    int32_t count {  };
    ::char16_tArray* value {  };

protected:
    void ctor();
    void ctor(int32_t capacity);

public:
    virtual AbstractStringBuilder* append(Object* obj);
    virtual AbstractStringBuilder* append(String* str);
    virtual AbstractStringBuilder* append(StringBuffer* sb);
    AbstractStringBuilder* append(CharSequence* s) override;
    virtual AbstractStringBuilder* append(::char16_tArray* str);
    virtual AbstractStringBuilder* append(bool b);
    AbstractStringBuilder* append(char16_t c) override;
    virtual AbstractStringBuilder* append(int32_t i);
    virtual AbstractStringBuilder* append(int64_t l);
    virtual AbstractStringBuilder* append(float f);
    virtual AbstractStringBuilder* append(double d);
    AbstractStringBuilder* append(CharSequence* s, int32_t start, int32_t end) override;
    virtual AbstractStringBuilder* append(::char16_tArray* str, int32_t offset, int32_t len);
    virtual AbstractStringBuilder* appendCodePoint(int32_t codePoint);
    virtual int32_t capacity();
    char16_t charAt(int32_t index) override;
    virtual int32_t codePointAt(int32_t index);
    virtual int32_t codePointBefore(int32_t index);
    virtual int32_t codePointCount(int32_t beginIndex, int32_t endIndex);
    virtual AbstractStringBuilder* delete_(int32_t start, int32_t end);
    virtual AbstractStringBuilder* deleteCharAt(int32_t index);
    virtual void ensureCapacity(int32_t minimumCapacity);
    /*void ensureCapacityInternal(int32_t minimumCapacity); (private) */

public: /* package */
    virtual void expandCapacity(int32_t minimumCapacity);

public:
    virtual void getChars(int32_t srcBegin, int32_t srcEnd, ::char16_tArray* dst, int32_t dstBegin);

public: /* package */
    ::char16_tArray* getValue();

public:
    virtual int32_t indexOf(String* str);
    virtual int32_t indexOf(String* str, int32_t fromIndex);
    virtual AbstractStringBuilder* insert(int32_t offset, Object* obj);
    virtual AbstractStringBuilder* insert(int32_t offset, String* str);
    virtual AbstractStringBuilder* insert(int32_t offset, ::char16_tArray* str);
    virtual AbstractStringBuilder* insert(int32_t dstOffset, CharSequence* s);
    virtual AbstractStringBuilder* insert(int32_t offset, bool b);
    virtual AbstractStringBuilder* insert(int32_t offset, char16_t c);
    virtual AbstractStringBuilder* insert(int32_t offset, int32_t i);
    virtual AbstractStringBuilder* insert(int32_t offset, int64_t l);
    virtual AbstractStringBuilder* insert(int32_t offset, float f);
    virtual AbstractStringBuilder* insert(int32_t offset, double d);
    virtual AbstractStringBuilder* insert(int32_t index, ::char16_tArray* str, int32_t offset, int32_t len);
    virtual AbstractStringBuilder* insert(int32_t dstOffset, CharSequence* s, int32_t start, int32_t end);
    virtual int32_t lastIndexOf(String* str);
    virtual int32_t lastIndexOf(String* str, int32_t fromIndex);
    int32_t length() override;
    virtual int32_t offsetByCodePoints(int32_t index, int32_t codePointOffset);
    virtual AbstractStringBuilder* replace(int32_t start, int32_t end, String* str);
    virtual AbstractStringBuilder* reverse();
    virtual void setCharAt(int32_t index, char16_t ch);
    virtual void setLength(int32_t newLength);
    CharSequence* subSequence(int32_t start, int32_t end) override;
    virtual String* substring(int32_t start);
    virtual String* substring(int32_t start, int32_t end);
    /*String* toString(); (already declared) */
    virtual void trimToSize();

    // Generated

public: /* package */
    AbstractStringBuilder();
    AbstractStringBuilder(int32_t capacity);
protected:
    AbstractStringBuilder(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/AbstractStringBuilder.hpp>
#include <java/io/Serializable.hpp>
#include <java/lang/CharSequence.hpp>

struct default_init_tag;

class java::lang::StringBuilder final
    : public AbstractStringBuilder
    , public ::java::io::Serializable
    , public virtual CharSequence
{

public:
    typedef AbstractStringBuilder super;

public: /* package */
    static constexpr int64_t serialVersionUID { int64_t(4383685877147921099LL) };

protected:
    void ctor();
    void ctor(int32_t capacity);
    void ctor(String* str);
    void ctor(CharSequence* seq);

public:
    StringBuilder* append(Object* obj) override;
    StringBuilder* append(String* str) override;
    /*StringBuilder* append(StringBuilder* sb); (private) */
    StringBuilder* append(StringBuffer* sb) override;
    StringBuilder* append(CharSequence* s) override;
    StringBuilder* append(::char16_tArray* str) override;
    StringBuilder* append(bool b) override;
    StringBuilder* append(char16_t c) override;
    StringBuilder* append(int32_t i) override;
    StringBuilder* append(int64_t lng) override;
    StringBuilder* append(float f) override;
    StringBuilder* append(double d) override;
    StringBuilder* append(CharSequence* s, int32_t start, int32_t end) override;
    StringBuilder* append(::char16_tArray* str, int32_t offset, int32_t len) override;
    StringBuilder* appendCodePoint(int32_t codePoint) override;
    StringBuilder* delete_(int32_t start, int32_t end) override;
    StringBuilder* deleteCharAt(int32_t index) override;
    int32_t indexOf(String* str) override;
    int32_t indexOf(String* str, int32_t fromIndex) override;
    StringBuilder* insert(int32_t offset, Object* obj) override;
    StringBuilder* insert(int32_t offset, String* str) override;
    StringBuilder* insert(int32_t offset, ::char16_tArray* str) override;
    StringBuilder* insert(int32_t dstOffset, CharSequence* s) override;
    StringBuilder* insert(int32_t offset, bool b) override;
    StringBuilder* insert(int32_t offset, char16_t c) override;
    StringBuilder* insert(int32_t offset, int32_t i) override;
    StringBuilder* insert(int32_t offset, int64_t l) override;
    StringBuilder* insert(int32_t offset, float f) override;
    StringBuilder* insert(int32_t offset, double d) override;
    StringBuilder* insert(int32_t index, ::char16_tArray* str, int32_t offset, int32_t len) override;
    StringBuilder* insert(int32_t dstOffset, CharSequence* s, int32_t start, int32_t end) override;
    int32_t lastIndexOf(String* str) override;
    int32_t lastIndexOf(String* str, int32_t fromIndex) override;
    /*void readObject(::java::io::ObjectInputStream* s); (private) */
    StringBuilder* replace(int32_t start, int32_t end, String* str) override;
    StringBuilder* reverse() override;
    String* toString() override;
    /*void writeObject(::java::io::ObjectOutputStream* s); (private) */

    // Generated
    StringBuilder();
    StringBuilder(int32_t capacity);
    StringBuilder(String* str);
    StringBuilder(CharSequence* seq);
protected:
    StringBuilder(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual char16_t charAt(int32_t index);
    virtual int32_t length();
    virtual CharSequence* subSequence(int32_t start, int32_t end);

private:
    virtual ::java::lang::Class* getClass0();
};

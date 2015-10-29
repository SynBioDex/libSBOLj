// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Number.hpp>
#include <java/lang/Comparable.hpp>

struct default_init_tag;

class java::lang::Integer final
    : public Number
    , public Comparable
{

public:
    typedef Number super;

private:
    static bool $assertionsDisabled_;
    static ::char16_tArray* DigitOnes_;
    static ::char16_tArray* DigitTens_;

public:
    static constexpr int32_t MAX_VALUE { int32_t(2147483647) };
    static constexpr int32_t MIN_VALUE { int32_t(-0x7fffffff-1) };
    static constexpr int32_t SIZE { int32_t(32) };

private:
    static Class* TYPE_;
    static ::char16_tArray* digits_;
    static constexpr int64_t serialVersionUID { int64_t(1360826667806852920LL) };
    static ::int32_tArray* sizeTable_;
    int32_t value {  };

protected:
    void ctor(int32_t value);
    void ctor(String* s);

public:
    static int32_t bitCount(int32_t i);
    int8_t byteValue() override;
    static int32_t compare(int32_t x, int32_t y);
    int32_t compareTo(Integer* anotherInteger);
    static Integer* decode(String* nm);
    double doubleValue() override;
    bool equals(Object* obj) override;
    float floatValue() override;

public: /* package */
    static void getChars(int32_t i, int32_t index, ::char16_tArray* buf);

public:
    static Integer* getInteger(String* nm);
    static Integer* getInteger(String* nm, int32_t val);
    static Integer* getInteger(String* nm, Integer* val);
    int32_t hashCode() override;
    static int32_t highestOneBit(int32_t i);
    int32_t intValue() override;
    int64_t longValue() override;
    static int32_t lowestOneBit(int32_t i);
    static int32_t numberOfLeadingZeros(int32_t i);
    static int32_t numberOfTrailingZeros(int32_t i);
    static int32_t parseInt(String* s);
    static int32_t parseInt(String* s, int32_t radix);
    static int32_t reverse(int32_t i);
    static int32_t reverseBytes(int32_t i);
    static int32_t rotateLeft(int32_t i, int32_t distance);
    static int32_t rotateRight(int32_t i, int32_t distance);
    int16_t shortValue() override;
    static int32_t signum(int32_t i);

public: /* package */
    static int32_t stringSize(int32_t x);

public:
    static String* toBinaryString(int32_t i);
    static String* toHexString(int32_t i);
    static String* toOctalString(int32_t i);
    String* toString() override;
    static String* toString(int32_t i);
    static String* toString(int32_t i, int32_t radix);
    /*static String* toUnsignedString(int32_t i, int32_t shift); (private) */
    static Integer* valueOf(String* s);
    static Integer* valueOf(int32_t i);
    static Integer* valueOf(String* s, int32_t radix);

    // Generated
    Integer(int32_t value);
    Integer(String* s);
protected:
    Integer(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual int32_t compareTo(Object* o) override;

public: /* package */
    static bool& $assertionsDisabled();
    static ::char16_tArray*& DigitOnes();
    static ::char16_tArray*& DigitTens();

public:
    static Class*& TYPE();

public: /* package */
    static ::char16_tArray*& digits();
    static ::int32_tArray*& sizeTable();

private:
    virtual ::java::lang::Class* getClass0();
};

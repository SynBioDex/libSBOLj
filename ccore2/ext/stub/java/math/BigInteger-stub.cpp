// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/math/BigInteger.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::Number, ObjectArray, ::java::io::SerializableArray > NumberArray;
    } // lang

    namespace math
    {
typedef ::SubArray< ::java::math::BigInteger, ::java::lang::NumberArray, ::java::lang::ComparableArray > BigIntegerArray;
    } // math
} // java

extern void unimplemented_(const char16_t* name);
java::math::BigInteger::BigInteger(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::math::BigInteger::BigInteger(::int8_tArray* val)
    : BigInteger(*static_cast< ::default_init_tag* >(0))
{
    ctor(val);
}

java::math::BigInteger::BigInteger(::char16_tArray* val)
    : BigInteger(*static_cast< ::default_init_tag* >(0))
{
    ctor(val);
}

java::math::BigInteger::BigInteger(::java::lang::String* val)
    : BigInteger(*static_cast< ::default_init_tag* >(0))
{
    ctor(val);
}

java::math::BigInteger::BigInteger(int32_t signum, ::int8_tArray* magnitude)
    : BigInteger(*static_cast< ::default_init_tag* >(0))
{
    ctor(signum, magnitude);
}

java::math::BigInteger::BigInteger(::java::lang::String* val, int32_t radix)
    : BigInteger(*static_cast< ::default_init_tag* >(0))
{
    ctor(val, radix);
}

java::math::BigInteger::BigInteger(int32_t numBits, ::java::util::Random* rnd)
    : BigInteger(*static_cast< ::default_init_tag* >(0))
{
    ctor(numBits, rnd);
}

java::math::BigInteger::BigInteger(::int32_tArray* magnitude, int32_t signum)
    : BigInteger(*static_cast< ::default_init_tag* >(0))
{
    ctor(magnitude, signum);
}

java::math::BigInteger::BigInteger(int32_t bitLength, int32_t certainty, ::java::util::Random* rnd)
    : BigInteger(*static_cast< ::default_init_tag* >(0))
{
    ctor(bitLength, certainty, rnd);
}

bool& java::math::BigInteger::$assertionsDisabled()
{
    clinit();
    return $assertionsDisabled_;
}
bool java::math::BigInteger::$assertionsDisabled_;
constexpr int32_t java::math::BigInteger::DEFAULT_PRIME_CERTAINTY;
constexpr int64_t java::math::BigInteger::LONG_MASK;
constexpr int32_t java::math::BigInteger::MAX_CONSTANT;
java::math::BigInteger*& java::math::BigInteger::ONE()
{
    clinit();
    return ONE_;
}
java::math::BigInteger* java::math::BigInteger::ONE_;
java::math::BigInteger*& java::math::BigInteger::SMALL_PRIME_PRODUCT()
{
    clinit();
    return SMALL_PRIME_PRODUCT_;
}
java::math::BigInteger* java::math::BigInteger::SMALL_PRIME_PRODUCT_;
constexpr int32_t java::math::BigInteger::SMALL_PRIME_THRESHOLD;
java::math::BigInteger*& java::math::BigInteger::TEN()
{
    clinit();
    return TEN_;
}
java::math::BigInteger* java::math::BigInteger::TEN_;
java::math::BigInteger*& java::math::BigInteger::TWO()
{
    clinit();
    return TWO_;
}
java::math::BigInteger* java::math::BigInteger::TWO_;
java::math::BigInteger*& java::math::BigInteger::ZERO()
{
    clinit();
    return ZERO_;
}
java::math::BigInteger* java::math::BigInteger::ZERO_;
int64_tArray*& java::math::BigInteger::bitsPerDigit()
{
    clinit();
    return bitsPerDigit_;
}
int64_tArray* java::math::BigInteger::bitsPerDigit_;
int32_tArray*& java::math::BigInteger::bnExpModThreshTable()
{
    clinit();
    return bnExpModThreshTable_;
}
int32_tArray* java::math::BigInteger::bnExpModThreshTable_;
int32_tArray*& java::math::BigInteger::digitsPerInt()
{
    clinit();
    return digitsPerInt_;
}
int32_tArray* java::math::BigInteger::digitsPerInt_;
int32_tArray*& java::math::BigInteger::digitsPerLong()
{
    clinit();
    return digitsPerLong_;
}
int32_tArray* java::math::BigInteger::digitsPerLong_;
int32_tArray*& java::math::BigInteger::intRadix()
{
    clinit();
    return intRadix_;
}
int32_tArray* java::math::BigInteger::intRadix_;
java::math::BigIntegerArray*& java::math::BigInteger::longRadix()
{
    clinit();
    return longRadix_;
}
java::math::BigIntegerArray* java::math::BigInteger::longRadix_;
int64_t& java::math::BigInteger::magOffset()
{
    clinit();
    return magOffset_;
}
int64_t java::math::BigInteger::magOffset_;
java::math::BigIntegerArray*& java::math::BigInteger::negConst()
{
    clinit();
    return negConst_;
}
java::math::BigIntegerArray* java::math::BigInteger::negConst_;
java::math::BigIntegerArray*& java::math::BigInteger::posConst()
{
    clinit();
    return posConst_;
}
java::math::BigIntegerArray* java::math::BigInteger::posConst_;
java::io::ObjectStreamFieldArray*& java::math::BigInteger::serialPersistentFields()
{
    clinit();
    return serialPersistentFields_;
}
java::io::ObjectStreamFieldArray* java::math::BigInteger::serialPersistentFields_;
constexpr int64_t java::math::BigInteger::serialVersionUID;
int64_t& java::math::BigInteger::signumOffset()
{
    clinit();
    return signumOffset_;
}
int64_t java::math::BigInteger::signumOffset_;
std::atomic< java::util::Random* >& java::math::BigInteger::staticRandom()
{
    clinit();
    return staticRandom_;
}
std::atomic< java::util::Random* > java::math::BigInteger::staticRandom_;
sun::misc::Unsafe*& java::math::BigInteger::unsafe()
{
    clinit();
    return unsafe_;
}
sun::misc::Unsafe* java::math::BigInteger::unsafe_;
java::lang::StringArray*& java::math::BigInteger::zeros()
{
    clinit();
    return zeros_;
}
java::lang::StringArray* java::math::BigInteger::zeros_;

void ::java::math::BigInteger::ctor(::int8_tArray* val)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::math::BigInteger::ctor(::int8_tArray* val)");
}

/* private: void ::java::math::BigInteger::ctor(::int32_tArray* val) */
void ::java::math::BigInteger::ctor(::char16_tArray* val)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::math::BigInteger::ctor(::char16_tArray* val)");
}

void ::java::math::BigInteger::ctor(::java::lang::String* val)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::math::BigInteger::ctor(::java::lang::String* val)");
}

/* private: void ::java::math::BigInteger::ctor(int64_t val) */
void ::java::math::BigInteger::ctor(int32_t signum, ::int8_tArray* magnitude)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::math::BigInteger::ctor(int32_t signum, ::int8_tArray* magnitude)");
}

/* private: void ::java::math::BigInteger::ctor(int32_t signum, ::int32_tArray* magnitude) */
void ::java::math::BigInteger::ctor(::java::lang::String* val, int32_t radix)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::math::BigInteger::ctor(::java::lang::String* val, int32_t radix)");
}

void ::java::math::BigInteger::ctor(int32_t numBits, ::java::util::Random* rnd)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::math::BigInteger::ctor(int32_t numBits, ::java::util::Random* rnd)");
}

void ::java::math::BigInteger::ctor(::int32_tArray* magnitude, int32_t signum)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::math::BigInteger::ctor(::int32_tArray* magnitude, int32_t signum)");
}

/* private: void ::java::math::BigInteger::ctor(::int8_tArray* magnitude, int32_t signum) */
void ::java::math::BigInteger::ctor(int32_t bitLength, int32_t certainty, ::java::util::Random* rnd)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::math::BigInteger::ctor(int32_t bitLength, int32_t certainty, ::java::util::Random* rnd)");
}

java::math::BigInteger* java::math::BigInteger::abs()
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::abs()");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::add(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::add(BigInteger* val)");
    return 0;
}

/* private: int32_tArray* java::math::BigInteger::add(::int32_tArray* x, ::int32_tArray* y) */
int32_t java::math::BigInteger::addOne(::int32_tArray* a, int32_t offset, int32_t mlen, int32_t carry)
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::math::BigInteger::addOne(::int32_tArray* a, int32_t offset, int32_t mlen, int32_t carry)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::and_(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::and_(BigInteger* val)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::andNot(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::andNot(BigInteger* val)");
    return 0;
}

int32_t java::math::BigInteger::bitCount()
{ /* stub */
    unimplemented_(u"int32_t java::math::BigInteger::bitCount()");
    return 0;
}

int32_t java::math::BigInteger::bitLength()
{ /* stub */
    unimplemented_(u"int32_t java::math::BigInteger::bitLength()");
    return 0;
}

/* private: int32_t java::math::BigInteger::bitLength(::int32_tArray* val, int32_t len) */
int32_t java::math::BigInteger::bitLengthForInt(int32_t n)
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::math::BigInteger::bitLengthForInt(int32_t n)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::clearBit(int32_t n)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::clearBit(int32_t n)");
    return 0;
}

int32_t java::math::BigInteger::compareMagnitude(BigInteger* val)
{ /* stub */
    unimplemented_(u"int32_t java::math::BigInteger::compareMagnitude(BigInteger* val)");
    return 0;
}

int32_t java::math::BigInteger::compareTo(BigInteger* val)
{ /* stub */
    unimplemented_(u"int32_t java::math::BigInteger::compareTo(BigInteger* val)");
    return 0;
}

int32_t java::math::BigInteger::compareTo(::java::lang::Object* o)
{ 
    return compareTo(dynamic_cast< BigInteger* >(o));
}

/* private: void java::math::BigInteger::destructiveMulAdd(::int32_tArray* x, int32_t y, int32_t z) */
java::math::BigInteger* java::math::BigInteger::divide(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::divide(BigInteger* val)");
    return 0;
}

java::math::BigIntegerArray* java::math::BigInteger::divideAndRemainder(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigIntegerArray* java::math::BigInteger::divideAndRemainder(BigInteger* val)");
    return 0;
}

double java::math::BigInteger::doubleValue()
{ /* stub */
    unimplemented_(u"double java::math::BigInteger::doubleValue()");
    return 0;
}

bool java::math::BigInteger::equals(::java::lang::Object* x)
{ /* stub */
    unimplemented_(u"bool java::math::BigInteger::equals(::java::lang::Object* x)");
    return 0;
}

/* private: int32_t java::math::BigInteger::firstNonzeroIntNum() */
java::math::BigInteger* java::math::BigInteger::flipBit(int32_t n)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::flipBit(int32_t n)");
    return 0;
}

float java::math::BigInteger::floatValue()
{ /* stub */
    unimplemented_(u"float java::math::BigInteger::floatValue()");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::gcd(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::gcd(BigInteger* val)");
    return 0;
}

/* private: int32_t java::math::BigInteger::getInt(int32_t n) */
int32_t java::math::BigInteger::getLowestSetBit()
{ /* stub */
return lowestSetBit ; /* getter */
}

/* private: java::util::Random* java::math::BigInteger::getSecureRandom() */
int32_t java::math::BigInteger::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::math::BigInteger::hashCode()");
    return 0;
}

/* private: int32_t java::math::BigInteger::intArrayCmpToLen(::int32_tArray* arg1, ::int32_tArray* arg2, int32_t len) */
/* private: int32_t java::math::BigInteger::intLength() */
int32_t java::math::BigInteger::intValue()
{ /* stub */
    unimplemented_(u"int32_t java::math::BigInteger::intValue()");
    return 0;
}

bool java::math::BigInteger::isProbablePrime(int32_t certainty)
{ /* stub */
    unimplemented_(u"bool java::math::BigInteger::isProbablePrime(int32_t certainty)");
    return 0;
}

/* private: int32_t java::math::BigInteger::jacobiSymbol(int32_t p, BigInteger* n) */
int32_tArray* java::math::BigInteger::javaIncrement(::int32_tArray* val)
{ /* stub */
    unimplemented_(u"int32_tArray* java::math::BigInteger::javaIncrement(::int32_tArray* val)");
    return 0;
}

/* private: java::math::BigInteger* java::math::BigInteger::largePrime(int32_t bitLength, int32_t certainty, ::java::util::Random* rnd) */
/* private: int32_tArray* java::math::BigInteger::leftShift(::int32_tArray* a, int32_t len, int32_t n) */
int64_t java::math::BigInteger::longValue()
{ /* stub */
    unimplemented_(u"int64_t java::math::BigInteger::longValue()");
    return 0;
}

/* private: java::math::BigInteger* java::math::BigInteger::lucasLehmerSequence(int32_t z, BigInteger* k, BigInteger* n) */
/* private: int8_tArray* java::math::BigInteger::magSerializedForm() */
/* private: int32_tArray* java::math::BigInteger::makePositive(::int8_tArray* a) */
/* private: int32_tArray* java::math::BigInteger::makePositive(::int32_tArray* a) */
java::math::BigInteger* java::math::BigInteger::max(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::max(BigInteger* val)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::min(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::min(BigInteger* val)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::mod(BigInteger* m)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::mod(BigInteger* m)");
    return 0;
}

/* private: java::math::BigInteger* java::math::BigInteger::mod2(int32_t p) */
java::math::BigInteger* java::math::BigInteger::modInverse(BigInteger* m)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::modInverse(BigInteger* m)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::modPow(BigInteger* exponent, BigInteger* m)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::modPow(BigInteger* exponent, BigInteger* m)");
    return 0;
}

/* private: java::math::BigInteger* java::math::BigInteger::modPow2(BigInteger* exponent, int32_t p) */
/* private: int32_tArray* java::math::BigInteger::montReduce(::int32_tArray* n, ::int32_tArray* mod, int32_t mlen, int32_t inv) */
int32_t java::math::BigInteger::mulAdd(::int32_tArray* out, ::int32_tArray* in, int32_t offset, int32_t len, int32_t k)
{ /* stub */
    clinit();
    unimplemented_(u"int32_t java::math::BigInteger::mulAdd(::int32_tArray* out, ::int32_tArray* in, int32_t offset, int32_t len, int32_t k)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::multiply(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::multiply(BigInteger* val)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::multiply(int64_t v)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::multiply(int64_t v)");
    return 0;
}

/* private: int32_tArray* java::math::BigInteger::multiplyToLen(::int32_tArray* x, int32_t xlen, ::int32_tArray* y, int32_t ylen, ::int32_tArray* z) */
java::math::BigInteger* java::math::BigInteger::negate()
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::negate()");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::nextProbablePrime()
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::nextProbablePrime()");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::not_()
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::not_()");
    return 0;
}

/* private: java::math::BigInteger* java::math::BigInteger::oddModPow(BigInteger* y, BigInteger* z) */
java::math::BigInteger* java::math::BigInteger::or_(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::or_(BigInteger* val)");
    return 0;
}

/* private: int32_t java::math::BigInteger::parseInt(::char16_tArray* source, int32_t start, int32_t end) */
/* private: bool java::math::BigInteger::passesLucasLehmer() */
/* private: bool java::math::BigInteger::passesMillerRabin(int32_t iterations, ::java::util::Random* rnd) */
java::math::BigInteger* java::math::BigInteger::pow(int32_t exponent)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::pow(int32_t exponent)");
    return 0;
}

bool java::math::BigInteger::primeToCertainty(int32_t certainty, ::java::util::Random* random)
{ /* stub */
    unimplemented_(u"bool java::math::BigInteger::primeToCertainty(int32_t certainty, ::java::util::Random* random)");
    return 0;
}

void java::math::BigInteger::primitiveLeftShift(::int32_tArray* a, int32_t len, int32_t n)
{ /* stub */
    clinit();
    unimplemented_(u"void java::math::BigInteger::primitiveLeftShift(::int32_tArray* a, int32_t len, int32_t n)");
}

void java::math::BigInteger::primitiveRightShift(::int32_tArray* a, int32_t len, int32_t n)
{ /* stub */
    clinit();
    unimplemented_(u"void java::math::BigInteger::primitiveRightShift(::int32_tArray* a, int32_t len, int32_t n)");
}

java::math::BigInteger* java::math::BigInteger::probablePrime(int32_t bitLength, ::java::util::Random* rnd)
{ /* stub */
    clinit();
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::probablePrime(int32_t bitLength, ::java::util::Random* rnd)");
    return 0;
}

/* private: int8_tArray* java::math::BigInteger::randomBits(int32_t numBits, ::java::util::Random* rnd) */
/* private: void java::math::BigInteger::readObject(::java::io::ObjectInputStream* s) */
java::math::BigInteger* java::math::BigInteger::remainder(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::remainder(BigInteger* val)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::setBit(int32_t n)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::setBit(int32_t n)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::shiftLeft(int32_t n)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::shiftLeft(int32_t n)");
    return 0;
}

java::math::BigInteger* java::math::BigInteger::shiftRight(int32_t n)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::shiftRight(int32_t n)");
    return 0;
}

/* private: int32_t java::math::BigInteger::signBit() */
/* private: int32_t java::math::BigInteger::signInt() */
int32_t java::math::BigInteger::signum()
{ /* stub */
    unimplemented_(u"int32_t java::math::BigInteger::signum()");
    return 0;
}

/* private: java::math::BigInteger* java::math::BigInteger::smallPrime(int32_t bitLength, int32_t certainty, ::java::util::Random* rnd) */
/* private: java::math::BigInteger* java::math::BigInteger::square() */
/* private: int32_tArray* java::math::BigInteger::squareToLen(::int32_tArray* x, int32_t len, ::int32_tArray* z) */
/* private: int32_tArray* java::math::BigInteger::stripLeadingZeroBytes(::int8_tArray* a) */
/* private: int32_tArray* java::math::BigInteger::stripLeadingZeroInts(::int32_tArray* val) */
/* private: int32_t java::math::BigInteger::subN(::int32_tArray* a, ::int32_tArray* b, int32_t len) */
java::math::BigInteger* java::math::BigInteger::subtract(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::subtract(BigInteger* val)");
    return 0;
}

/* private: int32_tArray* java::math::BigInteger::subtract(::int32_tArray* big, ::int32_tArray* little) */
bool java::math::BigInteger::testBit(int32_t n)
{ /* stub */
    unimplemented_(u"bool java::math::BigInteger::testBit(int32_t n)");
    return 0;
}

int8_tArray* java::math::BigInteger::toByteArray_()
{ /* stub */
    unimplemented_(u"int8_tArray* java::math::BigInteger::toByteArray_()");
    return 0;
}

java::lang::String* java::math::BigInteger::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::math::BigInteger::toString()");
    return 0;
}

java::lang::String* java::math::BigInteger::toString(int32_t radix)
{ /* stub */
    unimplemented_(u"java::lang::String* java::math::BigInteger::toString(int32_t radix)");
    return 0;
}

/* private: int32_tArray* java::math::BigInteger::trustedStripLeadingZeroInts(::int32_tArray* val) */
java::math::BigInteger* java::math::BigInteger::valueOf(int64_t val)
{ /* stub */
    clinit();
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::valueOf(int64_t val)");
    return 0;
}

/* private: java::math::BigInteger* java::math::BigInteger::valueOf(::int32_tArray* val) */
/* private: void java::math::BigInteger::writeObject(::java::io::ObjectOutputStream* s) */
java::math::BigInteger* java::math::BigInteger::xor_(BigInteger* val)
{ /* stub */
    unimplemented_(u"java::math::BigInteger* java::math::BigInteger::xor_(BigInteger* val)");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::math::BigInteger::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.math.BigInteger", 20);
    return c;
}

java::lang::Class* java::math::BigInteger::getClass0()
{
    return class_();
}


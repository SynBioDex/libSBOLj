// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/math/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/channels/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/charset/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/file/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <sun/misc/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Iterator.hpp>
#include <java/io/Closeable.hpp>
#include <java/lang/String.hpp>

struct default_init_tag;

class java::util::Scanner final
    : public virtual ::java::lang::Object
    , public Iterator
    , public ::java::io::Closeable
{

public:
    typedef ::java::lang::Object super;

private:
    static bool $assertionsDisabled_;
    static ::java::lang::String* BOOLEAN_PATTERN_;
    static constexpr int32_t BUFFER_SIZE { int32_t(1024) };
    static ::java::util::regex::Pattern* FIND_ANY_PATTERN_;
    static ::java::lang::String* LINE_PATTERN_;
    static ::java::lang::String* LINE_SEPARATOR_PATTERN_;
    static ::java::util::regex::Pattern* NON_ASCII_DIGIT_;
    int32_t SIMPLE_GROUP_INDEX {  };
    static ::java::util::regex::Pattern* WHITESPACE_PATTERN_;
    static std::atomic< ::java::util::regex::Pattern* > boolPattern__;
    ::java::nio::CharBuffer* buf {  };
    bool closed {  };
    ::java::util::regex::Pattern* decimalPattern_ {  };
    ::java::lang::String* decimalSeparator {  };
    int32_t defaultRadix {  };
    ::java::util::regex::Pattern* delimPattern {  };
    ::java::lang::String* digits {  };
    ::java::util::regex::Pattern* floatPattern_ {  };
    ::java::lang::String* groupSeparator {  };
    ::java::util::regex::Pattern* hasNextPattern {  };
    int32_t hasNextPosition {  };
    ::java::lang::String* hasNextResult {  };
    ::java::lang::String* infinityString {  };
    ::java::util::regex::Pattern* integerPattern_ {  };
    ::java::io::IOException* lastException {  };
    static std::atomic< ::java::util::regex::Pattern* > linePattern__;
    Locale* locale_ {  };
    bool matchValid {  };
    ::java::util::regex::Matcher* matcher {  };
    ::java::lang::String* nanString {  };
    bool needInput {  };
    ::java::lang::String* negativePrefix {  };
    ::java::lang::String* negativeSuffix {  };
    ::java::lang::String* non0Digit {  };
    ::sun::misc::LRUCache* patternCache {  };
    int32_t position {  };
    ::java::lang::String* positivePrefix {  };
    ::java::lang::String* positiveSuffix {  };
    int32_t radix_ {  };
    int32_t savedScannerPosition {  };
    static std::atomic< ::java::util::regex::Pattern* > separatorPattern__;
    bool skipped {  };
    ::java::lang::Readable* source {  };
    bool sourceClosed {  };
    ::java::lang::Object* typeCache {  };

protected:
    void ctor(::java::lang::Readable* source);
    void ctor(::java::io::InputStream* source);
    void ctor(::java::io::File* source);
    void ctor(::java::nio::file::Path* source);
    void ctor(::java::lang::String* source);
    void ctor(::java::nio::channels::ReadableByteChannel* source);
    /*void ctor(::java::lang::Readable* source, ::java::util::regex::Pattern* pattern); (private) */
    void ctor(::java::io::InputStream* source, ::java::lang::String* charsetName);
    void ctor(::java::io::File* source, ::java::lang::String* charsetName);
    /*void ctor(::java::io::File* source, ::java::nio::charset::CharsetDecoder* dec); (private) */
    void ctor(::java::nio::file::Path* source, ::java::lang::String* charsetName);
    /*void ctor(::java::nio::file::Path* source, ::java::nio::charset::Charset* charset); (private) */
    void ctor(::java::nio::channels::ReadableByteChannel* source, ::java::lang::String* charsetName);
    /*static ::java::util::regex::Pattern* boolPattern(); (private) */
    /*void buildFloatAndDecimalPattern(); (private) */
    /*::java::lang::String* buildIntegerPatternString(); (private) */
    /*void cacheResult(); (private) */
    /*void cacheResult(::java::lang::String* result); (private) */
    /*void clearCaches(); (private) */

public:
    void close() override;
    /*::java::util::regex::Pattern* decimalPattern(); (private) */
    ::java::util::regex::Pattern* delimiter();
    /*void ensureOpen(); (private) */
    ::java::lang::String* findInLine(::java::lang::String* pattern);
    ::java::lang::String* findInLine(::java::util::regex::Pattern* pattern);
    /*::java::lang::String* findPatternInBuffer(::java::util::regex::Pattern* pattern, int32_t horizon); (private) */
    ::java::lang::String* findWithinHorizon(::java::lang::String* pattern, int32_t horizon);
    ::java::lang::String* findWithinHorizon(::java::util::regex::Pattern* pattern, int32_t horizon);
    /*::java::util::regex::Pattern* floatPattern(); (private) */
    /*::java::lang::String* getCachedResult(); (private) */
    /*::java::lang::String* getCompleteTokenInBuffer(::java::util::regex::Pattern* pattern); (private) */
    bool hasNext() override;
    bool hasNext(::java::lang::String* pattern);
    bool hasNext(::java::util::regex::Pattern* pattern);
    bool hasNextBigDecimal();
    bool hasNextBigInteger();
    bool hasNextBigInteger(int32_t radix);
    bool hasNextBoolean();
    bool hasNextByte();
    bool hasNextByte(int32_t radix);
    bool hasNextDouble();
    bool hasNextFloat();
    bool hasNextInt();
    bool hasNextInt(int32_t radix);
    bool hasNextLine();
    bool hasNextLong();
    bool hasNextLong(int32_t radix);
    bool hasNextShort();
    bool hasNextShort(int32_t radix);
    /*bool hasTokenInBuffer(); (private) */
    /*::java::util::regex::Pattern* integerPattern(); (private) */
    ::java::io::IOException* ioException();
    /*static ::java::util::regex::Pattern* linePattern(); (private) */
    Locale* locale();
    /*static ::java::lang::Readable* makeReadable(::java::nio::channels::ReadableByteChannel* source); (private) */
    /*static ::java::lang::Readable* makeReadable(::java::io::InputStream* source, ::java::nio::charset::Charset* charset); (private) */
    /*static ::java::lang::Readable* makeReadable(::java::nio::channels::ReadableByteChannel* source, ::java::nio::charset::CharsetDecoder* dec); (private) */
    /*bool makeSpace(); (private) */
    ::java::util::regex::MatchResult* match();
    /*::java::lang::String* matchPatternInBuffer(::java::util::regex::Pattern* pattern); (private) */
    ::java::lang::String* next() override;
    ::java::lang::String* next(::java::lang::String* pattern);
    ::java::lang::String* next(::java::util::regex::Pattern* pattern);
    ::java::math::BigDecimal* nextBigDecimal();
    ::java::math::BigInteger* nextBigInteger();
    ::java::math::BigInteger* nextBigInteger(int32_t radix);
    bool nextBoolean();
    int8_t nextByte();
    int8_t nextByte(int32_t radix);
    double nextDouble();
    float nextFloat();
    int32_t nextInt();
    int32_t nextInt(int32_t radix);
    ::java::lang::String* nextLine();
    int64_t nextLong();
    int64_t nextLong(int32_t radix);
    int16_t nextShort();
    int16_t nextShort(int32_t radix);
    /*::java::lang::String* processFloatToken(::java::lang::String* token); (private) */
    /*::java::lang::String* processIntegerToken(::java::lang::String* token); (private) */
    int32_t radix();
    /*void readInput(); (private) */
    void remove() override;
    Scanner* reset();
    /*void revertState(); (private) */
    /*bool revertState(bool b); (private) */
    /*void saveState(); (private) */
    /*static ::java::util::regex::Pattern* separatorPattern(); (private) */
    /*void setRadix(int32_t radix); (private) */
    Scanner* skip(::java::util::regex::Pattern* pattern);
    Scanner* skip(::java::lang::String* pattern);
    /*void throwFor(); (private) */
    /*static ::java::nio::charset::Charset* toCharset(::java::lang::String* csn); (private) */
    /*static ::java::nio::charset::CharsetDecoder* toDecoder(::java::lang::String* charsetName); (private) */
    ::java::lang::String* toString() override;
    /*void translateSavedIndexes(int32_t offset); (private) */
    Scanner* useDelimiter(::java::util::regex::Pattern* pattern);
    Scanner* useDelimiter(::java::lang::String* pattern);
    Scanner* useLocale(Locale* locale);
    Scanner* useRadix(int32_t radix);
    /*void useTypeCache(); (private) */

    // Generated
    Scanner(::java::lang::Readable* source);
    Scanner(::java::io::InputStream* source);
    Scanner(::java::io::File* source);
    Scanner(::java::nio::file::Path* source);
    Scanner(::java::lang::String* source);
    Scanner(::java::nio::channels::ReadableByteChannel* source);
    Scanner(::java::io::InputStream* source, ::java::lang::String* charsetName);
    Scanner(::java::io::File* source, ::java::lang::String* charsetName);
    Scanner(::java::nio::file::Path* source, ::java::lang::String* charsetName);
    Scanner(::java::nio::channels::ReadableByteChannel* source, ::java::lang::String* charsetName);
protected:
    Scanner(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

public: /* package */
    static bool& $assertionsDisabled();

private:
    static ::java::lang::String*& BOOLEAN_PATTERN();
    static ::java::util::regex::Pattern*& FIND_ANY_PATTERN();
    static ::java::lang::String*& LINE_PATTERN();
    static ::java::lang::String*& LINE_SEPARATOR_PATTERN();
    static ::java::util::regex::Pattern*& NON_ASCII_DIGIT();
    static ::java::util::regex::Pattern*& WHITESPACE_PATTERN();
    static std::atomic< ::java::util::regex::Pattern* >& boolPattern_();
    static std::atomic< ::java::util::regex::Pattern* >& linePattern_();
    static std::atomic< ::java::util::regex::Pattern* >& separatorPattern_();
    virtual ::java::lang::Class* getClass0();
};

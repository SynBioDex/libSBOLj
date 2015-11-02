// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/io/Serializable.hpp>

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

    namespace util
    {
        namespace regex
        {
typedef ::SubArray< ::java::util::regex::Pattern_Node, ::java::lang::ObjectArray > Pattern_NodeArray;
typedef ::SubArray< ::java::util::regex::Pattern_GroupHead, Pattern_NodeArray > Pattern_GroupHeadArray;
        } // regex
    } // util
} // java

struct default_init_tag;

class java::util::regex::Pattern final
    : public virtual ::java::lang::Object
    , public ::java::io::Serializable
{

public:
    typedef ::java::lang::Object super;

private:
    static bool $assertionsDisabled_;

public:
    static constexpr int32_t CANON_EQ { int32_t(128) };
    static constexpr int32_t CASE_INSENSITIVE { int32_t(2) };
    static constexpr int32_t COMMENTS { int32_t(4) };
    static constexpr int32_t DOTALL { int32_t(32) };

public: /* package */
    static constexpr int32_t GREEDY { int32_t(0) };
    static constexpr int32_t INDEPENDENT { int32_t(3) };
    static constexpr int32_t LAZY { int32_t(1) };

public:
    static constexpr int32_t LITERAL { int32_t(16) };

public: /* package */
    static constexpr int32_t MAX_REPS { int32_t(2147483647) };

public:
    static constexpr int32_t MULTILINE { int32_t(8) };

public: /* package */
    static constexpr int32_t POSSESSIVE { int32_t(2) };

public:
    static constexpr int32_t UNICODE_CASE { int32_t(64) };
    static constexpr int32_t UNICODE_CHARACTER_CLASS { int32_t(256) };
    static constexpr int32_t UNIX_LINES { int32_t(1) };

private:
    static Pattern_Node* accept__;

public: /* package */
    ::int32_tArray* buffer {  };
    int32_t capturingGroupCount {  };

private:
    std::atomic< bool > compiled {  };
    int32_t cursor_ {  };
    int32_t flags_ {  };

public: /* package */
    Pattern_GroupHeadArray* groupNodes {  };

private:
    bool hasSupplementary {  };
    static Pattern_Node* lastAccept_;

public: /* package */
    int32_t localCount {  };

private:
    static Pattern_Node* lookbehindEnd_;

public: /* package */
    Pattern_Node* matchRoot {  };
    std::atomic< ::java::util::Map* > namedGroups_ {  };

private:
    ::java::lang::String* normalizedPattern {  };
    ::java::lang::String* pattern_ {  };
    int32_t patternLength {  };

public: /* package */
    Pattern_Node* root {  };

private:
    static constexpr int64_t serialVersionUID { int64_t(5073258162644648461LL) };
    ::int32_tArray* temp {  };

    /*void ctor(::java::lang::String* p, int32_t f); (private) */
    /*void RemoveQEQuoting(); (private) */
    /*void accept(int32_t ch, ::java::lang::String* s); (private) */
    /*void addFlag(); (private) */
    /*void append(int32_t ch, int32_t len); (private) */
    /*Pattern_Node* atom(); (private) */
    /*Pattern_CharProperty* bitsOrSingle(Pattern_BitClass* bits, int32_t ch); (private) */
    /*int32_t c(); (private) */
    /*Pattern_CharProperty* caseInsensitiveRangeFor(int32_t lower, int32_t upper); (private) */
    /*Pattern_CharProperty* charPropertyNodeFor(::java::lang::String* name); (private) */
    /*Pattern_CharProperty* clazz(bool consume); (private) */
    /*Pattern_Node* closure(Pattern_Node* prev); (private) */
    /*void compile(); (private) */

public:
    static Pattern* compile(::java::lang::String* regex);
    static Pattern* compile(::java::lang::String* regex, int32_t flags);
    /*::java::lang::String* composeOneStep(::java::lang::String* input); (private) */
    /*static int32_t countChars(::java::lang::CharSequence* seq, int32_t index, int32_t lengthInCodePoints); (private) */
    /*static int32_t countCodePoints(::java::lang::CharSequence* seq); (private) */
    /*Pattern_Node* createGroup(bool anonymous); (private) */
    /*int32_t cursor(); (private) */
    /*PatternSyntaxException* error(::java::lang::String* s); (private) */
    /*int32_t escape(bool inclass, bool create); (private) */
    /*Pattern_Node* expr(Pattern_Node* end); (private) */
    /*Pattern_CharProperty* family(bool singleLetter, bool maybeComplement); (private) */
    /*bool findSupplementary(int32_t start, int32_t end); (private) */
    int32_t flags();
    /*int32_t getClass(int32_t c); (private) */
    /*Pattern_Node* group0(); (private) */
    /*::java::lang::String* groupname(int32_t ch); (private) */
    /*bool has(int32_t f); (private) */
    /*static bool hasBaseCharacter(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq); (private) */
    /*static bool inRange(int32_t lower, int32_t ch, int32_t upper); (private) */
    /*static Pattern_CharProperty* intersection(Pattern_CharProperty* lhs, Pattern_CharProperty* rhs); (private) */
    /*bool isLineSeparator(int32_t ch); (private) */
    /*static bool isSupplementary(int32_t ch); (private) */
    /*void mark(int32_t c); (private) */
    Matcher* matcher(::java::lang::CharSequence* input);
    static bool matches(::java::lang::String* regex, ::java::lang::CharSequence* input);

public: /* package */
    ::java::util::Map* namedGroups();
    /*Pattern_CharProperty* newSingle(int32_t ch); (private) */
    /*Pattern_Node* newSlice(::int32_tArray* buf, int32_t count, bool hasSupplementary); (private) */
    /*int32_t next(); (private) */
    /*int32_t nextEscaped(); (private) */
    /*void normalize(); (private) */
    /*int32_t normalizeCharClass(::java::lang::StringBuilder* newPattern, int32_t i); (private) */
    /*int32_t o(); (private) */
    /*int32_t parsePastLine(); (private) */
    /*int32_t parsePastWhitespace(int32_t ch); (private) */

public:
    ::java::lang::String* pattern();
    /*int32_t peek(); (private) */
    /*int32_t peekPastLine(); (private) */
    /*int32_t peekPastWhitespace(int32_t ch); (private) */
    /*static void printObjectTree(Pattern_Node* node); (private) */
    /*::java::lang::String* produceEquivalentAlternation(::java::lang::String* source); (private) */
    /*::java::lang::StringArray* producePermutations(::java::lang::String* input); (private) */
    static ::java::lang::String* quote(::java::lang::String* s);
    /*Pattern_CharProperty* range(Pattern_BitClass* bits); (private) */
    /*static Pattern_CharProperty* rangeFor(int32_t lower, int32_t upper); (private) */
    /*int32_t read(); (private) */
    /*int32_t readEscaped(); (private) */
    /*void readObject(::java::io::ObjectInputStream* s); (private) */
    /*Pattern_Node* ref(int32_t refNum); (private) */
    /*Pattern_Node* sequence(Pattern_Node* end); (private) */
    /*static Pattern_CharProperty* setDifference(Pattern_CharProperty* lhs, Pattern_CharProperty* rhs); (private) */
    /*void setcursor(int32_t pos); (private) */
    /*int32_t single(); (private) */
    /*int32_t skip(); (private) */
    ::java::lang::StringArray* split(::java::lang::CharSequence* input);
    ::java::lang::StringArray* split(::java::lang::CharSequence* input, int32_t limit);
    /*void subFlag(); (private) */
    ::java::lang::String* toString() override;
    /*int32_t u(); (private) */
    /*Pattern_CharProperty* unicodeBlockPropertyFor(::java::lang::String* name); (private) */
    /*Pattern_CharProperty* unicodeScriptPropertyFor(::java::lang::String* name); (private) */
    /*static Pattern_CharProperty* union_(Pattern_CharProperty* lhs, Pattern_CharProperty* rhs); (private) */
    /*void unread(); (private) */
    /*int32_t uxxxx(); (private) */
    /*int32_t x(); (private) */

    // Generated
    Pattern();
protected:
    Pattern(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

public: /* package */
    static bool& $assertionsDisabled();
    static Pattern_Node*& accept_();
    static Pattern_Node*& lastAccept();
    static Pattern_Node*& lookbehindEnd();

private:
    virtual ::java::lang::Class* getClass0();
};

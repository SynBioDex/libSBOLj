// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern.hpp>

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
java::util::regex::Pattern::Pattern(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

bool& java::util::regex::Pattern::$assertionsDisabled()
{
    clinit();
    return $assertionsDisabled_;
}
bool java::util::regex::Pattern::$assertionsDisabled_;
constexpr int32_t java::util::regex::Pattern::CANON_EQ;
constexpr int32_t java::util::regex::Pattern::CASE_INSENSITIVE;
constexpr int32_t java::util::regex::Pattern::COMMENTS;
constexpr int32_t java::util::regex::Pattern::DOTALL;
constexpr int32_t java::util::regex::Pattern::GREEDY;
constexpr int32_t java::util::regex::Pattern::INDEPENDENT;
constexpr int32_t java::util::regex::Pattern::LAZY;
constexpr int32_t java::util::regex::Pattern::LITERAL;
constexpr int32_t java::util::regex::Pattern::MAX_REPS;
constexpr int32_t java::util::regex::Pattern::MULTILINE;
constexpr int32_t java::util::regex::Pattern::POSSESSIVE;
constexpr int32_t java::util::regex::Pattern::UNICODE_CASE;
constexpr int32_t java::util::regex::Pattern::UNICODE_CHARACTER_CLASS;
constexpr int32_t java::util::regex::Pattern::UNIX_LINES;
java::util::regex::Pattern_Node*& java::util::regex::Pattern::accept_()
{
    clinit();
    return accept__;
}
java::util::regex::Pattern_Node* java::util::regex::Pattern::accept__;
java::util::regex::Pattern_Node*& java::util::regex::Pattern::lastAccept()
{
    clinit();
    return lastAccept_;
}
java::util::regex::Pattern_Node* java::util::regex::Pattern::lastAccept_;
java::util::regex::Pattern_Node*& java::util::regex::Pattern::lookbehindEnd()
{
    clinit();
    return lookbehindEnd_;
}
java::util::regex::Pattern_Node* java::util::regex::Pattern::lookbehindEnd_;
constexpr int64_t java::util::regex::Pattern::serialVersionUID;

/* private: void ::java::util::regex::Pattern::ctor(::java::lang::String* p, int32_t f) */
/* private: void java::util::regex::Pattern::RemoveQEQuoting() */
/* private: void java::util::regex::Pattern::accept(int32_t ch, ::java::lang::String* s) */
/* private: void java::util::regex::Pattern::addFlag() */
/* private: void java::util::regex::Pattern::append(int32_t ch, int32_t len) */
/* private: java::util::regex::Pattern_Node* java::util::regex::Pattern::atom() */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::bitsOrSingle(Pattern_BitClass* bits, int32_t ch) */
/* private: int32_t java::util::regex::Pattern::c() */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::caseInsensitiveRangeFor(int32_t lower, int32_t upper) */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::charPropertyNodeFor(::java::lang::String* name) */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::clazz(bool consume) */
/* private: java::util::regex::Pattern_Node* java::util::regex::Pattern::closure(Pattern_Node* prev) */
/* private: void java::util::regex::Pattern::compile() */
java::util::regex::Pattern* java::util::regex::Pattern::compile(::java::lang::String* regex)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::regex::Pattern* java::util::regex::Pattern::compile(::java::lang::String* regex)");
    return 0;
}

java::util::regex::Pattern* java::util::regex::Pattern::compile(::java::lang::String* regex, int32_t flags)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::regex::Pattern* java::util::regex::Pattern::compile(::java::lang::String* regex, int32_t flags)");
    return 0;
}

/* private: java::lang::String* java::util::regex::Pattern::composeOneStep(::java::lang::String* input) */
/* private: int32_t java::util::regex::Pattern::countChars(::java::lang::CharSequence* seq, int32_t index, int32_t lengthInCodePoints) */
/* private: int32_t java::util::regex::Pattern::countCodePoints(::java::lang::CharSequence* seq) */
/* private: java::util::regex::Pattern_Node* java::util::regex::Pattern::createGroup(bool anonymous) */
/* private: int32_t java::util::regex::Pattern::cursor() */
/* private: java::util::regex::PatternSyntaxException* java::util::regex::Pattern::error(::java::lang::String* s) */
/* private: int32_t java::util::regex::Pattern::escape(bool inclass, bool create) */
/* private: java::util::regex::Pattern_Node* java::util::regex::Pattern::expr(Pattern_Node* end) */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::family(bool singleLetter, bool maybeComplement) */
/* private: bool java::util::regex::Pattern::findSupplementary(int32_t start, int32_t end) */
int32_t java::util::regex::Pattern::flags()
{ /* stub */
    unimplemented_(u"int32_t java::util::regex::Pattern::flags()");
    return 0;
}

/* private: int32_t java::util::regex::Pattern::getClass(int32_t c) */
/* private: java::util::regex::Pattern_Node* java::util::regex::Pattern::group0() */
/* private: java::lang::String* java::util::regex::Pattern::groupname(int32_t ch) */
/* private: bool java::util::regex::Pattern::has(int32_t f) */
/* private: bool java::util::regex::Pattern::hasBaseCharacter(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) */
/* private: bool java::util::regex::Pattern::inRange(int32_t lower, int32_t ch, int32_t upper) */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::intersection(Pattern_CharProperty* lhs, Pattern_CharProperty* rhs) */
/* private: bool java::util::regex::Pattern::isLineSeparator(int32_t ch) */
/* private: bool java::util::regex::Pattern::isSupplementary(int32_t ch) */
/* private: void java::util::regex::Pattern::mark(int32_t c) */
java::util::regex::Matcher* java::util::regex::Pattern::matcher(::java::lang::CharSequence* input)
{ /* stub */
    unimplemented_(u"java::util::regex::Matcher* java::util::regex::Pattern::matcher(::java::lang::CharSequence* input)");
    return 0;
}

bool java::util::regex::Pattern::matches(::java::lang::String* regex, ::java::lang::CharSequence* input)
{ /* stub */
    clinit();
    unimplemented_(u"bool java::util::regex::Pattern::matches(::java::lang::String* regex, ::java::lang::CharSequence* input)");
    return 0;
}

java::util::Map* java::util::regex::Pattern::namedGroups()
{ /* stub */
    unimplemented_(u"java::util::Map* java::util::regex::Pattern::namedGroups()");
    return 0;
}

/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::newSingle(int32_t ch) */
/* private: java::util::regex::Pattern_Node* java::util::regex::Pattern::newSlice(::int32_tArray* buf, int32_t count, bool hasSupplementary) */
/* private: int32_t java::util::regex::Pattern::next() */
/* private: int32_t java::util::regex::Pattern::nextEscaped() */
/* private: void java::util::regex::Pattern::normalize() */
/* private: int32_t java::util::regex::Pattern::normalizeCharClass(::java::lang::StringBuilder* newPattern, int32_t i) */
/* private: int32_t java::util::regex::Pattern::o() */
/* private: int32_t java::util::regex::Pattern::parsePastLine() */
/* private: int32_t java::util::regex::Pattern::parsePastWhitespace(int32_t ch) */
java::lang::String* java::util::regex::Pattern::pattern()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::regex::Pattern::pattern()");
    return 0;
}

/* private: int32_t java::util::regex::Pattern::peek() */
/* private: int32_t java::util::regex::Pattern::peekPastLine() */
/* private: int32_t java::util::regex::Pattern::peekPastWhitespace(int32_t ch) */
/* private: void java::util::regex::Pattern::printObjectTree(Pattern_Node* node) */
/* private: java::lang::String* java::util::regex::Pattern::produceEquivalentAlternation(::java::lang::String* source) */
/* private: java::lang::StringArray* java::util::regex::Pattern::producePermutations(::java::lang::String* input) */
java::lang::String* java::util::regex::Pattern::quote(::java::lang::String* s)
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::String* java::util::regex::Pattern::quote(::java::lang::String* s)");
    return 0;
}

/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::range(Pattern_BitClass* bits) */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::rangeFor(int32_t lower, int32_t upper) */
/* private: int32_t java::util::regex::Pattern::read() */
/* private: int32_t java::util::regex::Pattern::readEscaped() */
/* private: void java::util::regex::Pattern::readObject(::java::io::ObjectInputStream* s) */
/* private: java::util::regex::Pattern_Node* java::util::regex::Pattern::ref(int32_t refNum) */
/* private: java::util::regex::Pattern_Node* java::util::regex::Pattern::sequence(Pattern_Node* end) */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::setDifference(Pattern_CharProperty* lhs, Pattern_CharProperty* rhs) */
/* private: void java::util::regex::Pattern::setcursor(int32_t pos) */
/* private: int32_t java::util::regex::Pattern::single() */
/* private: int32_t java::util::regex::Pattern::skip() */
java::lang::StringArray* java::util::regex::Pattern::split(::java::lang::CharSequence* input)
{ /* stub */
    unimplemented_(u"java::lang::StringArray* java::util::regex::Pattern::split(::java::lang::CharSequence* input)");
    return 0;
}

java::lang::StringArray* java::util::regex::Pattern::split(::java::lang::CharSequence* input, int32_t limit)
{ /* stub */
    unimplemented_(u"java::lang::StringArray* java::util::regex::Pattern::split(::java::lang::CharSequence* input, int32_t limit)");
    return 0;
}

/* private: void java::util::regex::Pattern::subFlag() */
java::lang::String* java::util::regex::Pattern::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::regex::Pattern::toString()");
    return 0;
}

/* private: int32_t java::util::regex::Pattern::u() */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::unicodeBlockPropertyFor(::java::lang::String* name) */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::unicodeScriptPropertyFor(::java::lang::String* name) */
/* private: java::util::regex::Pattern_CharProperty* java::util::regex::Pattern::union_(Pattern_CharProperty* lhs, Pattern_CharProperty* rhs) */
/* private: void java::util::regex::Pattern::unread() */
/* private: int32_t java::util::regex::Pattern::uxxxx() */
/* private: int32_t java::util::regex::Pattern::x() */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern", 23);
    return c;
}

java::lang::Class* java::util::regex::Pattern::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/regex/MatchResult.hpp>

struct default_init_tag;

class java::util::regex::Matcher final
    : public virtual ::java::lang::Object
    , public MatchResult
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    static constexpr int32_t ENDANCHOR { int32_t(1) };
    static constexpr int32_t NOANCHOR { int32_t(0) };
    int32_t acceptMode {  };
    bool anchoringBounds {  };
    int32_t first {  };
    int32_t from {  };
    ::int32_tArray* groups {  };
    bool hitEnd_ {  };
    int32_t last {  };
    int32_t lastAppendPosition {  };
    ::int32_tArray* locals {  };
    int32_t lookbehindTo {  };
    int32_t oldLast {  };
    Pattern* parentPattern {  };
    bool requireEnd_ {  };
    ::java::lang::CharSequence* text {  };
    int32_t to {  };
    bool transparentBounds {  };

protected:
    void ctor();
    void ctor(Pattern* parent, ::java::lang::CharSequence* text);

public:
    Matcher* appendReplacement(::java::lang::StringBuffer* sb, ::java::lang::String* replacement);
    ::java::lang::StringBuffer* appendTail(::java::lang::StringBuffer* sb);

public: /* package */
    char16_t charAt(int32_t i);

public:
    int32_t end() override;
    int32_t end(int32_t group) override;
    bool find();
    bool find(int32_t start);

public: /* package */
    ::java::lang::CharSequence* getSubSequence(int32_t beginIndex, int32_t endIndex);
    int32_t getTextLength();

public:
    ::java::lang::String* group() override;
    ::java::lang::String* group(int32_t group) override;
    ::java::lang::String* group(::java::lang::String* name);
    int32_t groupCount() override;
    bool hasAnchoringBounds();
    bool hasTransparentBounds();
    bool hitEnd();
    bool lookingAt();

public: /* package */
    bool match(int32_t from, int32_t anchor);

public:
    bool matches();
    Pattern* pattern();
    static ::java::lang::String* quoteReplacement(::java::lang::String* s);
    Matcher* region(int32_t start, int32_t end);
    int32_t regionEnd();
    int32_t regionStart();
    ::java::lang::String* replaceAll(::java::lang::String* replacement);
    ::java::lang::String* replaceFirst(::java::lang::String* replacement);
    bool requireEnd();
    Matcher* reset();
    Matcher* reset(::java::lang::CharSequence* input);

public: /* package */
    bool search(int32_t from);

public:
    int32_t start() override;
    int32_t start(int32_t group) override;
    MatchResult* toMatchResult();
    ::java::lang::String* toString() override;
    Matcher* useAnchoringBounds(bool b);
    Matcher* usePattern(Pattern* newPattern);
    Matcher* useTransparentBounds(bool b);

    // Generated

public: /* package */
    Matcher();
    Matcher(Pattern* parent, ::java::lang::CharSequence* text);
protected:
    Matcher(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

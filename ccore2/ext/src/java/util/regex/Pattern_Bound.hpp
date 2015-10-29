// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_Node.hpp>

struct default_init_tag;

class java::util::regex::Pattern_Bound final
    : public Pattern_Node
{

public:
    typedef Pattern_Node super;

private:
    static int32_t BOTH_;
    static int32_t LEFT_;
    static int32_t NONE_;
    static int32_t RIGHT_;

public: /* package */
    int32_t type {  };
    bool useUWORD {  };

protected:
    void ctor(int32_t n, bool useUWORD);

public: /* package */
    int32_t check(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq);
    bool isWord(int32_t ch);
    bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) override;

    // Generated
    Pattern_Bound(int32_t n, bool useUWORD);
protected:
    Pattern_Bound(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

public: /* package */
    static int32_t& BOTH();
    static int32_t& LEFT();
    static int32_t& NONE();
    static int32_t& RIGHT();

private:
    virtual ::java::lang::Class* getClass0();
};

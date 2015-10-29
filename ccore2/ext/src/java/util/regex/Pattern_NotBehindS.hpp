// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_NotBehind.hpp>

struct default_init_tag;

class java::util::regex::Pattern_NotBehindS final
    : public Pattern_NotBehind
{

public:
    typedef Pattern_NotBehind super;

protected:
    void ctor(Pattern_Node* cond, int32_t rmax, int32_t rmin);

public: /* package */
    bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) override;

    // Generated
    Pattern_NotBehindS(Pattern_Node* cond, int32_t rmax, int32_t rmin);
protected:
    Pattern_NotBehindS(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

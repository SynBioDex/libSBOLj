// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_SliceNode.hpp>

struct default_init_tag;

class java::util::regex::Pattern_SliceI
    : public Pattern_SliceNode
{

public:
    typedef Pattern_SliceNode super;

protected:
    void ctor(::int32_tArray* buf);

public: /* package */
    bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) override;

    // Generated
    Pattern_SliceI(::int32_tArray* buf);
protected:
    Pattern_SliceI(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

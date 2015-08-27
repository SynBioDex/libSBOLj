// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_BnM.hpp>

struct default_init_tag;

class java::util::regex::Pattern_BnMS final
    : public Pattern_BnM
{

public:
    typedef Pattern_BnM super;

public: /* package */
    int32_t lengthInChars {  };

protected:
    void ctor(::int32_tArray* src, ::int32_tArray* lastOcc, ::int32_tArray* optoSft, Pattern_Node* next);

public: /* package */
    bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) override;

    // Generated
    Pattern_BnMS(::int32_tArray* src, ::int32_tArray* lastOcc, ::int32_tArray* optoSft, Pattern_Node* next);
protected:
    Pattern_BnMS(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

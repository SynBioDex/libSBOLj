// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_Node.hpp>

struct default_init_tag;

class java::util::regex::Pattern_BnM
    : public Pattern_Node
{

public:
    typedef Pattern_Node super;

public: /* package */
    ::int32_tArray* buffer {  };
    ::int32_tArray* lastOcc {  };
    ::int32_tArray* optoSft {  };

protected:
    void ctor(::int32_tArray* src, ::int32_tArray* lastOcc, ::int32_tArray* optoSft, Pattern_Node* next);

public: /* package */
    bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) override;
    static Pattern_Node* optimize(Pattern_Node* node);
    bool study(Pattern_TreeInfo* info) override;

    // Generated
    Pattern_BnM(::int32_tArray* src, ::int32_tArray* lastOcc, ::int32_tArray* optoSft, Pattern_Node* next);
protected:
    Pattern_BnM(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

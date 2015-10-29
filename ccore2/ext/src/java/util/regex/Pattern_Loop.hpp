// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_Node.hpp>

struct default_init_tag;

class java::util::regex::Pattern_Loop
    : public Pattern_Node
{

public:
    typedef Pattern_Node super;

public: /* package */
    int32_t beginIndex {  };
    Pattern_Node* body {  };
    int32_t cmax {  };
    int32_t cmin {  };
    int32_t countIndex {  };

protected:
    void ctor(int32_t countIndex, int32_t beginIndex);

public: /* package */
    bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) override;
    virtual bool matchInit(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq);
    bool study(Pattern_TreeInfo* info) override;

    // Generated
    Pattern_Loop(int32_t countIndex, int32_t beginIndex);
protected:
    Pattern_Loop(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

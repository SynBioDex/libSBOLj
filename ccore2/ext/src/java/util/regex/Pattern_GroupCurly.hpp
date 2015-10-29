// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_Node.hpp>

struct default_init_tag;

class java::util::regex::Pattern_GroupCurly final
    : public Pattern_Node
{

public:
    typedef Pattern_Node super;

public: /* package */
    Pattern_Node* atom {  };
    bool capture {  };
    int32_t cmax {  };
    int32_t cmin {  };
    int32_t groupIndex {  };
    int32_t localIndex {  };
    int32_t type {  };

protected:
    void ctor(Pattern_Node* node, int32_t cmin, int32_t cmax, int32_t type, int32_t local, int32_t group, bool capture);

public: /* package */
    bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) override;
    bool match0(Matcher* matcher, int32_t i, int32_t j, ::java::lang::CharSequence* seq);
    bool match1(Matcher* matcher, int32_t i, int32_t j, ::java::lang::CharSequence* seq);
    bool match2(Matcher* matcher, int32_t i, int32_t j, ::java::lang::CharSequence* seq);
    bool study(Pattern_TreeInfo* info) override;

    // Generated
    Pattern_GroupCurly(Pattern_Node* node, int32_t cmin, int32_t cmax, int32_t type, int32_t local, int32_t group, bool capture);
protected:
    Pattern_GroupCurly(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

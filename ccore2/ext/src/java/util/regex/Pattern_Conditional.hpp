// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_Node.hpp>

struct default_init_tag;

class java::util::regex::Pattern_Conditional final
    : public Pattern_Node
{

public:
    typedef Pattern_Node super;

public: /* package */
    Pattern_Node* cond {  };
    Pattern_Node* not_ {  };
    Pattern_Node* yes {  };

protected:
    void ctor(Pattern_Node* cond, Pattern_Node* yes, Pattern_Node* not_);

public: /* package */
    bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq) override;
    bool study(Pattern_TreeInfo* info) override;

    // Generated
    Pattern_Conditional(Pattern_Node* cond, Pattern_Node* yes, Pattern_Node* not_);
protected:
    Pattern_Conditional(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::regex::Pattern_Node
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    Pattern_Node* next {  };

protected:
    void ctor();

public: /* package */
    virtual bool match(Matcher* matcher, int32_t i, ::java::lang::CharSequence* seq);
    virtual bool study(Pattern_TreeInfo* info);

    // Generated
    Pattern_Node();
protected:
    Pattern_Node(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

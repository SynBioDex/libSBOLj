// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_Node.hpp>

struct default_init_tag;

class java::util::regex::Pattern_SliceNode
    : public Pattern_Node
{

public:
    typedef Pattern_Node super;

public: /* package */
    ::int32_tArray* buffer {  };

protected:
    void ctor(::int32_tArray* buf);

public: /* package */
    bool study(Pattern_TreeInfo* info) override;

    // Generated
    Pattern_SliceNode(::int32_tArray* buf);
protected:
    Pattern_SliceNode(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

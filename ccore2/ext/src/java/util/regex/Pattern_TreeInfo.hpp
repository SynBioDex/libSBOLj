// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::regex::Pattern_TreeInfo final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    bool deterministic {  };
    int32_t maxLength {  };
    bool maxValid {  };
    int32_t minLength {  };

protected:
    void ctor();

public: /* package */
    void reset();

    // Generated
    Pattern_TreeInfo();
protected:
    Pattern_TreeInfo(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

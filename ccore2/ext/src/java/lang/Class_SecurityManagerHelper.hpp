// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::lang::Class_SecurityManagerHelper
    : public virtual Object
{

public:
    typedef Object super;

public: /* package */
    bool overrideCheckMemberAccess {  };
    SecurityManager* sm {  };

protected:
    void ctor(SecurityManager* sm);

    // Generated

public: /* package */
    Class_SecurityManagerHelper(SecurityManager* sm);
protected:
    Class_SecurityManagerHelper(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

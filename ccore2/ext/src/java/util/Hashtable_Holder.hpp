// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::Hashtable_Holder
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static int32_t ALTERNATIVE_HASHING_THRESHOLD_;

    /*void ctor(); (private) */

    // Generated

public:
    Hashtable_Holder();
protected:
    Hashtable_Holder(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

public: /* package */
    static int32_t& ALTERNATIVE_HASHING_THRESHOLD();

private:
    virtual ::java::lang::Class* getClass0();
};

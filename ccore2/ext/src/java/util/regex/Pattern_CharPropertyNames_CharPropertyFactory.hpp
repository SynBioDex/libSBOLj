// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::regex::Pattern_CharPropertyNames_CharPropertyFactory
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

    /*void ctor(); (private) */

public: /* package */
    virtual Pattern_CharProperty* make() = 0;

    // Generated

public:
    Pattern_CharPropertyNames_CharPropertyFactory();
protected:
    Pattern_CharPropertyNames_CharPropertyFactory(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_CharProperty.hpp>
#include <java/lang/Cloneable.hpp>

struct default_init_tag;

class java::util::regex::Pattern_CharPropertyNames_CloneableProperty
    : public Pattern_CharProperty
    , public virtual ::java::lang::Cloneable
{

public:
    typedef Pattern_CharProperty super;

    /*void ctor(); (private) */
    Pattern_CharPropertyNames_CloneableProperty* clone() override;

    // Generated
    Pattern_CharPropertyNames_CloneableProperty();
protected:
    Pattern_CharPropertyNames_CloneableProperty(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

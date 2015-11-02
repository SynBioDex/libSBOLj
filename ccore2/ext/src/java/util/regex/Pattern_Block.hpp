// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_CharProperty.hpp>

struct default_init_tag;

class java::util::regex::Pattern_Block final
    : public Pattern_CharProperty
{

public:
    typedef Pattern_CharProperty super;

public: /* package */
    ::java::lang::Character_UnicodeBlock* block {  };

protected:
    void ctor(::java::lang::Character_UnicodeBlock* block);

public: /* package */
    bool isSatisfiedBy(int32_t ch) override;

    // Generated
    Pattern_Block(::java::lang::Character_UnicodeBlock* block);
protected:
    Pattern_Block(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_BmpCharProperty.hpp>

struct default_init_tag;

class java::util::regex::Pattern_SingleI final
    : public Pattern_BmpCharProperty
{

public:
    typedef Pattern_BmpCharProperty super;

public: /* package */
    int32_t lower {  };
    int32_t upper {  };

protected:
    void ctor(int32_t lower, int32_t upper);

public: /* package */
    bool isSatisfiedBy(int32_t ch) override;

    // Generated
    Pattern_SingleI(int32_t lower, int32_t upper);
protected:
    Pattern_SingleI(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};
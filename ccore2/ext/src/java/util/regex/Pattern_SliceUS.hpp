// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/Pattern_SliceIS.hpp>

struct default_init_tag;

class java::util::regex::Pattern_SliceUS final
    : public Pattern_SliceIS
{

public:
    typedef Pattern_SliceIS super;

protected:
    void ctor(::int32_tArray* buf);

public: /* package */
    int32_t toLower(int32_t c) override;

    // Generated
    Pattern_SliceUS(::int32_tArray* buf);
protected:
    Pattern_SliceUS(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

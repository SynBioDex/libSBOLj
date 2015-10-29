// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Version.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Version final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    static bool isFirstVersionNewer(::java::lang::String* version1, ::java::lang::String* version2);

    // Generated

public:
    Version();
protected:
    Version(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

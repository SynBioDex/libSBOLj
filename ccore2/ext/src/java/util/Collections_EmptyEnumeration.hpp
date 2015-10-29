// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Enumeration.hpp>

struct default_init_tag;

class java::util::Collections_EmptyEnumeration
    : public virtual ::java::lang::Object
    , public virtual Enumeration
{

public:
    typedef ::java::lang::Object super;

private:
    static Collections_EmptyEnumeration* EMPTY_ENUMERATION_;

    /*void ctor(); (private) */

public:
    bool hasMoreElements() override;
    ::java::lang::Object* nextElement() override;

    // Generated
    Collections_EmptyEnumeration();
protected:
    Collections_EmptyEnumeration(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

public: /* package */
    static Collections_EmptyEnumeration*& EMPTY_ENUMERATION();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/RuntimeException.hpp>

struct default_init_tag;

class java::lang::ClassCastException
    : public RuntimeException
{

public:
    typedef RuntimeException super;

private:
    static constexpr int64_t serialVersionUID { int64_t(-9223365651070458532LL) };

protected:
    void ctor();
    void ctor(String* s);

    // Generated

public:
    ClassCastException();
    ClassCastException(String* s);
protected:
    ClassCastException(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

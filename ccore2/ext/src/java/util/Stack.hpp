// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Vector.hpp>

struct default_init_tag;

class java::util::Stack
    : public Vector
{

public:
    typedef Vector super;

private:
    static constexpr int64_t serialVersionUID { int64_t(1224463164541339165LL) };

protected:
    void ctor();

public:
    virtual bool empty();
    virtual ::java::lang::Object* peek();
    virtual ::java::lang::Object* pop();
    virtual ::java::lang::Object* push(::java::lang::Object* item);
    virtual int32_t search(::java::lang::Object* o);

    // Generated
    Stack();
protected:
    Stack(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

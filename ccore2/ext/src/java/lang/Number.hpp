// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::lang::Number
    : public virtual Object
    , public virtual ::java::io::Serializable
{

public:
    typedef Object super;

private:
    static constexpr int64_t serialVersionUID { int64_t(-8742448824652078965LL) };

protected:
    void ctor();

public:
    virtual int8_t byteValue();
    virtual double doubleValue() = 0;
    virtual float floatValue() = 0;
    virtual int32_t intValue() = 0;
    virtual int64_t longValue() = 0;
    virtual int16_t shortValue();

    // Generated
    Number();
protected:
    Number(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>

struct default_init_tag;

class java::lang::Object
{

protected:
    void ctor();

public: /* protected */
    virtual Object* clone();

public:
    virtual bool equals(Object* obj);

public: /* protected */
    virtual void finalize();

public:
    Class* getClass();
    virtual int32_t hashCode();
    void notify();
    void notifyAll();
    /*static void registerNatives(); (private) */
    virtual String* toString();
    void wait();
    void wait(int64_t timeout);
    void wait(int64_t timeout, int32_t nanos);

    // Generated
    Object();
protected:
    Object(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();
    virtual ~Object();

private:
    virtual ::java::lang::Class* getClass0();
};

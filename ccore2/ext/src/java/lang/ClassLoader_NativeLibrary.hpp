// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::lang::ClassLoader_NativeLibrary
    : public virtual Object
{

public:
    typedef Object super;

private:
    Class* fromClass {  };

public: /* package */
    int64_t handle {  };

private:
    int32_t jniVersion {  };

public: /* package */
    String* name {  };

protected:
    void ctor(Class* fromClass, String* name);

public: /* protected */
    void finalize() override;

public: /* package */
    virtual int64_t find(String* name);
    static Class* getFromClass();
    virtual void load(String* name);
    virtual void unload();

    // Generated

public:
    ClassLoader_NativeLibrary(Class* fromClass, String* name);
protected:
    ClassLoader_NativeLibrary(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

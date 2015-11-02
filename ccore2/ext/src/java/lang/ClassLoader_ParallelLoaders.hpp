// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::lang::ClassLoader_ParallelLoaders
    : public virtual Object
{

public:
    typedef Object super;

private:
    static ::java::util::Set* loaderTypes_;

    /*void ctor(); (private) */

public: /* package */
    static bool isRegistered(Class* c);
    static bool register_(Class* c);

    // Generated

public:
    ClassLoader_ParallelLoaders();
protected:
    ClassLoader_ParallelLoaders(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static ::java::util::Set*& loaderTypes();
    virtual ::java::lang::Class* getClass0();
};

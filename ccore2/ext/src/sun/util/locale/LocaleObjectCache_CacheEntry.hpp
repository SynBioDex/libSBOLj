// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/ref/fwd-${project.parent.artifactId}-core2.hpp>
#include <sun/util/locale/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/ref/SoftReference.hpp>

struct default_init_tag;

class sun::util::locale::LocaleObjectCache_CacheEntry
    : public ::java::lang::ref::SoftReference
{

public:
    typedef ::java::lang::ref::SoftReference super;

private:
    ::java::lang::Object* key {  };

protected:
    void ctor(::java::lang::Object* arg0, ::java::lang::Object* arg1, ::java::lang::ref::ReferenceQueue* arg2);

public: /* package */
    virtual ::java::lang::Object* getKey();

    // Generated
    LocaleObjectCache_CacheEntry(::java::lang::Object* arg0, ::java::lang::Object* arg1, ::java::lang::ref::ReferenceQueue* arg2);
protected:
    LocaleObjectCache_CacheEntry(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/regex/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::regex::Pattern_CharPropertyNames
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    static ::java::util::HashMap* map_;

    /*void ctor(); (private) */

public: /* package */
    static Pattern_CharProperty* charPropertyFor(::java::lang::String* name);
    /*static void defCategory(::java::lang::String* name, int32_t typeMask); (private) */
    /*static void defClone(::java::lang::String* name, Pattern_CharPropertyNames_CloneableProperty* p); (private) */
    /*static void defCtype(::java::lang::String* name, int32_t ctype); (private) */
    /*static void defRange(::java::lang::String* name, int32_t lower, int32_t upper); (private) */

    // Generated

public:
    Pattern_CharPropertyNames();
protected:
    Pattern_CharPropertyNames(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static ::java::util::HashMap*& map();
    virtual ::java::lang::Class* getClass0();
};

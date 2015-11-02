// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Comparator.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_ReverseComparator
    : public virtual ::java::lang::Object
    , public virtual Comparator
    , public virtual ::java::io::Serializable
{

public:
    typedef ::java::lang::Object super;

private:
    static Collections_ReverseComparator* REVERSE_ORDER_;
    static constexpr int64_t serialVersionUID { int64_t(7207038068494060240LL) };

    /*void ctor(); (private) */

public:
    virtual int32_t compare(::java::lang::Comparable* c1, ::java::lang::Comparable* c2);
    /*::java::lang::Object* readResolve(); (private) */

    // Generated
    Collections_ReverseComparator();
protected:
    Collections_ReverseComparator(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual bool equals(::java::lang::Object* obj);
    virtual int32_t compare(::java::lang::Object* o1, ::java::lang::Object* o2) override;

public: /* package */
    static Collections_ReverseComparator*& REVERSE_ORDER();

private:
    virtual ::java::lang::Class* getClass0();
};

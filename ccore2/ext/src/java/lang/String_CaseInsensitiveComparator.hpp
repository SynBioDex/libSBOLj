// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Comparator.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::lang::String_CaseInsensitiveComparator
    : public virtual Object
    , public virtual ::java::util::Comparator
    , public virtual ::java::io::Serializable
{

public:
    typedef Object super;

private:
    static constexpr int64_t serialVersionUID { int64_t(8575799808933029326LL) };

    /*void ctor(); (private) */

public:
    virtual int32_t compare(String* s1, String* s2);

    // Generated
    String_CaseInsensitiveComparator();
protected:
    String_CaseInsensitiveComparator(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual bool equals(Object* obj);
    virtual int32_t compare(Object* o1, Object* o2) override;

private:
    virtual ::java::lang::Class* getClass0();
};

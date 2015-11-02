// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Comparator.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_ReverseComparator2
    : public virtual ::java::lang::Object
    , public virtual Comparator
    , public virtual ::java::io::Serializable
{

public:
    typedef ::java::lang::Object super;

private:
    static bool $assertionsDisabled_;

public: /* package */
    Comparator* cmp {  };

private:
    static constexpr int64_t serialVersionUID { int64_t(4374092139857LL) };

protected:
    void ctor(Comparator* cmp);

public:
    int32_t compare(::java::lang::Object* t1, ::java::lang::Object* t2) override;
    bool equals(::java::lang::Object* o) override;
    int32_t hashCode() override;

    // Generated

public: /* package */
    Collections_ReverseComparator2(Comparator* cmp);
protected:
    Collections_ReverseComparator2(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

public: /* package */
    static bool& $assertionsDisabled();

private:
    virtual ::java::lang::Class* getClass0();
};

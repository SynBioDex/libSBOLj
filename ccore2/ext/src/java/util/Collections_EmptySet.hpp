// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractSet.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_EmptySet
    : public AbstractSet
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractSet super;

private:
    static constexpr int64_t serialVersionUID { int64_t(1582296315990362920LL) };

    /*void ctor(); (private) */

public:
    bool contains(::java::lang::Object* obj) override;
    bool containsAll(Collection* c) override;
    bool isEmpty() override;
    Iterator* iterator() override;
    /*::java::lang::Object* readResolve(); (private) */
    int32_t size() override;
    ::java::lang::ObjectArray* toArray_() override;
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) override;

    // Generated
    Collections_EmptySet();
protected:
    Collections_EmptySet(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

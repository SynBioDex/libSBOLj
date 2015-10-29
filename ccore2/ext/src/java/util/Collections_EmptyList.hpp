// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractList.hpp>
#include <java/util/RandomAccess.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_EmptyList
    : public AbstractList
    , public virtual RandomAccess
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractList super;

private:
    static constexpr int64_t serialVersionUID { int64_t(8842843931221139166LL) };

    /*void ctor(); (private) */

public:
    bool contains(::java::lang::Object* obj) override;
    bool containsAll(Collection* c) override;
    bool equals(::java::lang::Object* o) override;
    ::java::lang::Object* get(int32_t index) override;
    int32_t hashCode() override;
    bool isEmpty() override;
    Iterator* iterator() override;
    ListIterator* listIterator() override;
    /*::java::lang::Object* readResolve(); (private) */
    int32_t size() override;
    ::java::lang::ObjectArray* toArray_() override;
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) override;

    // Generated
    Collections_EmptyList();
protected:
    Collections_EmptyList(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    ListIterator* listIterator(int32_t index);

private:
    virtual ::java::lang::Class* getClass0();
};

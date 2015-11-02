// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractCollection.hpp>
#include <java/util/Set.hpp>

struct default_init_tag;

class java::util::AbstractSet
    : public AbstractCollection
    , public virtual Set
{

public:
    typedef AbstractCollection super;

protected:
    void ctor();

public:
    bool equals(::java::lang::Object* o) override;
    int32_t hashCode() override;
    bool removeAll(Collection* c) override;

    // Generated

public: /* protected */
    AbstractSet();
protected:
    AbstractSet(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool add(::java::lang::Object* e);
    bool addAll(Collection* c);
    void clear();
    bool contains(::java::lang::Object* o);
    bool containsAll(Collection* c);
    bool isEmpty();
    bool remove(::java::lang::Object* o);
    bool retainAll(Collection* c);
    ::java::lang::ObjectArray* toArray_();
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a);

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractCollection.hpp>
#include <java/util/Queue.hpp>

struct default_init_tag;

class java::util::AbstractQueue
    : public AbstractCollection
    , public virtual Queue
{

public:
    typedef AbstractCollection super;

protected:
    void ctor();

public:
    bool add(::java::lang::Object* e) override;
    bool addAll(Collection* c) override;
    void clear() override;
    ::java::lang::Object* element() override;
    ::java::lang::Object* remove() override;

    // Generated

public: /* protected */
    AbstractQueue();
protected:
    AbstractQueue(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual bool contains(::java::lang::Object* o);
    virtual bool containsAll(Collection* c);
    virtual bool equals(::java::lang::Object* o);
    virtual int32_t hashCode();
    virtual bool isEmpty();
    virtual bool remove(::java::lang::Object* o);
    virtual bool removeAll(Collection* c);
    virtual bool retainAll(Collection* c);
    virtual ::java::lang::ObjectArray* toArray_();
    virtual ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a);

private:
    virtual ::java::lang::Class* getClass0();
};

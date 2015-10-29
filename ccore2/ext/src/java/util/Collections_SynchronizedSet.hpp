// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Collections_SynchronizedCollection.hpp>
#include <java/util/Set.hpp>

struct default_init_tag;

class java::util::Collections_SynchronizedSet
    : public Collections_SynchronizedCollection
    , public virtual Set
{

public:
    typedef Collections_SynchronizedCollection super;

private:
    static constexpr int64_t serialVersionUID { int64_t(487447009682186044LL) };

protected:
    void ctor(Set* s);
    void ctor(Set* s, ::java::lang::Object* mutex);

public:
    bool equals(::java::lang::Object* o) override;
    int32_t hashCode() override;

    // Generated

public: /* package */
    Collections_SynchronizedSet(Set* s);
    Collections_SynchronizedSet(Set* s, ::java::lang::Object* mutex);
protected:
    Collections_SynchronizedSet(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool add(::java::lang::Object* e);
    bool addAll(Collection* c);
    void clear();
    bool contains(::java::lang::Object* o);
    bool containsAll(Collection* c);
    bool isEmpty();
    Iterator* iterator();
    bool remove(::java::lang::Object* o);
    bool removeAll(Collection* c);
    bool retainAll(Collection* c);
    int32_t size();
    ::java::lang::ObjectArray* toArray_();
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a);

private:
    virtual ::java::lang::Class* getClass0();
};

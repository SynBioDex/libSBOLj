// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractSet.hpp>
#include <java/util/Set.hpp>
#include <java/lang/Cloneable.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::HashSet
    : public AbstractSet
    , public virtual Set
    , public virtual ::java::lang::Cloneable
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractSet super;

private:
    static ::java::lang::Object* PRESENT_;
    HashMap* map {  };

public: /* package */
    static constexpr int64_t serialVersionUID { int64_t(-5024744406713321676LL) };

protected:
    void ctor();
    void ctor(Collection* c);
    void ctor(int32_t initialCapacity);
    void ctor(int32_t initialCapacity, float loadFactor);
    void ctor(int32_t initialCapacity, float loadFactor, bool dummy);

public:
    bool add(::java::lang::Object* e) override;
    void clear() override;
    ::java::lang::Object* clone() override;
    bool contains(::java::lang::Object* o) override;
    bool isEmpty() override;
    Iterator* iterator() override;
    /*void readObject(::java::io::ObjectInputStream* s); (private) */
    bool remove(::java::lang::Object* o) override;
    int32_t size() override;
    /*void writeObject(::java::io::ObjectOutputStream* s); (private) */

    // Generated
    HashSet();
    HashSet(Collection* c);
    HashSet(int32_t initialCapacity);
    HashSet(int32_t initialCapacity, float loadFactor);

public: /* package */
    HashSet(int32_t initialCapacity, float loadFactor, bool dummy);
protected:
    HashSet(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool addAll(Collection* c);
    bool containsAll(Collection* c);
    bool equals(::java::lang::Object* o);
    int32_t hashCode();
    bool removeAll(Collection* c);
    bool retainAll(Collection* c);
    ::java::lang::ObjectArray* toArray_();
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a);

private:
    static ::java::lang::Object*& PRESENT();
    virtual ::java::lang::Class* getClass0();
};

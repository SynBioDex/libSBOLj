// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Collection.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_SynchronizedCollection
    : public virtual ::java::lang::Object
    , public virtual Collection
    , public virtual ::java::io::Serializable
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    Collection* c {  };
    ::java::lang::Object* mutex {  };

private:
    static constexpr int64_t serialVersionUID { int64_t(3053995032091335093LL) };

protected:
    void ctor(Collection* c);
    void ctor(Collection* c, ::java::lang::Object* mutex);

public:
    bool add(::java::lang::Object* e) override;
    bool addAll(Collection* coll) override;
    void clear() override;
    bool contains(::java::lang::Object* o) override;
    bool containsAll(Collection* coll) override;
    bool isEmpty() override;
    Iterator* iterator() override;
    bool remove(::java::lang::Object* o) override;
    bool removeAll(Collection* coll) override;
    bool retainAll(Collection* coll) override;
    int32_t size() override;
    ::java::lang::ObjectArray* toArray_() override;
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) override;
    ::java::lang::String* toString() override;
    /*void writeObject(::java::io::ObjectOutputStream* s); (private) */

    // Generated

public: /* package */
    Collections_SynchronizedCollection(Collection* c);
    Collections_SynchronizedCollection(Collection* c, ::java::lang::Object* mutex);
protected:
    Collections_SynchronizedCollection(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual bool equals(::java::lang::Object* o);
    virtual int32_t hashCode();

private:
    virtual ::java::lang::Class* getClass0();
};

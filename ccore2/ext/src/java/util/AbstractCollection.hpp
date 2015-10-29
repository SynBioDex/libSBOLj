// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Collection.hpp>

struct default_init_tag;

class java::util::AbstractCollection
    : public virtual ::java::lang::Object
    , public virtual Collection
{

public:
    typedef ::java::lang::Object super;

private:
    static constexpr int32_t MAX_ARRAY_SIZE { int32_t(2147483639) };

protected:
    void ctor();

public:
    bool add(::java::lang::Object* e) override;
    bool addAll(Collection* c) override;
    void clear() override;
    bool contains(::java::lang::Object* o) override;
    bool containsAll(Collection* c) override;
    /*static ::java::lang::ObjectArray* finishToArray_(::java::lang::ObjectArray* r, Iterator* it); (private) */
    /*static int32_t hugeCapacity(int32_t minCapacity); (private) */
    bool isEmpty() override;
    /*Iterator* iterator(); (already declared) */
    bool remove(::java::lang::Object* o) override;
    bool removeAll(Collection* c) override;
    bool retainAll(Collection* c) override;
    /*int32_t size(); (already declared) */
    ::java::lang::ObjectArray* toArray_() override;
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) override;
    ::java::lang::String* toString() override;

    // Generated

public: /* protected */
    AbstractCollection();
protected:
    AbstractCollection(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual bool equals(::java::lang::Object* o);
    virtual int32_t hashCode();

private:
    virtual ::java::lang::Class* getClass0();
};

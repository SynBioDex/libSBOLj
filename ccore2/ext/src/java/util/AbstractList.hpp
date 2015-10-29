// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractCollection.hpp>
#include <java/util/List.hpp>

struct default_init_tag;

class java::util::AbstractList
    : public AbstractCollection
    , public virtual List
{

public:
    typedef AbstractCollection super;

public: /* protected */
    int32_t modCount {  };

protected:
    void ctor();

public:
    bool add(::java::lang::Object* e) override;
    void add(int32_t index, ::java::lang::Object* element) override;
    bool addAll(int32_t index, Collection* c) override;
    void clear() override;
    bool equals(::java::lang::Object* o) override;
    /*::java::lang::Object* get(int32_t index); (already declared) */
    int32_t hashCode() override;
    int32_t indexOf(::java::lang::Object* o) override;
    Iterator* iterator() override;
    int32_t lastIndexOf(::java::lang::Object* o) override;
    ListIterator* listIterator() override;
    ListIterator* listIterator(int32_t index) override;
    /*::java::lang::String* outOfBoundsMsg(int32_t index); (private) */
    /*void rangeCheckForAdd(int32_t index); (private) */
    ::java::lang::Object* remove(int32_t index) override;

public: /* protected */
    virtual void removeRange(int32_t fromIndex, int32_t toIndex);

public:
    ::java::lang::Object* set(int32_t index, ::java::lang::Object* element) override;
    List* subList(int32_t fromIndex, int32_t toIndex) override;

    // Generated

public: /* protected */
    AbstractList();
protected:
    AbstractList(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool addAll(Collection* c);
    bool contains(::java::lang::Object* o);
    bool containsAll(Collection* c);
    bool isEmpty();
    bool remove(::java::lang::Object* o);
    bool removeAll(Collection* c);
    bool retainAll(Collection* c);
    ::java::lang::ObjectArray* toArray_();
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a);

private:
    virtual ::java::lang::Class* getClass0();
};

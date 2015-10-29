// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Collections_SynchronizedCollection.hpp>
#include <java/util/List.hpp>

struct default_init_tag;

class java::util::Collections_SynchronizedList
    : public Collections_SynchronizedCollection
    , public virtual List
{

public:
    typedef Collections_SynchronizedCollection super;

public: /* package */
    List* list {  };

private:
    static constexpr int64_t serialVersionUID { int64_t(-7754090372962971524LL) };

protected:
    void ctor(List* list);
    void ctor(List* list, ::java::lang::Object* mutex);

public:
    void add(int32_t index, ::java::lang::Object* element) override;
    bool addAll(int32_t index, Collection* c) override;
    bool equals(::java::lang::Object* o) override;
    ::java::lang::Object* get(int32_t index) override;
    int32_t hashCode() override;
    int32_t indexOf(::java::lang::Object* o) override;
    int32_t lastIndexOf(::java::lang::Object* o) override;
    ListIterator* listIterator() override;
    ListIterator* listIterator(int32_t index) override;
    /*::java::lang::Object* readResolve(); (private) */
    ::java::lang::Object* remove(int32_t index) override;
    ::java::lang::Object* set(int32_t index, ::java::lang::Object* element) override;
    List* subList(int32_t fromIndex, int32_t toIndex) override;

    // Generated

public: /* package */
    Collections_SynchronizedList(List* list);
    Collections_SynchronizedList(List* list, ::java::lang::Object* mutex);
protected:
    Collections_SynchronizedList(const ::default_init_tag&);


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

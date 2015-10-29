// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Collections_CheckedSet.hpp>
#include <java/util/SortedSet.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_CheckedSortedSet
    : public Collections_CheckedSet
    , public virtual SortedSet
    , public virtual ::java::io::Serializable
{

public:
    typedef Collections_CheckedSet super;

private:
    static constexpr int64_t serialVersionUID { int64_t(1599911165492914959LL) };
    SortedSet* ss {  };

protected:
    void ctor(SortedSet* s, ::java::lang::Class* type);

public:
    Comparator* comparator() override;
    ::java::lang::Object* first() override;
    SortedSet* headSet(::java::lang::Object* toElement) override;
    ::java::lang::Object* last() override;
    SortedSet* subSet(::java::lang::Object* fromElement, ::java::lang::Object* toElement) override;
    SortedSet* tailSet(::java::lang::Object* fromElement) override;

    // Generated

public: /* package */
    Collections_CheckedSortedSet(SortedSet* s, ::java::lang::Class* type);
protected:
    Collections_CheckedSortedSet(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool add(::java::lang::Object* e);
    bool addAll(Collection* c);
    void clear();
    bool contains(::java::lang::Object* o);
    bool containsAll(Collection* c);
    bool equals(::java::lang::Object* o);
    int32_t hashCode();
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

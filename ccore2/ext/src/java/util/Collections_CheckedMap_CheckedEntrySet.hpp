// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Set.hpp>

struct default_init_tag;

class java::util::Collections_CheckedMap_CheckedEntrySet
    : public virtual ::java::lang::Object
    , public virtual Set
{

public:
    typedef ::java::lang::Object super;

private:
    Set* s {  };
    ::java::lang::Class* valueType {  };

protected:
    void ctor(Set* s, ::java::lang::Class* valueType);

public:
    virtual bool add(Map_Entry* e);
    bool addAll(Collection* coll) override;
    /*bool batchRemove(Collection* c, bool complement); (private) */

public: /* package */
    static Collections_CheckedMap_CheckedEntrySet_CheckedEntry* checkedEntry(Map_Entry* e, ::java::lang::Class* valueType);

public:
    void clear() override;
    bool contains(::java::lang::Object* o) override;
    bool containsAll(Collection* c) override;
    bool equals(::java::lang::Object* o) override;
    int32_t hashCode() override;
    bool isEmpty() override;
    Iterator* iterator() override;
    bool remove(::java::lang::Object* o) override;
    bool removeAll(Collection* c) override;
    bool retainAll(Collection* c) override;
    int32_t size() override;
    ::java::lang::ObjectArray* toArray_() override;
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) override;
    ::java::lang::String* toString() override;

    // Generated

public: /* package */
    Collections_CheckedMap_CheckedEntrySet(Set* s, ::java::lang::Class* valueType);
protected:
    Collections_CheckedMap_CheckedEntrySet(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool add(::java::lang::Object* e) override;

private:
    virtual ::java::lang::Class* getClass0();
};

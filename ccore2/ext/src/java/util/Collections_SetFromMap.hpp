// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractSet.hpp>
#include <java/util/Set.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_SetFromMap
    : public AbstractSet
    , public virtual Set
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractSet super;

private:
    Map* m {  };
    Set* s {  };
    static constexpr int64_t serialVersionUID { int64_t(2454657854757543876LL) };

protected:
    void ctor(Map* map);

public:
    bool add(::java::lang::Object* e) override;
    void clear() override;
    bool contains(::java::lang::Object* o) override;
    bool containsAll(Collection* c) override;
    bool equals(::java::lang::Object* o) override;
    int32_t hashCode() override;
    bool isEmpty() override;
    Iterator* iterator() override;
    /*void readObject(::java::io::ObjectInputStream* stream); (private) */
    bool remove(::java::lang::Object* o) override;
    bool removeAll(Collection* c) override;
    bool retainAll(Collection* c) override;
    int32_t size() override;
    ::java::lang::ObjectArray* toArray_() override;
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) override;
    ::java::lang::String* toString() override;

    // Generated

public: /* package */
    Collections_SetFromMap(Map* map);
protected:
    Collections_SetFromMap(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool addAll(Collection* c);

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Collections_UnmodifiableSet.hpp>

struct default_init_tag;

class java::util::Collections_UnmodifiableMap_UnmodifiableEntrySet
    : public Collections_UnmodifiableSet
{

public:
    typedef Collections_UnmodifiableSet super;

private:
    static constexpr int64_t serialVersionUID { int64_t(7854390611657943733LL) };

protected:
    void ctor(Set* s);

public:
    bool contains(::java::lang::Object* o) override;
    bool containsAll(Collection* coll) override;
    bool equals(::java::lang::Object* o) override;
    Iterator* iterator() override;
    ::java::lang::ObjectArray* toArray_() override;
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) override;

    // Generated

public: /* package */
    Collections_UnmodifiableMap_UnmodifiableEntrySet(Set* s);
protected:
    Collections_UnmodifiableMap_UnmodifiableEntrySet(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

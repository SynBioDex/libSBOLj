// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Collections_EmptyIterator.hpp>
#include <java/util/ListIterator.hpp>

struct default_init_tag;

class java::util::Collections_EmptyListIterator
    : public Collections_EmptyIterator
    , public virtual ListIterator
{

public:
    typedef Collections_EmptyIterator super;

private:
    static Collections_EmptyListIterator* EMPTY_ITERATOR_;

    /*void ctor(); (private) */

public:
    void add(::java::lang::Object* e) override;
    bool hasPrevious() override;
    int32_t nextIndex() override;
    ::java::lang::Object* previous() override;
    int32_t previousIndex() override;
    void set(::java::lang::Object* e) override;

    // Generated
    Collections_EmptyListIterator();
protected:
    Collections_EmptyListIterator(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool hasNext();
    ::java::lang::Object* next();
    void remove();

public: /* package */
    static Collections_EmptyListIterator*& EMPTY_ITERATOR();

private:
    virtual ::java::lang::Class* getClass0();
};

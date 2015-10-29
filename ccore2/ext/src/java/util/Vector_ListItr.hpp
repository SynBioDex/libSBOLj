// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Vector_Itr.hpp>
#include <java/util/ListIterator.hpp>

struct default_init_tag;

class java::util::Vector_ListItr final
    : public Vector_Itr
    , public ListIterator
{

public:
    typedef Vector_Itr super;

public: /* package */
    Vector* this$0 {  };

protected:
    void ctor(int32_t index);

public:
    void add(::java::lang::Object* e) override;
    bool hasPrevious() override;
    int32_t nextIndex() override;
    ::java::lang::Object* previous() override;
    int32_t previousIndex() override;
    void set(::java::lang::Object* e) override;

    // Generated

public: /* package */
    Vector_ListItr(Vector *Vector_this, int32_t index);
protected:
    Vector_ListItr(Vector *Vector_this, const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool hasNext();
    ::java::lang::Object* next();
    void remove();

private:
    virtual ::java::lang::Class* getClass0();
};

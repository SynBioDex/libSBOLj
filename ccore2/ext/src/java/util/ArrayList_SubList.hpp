// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractList.hpp>
#include <java/util/RandomAccess.hpp>

struct default_init_tag;

class java::util::ArrayList_SubList
    : public AbstractList
    , public virtual RandomAccess
{

public:
    typedef AbstractList super;

private:
    int32_t offset {  };
    AbstractList* parent {  };
    int32_t parentOffset {  };

public: /* package */
    int32_t size_ {  };
    ArrayList* this$0 {  };

protected:
    void ctor(AbstractList* parent, int32_t offset, int32_t fromIndex, int32_t toIndex);

public:
    void add(int32_t index, ::java::lang::Object* e) override;
    bool addAll(Collection* c) override;
    bool addAll(int32_t index, Collection* c) override;
    /*void checkForComodification(); (private) */
    ::java::lang::Object* get(int32_t index) override;
    Iterator* iterator() override;
    ListIterator* listIterator(int32_t index) override;
    /*::java::lang::String* outOfBoundsMsg(int32_t index); (private) */
    /*void rangeCheck(int32_t index); (private) */
    /*void rangeCheckForAdd(int32_t index); (private) */
    ::java::lang::Object* remove(int32_t index) override;

public: /* protected */
    void removeRange(int32_t fromIndex, int32_t toIndex) override;

public:
    ::java::lang::Object* set(int32_t index, ::java::lang::Object* e) override;
    int32_t size() override;
    List* subList(int32_t fromIndex, int32_t toIndex) override;

    // Generated

public: /* package */
    ArrayList_SubList(ArrayList *ArrayList_this, AbstractList* parent, int32_t offset, int32_t fromIndex, int32_t toIndex);
protected:
    ArrayList_SubList(ArrayList *ArrayList_this, const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    bool add(::java::lang::Object* e);
    ListIterator* listIterator();
    bool remove(::java::lang::Object* o);
    ArrayList *ArrayList_this;

private:
    virtual ::java::lang::Class* getClass0();
};

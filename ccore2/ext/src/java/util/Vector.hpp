// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractList.hpp>
#include <java/util/List.hpp>
#include <java/util/RandomAccess.hpp>
#include <java/lang/Cloneable.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Vector
    : public AbstractList
    , public virtual List
    , public virtual RandomAccess
    , public virtual ::java::lang::Cloneable
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractList super;

private:
    static constexpr int32_t MAX_ARRAY_SIZE { int32_t(2147483639) };

public: /* protected */
    int32_t capacityIncrement {  };
    int32_t elementCount {  };
    ::java::lang::ObjectArray* elementData_ {  };

private:
    static constexpr int64_t serialVersionUID { int64_t(-2767605614048989439LL) };

protected:
    void ctor();
    void ctor(int32_t initialCapacity);
    void ctor(Collection* c);
    void ctor(int32_t initialCapacity, int32_t capacityIncrement);

public:
    bool add(::java::lang::Object* e) override;
    void add(int32_t index, ::java::lang::Object* element) override;
    bool addAll(Collection* c) override;
    bool addAll(int32_t index, Collection* c) override;
    virtual void addElement(::java::lang::Object* obj);
    virtual int32_t capacity();
    void clear() override;
    ::java::lang::Object* clone() override;
    bool contains(::java::lang::Object* o) override;
    bool containsAll(Collection* c) override;
    virtual void copyInto(::java::lang::ObjectArray* anArray_);
    virtual ::java::lang::Object* elementAt(int32_t index);

public: /* package */
    virtual ::java::lang::Object* elementData(int32_t index);

public:
    virtual Enumeration* elements();
    virtual void ensureCapacity(int32_t minCapacity);
    /*void ensureCapacityHelper(int32_t minCapacity); (private) */
    bool equals(::java::lang::Object* o) override;
    virtual ::java::lang::Object* firstElement();
    ::java::lang::Object* get(int32_t index) override;
    /*void grow(int32_t minCapacity); (private) */
    int32_t hashCode() override;
    /*static int32_t hugeCapacity(int32_t minCapacity); (private) */
    int32_t indexOf(::java::lang::Object* o) override;
    virtual int32_t indexOf(::java::lang::Object* o, int32_t index);
    virtual void insertElementAt(::java::lang::Object* obj, int32_t index);
    bool isEmpty() override;
    Iterator* iterator() override;
    virtual ::java::lang::Object* lastElement();
    int32_t lastIndexOf(::java::lang::Object* o) override;
    virtual int32_t lastIndexOf(::java::lang::Object* o, int32_t index);
    ListIterator* listIterator() override;
    ListIterator* listIterator(int32_t index) override;
    bool remove(::java::lang::Object* o) override;
    ::java::lang::Object* remove(int32_t index) override;
    bool removeAll(Collection* c) override;
    virtual void removeAllElements();
    virtual bool removeElement(::java::lang::Object* obj);
    virtual void removeElementAt(int32_t index);

public: /* protected */
    void removeRange(int32_t fromIndex, int32_t toIndex) override;

public:
    bool retainAll(Collection* c) override;
    ::java::lang::Object* set(int32_t index, ::java::lang::Object* element) override;
    virtual void setElementAt(::java::lang::Object* obj, int32_t index);
    virtual void setSize(int32_t newSize);
    int32_t size() override;
    List* subList(int32_t fromIndex, int32_t toIndex) override;
    ::java::lang::ObjectArray* toArray_() override;
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) override;
    ::java::lang::String* toString() override;
    virtual void trimToSize();
    /*void writeObject(::java::io::ObjectOutputStream* s); (private) */

    // Generated
    Vector();
    Vector(int32_t initialCapacity);
    Vector(Collection* c);
    Vector(int32_t initialCapacity, int32_t capacityIncrement);
protected:
    Vector(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

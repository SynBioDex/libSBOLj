// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractQueue.hpp>
#include <java/util/Queue.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_AsLIFOQueue
    : public AbstractQueue
    , public virtual Queue
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractQueue super;

private:
    Deque* q {  };
    static constexpr int64_t serialVersionUID { int64_t(1802017725587941708LL) };

protected:
    void ctor(Deque* q);

public:
    bool add(::java::lang::Object* e) override;
    void clear() override;
    bool contains(::java::lang::Object* o) override;
    bool containsAll(Collection* c) override;
    ::java::lang::Object* element() override;
    bool isEmpty() override;
    Iterator* iterator() override;
    bool offer(::java::lang::Object* e) override;
    ::java::lang::Object* peek() override;
    ::java::lang::Object* poll() override;
    ::java::lang::Object* remove() override;
    bool remove(::java::lang::Object* o) override;
    bool removeAll(Collection* c) override;
    bool retainAll(Collection* c) override;
    int32_t size() override;
    ::java::lang::ObjectArray* toArray_() override;
    ::java::lang::ObjectArray* toArray_(::java::lang::ObjectArray* a) override;
    ::java::lang::String* toString() override;

    // Generated

public: /* package */
    Collections_AsLIFOQueue(Deque* q);
protected:
    Collections_AsLIFOQueue(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual bool addAll(Collection* c);
    virtual bool equals(::java::lang::Object* o);
    virtual int32_t hashCode();

private:
    virtual ::java::lang::Class* getClass0();
};

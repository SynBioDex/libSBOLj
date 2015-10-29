// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Collections_UnmodifiableMap.hpp>
#include <java/util/SortedMap.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_UnmodifiableSortedMap
    : public Collections_UnmodifiableMap
    , public virtual SortedMap
    , public virtual ::java::io::Serializable
{

public:
    typedef Collections_UnmodifiableMap super;

private:
    static constexpr int64_t serialVersionUID { int64_t(-8806743815996713206LL) };
    SortedMap* sm {  };

protected:
    void ctor(SortedMap* m);

public:
    Comparator* comparator() override;
    ::java::lang::Object* firstKey() override;
    SortedMap* headMap(::java::lang::Object* toKey) override;
    ::java::lang::Object* lastKey() override;
    SortedMap* subMap(::java::lang::Object* fromKey, ::java::lang::Object* toKey) override;
    SortedMap* tailMap(::java::lang::Object* fromKey) override;

    // Generated

public: /* package */
    Collections_UnmodifiableSortedMap(SortedMap* m);
protected:
    Collections_UnmodifiableSortedMap(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual void clear();
    virtual bool containsKey(::java::lang::Object* key);
    virtual bool containsValue(::java::lang::Object* value);
    virtual bool equals(::java::lang::Object* o);
    virtual ::java::lang::Object* get(::java::lang::Object* key);
    virtual int32_t hashCode();
    virtual bool isEmpty();
    virtual ::java::lang::Object* put(::java::lang::Object* key, ::java::lang::Object* value);
    virtual void putAll(Map* m);
    virtual ::java::lang::Object* remove(::java::lang::Object* key);
    virtual int32_t size();
    Set* entrySet();
    Set* keySet();
    Collection* values();

private:
    virtual ::java::lang::Class* getClass0();
};

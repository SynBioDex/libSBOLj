// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractMap.hpp>
#include <java/util/Map.hpp>
#include <java/lang/Cloneable.hpp>
#include <java/io/Serializable.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
typedef ::SubArray< ::java::util::Map_Entry, ::java::lang::ObjectArray > Map_EntryArray;
typedef ::SubArray< ::java::util::HashMap_Entry, ::java::lang::ObjectArray, Map_EntryArray > HashMap_EntryArray;
    } // util
} // java

struct default_init_tag;

class java::util::HashMap
    : public AbstractMap
    , public virtual Map
    , public virtual ::java::lang::Cloneable
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractMap super;

public: /* package */
    static constexpr int32_t ALTERNATIVE_HASHING_THRESHOLD_DEFAULT { int32_t(2147483647) };
    static constexpr int32_t DEFAULT_INITIAL_CAPACITY { int32_t(16) };
    static constexpr float DEFAULT_LOAD_FACTOR { 0.75f };

private:
    static HashMap_EntryArray* EMPTY_TABLE_;

public: /* package */
    static constexpr int32_t MAXIMUM_CAPACITY { int32_t(1073741824) };

private:
    Set* entrySet_ {  };

public: /* package */
    int32_t hashSeed {  };
    float loadFactor_ {  };
    int32_t modCount {  };

private:
    static constexpr int64_t serialVersionUID { int64_t(362498820763181265LL) };

public: /* package */
    int32_t size_ {  };
    HashMap_EntryArray* table {  };
    int32_t threshold {  };

protected:
    void ctor();
    void ctor(int32_t initialCapacity);
    void ctor(Map* m);
    void ctor(int32_t initialCapacity, float loadFactor);

public: /* package */
    virtual void addEntry(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, int32_t bucketIndex);
    virtual int32_t capacity();

public:
    void clear() override;
    ::java::lang::Object* clone() override;
    bool containsKey(::java::lang::Object* key) override;
    /*bool containsNullValue(); (private) */
    bool containsValue(::java::lang::Object* value) override;

public: /* package */
    virtual void createEntry(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, int32_t bucketIndex);

public:
    Set* entrySet() override;
    /*Set* entrySet0(); (private) */
    ::java::lang::Object* get(::java::lang::Object* key) override;

public: /* package */
    HashMap_Entry* getEntry(::java::lang::Object* key);
    /*::java::lang::Object* getForNullKey(); (private) */
    int32_t hash(::java::lang::Object* k);
    static int32_t indexFor(int32_t h, int32_t length);
    /*void inflateTable(int32_t toSize); (private) */
    virtual void init_();
    bool initHashSeedAsNeeded(int32_t capacity);

public:
    bool isEmpty() override;
    Set* keySet() override;

public: /* package */
    virtual float loadFactor();
    virtual Iterator* newEntryIterator();
    virtual Iterator* newKeyIterator();
    virtual Iterator* newValueIterator();

public:
    ::java::lang::Object* put(::java::lang::Object* key, ::java::lang::Object* value) override;
    void putAll(Map* m) override;
    /*void putAllForCreate(Map* m); (private) */
    /*void putForCreate(::java::lang::Object* key, ::java::lang::Object* value); (private) */
    /*::java::lang::Object* putForNullKey(::java::lang::Object* value); (private) */
    /*void readObject(::java::io::ObjectInputStream* s); (private) */
    ::java::lang::Object* remove(::java::lang::Object* key) override;

public: /* package */
    HashMap_Entry* removeEntryForKey(::java::lang::Object* key);
    HashMap_Entry* removeMapping(::java::lang::Object* o);
    virtual void resize(int32_t newCapacity);
    /*static int32_t roundUpToPowerOf2(int32_t number); (private) */

public:
    int32_t size() override;

public: /* package */
    virtual void transfer(HashMap_EntryArray* newTable, bool rehash);

public:
    Collection* values() override;
    /*void writeObject(::java::io::ObjectOutputStream* s); (private) */

    // Generated
    HashMap();
    HashMap(int32_t initialCapacity);
    HashMap(Map* m);
    HashMap(int32_t initialCapacity, float loadFactor);
protected:
    HashMap(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual bool equals(::java::lang::Object* o);
    virtual int32_t hashCode();

public: /* package */
    static HashMap_EntryArray*& EMPTY_TABLE();

private:
    virtual ::java::lang::Class* getClass0();
};

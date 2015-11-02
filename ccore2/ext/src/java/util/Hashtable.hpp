// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/Dictionary.hpp>
#include <java/util/Map.hpp>
#include <java/lang/Cloneable.hpp>
#include <java/io/Serializable.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
typedef ::SubArray< ::java::util::Map_Entry, ::java::lang::ObjectArray > Map_EntryArray;
typedef ::SubArray< ::java::util::Hashtable_Entry, ::java::lang::ObjectArray, Map_EntryArray > Hashtable_EntryArray;
    } // util
} // java

struct default_init_tag;

class java::util::Hashtable
    : public Dictionary
    , public virtual Map
    , public virtual ::java::lang::Cloneable
    , public virtual ::java::io::Serializable
{

public:
    typedef Dictionary super;

public: /* package */
    static constexpr int32_t ALTERNATIVE_HASHING_THRESHOLD_DEFAULT { int32_t(2147483647) };

private:
    static constexpr int32_t ENTRIES { int32_t(2) };
    static constexpr int32_t KEYS { int32_t(0) };
    static constexpr int32_t MAX_ARRAY_SIZE { int32_t(2147483639) };
    static constexpr int32_t VALUES { int32_t(1) };
    int32_t count {  };
    std::atomic< Set* > entrySet_ {  };

public: /* package */
    int32_t hashSeed {  };

private:
    std::atomic< Set* > keySet_ {  };
    float loadFactor {  };
    int32_t modCount {  };
    static constexpr int64_t serialVersionUID { int64_t(1421746759512286392LL) };
    Hashtable_EntryArray* table {  };
    int32_t threshold {  };
    std::atomic< Collection* > values_ {  };

protected:
    void ctor();
    void ctor(int32_t initialCapacity);
    void ctor(Map* t);
    void ctor(int32_t initialCapacity, float loadFactor);

public:
    void clear() override;
    ::java::lang::Object* clone() override;
    virtual bool contains(::java::lang::Object* value);
    bool containsKey(::java::lang::Object* key) override;
    bool containsValue(::java::lang::Object* value) override;
    Enumeration* elements() override;
    Set* entrySet() override;
    bool equals(::java::lang::Object* o) override;
    ::java::lang::Object* get(::java::lang::Object* key) override;
    /*Enumeration* getEnumeration(int32_t type); (private) */
    /*Iterator* getIterator(int32_t type); (private) */
    /*int32_t hash(::java::lang::Object* k); (private) */
    int32_t hashCode() override;

public: /* package */
    bool initHashSeedAsNeeded(int32_t capacity);

public:
    bool isEmpty() override;
    Set* keySet() override;
    Enumeration* keys() override;
    ::java::lang::Object* put(::java::lang::Object* key, ::java::lang::Object* value) override;
    void putAll(Map* t) override;
    /*void readObject(::java::io::ObjectInputStream* s); (private) */
    /*void reconstitutionPut(Hashtable_EntryArray* tab, ::java::lang::Object* key, ::java::lang::Object* value); (private) */

public: /* protected */
    virtual void rehash();

public:
    ::java::lang::Object* remove(::java::lang::Object* key) override;
    int32_t size() override;
    ::java::lang::String* toString() override;
    Collection* values() override;
    /*void writeObject(::java::io::ObjectOutputStream* s); (private) */

    // Generated
    Hashtable();
    Hashtable(int32_t initialCapacity);
    Hashtable(Map* t);
    Hashtable(int32_t initialCapacity, float loadFactor);
protected:
    Hashtable(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

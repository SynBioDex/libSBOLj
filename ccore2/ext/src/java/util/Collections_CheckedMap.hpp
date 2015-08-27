// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Map.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_CheckedMap
    : public virtual ::java::lang::Object
    , public virtual Map
    , public virtual ::java::io::Serializable
{

public:
    typedef ::java::lang::Object super;

private:
    Set* entrySet_ {  };

public: /* package */
    ::java::lang::Class* keyType {  };

private:
    Map* m {  };
    static constexpr int64_t serialVersionUID { int64_t(5742860141034234728LL) };

public: /* package */
    ::java::lang::Class* valueType {  };

protected:
    void ctor(Map* m, ::java::lang::Class* keyType, ::java::lang::Class* valueType);
    /*::java::lang::String* badKeyMsg(::java::lang::Object* key); (private) */
    /*::java::lang::String* badValueMsg(::java::lang::Object* value); (private) */

public:
    void clear() override;
    bool containsKey(::java::lang::Object* key) override;
    bool containsValue(::java::lang::Object* v) override;
    Set* entrySet() override;
    bool equals(::java::lang::Object* o) override;
    ::java::lang::Object* get(::java::lang::Object* key) override;
    int32_t hashCode() override;
    bool isEmpty() override;
    Set* keySet() override;
    ::java::lang::Object* put(::java::lang::Object* key, ::java::lang::Object* value) override;
    void putAll(Map* t) override;
    ::java::lang::Object* remove(::java::lang::Object* key) override;
    int32_t size() override;
    ::java::lang::String* toString() override;
    /*void typeCheck(::java::lang::Object* key, ::java::lang::Object* value); (private) */
    Collection* values() override;

    // Generated

public: /* package */
    Collections_CheckedMap(Map* m, ::java::lang::Class* keyType, ::java::lang::Class* valueType);
protected:
    Collections_CheckedMap(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Map.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_UnmodifiableMap
    : public virtual ::java::lang::Object
    , public virtual Map
    , public virtual ::java::io::Serializable
{

public:
    typedef ::java::lang::Object super;

private:
    Set* entrySet_ {  };
    Set* keySet_ {  };
    Map* m {  };
    static constexpr int64_t serialVersionUID { int64_t(-1034234728574286014LL) };
    Collection* values_ {  };

protected:
    void ctor(Map* m);

public:
    void clear() override;
    bool containsKey(::java::lang::Object* key) override;
    bool containsValue(::java::lang::Object* val) override;
    Set* entrySet() override;
    bool equals(::java::lang::Object* o) override;
    ::java::lang::Object* get(::java::lang::Object* key) override;
    int32_t hashCode() override;
    bool isEmpty() override;
    Set* keySet() override;
    ::java::lang::Object* put(::java::lang::Object* key, ::java::lang::Object* value) override;
    void putAll(Map* m) override;
    ::java::lang::Object* remove(::java::lang::Object* key) override;
    int32_t size() override;
    ::java::lang::String* toString() override;
    Collection* values() override;

    // Generated

public: /* package */
    Collections_UnmodifiableMap(Map* m);
protected:
    Collections_UnmodifiableMap(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

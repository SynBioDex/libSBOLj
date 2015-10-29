// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractMap.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_SingletonMap
    : public AbstractMap
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractMap super;

private:
    Set* entrySet_ {  };
    ::java::lang::Object* k {  };
    Set* keySet_ {  };
    static constexpr int64_t serialVersionUID { int64_t(-6979724477215052911LL) };
    ::java::lang::Object* v {  };
    Collection* values_ {  };

protected:
    void ctor(::java::lang::Object* key, ::java::lang::Object* value);

public:
    bool containsKey(::java::lang::Object* key) override;
    bool containsValue(::java::lang::Object* value) override;
    Set* entrySet() override;
    ::java::lang::Object* get(::java::lang::Object* key) override;
    bool isEmpty() override;
    Set* keySet() override;
    int32_t size() override;
    Collection* values() override;

    // Generated

public: /* package */
    Collections_SingletonMap(::java::lang::Object* key, ::java::lang::Object* value);
protected:
    Collections_SingletonMap(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

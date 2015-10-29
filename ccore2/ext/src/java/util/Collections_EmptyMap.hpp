// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractMap.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_EmptyMap
    : public AbstractMap
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractMap super;

private:
    static constexpr int64_t serialVersionUID { int64_t(6428348081105594320LL) };

    /*void ctor(); (private) */

public:
    bool containsKey(::java::lang::Object* key) override;
    bool containsValue(::java::lang::Object* value) override;
    Set* entrySet() override;
    bool equals(::java::lang::Object* o) override;
    ::java::lang::Object* get(::java::lang::Object* key) override;
    int32_t hashCode() override;
    bool isEmpty() override;
    Set* keySet() override;
    /*::java::lang::Object* readResolve(); (private) */
    int32_t size() override;
    Collection* values() override;

    // Generated
    Collections_EmptyMap();
protected:
    Collections_EmptyMap(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/AbstractList.hpp>
#include <java/util/RandomAccess.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::util::Collections_SingletonList
    : public AbstractList
    , public virtual RandomAccess
    , public virtual ::java::io::Serializable
{

public:
    typedef AbstractList super;

private:
    ::java::lang::Object* element {  };
    static constexpr int64_t serialVersionUID { int64_t(3093736618740652951LL) };

protected:
    void ctor(::java::lang::Object* obj);

public:
    bool contains(::java::lang::Object* obj) override;
    ::java::lang::Object* get(int32_t index) override;
    Iterator* iterator() override;
    int32_t size() override;

    // Generated

public: /* package */
    Collections_SingletonList(::java::lang::Object* obj);
protected:
    Collections_SingletonList(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

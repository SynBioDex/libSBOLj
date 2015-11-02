// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Map_Entry.hpp>

struct default_init_tag;

class java::util::HashMap_Entry
    : public virtual ::java::lang::Object
    , public virtual Map_Entry
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    int32_t hash {  };
    ::java::lang::Object* key {  };
    HashMap_Entry* next {  };
    ::java::lang::Object* value {  };

protected:
    void ctor(int32_t h, ::java::lang::Object* k, ::java::lang::Object* v, HashMap_Entry* n);

public:
    bool equals(::java::lang::Object* o) override;
    ::java::lang::Object* getKey() override;
    ::java::lang::Object* getValue() override;
    int32_t hashCode() override;

public: /* package */
    virtual void recordAccess(HashMap* m);
    virtual void recordRemoval(HashMap* m);

public:
    ::java::lang::Object* setValue(::java::lang::Object* newValue) override;
    ::java::lang::String* toString() override;

    // Generated

public: /* package */
    HashMap_Entry(int32_t h, ::java::lang::Object* k, ::java::lang::Object* v, HashMap_Entry* n);
protected:
    HashMap_Entry(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

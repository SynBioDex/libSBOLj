// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/util/Map_Entry.hpp>

struct default_init_tag;

class java::util::Hashtable_Entry
    : public virtual ::java::lang::Object
    , public virtual Map_Entry
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    int32_t hash {  };
    ::java::lang::Object* key {  };
    Hashtable_Entry* next {  };
    ::java::lang::Object* value {  };

protected:
    void ctor(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, Hashtable_Entry* next);

public: /* protected */
    ::java::lang::Object* clone() override;

public:
    bool equals(::java::lang::Object* o) override;
    ::java::lang::Object* getKey() override;
    ::java::lang::Object* getValue() override;
    int32_t hashCode() override;
    ::java::lang::Object* setValue(::java::lang::Object* value) override;
    ::java::lang::String* toString() override;

    // Generated

public: /* protected */
    Hashtable_Entry(int32_t hash, ::java::lang::Object* key, ::java::lang::Object* value, Hashtable_Entry* next);
protected:
    Hashtable_Entry(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

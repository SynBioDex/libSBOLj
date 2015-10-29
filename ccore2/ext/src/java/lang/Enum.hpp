// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/Comparable.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class java::lang::Enum
    : public virtual Object
    , public virtual Comparable
    , public virtual ::java::io::Serializable
{

public:
    typedef Object super;

private:
    String* name_ {  };
    int32_t ordinal_ {  };

protected:
    void ctor(String* name, int32_t ordinal);

public: /* protected */
    Object* clone() override;

public:
    int32_t compareTo(Enum* o);
    bool equals(Object* other) override;

public: /* protected */
    void finalize() override;

public:
    Class* getDeclaringClass();
    int32_t hashCode() override;
    String* name();
    int32_t ordinal();
    /*void readObject(::java::io::ObjectInputStream* in); (private) */
    /*void readObjectNoData(); (private) */
    String* toString() override;
    static Enum* valueOf(Class* enumType, String* name);

    // Generated

public: /* protected */
    Enum(String* name, int32_t ordinal);
protected:
    Enum(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual int32_t compareTo(Object* o) override;

private:
    virtual ::java::lang::Class* getClass0();
};

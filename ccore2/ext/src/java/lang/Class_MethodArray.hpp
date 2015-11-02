// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/reflect/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace lang
    {
        namespace reflect
        {
typedef ::SubArray< ::java::lang::reflect::AnnotatedElement, ::java::lang::ObjectArray > AnnotatedElementArray;
typedef ::SubArray< ::java::lang::reflect::AccessibleObject, ::java::lang::ObjectArray, AnnotatedElementArray > AccessibleObjectArray;
typedef ::SubArray< ::java::lang::reflect::GenericDeclaration, ::java::lang::ObjectArray > GenericDeclarationArray;
typedef ::SubArray< ::java::lang::reflect::Member, ::java::lang::ObjectArray > MemberArray;
typedef ::SubArray< ::java::lang::reflect::Method, AccessibleObjectArray, GenericDeclarationArray, MemberArray > MethodArray;
        } // reflect
    } // lang
} // java

struct default_init_tag;

class java::lang::Class_MethodArray
    : public virtual Object
{

public:
    typedef Object super;

private:
    int32_t length_ {  };
    ::java::lang::reflect::MethodArray* methods {  };

protected:
    void ctor();

public: /* package */
    virtual void add(::java::lang::reflect::Method* m);
    virtual void addAll(::java::lang::reflect::MethodArray* ma);
    virtual void addAll(Class_MethodArray* ma);
    virtual void addAllIfNotPresent(Class_MethodArray* newMethods);
    virtual void addIfNotPresent(::java::lang::reflect::Method* newMethod);
    virtual void compactAndTrim();
    virtual ::java::lang::reflect::Method* get(int32_t i);
    virtual ::java::lang::reflect::MethodArray* getArray_();
    virtual int32_t length();
    virtual void removeByNameAndSignature(::java::lang::reflect::Method* toRemove);

    // Generated
    Class_MethodArray();
protected:
    Class_MethodArray(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

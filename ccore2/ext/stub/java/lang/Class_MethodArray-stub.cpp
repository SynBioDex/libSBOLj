// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Class_MethodArray.hpp>

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

extern void unimplemented_(const char16_t* name);
java::lang::Class_MethodArray::Class_MethodArray(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::Class_MethodArray::Class_MethodArray()
    : Class_MethodArray(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}


void ::java::lang::Class_MethodArray::ctor()
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::lang::Class_MethodArray::ctor()");
}

void java::lang::Class_MethodArray::add(::java::lang::reflect::Method* m)
{ /* stub */
    unimplemented_(u"void java::lang::Class_MethodArray::add(::java::lang::reflect::Method* m)");
}

void java::lang::Class_MethodArray::addAll(::java::lang::reflect::MethodArray* ma)
{ /* stub */
    unimplemented_(u"void java::lang::Class_MethodArray::addAll(::java::lang::reflect::MethodArray* ma)");
}

void java::lang::Class_MethodArray::addAll(Class_MethodArray* ma)
{ /* stub */
    unimplemented_(u"void java::lang::Class_MethodArray::addAll(Class_MethodArray* ma)");
}

void java::lang::Class_MethodArray::addAllIfNotPresent(Class_MethodArray* newMethods)
{ /* stub */
    unimplemented_(u"void java::lang::Class_MethodArray::addAllIfNotPresent(Class_MethodArray* newMethods)");
}

void java::lang::Class_MethodArray::addIfNotPresent(::java::lang::reflect::Method* newMethod)
{ /* stub */
    unimplemented_(u"void java::lang::Class_MethodArray::addIfNotPresent(::java::lang::reflect::Method* newMethod)");
}

void java::lang::Class_MethodArray::compactAndTrim()
{ /* stub */
    unimplemented_(u"void java::lang::Class_MethodArray::compactAndTrim()");
}

java::lang::reflect::Method* java::lang::Class_MethodArray::get(int32_t i)
{ /* stub */
    unimplemented_(u"java::lang::reflect::Method* java::lang::Class_MethodArray::get(int32_t i)");
    return 0;
}

java::lang::reflect::MethodArray* java::lang::Class_MethodArray::getArray_()
{ /* stub */
    unimplemented_(u"java::lang::reflect::MethodArray* java::lang::Class_MethodArray::getArray_()");
    return 0;
}

int32_t java::lang::Class_MethodArray::length()
{ /* stub */
    unimplemented_(u"int32_t java::lang::Class_MethodArray::length()");
    return 0;
}

void java::lang::Class_MethodArray::removeByNameAndSignature(::java::lang::reflect::Method* toRemove)
{ /* stub */
    unimplemented_(u"void java::lang::Class_MethodArray::removeByNameAndSignature(::java::lang::reflect::Method* toRemove)");
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Class_MethodArray::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Class.MethodArray", 27);
    return c;
}

java::lang::Class* java::lang::Class_MethodArray::getClass0()
{
    return class_();
}


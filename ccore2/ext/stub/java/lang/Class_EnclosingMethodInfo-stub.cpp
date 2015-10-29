// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Class_EnclosingMethodInfo.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Class_EnclosingMethodInfo::Class_EnclosingMethodInfo(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

bool& java::lang::Class_EnclosingMethodInfo::$assertionsDisabled()
{
    clinit();
    return $assertionsDisabled_;
}
bool java::lang::Class_EnclosingMethodInfo::$assertionsDisabled_;

/* private: void ::java::lang::Class_EnclosingMethodInfo::ctor(ObjectArray* enclosingInfo) */
java::lang::String* java::lang::Class_EnclosingMethodInfo::getDescriptor()
{ /* stub */
return descriptor ; /* getter */
}

java::lang::Class* java::lang::Class_EnclosingMethodInfo::getEnclosingClass()
{ /* stub */
return enclosingClass ; /* getter */
}

java::lang::String* java::lang::Class_EnclosingMethodInfo::getName()
{ /* stub */
return name ; /* getter */
}

bool java::lang::Class_EnclosingMethodInfo::isConstructor()
{ /* stub */
    unimplemented_(u"bool java::lang::Class_EnclosingMethodInfo::isConstructor()");
    return 0;
}

bool java::lang::Class_EnclosingMethodInfo::isMethod()
{ /* stub */
    unimplemented_(u"bool java::lang::Class_EnclosingMethodInfo::isMethod()");
    return 0;
}

bool java::lang::Class_EnclosingMethodInfo::isPartial()
{ /* stub */
    unimplemented_(u"bool java::lang::Class_EnclosingMethodInfo::isPartial()");
    return 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Class_EnclosingMethodInfo::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Class.EnclosingMethodInfo", 35);
    return c;
}

java::lang::Class* java::lang::Class_EnclosingMethodInfo::getClass0()
{
    return class_();
}


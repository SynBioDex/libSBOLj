// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/String_CaseInsensitiveComparator.hpp>

#include <java/lang/String.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::String_CaseInsensitiveComparator::String_CaseInsensitiveComparator(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

constexpr int64_t java::lang::String_CaseInsensitiveComparator::serialVersionUID;

/* private: void ::java::lang::String_CaseInsensitiveComparator::ctor() */
int32_t java::lang::String_CaseInsensitiveComparator::compare(String* s1, String* s2)
{ /* stub */
    unimplemented_(u"int32_t java::lang::String_CaseInsensitiveComparator::compare(String* s1, String* s2)");
    return 0;
}

int32_t java::lang::String_CaseInsensitiveComparator::compare(Object* o1, Object* o2)
{ 
    return compare(dynamic_cast< String* >(o1), dynamic_cast< String* >(o2));
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::String_CaseInsensitiveComparator::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.String.CaseInsensitiveComparator", 42);
    return c;
}

bool java::lang::String_CaseInsensitiveComparator::equals(Object* obj)
{
    return Object::equals(obj);
}

java::lang::Class* java::lang::String_CaseInsensitiveComparator::getClass0()
{
    return class_();
}


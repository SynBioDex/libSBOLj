// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Integer_IntegerCache.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Integer_IntegerCache::Integer_IntegerCache(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::IntegerArray*& java::lang::Integer_IntegerCache::cache()
{
    clinit();
    return cache_;
}
java::lang::IntegerArray* java::lang::Integer_IntegerCache::cache_;
int32_t& java::lang::Integer_IntegerCache::high()
{
    clinit();
    return high_;
}
int32_t java::lang::Integer_IntegerCache::high_;
constexpr int32_t java::lang::Integer_IntegerCache::low;

/* private: void ::java::lang::Integer_IntegerCache::ctor() */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Integer_IntegerCache::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Integer.IntegerCache", 30);
    return c;
}

java::lang::Class* java::lang::Integer_IntegerCache::getClass0()
{
    return class_();
}


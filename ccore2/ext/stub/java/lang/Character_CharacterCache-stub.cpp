// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/lang/Character_CharacterCache.hpp>

extern void unimplemented_(const char16_t* name);
java::lang::Character_CharacterCache::Character_CharacterCache(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::lang::CharacterArray*& java::lang::Character_CharacterCache::cache()
{
    clinit();
    return cache_;
}
java::lang::CharacterArray* java::lang::Character_CharacterCache::cache_;

/* private: void ::java::lang::Character_CharacterCache::ctor() */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::lang::Character_CharacterCache::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.lang.Character.CharacterCache", 34);
    return c;
}

java::lang::Class* java::lang::Character_CharacterCache::getClass0()
{
    return class_();
}


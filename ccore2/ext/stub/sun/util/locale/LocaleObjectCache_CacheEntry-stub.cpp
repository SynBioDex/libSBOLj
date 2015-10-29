// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <sun/util/locale/LocaleObjectCache_CacheEntry.hpp>

extern void unimplemented_(const char16_t* name);
sun::util::locale::LocaleObjectCache_CacheEntry::LocaleObjectCache_CacheEntry(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

sun::util::locale::LocaleObjectCache_CacheEntry::LocaleObjectCache_CacheEntry(::java::lang::Object* arg0, ::java::lang::Object* arg1, ::java::lang::ref::ReferenceQueue* arg2)
    : LocaleObjectCache_CacheEntry(*static_cast< ::default_init_tag* >(0))
{
    ctor(arg0, arg1, arg2);
}


void ::sun::util::locale::LocaleObjectCache_CacheEntry::ctor(::java::lang::Object* arg0, ::java::lang::Object* arg1, ::java::lang::ref::ReferenceQueue* arg2)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::sun::util::locale::LocaleObjectCache_CacheEntry::ctor(::java::lang::Object* arg0, ::java::lang::Object* arg1, ::java::lang::ref::ReferenceQueue* arg2)");
}

java::lang::Object* sun::util::locale::LocaleObjectCache_CacheEntry::getKey()
{ /* stub */
return key ; /* getter */
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* sun::util::locale::LocaleObjectCache_CacheEntry::class_()
{
    static ::java::lang::Class* c = ::class_(u"sun.util.locale.LocaleObjectCache.CacheEntry", 44);
    return c;
}

java::lang::Class* sun::util::locale::LocaleObjectCache_CacheEntry::getClass0()
{
    return class_();
}


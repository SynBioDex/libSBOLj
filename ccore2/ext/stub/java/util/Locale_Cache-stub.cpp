// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Locale_Cache.hpp>

#include <java/util/Locale_LocaleKey.hpp>
#include <java/util/Locale.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Locale_Cache::Locale_Cache(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}


/* private: void ::java::util::Locale_Cache::ctor() */
java::util::Locale* java::util::Locale_Cache::createObject(Locale_LocaleKey* key)
{ /* stub */
    unimplemented_(u"java::util::Locale* java::util::Locale_Cache::createObject(Locale_LocaleKey* key)");
    return 0;
}

java::lang::Object* java::util::Locale_Cache::createObject(::java::lang::Object* arg0)
{ 
    return createObject(dynamic_cast< Locale_LocaleKey* >(arg0));
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Locale_Cache::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Locale.Cache", 22);
    return c;
}

java::lang::Class* java::util::Locale_Cache::getClass0()
{
    return class_();
}


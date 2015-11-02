// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Locale_LocaleNameGetter.hpp>

#include <java/lang/String.hpp>
#include <java/util/spi/LocaleNameProvider.hpp>

extern void unimplemented_(const char16_t* name);
java::util::Locale_LocaleNameGetter::Locale_LocaleNameGetter(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

bool& java::util::Locale_LocaleNameGetter::$assertionsDisabled()
{
    clinit();
    return $assertionsDisabled_;
}
bool java::util::Locale_LocaleNameGetter::$assertionsDisabled_;
java::util::Locale_LocaleNameGetter*& java::util::Locale_LocaleNameGetter::INSTANCE()
{
    clinit();
    return INSTANCE_;
}
java::util::Locale_LocaleNameGetter* java::util::Locale_LocaleNameGetter::INSTANCE_;

/* private: void ::java::util::Locale_LocaleNameGetter::ctor() */
java::lang::String* java::util::Locale_LocaleNameGetter::getObject(::java::util::spi::LocaleNameProvider* localeNameProvider, Locale* locale, ::java::lang::String* key, ::java::lang::ObjectArray* params)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale_LocaleNameGetter::getObject(::java::util::spi::LocaleNameProvider* localeNameProvider, Locale* locale, ::java::lang::String* key, ::java::lang::ObjectArray* params)");
    return 0;
}

java::lang::Object* java::util::Locale_LocaleNameGetter::getObject(::java::lang::Object* arg0, Locale* arg1, ::java::lang::String* arg2, ::java::lang::ObjectArray* arg3)
{ 
    return getObject(dynamic_cast< ::java::util::spi::LocaleNameProvider* >(arg0), arg1, arg2, arg3);
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Locale_LocaleNameGetter::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Locale.LocaleNameGetter", 33);
    return c;
}

java::lang::Class* java::util::Locale_LocaleNameGetter::getClass0()
{
    return class_();
}


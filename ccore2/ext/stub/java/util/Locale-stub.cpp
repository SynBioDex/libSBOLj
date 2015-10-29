// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/Locale.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::CharSequence, ObjectArray > CharSequenceArray;
typedef ::SubArray< ::java::lang::Cloneable, ObjectArray > CloneableArray;
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::String, ObjectArray, ::java::io::SerializableArray, ComparableArray, CharSequenceArray > StringArray;
    } // lang

    namespace util
    {
typedef ::SubArray< ::java::util::Locale, ::java::lang::ObjectArray, ::java::lang::CloneableArray, ::java::io::SerializableArray > LocaleArray;
    } // util
} // java

extern void unimplemented_(const char16_t* name);
java::util::Locale::Locale(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::Locale::Locale(::java::lang::String* language)
    : Locale(*static_cast< ::default_init_tag* >(0))
{
    ctor(language);
}

java::util::Locale::Locale(::java::lang::String* language, ::java::lang::String* country)
    : Locale(*static_cast< ::default_init_tag* >(0))
{
    ctor(language, country);
}

java::util::Locale::Locale(::java::lang::String* language, ::java::lang::String* country, ::java::lang::String* variant)
    : Locale(*static_cast< ::default_init_tag* >(0))
{
    ctor(language, country, variant);
}

bool& java::util::Locale::$assertionsDisabled()
{
    clinit();
    return $assertionsDisabled_;
}
bool java::util::Locale::$assertionsDisabled_;
java::util::Locale*& java::util::Locale::CANADA()
{
    clinit();
    return CANADA_;
}
java::util::Locale* java::util::Locale::CANADA_;
java::util::Locale*& java::util::Locale::CANADA_FRENCH()
{
    clinit();
    return CANADA_FRENCH_;
}
java::util::Locale* java::util::Locale::CANADA_FRENCH_;
java::util::Locale*& java::util::Locale::CHINA()
{
    clinit();
    return CHINA_;
}
java::util::Locale* java::util::Locale::CHINA_;
java::util::Locale*& java::util::Locale::CHINESE()
{
    clinit();
    return CHINESE_;
}
java::util::Locale* java::util::Locale::CHINESE_;
constexpr int32_t java::util::Locale::DISPLAY_COUNTRY;
constexpr int32_t java::util::Locale::DISPLAY_LANGUAGE;
constexpr int32_t java::util::Locale::DISPLAY_SCRIPT;
constexpr int32_t java::util::Locale::DISPLAY_VARIANT;
java::util::Locale*& java::util::Locale::ENGLISH()
{
    clinit();
    return ENGLISH_;
}
java::util::Locale* java::util::Locale::ENGLISH_;
java::util::Locale*& java::util::Locale::FRANCE()
{
    clinit();
    return FRANCE_;
}
java::util::Locale* java::util::Locale::FRANCE_;
java::util::Locale*& java::util::Locale::FRENCH()
{
    clinit();
    return FRENCH_;
}
java::util::Locale* java::util::Locale::FRENCH_;
java::util::Locale*& java::util::Locale::GERMAN()
{
    clinit();
    return GERMAN_;
}
java::util::Locale* java::util::Locale::GERMAN_;
java::util::Locale*& java::util::Locale::GERMANY()
{
    clinit();
    return GERMANY_;
}
java::util::Locale* java::util::Locale::GERMANY_;
java::util::Locale*& java::util::Locale::ITALIAN()
{
    clinit();
    return ITALIAN_;
}
java::util::Locale* java::util::Locale::ITALIAN_;
java::util::Locale*& java::util::Locale::ITALY()
{
    clinit();
    return ITALY_;
}
java::util::Locale* java::util::Locale::ITALY_;
java::util::Locale*& java::util::Locale::JAPAN()
{
    clinit();
    return JAPAN_;
}
java::util::Locale* java::util::Locale::JAPAN_;
java::util::Locale*& java::util::Locale::JAPANESE()
{
    clinit();
    return JAPANESE_;
}
java::util::Locale* java::util::Locale::JAPANESE_;
java::util::Locale*& java::util::Locale::KOREA()
{
    clinit();
    return KOREA_;
}
java::util::Locale* java::util::Locale::KOREA_;
java::util::Locale*& java::util::Locale::KOREAN()
{
    clinit();
    return KOREAN_;
}
java::util::Locale* java::util::Locale::KOREAN_;
java::util::Locale_Cache*& java::util::Locale::LOCALECACHE()
{
    clinit();
    return LOCALECACHE_;
}
java::util::Locale_Cache* java::util::Locale::LOCALECACHE_;
java::util::Locale*& java::util::Locale::PRC()
{
    clinit();
    return PRC_;
}
java::util::Locale* java::util::Locale::PRC_;
constexpr char16_t java::util::Locale::PRIVATE_USE_EXTENSION;
java::util::Locale*& java::util::Locale::ROOT()
{
    clinit();
    return ROOT_;
}
java::util::Locale* java::util::Locale::ROOT_;
java::util::Locale*& java::util::Locale::SIMPLIFIED_CHINESE()
{
    clinit();
    return SIMPLIFIED_CHINESE_;
}
java::util::Locale* java::util::Locale::SIMPLIFIED_CHINESE_;
java::util::Locale*& java::util::Locale::TAIWAN()
{
    clinit();
    return TAIWAN_;
}
java::util::Locale* java::util::Locale::TAIWAN_;
java::util::Locale*& java::util::Locale::TRADITIONAL_CHINESE()
{
    clinit();
    return TRADITIONAL_CHINESE_;
}
java::util::Locale* java::util::Locale::TRADITIONAL_CHINESE_;
java::util::Locale*& java::util::Locale::UK()
{
    clinit();
    return UK_;
}
java::util::Locale* java::util::Locale::UK_;
constexpr char16_t java::util::Locale::UNICODE_LOCALE_EXTENSION;
java::util::Locale*& java::util::Locale::US()
{
    clinit();
    return US_;
}
java::util::Locale* java::util::Locale::US_;
java::util::Locale*& java::util::Locale::defaultDisplayLocale()
{
    clinit();
    return defaultDisplayLocale_;
}
java::util::Locale* java::util::Locale::defaultDisplayLocale_;
java::util::Locale*& java::util::Locale::defaultFormatLocale()
{
    clinit();
    return defaultFormatLocale_;
}
java::util::Locale* java::util::Locale::defaultFormatLocale_;
java::util::Locale*& java::util::Locale::defaultLocale()
{
    clinit();
    return defaultLocale_;
}
java::util::Locale* java::util::Locale::defaultLocale_;
std::atomic< java::lang::StringArray* >& java::util::Locale::isoCountries()
{
    clinit();
    return isoCountries_;
}
std::atomic< java::lang::StringArray* > java::util::Locale::isoCountries_;
std::atomic< java::lang::StringArray* >& java::util::Locale::isoLanguages()
{
    clinit();
    return isoLanguages_;
}
std::atomic< java::lang::StringArray* > java::util::Locale::isoLanguages_;
java::io::ObjectStreamFieldArray*& java::util::Locale::serialPersistentFields()
{
    clinit();
    return serialPersistentFields_;
}
java::io::ObjectStreamFieldArray* java::util::Locale::serialPersistentFields_;
constexpr int64_t java::util::Locale::serialVersionUID;

void ::java::util::Locale::ctor(::java::lang::String* language)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Locale::ctor(::java::lang::String* language)");
}

/* private: void ::java::util::Locale::ctor(::sun::util::locale::BaseLocale* baseLocale, ::sun::util::locale::LocaleExtensions* extensions) */
void ::java::util::Locale::ctor(::java::lang::String* language, ::java::lang::String* country)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Locale::ctor(::java::lang::String* language, ::java::lang::String* country)");
}

void ::java::util::Locale::ctor(::java::lang::String* language, ::java::lang::String* country, ::java::lang::String* variant)
{ /* stub */
    /* super::ctor(); */
    unimplemented_(u"void ::java::util::Locale::ctor(::java::lang::String* language, ::java::lang::String* country, ::java::lang::String* variant)");
}

java::lang::Object* java::util::Locale::clone()
{ /* stub */
    unimplemented_(u"java::lang::Object* java::util::Locale::clone()");
    return 0;
}

/* private: java::lang::StringArray* java::util::Locale::composeList(::java::text::MessageFormat* format, ::java::lang::StringArray* list) */
/* private: java::lang::String* java::util::Locale::convertOldISOCodes(::java::lang::String* language) */
/* private: java::util::Locale* java::util::Locale::createConstant(::java::lang::String* lang, ::java::lang::String* country) */
bool java::util::Locale::equals(::java::lang::Object* obj)
{ /* stub */
    unimplemented_(u"bool java::util::Locale::equals(::java::lang::Object* obj)");
    return 0;
}

java::util::Locale* java::util::Locale::forLanguageTag(::java::lang::String* languageTag)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Locale* java::util::Locale::forLanguageTag(::java::lang::String* languageTag)");
    return 0;
}

/* private: java::lang::String* java::util::Locale::formatList(::java::lang::StringArray* stringList, ::java::lang::String* listPattern, ::java::lang::String* listCompositionPattern) */
java::util::LocaleArray* java::util::Locale::getAvailableLocales()
{ /* stub */
    clinit();
    unimplemented_(u"java::util::LocaleArray* java::util::Locale::getAvailableLocales()");
    return 0;
}

sun::util::locale::BaseLocale* java::util::Locale::getBaseLocale()
{ /* stub */
return baseLocale ; /* getter */
}

/* private: sun::util::locale::LocaleExtensions* java::util::Locale::getCompatibilityExtensions(::java::lang::String* language, ::java::lang::String* script, ::java::lang::String* country, ::java::lang::String* variant) */
java::lang::String* java::util::Locale::getCountry()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getCountry()");
    return 0;
}

java::util::Locale* java::util::Locale::getDefault()
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Locale* java::util::Locale::getDefault()");
    return 0;
}

java::util::Locale* java::util::Locale::getDefault(Locale_Category* category)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Locale* java::util::Locale::getDefault(Locale_Category* category)");
    return 0;
}

java::lang::String* java::util::Locale::getDisplayCountry()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayCountry()");
    return 0;
}

java::lang::String* java::util::Locale::getDisplayCountry(Locale* inLocale)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayCountry(Locale* inLocale)");
    return 0;
}

java::lang::String* java::util::Locale::getDisplayLanguage()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayLanguage()");
    return 0;
}

java::lang::String* java::util::Locale::getDisplayLanguage(Locale* inLocale)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayLanguage(Locale* inLocale)");
    return 0;
}

java::lang::String* java::util::Locale::getDisplayName()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayName()");
    return 0;
}

java::lang::String* java::util::Locale::getDisplayName(Locale* inLocale)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayName(Locale* inLocale)");
    return 0;
}

java::lang::String* java::util::Locale::getDisplayScript()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayScript()");
    return 0;
}

java::lang::String* java::util::Locale::getDisplayScript(Locale* inLocale)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayScript(Locale* inLocale)");
    return 0;
}

/* private: java::lang::String* java::util::Locale::getDisplayString(::java::lang::String* code, Locale* inLocale, int32_t type) */
java::lang::String* java::util::Locale::getDisplayVariant()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayVariant()");
    return 0;
}

java::lang::String* java::util::Locale::getDisplayVariant(Locale* inLocale)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getDisplayVariant(Locale* inLocale)");
    return 0;
}

/* private: java::lang::StringArray* java::util::Locale::getDisplayVariantArray_(::sun::util::resources::OpenListResourceBundle* bundle, Locale* inLocale) */
java::lang::String* java::util::Locale::getExtension(char16_t key)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getExtension(char16_t key)");
    return 0;
}

java::util::Set* java::util::Locale::getExtensionKeys()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::Locale::getExtensionKeys()");
    return 0;
}

/* private: java::lang::StringArray* java::util::Locale::getISO2Table(::java::lang::String* table) */
/* private: java::lang::String* java::util::Locale::getISO3Code(::java::lang::String* iso2Code, ::java::lang::String* table) */
java::lang::String* java::util::Locale::getISO3Country()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getISO3Country()");
    return 0;
}

java::lang::String* java::util::Locale::getISO3Language()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getISO3Language()");
    return 0;
}

java::lang::StringArray* java::util::Locale::getISOCountries()
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::StringArray* java::util::Locale::getISOCountries()");
    return 0;
}

java::lang::StringArray* java::util::Locale::getISOLanguages()
{ /* stub */
    clinit();
    unimplemented_(u"java::lang::StringArray* java::util::Locale::getISOLanguages()");
    return 0;
}

java::util::Locale* java::util::Locale::getInstance(::sun::util::locale::BaseLocale* baseloc, ::sun::util::locale::LocaleExtensions* extensions)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Locale* java::util::Locale::getInstance(::sun::util::locale::BaseLocale* baseloc, ::sun::util::locale::LocaleExtensions* extensions)");
    return 0;
}

java::util::Locale* java::util::Locale::getInstance(::java::lang::String* language, ::java::lang::String* country, ::java::lang::String* variant)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Locale* java::util::Locale::getInstance(::java::lang::String* language, ::java::lang::String* country, ::java::lang::String* variant)");
    return 0;
}

java::util::Locale* java::util::Locale::getInstance(::java::lang::String* language, ::java::lang::String* script, ::java::lang::String* country, ::java::lang::String* variant, ::sun::util::locale::LocaleExtensions* extensions)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::Locale* java::util::Locale::getInstance(::java::lang::String* language, ::java::lang::String* script, ::java::lang::String* country, ::java::lang::String* variant, ::sun::util::locale::LocaleExtensions* extensions)");
    return 0;
}

java::lang::String* java::util::Locale::getLanguage()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getLanguage()");
    return 0;
}

sun::util::locale::LocaleExtensions* java::util::Locale::getLocaleExtensions()
{ /* stub */
return localeExtensions ; /* getter */
}

java::lang::String* java::util::Locale::getScript()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getScript()");
    return 0;
}

java::util::Set* java::util::Locale::getUnicodeLocaleAttributes()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::Locale::getUnicodeLocaleAttributes()");
    return 0;
}

java::util::Set* java::util::Locale::getUnicodeLocaleKeys()
{ /* stub */
    unimplemented_(u"java::util::Set* java::util::Locale::getUnicodeLocaleKeys()");
    return 0;
}

java::lang::String* java::util::Locale::getUnicodeLocaleType(::java::lang::String* key)
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getUnicodeLocaleType(::java::lang::String* key)");
    return 0;
}

java::lang::String* java::util::Locale::getVariant()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::getVariant()");
    return 0;
}

int32_t java::util::Locale::hashCode()
{ /* stub */
    unimplemented_(u"int32_t java::util::Locale::hashCode()");
    return 0;
}

/* private: void java::util::Locale::initDefault() */
/* private: void java::util::Locale::initDefault(Locale_Category* category) */
/* private: void java::util::Locale::readObject(::java::io::ObjectInputStream* in) */
/* private: java::lang::Object* java::util::Locale::readResolve() */
void java::util::Locale::setDefault(Locale* newLocale)
{ /* stub */
    clinit();
    unimplemented_(u"void java::util::Locale::setDefault(Locale* newLocale)");
}

void java::util::Locale::setDefault(Locale_Category* category, Locale* newLocale)
{ /* stub */
    clinit();
    unimplemented_(u"void java::util::Locale::setDefault(Locale_Category* category, Locale* newLocale)");
}

java::lang::String* java::util::Locale::toLanguageTag()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::toLanguageTag()");
    return 0;
}

java::lang::String* java::util::Locale::toString()
{ /* stub */
    unimplemented_(u"java::lang::String* java::util::Locale::toString()");
    return 0;
}

/* private: void java::util::Locale::writeObject(::java::io::ObjectOutputStream* out) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::Locale::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.Locale", 16);
    return c;
}

java::lang::Class* java::util::Locale::getClass0()
{
    return class_();
}


// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/text/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <sun/util/locale/fwd-${project.parent.artifactId}-core2.hpp>
#include <sun/util/resources/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/Cloneable.hpp>
#include <java/io/Serializable.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace lang
    {
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
    } // lang

    namespace io
    {
typedef ::SubArray< ::java::io::ObjectStreamField, ::java::lang::ObjectArray, ::java::lang::ComparableArray > ObjectStreamFieldArray;
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::CharSequence, ObjectArray > CharSequenceArray;
typedef ::SubArray< ::java::lang::Cloneable, ObjectArray > CloneableArray;
typedef ::SubArray< ::java::lang::String, ObjectArray, ::java::io::SerializableArray, ComparableArray, CharSequenceArray > StringArray;
    } // lang

    namespace util
    {
typedef ::SubArray< ::java::util::Locale, ::java::lang::ObjectArray, ::java::lang::CloneableArray, ::java::io::SerializableArray > LocaleArray;
    } // util
} // java

struct default_init_tag;

class java::util::Locale final
    : public virtual ::java::lang::Object
    , public ::java::lang::Cloneable
    , public ::java::io::Serializable
{

public:
    typedef ::java::lang::Object super;

private:
    static bool $assertionsDisabled_;
    static Locale* CANADA_;
    static Locale* CANADA_FRENCH_;
    static Locale* CHINA_;
    static Locale* CHINESE_;
    static constexpr int32_t DISPLAY_COUNTRY { int32_t(1) };
    static constexpr int32_t DISPLAY_LANGUAGE { int32_t(0) };
    static constexpr int32_t DISPLAY_SCRIPT { int32_t(3) };
    static constexpr int32_t DISPLAY_VARIANT { int32_t(2) };
    static Locale* ENGLISH_;
    static Locale* FRANCE_;
    static Locale* FRENCH_;
    static Locale* GERMAN_;
    static Locale* GERMANY_;
    static Locale* ITALIAN_;
    static Locale* ITALY_;
    static Locale* JAPAN_;
    static Locale* JAPANESE_;
    static Locale* KOREA_;
    static Locale* KOREAN_;
    static Locale_Cache* LOCALECACHE_;
    static Locale* PRC_;

public:
    static constexpr char16_t PRIVATE_USE_EXTENSION { u'x' };

private:
    static Locale* ROOT_;
    static Locale* SIMPLIFIED_CHINESE_;
    static Locale* TAIWAN_;
    static Locale* TRADITIONAL_CHINESE_;
    static Locale* UK_;

public:
    static constexpr char16_t UNICODE_LOCALE_EXTENSION { u'u' };

private:
    static Locale* US_;
    ::sun::util::locale::BaseLocale* baseLocale {  };
    static Locale* defaultDisplayLocale_;
    static Locale* defaultFormatLocale_;
    static Locale* defaultLocale_;
    std::atomic< int32_t > hashCodeValue {  };
    static std::atomic< ::java::lang::StringArray* > isoCountries_;
    static std::atomic< ::java::lang::StringArray* > isoLanguages_;
    ::sun::util::locale::LocaleExtensions* localeExtensions {  };
    static ::java::io::ObjectStreamFieldArray* serialPersistentFields_;

public: /* package */
    static constexpr int64_t serialVersionUID { int64_t(9149081749638150636LL) };

protected:
    void ctor(::java::lang::String* language);
    /*void ctor(::sun::util::locale::BaseLocale* baseLocale, ::sun::util::locale::LocaleExtensions* extensions); (private) */
    void ctor(::java::lang::String* language, ::java::lang::String* country);
    void ctor(::java::lang::String* language, ::java::lang::String* country, ::java::lang::String* variant);

public:
    ::java::lang::Object* clone() override;
    /*static ::java::lang::StringArray* composeList(::java::text::MessageFormat* format, ::java::lang::StringArray* list); (private) */
    /*static ::java::lang::String* convertOldISOCodes(::java::lang::String* language); (private) */
    /*static Locale* createConstant(::java::lang::String* lang, ::java::lang::String* country); (private) */
    bool equals(::java::lang::Object* obj) override;
    static Locale* forLanguageTag(::java::lang::String* languageTag);
    /*static ::java::lang::String* formatList(::java::lang::StringArray* stringList, ::java::lang::String* listPattern, ::java::lang::String* listCompositionPattern); (private) */
    static LocaleArray* getAvailableLocales();

public: /* package */
    ::sun::util::locale::BaseLocale* getBaseLocale();
    /*static ::sun::util::locale::LocaleExtensions* getCompatibilityExtensions(::java::lang::String* language, ::java::lang::String* script, ::java::lang::String* country, ::java::lang::String* variant); (private) */

public:
    ::java::lang::String* getCountry();
    static Locale* getDefault();
    static Locale* getDefault(Locale_Category* category);
    ::java::lang::String* getDisplayCountry();
    ::java::lang::String* getDisplayCountry(Locale* inLocale);
    ::java::lang::String* getDisplayLanguage();
    ::java::lang::String* getDisplayLanguage(Locale* inLocale);
    ::java::lang::String* getDisplayName();
    ::java::lang::String* getDisplayName(Locale* inLocale);
    ::java::lang::String* getDisplayScript();
    ::java::lang::String* getDisplayScript(Locale* inLocale);
    /*::java::lang::String* getDisplayString(::java::lang::String* code, Locale* inLocale, int32_t type); (private) */
    ::java::lang::String* getDisplayVariant();
    ::java::lang::String* getDisplayVariant(Locale* inLocale);
    /*::java::lang::StringArray* getDisplayVariantArray_(::sun::util::resources::OpenListResourceBundle* bundle, Locale* inLocale); (private) */
    ::java::lang::String* getExtension(char16_t key);
    Set* getExtensionKeys();
    /*static ::java::lang::StringArray* getISO2Table(::java::lang::String* table); (private) */
    /*static ::java::lang::String* getISO3Code(::java::lang::String* iso2Code, ::java::lang::String* table); (private) */
    ::java::lang::String* getISO3Country();
    ::java::lang::String* getISO3Language();
    static ::java::lang::StringArray* getISOCountries();
    static ::java::lang::StringArray* getISOLanguages();

public: /* package */
    static Locale* getInstance(::sun::util::locale::BaseLocale* baseloc, ::sun::util::locale::LocaleExtensions* extensions);
    static Locale* getInstance(::java::lang::String* language, ::java::lang::String* country, ::java::lang::String* variant);
    static Locale* getInstance(::java::lang::String* language, ::java::lang::String* script, ::java::lang::String* country, ::java::lang::String* variant, ::sun::util::locale::LocaleExtensions* extensions);

public:
    ::java::lang::String* getLanguage();

public: /* package */
    ::sun::util::locale::LocaleExtensions* getLocaleExtensions();

public:
    ::java::lang::String* getScript();
    Set* getUnicodeLocaleAttributes();
    Set* getUnicodeLocaleKeys();
    ::java::lang::String* getUnicodeLocaleType(::java::lang::String* key);
    ::java::lang::String* getVariant();
    int32_t hashCode() override;
    /*static void initDefault(); (private) */
    /*static void initDefault(Locale_Category* category); (private) */
    /*void readObject(::java::io::ObjectInputStream* in); (private) */
    /*::java::lang::Object* readResolve(); (private) */
    static void setDefault(Locale* newLocale);
    static void setDefault(Locale_Category* category, Locale* newLocale);
    ::java::lang::String* toLanguageTag();
    ::java::lang::String* toString() override;
    /*void writeObject(::java::io::ObjectOutputStream* out); (private) */

    // Generated
    Locale(::java::lang::String* language);
    Locale(::java::lang::String* language, ::java::lang::String* country);
    Locale(::java::lang::String* language, ::java::lang::String* country, ::java::lang::String* variant);
protected:
    Locale(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

public: /* package */
    static bool& $assertionsDisabled();

public:
    static Locale*& CANADA();
    static Locale*& CANADA_FRENCH();
    static Locale*& CHINA();
    static Locale*& CHINESE();
    static Locale*& ENGLISH();
    static Locale*& FRANCE();
    static Locale*& FRENCH();
    static Locale*& GERMAN();
    static Locale*& GERMANY();
    static Locale*& ITALIAN();
    static Locale*& ITALY();
    static Locale*& JAPAN();
    static Locale*& JAPANESE();
    static Locale*& KOREA();
    static Locale*& KOREAN();

private:
    static Locale_Cache*& LOCALECACHE();

public:
    static Locale*& PRC();
    static Locale*& ROOT();
    static Locale*& SIMPLIFIED_CHINESE();
    static Locale*& TAIWAN();
    static Locale*& TRADITIONAL_CHINESE();
    static Locale*& UK();
    static Locale*& US();

private:
    static Locale*& defaultDisplayLocale();
    static Locale*& defaultFormatLocale();
    static Locale*& defaultLocale();
    static std::atomic< ::java::lang::StringArray* >& isoCountries();
    static std::atomic< ::java::lang::StringArray* >& isoLanguages();
    static ::java::io::ObjectStreamFieldArray*& serialPersistentFields();
    virtual ::java::lang::Class* getClass0();
};

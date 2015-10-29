// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <sun/util/locale/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class java::util::Locale_Builder final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    ::sun::util::locale::InternalLocaleBuilder* localeBuilder {  };

protected:
    void ctor();

public:
    Locale_Builder* addUnicodeLocaleAttribute(::java::lang::String* attribute);
    Locale* build();
    Locale_Builder* clear();
    Locale_Builder* clearExtensions();
    Locale_Builder* removeUnicodeLocaleAttribute(::java::lang::String* attribute);
    Locale_Builder* setExtension(char16_t key, ::java::lang::String* value);
    Locale_Builder* setLanguage(::java::lang::String* language);
    Locale_Builder* setLanguageTag(::java::lang::String* languageTag);
    Locale_Builder* setLocale(Locale* locale);
    Locale_Builder* setRegion(::java::lang::String* region);
    Locale_Builder* setScript(::java::lang::String* script);
    Locale_Builder* setUnicodeLocaleKeyword(::java::lang::String* key, ::java::lang::String* type);
    Locale_Builder* setVariant(::java::lang::String* variant);

    // Generated
    Locale_Builder();
protected:
    Locale_Builder(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

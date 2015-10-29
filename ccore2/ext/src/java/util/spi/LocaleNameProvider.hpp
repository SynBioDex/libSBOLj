// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/spi/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/spi/LocaleServiceProvider.hpp>

struct default_init_tag;

class java::util::spi::LocaleNameProvider
    : public LocaleServiceProvider
{

public:
    typedef LocaleServiceProvider super;

protected:
    void ctor();

public:
    virtual ::java::lang::String* getDisplayCountry(::java::lang::String* countryCode, ::java::util::Locale* locale) = 0;
    virtual ::java::lang::String* getDisplayLanguage(::java::lang::String* languageCode, ::java::util::Locale* locale) = 0;
    virtual ::java::lang::String* getDisplayScript(::java::lang::String* scriptCode, ::java::util::Locale* locale);
    virtual ::java::lang::String* getDisplayVariant(::java::lang::String* variant, ::java::util::Locale* locale) = 0;

    // Generated

public: /* protected */
    LocaleNameProvider();
protected:
    LocaleNameProvider(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

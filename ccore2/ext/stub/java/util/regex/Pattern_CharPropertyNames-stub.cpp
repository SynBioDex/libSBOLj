// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar
#include <java/util/regex/Pattern_CharPropertyNames.hpp>

extern void unimplemented_(const char16_t* name);
java::util::regex::Pattern_CharPropertyNames::Pattern_CharPropertyNames(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

java::util::HashMap*& java::util::regex::Pattern_CharPropertyNames::map()
{
    clinit();
    return map_;
}
java::util::HashMap* java::util::regex::Pattern_CharPropertyNames::map_;

/* private: void ::java::util::regex::Pattern_CharPropertyNames::ctor() */
java::util::regex::Pattern_CharProperty* java::util::regex::Pattern_CharPropertyNames::charPropertyFor(::java::lang::String* name)
{ /* stub */
    clinit();
    unimplemented_(u"java::util::regex::Pattern_CharProperty* java::util::regex::Pattern_CharPropertyNames::charPropertyFor(::java::lang::String* name)");
    return 0;
}

/* private: void java::util::regex::Pattern_CharPropertyNames::defCategory(::java::lang::String* name, int32_t typeMask) */
/* private: void java::util::regex::Pattern_CharPropertyNames::defClone(::java::lang::String* name, Pattern_CharPropertyNames_CloneableProperty* p) */
/* private: void java::util::regex::Pattern_CharPropertyNames::defCtype(::java::lang::String* name, int32_t ctype) */
/* private: void java::util::regex::Pattern_CharPropertyNames::defRange(::java::lang::String* name, int32_t lower, int32_t upper) */
extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* java::util::regex::Pattern_CharPropertyNames::class_()
{
    static ::java::lang::Class* c = ::class_(u"java.util.regex.Pattern.CharPropertyNames", 41);
    return c;
}

java::lang::Class* java::util::regex::Pattern_CharPropertyNames::getClass0()
{
    return class_();
}


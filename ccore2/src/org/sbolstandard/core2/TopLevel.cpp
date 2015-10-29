// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/TopLevel.java
#include <org/sbolstandard/core2/TopLevel.hpp>

#include <java/lang/String.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

org::sbolstandard::core2::TopLevel::TopLevel(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::TopLevel::TopLevel(::java::net::URI* identity) 
    : TopLevel(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity);
}

org::sbolstandard::core2::TopLevel::TopLevel(TopLevel* toplevel) 
    : TopLevel(*static_cast< ::default_init_tag* >(0))
{
    ctor(toplevel);
}

java::lang::String*& org::sbolstandard::core2::TopLevel::COLLECTION()
{
    clinit();
    return COLLECTION_;
}
java::lang::String* org::sbolstandard::core2::TopLevel::COLLECTION_;

java::lang::String*& org::sbolstandard::core2::TopLevel::MODULE_DEFINITION()
{
    clinit();
    return MODULE_DEFINITION_;
}
java::lang::String* org::sbolstandard::core2::TopLevel::MODULE_DEFINITION_;

java::lang::String*& org::sbolstandard::core2::TopLevel::MODEL()
{
    clinit();
    return MODEL_;
}
java::lang::String* org::sbolstandard::core2::TopLevel::MODEL_;

java::lang::String*& org::sbolstandard::core2::TopLevel::COMPONENT_DEFINITION()
{
    clinit();
    return COMPONENT_DEFINITION_;
}
java::lang::String* org::sbolstandard::core2::TopLevel::COMPONENT_DEFINITION_;

java::lang::String*& org::sbolstandard::core2::TopLevel::SEQUENCE()
{
    clinit();
    return SEQUENCE_;
}
java::lang::String* org::sbolstandard::core2::TopLevel::SEQUENCE_;

java::lang::String*& org::sbolstandard::core2::TopLevel::GENERIC_TOP_LEVEL()
{
    clinit();
    return GENERIC_TOP_LEVEL_;
}
java::lang::String* org::sbolstandard::core2::TopLevel::GENERIC_TOP_LEVEL_;

void org::sbolstandard::core2::TopLevel::ctor(::java::net::URI* identity)
{
    super::ctor(identity);
}

void org::sbolstandard::core2::TopLevel::ctor(TopLevel* toplevel)
{
    super::ctor(static_cast< Identified* >(toplevel));
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::TopLevel::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.TopLevel", 31);
    return c;
}

void org::sbolstandard::core2::TopLevel::clinit()
{
struct string_init_ {
    string_init_() {
    COLLECTION_ = u"col"_j;
    MODULE_DEFINITION_ = u"md"_j;
    MODEL_ = u"mod"_j;
    COMPONENT_DEFINITION_ = u"cd"_j;
    SEQUENCE_ = u"seq"_j;
    GENERIC_TOP_LEVEL_ = u"gen"_j;
    }
};

    static string_init_ string_init_instance;

    super::clinit();
}

java::lang::Class* org::sbolstandard::core2::TopLevel::getClass0()
{
    return class_();
}


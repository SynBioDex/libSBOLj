// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Version.java
#include <org/sbolstandard/core2/Version.hpp>

#include <java/lang/NullPointerException.hpp>
#include <java/lang/String.hpp>
#include <org/sbolstandard/core2/ComparableVersion.hpp>

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::Version::Version(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Version::Version()
    : Version(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

bool org::sbolstandard::core2::Version::isFirstVersionNewer(::java::lang::String* version1, ::java::lang::String* version2)
{
    clinit();
    if(version1 == nullptr)
        return true;

    if(version2 == nullptr)
        return false;

    auto v1 = new ComparableVersion(version1);
    auto v2 = new ComparableVersion(version2);
    return npc(v1)->compareTo(v2) > 0;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Version::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Version", 30);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Version::getClass0()
{
    return class_();
}


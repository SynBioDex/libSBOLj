// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/GenericLocation.java
#include <org/sbolstandard/core2/GenericLocation.hpp>

#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <org/sbolstandard/core2/Location.hpp>
#include <org/sbolstandard/core2/OrientationType.hpp>

template<typename T, typename U>
static T java_cast(U* u)
{
    if(!u) return static_cast<T>(nullptr);
    auto t = dynamic_cast<T>(u);
    if(!t) throw new ::java::lang::ClassCastException();
    return t;
}

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::GenericLocation::GenericLocation(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::GenericLocation::GenericLocation(::java::net::URI* identity) 
    : GenericLocation(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity);
}

org::sbolstandard::core2::GenericLocation::GenericLocation(GenericLocation* genericLocation) 
    : GenericLocation(*static_cast< ::default_init_tag* >(0))
{
    ctor(genericLocation);
}

void org::sbolstandard::core2::GenericLocation::ctor(::java::net::URI* identity)
{
    super::ctor(identity);
}

void org::sbolstandard::core2::GenericLocation::ctor(GenericLocation* genericLocation)
{
    super::ctor(static_cast< Location* >(genericLocation));
}

int32_t org::sbolstandard::core2::GenericLocation::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((orientation == nullptr) ? int32_t(0) : npc(orientation)->hashCode());
    return result;
}

bool org::sbolstandard::core2::GenericLocation::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< GenericLocation* >(obj);
    return orientation == npc(other)->orientation;
}

org::sbolstandard::core2::GenericLocation* org::sbolstandard::core2::GenericLocation::deepCopy()
{
    return new GenericLocation(this);
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::GenericLocation::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.GenericLocation", 38);
    return c;
}

java::lang::Class* org::sbolstandard::core2::GenericLocation::getClass0()
{
    return class_();
}


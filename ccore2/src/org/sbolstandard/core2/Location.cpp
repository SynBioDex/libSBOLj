// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Location.java
#include <org/sbolstandard/core2/Location.hpp>

#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/net/URI.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/OrientationType.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::Location::Location(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Location::Location(::java::net::URI* identity) 
    : Location(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity);
}

org::sbolstandard::core2::Location::Location(Location* location) 
    : Location(*static_cast< ::default_init_tag* >(0))
{
    ctor(location);
}

void org::sbolstandard::core2::Location::ctor(::java::net::URI* identity)
{
    super::ctor(identity);
}

void org::sbolstandard::core2::Location::ctor(Location* location)
{
    super::ctor(static_cast< Identified* >(location));
    this->setOrientation(npc(location)->getOrientation());
}

bool org::sbolstandard::core2::Location::isSetOrientation()
{
    return orientation != nullptr;
}

org::sbolstandard::core2::OrientationType* org::sbolstandard::core2::Location::getOrientation()
{
    return this->orientation;
}

void org::sbolstandard::core2::Location::setOrientation(OrientationType* orientation)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    this->orientation = orientation;
}

void org::sbolstandard::core2::Location::unsetOrientation()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    orientation = nullptr;
}

void org::sbolstandard::core2::Location::updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    if(!npc(this->getIdentity())->equals(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(URIprefix, displayId, version)))) {
        this->setWasDerivedFrom(this->getIdentity());
    }
    this->setIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, version));
    this->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    this->setDisplayId(displayId);
    this->setVersion(version);
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Location::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Location", 31);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Location::getClass0()
{
    return class_();
}


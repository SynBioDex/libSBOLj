// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/GenericTopLevel.java
#include <org/sbolstandard/core2/GenericTopLevel.hpp>

#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <javax/xml/namespace_/QName.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/SBOLValidationException.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>
#include <ObjectArray.hpp>
#include <SubArray.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace org
{
    namespace sbolstandard
    {
        namespace core2
        {
typedef ::SubArray< ::org::sbolstandard::core2::Identified, ::java::lang::ObjectArray > IdentifiedArray;
        } // core2
    } // sbolstandard
} // org

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

org::sbolstandard::core2::GenericTopLevel::GenericTopLevel(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::GenericTopLevel::GenericTopLevel(::java::net::URI* identity, ::javax::xml::namespace_::QName* rdfType) 
    : GenericTopLevel(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,rdfType);
}

org::sbolstandard::core2::GenericTopLevel::GenericTopLevel(GenericTopLevel* genericTopLevel) 
    : GenericTopLevel(*static_cast< ::default_init_tag* >(0))
{
    ctor(genericTopLevel);
}

void org::sbolstandard::core2::GenericTopLevel::ctor(::java::net::URI* identity, ::javax::xml::namespace_::QName* rdfType)
{
    super::ctor(identity);
    this->rdfType = rdfType;
    if(npc(npc(npc(rdfType)->getPrefix())->toString())->equals(static_cast< ::java::lang::Object* >(u"sbol"_j))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(npc(rdfType)->getLocalPart())->append(u" is not an SBOL object, so it cannot be in the SBOL namespace."_j)->toString(), new IdentifiedArray());
    }
}

void org::sbolstandard::core2::GenericTopLevel::ctor(GenericTopLevel* genericTopLevel)
{
    super::ctor(static_cast< TopLevel* >(genericTopLevel));
    this->setRDFType(npc(genericTopLevel)->getRDFType());
}

javax::xml::namespace_::QName* org::sbolstandard::core2::GenericTopLevel::getRDFType()
{
    return rdfType;
}

void org::sbolstandard::core2::GenericTopLevel::setRDFType(::javax::xml::namespace_::QName* rdfType)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(rdfType == nullptr) {
        throw new ::java::lang::IllegalArgumentException(u"RDF type is a required field."_j);
    }
    this->rdfType = rdfType;
}

int32_t org::sbolstandard::core2::GenericTopLevel::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((rdfType == nullptr) ? int32_t(0) : npc(rdfType)->hashCode());
    return result;
}

bool org::sbolstandard::core2::GenericTopLevel::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< GenericTopLevel* >(obj);
    if(rdfType == nullptr) {
        if(npc(other)->rdfType != nullptr)
            return false;

    } else if(!npc(rdfType)->equals(static_cast< ::java::lang::Object* >(npc(other)->rdfType)))
        return false;

    return true;
}

org::sbolstandard::core2::GenericTopLevel* org::sbolstandard::core2::GenericTopLevel::deepCopy()
{
    return new GenericTopLevel(this);
}

org::sbolstandard::core2::GenericTopLevel* org::sbolstandard::core2::GenericTopLevel::copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    auto cloned = this->deepCopy();
    npc(cloned)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    npc(cloned)->setDisplayId(displayId);
    npc(cloned)->setVersion(version);
    auto newIdentity = URIcompliance::createCompliantURI(URIprefix, displayId, version);
    if(!npc(this->getIdentity())->equals(static_cast< ::java::lang::Object* >(newIdentity))) {
        npc(cloned)->setWasDerivedFrom(this->getIdentity());
    } else {
        npc(cloned)->setWasDerivedFrom(this->getWasDerivedFrom());
    }
    npc(cloned)->setIdentity(newIdentity);
    return cloned;
}

bool org::sbolstandard::core2::GenericTopLevel::checkDescendantsURIcompliance()
{
    return URIcompliance::isTopLevelURIformCompliant(this->getIdentity());
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::GenericTopLevel::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.GenericTopLevel", 38);
    return c;
}

java::lang::Class* org::sbolstandard::core2::GenericTopLevel::getClass0()
{
    return class_();
}


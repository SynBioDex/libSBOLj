// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Participation.java
#include <org/sbolstandard/core2/Participation.hpp>

#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <java/util/HashSet.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/Set.hpp>
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/FunctionalComponent.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/ModuleDefinition.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>

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

org::sbolstandard::core2::Participation::Participation(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Participation::Participation(::java::net::URI* identity, ::java::net::URI* participant) 
    : Participation(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,participant);
}

org::sbolstandard::core2::Participation::Participation(Participation* participation) 
    : Participation(*static_cast< ::default_init_tag* >(0))
{
    ctor(participation);
}

void org::sbolstandard::core2::Participation::init()
{
    moduleDefinition = nullptr;
}

void org::sbolstandard::core2::Participation::ctor(::java::net::URI* identity, ::java::net::URI* participant)
{
    super::ctor(identity);
    init();
    roles = new ::java::util::HashSet();
    setParticipant(participant);
}

void org::sbolstandard::core2::Participation::ctor(Participation* participation)
{
    super::ctor(static_cast< Identified* >(participation));
    init();
    roles = new ::java::util::HashSet();
    for (auto _i = npc(npc(participation)->getRoles())->iterator(); _i->hasNext(); ) {
        ::java::net::URI* role = java_cast< ::java::net::URI* >(_i->next());
        {
            npc(roles)->add(static_cast< ::java::lang::Object* >(role));
        }
    }
    this->setRoles(roles);
    this->setParticipant(npc(participation)->getParticipantURI());
}

java::net::URI* org::sbolstandard::core2::Participation::getParticipantURI()
{
    return participant;
}

org::sbolstandard::core2::FunctionalComponent* org::sbolstandard::core2::Participation::getParticipant()
{
    if(moduleDefinition == nullptr)
        return nullptr;

    return npc(moduleDefinition)->getFunctionalComponent(participant);
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::Participation::getParticipantDefinition()
{
    if(moduleDefinition != nullptr) {
        return npc(npc(moduleDefinition)->getFunctionalComponent(participant))->getDefinition();
    }
    return nullptr;
}

void org::sbolstandard::core2::Participation::setParticipant(::java::net::URI* participant)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(participant == nullptr) {
        throw new ::java::lang::IllegalArgumentException(u"Participation is required to have a participant."_j);
    }
    if(moduleDefinition != nullptr && npc(moduleDefinition)->getFunctionalComponent(participant) == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Functional component '"_j)->append(static_cast< ::java::lang::Object* >(participant))
            ->append(u"' does not exist."_j)->toString());
    }
    this->participant = participant;
}

bool org::sbolstandard::core2::Participation::addRole(::java::net::URI* roleURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(roles)->add(static_cast< ::java::lang::Object* >(roleURI));
}

bool org::sbolstandard::core2::Participation::removeRole(::java::net::URI* roleURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(roles)->remove(static_cast< ::java::lang::Object* >(roleURI));
}

void org::sbolstandard::core2::Participation::setRoles(::java::util::Set* roles)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    clearRoles();
    if(roles == nullptr)
        return;

    for (auto _i = npc(roles)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* role = java_cast< ::java::net::URI* >(_i->next());
        {
            addRole(role);
        }
    }
}

java::util::Set* org::sbolstandard::core2::Participation::getRoles()
{
    return roles;
}

bool org::sbolstandard::core2::Participation::containsRole(::java::net::URI* roleURI)
{
    return npc(roles)->contains(static_cast< ::java::lang::Object* >(roleURI));
}

void org::sbolstandard::core2::Participation::clearRoles()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    npc(roles)->clear();
}

org::sbolstandard::core2::Participation* org::sbolstandard::core2::Participation::deepCopy()
{
    return new Participation(this);
}

void org::sbolstandard::core2::Participation::updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    if(!npc(this->getIdentity())->equals(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(URIprefix, displayId, version)))) {
        this->setWasDerivedFrom(this->getIdentity());
    }
    this->setIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, version));
    this->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    this->setDisplayId(displayId);
    this->setVersion(version);
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::Participation::getModuleDefinition()
{
    return moduleDefinition;
}

void org::sbolstandard::core2::Participation::setModuleDefinition(ModuleDefinition* moduleDefinition)
{
    this->moduleDefinition = moduleDefinition;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Participation::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Participation", 36);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Participation::getClass0()
{
    return class_();
}


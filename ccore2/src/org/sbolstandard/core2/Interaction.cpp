// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Interaction.java
#include <org/sbolstandard/core2/Interaction.hpp>

#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/IllegalStateException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <java/util/ArrayList.hpp>
#include <java/util/Collection.hpp>
#include <java/util/HashMap.hpp>
#include <java/util/HashSet.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/List.hpp>
#include <java/util/Map.hpp>
#include <java/util/Set.hpp>
#include <org/sbolstandard/core2/AccessType.hpp>
#include <org/sbolstandard/core2/DirectionType.hpp>
#include <org/sbolstandard/core2/FunctionalComponent.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/ModuleDefinition.hpp>
#include <org/sbolstandard/core2/Participation.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>
#include <ObjectArray.hpp>
#include <SubArray.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
typedef ::SubArray< ::java::util::Map, ::java::lang::ObjectArray > MapArray;
    } // util
} // java

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

org::sbolstandard::core2::Interaction::Interaction(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Interaction::Interaction(::java::net::URI* identity, ::java::util::Set* type) 
    : Interaction(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,type);
}

org::sbolstandard::core2::Interaction::Interaction(Interaction* interaction) 
    : Interaction(*static_cast< ::default_init_tag* >(0))
{
    ctor(interaction);
}

void org::sbolstandard::core2::Interaction::init()
{
    moduleDefinition = nullptr;
}

void org::sbolstandard::core2::Interaction::ctor(::java::net::URI* identity, ::java::util::Set* type)
{
    super::ctor(identity);
    init();
    this->types = new ::java::util::HashSet();
    this->participations = new ::java::util::HashMap();
    setTypes(type);
}

void org::sbolstandard::core2::Interaction::ctor(Interaction* interaction)
{
    super::ctor(static_cast< Identified* >(interaction));
    init();
    this->types = new ::java::util::HashSet();
    this->participations = new ::java::util::HashMap();
    ::java::util::Set* type = new ::java::util::HashSet();
    for (auto _i = npc(npc(interaction)->getTypes())->iterator(); _i->hasNext(); ) {
        ::java::net::URI* typeElement = java_cast< ::java::net::URI* >(_i->next());
        {
            npc(type)->add(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(typeElement)->toString())));
        }
    }
    this->setTypes(type);
    ::java::util::List* participations = new ::java::util::ArrayList();
    for (auto _i = npc(npc(interaction)->getParticipations())->iterator(); _i->hasNext(); ) {
        Participation* participation = java_cast< Participation* >(_i->next());
        {
            npc(participations)->add(static_cast< ::java::lang::Object* >(npc(participation)->deepCopy()));
        }
    }
    this->setParticipations(participations);
}

bool org::sbolstandard::core2::Interaction::addType(::java::net::URI* typeURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(types)->add(static_cast< ::java::lang::Object* >(typeURI));
}

bool org::sbolstandard::core2::Interaction::removeType(::java::net::URI* typeURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(npc(types)->size() == 1 && npc(types)->contains(static_cast< ::java::lang::Object* >(typeURI))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Interaction "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have at least one type."_j)->toString());
    }
    return npc(types)->remove(static_cast< ::java::lang::Object* >(typeURI));
}

void org::sbolstandard::core2::Interaction::setTypes(::java::util::Set* types)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(types == nullptr || npc(types)->size() == 0) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Interaction "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have at least one type."_j)->toString());
    }
    clearTypes();
    for (auto _i = npc(types)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* type = java_cast< ::java::net::URI* >(_i->next());
        {
            addType(type);
        }
    }
}

java::util::Set* org::sbolstandard::core2::Interaction::getTypes()
{
    return types;
}

bool org::sbolstandard::core2::Interaction::containsType(::java::net::URI* typeURI)
{
    return npc(types)->contains(static_cast< ::java::lang::Object* >(typeURI));
}

void org::sbolstandard::core2::Interaction::clearTypes()
{
    npc(types)->clear();
}

org::sbolstandard::core2::Participation* org::sbolstandard::core2::Interaction::createParticipation(::java::net::URI* identity, ::java::net::URI* participant)
{
    auto participation = new Participation(identity, participant);
    addParticipation(participation);
    return participation;
}

org::sbolstandard::core2::Participation* org::sbolstandard::core2::Interaction::createParticipation(::java::lang::String* displayId, ::java::lang::String* participantId)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto participantURI = URIcompliance::createCompliantURI(npc(npc(moduleDefinition)->getPersistentIdentity())->toString(), participantId, npc(moduleDefinition)->getVersion());
    if(sbolDocument != nullptr && npc(sbolDocument)->isCreateDefaults() && moduleDefinition != nullptr && npc(moduleDefinition)->getFunctionalComponent(participantURI) == nullptr) {
        npc(moduleDefinition)->createFunctionalComponent(participantId, AccessType::PUBLIC, participantId, u""_j, DirectionType::INOUT);
    }
    return createParticipation(displayId, participantURI);
}

org::sbolstandard::core2::Participation* org::sbolstandard::core2::Interaction::createParticipation(::java::lang::String* displayId, ::java::net::URI* participant)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(moduleDefinition != nullptr) {
        if(npc(moduleDefinition)->getFunctionalComponent(participant) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Functional component '"_j)->append(static_cast< ::java::lang::Object* >(participant))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    auto parentPersistentIdStr = npc(this->getPersistentIdentity())->toString();
    auto version = this->getVersion();
    if(parentPersistentIdStr == nullptr) {
        throw new ::java::lang::IllegalStateException(::java::lang::StringBuilder().append(u"Can not create a child on a parent that has the non-standard compliant identity "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))->toString());
    }
    auto p = createParticipation(URIcompliance::createCompliantURI(parentPersistentIdStr, displayId, version), participant);
    npc(p)->setPersistentIdentity(URIcompliance::createCompliantURI(parentPersistentIdStr, displayId, u""_j));
    npc(p)->setDisplayId(displayId);
    npc(p)->setVersion(version);
    return p;
}

void org::sbolstandard::core2::Interaction::addParticipation(Participation* participation)
{
    addChildSafely(participation, participations, u"participation"_j, new ::java::util::MapArray());
    npc(participation)->setSBOLDocument(this->sbolDocument);
    npc(participation)->setModuleDefinition(moduleDefinition);
}

bool org::sbolstandard::core2::Interaction::removeParticipation(Participation* participation)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return removeChildSafely(participation, participations);
}

org::sbolstandard::core2::Participation* org::sbolstandard::core2::Interaction::getParticipation(::java::lang::String* displayId)
{
    return java_cast< Participation* >(npc(participations)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::Participation* org::sbolstandard::core2::Interaction::getParticipation(::java::net::URI* participationURI)
{
    return java_cast< Participation* >(npc(participations)->get(static_cast< ::java::lang::Object* >(participationURI)));
}

java::util::Set* org::sbolstandard::core2::Interaction::getParticipations()
{
    return new ::java::util::HashSet(static_cast< ::java::util::Collection* >(npc(participations)->values()));
}

void org::sbolstandard::core2::Interaction::clearParticipations()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(participations)->values())->toArray_();
    for(auto participation : *npc(valueSetArray_)) {
        removeParticipation(java_cast< Participation* >(participation));
    }
}

void org::sbolstandard::core2::Interaction::setParticipations(::java::util::List* participations)
{
    clearParticipations();
    for (auto _i = npc(participations)->iterator(); _i->hasNext(); ) {
        Participation* participation = java_cast< Participation* >(_i->next());
        {
            addParticipation(participation);
        }
    }
}

int32_t org::sbolstandard::core2::Interaction::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((participations == nullptr) ? int32_t(0) : npc(participations)->hashCode());
    result = prime * result + ((types == nullptr) ? int32_t(0) : npc(types)->hashCode());
    return result;
}

bool org::sbolstandard::core2::Interaction::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< Interaction* >(obj);
    if(participations == nullptr) {
        if(npc(other)->participations != nullptr)
            return false;

    } else if(!npc(participations)->equals(static_cast< ::java::lang::Object* >(npc(other)->participations)))
        return false;

    if(types == nullptr) {
        if(npc(other)->types != nullptr)
            return false;

    } else if(!npc(types)->equals(static_cast< ::java::lang::Object* >(npc(other)->types)))
        return false;

    return true;
}

org::sbolstandard::core2::Interaction* org::sbolstandard::core2::Interaction::deepCopy()
{
    return new Interaction(this);
}

void org::sbolstandard::core2::Interaction::updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    if(!npc(this->getIdentity())->equals(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(URIprefix, displayId, version)))) {
        this->setWasDerivedFrom(this->getIdentity());
    }
    this->setIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, version));
    this->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    this->setDisplayId(displayId);
    this->setVersion(version);
    auto count = int32_t(0);
    for (auto _i = npc(this->getParticipations())->iterator(); _i->hasNext(); ) {
        Participation* participation = java_cast< Participation* >(_i->next());
        {
            if(!npc(participation)->isSetDisplayId())
                npc(participation)->setDisplayId(::java::lang::StringBuilder().append(u"participation"_j)->append(++count)->toString());

            npc(participation)->updateCompliantURI(npc(this->getPersistentIdentity())->toString(), npc(participation)->getDisplayId(), version);
            this->removeChildSafely(participation, java_cast< ::java::util::HashMap* >(this->participations));
            this->addParticipation(participation);
            auto participantId = URIcompliance::extractDisplayId(npc(participation)->getParticipantURI());
            npc(participation)->setParticipant(URIcompliance::createCompliantURI(URIprefix, participantId, version));
        }
    }
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::Interaction::getModuleDefinition()
{
    return moduleDefinition;
}

void org::sbolstandard::core2::Interaction::setModuleDefinition(ModuleDefinition* moduleDefinition)
{
    this->moduleDefinition = moduleDefinition;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Interaction::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Interaction", 34);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Interaction::getClass0()
{
    return class_();
}


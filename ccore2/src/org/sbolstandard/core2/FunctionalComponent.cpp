// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/FunctionalComponent.java
#include <org/sbolstandard/core2/FunctionalComponent.hpp>

#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
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
#include <org/sbolstandard/core2/Component.hpp>
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/DirectionType.hpp>
#include <org/sbolstandard/core2/MapsTo.hpp>
#include <org/sbolstandard/core2/ModuleDefinition.hpp>
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

org::sbolstandard::core2::FunctionalComponent::FunctionalComponent(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::FunctionalComponent::FunctionalComponent(::java::net::URI* identity, AccessType* access, ::java::net::URI* definitionURI, DirectionType* direction) 
    : FunctionalComponent(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,access,definitionURI,direction);
}

org::sbolstandard::core2::FunctionalComponent::FunctionalComponent(FunctionalComponent* functionalComponent) 
    : FunctionalComponent(*static_cast< ::default_init_tag* >(0))
{
    ctor(functionalComponent);
}

void org::sbolstandard::core2::FunctionalComponent::init()
{
    moduleDefinition = nullptr;
}

void org::sbolstandard::core2::FunctionalComponent::ctor(::java::net::URI* identity, AccessType* access, ::java::net::URI* definitionURI, DirectionType* direction)
{
    super::ctor(identity, access, definitionURI);
    init();
    this->mapsTos = new ::java::util::HashMap();
    setDirection(direction);
}

void org::sbolstandard::core2::FunctionalComponent::ctor(FunctionalComponent* functionalComponent)
{
    super::ctor(functionalComponent);
    init();
    this->setDirection(npc(functionalComponent)->getDirection());
    this->mapsTos = new ::java::util::HashMap();
    if(!npc(npc(functionalComponent)->getMapsTos())->isEmpty()) {
        ::java::util::List* mapsTos = new ::java::util::ArrayList();
        for (auto _i = npc(npc(functionalComponent)->getMapsTos())->iterator(); _i->hasNext(); ) {
            MapsTo* mapsTo = java_cast< MapsTo* >(_i->next());
            {
                npc(mapsTos)->add(static_cast< ::java::lang::Object* >(npc(mapsTo)->deepCopy()));
            }
        }
        this->setMapsTo(mapsTos);
    }
}

org::sbolstandard::core2::DirectionType* org::sbolstandard::core2::FunctionalComponent::getDirection()
{
    return direction;
}

void org::sbolstandard::core2::FunctionalComponent::setDirection(DirectionType* direction)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(direction == nullptr) {
        throw new ::java::lang::NullPointerException(u"Not a valid direction type."_j);
    }
    this->direction = direction;
}

int32_t org::sbolstandard::core2::FunctionalComponent::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((direction == nullptr) ? int32_t(0) : npc(direction)->hashCode());
    return result;
}

bool org::sbolstandard::core2::FunctionalComponent::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< FunctionalComponent* >(obj);
    return direction == npc(other)->direction;
}

org::sbolstandard::core2::FunctionalComponent* org::sbolstandard::core2::FunctionalComponent::deepCopy()
{
    return new FunctionalComponent(this);
}

void org::sbolstandard::core2::FunctionalComponent::updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    if(!npc(this->getIdentity())->equals(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(URIprefix, displayId, version)))) {
        this->setWasDerivedFrom(this->getIdentity());
    }
    this->setIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, version));
    this->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    this->setDisplayId(displayId);
    this->setVersion(version);
    for (auto _i = npc(this->getMapsTos())->iterator(); _i->hasNext(); ) {
        MapsTo* mapsTo = java_cast< MapsTo* >(_i->next());
        {
            npc(mapsTo)->updateCompliantURI(npc(this->getPersistentIdentity())->toString(), npc(mapsTo)->getDisplayId(), version);
            this->removeChildSafely(mapsTo, java_cast< ::java::util::HashMap* >(this->mapsTos));
            this->addMapsTo(mapsTo);
            auto localId = URIcompliance::extractDisplayId(npc(mapsTo)->getLocalURI());
            npc(mapsTo)->setLocal(URIcompliance::createCompliantURI(URIprefix, localId, version));
        }
    }
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::FunctionalComponent::createMapsTo(::java::net::URI* identity, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote)
{
    auto mapping = new MapsTo(identity, refinement, local, remote);
    addMapsTo(mapping);
    return mapping;
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::FunctionalComponent::createMapsTo(::java::lang::String* displayId, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto parentPersistentIdStr = npc(this->getPersistentIdentity())->toString();
    auto version = this->getVersion();
    auto m = createMapsTo(URIcompliance::createCompliantURI(parentPersistentIdStr, displayId, version), refinement, local, remote);
    npc(m)->setPersistentIdentity(URIcompliance::createCompliantURI(parentPersistentIdStr, displayId, u""_j));
    npc(m)->setDisplayId(displayId);
    npc(m)->setVersion(version);
    return m;
}

void org::sbolstandard::core2::FunctionalComponent::addMapsTo(MapsTo* mapsTo)
{
    if(sbolDocument != nullptr) {
        if(npc(moduleDefinition)->getFunctionalComponent(npc(mapsTo)->getLocalURI()) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Functional component '"_j)->append(static_cast< ::java::lang::Object* >(npc(mapsTo)->getLocalURI()))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(getDefinition())->getComponent(npc(mapsTo)->getRemoteURI()) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(npc(mapsTo)->getRemoteURI()))
                ->append(u"' does not exist."_j)->toString());
        }
        if(npc(npc(npc(getDefinition())->getComponent(npc(mapsTo)->getRemoteURI()))->getAccess())->equals(static_cast< ::java::lang::Object* >(AccessType::PRIVATE))) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(npc(mapsTo)->getRemoteURI()))
                ->append(u"' is private."_j)->toString());
        }
    }
    addChildSafely(mapsTo, mapsTos, u"mapsTo"_j, new ::java::util::MapArray());
    npc(mapsTo)->setSBOLDocument(this->sbolDocument);
    npc(mapsTo)->setModuleDefinition(moduleDefinition);
    npc(mapsTo)->setComponentInstance(this);
}

bool org::sbolstandard::core2::FunctionalComponent::removeMapsTo(MapsTo* mapsTo)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return removeChildSafely(mapsTo, mapsTos);
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::FunctionalComponent::getMapsTo(::java::lang::String* displayId)
{
    return java_cast< MapsTo* >(npc(mapsTos)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::FunctionalComponent::getMapsTo(::java::net::URI* mapsToURI)
{
    return java_cast< MapsTo* >(npc(mapsTos)->get(static_cast< ::java::lang::Object* >(mapsToURI)));
}

java::util::Set* org::sbolstandard::core2::FunctionalComponent::getMapsTos()
{
    return new ::java::util::HashSet(static_cast< ::java::util::Collection* >(npc(mapsTos)->values()));
}

void org::sbolstandard::core2::FunctionalComponent::clearMapsTos()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(mapsTos)->values())->toArray_();
    for(auto mapsTo : *npc(valueSetArray_)) {
        removeMapsTo(java_cast< MapsTo* >(mapsTo));
    }
}

void org::sbolstandard::core2::FunctionalComponent::setMapsTo(::java::util::List* mapsTos)
{
    clearMapsTos();
    for (auto _i = npc(mapsTos)->iterator(); _i->hasNext(); ) {
        MapsTo* reference = java_cast< MapsTo* >(_i->next());
        {
            addMapsTo(reference);
        }
    }
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::FunctionalComponent::getModuleDefinition()
{
    return moduleDefinition;
}

void org::sbolstandard::core2::FunctionalComponent::setModuleDefinition(ModuleDefinition* moduleDefinition)
{
    this->moduleDefinition = moduleDefinition;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::FunctionalComponent::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.FunctionalComponent", 42);
    return c;
}

java::lang::Class* org::sbolstandard::core2::FunctionalComponent::getClass0()
{
    return class_();
}


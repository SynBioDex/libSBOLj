// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Module.java
#include <org/sbolstandard/core2/Module.hpp>

#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
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

org::sbolstandard::core2::Module::Module(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Module::Module(::java::net::URI* identity, ::java::net::URI* moduleDefinition) 
    : Module(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,moduleDefinition);
}

org::sbolstandard::core2::Module::Module(Module* module) 
    : Module(*static_cast< ::default_init_tag* >(0))
{
    ctor(module);
}

void org::sbolstandard::core2::Module::init()
{
    moduleDefinition = nullptr;
}

void org::sbolstandard::core2::Module::ctor(::java::net::URI* identity, ::java::net::URI* moduleDefinition)
{
    super::ctor(identity);
    init();
    this->mapsTos = new ::java::util::HashMap();
    setDefinition(moduleDefinition);
}

void org::sbolstandard::core2::Module::ctor(Module* module)
{
    super::ctor(static_cast< Identified* >(module));
    init();
    this->mapsTos = new ::java::util::HashMap();
    this->setDefinition(npc(module)->getDefinitionURI());
    for (auto _i = npc(npc(module)->getMapsTos())->iterator(); _i->hasNext(); ) {
        MapsTo* mapping = java_cast< MapsTo* >(_i->next());
        {
            this->addMapsTo(npc(mapping)->deepCopy());
        }
    }
}

java::net::URI* org::sbolstandard::core2::Module::getDefinitionURI()
{
    return definition;
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::Module::getDefinition()
{
    if(sbolDocument == nullptr)
        return nullptr;

    return npc(sbolDocument)->getModuleDefinition(definition);
}

void org::sbolstandard::core2::Module::setDefinition(::java::net::URI* definitionURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(definitionURI == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Module "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have a definition."_j)->toString());
    }
    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getModuleDefinition(definitionURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Module definition '"_j)->append(static_cast< ::java::lang::Object* >(definition))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    this->definition = definitionURI;
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::Module::createMapsTo(::java::net::URI* identity, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote)
{
    auto mapping = new MapsTo(identity, refinement, local, remote);
    addMapsTo(mapping);
    return mapping;
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::Module::createMapsTo(::java::lang::String* displayId, RefinementType* refinement, ::java::lang::String* localId, ::java::lang::String* remoteId)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto localURI = URIcompliance::createCompliantURI(npc(npc(moduleDefinition)->getPersistentIdentity())->toString(), localId, npc(moduleDefinition)->getVersion());
    if(sbolDocument != nullptr && npc(sbolDocument)->isCreateDefaults() && moduleDefinition != nullptr && npc(moduleDefinition)->getFunctionalComponent(localURI) == nullptr) {
        npc(moduleDefinition)->createFunctionalComponent(localId, AccessType::PUBLIC, localId, u""_j, DirectionType::INOUT);
    }
    auto remote = URIcompliance::createCompliantURI(npc(npc(getDefinition())->getPersistentIdentity())->toString(), remoteId, npc(getDefinition())->getVersion());
    return createMapsTo(displayId, refinement, localURI, remote);
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::Module::createMapsTo(::java::lang::String* displayId, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto parentPersistentIdStr = npc(this->getPersistentIdentity())->toString();
    auto version = this->getVersion();
    auto newMapsToURI = URIcompliance::createCompliantURI(parentPersistentIdStr, displayId, version);
    auto m = createMapsTo(newMapsToURI, refinement, local, remote);
    npc(m)->setPersistentIdentity(URIcompliance::createCompliantURI(parentPersistentIdStr, displayId, u""_j));
    npc(m)->setDisplayId(displayId);
    npc(m)->setVersion(version);
    return m;
}

void org::sbolstandard::core2::Module::addMapsTo(MapsTo* mapsTo)
{
    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(moduleDefinition)->getFunctionalComponent(npc(mapsTo)->getLocalURI()) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Functional component '"_j)->append(static_cast< ::java::lang::Object* >(npc(mapsTo)->getLocalURI()))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(getDefinition())->getFunctionalComponent(npc(mapsTo)->getRemoteURI()) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Functional component '"_j)->append(static_cast< ::java::lang::Object* >(npc(mapsTo)->getRemoteURI()))
                ->append(u"' does not exist."_j)->toString());
        }
        if(npc(npc(npc(getDefinition())->getFunctionalComponent(npc(mapsTo)->getRemoteURI()))->getAccess())->equals(static_cast< ::java::lang::Object* >(AccessType::PRIVATE))) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Functional Component '"_j)->append(static_cast< ::java::lang::Object* >(npc(mapsTo)->getRemoteURI()))
                ->append(u"' is private."_j)->toString());
        }
    }
    addChildSafely(mapsTo, mapsTos, u"mapsTo"_j, new ::java::util::MapArray());
    npc(mapsTo)->setSBOLDocument(this->sbolDocument);
    npc(mapsTo)->setModuleDefinition(moduleDefinition);
    npc(mapsTo)->setModule(this);
}

bool org::sbolstandard::core2::Module::removeMapsTo(MapsTo* mapsTo)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return removeChildSafely(mapsTo, mapsTos);
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::Module::getMapsTo(::java::lang::String* displayId)
{
    return java_cast< MapsTo* >(npc(mapsTos)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::Module::getMapsTo(::java::net::URI* referenceURI)
{
    return java_cast< MapsTo* >(npc(mapsTos)->get(static_cast< ::java::lang::Object* >(referenceURI)));
}

java::util::Set* org::sbolstandard::core2::Module::getMapsTos()
{
    return new ::java::util::HashSet(static_cast< ::java::util::Collection* >(npc(mapsTos)->values()));
}

void org::sbolstandard::core2::Module::clearMapsTos()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(mapsTos)->values())->toArray_();
    for(auto mapsTo : *npc(valueSetArray_)) {
        removeMapsTo(java_cast< MapsTo* >(mapsTo));
    }
}

void org::sbolstandard::core2::Module::setMapsTos(::java::util::List* mappings)
{
    clearMapsTos();
    for (auto _i = npc(mappings)->iterator(); _i->hasNext(); ) {
        MapsTo* mapping = java_cast< MapsTo* >(_i->next());
        {
            addMapsTo(mapping);
        }
    }
}

int32_t org::sbolstandard::core2::Module::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((definition == nullptr) ? int32_t(0) : npc(definition)->hashCode());
    result = prime * result + ((mapsTos == nullptr) ? int32_t(0) : npc(mapsTos)->hashCode());
    return result;
}

bool org::sbolstandard::core2::Module::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< Module* >(obj);
    if(definition == nullptr) {
        if(npc(other)->definition != nullptr)
            return false;

    } else if(!npc(definition)->equals(static_cast< ::java::lang::Object* >(npc(other)->definition)))
        return false;

    if(mapsTos == nullptr) {
        if(npc(other)->mapsTos != nullptr)
            return false;

    } else if(!npc(mapsTos)->equals(static_cast< ::java::lang::Object* >(npc(other)->mapsTos)))
        return false;

    return true;
}

org::sbolstandard::core2::Module* org::sbolstandard::core2::Module::deepCopy()
{
    return new Module(this);
}

void org::sbolstandard::core2::Module::updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
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

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::Module::getModuleDefinition()
{
    return moduleDefinition;
}

void org::sbolstandard::core2::Module::setModuleDefinition(ModuleDefinition* moduleDefinition)
{
    this->moduleDefinition = moduleDefinition;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Module::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Module", 29);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Module::getClass0()
{
    return class_();
}


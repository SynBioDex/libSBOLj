// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ModuleDefinition.java
#include <org/sbolstandard/core2/ModuleDefinition.hpp>

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
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/FunctionalComponent.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/Interaction.hpp>
#include <org/sbolstandard/core2/MapsTo.hpp>
#include <org/sbolstandard/core2/Model.hpp>
#include <org/sbolstandard/core2/Module.hpp>
#include <org/sbolstandard/core2/Participation.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/SBOLValidationException.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>
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

org::sbolstandard::core2::ModuleDefinition::ModuleDefinition(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::ModuleDefinition::ModuleDefinition(::java::net::URI* identity) 
    : ModuleDefinition(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity);
}

org::sbolstandard::core2::ModuleDefinition::ModuleDefinition(ModuleDefinition* moduleDefinition) 
    : ModuleDefinition(*static_cast< ::default_init_tag* >(0))
{
    ctor(moduleDefinition);
}

void org::sbolstandard::core2::ModuleDefinition::ctor(::java::net::URI* identity)
{
    super::ctor(identity);
    this->roles = new ::java::util::HashSet();
    this->modules = new ::java::util::HashMap();
    this->interactions = new ::java::util::HashMap();
    this->functionalComponents = new ::java::util::HashMap();
    this->models = new ::java::util::HashSet();
}

void org::sbolstandard::core2::ModuleDefinition::ctor(ModuleDefinition* moduleDefinition)
{
    super::ctor(static_cast< TopLevel* >(moduleDefinition));
    this->roles = new ::java::util::HashSet();
    this->modules = new ::java::util::HashMap();
    this->interactions = new ::java::util::HashMap();
    this->functionalComponents = new ::java::util::HashMap();
    this->models = new ::java::util::HashSet();
    for (auto _i = npc(npc(moduleDefinition)->getRoles())->iterator(); _i->hasNext(); ) {
        ::java::net::URI* role = java_cast< ::java::net::URI* >(_i->next());
        {
            this->addRole(role);
        }
    }
    for (auto _i = npc(npc(moduleDefinition)->getModules())->iterator(); _i->hasNext(); ) {
        Module* subModule = java_cast< Module* >(_i->next());
        {
            this->addModule(npc(subModule)->deepCopy());
        }
    }
    for (auto _i = npc(npc(moduleDefinition)->getInteractions())->iterator(); _i->hasNext(); ) {
        Interaction* interaction = java_cast< Interaction* >(_i->next());
        {
            this->addInteraction(npc(interaction)->deepCopy());
        }
    }
    for (auto _i = npc(npc(moduleDefinition)->getFunctionalComponents())->iterator(); _i->hasNext(); ) {
        FunctionalComponent* component = java_cast< FunctionalComponent* >(_i->next());
        {
            this->addFunctionalComponent(npc(component)->deepCopy());
        }
    }
    this->setModels(npc(moduleDefinition)->getModelURIs());
}

bool org::sbolstandard::core2::ModuleDefinition::addRole(::java::net::URI* roleURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(roles)->add(static_cast< ::java::lang::Object* >(roleURI));
}

bool org::sbolstandard::core2::ModuleDefinition::removeRole(::java::net::URI* roleURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(roles)->remove(static_cast< ::java::lang::Object* >(roleURI));
}

void org::sbolstandard::core2::ModuleDefinition::setRoles(::java::util::Set* roles)
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

java::util::Set* org::sbolstandard::core2::ModuleDefinition::getRoles()
{
    ::java::util::Set* result = new ::java::util::HashSet();
    npc(result)->addAll(static_cast< ::java::util::Collection* >(roles));
    return result;
}

bool org::sbolstandard::core2::ModuleDefinition::containsRole(::java::net::URI* roleURI)
{
    return npc(roles)->contains(static_cast< ::java::lang::Object* >(roleURI));
}

void org::sbolstandard::core2::ModuleDefinition::clearRoles()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    npc(roles)->clear();
}

org::sbolstandard::core2::Module* org::sbolstandard::core2::ModuleDefinition::createModule(::java::net::URI* identity, ::java::net::URI* moduleDefinitionURI)
{
    auto module = new Module(identity, moduleDefinitionURI);
    addModule(module);
    return module;
}

org::sbolstandard::core2::Module* org::sbolstandard::core2::ModuleDefinition::createModule(::java::lang::String* displayId, ::java::lang::String* moduleDefinitionId, ::java::lang::String* version)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto module = URIcompliance::createCompliantURI(npc(sbolDocument)->getDefaultURIprefix(), TopLevel::MODULE_DEFINITION(), moduleDefinitionId, version, npc(sbolDocument)->isTypesInURIs());
    return createModule(displayId, module);
}

org::sbolstandard::core2::Module* org::sbolstandard::core2::ModuleDefinition::createModule(::java::lang::String* displayId, ::java::net::URI* moduleDefinitionURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getModuleDefinition(moduleDefinitionURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Module definition '"_j)->append(static_cast< ::java::lang::Object* >(moduleDefinitionURI))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    auto URIprefix = npc(this->getPersistentIdentity())->toString();
    auto version = this->getVersion();
    auto newModuleURI = URIcompliance::createCompliantURI(URIprefix, displayId, version);
    auto m = createModule(newModuleURI, moduleDefinitionURI);
    npc(m)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    npc(m)->setDisplayId(displayId);
    npc(m)->setVersion(version);
    return m;
}

void org::sbolstandard::core2::ModuleDefinition::addModule(Module* module)
{
    addChildSafely(module, modules, u"module"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(functionalComponents), static_cast< ::java::util::Map* >(interactions)}));
    npc(module)->setSBOLDocument(this->sbolDocument);
    npc(module)->setModuleDefinition(this);
}

bool org::sbolstandard::core2::ModuleDefinition::removeModule(Module* module)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return removeChildSafely(module, modules);
}

org::sbolstandard::core2::Module* org::sbolstandard::core2::ModuleDefinition::getModule(::java::lang::String* displayId)
{
    return java_cast< Module* >(npc(modules)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::Module* org::sbolstandard::core2::ModuleDefinition::getModule(::java::net::URI* moduleURI)
{
    return java_cast< Module* >(npc(modules)->get(static_cast< ::java::lang::Object* >(moduleURI)));
}

java::util::Set* org::sbolstandard::core2::ModuleDefinition::getModules()
{
    return new ::java::util::HashSet(static_cast< ::java::util::Collection* >(npc(modules)->values()));
}

void org::sbolstandard::core2::ModuleDefinition::clearModules()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(modules)->values())->toArray_();
    for(auto module : *npc(valueSetArray_)) {
        removeModule(java_cast< Module* >(module));
    }
}

void org::sbolstandard::core2::ModuleDefinition::setModules(::java::util::List* modules)
{
    clearModules();
    if(modules == nullptr)
        return;

    for (auto _i = npc(modules)->iterator(); _i->hasNext(); ) {
        Module* module = java_cast< Module* >(_i->next());
        {
            addModule(module);
        }
    }
}

org::sbolstandard::core2::Interaction* org::sbolstandard::core2::ModuleDefinition::createInteraction(::java::net::URI* identity, ::java::util::Set* type)
{
    auto interaction = new Interaction(identity, type);
    addInteraction(interaction);
    return interaction;
}

org::sbolstandard::core2::Interaction* org::sbolstandard::core2::ModuleDefinition::createInteraction(::java::lang::String* displayId, ::java::util::Set* types)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto URIprefix = npc(this->getPersistentIdentity())->toString();
    auto version = this->getVersion();
    auto newInteractionURI = URIcompliance::createCompliantURI(URIprefix, displayId, version);
    auto i = createInteraction(newInteractionURI, types);
    npc(i)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    npc(i)->setDisplayId(displayId);
    npc(i)->setVersion(version);
    return i;
}

void org::sbolstandard::core2::ModuleDefinition::addInteraction(Interaction* interaction)
{
    addChildSafely(interaction, interactions, u"interaction"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(functionalComponents), static_cast< ::java::util::Map* >(modules)}));
    npc(interaction)->setSBOLDocument(this->sbolDocument);
    npc(interaction)->setModuleDefinition(this);
}

bool org::sbolstandard::core2::ModuleDefinition::removeInteraction(Interaction* interaction)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return removeChildSafely(interaction, interactions);
}

org::sbolstandard::core2::Interaction* org::sbolstandard::core2::ModuleDefinition::getInteraction(::java::lang::String* displayId)
{
    return java_cast< Interaction* >(npc(interactions)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::Interaction* org::sbolstandard::core2::ModuleDefinition::getInteraction(::java::net::URI* interactionURI)
{
    return java_cast< Interaction* >(npc(interactions)->get(static_cast< ::java::lang::Object* >(interactionURI)));
}

java::util::Set* org::sbolstandard::core2::ModuleDefinition::getInteractions()
{
    return new ::java::util::HashSet(static_cast< ::java::util::Collection* >(npc(interactions)->values()));
}

void org::sbolstandard::core2::ModuleDefinition::clearInteractions()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(interactions)->values())->toArray_();
    for(auto interaction : *npc(valueSetArray_)) {
        removeInteraction(java_cast< Interaction* >(interaction));
    }
}

void org::sbolstandard::core2::ModuleDefinition::setInteractions(::java::util::List* interactions)
{
    clearInteractions();
    if(interactions == nullptr)
        return;

    for (auto _i = npc(interactions)->iterator(); _i->hasNext(); ) {
        Interaction* interaction = java_cast< Interaction* >(_i->next());
        {
            addInteraction(interaction);
        }
    }
}

org::sbolstandard::core2::FunctionalComponent* org::sbolstandard::core2::ModuleDefinition::createFunctionalComponent(::java::net::URI* identity, AccessType* access, ::java::net::URI* definitionURI, DirectionType* direction)
{
    auto functionalComponent = new FunctionalComponent(identity, access, definitionURI, direction);
    addFunctionalComponent(functionalComponent);
    return functionalComponent;
}

org::sbolstandard::core2::FunctionalComponent* org::sbolstandard::core2::ModuleDefinition::createFunctionalComponent(::java::lang::String* displayId, AccessType* access, ::java::lang::String* definitionId, ::java::lang::String* version, DirectionType* direction)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto definitionURI = URIcompliance::createCompliantURI(npc(sbolDocument)->getDefaultURIprefix(), TopLevel::COMPONENT_DEFINITION(), definitionId, version, npc(sbolDocument)->isTypesInURIs());
    return createFunctionalComponent(displayId, access, definitionURI, direction);
}

org::sbolstandard::core2::FunctionalComponent* org::sbolstandard::core2::ModuleDefinition::createFunctionalComponent(::java::lang::String* displayId, AccessType* access, ::java::net::URI* componentDefinitionURI, DirectionType* direction)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getComponentDefinition(componentDefinitionURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component definition '"_j)->append(static_cast< ::java::lang::Object* >(componentDefinitionURI))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    auto URIprefix = npc(this->getPersistentIdentity())->toString();
    auto version = this->getVersion();
    auto functionalComponentURI = URIcompliance::createCompliantURI(URIprefix, displayId, version);
    auto fc = createFunctionalComponent(functionalComponentURI, access, componentDefinitionURI, direction);
    npc(fc)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    npc(fc)->setDisplayId(displayId);
    npc(fc)->setVersion(version);
    return fc;
}

void org::sbolstandard::core2::ModuleDefinition::addFunctionalComponent(FunctionalComponent* functionalComponent)
{
    addChildSafely(functionalComponent, functionalComponents, u"functionalComponent"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(interactions), static_cast< ::java::util::Map* >(modules)}));
    npc(functionalComponent)->setSBOLDocument(this->sbolDocument);
    npc(functionalComponent)->setModuleDefinition(this);
}

bool org::sbolstandard::core2::ModuleDefinition::removeFunctionalComponent(FunctionalComponent* functionalComponent)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    for (auto _i = npc(npc(interactions)->values())->iterator(); _i->hasNext(); ) {
        Interaction* i = java_cast< Interaction* >(_i->next());
        {
            for (auto _i = npc(npc(i)->getParticipations())->iterator(); _i->hasNext(); ) {
                Participation* p = java_cast< Participation* >(_i->next());
                {
                    if(npc(npc(p)->getParticipantURI())->equals(static_cast< ::java::lang::Object* >(npc(functionalComponent)->getIdentity()))) {
                        throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(functionalComponent)->getIdentity()))
                            ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                    }
                }
            }
        }
    }
    for (auto _i = npc(npc(functionalComponents)->values())->iterator(); _i->hasNext(); ) {
        FunctionalComponent* c = java_cast< FunctionalComponent* >(_i->next());
        {
            for (auto _i = npc(npc(c)->getMapsTos())->iterator(); _i->hasNext(); ) {
                MapsTo* mt = java_cast< MapsTo* >(_i->next());
                {
                    if(npc(npc(mt)->getLocalURI())->equals(static_cast< ::java::lang::Object* >(npc(functionalComponent)->getIdentity()))) {
                        throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(functionalComponent)->getIdentity()))
                            ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                    }
                }
            }
        }
    }
    for (auto _i = npc(npc(modules)->values())->iterator(); _i->hasNext(); ) {
        Module* m = java_cast< Module* >(_i->next());
        {
            for (auto _i = npc(npc(m)->getMapsTos())->iterator(); _i->hasNext(); ) {
                MapsTo* mt = java_cast< MapsTo* >(_i->next());
                {
                    if(npc(npc(mt)->getLocalURI())->equals(static_cast< ::java::lang::Object* >(npc(functionalComponent)->getIdentity()))) {
                        throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(functionalComponent)->getIdentity()))
                            ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                    }
                }
            }
        }
    }
    if(sbolDocument != nullptr) {
        for (auto _i = npc(npc(sbolDocument)->getModuleDefinitions())->iterator(); _i->hasNext(); ) {
            ModuleDefinition* md = java_cast< ModuleDefinition* >(_i->next());
            {
                for (auto _i = npc(npc(md)->getModules())->iterator(); _i->hasNext(); ) {
                    Module* m = java_cast< Module* >(_i->next());
                    {
                        for (auto _i = npc(npc(m)->getMapsTos())->iterator(); _i->hasNext(); ) {
                            MapsTo* mt = java_cast< MapsTo* >(_i->next());
                            {
                                if(npc(npc(mt)->getRemoteURI())->equals(static_cast< ::java::lang::Object* >(npc(functionalComponent)->getIdentity()))) {
                                    throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(functionalComponent)->getIdentity()))
                                        ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return removeChildSafely(functionalComponent, functionalComponents);
}

org::sbolstandard::core2::FunctionalComponent* org::sbolstandard::core2::ModuleDefinition::getFunctionalComponent(::java::lang::String* displayId)
{
    return java_cast< FunctionalComponent* >(npc(functionalComponents)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::FunctionalComponent* org::sbolstandard::core2::ModuleDefinition::getFunctionalComponent(::java::net::URI* functionalComponentURI)
{
    return java_cast< FunctionalComponent* >(npc(functionalComponents)->get(static_cast< ::java::lang::Object* >(functionalComponentURI)));
}

java::util::Set* org::sbolstandard::core2::ModuleDefinition::getFunctionalComponents()
{
    return new ::java::util::HashSet(static_cast< ::java::util::Collection* >(npc(functionalComponents)->values()));
}

void org::sbolstandard::core2::ModuleDefinition::clearFunctionalComponents()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(functionalComponents)->values())->toArray_();
    for(auto functionalComponent : *npc(valueSetArray_)) {
        removeFunctionalComponent(java_cast< FunctionalComponent* >(functionalComponent));
    }
}

void org::sbolstandard::core2::ModuleDefinition::setFunctionalComponents(::java::util::List* components)
{
    clearFunctionalComponents();
    if(components == nullptr)
        return;

    for (auto _i = npc(components)->iterator(); _i->hasNext(); ) {
        FunctionalComponent* component = java_cast< FunctionalComponent* >(_i->next());
        {
            addFunctionalComponent(component);
        }
    }
}

bool org::sbolstandard::core2::ModuleDefinition::addModel(Model* model)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getModel(npc(model)->getIdentity()) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Model '"_j)->append(static_cast< ::java::lang::Object* >(npc(model)->getIdentity()))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    return this->addModel(npc(model)->getIdentity());
}

bool org::sbolstandard::core2::ModuleDefinition::addModel(::java::lang::String* modelId, ::java::lang::String* version)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto modelURI = URIcompliance::createCompliantURI(npc(sbolDocument)->getDefaultURIprefix(), TopLevel::MODEL(), modelId, version, npc(sbolDocument)->isTypesInURIs());
    return addModel(modelURI);
}

bool org::sbolstandard::core2::ModuleDefinition::addModel(::java::net::URI* modelURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getModel(modelURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Model '"_j)->append(static_cast< ::java::lang::Object* >(modelURI))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    return npc(models)->add(static_cast< ::java::lang::Object* >(modelURI));
}

bool org::sbolstandard::core2::ModuleDefinition::removeModel(::java::net::URI* modelURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(models)->remove(static_cast< ::java::lang::Object* >(modelURI));
}

void org::sbolstandard::core2::ModuleDefinition::setModels(::java::util::Set* models)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    clearModels();
    if(models == nullptr)
        return;

    for (auto _i = npc(models)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* model = java_cast< ::java::net::URI* >(_i->next());
        {
            addModel(model);
        }
    }
}

java::util::Set* org::sbolstandard::core2::ModuleDefinition::getModelURIs()
{
    ::java::util::Set* result = new ::java::util::HashSet();
    npc(result)->addAll(static_cast< ::java::util::Collection* >(models));
    return result;
}

java::util::Set* org::sbolstandard::core2::ModuleDefinition::getModels()
{
    ::java::util::Set* result = new ::java::util::HashSet();
    for (auto _i = npc(models)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* modelURI = java_cast< ::java::net::URI* >(_i->next());
        {
            auto model = npc(sbolDocument)->getModel(modelURI);
            npc(result)->add(static_cast< ::java::lang::Object* >(model));
        }
    }
    return result;
}

bool org::sbolstandard::core2::ModuleDefinition::containsModel(::java::net::URI* modelURI)
{
    return npc(models)->contains(static_cast< ::java::lang::Object* >(modelURI));
}

void org::sbolstandard::core2::ModuleDefinition::clearModels()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    npc(models)->clear();
}

int32_t org::sbolstandard::core2::ModuleDefinition::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((functionalComponents == nullptr) ? int32_t(0) : npc(functionalComponents)->hashCode());
    result = prime * result + ((interactions == nullptr) ? int32_t(0) : npc(interactions)->hashCode());
    result = prime * result + ((models == nullptr) ? int32_t(0) : npc(models)->hashCode());
    result = prime * result + ((roles == nullptr) ? int32_t(0) : npc(roles)->hashCode());
    result = prime * result + ((modules == nullptr) ? int32_t(0) : npc(modules)->hashCode());
    return result;
}

bool org::sbolstandard::core2::ModuleDefinition::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< ModuleDefinition* >(obj);
    if(functionalComponents == nullptr) {
        if(npc(other)->functionalComponents != nullptr)
            return false;

    } else if(!npc(functionalComponents)->equals(static_cast< ::java::lang::Object* >(npc(other)->functionalComponents)))
        return false;

    if(interactions == nullptr) {
        if(npc(other)->interactions != nullptr)
            return false;

    } else if(!npc(interactions)->equals(static_cast< ::java::lang::Object* >(npc(other)->interactions)))
        return false;

    if(models == nullptr) {
        if(npc(other)->models != nullptr)
            return false;

    } else if(!npc(models)->equals(static_cast< ::java::lang::Object* >(npc(other)->models)))
        return false;

    if(roles == nullptr) {
        if(npc(other)->roles != nullptr)
            return false;

    } else if(!npc(roles)->equals(static_cast< ::java::lang::Object* >(npc(other)->roles)))
        return false;

    if(modules == nullptr) {
        if(npc(other)->modules != nullptr)
            return false;

    } else if(!npc(modules)->equals(static_cast< ::java::lang::Object* >(npc(other)->modules)))
        return false;

    return true;
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::ModuleDefinition::deepCopy()
{
    return new ModuleDefinition(this);
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::ModuleDefinition::copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
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
    auto count = int32_t(0);
    for (auto _i = npc(npc(cloned)->getFunctionalComponents())->iterator(); _i->hasNext(); ) {
        FunctionalComponent* component = java_cast< FunctionalComponent* >(_i->next());
        {
            if(!npc(component)->isSetDisplayId())
                npc(component)->setDisplayId(::java::lang::StringBuilder().append(u"functionalComponent"_j)->append(++count)->toString());

            npc(component)->updateCompliantURI(npc(npc(cloned)->getPersistentIdentity())->toString(), npc(component)->getDisplayId(), version);
            npc(cloned)->removeChildSafely(component, npc(cloned)->functionalComponents);
            npc(cloned)->addFunctionalComponent(component);
        }
    }
    count = 0;
    for (auto _i = npc(npc(cloned)->getModules())->iterator(); _i->hasNext(); ) {
        Module* module = java_cast< Module* >(_i->next());
        {
            if(!npc(module)->isSetDisplayId())
                npc(module)->setDisplayId(::java::lang::StringBuilder().append(u"module"_j)->append(++count)->toString());

            npc(module)->updateCompliantURI(npc(npc(cloned)->getPersistentIdentity())->toString(), npc(module)->getDisplayId(), version);
            npc(cloned)->removeChildSafely(module, npc(cloned)->modules);
            npc(cloned)->addModule(module);
        }
    }
    count = 0;
    for (auto _i = npc(npc(cloned)->getInteractions())->iterator(); _i->hasNext(); ) {
        Interaction* interaction = java_cast< Interaction* >(_i->next());
        {
            if(!npc(interaction)->isSetDisplayId())
                npc(interaction)->setDisplayId(::java::lang::StringBuilder().append(u"interaction"_j)->append(++count)->toString());

            npc(interaction)->updateCompliantURI(npc(npc(cloned)->getPersistentIdentity())->toString(), npc(interaction)->getDisplayId(), version);
            npc(cloned)->removeChildSafely(interaction, npc(cloned)->interactions);
            npc(cloned)->addInteraction(interaction);
        }
    }
    return cloned;
}

bool org::sbolstandard::core2::ModuleDefinition::checkDescendantsURIcompliance()
{
    if(!URIcompliance::isTopLevelURIformCompliant(this->getIdentity()))
        return false;

    auto allDescendantsCompliant = true;
    if(!npc(this->getModules())->isEmpty()) {
        for (auto _i = npc(this->getModules())->iterator(); _i->hasNext(); ) {
            Module* module = java_cast< Module* >(_i->next());
            {
                allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(this, module);
                if(!allDescendantsCompliant) {
                    return allDescendantsCompliant;
                }
                if(!npc(npc(module)->getMapsTos())->isEmpty()) {
                    for (auto _i = npc(npc(module)->getMapsTos())->iterator(); _i->hasNext(); ) {
                        MapsTo* mapsTo = java_cast< MapsTo* >(_i->next());
                        {
                            allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(module, mapsTo);
                            if(!allDescendantsCompliant) {
                                return allDescendantsCompliant;
                            }
                        }
                    }
                }
            }
        }
    }
    if(!npc(this->getFunctionalComponents())->isEmpty()) {
        for (auto _i = npc(this->getFunctionalComponents())->iterator(); _i->hasNext(); ) {
            FunctionalComponent* functionalComponent = java_cast< FunctionalComponent* >(_i->next());
            {
                allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(this, functionalComponent);
                if(!allDescendantsCompliant) {
                    return allDescendantsCompliant;
                }
                if(!npc(npc(functionalComponent)->getMapsTos())->isEmpty()) {
                    for (auto _i = npc(npc(functionalComponent)->getMapsTos())->iterator(); _i->hasNext(); ) {
                        MapsTo* mapsTo = java_cast< MapsTo* >(_i->next());
                        {
                            allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(functionalComponent, mapsTo);
                            if(!allDescendantsCompliant) {
                                return allDescendantsCompliant;
                            }
                        }
                    }
                }
            }
        }
    }
    if(!npc(this->getInteractions())->isEmpty()) {
        for (auto _i = npc(this->getInteractions())->iterator(); _i->hasNext(); ) {
            Interaction* interaction = java_cast< Interaction* >(_i->next());
            {
                allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(this, interaction);
                if(!allDescendantsCompliant) {
                    return allDescendantsCompliant;
                }
                for (auto _i = npc(npc(interaction)->getParticipations())->iterator(); _i->hasNext(); ) {
                    Participation* participation = java_cast< Participation* >(_i->next());
                    {
                        allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(interaction, participation);
                        if(!allDescendantsCompliant) {
                            return allDescendantsCompliant;
                        }
                    }
                }
            }
        }
    }
    return allDescendantsCompliant;
}

bool org::sbolstandard::core2::ModuleDefinition::isComplete()
{
    if(sbolDocument == nullptr)
        return false;

    for (auto _i = npc(models)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* modelURI = java_cast< ::java::net::URI* >(_i->next());
        {
            if(npc(sbolDocument)->getModel(modelURI) == nullptr)
                return false;

        }
    }
    for (auto _i = npc(getFunctionalComponents())->iterator(); _i->hasNext(); ) {
        FunctionalComponent* functionalComponent = java_cast< FunctionalComponent* >(_i->next());
        {
            if(npc(functionalComponent)->getDefinition() == nullptr)
                return false;

        }
    }
    for (auto _i = npc(getModules())->iterator(); _i->hasNext(); ) {
        Module* module = java_cast< Module* >(_i->next());
        {
            if(npc(module)->getDefinition() == nullptr)
                return false;

        }
    }
    return true;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::ModuleDefinition::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.ModuleDefinition", 39);
    return c;
}

java::lang::Class* org::sbolstandard::core2::ModuleDefinition::getClass0()
{
    return class_();
}


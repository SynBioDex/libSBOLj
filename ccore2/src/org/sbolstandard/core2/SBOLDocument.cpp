// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLDocument.java
#include <org/sbolstandard/core2/SBOLDocument.hpp>

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
#include <javax/xml/namespace_/QName.hpp>
#include <org/sbolstandard/core2/Collection.hpp>
#include <org/sbolstandard/core2/Component.hpp>
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/FunctionalComponent.hpp>
#include <org/sbolstandard/core2/GenericTopLevel.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/Interaction.hpp>
#include <org/sbolstandard/core2/Location.hpp>
#include <org/sbolstandard/core2/MapsTo.hpp>
#include <org/sbolstandard/core2/Model.hpp>
#include <org/sbolstandard/core2/Module.hpp>
#include <org/sbolstandard/core2/ModuleDefinition.hpp>
#include <org/sbolstandard/core2/Participation.hpp>
#include <org/sbolstandard/core2/SBOLValidationException.hpp>
#include <org/sbolstandard/core2/Sbol1Terms.hpp>
#include <org/sbolstandard/core2/Sbol2Terms.hpp>
#include <org/sbolstandard/core2/Sequence.hpp>
#include <org/sbolstandard/core2/SequenceAnnotation.hpp>
#include <org/sbolstandard/core2/SequenceConstraint.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>
#include <org/sbolstandard/core2/Version.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Datatree.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NamespaceBinding.hpp>
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

org::sbolstandard::core2::SBOLDocument::SBOLDocument(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SBOLDocument::SBOLDocument() 
    : SBOLDocument(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

void org::sbolstandard::core2::SBOLDocument::init()
{
    complete = false;
    compliant = true;
    typesInURIs = false;
    createDefaults = false;
}

void org::sbolstandard::core2::SBOLDocument::ctor()
{
    super::ctor();
    init();
    genericTopLevels = new ::java::util::HashMap();
    collections = new ::java::util::HashMap();
    componentDefinitions = new ::java::util::HashMap();
    models = new ::java::util::HashMap();
    moduleDefinitions = new ::java::util::HashMap();
    sequences = new ::java::util::HashMap();
    nameSpaces = new ::java::util::HashMap();
    npc(nameSpaces)->put(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(Sbol2Terms::sbol2())->getNamespaceURI())), static_cast< ::java::lang::Object* >(Sbol2Terms::sbol2()));
    npc(nameSpaces)->put(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(Sbol1Terms::rdf())->getNamespaceURI())), static_cast< ::java::lang::Object* >(Sbol1Terms::rdf()));
    npc(nameSpaces)->put(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(Sbol2Terms::dc())->getNamespaceURI())), static_cast< ::java::lang::Object* >(Sbol2Terms::dc()));
    npc(nameSpaces)->put(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(Sbol2Terms::prov())->getNamespaceURI())), static_cast< ::java::lang::Object* >(Sbol2Terms::prov()));
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::SBOLDocument::createModuleDefinition(::java::lang::String* displayId)
{
    return createModuleDefinition(defaultURIprefix, displayId, u""_j);
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::SBOLDocument::createModuleDefinition(::java::lang::String* displayId, ::java::lang::String* version)
{
    return createModuleDefinition(defaultURIprefix, displayId, version);
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::SBOLDocument::createModuleDefinition(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    checkReadOnly();
    URIprefix = checkURIprefix(URIprefix);
    URIcompliance::validateIdVersion(displayId, version);
    auto md = createModuleDefinition(URIcompliance::createCompliantURI(URIprefix, TopLevel::MODULE_DEFINITION(), displayId, version, typesInURIs));
    npc(md)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, TopLevel::MODULE_DEFINITION(), displayId, u""_j, typesInURIs));
    npc(md)->setDisplayId(displayId);
    npc(md)->setVersion(version);
    return md;
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::SBOLDocument::createModuleDefinition(::java::net::URI* identity)
{
    auto newModule = new ModuleDefinition(identity);
    addModuleDefinition(newModule);
    return newModule;
}

void org::sbolstandard::core2::SBOLDocument::addModuleDefinition(ModuleDefinition* newModuleDefinition)
{
    addTopLevel(newModuleDefinition, moduleDefinitions, u"moduleDefinition"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(collections), static_cast< ::java::util::Map* >(componentDefinitions), static_cast< ::java::util::Map* >(genericTopLevels), static_cast< ::java::util::Map* >(models), static_cast< ::java::util::Map* >(sequences)}));
    for (auto _i = npc(npc(newModuleDefinition)->getFunctionalComponents())->iterator(); _i->hasNext(); ) {
        FunctionalComponent* functionalComponent = java_cast< FunctionalComponent* >(_i->next());
        {
            npc(functionalComponent)->setSBOLDocument(this);
            for (auto _i = npc(npc(functionalComponent)->getMapsTos())->iterator(); _i->hasNext(); ) {
                MapsTo* mapsTo = java_cast< MapsTo* >(_i->next());
                {
                    npc(mapsTo)->setSBOLDocument(this);
                }
            }
        }
    }
    for (auto _i = npc(npc(newModuleDefinition)->getModules())->iterator(); _i->hasNext(); ) {
        Module* module = java_cast< Module* >(_i->next());
        {
            npc(module)->setSBOLDocument(this);
            for (auto _i = npc(npc(module)->getMapsTos())->iterator(); _i->hasNext(); ) {
                MapsTo* mapsTo = java_cast< MapsTo* >(_i->next());
                {
                    npc(mapsTo)->setSBOLDocument(this);
                }
            }
        }
    }
    for (auto _i = npc(npc(newModuleDefinition)->getInteractions())->iterator(); _i->hasNext(); ) {
        Interaction* interaction = java_cast< Interaction* >(_i->next());
        {
            npc(interaction)->setSBOLDocument(this);
            for (auto _i = npc(npc(interaction)->getParticipations())->iterator(); _i->hasNext(); ) {
                Participation* participation = java_cast< Participation* >(_i->next());
                {
                    npc(participation)->setSBOLDocument(this);
                }
            }
        }
    }
}

bool org::sbolstandard::core2::SBOLDocument::removeModuleDefinition(ModuleDefinition* moduleDefinition)
{
    checkReadOnly();
    if(complete) {
        for (auto _i = npc(npc(moduleDefinitions)->values())->iterator(); _i->hasNext(); ) {
            ModuleDefinition* md = java_cast< ModuleDefinition* >(_i->next());
            {
                for (auto _i = npc(npc(md)->getModules())->iterator(); _i->hasNext(); ) {
                    Module* m = java_cast< Module* >(_i->next());
                    {
                        if(npc(npc(m)->getDefinitionURI())->equals(static_cast< ::java::lang::Object* >(npc(moduleDefinition)->getIdentity()))) {
                            throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(moduleDefinition)->getIdentity()))
                                ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                        }
                    }
                }
            }
        }
    }
    return removeTopLevel(moduleDefinition, moduleDefinitions);
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::SBOLDocument::getModuleDefinition(::java::lang::String* displayId, ::java::lang::String* version)
{
    validateIdentityData(displayId, version);
    return java_cast< ModuleDefinition* >(npc(moduleDefinitions)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(defaultURIprefix, TopLevel::MODULE_DEFINITION(), displayId, version, typesInURIs))));
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::SBOLDocument::getModuleDefinition(::java::net::URI* moduleURI)
{
    return java_cast< ModuleDefinition* >(npc(moduleDefinitions)->get(static_cast< ::java::lang::Object* >(moduleURI)));
}

java::util::Set* org::sbolstandard::core2::SBOLDocument::getModuleDefinitions()
{
    ::java::util::Set* moduleDefinitions = new ::java::util::HashSet();
    npc(moduleDefinitions)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->moduleDefinitions))->values()));
    return moduleDefinitions;
}

void org::sbolstandard::core2::SBOLDocument::clearModuleDefinitions()
{
    checkReadOnly();
    auto valueSetArray_ = npc(npc(moduleDefinitions)->values())->toArray_();
    for(auto moduleDefinition : *npc(valueSetArray_)) {
        removeModuleDefinition(java_cast< ModuleDefinition* >(moduleDefinition));
    }
}

void org::sbolstandard::core2::SBOLDocument::setModuleDefinitions(::java::util::List* moduleDefinitions)
{
    clearModuleDefinitions();
    for (auto _i = npc(moduleDefinitions)->iterator(); _i->hasNext(); ) {
        ModuleDefinition* module = java_cast< ModuleDefinition* >(_i->next());
        {
            addModuleDefinition(module);
        }
    }
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::SBOLDocument::createCollection(::java::net::URI* identity)
{
    auto newCollection = new Collection(identity);
    addCollection(newCollection);
    return newCollection;
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::SBOLDocument::createCollection(::java::lang::String* displayId)
{
    return createCollection(defaultURIprefix, displayId, u""_j);
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::SBOLDocument::createCollection(::java::lang::String* displayId, ::java::lang::String* version)
{
    return createCollection(defaultURIprefix, displayId, version);
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::SBOLDocument::createCollection(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    checkReadOnly();
    URIprefix = checkURIprefix(URIprefix);
    URIcompliance::validateIdVersion(displayId, version);
    auto c = createCollection(URIcompliance::createCompliantURI(URIprefix, TopLevel::COLLECTION(), displayId, version, typesInURIs));
    npc(c)->setDisplayId(displayId);
    npc(c)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, TopLevel::COLLECTION(), displayId, u""_j, typesInURIs));
    npc(c)->setVersion(version);
    return c;
}

void org::sbolstandard::core2::SBOLDocument::addCollection(Collection* collection)
{
    addTopLevel(collection, collections, u"collection"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(componentDefinitions), static_cast< ::java::util::Map* >(genericTopLevels), static_cast< ::java::util::Map* >(models), static_cast< ::java::util::Map* >(moduleDefinitions), static_cast< ::java::util::Map* >(sequences)}));
}

bool org::sbolstandard::core2::SBOLDocument::removeCollection(Collection* collection)
{
    checkReadOnly();
    return removeTopLevel(collection, collections);
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::SBOLDocument::getCollection(::java::lang::String* displayId, ::java::lang::String* version)
{
    validateIdentityData(displayId, version);
    return java_cast< Collection* >(npc(collections)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(defaultURIprefix, TopLevel::COLLECTION(), displayId, version, typesInURIs))));
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::SBOLDocument::getCollection(::java::net::URI* collectionURI)
{
    return java_cast< Collection* >(npc(collections)->get(static_cast< ::java::lang::Object* >(collectionURI)));
}

java::util::Set* org::sbolstandard::core2::SBOLDocument::getCollections()
{
    ::java::util::Set* collections = new ::java::util::HashSet();
    npc(collections)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->collections))->values()));
    return collections;
}

void org::sbolstandard::core2::SBOLDocument::clearCollections()
{
    checkReadOnly();
    auto valueSetArray_ = npc(npc(collections)->values())->toArray_();
    for(auto collection : *npc(valueSetArray_)) {
        removeCollection(java_cast< Collection* >(collection));
    }
}

void org::sbolstandard::core2::SBOLDocument::setCollections(::java::util::List* collections)
{
    clearCollections();
    for (auto _i = npc(collections)->iterator(); _i->hasNext(); ) {
        Collection* collection = java_cast< Collection* >(_i->next());
        {
            addCollection(collection);
        }
    }
}

org::sbolstandard::core2::Model* org::sbolstandard::core2::SBOLDocument::createModel(::java::lang::String* displayId, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework)
{
    return createModel(defaultURIprefix, displayId, u""_j, source, language, framework);
}

org::sbolstandard::core2::Model* org::sbolstandard::core2::SBOLDocument::createModel(::java::lang::String* displayId, ::java::lang::String* version, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework)
{
    return createModel(defaultURIprefix, displayId, version, source, language, framework);
}

org::sbolstandard::core2::Model* org::sbolstandard::core2::SBOLDocument::createModel(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework)
{
    checkReadOnly();
    URIprefix = checkURIprefix(URIprefix);
    URIcompliance::validateIdVersion(displayId, version);
    auto model = createModel(URIcompliance::createCompliantURI(URIprefix, TopLevel::MODEL(), displayId, version, typesInURIs), source, language, framework);
    npc(model)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, TopLevel::MODEL(), displayId, u""_j, typesInURIs));
    npc(model)->setDisplayId(displayId);
    npc(model)->setVersion(version);
    return model;
}

org::sbolstandard::core2::Model* org::sbolstandard::core2::SBOLDocument::createModel(::java::net::URI* identity, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework)
{
    auto newModel = new Model(identity, source, language, framework);
    addModel(newModel);
    return newModel;
}

void org::sbolstandard::core2::SBOLDocument::addModel(Model* newModel)
{
    addTopLevel(newModel, models, u"model"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(collections), static_cast< ::java::util::Map* >(componentDefinitions), static_cast< ::java::util::Map* >(genericTopLevels), static_cast< ::java::util::Map* >(moduleDefinitions), static_cast< ::java::util::Map* >(sequences)}));
}

bool org::sbolstandard::core2::SBOLDocument::removeModel(Model* model)
{
    checkReadOnly();
    if(complete) {
        for (auto _i = npc(npc(moduleDefinitions)->values())->iterator(); _i->hasNext(); ) {
            ModuleDefinition* md = java_cast< ModuleDefinition* >(_i->next());
            {
                if(npc(md)->containsModel(npc(model)->getIdentity())) {
                    throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(model)->getIdentity()))
                        ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                }
            }
        }
    }
    return removeTopLevel(model, models);
}

org::sbolstandard::core2::Model* org::sbolstandard::core2::SBOLDocument::getModel(::java::lang::String* displayId, ::java::lang::String* version)
{
    validateIdentityData(displayId, version);
    return java_cast< Model* >(npc(models)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(defaultURIprefix, TopLevel::MODEL(), displayId, version, typesInURIs))));
}

org::sbolstandard::core2::Model* org::sbolstandard::core2::SBOLDocument::getModel(::java::net::URI* modelURI)
{
    return java_cast< Model* >(npc(models)->get(static_cast< ::java::lang::Object* >(modelURI)));
}

java::util::Set* org::sbolstandard::core2::SBOLDocument::getModels()
{
    ::java::util::Set* models = new ::java::util::HashSet();
    npc(models)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->models))->values()));
    return models;
}

void org::sbolstandard::core2::SBOLDocument::clearModels()
{
    checkReadOnly();
    auto valueSetArray_ = npc(npc(models)->values())->toArray_();
    for(auto model : *npc(valueSetArray_)) {
        removeModel(java_cast< Model* >(model));
    }
}

void org::sbolstandard::core2::SBOLDocument::setModels(::java::util::List* models)
{
    clearModels();
    for (auto _i = npc(models)->iterator(); _i->hasNext(); ) {
        Model* model = java_cast< Model* >(_i->next());
        {
            addModel(model);
        }
    }
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SBOLDocument::createComponentDefinition(::java::net::URI* identity, ::java::util::Set* types)
{
    auto newComponentDefinition = new ComponentDefinition(identity, types);
    addComponentDefinition(newComponentDefinition);
    return newComponentDefinition;
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SBOLDocument::createComponentDefinition(::java::lang::String* displayId, ::java::util::Set* types)
{
    return createComponentDefinition(defaultURIprefix, displayId, u""_j, types);
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SBOLDocument::createComponentDefinition(::java::lang::String* displayId, ::java::lang::String* version, ::java::util::Set* types)
{
    return createComponentDefinition(defaultURIprefix, displayId, version, types);
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SBOLDocument::createComponentDefinition(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version, ::java::util::Set* types)
{
    checkReadOnly();
    URIprefix = checkURIprefix(URIprefix);
    URIcompliance::validateIdVersion(displayId, version);
    auto cd = createComponentDefinition(URIcompliance::createCompliantURI(URIprefix, TopLevel::COMPONENT_DEFINITION(), displayId, version, typesInURIs), types);
    npc(cd)->setDisplayId(displayId);
    npc(cd)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, TopLevel::COMPONENT_DEFINITION(), displayId, u""_j, typesInURIs));
    npc(cd)->setVersion(version);
    return cd;
}

void org::sbolstandard::core2::SBOLDocument::addComponentDefinition(ComponentDefinition* newComponentDefinition)
{
    addTopLevel(newComponentDefinition, componentDefinitions, u"componentDefinition"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(collections), static_cast< ::java::util::Map* >(genericTopLevels), static_cast< ::java::util::Map* >(models), static_cast< ::java::util::Map* >(moduleDefinitions), static_cast< ::java::util::Map* >(sequences)}));
    for (auto _i = npc(npc(newComponentDefinition)->getComponents())->iterator(); _i->hasNext(); ) {
        Component* component = java_cast< Component* >(_i->next());
        {
            npc(component)->setSBOLDocument(this);
            for (auto _i = npc(npc(component)->getMapsTos())->iterator(); _i->hasNext(); ) {
                MapsTo* mapsTo = java_cast< MapsTo* >(_i->next());
                {
                    npc(mapsTo)->setSBOLDocument(this);
                }
            }
        }
    }
    for (auto _i = npc(npc(newComponentDefinition)->getSequenceAnnotations())->iterator(); _i->hasNext(); ) {
        SequenceAnnotation* sequenceAnnotation = java_cast< SequenceAnnotation* >(_i->next());
        {
            npc(sequenceAnnotation)->setSBOLDocument(this);
            for (auto _i = npc(npc(sequenceAnnotation)->getLocations())->iterator(); _i->hasNext(); ) {
                Location* location = java_cast< Location* >(_i->next());
                {
                    npc(location)->setSBOLDocument(this);
                }
            }
        }
    }
    for (auto _i = npc(npc(newComponentDefinition)->getSequenceConstraints())->iterator(); _i->hasNext(); ) {
        SequenceConstraint* sequenceConstraint = java_cast< SequenceConstraint* >(_i->next());
        {
            npc(sequenceConstraint)->setSBOLDocument(this);
        }
    }
}

bool org::sbolstandard::core2::SBOLDocument::removeComponentDefinition(ComponentDefinition* componentDefinition)
{
    checkReadOnly();
    if(complete) {
        for (auto _i = npc(npc(componentDefinitions)->values())->iterator(); _i->hasNext(); ) {
            ComponentDefinition* cd = java_cast< ComponentDefinition* >(_i->next());
            {
                for (auto _i = npc(npc(cd)->getComponents())->iterator(); _i->hasNext(); ) {
                    Component* c = java_cast< Component* >(_i->next());
                    {
                        if(npc(npc(c)->getDefinitionURI())->equals(static_cast< ::java::lang::Object* >(npc(componentDefinition)->getIdentity()))) {
                            throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(componentDefinition)->getIdentity()))
                                ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                        }
                    }
                }
            }
        }
        for (auto _i = npc(npc(moduleDefinitions)->values())->iterator(); _i->hasNext(); ) {
            ModuleDefinition* md = java_cast< ModuleDefinition* >(_i->next());
            {
                for (auto _i = npc(npc(md)->getFunctionalComponents())->iterator(); _i->hasNext(); ) {
                    FunctionalComponent* c = java_cast< FunctionalComponent* >(_i->next());
                    {
                        if(npc(npc(c)->getDefinitionURI())->equals(static_cast< ::java::lang::Object* >(npc(componentDefinition)->getIdentity()))) {
                            throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(componentDefinition)->getIdentity()))
                                ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                        }
                    }
                }
            }
        }
    }
    return removeTopLevel(componentDefinition, componentDefinitions);
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SBOLDocument::getComponentDefinition(::java::lang::String* displayId, ::java::lang::String* version)
{
    validateIdentityData(displayId, version);
    return java_cast< ComponentDefinition* >(npc(componentDefinitions)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(defaultURIprefix, TopLevel::COMPONENT_DEFINITION(), displayId, version, typesInURIs))));
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SBOLDocument::getComponentDefinition(::java::net::URI* componentDefinitionURI)
{
    return java_cast< ComponentDefinition* >(npc(componentDefinitions)->get(static_cast< ::java::lang::Object* >(componentDefinitionURI)));
}

java::util::Set* org::sbolstandard::core2::SBOLDocument::getComponentDefinitions()
{
    ::java::util::Set* components = new ::java::util::HashSet();
    npc(components)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->componentDefinitions))->values()));
    return components;
}

void org::sbolstandard::core2::SBOLDocument::clearComponentDefinitions()
{
    auto valueSetArray_ = npc(npc(componentDefinitions)->values())->toArray_();
    for(auto componentDefinition : *npc(valueSetArray_)) {
        removeComponentDefinition(java_cast< ComponentDefinition* >(componentDefinition));
    }
}

void org::sbolstandard::core2::SBOLDocument::setComponentDefinitions(::java::util::List* componentDefinitions)
{
    checkReadOnly();
    clearComponentDefinitions();
    for (auto _i = npc(componentDefinitions)->iterator(); _i->hasNext(); ) {
        ComponentDefinition* componentDefinition = java_cast< ComponentDefinition* >(_i->next());
        {
            addComponentDefinition(componentDefinition);
        }
    }
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::SBOLDocument::createSequence(::java::net::URI* identity, ::java::lang::String* elements, ::java::net::URI* encoding)
{
    auto newSequence = new Sequence(identity, elements, encoding);
    addSequence(newSequence);
    return newSequence;
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::SBOLDocument::createSequence(::java::lang::String* displayId, ::java::lang::String* elements, ::java::net::URI* encoding)
{
    return createSequence(defaultURIprefix, displayId, u""_j, elements, encoding);
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::SBOLDocument::createSequence(::java::lang::String* displayId, ::java::lang::String* version, ::java::lang::String* elements, ::java::net::URI* encoding)
{
    return createSequence(defaultURIprefix, displayId, version, elements, encoding);
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::SBOLDocument::createSequence(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version, ::java::lang::String* elements, ::java::net::URI* encoding)
{
    checkReadOnly();
    URIprefix = checkURIprefix(URIprefix);
    URIcompliance::validateIdVersion(displayId, version);
    auto s = createSequence(URIcompliance::createCompliantURI(URIprefix, TopLevel::SEQUENCE(), displayId, version, typesInURIs), elements, encoding);
    npc(s)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, TopLevel::SEQUENCE(), displayId, u""_j, typesInURIs));
    npc(s)->setDisplayId(displayId);
    npc(s)->setVersion(version);
    return s;
}

org::sbolstandard::core2::TopLevel* org::sbolstandard::core2::SBOLDocument::createCopy(TopLevel* topLevel)
{
    return createCopy(topLevel, nullptr, nullptr, nullptr);
}

org::sbolstandard::core2::TopLevel* org::sbolstandard::core2::SBOLDocument::createCopy(TopLevel* topLevel, ::java::lang::String* displayId)
{
    return createCopy(topLevel, defaultURIprefix, displayId, u""_j);
}

org::sbolstandard::core2::TopLevel* org::sbolstandard::core2::SBOLDocument::createCopy(TopLevel* topLevel, ::java::lang::String* displayId, ::java::lang::String* version)
{
    return createCopy(topLevel, defaultURIprefix, displayId, version);
}

org::sbolstandard::core2::TopLevel* org::sbolstandard::core2::SBOLDocument::createCopy(TopLevel* topLevel, ::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    checkReadOnly();
    if(!URIcompliance::isTopLevelURIcompliant(topLevel)) {
        throw new SBOLValidationException(u"Cannot copy a non-compliant SBOL object"_j, new IdentifiedArray());
    }
    if(URIprefix == nullptr) {
        URIprefix = URIcompliance::extractURIprefix(npc(topLevel)->getIdentity());
        URIprefix = checkURIprefix(URIprefix);
    } else {
        URIprefix = checkURIprefix(URIprefix);
    }
    if(displayId == nullptr) {
        displayId = npc(topLevel)->getDisplayId();
    }
    if(version == nullptr) {
        version = npc(topLevel)->getVersion();
    }
    URIcompliance::validateIdVersion(displayId, version);
    if(dynamic_cast< Collection* >(topLevel) != nullptr) {
        auto newCollection = npc((java_cast< Collection* >(topLevel)))->copy(URIprefix, displayId, version);
        addCollection(newCollection);
        return newCollection;
    } else if(dynamic_cast< ComponentDefinition* >(topLevel) != nullptr) {
        auto newComponentDefinition = npc((java_cast< ComponentDefinition* >(topLevel)))->copy(URIprefix, displayId, version);
        addComponentDefinition(newComponentDefinition);
        return newComponentDefinition;
    } else if(dynamic_cast< Model* >(topLevel) != nullptr) {
        auto newModel = npc((java_cast< Model* >(topLevel)))->copy(URIprefix, displayId, version);
        addModel(newModel);
        return newModel;
    } else if(dynamic_cast< ModuleDefinition* >(topLevel) != nullptr) {
        auto newModuleDefinition = npc((java_cast< ModuleDefinition* >(topLevel)))->copy(URIprefix, displayId, version);
        addModuleDefinition(newModuleDefinition);
        return newModuleDefinition;
    } else if(dynamic_cast< Sequence* >(topLevel) != nullptr) {
        auto newSequence = npc((java_cast< Sequence* >(topLevel)))->copy(URIprefix, displayId, version);
        addSequence(newSequence);
        return newSequence;
    } else if(dynamic_cast< GenericTopLevel* >(topLevel) != nullptr) {
        auto newGenericTopLevel = npc((java_cast< GenericTopLevel* >(topLevel)))->copy(URIprefix, displayId, version);
        addGenericTopLevel(newGenericTopLevel);
        return newGenericTopLevel;
    } else {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Unable to copy "_j)->append(static_cast< ::java::lang::Object* >(npc(topLevel)->getIdentity()))->toString());
    }
}

void org::sbolstandard::core2::SBOLDocument::addSequence(Sequence* newSequence)
{
    addTopLevel(newSequence, sequences, u"sequence"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(collections), static_cast< ::java::util::Map* >(componentDefinitions), static_cast< ::java::util::Map* >(genericTopLevels), static_cast< ::java::util::Map* >(models), static_cast< ::java::util::Map* >(moduleDefinitions)}));
}

bool org::sbolstandard::core2::SBOLDocument::removeSequence(Sequence* sequence)
{
    checkReadOnly();
    if(complete) {
        for (auto _i = npc(npc(componentDefinitions)->values())->iterator(); _i->hasNext(); ) {
            ComponentDefinition* cd = java_cast< ComponentDefinition* >(_i->next());
            {
                if(npc(cd)->containsSequence(npc(sequence)->getIdentity())) {
                    throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(sequence)->getIdentity()))
                        ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                }
            }
        }
    }
    return removeTopLevel(sequence, sequences);
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::SBOLDocument::getSequence(::java::lang::String* displayId, ::java::lang::String* version)
{
    validateIdentityData(displayId, version);
    return java_cast< Sequence* >(npc(sequences)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(defaultURIprefix, TopLevel::SEQUENCE(), displayId, version, typesInURIs))));
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::SBOLDocument::getSequence(::java::net::URI* sequenceURI)
{
    return java_cast< Sequence* >(npc(sequences)->get(static_cast< ::java::lang::Object* >(sequenceURI)));
}

java::util::Set* org::sbolstandard::core2::SBOLDocument::getSequences()
{
    ::java::util::Set* structures = new ::java::util::HashSet();
    npc(structures)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->sequences))->values()));
    return structures;
}

void org::sbolstandard::core2::SBOLDocument::clearSequences()
{
    checkReadOnly();
    auto valueSetArray_ = npc(npc(sequences)->values())->toArray_();
    for(auto sequence : *npc(valueSetArray_)) {
        removeSequence(java_cast< Sequence* >(sequence));
    }
}

void org::sbolstandard::core2::SBOLDocument::setSequences(::java::util::List* sequences)
{
    clearSequences();
    for (auto _i = npc(sequences)->iterator(); _i->hasNext(); ) {
        Sequence* sequence = java_cast< Sequence* >(_i->next());
        {
            addSequence(sequence);
        }
    }
}

org::sbolstandard::core2::GenericTopLevel* org::sbolstandard::core2::SBOLDocument::createGenericTopLevel(::java::lang::String* displayId, ::javax::xml::namespace_::QName* rdfType)
{
    return createGenericTopLevel(defaultURIprefix, displayId, u""_j, rdfType);
}

org::sbolstandard::core2::GenericTopLevel* org::sbolstandard::core2::SBOLDocument::createGenericTopLevel(::java::lang::String* displayId, ::java::lang::String* version, ::javax::xml::namespace_::QName* rdfType)
{
    return createGenericTopLevel(defaultURIprefix, displayId, version, rdfType);
}

org::sbolstandard::core2::GenericTopLevel* org::sbolstandard::core2::SBOLDocument::createGenericTopLevel(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version, ::javax::xml::namespace_::QName* rdfType)
{
    checkReadOnly();
    URIprefix = checkURIprefix(URIprefix);
    URIcompliance::validateIdVersion(displayId, version);
    auto g = createGenericTopLevel(URIcompliance::createCompliantURI(URIprefix, TopLevel::GENERIC_TOP_LEVEL(), displayId, version, typesInURIs), rdfType);
    npc(g)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, TopLevel::GENERIC_TOP_LEVEL(), displayId, u""_j, typesInURIs));
    npc(g)->setDisplayId(displayId);
    npc(g)->setVersion(version);
    return g;
}

org::sbolstandard::core2::GenericTopLevel* org::sbolstandard::core2::SBOLDocument::createGenericTopLevel(::java::net::URI* identity, ::javax::xml::namespace_::QName* rdfType)
{
    if(npc(npc(npc(rdfType)->getPrefix())->toString())->equals(static_cast< ::java::lang::Object* >(u"sbol"_j))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(npc(rdfType)->getLocalPart())->append(u" is not an SBOL object, so it cannot be in the SBOL namespace."_j)->toString(), new IdentifiedArray());
    }
    auto newGenericTopLevel = new GenericTopLevel(identity, rdfType);
    addGenericTopLevel(newGenericTopLevel);
    return newGenericTopLevel;
}

void org::sbolstandard::core2::SBOLDocument::addGenericTopLevel(GenericTopLevel* newGenericTopLevel)
{
    addTopLevel(newGenericTopLevel, genericTopLevels, u"genericTopLevel"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(collections), static_cast< ::java::util::Map* >(componentDefinitions), static_cast< ::java::util::Map* >(models), static_cast< ::java::util::Map* >(moduleDefinitions), static_cast< ::java::util::Map* >(sequences)}));
}

bool org::sbolstandard::core2::SBOLDocument::removeGenericTopLevel(GenericTopLevel* genericTopLevel)
{
    checkReadOnly();
    return removeTopLevel(genericTopLevel, genericTopLevels);
}

org::sbolstandard::core2::GenericTopLevel* org::sbolstandard::core2::SBOLDocument::getGenericTopLevel(::java::lang::String* displayId, ::java::lang::String* version)
{
    validateIdentityData(displayId, version);
    return java_cast< GenericTopLevel* >(npc(genericTopLevels)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(defaultURIprefix, TopLevel::GENERIC_TOP_LEVEL(), displayId, version, typesInURIs))));
}

org::sbolstandard::core2::GenericTopLevel* org::sbolstandard::core2::SBOLDocument::getGenericTopLevel(::java::net::URI* topLevelURI)
{
    return java_cast< GenericTopLevel* >(npc(genericTopLevels)->get(static_cast< ::java::lang::Object* >(topLevelURI)));
}

java::util::Set* org::sbolstandard::core2::SBOLDocument::getGenericTopLevels()
{
    ::java::util::Set* topLevels = new ::java::util::HashSet();
    npc(topLevels)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->genericTopLevels))->values()));
    return topLevels;
}

void org::sbolstandard::core2::SBOLDocument::clearGenericTopLevels()
{
    checkReadOnly();
    auto valueSetArray_ = npc(npc(genericTopLevels)->values())->toArray_();
    for(auto genericTopLevel : *npc(valueSetArray_)) {
        removeGenericTopLevel(java_cast< GenericTopLevel* >(genericTopLevel));
    }
}

void org::sbolstandard::core2::SBOLDocument::setGenericTopLevels(::java::util::List* topLevels)
{
    clearGenericTopLevels();
    for (auto _i = npc(topLevels)->iterator(); _i->hasNext(); ) {
        GenericTopLevel* topLevel = java_cast< GenericTopLevel* >(_i->next());
        {
            addGenericTopLevel(topLevel);
        }
    }
}

org::sbolstandard::core2::TopLevel* org::sbolstandard::core2::SBOLDocument::getTopLevel(::java::net::URI* topLevelURI)
{
    TopLevel* topLevel = java_cast< Collection* >(npc(collections)->get(static_cast< ::java::lang::Object* >(topLevelURI)));
    if(topLevel != nullptr) {
        return topLevel;
    }
    topLevel = java_cast< ModuleDefinition* >(npc(moduleDefinitions)->get(static_cast< ::java::lang::Object* >(topLevelURI)));
    if(topLevel != nullptr) {
        return topLevel;
    }
    topLevel = java_cast< Model* >(npc(models)->get(static_cast< ::java::lang::Object* >(topLevelURI)));
    if(topLevel != nullptr) {
        return topLevel;
    }
    topLevel = java_cast< ComponentDefinition* >(npc(componentDefinitions)->get(static_cast< ::java::lang::Object* >(topLevelURI)));
    if(topLevel != nullptr) {
        return topLevel;
    }
    topLevel = java_cast< Sequence* >(npc(sequences)->get(static_cast< ::java::lang::Object* >(topLevelURI)));
    if(topLevel != nullptr) {
        return topLevel;
    }
    topLevel = java_cast< GenericTopLevel* >(npc(genericTopLevels)->get(static_cast< ::java::lang::Object* >(topLevelURI)));
    if(topLevel != nullptr) {
        return topLevel;
    }
    return nullptr;
}

void org::sbolstandard::core2::SBOLDocument::addNamespace(::java::net::URI* nameSpaceURI, ::java::lang::String* prefix)
{
    npc(nameSpaces)->put(static_cast< ::java::lang::Object* >(nameSpaceURI), static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(npc(nameSpaceURI)->toString(), prefix)));
}

void org::sbolstandard::core2::SBOLDocument::addNamespace(::javax::xml::namespace_::QName* qName)
{
    npc(nameSpaces)->put(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(qName)->getNamespaceURI())), static_cast< ::java::lang::Object* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(npc(qName)->getNamespaceURI(), npc(qName)->getPrefix())));
}

void org::sbolstandard::core2::SBOLDocument::addNamespaceBinding(::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* namespaceBinding)
{
    npc(nameSpaces)->put(static_cast< ::java::lang::Object* >(::java::net::URI::create(npc(namespaceBinding)->getNamespaceURI())), static_cast< ::java::lang::Object* >(namespaceBinding));
}

void org::sbolstandard::core2::SBOLDocument::clearNamespaces()
{
    checkReadOnly();
    auto keySetArray_ = npc(npc(nameSpaces)->keySet())->toArray_();
    for(auto key : *npc(keySetArray_)) {
        if(isRequiredNamespaceBinding(java_cast< ::java::net::URI* >(key)))
            continue;

        removeNamespace(java_cast< ::java::net::URI* >(key));
    }
}

javax::xml::namespace_::QName* org::sbolstandard::core2::SBOLDocument::getNamespace(::java::net::URI* namespaceURI)
{
    if(java_cast< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* >(npc(nameSpaces)->get(static_cast< ::java::lang::Object* >(namespaceURI))) == nullptr)
        return nullptr;

    return new ::javax::xml::namespace_::QName(npc(namespaceURI)->toString(), u""_j, npc(java_cast< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* >(npc(nameSpaces)->get(static_cast< ::java::lang::Object* >(namespaceURI))))->getPrefix());
}

java::util::List* org::sbolstandard::core2::SBOLDocument::getNamespaces()
{
    ::java::util::List* bindings = new ::java::util::ArrayList();
    for (auto _i = npc(npc(java_cast< ::java::util::HashMap* >(this->nameSpaces))->values())->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* namespaceBinding = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* >(_i->next());
        {
            npc(bindings)->add(static_cast< ::java::lang::Object* >(new ::javax::xml::namespace_::QName(npc(namespaceBinding)->getNamespaceURI(), u""_j, npc(namespaceBinding)->getPrefix())));
        }
    }
    return bindings;
}

java::util::List* org::sbolstandard::core2::SBOLDocument::getNamespaceBindings()
{
    ::java::util::List* bindings = new ::java::util::ArrayList();
    npc(bindings)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->nameSpaces))->values()));
    return bindings;
}

void org::sbolstandard::core2::SBOLDocument::removeNamespace(::java::net::URI* namespaceURI)
{
    checkReadOnly();
    if(isRequiredNamespaceBinding(namespaceURI)) {
        throw new ::java::lang::IllegalStateException(::java::lang::StringBuilder().append(u"Cannot remove required namespace "_j)->append(npc(namespaceURI)->toString())->toString());
    }
    npc(nameSpaces)->remove(static_cast< ::java::lang::Object* >(namespaceURI));
}

void org::sbolstandard::core2::SBOLDocument::setNameSpaceBindings(::java::util::List* namespaceBinding)
{
    clearNamespaces();
    for (auto _i = npc(namespaceBinding)->iterator(); _i->hasNext(); ) {
        ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* namespace_ = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* >(_i->next());
        {
            addNamespaceBinding(namespace_);
        }
    }
}

bool org::sbolstandard::core2::SBOLDocument::isRequiredNamespaceBinding(::java::net::URI* namespaceURI)
{
    if(npc(npc(namespaceURI)->toString())->equals(static_cast< ::java::lang::Object* >(npc(Sbol2Terms::sbol2())->getNamespaceURI())))
        return true;

    if(npc(npc(namespaceURI)->toString())->equals(static_cast< ::java::lang::Object* >(npc(Sbol2Terms::dc())->getNamespaceURI())))
        return true;

    if(npc(npc(namespaceURI)->toString())->equals(static_cast< ::java::lang::Object* >(npc(Sbol2Terms::prov())->getNamespaceURI())))
        return true;

    if(npc(npc(namespaceURI)->toString())->equals(static_cast< ::java::lang::Object* >(npc(Sbol1Terms::rdf())->getNamespaceURI())))
        return true;

    return false;
}

int32_t org::sbolstandard::core2::SBOLDocument::hashCode()
{
    auto const prime = int32_t(31);
    auto result = int32_t(1);
    result = prime * result + ((collections == nullptr) ? int32_t(0) : npc(collections)->hashCode());
    result = prime * result + ((componentDefinitions == nullptr) ? int32_t(0) : npc(componentDefinitions)->hashCode());
    result = prime * result + ((genericTopLevels == nullptr) ? int32_t(0) : npc(genericTopLevels)->hashCode());
    result = prime * result + ((models == nullptr) ? int32_t(0) : npc(models)->hashCode());
    result = prime * result + ((moduleDefinitions == nullptr) ? int32_t(0) : npc(moduleDefinitions)->hashCode());
    result = prime * result + ((nameSpaces == nullptr) ? int32_t(0) : npc(nameSpaces)->hashCode());
    result = prime * result + ((sequences == nullptr) ? int32_t(0) : npc(sequences)->hashCode());
    return result;
}

bool org::sbolstandard::core2::SBOLDocument::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(obj == nullptr)
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< SBOLDocument* >(obj);
    if(collections == nullptr) {
        if(npc(other)->collections != nullptr)
            return false;

    } else if(!npc(collections)->equals(static_cast< ::java::lang::Object* >(npc(other)->collections)))
        return false;

    if(componentDefinitions == nullptr) {
        if(npc(other)->componentDefinitions != nullptr)
            return false;

    } else if(!npc(componentDefinitions)->equals(static_cast< ::java::lang::Object* >(npc(other)->componentDefinitions)))
        return false;

    if(genericTopLevels == nullptr) {
        if(npc(other)->genericTopLevels != nullptr)
            return false;

    } else if(!npc(genericTopLevels)->equals(static_cast< ::java::lang::Object* >(npc(other)->genericTopLevels)))
        return false;

    if(models == nullptr) {
        if(npc(other)->models != nullptr)
            return false;

    } else if(!npc(models)->equals(static_cast< ::java::lang::Object* >(npc(other)->models)))
        return false;

    if(moduleDefinitions == nullptr) {
        if(npc(other)->moduleDefinitions != nullptr)
            return false;

    } else if(!npc(moduleDefinitions)->equals(static_cast< ::java::lang::Object* >(npc(other)->moduleDefinitions)))
        return false;

    if(nameSpaces == nullptr) {
        if(npc(other)->nameSpaces != nullptr)
            return false;

    } else if(!npc(nameSpaces)->equals(static_cast< ::java::lang::Object* >(npc(other)->nameSpaces)))
        return false;

    if(sequences == nullptr) {
        if(npc(other)->sequences != nullptr)
            return false;

    } else if(!npc(sequences)->equals(static_cast< ::java::lang::Object* >(npc(other)->sequences)))
        return false;

    return true;
}

void org::sbolstandard::core2::SBOLDocument::validateIdentityData(::java::lang::String* displayId, ::java::lang::String* version)
{
    URIcompliance::validateIdVersion(displayId, version);
    if(defaultURIprefix == nullptr) {
        throw new ::java::lang::IllegalStateException(u"The defaultURIprefix is not set. Please set it to a non-null value"_j);
    }
}

/* <TL extends TopLevel> */void org::sbolstandard::core2::SBOLDocument::addTopLevel(TopLevel* newTopLevel, ::java::util::Map* instancesMap, ::java::lang::String* typeName, ::java::util::MapArray*/*...*/ maps)
{
    if(npc(newTopLevel)->checkDescendantsURIcompliance()) {
        auto persistentId = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(newTopLevel)->getIdentity()));
        if(URIcompliance::keyExistsInAnyMap(persistentId, maps))
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Instance for identity `"_j)->append(static_cast< ::java::lang::Object* >(npc(newTopLevel)->identity))
                ->append(u"' and persistent identity `"_j)
                ->append(static_cast< ::java::lang::Object* >(persistentId))
                ->append(u"' exists for a non-"_j)
                ->append(typeName)->toString());

        if(npc(instancesMap)->containsKey(npc(newTopLevel)->getIdentity()))
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Instance for identity `"_j)->append(static_cast< ::java::lang::Object* >(npc(newTopLevel)->identity))
                ->append(u"' and persistent identity `"_j)
                ->append(static_cast< ::java::lang::Object* >(persistentId))
                ->append(u"' already exists for a "_j)
                ->append(typeName)->toString());

        npc(instancesMap)->put(npc(newTopLevel)->getIdentity(), newTopLevel);
        Identified* latest = java_cast< TopLevel* >(npc(instancesMap)->get(persistentId));
        if(latest == nullptr) {
            npc(instancesMap)->put(persistentId, newTopLevel);
        } else {
            if(Version::isFirstVersionNewer(URIcompliance::extractVersion(npc(newTopLevel)->getIdentity()), URIcompliance::extractVersion(npc(latest)->getIdentity()))) {
                npc(instancesMap)->put(persistentId, newTopLevel);
            }
        }
    } else {
        if(URIcompliance::keyExistsInAnyMap(npc(newTopLevel)->getIdentity(), new ::java::util::MapArray()))
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Instance for identity `"_j)->append(static_cast< ::java::lang::Object* >(npc(newTopLevel)->identity))
                ->append(u"' exists for a non-"_j)
                ->append(typeName)->toString());

        if(npc(instancesMap)->containsKey(npc(newTopLevel)->getIdentity()))
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Instance for identity `"_j)->append(static_cast< ::java::lang::Object* >(npc(newTopLevel)->identity))
                ->append(u"' exists for a "_j)
                ->append(typeName)->toString());

        npc(instancesMap)->put(npc(newTopLevel)->getIdentity(), newTopLevel);
    }
    npc(newTopLevel)->setSBOLDocument(this);
}

/* <TL extends TopLevel> */bool org::sbolstandard::core2::SBOLDocument::removeTopLevel(TopLevel* topLevel, ::java::util::Map* instancesMap)
{
    if(complete) {
        for (auto _i = npc(npc(collections)->values())->iterator(); _i->hasNext(); ) {
            Collection* c = java_cast< Collection* >(_i->next());
            {
                if(npc(c)->containsMember(npc(topLevel)->getIdentity())) {
                    throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(topLevel)->getIdentity()))
                        ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                }
            }
        }
    }
    ::java::util::Set* setToRemove = new ::java::util::HashSet();
    npc(setToRemove)->add(static_cast< ::java::lang::Object* >(topLevel));
    auto changed = npc(npc(instancesMap)->values())->removeAll(setToRemove);
    ::java::net::URI* latestVersion = nullptr;
    for (auto _i = npc(npc(instancesMap)->values())->iterator(); _i->hasNext(); ) {
        TopLevel* tl = java_cast< TopLevel* >(_i->next());
        {
            if(npc(npc(npc(topLevel)->getPersistentIdentity())->toString())->equals(static_cast< ::java::lang::Object* >(npc(npc(tl)->getPersistentIdentity())->toString()))) {
                if(latestVersion == nullptr) {
                    latestVersion = npc(tl)->getIdentity();
                } else if(Version::isFirstVersionNewer(URIcompliance::extractVersion(npc(tl)->getIdentity()), URIcompliance::extractVersion(latestVersion))) {
                    latestVersion = npc(tl)->getIdentity();
                }
            }
        }
    }
    if(latestVersion != nullptr) {
        npc(instancesMap)->put(npc(topLevel)->getPersistentIdentity(), java_cast< TopLevel* >(npc(instancesMap)->get(latestVersion)));
    }
    return changed;
}

java::lang::String* org::sbolstandard::core2::SBOLDocument::checkURIprefix(::java::lang::String* URIprefix)
{
    if(URIprefix == nullptr) {
        throw new ::java::lang::IllegalArgumentException(u"URI prefix must not be null"_j);
    }
    if(!npc(URIprefix)->endsWith(u"/"_j) && !npc(URIprefix)->endsWith(u":"_j) && !npc(URIprefix)->endsWith(u"#"_j)) {
        URIprefix = ::java::lang::StringBuilder(URIprefix).append(u"/"_j)->toString();
    }
    if(!URIcompliance::isURIprefixCompliant(URIprefix)) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"URI prefix '"_j)->append(URIprefix)
            ->append(u"' is invalid"_j)->toString());
    }
    return URIprefix;
}

void org::sbolstandard::core2::SBOLDocument::setDefaultURIprefix(::java::lang::String* defaultURIprefix)
{
    if(!npc(defaultURIprefix)->endsWith(u"/"_j) && !npc(defaultURIprefix)->endsWith(u":"_j) && !npc(defaultURIprefix)->endsWith(u"#"_j)) {
        defaultURIprefix = ::java::lang::StringBuilder(defaultURIprefix).append(u"/"_j)->toString();
    }
    if(URIcompliance::isURIprefixCompliant(defaultURIprefix)) {
        this->defaultURIprefix = defaultURIprefix;
    } else {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Unable to set default URI prefix to non-compliant value `"_j)->append(defaultURIprefix)
            ->append(u"'"_j)->toString());
    }
}

java::lang::String* org::sbolstandard::core2::SBOLDocument::getDefaultURIprefix()
{
    return defaultURIprefix;
}

bool org::sbolstandard::core2::SBOLDocument::isComplete()
{
    return complete;
}

void org::sbolstandard::core2::SBOLDocument::setComplete(bool complete)
{
    this->complete = complete;
}

bool org::sbolstandard::core2::SBOLDocument::isCompliant()
{
    return compliant;
}

void org::sbolstandard::core2::SBOLDocument::setCompliant(bool compliant)
{
    this->compliant = compliant;
}

bool org::sbolstandard::core2::SBOLDocument::isTypesInURIs()
{
    return typesInURIs;
}

void org::sbolstandard::core2::SBOLDocument::setTypesInURIs(bool typesInURIs)
{
    this->typesInURIs = typesInURIs;
}

bool org::sbolstandard::core2::SBOLDocument::isCreateDefaults()
{
    return createDefaults;
}

void org::sbolstandard::core2::SBOLDocument::setCreateDefaults(bool createDefaults)
{
    this->createDefaults = createDefaults;
}

void org::sbolstandard::core2::SBOLDocument::checkReadOnly()
{
    if(!compliant) {
        throw new SBOLValidationException(u"Cannot modify a non-compliant SBOL document"_j, new IdentifiedArray());
    }
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SBOLDocument::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SBOLDocument", 35);
    return c;
}

java::lang::Class* org::sbolstandard::core2::SBOLDocument::getClass0()
{
    return class_();
}


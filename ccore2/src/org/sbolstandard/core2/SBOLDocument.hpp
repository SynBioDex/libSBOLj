// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLDocument.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
typedef ::SubArray< ::java::util::Map, ::java::lang::ObjectArray > MapArray;
    } // util
} // java

struct default_init_tag;

class org::sbolstandard::core2::SBOLDocument
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    ::java::util::HashMap* genericTopLevels {  };
    ::java::util::HashMap* collections {  };
    ::java::util::HashMap* componentDefinitions {  };
    ::java::util::HashMap* models {  };
    ::java::util::HashMap* moduleDefinitions {  };
    ::java::util::HashMap* sequences {  };
    ::java::util::HashMap* nameSpaces {  };
    ::java::lang::String* defaultURIprefix {  };
    bool complete {  };
    bool compliant {  };
    bool typesInURIs {  };
    bool createDefaults {  };
protected:
    void ctor();

public:
    virtual ModuleDefinition* createModuleDefinition(::java::lang::String* displayId);
    virtual ModuleDefinition* createModuleDefinition(::java::lang::String* displayId, ::java::lang::String* version);
    virtual ModuleDefinition* createModuleDefinition(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);

public: /* package */
    virtual ModuleDefinition* createModuleDefinition(::java::net::URI* identity);
    virtual void addModuleDefinition(ModuleDefinition* newModuleDefinition);

public:
    virtual bool removeModuleDefinition(ModuleDefinition* moduleDefinition);
    virtual ModuleDefinition* getModuleDefinition(::java::lang::String* displayId, ::java::lang::String* version);
    virtual ModuleDefinition* getModuleDefinition(::java::net::URI* moduleURI);
    virtual ::java::util::Set* getModuleDefinitions();
    virtual void clearModuleDefinitions();

public: /* package */
    virtual void setModuleDefinitions(::java::util::List* moduleDefinitions);
    virtual Collection* createCollection(::java::net::URI* identity);

public:
    virtual Collection* createCollection(::java::lang::String* displayId);
    virtual Collection* createCollection(::java::lang::String* displayId, ::java::lang::String* version);
    virtual Collection* createCollection(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);

public: /* package */
    virtual void addCollection(Collection* collection);

public:
    virtual bool removeCollection(Collection* collection);
    virtual Collection* getCollection(::java::lang::String* displayId, ::java::lang::String* version);
    virtual Collection* getCollection(::java::net::URI* collectionURI);
    virtual ::java::util::Set* getCollections();
    virtual void clearCollections();

public: /* package */
    virtual void setCollections(::java::util::List* collections);

public:
    virtual Model* createModel(::java::lang::String* displayId, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework);
    virtual Model* createModel(::java::lang::String* displayId, ::java::lang::String* version, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework);
    virtual Model* createModel(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework);

public: /* package */
    virtual Model* createModel(::java::net::URI* identity, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework);
    virtual void addModel(Model* newModel);

public:
    virtual bool removeModel(Model* model);
    virtual Model* getModel(::java::lang::String* displayId, ::java::lang::String* version);
    virtual Model* getModel(::java::net::URI* modelURI);
    virtual ::java::util::Set* getModels();
    virtual void clearModels();

public: /* package */
    virtual void setModels(::java::util::List* models);
    virtual ComponentDefinition* createComponentDefinition(::java::net::URI* identity, ::java::util::Set* types);

public:
    virtual ComponentDefinition* createComponentDefinition(::java::lang::String* displayId, ::java::util::Set* types);
    virtual ComponentDefinition* createComponentDefinition(::java::lang::String* displayId, ::java::lang::String* version, ::java::util::Set* types);
    virtual ComponentDefinition* createComponentDefinition(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version, ::java::util::Set* types);

public: /* package */
    virtual void addComponentDefinition(ComponentDefinition* newComponentDefinition);

public:
    virtual bool removeComponentDefinition(ComponentDefinition* componentDefinition);
    virtual ComponentDefinition* getComponentDefinition(::java::lang::String* displayId, ::java::lang::String* version);
    virtual ComponentDefinition* getComponentDefinition(::java::net::URI* componentDefinitionURI);
    virtual ::java::util::Set* getComponentDefinitions();
    virtual void clearComponentDefinitions();

public: /* package */
    virtual void setComponentDefinitions(::java::util::List* componentDefinitions);
    virtual Sequence* createSequence(::java::net::URI* identity, ::java::lang::String* elements, ::java::net::URI* encoding);

public:
    virtual Sequence* createSequence(::java::lang::String* displayId, ::java::lang::String* elements, ::java::net::URI* encoding);
    virtual Sequence* createSequence(::java::lang::String* displayId, ::java::lang::String* version, ::java::lang::String* elements, ::java::net::URI* encoding);
    virtual Sequence* createSequence(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version, ::java::lang::String* elements, ::java::net::URI* encoding);
    virtual TopLevel* createCopy(TopLevel* topLevel);
    virtual TopLevel* createCopy(TopLevel* topLevel, ::java::lang::String* displayId);
    virtual TopLevel* createCopy(TopLevel* topLevel, ::java::lang::String* displayId, ::java::lang::String* version);
    virtual TopLevel* createCopy(TopLevel* topLevel, ::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);

public: /* package */
    virtual void addSequence(Sequence* newSequence);

public:
    virtual bool removeSequence(Sequence* sequence);
    virtual Sequence* getSequence(::java::lang::String* displayId, ::java::lang::String* version);
    virtual Sequence* getSequence(::java::net::URI* sequenceURI);
    virtual ::java::util::Set* getSequences();
    virtual void clearSequences();

public: /* package */
    virtual void setSequences(::java::util::List* sequences);

public:
    virtual GenericTopLevel* createGenericTopLevel(::java::lang::String* displayId, ::javax::xml::namespace_::QName* rdfType);
    virtual GenericTopLevel* createGenericTopLevel(::java::lang::String* displayId, ::java::lang::String* version, ::javax::xml::namespace_::QName* rdfType);
    virtual GenericTopLevel* createGenericTopLevel(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version, ::javax::xml::namespace_::QName* rdfType);

public: /* package */
    virtual GenericTopLevel* createGenericTopLevel(::java::net::URI* identity, ::javax::xml::namespace_::QName* rdfType);
    virtual void addGenericTopLevel(GenericTopLevel* newGenericTopLevel);

public:
    virtual bool removeGenericTopLevel(GenericTopLevel* genericTopLevel);
    virtual GenericTopLevel* getGenericTopLevel(::java::lang::String* displayId, ::java::lang::String* version);
    virtual GenericTopLevel* getGenericTopLevel(::java::net::URI* topLevelURI);
    virtual ::java::util::Set* getGenericTopLevels();
    virtual void clearGenericTopLevels();

public: /* package */
    virtual void setGenericTopLevels(::java::util::List* topLevels);

public:
    virtual TopLevel* getTopLevel(::java::net::URI* topLevelURI);
    virtual void addNamespace(::java::net::URI* nameSpaceURI, ::java::lang::String* prefix);
    virtual void addNamespace(::javax::xml::namespace_::QName* qName);

public: /* package */
    virtual void addNamespaceBinding(::uk::ac::ncl::intbio::core::datatree::NamespaceBinding* namespaceBinding);

public:
    virtual void clearNamespaces();
    virtual ::javax::xml::namespace_::QName* getNamespace(::java::net::URI* namespaceURI);
    virtual ::java::util::List* getNamespaces();

public: /* package */
    virtual ::java::util::List* getNamespaceBindings();

public:
    virtual void removeNamespace(::java::net::URI* namespaceURI);

public: /* package */
    virtual void setNameSpaceBindings(::java::util::List* namespaceBinding);
    virtual bool isRequiredNamespaceBinding(::java::net::URI* namespaceURI);

public:
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

private:
    void validateIdentityData(::java::lang::String* displayId, ::java::lang::String* version);
    /* <TL extends TopLevel> */void addTopLevel(TopLevel* newTopLevel, ::java::util::Map* instancesMap, ::java::lang::String* typeName, ::java::util::MapArray*/*...*/ maps);
    /* <TL extends TopLevel> */bool removeTopLevel(TopLevel* topLevel, ::java::util::Map* instancesMap);

public: /* package */
    virtual ::java::lang::String* checkURIprefix(::java::lang::String* URIprefix);

public:
    virtual void setDefaultURIprefix(::java::lang::String* defaultURIprefix);
    virtual ::java::lang::String* getDefaultURIprefix();
    virtual bool isComplete();
    virtual void setComplete(bool complete);
    virtual bool isCompliant();

public: /* package */
    virtual void setCompliant(bool compliant);

public:
    virtual bool isTypesInURIs();
    virtual void setTypesInURIs(bool typesInURIs);
    virtual bool isCreateDefaults();
    virtual void setCreateDefaults(bool createDefaults);

public: /* package */
    virtual void checkReadOnly();

    // Generated

public:
    SBOLDocument();
protected:
    SBOLDocument(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

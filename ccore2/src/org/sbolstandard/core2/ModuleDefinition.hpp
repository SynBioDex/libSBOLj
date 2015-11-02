// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ModuleDefinition.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>

struct default_init_tag;

class org::sbolstandard::core2::ModuleDefinition
    : public TopLevel
{

public:
    typedef TopLevel super;

private:
    ::java::util::Set* roles {  };
    ::java::util::HashMap* modules {  };
    ::java::util::HashMap* interactions {  };
    ::java::util::HashMap* functionalComponents {  };
    ::java::util::Set* models {  };
protected:
    void ctor(::java::net::URI* identity);
    void ctor(ModuleDefinition* moduleDefinition);

public:
    virtual bool addRole(::java::net::URI* roleURI);
    virtual bool removeRole(::java::net::URI* roleURI);
    virtual void setRoles(::java::util::Set* roles);
    virtual ::java::util::Set* getRoles();
    virtual bool containsRole(::java::net::URI* roleURI);
    virtual void clearRoles();

public: /* package */
    virtual Module* createModule(::java::net::URI* identity, ::java::net::URI* moduleDefinitionURI);

public:
    virtual Module* createModule(::java::lang::String* displayId, ::java::lang::String* moduleDefinitionId, ::java::lang::String* version);
    virtual Module* createModule(::java::lang::String* displayId, ::java::net::URI* moduleDefinitionURI);

public: /* package */
    virtual void addModule(Module* module);

public:
    virtual bool removeModule(Module* module);
    virtual Module* getModule(::java::lang::String* displayId);
    virtual Module* getModule(::java::net::URI* moduleURI);
    virtual ::java::util::Set* getModules();
    virtual void clearModules();

public: /* package */
    virtual void setModules(::java::util::List* modules);
    virtual Interaction* createInteraction(::java::net::URI* identity, ::java::util::Set* type);

public:
    virtual Interaction* createInteraction(::java::lang::String* displayId, ::java::util::Set* types);

public: /* package */
    virtual void addInteraction(Interaction* interaction);

public:
    virtual bool removeInteraction(Interaction* interaction);
    virtual Interaction* getInteraction(::java::lang::String* displayId);
    virtual Interaction* getInteraction(::java::net::URI* interactionURI);
    virtual ::java::util::Set* getInteractions();
    virtual void clearInteractions();

public: /* package */
    virtual void setInteractions(::java::util::List* interactions);
    virtual FunctionalComponent* createFunctionalComponent(::java::net::URI* identity, AccessType* access, ::java::net::URI* definitionURI, DirectionType* direction);

public:
    virtual FunctionalComponent* createFunctionalComponent(::java::lang::String* displayId, AccessType* access, ::java::lang::String* definitionId, ::java::lang::String* version, DirectionType* direction);
    virtual FunctionalComponent* createFunctionalComponent(::java::lang::String* displayId, AccessType* access, ::java::net::URI* componentDefinitionURI, DirectionType* direction);

public: /* package */
    virtual void addFunctionalComponent(FunctionalComponent* functionalComponent);

public:
    virtual bool removeFunctionalComponent(FunctionalComponent* functionalComponent);
    virtual FunctionalComponent* getFunctionalComponent(::java::lang::String* displayId);
    virtual FunctionalComponent* getFunctionalComponent(::java::net::URI* functionalComponentURI);
    virtual ::java::util::Set* getFunctionalComponents();
    virtual void clearFunctionalComponents();

public: /* package */
    virtual void setFunctionalComponents(::java::util::List* components);

public:
    virtual bool addModel(Model* model);
    virtual bool addModel(::java::lang::String* modelId, ::java::lang::String* version);
    virtual bool addModel(::java::net::URI* modelURI);
    virtual bool removeModel(::java::net::URI* modelURI);
    virtual void setModels(::java::util::Set* models);
    virtual ::java::util::Set* getModelURIs();
    virtual ::java::util::Set* getModels();
    virtual bool containsModel(::java::net::URI* modelURI);
    virtual void clearModels();
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    ModuleDefinition* deepCopy() override;

public: /* package */
    ModuleDefinition* copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version) override;

public: /* protected */
    bool checkDescendantsURIcompliance() override;
    virtual bool isComplete();

    // Generated

public: /* package */
    ModuleDefinition(::java::net::URI* identity);

private:
    ModuleDefinition(ModuleDefinition* moduleDefinition);
protected:
    ModuleDefinition(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

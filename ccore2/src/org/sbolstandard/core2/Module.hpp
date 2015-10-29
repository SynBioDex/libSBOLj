// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Module.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Module
    : public Identified
{

public:
    typedef Identified super;

private:
    ::java::util::HashMap* mapsTos {  };
    ::java::net::URI* definition {  };
    ModuleDefinition* moduleDefinition {  };
protected:
    void ctor(::java::net::URI* identity, ::java::net::URI* moduleDefinition);
    void ctor(Module* module);

public:
    virtual ::java::net::URI* getDefinitionURI();
    virtual ModuleDefinition* getDefinition();
    virtual void setDefinition(::java::net::URI* definitionURI);

public: /* package */
    virtual MapsTo* createMapsTo(::java::net::URI* identity, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote);

public:
    virtual MapsTo* createMapsTo(::java::lang::String* displayId, RefinementType* refinement, ::java::lang::String* localId, ::java::lang::String* remoteId);
    virtual MapsTo* createMapsTo(::java::lang::String* displayId, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote);

public: /* package */
    virtual void addMapsTo(MapsTo* mapsTo);

public:
    virtual bool removeMapsTo(MapsTo* mapsTo);
    virtual MapsTo* getMapsTo(::java::lang::String* displayId);
    virtual MapsTo* getMapsTo(::java::net::URI* referenceURI);
    virtual ::java::util::Set* getMapsTos();
    virtual void clearMapsTos();

public: /* package */
    virtual void setMapsTos(::java::util::List* mappings);

public:
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    Module* deepCopy() override;

public: /* package */
    virtual void updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);
    virtual ModuleDefinition* getModuleDefinition();
    virtual void setModuleDefinition(ModuleDefinition* moduleDefinition);

    // Generated
    Module(::java::net::URI* identity, ::java::net::URI* moduleDefinition);

private:
    Module(Module* module);
protected:
    Module(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

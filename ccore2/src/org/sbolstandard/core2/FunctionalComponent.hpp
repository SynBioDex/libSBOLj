// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/FunctionalComponent.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/ComponentInstance.hpp>

struct default_init_tag;

class org::sbolstandard::core2::FunctionalComponent
    : public ComponentInstance
{

public:
    typedef ComponentInstance super;

private:
    DirectionType* direction {  };

public: /* protected */
    ::java::util::HashMap* mapsTos {  };

private:
    ModuleDefinition* moduleDefinition {  };
protected:
    void ctor(::java::net::URI* identity, AccessType* access, ::java::net::URI* definitionURI, DirectionType* direction);
    void ctor(FunctionalComponent* functionalComponent);

public:
    virtual DirectionType* getDirection();
    virtual void setDirection(DirectionType* direction);
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    FunctionalComponent* deepCopy() override;

public: /* package */
    virtual void updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);
    virtual MapsTo* createMapsTo(::java::net::URI* identity, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote);

public:
    virtual MapsTo* createMapsTo(::java::lang::String* displayId, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote);

public: /* package */
    virtual void addMapsTo(MapsTo* mapsTo);

public:
    virtual bool removeMapsTo(MapsTo* mapsTo);
    virtual MapsTo* getMapsTo(::java::lang::String* displayId);
    virtual MapsTo* getMapsTo(::java::net::URI* mapsToURI);
    virtual ::java::util::Set* getMapsTos();
    virtual void clearMapsTos();

public: /* package */
    virtual void setMapsTo(::java::util::List* mapsTos);
    virtual ModuleDefinition* getModuleDefinition();
    virtual void setModuleDefinition(ModuleDefinition* moduleDefinition);

    // Generated
    FunctionalComponent(::java::net::URI* identity, AccessType* access, ::java::net::URI* definitionURI, DirectionType* direction);

private:
    FunctionalComponent(FunctionalComponent* functionalComponent);
protected:
    FunctionalComponent(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

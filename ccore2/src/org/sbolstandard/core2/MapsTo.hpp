// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/MapsTo.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

struct default_init_tag;

class org::sbolstandard::core2::MapsTo
    : public Identified
{

public:
    typedef Identified super;

private:
    RefinementType* refinement {  };
    ::java::net::URI* local {  };
    ::java::net::URI* remote {  };
    ModuleDefinition* moduleDefinition {  };
    Module* module {  };
    ComponentDefinition* componentDefinition {  };
    ComponentInstance* componentInstance {  };
protected:
    void ctor(::java::net::URI* identity, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote);
    void ctor(MapsTo* mapsTo);

public:
    virtual RefinementType* getRefinement();
    virtual void setRefinement(RefinementType* refinement);
    virtual ::java::net::URI* getLocalURI();
    virtual ComponentInstance* getLocal();
    virtual ComponentDefinition* getLocalDefinition();
    virtual void setLocal(::java::net::URI* local);
    virtual ::java::net::URI* getRemoteURI();
    virtual ComponentInstance* getRemote();
    virtual ComponentDefinition* getRemoteDefinition();
    virtual void setRemote(::java::net::URI* remote);
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    MapsTo* deepCopy() override;

public: /* package */
    virtual void updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);
    virtual ModuleDefinition* getModuleDefinition();
    virtual void setModuleDefinition(ModuleDefinition* moduleDefinition);
    virtual Module* getModule();
    virtual void setModule(Module* module);
    virtual ComponentDefinition* getComponentDefinition();
    virtual void setComponentDefinition(ComponentDefinition* componentDefinition);
    virtual ComponentInstance* getComponentInstance();
    virtual void setComponentInstance(ComponentInstance* componentInstance);

    // Generated
    MapsTo(::java::net::URI* identity, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote);

private:
    MapsTo(MapsTo* mapsTo);
protected:
    MapsTo(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Participation.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Participation
    : public Identified
{

public:
    typedef Identified super;

private:
    ::java::util::Set* roles {  };
    ::java::net::URI* participant {  };
    ModuleDefinition* moduleDefinition {  };
protected:
    void ctor(::java::net::URI* identity, ::java::net::URI* participant);
    void ctor(Participation* participation);

public:
    virtual ::java::net::URI* getParticipantURI();
    virtual FunctionalComponent* getParticipant();
    virtual ComponentDefinition* getParticipantDefinition();
    virtual void setParticipant(::java::net::URI* participant);
    virtual bool addRole(::java::net::URI* roleURI);
    virtual bool removeRole(::java::net::URI* roleURI);
    virtual void setRoles(::java::util::Set* roles);
    virtual ::java::util::Set* getRoles();
    virtual bool containsRole(::java::net::URI* roleURI);
    virtual void clearRoles();

public: /* protected */
    Participation* deepCopy() override;

public: /* package */
    virtual void updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);
    virtual ModuleDefinition* getModuleDefinition();
    virtual void setModuleDefinition(ModuleDefinition* moduleDefinition);

    // Generated
    Participation(::java::net::URI* identity, ::java::net::URI* participant);

private:
    Participation(Participation* participation);
protected:
    Participation(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

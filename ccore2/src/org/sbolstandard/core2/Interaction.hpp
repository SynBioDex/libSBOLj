// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Interaction.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Interaction
    : public Identified
{

public:
    typedef Identified super;

private:
    ::java::util::Set* types {  };
    ::java::util::HashMap* participations {  };
    ModuleDefinition* moduleDefinition {  };
protected:
    void ctor(::java::net::URI* identity, ::java::util::Set* type);
    void ctor(Interaction* interaction);

public:
    virtual bool addType(::java::net::URI* typeURI);
    virtual bool removeType(::java::net::URI* typeURI);
    virtual void setTypes(::java::util::Set* types);
    virtual ::java::util::Set* getTypes();
    virtual bool containsType(::java::net::URI* typeURI);

public: /* package */
    virtual void clearTypes();
    virtual Participation* createParticipation(::java::net::URI* identity, ::java::net::URI* participant);

public:
    virtual Participation* createParticipation(::java::lang::String* displayId, ::java::lang::String* participantId);
    virtual Participation* createParticipation(::java::lang::String* displayId, ::java::net::URI* participant);

public: /* package */
    virtual void addParticipation(Participation* participation);

public:
    virtual bool removeParticipation(Participation* participation);
    virtual Participation* getParticipation(::java::lang::String* displayId);
    virtual Participation* getParticipation(::java::net::URI* participationURI);
    virtual ::java::util::Set* getParticipations();
    virtual void clearParticipations();

public: /* package */
    virtual void setParticipations(::java::util::List* participations);

public:
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    Interaction* deepCopy() override;

public: /* package */
    virtual void updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);
    virtual ModuleDefinition* getModuleDefinition();
    virtual void setModuleDefinition(ModuleDefinition* moduleDefinition);

    // Generated
    Interaction(::java::net::URI* identity, ::java::util::Set* type);
    Interaction(Interaction* interaction);
protected:
    Interaction(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

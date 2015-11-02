// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SequenceConstraint.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

struct default_init_tag;

class org::sbolstandard::core2::SequenceConstraint
    : public Identified
{

public:
    typedef Identified super;

private:
    ::java::net::URI* restriction {  };
    ::java::net::URI* subject {  };
    ::java::net::URI* object {  };
    ComponentDefinition* componentDefinition {  };
protected:
    void ctor(::java::net::URI* identity, ::java::net::URI* restriction, ::java::net::URI* subject, ::java::net::URI* object);
    void ctor(::java::net::URI* identity, RestrictionType* restriction, ::java::net::URI* subject, ::java::net::URI* object);
    void ctor(SequenceConstraint* sequenceConstraint);

public:
    virtual RestrictionType* getRestriction();
    virtual ::java::net::URI* getRestrictionURI();
    virtual void setRestriction(RestrictionType* restriction);
    virtual void setRestriction(::java::net::URI* restrictionURI);
    virtual ::java::net::URI* getSubjectURI();
    virtual Component* getSubject();
    virtual ComponentDefinition* getSubjectDefinition();
    virtual void setSubject(::java::net::URI* subjectURI);
    virtual ::java::net::URI* getObjectURI();
    virtual Component* getObject();
    virtual ComponentDefinition* getObjectDefinition();
    virtual void setObject(::java::net::URI* objectURI);
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    SequenceConstraint* deepCopy() override;

public: /* package */
    virtual void updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);
    virtual ComponentDefinition* getComponentDefinition();
    virtual void setComponentDefinition(ComponentDefinition* componentDefinition);

    // Generated
    SequenceConstraint(::java::net::URI* identity, ::java::net::URI* restriction, ::java::net::URI* subject, ::java::net::URI* object);
    SequenceConstraint(::java::net::URI* identity, RestrictionType* restriction, ::java::net::URI* subject, ::java::net::URI* object);

private:
    SequenceConstraint(SequenceConstraint* sequenceConstraint);
protected:
    SequenceConstraint(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

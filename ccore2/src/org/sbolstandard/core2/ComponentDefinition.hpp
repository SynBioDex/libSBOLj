// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComponentDefinition.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>

struct default_init_tag;

class org::sbolstandard::core2::ComponentDefinition
    : public TopLevel
{

public:
    typedef TopLevel super;

private:
    ::java::util::Set* types {  };
    ::java::util::Set* roles {  };
    ::java::util::Set* sequences {  };
    ::java::util::HashMap* components {  };
    ::java::util::HashMap* sequenceAnnotations {  };
    ::java::util::HashMap* sequenceConstraints {  };
    static ::java::net::URI* DNA_;
    static ::java::net::URI* RNA_;
    static ::java::net::URI* PROTEIN_;
    static ::java::net::URI* SMALL_MOLECULE_;
    static ::java::net::URI* EFFECTOR_;
protected:
    void ctor(::java::net::URI* identity, ::java::util::Set* types);
    void ctor(ComponentDefinition* componentDefinition);

public:
    virtual bool addType(::java::net::URI* typeURI);
    virtual bool removeType(::java::net::URI* typeURI);
    virtual void setTypes(::java::util::Set* types);
    virtual ::java::util::Set* getTypes();
    virtual bool containsType(::java::net::URI* typeURI);

public: /* package */
    virtual void clearTypes();

public:
    virtual bool addRole(::java::net::URI* roleURI);
    virtual bool removeRole(::java::net::URI* roleURI);
    virtual void setRoles(::java::util::Set* roles);
    virtual ::java::util::Set* getRoles();
    virtual bool containsRole(::java::net::URI* roleURI);
    virtual void clearRoles();
    virtual bool addSequence(Sequence* sequence);
    virtual bool addSequence(::java::net::URI* sequenceURI);
    virtual bool addSequence(::java::lang::String* sequenceId, ::java::lang::String* version);
    virtual ::java::util::Set* getSequenceURIs();
    virtual ::java::util::Set* getSequences();
    virtual void setSequences(::java::util::Set* sequences);
    virtual bool removeSequence(::java::net::URI* sequenceURI);
    virtual void clearSequences();
    virtual bool containsSequence(::java::net::URI* sequenceURI);

public: /* package */
    virtual SequenceAnnotation* createSequenceAnnotation(::java::net::URI* identity, ::java::util::List* locations);
    virtual SequenceAnnotation* createSequenceAnnotation(::java::lang::String* displayId, Location* location);

public:
    virtual SequenceAnnotation* createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId);
    virtual SequenceAnnotation* createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, OrientationType* orientation);
    virtual SequenceAnnotation* createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, int32_t at);
    virtual SequenceAnnotation* createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, int32_t at, OrientationType* orientation);
    virtual SequenceAnnotation* createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, int32_t start, int32_t end);
    virtual SequenceAnnotation* createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, int32_t start, int32_t end, OrientationType* orientation);

public: /* package */
    virtual void addSequenceAnnotation(SequenceAnnotation* sequenceAnnotation);

public:
    virtual bool removeSequenceAnnotation(SequenceAnnotation* sequenceAnnotation);
    virtual SequenceAnnotation* getSequenceAnnotation(::java::lang::String* displayId);
    virtual SequenceAnnotation* getSequenceAnnotation(::java::net::URI* sequenceAnnotationURI);
    virtual ::java::util::Set* getSequenceAnnotations();
    virtual void clearSequenceAnnotations();

public: /* package */
    virtual void setSequenceAnnotations(::java::util::List* sequenceAnnotations);
    virtual Component* createComponent(::java::net::URI* identity, AccessType* access, ::java::net::URI* componentDefinitionURI);

public:
    virtual Component* createComponent(::java::lang::String* displayId, AccessType* access, ::java::lang::String* componentDefinitionId, ::java::lang::String* version);
    virtual Component* createComponent(::java::lang::String* displayId, AccessType* access, ::java::net::URI* componentDefinitionURI);

public: /* package */
    virtual void addComponent(Component* component);

public:
    virtual bool removeComponent(Component* component);
    virtual Component* getComponent(::java::lang::String* displayId);
    virtual Component* getComponent(::java::net::URI* componentURI);
    virtual ::java::util::Set* getComponents();
    virtual void clearComponents();

public: /* package */
    virtual void setComponents(::java::util::List* components);
    virtual SequenceConstraint* createSequenceConstraint(::java::net::URI* identity, RestrictionType* restriction, ::java::net::URI* subject, ::java::net::URI* object);

public:
    virtual SequenceConstraint* createSequenceConstraint(::java::lang::String* displayId, RestrictionType* restriction, ::java::lang::String* subjectId, ::java::lang::String* objectId);
    virtual SequenceConstraint* createSequenceConstraint(::java::lang::String* displayId, RestrictionType* restriction, ::java::net::URI* subject, ::java::net::URI* object);

public: /* package */
    virtual void addSequenceConstraint(SequenceConstraint* sequenceConstraint);

public:
    virtual bool removeSequenceConstraint(SequenceConstraint* sequenceConstraint);
    virtual SequenceConstraint* getSequenceConstraint(::java::lang::String* displayId);
    virtual SequenceConstraint* getSequenceConstraint(::java::net::URI* sequenceConstraintURI);
    virtual ::java::util::Set* getSequenceConstraints();
    virtual void clearSequenceConstraints();

public: /* package */
    virtual void setSequenceConstraints(::java::util::List* sequenceConstraints);

public: /* protected */
    bool checkDescendantsURIcompliance() override;
    virtual bool isComplete();
    ComponentDefinition* deepCopy() override;

public: /* package */
    ComponentDefinition* copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version) override;

public:
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

    // Generated

public: /* package */
    ComponentDefinition(::java::net::URI* identity, ::java::util::Set* types);

private:
    ComponentDefinition(ComponentDefinition* componentDefinition);
protected:
    ComponentDefinition(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();
    static ::java::net::URI*& DNA();
    static ::java::net::URI*& RNA();
    static ::java::net::URI*& PROTEIN();
    static ::java::net::URI*& SMALL_MOLECULE();
    static ::java::net::URI*& EFFECTOR();

private:
    virtual ::java::lang::Class* getClass0();
};

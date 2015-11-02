// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SequenceAnnotation.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

struct default_init_tag;

class org::sbolstandard::core2::SequenceAnnotation
    : public Identified
{

public:
    typedef Identified super;

private:
    ::java::util::HashMap* locations {  };
    ::java::net::URI* component {  };
    ComponentDefinition* componentDefinition {  };
protected:
    void ctor(::java::net::URI* identity, ::java::util::List* locations);
    void ctor(SequenceAnnotation* sequenceAnnotation);

public:
    virtual void addGenericLocation(::java::lang::String* displayId);
    virtual void addGenericLocation(::java::lang::String* displayId, OrientationType* orientation);
    virtual void addCut(::java::lang::String* displayId, int32_t at);
    virtual void addCut(::java::lang::String* displayId, int32_t at, OrientationType* orientation);
    virtual void addRange(::java::lang::String* displayId, int32_t start, int32_t end);
    virtual void addRange(::java::lang::String* displayId, int32_t start, int32_t end, OrientationType* orientation);

public: /* package */
    virtual void addLocation(Location* location);

public:
    virtual bool removeLocation(Location* location);
    virtual Location* getLocation(::java::lang::String* displayId);
    virtual Location* getLocation(::java::net::URI* locationURI);
    virtual ::java::util::Set* getLocations();

public: /* package */
    virtual void clearLocations();
    virtual void setLocations(::java::util::List* locations);

public:
    virtual bool isSetComponent();
    virtual ::java::net::URI* getComponentURI();
    virtual Component* getComponent();
    virtual void setComponent(::java::lang::String* displayId);
    virtual void setComponent(::java::net::URI* componentURI);
    virtual void unsetComponent();
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    SequenceAnnotation* deepCopy() override;

public: /* package */
    virtual void updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);

public:
    virtual ComponentDefinition* getComponentDefinition();

public: /* package */
    virtual void setComponentDefinition(ComponentDefinition* componentDefinition);

    // Generated
    SequenceAnnotation(::java::net::URI* identity, ::java::util::List* locations);

private:
    SequenceAnnotation(SequenceAnnotation* sequenceAnnotation);
protected:
    SequenceAnnotation(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComponentInstance.java

#pragma once

#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

struct default_init_tag;

class org::sbolstandard::core2::ComponentInstance
    : public Identified
{

public:
    typedef Identified super;

private:
    AccessType* access {  };

public: /* protected */
    ::java::net::URI* definition {  };
protected:
    void ctor(::java::net::URI* identity, AccessType* access, ::java::net::URI* definition);
    void ctor(ComponentInstance* component);

public:
    virtual AccessType* getAccess();
    virtual void setAccess(AccessType* access);
    virtual ::java::net::URI* getDefinitionURI();
    virtual ComponentDefinition* getDefinition();
    virtual void setDefinition(::java::net::URI* definition);

public: /* protected */
    ComponentInstance* deepCopy() = 0;

    // Generated

public: /* package */
    ComponentInstance(::java::net::URI* identity, AccessType* access, ::java::net::URI* definition);

public: /* protected */
    ComponentInstance(ComponentInstance* component);
protected:
    ComponentInstance(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

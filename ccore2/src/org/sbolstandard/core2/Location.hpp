// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Location.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Location
    : public Identified
{

public:
    typedef Identified super;

public: /* protected */
    OrientationType* orientation {  };
protected:
    void ctor(::java::net::URI* identity);
    void ctor(Location* location);

public: /* protected */
    Location* deepCopy() = 0;

public:
    virtual bool isSetOrientation();
    virtual OrientationType* getOrientation();
    virtual void setOrientation(OrientationType* orientation);
    virtual void unsetOrientation();

public: /* package */
    virtual void updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version);

    // Generated
    Location(::java::net::URI* identity);

public: /* protected */
    Location(Location* location);
protected:
    Location(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

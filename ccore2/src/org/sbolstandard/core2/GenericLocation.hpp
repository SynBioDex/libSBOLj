// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/GenericLocation.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Location.hpp>

struct default_init_tag;

class org::sbolstandard::core2::GenericLocation
    : public Location
{

public:
    typedef Location super;
protected:
    void ctor(::java::net::URI* identity);
    void ctor(GenericLocation* genericLocation);

public:
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    GenericLocation* deepCopy() override;

    // Generated

public: /* package */
    GenericLocation(::java::net::URI* identity);

private:
    GenericLocation(GenericLocation* genericLocation);
protected:
    GenericLocation(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

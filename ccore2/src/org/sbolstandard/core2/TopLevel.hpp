// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/TopLevel.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Identified.hpp>

struct default_init_tag;

class org::sbolstandard::core2::TopLevel
    : public Identified
{

public:
    typedef Identified super;

private:
    static ::java::lang::String* COLLECTION_;
    static ::java::lang::String* MODULE_DEFINITION_;
    static ::java::lang::String* MODEL_;
    static ::java::lang::String* COMPONENT_DEFINITION_;
    static ::java::lang::String* SEQUENCE_;
    static ::java::lang::String* GENERIC_TOP_LEVEL_;
protected:
    void ctor(::java::net::URI* identity);
    void ctor(TopLevel* toplevel);

public: /* protected */
    Identified* deepCopy() = 0;

public: /* package */
    virtual Identified* copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version) = 0;

public: /* protected */
    virtual bool checkDescendantsURIcompliance() = 0;

    // Generated

public: /* package */
    TopLevel(::java::net::URI* identity);

public: /* protected */
    TopLevel(TopLevel* toplevel);
protected:
    TopLevel(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();
    static ::java::lang::String*& COLLECTION();
    static ::java::lang::String*& MODULE_DEFINITION();
    static ::java::lang::String*& MODEL();
    static ::java::lang::String*& COMPONENT_DEFINITION();
    static ::java::lang::String*& SEQUENCE();
    static ::java::lang::String*& GENERIC_TOP_LEVEL();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Model.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Model
    : public TopLevel
{

public:
    typedef TopLevel super;

private:
    ::java::net::URI* source {  };
    ::java::net::URI* language {  };
    ::java::net::URI* framework {  };
    static ::java::net::URI* SBML_;
    static ::java::net::URI* CELLML_;
    static ::java::net::URI* BIOPAX_;
protected:
    void ctor(::java::net::URI* identity, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework);
    void ctor(Model* model);

public:
    virtual ::java::net::URI* getSource();
    virtual void setSource(::java::net::URI* source);
    virtual ::java::net::URI* getLanguage();
    virtual void setLanguage(::java::net::URI* language);
    virtual ::java::net::URI* getFramework();
    virtual void setFramework(::java::net::URI* framework);
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    Model* deepCopy() override;

public: /* package */
    Model* copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version) override;

public: /* protected */
    bool checkDescendantsURIcompliance() override;

    // Generated

public: /* package */
    Model(::java::net::URI* identity, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework);

private:
    Model(Model* model);
protected:
    Model(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();
    static ::java::net::URI*& SBML();
    static ::java::net::URI*& CELLML();
    static ::java::net::URI*& BIOPAX();

private:
    virtual ::java::lang::Class* getClass0();
};

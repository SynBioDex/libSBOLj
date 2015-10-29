// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sequence.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Sequence
    : public TopLevel
{

public:
    typedef TopLevel super;

private:
    ::java::lang::String* elements {  };
    ::java::net::URI* encoding {  };
    static ::java::net::URI* IUPAC_DNA_;
    static ::java::net::URI* IUPAC_RNA_;
    static ::java::net::URI* IUPAC_PROTEIN_;
    static ::java::net::URI* SMILES_;
protected:
    void ctor(::java::net::URI* identity, ::java::lang::String* elements, ::java::net::URI* encoding);
    void ctor(Sequence* sequence);

public:
    virtual ::java::lang::String* getElements();
    virtual void setElements(::java::lang::String* elements);
    virtual ::java::net::URI* getEncoding();
    virtual void setEncoding(::java::net::URI* encoding);
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    Sequence* deepCopy() override;
    Sequence* copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version) override;
    bool checkDescendantsURIcompliance() override;

    // Generated

public: /* package */
    Sequence(::java::net::URI* identity, ::java::lang::String* elements, ::java::net::URI* encoding);

private:
    Sequence(Sequence* sequence);
protected:
    Sequence(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();
    static ::java::net::URI*& IUPAC_DNA();
    static ::java::net::URI*& IUPAC_RNA();
    static ::java::net::URI*& IUPAC_PROTEIN();
    static ::java::net::URI*& SMILES();

private:
    virtual ::java::lang::Class* getClass0();
};

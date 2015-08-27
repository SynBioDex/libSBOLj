// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class uk::ac::ncl::intbio::core::datatree::Literal_Visitor
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

protected:
    void ctor();

public:
    void visit(Literal* l);
    virtual void visit(Literal_StringLiteral* l) = 0;
    virtual void visit(Literal_UriLiteral* l) = 0;
    virtual void visit(Literal_IntegerLiteral* l) = 0;
    virtual void visit(Literal_DoubleLiteral* l) = 0;
    virtual void visit(Literal_TypedLiteral* l) = 0;
    virtual void visit(Literal_BooleanLiteral* l) = 0;

    // Generated
    Literal_Visitor();
protected:
    Literal_Visitor(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

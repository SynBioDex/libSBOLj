// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class uk::ac::ncl::intbio::core::datatree::PropertyValue_Visitor
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

protected:
    void ctor();

public:
    void visit(PropertyValue* v);
    virtual void visit(NestedDocument* v) = 0;
    virtual void visit(Literal* v) = 0;

    // Generated
    PropertyValue_Visitor();
protected:
    PropertyValue_Visitor(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

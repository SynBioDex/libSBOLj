// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <uk/ac/ncl/intbio/core/datatree/IdentifiableDocument.hpp>

struct default_init_tag;

class uk::ac::ncl::intbio::core::datatree::IdentifiableDocument_Abstract
    : public virtual ::java::lang::Object
    , public virtual IdentifiableDocument
{

public:
    typedef ::java::lang::Object super;

protected:
    void ctor();

public:
    ::java::util::List* getPropertyValues(::java::lang::Object* propertyName) override;

    // Generated
    IdentifiableDocument_Abstract();
protected:
    IdentifiableDocument_Abstract(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

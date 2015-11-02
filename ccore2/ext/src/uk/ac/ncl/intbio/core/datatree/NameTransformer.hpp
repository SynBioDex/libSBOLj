// Generated from /Users/myers/.m2/repository/uk/ac/ncl/intbio/sbol-data-core/0.1.2/sbol-data-core-0.1.2.jar

#pragma once

#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class uk::ac::ncl::intbio::core::datatree::NameTransformer
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

protected:
    void ctor();

public:
    virtual DocumentRoot* mapDR(DocumentRoot* f);
    virtual ::java::util::List* mapL(::java::util::List* as, Func* f);
    virtual NestedDocument* mapND(NestedDocument* f);
    virtual TopLevelDocument* mapTLD(TopLevelDocument* f);
    virtual ::java::util::List* mapTLDs(::java::util::List* fs);
    virtual PropertyValue* mapV(PropertyValue* f);
    virtual NamedProperty* mapVP(NamedProperty* f);
    virtual ::java::util::List* mapVPs(::java::util::List* fs);
    virtual ::java::lang::Object* transformName(::java::lang::Object* f) = 0;

    // Generated
    NameTransformer();
protected:
    NameTransformer(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

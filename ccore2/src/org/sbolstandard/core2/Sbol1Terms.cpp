// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol1Terms.java
#include <org/sbolstandard/core2/Sbol1Terms.hpp>

#include <java/lang/String.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Datatree.hpp>

org::sbolstandard::core2::Sbol1Terms::Sbol1Terms(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol1Terms::Sbol1Terms()
    : Sbol1Terms(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

uk::ac::ncl::intbio::core::datatree::NamespaceBinding*& org::sbolstandard::core2::Sbol1Terms::sbol1()
{
    clinit();
    return sbol1_;
}
uk::ac::ncl::intbio::core::datatree::NamespaceBinding* org::sbolstandard::core2::Sbol1Terms::sbol1_;

uk::ac::ncl::intbio::core::datatree::NamespaceBinding*& org::sbolstandard::core2::Sbol1Terms::rdf()
{
    clinit();
    return rdf_;
}
uk::ac::ncl::intbio::core::datatree::NamespaceBinding* org::sbolstandard::core2::Sbol1Terms::rdf_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol1Terms::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol1Terms", 33);
    return c;
}

void org::sbolstandard::core2::Sbol1Terms::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        sbol1_ = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(u"http://sbols.org/v1#"_j, u"sbol"_j);
        rdf_ = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(u"http://www.w3.org/1999/02/22-rdf-syntax-ns#"_j, u"rdf"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol1Terms::getClass0()
{
    return class_();
}


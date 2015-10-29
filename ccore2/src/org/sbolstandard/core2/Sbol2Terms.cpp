// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms.hpp>

#include <java/lang/String.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Datatree.hpp>

org::sbolstandard::core2::Sbol2Terms::Sbol2Terms(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms::Sbol2Terms()
    : Sbol2Terms(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

uk::ac::ncl::intbio::core::datatree::NamespaceBinding*& org::sbolstandard::core2::Sbol2Terms::sbol2()
{
    clinit();
    return sbol2_;
}
uk::ac::ncl::intbio::core::datatree::NamespaceBinding* org::sbolstandard::core2::Sbol2Terms::sbol2_;

uk::ac::ncl::intbio::core::datatree::NamespaceBinding*& org::sbolstandard::core2::Sbol2Terms::dc()
{
    clinit();
    return dc_;
}
uk::ac::ncl::intbio::core::datatree::NamespaceBinding* org::sbolstandard::core2::Sbol2Terms::dc_;

uk::ac::ncl::intbio::core::datatree::NamespaceBinding*& org::sbolstandard::core2::Sbol2Terms::prov()
{
    clinit();
    return prov_;
}
uk::ac::ncl::intbio::core::datatree::NamespaceBinding* org::sbolstandard::core2::Sbol2Terms::prov_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms", 33);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        sbol2_ = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(u"http://sbols.org/v2#"_j, u"sbol"_j);
        dc_ = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(u"http://purl.org/dc/terms/"_j, u"dcterms"_j);
        prov_ = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamespaceBinding(u"http://www.w3.org/ns/prov#"_j, u"prov"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms::getClass0()
{
    return class_();
}


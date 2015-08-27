// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_MapsTo.hpp>

#include <java/lang/NullPointerException.hpp>
#include <java/lang/String.hpp>
#include <org/sbolstandard/core2/Sbol2Terms.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NamespaceBinding.hpp>

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::Sbol2Terms_MapsTo::Sbol2Terms_MapsTo(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_MapsTo::Sbol2Terms_MapsTo()
    : Sbol2Terms_MapsTo(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_MapsTo::MapsTo_()
{
    clinit();
    return MapsTo__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_MapsTo::MapsTo__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_MapsTo::refinement()
{
    clinit();
    return refinement_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_MapsTo::refinement_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_MapsTo::hasRemote()
{
    clinit();
    return hasRemote_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_MapsTo::hasRemote_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_MapsTo::hasLocal()
{
    clinit();
    return hasLocal_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_MapsTo::hasLocal_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_MapsTo::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.MapsTo", 40);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_MapsTo::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        MapsTo__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"MapsTo"_j);
        refinement_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"refinement"_j);
        hasRemote_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"remote"_j);
        hasLocal_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"local"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_MapsTo::getClass0()
{
    return class_();
}


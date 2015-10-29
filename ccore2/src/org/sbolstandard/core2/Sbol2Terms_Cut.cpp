// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_Cut.hpp>

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

org::sbolstandard::core2::Sbol2Terms_Cut::Sbol2Terms_Cut(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_Cut::Sbol2Terms_Cut()
    : Sbol2Terms_Cut(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Cut::Cut_()
{
    clinit();
    return Cut__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Cut::Cut__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Cut::at()
{
    clinit();
    return at_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Cut::at_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Cut::orientation()
{
    clinit();
    return orientation_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Cut::orientation_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Cut::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.Cut", 37);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_Cut::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        Cut__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"Cut"_j);
        at_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"at"_j);
        orientation_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"orientation"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Cut::getClass0()
{
    return class_();
}


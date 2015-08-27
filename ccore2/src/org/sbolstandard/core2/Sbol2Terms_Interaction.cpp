// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_Interaction.hpp>

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

org::sbolstandard::core2::Sbol2Terms_Interaction::Sbol2Terms_Interaction(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_Interaction::Sbol2Terms_Interaction()
    : Sbol2Terms_Interaction(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Interaction::Interaction_()
{
    clinit();
    return Interaction__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Interaction::Interaction__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Interaction::type()
{
    clinit();
    return type_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Interaction::type_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Interaction::hasParticipations()
{
    clinit();
    return hasParticipations_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Interaction::hasParticipations_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Interaction::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.Interaction", 45);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_Interaction::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        Interaction__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"Interaction"_j);
        type_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"type"_j);
        hasParticipations_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"participation"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Interaction::getClass0()
{
    return class_();
}


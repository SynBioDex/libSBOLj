// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_Sequence.hpp>

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

org::sbolstandard::core2::Sbol2Terms_Sequence::Sbol2Terms_Sequence(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_Sequence::Sbol2Terms_Sequence()
    : Sbol2Terms_Sequence(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Sequence::Sequence_()
{
    clinit();
    return Sequence__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Sequence::Sequence__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Sequence::elements()
{
    clinit();
    return elements_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Sequence::elements_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Sequence::encoding()
{
    clinit();
    return encoding_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Sequence::encoding_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Sequence::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.Sequence", 42);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_Sequence::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        Sequence__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"Sequence"_j);
        elements_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"elements"_j);
        encoding_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"encoding"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Sequence::getClass0()
{
    return class_();
}


// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_SequenceAnnotation.hpp>

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

org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::Sbol2Terms_SequenceAnnotation(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::Sbol2Terms_SequenceAnnotation()
    : Sbol2Terms_SequenceAnnotation(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::SequenceAnnotation_()
{
    clinit();
    return SequenceAnnotation__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::SequenceAnnotation__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::hasComponent()
{
    clinit();
    return hasComponent_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::hasComponent_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::hasLocation()
{
    clinit();
    return hasLocation_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::hasLocation_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.SequenceAnnotation", 52);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        SequenceAnnotation__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"SequenceAnnotation"_j);
        hasComponent_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"component"_j);
        hasLocation_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"location"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_SequenceAnnotation::getClass0()
{
    return class_();
}


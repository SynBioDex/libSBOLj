// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol1Terms.java
#include <org/sbolstandard/core2/Sbol1Terms_SequenceAnnotations.hpp>

#include <java/lang/NullPointerException.hpp>
#include <java/lang/String.hpp>
#include <org/sbolstandard/core2/Sbol1Terms.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NamespaceBinding.hpp>

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::Sbol1Terms_SequenceAnnotations(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::Sbol1Terms_SequenceAnnotations()
    : Sbol1Terms_SequenceAnnotations(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::SequenceAnnotation()
{
    clinit();
    return SequenceAnnotation_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::SequenceAnnotation_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::uri()
{
    clinit();
    return uri_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::uri_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::bioStart()
{
    clinit();
    return bioStart_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::bioStart_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::bioEnd()
{
    clinit();
    return bioEnd_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::bioEnd_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::strand()
{
    clinit();
    return strand_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::strand_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::subComponent()
{
    clinit();
    return subComponent_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::subComponent_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::precedes()
{
    clinit();
    return precedes_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::precedes_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol1Terms.SequenceAnnotations", 53);
    return c;
}

void org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        SequenceAnnotation_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"SequenceAnnotation"_j);
        uri_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"uri"_j);
        bioStart_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"bioStart"_j);
        bioEnd_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"bioEnd"_j);
        strand_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"strand"_j);
        subComponent_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"subComponent"_j);
        precedes_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"precedes"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol1Terms_SequenceAnnotations::getClass0()
{
    return class_();
}


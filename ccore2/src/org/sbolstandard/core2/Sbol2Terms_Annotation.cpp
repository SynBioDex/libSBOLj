// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_Annotation.hpp>

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

org::sbolstandard::core2::Sbol2Terms_Annotation::Sbol2Terms_Annotation(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_Annotation::Sbol2Terms_Annotation()
    : Sbol2Terms_Annotation(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Annotation::Annotation_()
{
    clinit();
    return Annotation__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Annotation::Annotation__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Annotation::relation()
{
    clinit();
    return relation_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Annotation::relation_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Annotation::value()
{
    clinit();
    return value_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Annotation::value_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Annotation::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.Annotation", 44);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_Annotation::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        Annotation__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"Annotation"_j);
        relation_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"relation"_j);
        value_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"value"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Annotation::getClass0()
{
    return class_();
}


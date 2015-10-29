// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol1Terms.java
#include <org/sbolstandard/core2/Sbol1Terms_DNAComponent.hpp>

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

org::sbolstandard::core2::Sbol1Terms_DNAComponent::Sbol1Terms_DNAComponent(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol1Terms_DNAComponent::Sbol1Terms_DNAComponent()
    : Sbol1Terms_DNAComponent(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_DNAComponent::DNAComponent_()
{
    clinit();
    return DNAComponent__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_DNAComponent::DNAComponent__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_DNAComponent::uri()
{
    clinit();
    return uri_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_DNAComponent::uri_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_DNAComponent::displayId()
{
    clinit();
    return displayId_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_DNAComponent::displayId_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_DNAComponent::name()
{
    clinit();
    return name_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_DNAComponent::name_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_DNAComponent::description()
{
    clinit();
    return description_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_DNAComponent::description_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_DNAComponent::type()
{
    clinit();
    return type_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_DNAComponent::type_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_DNAComponent::annotations()
{
    clinit();
    return annotations_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_DNAComponent::annotations_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_DNAComponent::dnaSequence()
{
    clinit();
    return dnaSequence_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_DNAComponent::dnaSequence_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol1Terms_DNAComponent::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol1Terms.DNAComponent", 46);
    return c;
}

void org::sbolstandard::core2::Sbol1Terms_DNAComponent::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        DNAComponent__ = npc(Sbol1Terms::sbol1())->withLocalPart(u"DnaComponent"_j);
        uri_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"uri"_j);
        displayId_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"displayId"_j);
        name_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"name"_j);
        description_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"description"_j);
        type_ = npc(Sbol1Terms::rdf())->withLocalPart(u"type"_j);
        annotations_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"annotation"_j);
        dnaSequence_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"dnaSequence"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol1Terms_DNAComponent::getClass0()
{
    return class_();
}


// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol1Terms.java
#include <org/sbolstandard/core2/Sbol1Terms_Collection.hpp>

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

org::sbolstandard::core2::Sbol1Terms_Collection::Sbol1Terms_Collection(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol1Terms_Collection::Sbol1Terms_Collection()
    : Sbol1Terms_Collection(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_Collection::Collection_()
{
    clinit();
    return Collection__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_Collection::Collection__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_Collection::uri()
{
    clinit();
    return uri_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_Collection::uri_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_Collection::displayId()
{
    clinit();
    return displayId_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_Collection::displayId_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_Collection::name()
{
    clinit();
    return name_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_Collection::name_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_Collection::description()
{
    clinit();
    return description_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_Collection::description_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol1Terms_Collection::component()
{
    clinit();
    return component_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol1Terms_Collection::component_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol1Terms_Collection::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol1Terms.Collection", 44);
    return c;
}

void org::sbolstandard::core2::Sbol1Terms_Collection::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        Collection__ = npc(Sbol1Terms::sbol1())->withLocalPart(u"Collection"_j);
        uri_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"uri"_j);
        displayId_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"displayId"_j);
        name_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"name"_j);
        description_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"description"_j);
        component_ = npc(Sbol1Terms::sbol1())->withLocalPart(u"component"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol1Terms_Collection::getClass0()
{
    return class_();
}


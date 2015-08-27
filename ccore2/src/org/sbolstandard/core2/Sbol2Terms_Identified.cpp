// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_Identified.hpp>

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

org::sbolstandard::core2::Sbol2Terms_Identified::Sbol2Terms_Identified(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_Identified::Sbol2Terms_Identified()
    : Sbol2Terms_Identified(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::Identified_()
{
    clinit();
    return Identified__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::Identified__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::identity()
{
    clinit();
    return identity_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::identity_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::persistentIdentity()
{
    clinit();
    return persistentIdentity_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::persistentIdentity_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::version()
{
    clinit();
    return version_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::version_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::timeStamp()
{
    clinit();
    return timeStamp_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::timeStamp_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::hasAnnotations()
{
    clinit();
    return hasAnnotations_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::hasAnnotations_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::wasDerivedFrom()
{
    clinit();
    return wasDerivedFrom_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::wasDerivedFrom_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::displayId()
{
    clinit();
    return displayId_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::displayId_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::title()
{
    clinit();
    return title_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::title_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Identified::description()
{
    clinit();
    return description_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Identified::description_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Identified::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.Identified", 44);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_Identified::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        Identified__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"Identified"_j);
        identity_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"identity"_j);
        persistentIdentity_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"persistentIdentity"_j);
        version_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"version"_j);
        timeStamp_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"timeStamp"_j);
        hasAnnotations_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"annotation"_j);
        wasDerivedFrom_ = npc(Sbol2Terms::prov())->withLocalPart(u"wasDerivedFrom"_j);
        displayId_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"displayId"_j);
        title_ = npc(Sbol2Terms::dc())->withLocalPart(u"title"_j);
        description_ = npc(Sbol2Terms::dc())->withLocalPart(u"description"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Identified::getClass0()
{
    return class_();
}


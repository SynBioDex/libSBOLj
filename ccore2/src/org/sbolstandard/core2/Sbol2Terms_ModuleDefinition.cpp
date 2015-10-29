// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_ModuleDefinition.hpp>

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

org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::Sbol2Terms_ModuleDefinition(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::Sbol2Terms_ModuleDefinition()
    : Sbol2Terms_ModuleDefinition(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::ModuleDefinition_()
{
    clinit();
    return ModuleDefinition__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::ModuleDefinition__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::roles()
{
    clinit();
    return roles_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::roles_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasModule()
{
    clinit();
    return hasModule_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasModule_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasSubModule()
{
    clinit();
    return hasSubModule_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasSubModule_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasInteractions()
{
    clinit();
    return hasInteractions_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasInteractions_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasModels()
{
    clinit();
    return hasModels_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasModels_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasfunctionalComponent()
{
    clinit();
    return hasfunctionalComponent_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::hasfunctionalComponent_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.ModuleDefinition", 50);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        ModuleDefinition__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"ModuleDefinition"_j);
        roles_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"role"_j);
        hasModule_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"module"_j);
        hasSubModule_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"subModule"_j);
        hasInteractions_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"interaction"_j);
        hasModels_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"model"_j);
        hasfunctionalComponent_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"functionalComponent"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_ModuleDefinition::getClass0()
{
    return class_();
}


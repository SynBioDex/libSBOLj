// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_ComponentDefinition.hpp>

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

org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::Sbol2Terms_ComponentDefinition(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::Sbol2Terms_ComponentDefinition()
    : Sbol2Terms_ComponentDefinition(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::ComponentDefinition_()
{
    clinit();
    return ComponentDefinition__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::ComponentDefinition__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::type()
{
    clinit();
    return type_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::type_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::roles()
{
    clinit();
    return roles_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::roles_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasSequence()
{
    clinit();
    return hasSequence_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasSequence_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasSequenceAnnotations()
{
    clinit();
    return hasSequenceAnnotations_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasSequenceAnnotations_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasSequenceConstraints()
{
    clinit();
    return hasSequenceConstraints_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasSequenceConstraints_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasComponent()
{
    clinit();
    return hasComponent_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasComponent_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasSubComponent()
{
    clinit();
    return hasSubComponent_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::hasSubComponent_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.ComponentDefinition", 53);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        ComponentDefinition__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"ComponentDefinition"_j);
        type_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"type"_j);
        roles_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"role"_j);
        hasSequence_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"sequence"_j);
        hasSequenceAnnotations_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"sequenceAnnotation"_j);
        hasSequenceConstraints_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"sequenceConstraint"_j);
        hasComponent_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"component"_j);
        hasSubComponent_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"subComponent"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_ComponentDefinition::getClass0()
{
    return class_();
}


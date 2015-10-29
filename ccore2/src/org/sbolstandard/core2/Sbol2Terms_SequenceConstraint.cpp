// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_SequenceConstraint.hpp>

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

org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::Sbol2Terms_SequenceConstraint(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::Sbol2Terms_SequenceConstraint()
    : Sbol2Terms_SequenceConstraint(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::SequenceConstraint_()
{
    clinit();
    return SequenceConstraint__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::SequenceConstraint__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::restriction()
{
    clinit();
    return restriction_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::restriction_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::hasSubject()
{
    clinit();
    return hasSubject_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::hasSubject_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::hasObject()
{
    clinit();
    return hasObject_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::hasObject_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.SequenceConstraint", 52);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        SequenceConstraint__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"SequenceConstraint"_j);
        restriction_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"restriction"_j);
        hasSubject_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"subject"_j);
        hasObject_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"object"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_SequenceConstraint::getClass0()
{
    return class_();
}


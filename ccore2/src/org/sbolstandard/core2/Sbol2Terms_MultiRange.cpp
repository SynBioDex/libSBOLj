// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_MultiRange.hpp>

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

org::sbolstandard::core2::Sbol2Terms_MultiRange::Sbol2Terms_MultiRange(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_MultiRange::Sbol2Terms_MultiRange()
    : Sbol2Terms_MultiRange(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_MultiRange::MultiRange_()
{
    clinit();
    return MultiRange__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_MultiRange::MultiRange__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_MultiRange::hasRanges()
{
    clinit();
    return hasRanges_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_MultiRange::hasRanges_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_MultiRange::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.MultiRange", 44);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_MultiRange::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        MultiRange__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"MultiRange"_j);
        hasRanges_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"range"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_MultiRange::getClass0()
{
    return class_();
}


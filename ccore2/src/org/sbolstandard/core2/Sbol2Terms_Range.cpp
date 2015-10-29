// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_Range.hpp>

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

org::sbolstandard::core2::Sbol2Terms_Range::Sbol2Terms_Range(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_Range::Sbol2Terms_Range()
    : Sbol2Terms_Range(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Range::Range_()
{
    clinit();
    return Range__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Range::Range__;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Range::start()
{
    clinit();
    return start_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Range::start_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Range::end()
{
    clinit();
    return end_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Range::end_;

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Range::orientation()
{
    clinit();
    return orientation_;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Range::orientation_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Range::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.Range", 39);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_Range::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        Range__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"Range"_j);
        start_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"start"_j);
        end_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"end"_j);
        orientation_ = npc(Sbol2Terms::sbol2())->withLocalPart(u"orientation"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Range::getClass0()
{
    return class_();
}


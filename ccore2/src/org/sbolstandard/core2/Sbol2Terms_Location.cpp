// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_Location.hpp>

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

org::sbolstandard::core2::Sbol2Terms_Location::Sbol2Terms_Location(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_Location::Sbol2Terms_Location()
    : Sbol2Terms_Location(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

javax::xml::namespace_::QName*& org::sbolstandard::core2::Sbol2Terms_Location::Location_()
{
    clinit();
    return Location__;
}
javax::xml::namespace_::QName* org::sbolstandard::core2::Sbol2Terms_Location::Location__;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Location::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.Location", 42);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_Location::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        Location__ = npc(Sbol2Terms::sbol2())->withLocalPart(u"location"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_Location::getClass0()
{
    return class_();
}


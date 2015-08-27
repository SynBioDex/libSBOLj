// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sbol2Terms.java
#include <org/sbolstandard/core2/Sbol2Terms_SequenceURI.hpp>

#include <java/lang/NullPointerException.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <org/sbolstandard/core2/Sbol2Terms.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NamespaceBinding.hpp>

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::Sbol2Terms_SequenceURI::Sbol2Terms_SequenceURI(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sbol2Terms_SequenceURI::Sbol2Terms_SequenceURI()
    : Sbol2Terms_SequenceURI(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::net::URI*& org::sbolstandard::core2::Sbol2Terms_SequenceURI::encoding()
{
    clinit();
    return encoding_;
}
java::net::URI* org::sbolstandard::core2::Sbol2Terms_SequenceURI::encoding_;

java::net::URI*& org::sbolstandard::core2::Sbol2Terms_SequenceURI::DnaSequenceV1()
{
    clinit();
    return DnaSequenceV1_;
}
java::net::URI* org::sbolstandard::core2::Sbol2Terms_SequenceURI::DnaSequenceV1_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_SequenceURI::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sbol2Terms.SequenceURI", 45);
    return c;
}

void org::sbolstandard::core2::Sbol2Terms_SequenceURI::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        encoding_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"encoding"_j)->toString());
        DnaSequenceV1_ = ::java::net::URI::create(u"http://dx.doi.org/10.1021/bi00822a023"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sbol2Terms_SequenceURI::getClass0()
{
    return class_();
}


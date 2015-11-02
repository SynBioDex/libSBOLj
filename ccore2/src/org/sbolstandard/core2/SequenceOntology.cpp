// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SequenceOntology.java
#include <org/sbolstandard/core2/SequenceOntology.hpp>

#include <java/lang/CharSequence.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::SequenceOntology::SequenceOntology(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SequenceOntology::SequenceOntology()
    : SequenceOntology(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

java::lang::String*& org::sbolstandard::core2::SequenceOntology::URI_PREFIX()
{
    clinit();
    return URI_PREFIX_;
}
java::lang::String* org::sbolstandard::core2::SequenceOntology::URI_PREFIX_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::NAMESPACE()
{
    clinit();
    return NAMESPACE_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::NAMESPACE_;

java::net::URI* org::sbolstandard::core2::SequenceOntology::convertSeqOntologyV1(::java::lang::String* term)
{
    clinit();
    auto v1SO = u"http://purl.obolibrary.org/obo/SO_"_j;
    auto v2SO = u"http://identifiers.org/so/SO:"_j;
    auto convertedSO = term;
    if(npc(term)->startsWith(v1SO)) {
        convertedSO = npc(convertedSO)->replace(static_cast< ::java::lang::CharSequence* >(v1SO), static_cast< ::java::lang::CharSequence* >(v2SO));
        return ::java::net::URI::create(convertedSO);
    }
    return ::java::net::URI::create(convertedSO);
}

java::net::URI* org::sbolstandard::core2::SequenceOntology::type(::java::lang::String* localName)
{
    clinit();
    return ::java::net::URI::create(::java::lang::StringBuilder().append(URI_PREFIX_)->append(localName)->toString());
}

java::net::URI*& org::sbolstandard::core2::SequenceOntology::PROMOTER()
{
    clinit();
    return PROMOTER_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::PROMOTER_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::OPERATOR()
{
    clinit();
    return OPERATOR_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::OPERATOR_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::CDS()
{
    clinit();
    return CDS_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::CDS_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::FIVE_PRIME_UTR()
{
    clinit();
    return FIVE_PRIME_UTR_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::FIVE_PRIME_UTR_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::TERMINATOR()
{
    clinit();
    return TERMINATOR_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::TERMINATOR_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::INSULATOR()
{
    clinit();
    return INSULATOR_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::INSULATOR_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::ORIGIN_OF_REPLICATION()
{
    clinit();
    return ORIGIN_OF_REPLICATION_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::ORIGIN_OF_REPLICATION_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::PRIMER_BINDING_SITE()
{
    clinit();
    return PRIMER_BINDING_SITE_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::PRIMER_BINDING_SITE_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::RIBOSOME_ENTRY_SITE()
{
    clinit();
    return RIBOSOME_ENTRY_SITE_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::RIBOSOME_ENTRY_SITE_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::GENE()
{
    clinit();
    return GENE_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::GENE_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::MRNA()
{
    clinit();
    return MRNA_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::MRNA_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::RESTRICTION_ENZYME_RECOGNITION_SITE()
{
    clinit();
    return RESTRICTION_ENZYME_RECOGNITION_SITE_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::RESTRICTION_ENZYME_RECOGNITION_SITE_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::ENGINEERED_GENE()
{
    clinit();
    return ENGINEERED_GENE_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::ENGINEERED_GENE_;

java::net::URI*& org::sbolstandard::core2::SequenceOntology::ENGINEERED_REGION()
{
    clinit();
    return ENGINEERED_REGION_;
}
java::net::URI* org::sbolstandard::core2::SequenceOntology::ENGINEERED_REGION_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SequenceOntology::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SequenceOntology", 39);
    return c;
}

void org::sbolstandard::core2::SequenceOntology::clinit()
{
struct string_init_ {
    string_init_() {
    URI_PREFIX_ = u"http://identifiers.org/so/"_j;
    }
};

    static string_init_ string_init_instance;

    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        NAMESPACE_ = ::java::net::URI::create(URI_PREFIX_);
        PROMOTER_ = type(u"SO:0000167"_j);
        OPERATOR_ = type(u"SO:0000057"_j);
        CDS_ = type(u"SO:0000316"_j);
        FIVE_PRIME_UTR_ = type(u"SO:0000204"_j);
        TERMINATOR_ = type(u"SO:0000141"_j);
        INSULATOR_ = type(u"SO:0000627"_j);
        ORIGIN_OF_REPLICATION_ = type(u"SO:0000296"_j);
        PRIMER_BINDING_SITE_ = type(u"SO:0005850"_j);
        RIBOSOME_ENTRY_SITE_ = type(u"SO:0000139"_j);
        GENE_ = type(u"SO:0000704"_j);
        MRNA_ = type(u"SO:0000234"_j);
        RESTRICTION_ENZYME_RECOGNITION_SITE_ = type(u"SO:0001687"_j);
        ENGINEERED_GENE_ = type(u"SO:0000280"_j);
        ENGINEERED_REGION_ = type(u"SO:0000804"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::SequenceOntology::getClass0()
{
    return class_();
}


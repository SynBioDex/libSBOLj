// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Sequence.java
#include <org/sbolstandard/core2/Sequence.hpp>

#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/net/URI.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>

template<typename T, typename U>
static T java_cast(U* u)
{
    if(!u) return static_cast<T>(nullptr);
    auto t = dynamic_cast<T>(u);
    if(!t) throw new ::java::lang::ClassCastException();
    return t;
}

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::Sequence::Sequence(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Sequence::Sequence(::java::net::URI* identity, ::java::lang::String* elements, ::java::net::URI* encoding) 
    : Sequence(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,elements,encoding);
}

org::sbolstandard::core2::Sequence::Sequence(Sequence* sequence) 
    : Sequence(*static_cast< ::default_init_tag* >(0))
{
    ctor(sequence);
}

java::net::URI*& org::sbolstandard::core2::Sequence::IUPAC_DNA()
{
    clinit();
    return IUPAC_DNA_;
}
java::net::URI* org::sbolstandard::core2::Sequence::IUPAC_DNA_;

java::net::URI*& org::sbolstandard::core2::Sequence::IUPAC_RNA()
{
    clinit();
    return IUPAC_RNA_;
}
java::net::URI* org::sbolstandard::core2::Sequence::IUPAC_RNA_;

java::net::URI*& org::sbolstandard::core2::Sequence::IUPAC_PROTEIN()
{
    clinit();
    return IUPAC_PROTEIN_;
}
java::net::URI* org::sbolstandard::core2::Sequence::IUPAC_PROTEIN_;

java::net::URI*& org::sbolstandard::core2::Sequence::SMILES()
{
    clinit();
    return SMILES_;
}
java::net::URI* org::sbolstandard::core2::Sequence::SMILES_;

void org::sbolstandard::core2::Sequence::ctor(::java::net::URI* identity, ::java::lang::String* elements, ::java::net::URI* encoding)
{
    super::ctor(identity);
    setElements(elements);
    setEncoding(encoding);
}

void org::sbolstandard::core2::Sequence::ctor(Sequence* sequence)
{
    super::ctor(static_cast< TopLevel* >(sequence));
    this->setElements(npc(sequence)->getElements());
    this->setEncoding(npc(sequence)->getEncoding());
}

java::lang::String* org::sbolstandard::core2::Sequence::getElements()
{
    return elements;
}

void org::sbolstandard::core2::Sequence::setElements(::java::lang::String* elements)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(elements == nullptr) {
        throw new ::java::lang::IllegalArgumentException(u"Sequence is required to have elements."_j);
    }
    this->elements = elements;
}

java::net::URI* org::sbolstandard::core2::Sequence::getEncoding()
{
    return encoding;
}

void org::sbolstandard::core2::Sequence::setEncoding(::java::net::URI* encoding)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(encoding == nullptr) {
        throw new ::java::lang::IllegalArgumentException(u"Sequence is required to have an encoding."_j);
    }
    this->encoding = encoding;
}

int32_t org::sbolstandard::core2::Sequence::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((elements == nullptr) ? int32_t(0) : npc(elements)->hashCode());
    result = prime * result + ((encoding == nullptr) ? int32_t(0) : npc(encoding)->hashCode());
    return result;
}

bool org::sbolstandard::core2::Sequence::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< Sequence* >(obj);
    if(elements == nullptr) {
        if(npc(other)->elements != nullptr)
            return false;

    } else if(!npc(elements)->equals(static_cast< ::java::lang::Object* >(npc(other)->elements)))
        return false;

    if(encoding == nullptr) {
        if(npc(other)->encoding != nullptr)
            return false;

    } else if(!npc(encoding)->equals(static_cast< ::java::lang::Object* >(npc(other)->encoding)))
        return false;

    return true;
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::Sequence::deepCopy()
{
    return new Sequence(this);
}

org::sbolstandard::core2::Sequence* org::sbolstandard::core2::Sequence::copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    auto cloned = this->deepCopy();
    npc(cloned)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    npc(cloned)->setDisplayId(displayId);
    npc(cloned)->setVersion(version);
    auto newIdentity = URIcompliance::createCompliantURI(URIprefix, displayId, version);
    if(!npc(this->getIdentity())->equals(static_cast< ::java::lang::Object* >(newIdentity))) {
        npc(cloned)->setWasDerivedFrom(this->getIdentity());
    } else {
        npc(cloned)->setWasDerivedFrom(this->getWasDerivedFrom());
    }
    npc(cloned)->setIdentity(newIdentity);
    return cloned;
}

bool org::sbolstandard::core2::Sequence::checkDescendantsURIcompliance()
{
    return URIcompliance::isTopLevelURIformCompliant(this->getIdentity());
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Sequence::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Sequence", 31);
    return c;
}

void org::sbolstandard::core2::Sequence::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        IUPAC_DNA_ = ::java::net::URI::create(u"http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html"_j);
        IUPAC_RNA_ = ::java::net::URI::create(u"http://www.chem.qmul.ac.uk/iubmb/misc/naseq.html"_j);
        IUPAC_PROTEIN_ = ::java::net::URI::create(u"http://www.chem.qmul.ac.uk/iupac/AminoAcid/"_j);
        SMILES_ = ::java::net::URI::create(u"http://www.opensmiles.org/opensmiles.html"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Sequence::getClass0()
{
    return class_();
}


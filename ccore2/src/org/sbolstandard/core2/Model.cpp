// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Model.java
#include <org/sbolstandard/core2/Model.hpp>

#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
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

org::sbolstandard::core2::Model::Model(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Model::Model(::java::net::URI* identity, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework) 
    : Model(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,source,language,framework);
}

org::sbolstandard::core2::Model::Model(Model* model) 
    : Model(*static_cast< ::default_init_tag* >(0))
{
    ctor(model);
}

java::net::URI*& org::sbolstandard::core2::Model::SBML()
{
    clinit();
    return SBML_;
}
java::net::URI* org::sbolstandard::core2::Model::SBML_;

java::net::URI*& org::sbolstandard::core2::Model::CELLML()
{
    clinit();
    return CELLML_;
}
java::net::URI* org::sbolstandard::core2::Model::CELLML_;

java::net::URI*& org::sbolstandard::core2::Model::BIOPAX()
{
    clinit();
    return BIOPAX_;
}
java::net::URI* org::sbolstandard::core2::Model::BIOPAX_;

void org::sbolstandard::core2::Model::ctor(::java::net::URI* identity, ::java::net::URI* source, ::java::net::URI* language, ::java::net::URI* framework)
{
    super::ctor(identity);
    setSource(source);
    setLanguage(language);
    setFramework(framework);
}

void org::sbolstandard::core2::Model::ctor(Model* model)
{
    super::ctor(static_cast< TopLevel* >(model));
    this->setSource(npc(model)->getSource());
    this->setLanguage(npc(model)->getLanguage());
    this->setFramework(npc(model)->getFramework());
}

java::net::URI* org::sbolstandard::core2::Model::getSource()
{
    return source;
}

void org::sbolstandard::core2::Model::setSource(::java::net::URI* source)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(source == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Model '"_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u"' must specify a source location."_j)->toString());
    }
    this->source = source;
}

java::net::URI* org::sbolstandard::core2::Model::getLanguage()
{
    return language;
}

void org::sbolstandard::core2::Model::setLanguage(::java::net::URI* language)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(language == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Model '"_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u"' must specify a language."_j)->toString());
    }
    this->language = language;
}

java::net::URI* org::sbolstandard::core2::Model::getFramework()
{
    return framework;
}

void org::sbolstandard::core2::Model::setFramework(::java::net::URI* framework)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(framework == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Model '"_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u"' must specify a framework."_j)->toString());
    }
    this->framework = framework;
}

int32_t org::sbolstandard::core2::Model::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((framework == nullptr) ? int32_t(0) : npc(framework)->hashCode());
    result = prime * result + ((language == nullptr) ? int32_t(0) : npc(language)->hashCode());
    result = prime * result + ((source == nullptr) ? int32_t(0) : npc(source)->hashCode());
    return result;
}

bool org::sbolstandard::core2::Model::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< Model* >(obj);
    if(framework == nullptr) {
        if(npc(other)->framework != nullptr)
            return false;

    } else if(!npc(framework)->equals(static_cast< ::java::lang::Object* >(npc(other)->framework)))
        return false;

    if(language == nullptr) {
        if(npc(other)->language != nullptr)
            return false;

    } else if(!npc(language)->equals(static_cast< ::java::lang::Object* >(npc(other)->language)))
        return false;

    if(source == nullptr) {
        if(npc(other)->source != nullptr)
            return false;

    } else if(!npc(source)->equals(static_cast< ::java::lang::Object* >(npc(other)->source)))
        return false;

    return true;
}

org::sbolstandard::core2::Model* org::sbolstandard::core2::Model::deepCopy()
{
    return new Model(this);
}

org::sbolstandard::core2::Model* org::sbolstandard::core2::Model::copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
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

bool org::sbolstandard::core2::Model::checkDescendantsURIcompliance()
{
    return URIcompliance::isTopLevelURIformCompliant(this->getIdentity());
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Model::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Model", 28);
    return c;
}

void org::sbolstandard::core2::Model::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        SBML_ = ::java::net::URI::create(u"http://identifiers.org/edam/format_2585"_j);
        CELLML_ = ::java::net::URI::create(u"http://identifiers.org/edam/format_3240"_j);
        BIOPAX_ = ::java::net::URI::create(u"http://identifiers.org/edam/format_3156"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::Model::getClass0()
{
    return class_();
}


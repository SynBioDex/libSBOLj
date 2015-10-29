// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SequenceConstraint.java
#include <org/sbolstandard/core2/SequenceConstraint.hpp>

#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <org/sbolstandard/core2/Component.hpp>
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/RestrictionType.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
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

org::sbolstandard::core2::SequenceConstraint::SequenceConstraint(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SequenceConstraint::SequenceConstraint(::java::net::URI* identity, ::java::net::URI* restriction, ::java::net::URI* subject, ::java::net::URI* object) 
    : SequenceConstraint(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,restriction,subject,object);
}

org::sbolstandard::core2::SequenceConstraint::SequenceConstraint(::java::net::URI* identity, RestrictionType* restriction, ::java::net::URI* subject, ::java::net::URI* object) 
    : SequenceConstraint(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,restriction,subject,object);
}

org::sbolstandard::core2::SequenceConstraint::SequenceConstraint(SequenceConstraint* sequenceConstraint) 
    : SequenceConstraint(*static_cast< ::default_init_tag* >(0))
{
    ctor(sequenceConstraint);
}

void org::sbolstandard::core2::SequenceConstraint::init()
{
    componentDefinition = nullptr;
}

void org::sbolstandard::core2::SequenceConstraint::ctor(::java::net::URI* identity, ::java::net::URI* restriction, ::java::net::URI* subject, ::java::net::URI* object)
{
    super::ctor(identity);
    init();
    setRestriction(restriction);
    setSubject(subject);
    setObject(object);
}

void org::sbolstandard::core2::SequenceConstraint::ctor(::java::net::URI* identity, RestrictionType* restriction, ::java::net::URI* subject, ::java::net::URI* object)
{
    super::ctor(identity);
    init();
    setRestriction(restriction);
    setSubject(subject);
    setObject(object);
}

void org::sbolstandard::core2::SequenceConstraint::ctor(SequenceConstraint* sequenceConstraint)
{
    super::ctor(static_cast< Identified* >(sequenceConstraint));
    init();
    this->setRestriction(npc(sequenceConstraint)->getRestrictionURI());
    this->setSubject(npc(sequenceConstraint)->getSubjectURI());
    this->setObject(npc(sequenceConstraint)->getObjectURI());
}

org::sbolstandard::core2::RestrictionType* org::sbolstandard::core2::SequenceConstraint::getRestriction()
{
    return RestrictionType::convertToRestrictionType(restriction);
}

java::net::URI* org::sbolstandard::core2::SequenceConstraint::getRestrictionURI()
{
    return restriction;
}

void org::sbolstandard::core2::SequenceConstraint::setRestriction(RestrictionType* restriction)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(restriction == nullptr) {
        throw new ::java::lang::NullPointerException(u"Not a valid restriction type."_j);
    }
    this->restriction = RestrictionType::convertToURI(restriction);
}

void org::sbolstandard::core2::SequenceConstraint::setRestriction(::java::net::URI* restrictionURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(restrictionURI == nullptr) {
        throw new ::java::lang::NullPointerException(u"Not a valid restriction type."_j);
    }
    this->restriction = restrictionURI;
}

java::net::URI* org::sbolstandard::core2::SequenceConstraint::getSubjectURI()
{
    return subject;
}

org::sbolstandard::core2::Component* org::sbolstandard::core2::SequenceConstraint::getSubject()
{
    if(componentDefinition == nullptr)
        return nullptr;

    return npc(componentDefinition)->getComponent(subject);
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SequenceConstraint::getSubjectDefinition()
{
    if(componentDefinition != nullptr) {
        return npc(npc(componentDefinition)->getComponent(subject))->getDefinition();
    }
    return nullptr;
}

void org::sbolstandard::core2::SequenceConstraint::setSubject(::java::net::URI* subjectURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(componentDefinition != nullptr) {
        if(npc(componentDefinition)->getComponent(subjectURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(subjectURI))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    if(subjectURI == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Sequence constraint '"_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u"' must have a subject."_j)->toString());
    }
    if(subjectURI == object) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Sequence constraint '"_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u"' must have different subject and object."_j)->toString());
    }
    this->subject = subjectURI;
}

java::net::URI* org::sbolstandard::core2::SequenceConstraint::getObjectURI()
{
    return object;
}

org::sbolstandard::core2::Component* org::sbolstandard::core2::SequenceConstraint::getObject()
{
    if(componentDefinition == nullptr)
        return nullptr;

    return npc(componentDefinition)->getComponent(object);
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SequenceConstraint::getObjectDefinition()
{
    if(componentDefinition != nullptr) {
        return npc(npc(componentDefinition)->getComponent(object))->getDefinition();
    }
    return nullptr;
}

void org::sbolstandard::core2::SequenceConstraint::setObject(::java::net::URI* objectURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(componentDefinition != nullptr) {
        if(npc(componentDefinition)->getComponent(objectURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(objectURI))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    if(objectURI == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Sequence constraint '"_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u"' must have an object."_j)->toString());
    }
    if(objectURI == subject) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Sequence constraint '"_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u"' must have different subject and object."_j)->toString());
    }
    this->object = objectURI;
}

int32_t org::sbolstandard::core2::SequenceConstraint::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((object == nullptr) ? int32_t(0) : npc(object)->hashCode());
    result = prime * result + ((restriction == nullptr) ? int32_t(0) : npc(restriction)->hashCode());
    result = prime * result + ((subject == nullptr) ? int32_t(0) : npc(subject)->hashCode());
    return result;
}

bool org::sbolstandard::core2::SequenceConstraint::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< SequenceConstraint* >(obj);
    if(object == nullptr) {
        if(npc(other)->object != nullptr)
            return false;

    } else if(!npc(object)->equals(static_cast< ::java::lang::Object* >(npc(other)->object)))
        return false;

    if(!npc(restriction)->equals(static_cast< ::java::lang::Object* >(npc(other)->restriction)))
        return false;

    if(subject == nullptr) {
        if(npc(other)->subject != nullptr)
            return false;

    } else if(!npc(subject)->equals(static_cast< ::java::lang::Object* >(npc(other)->subject)))
        return false;

    return true;
}

org::sbolstandard::core2::SequenceConstraint* org::sbolstandard::core2::SequenceConstraint::deepCopy()
{
    return new SequenceConstraint(this);
}

void org::sbolstandard::core2::SequenceConstraint::updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    if(!npc(this->getIdentity())->equals(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(URIprefix, displayId, version)))) {
        this->setWasDerivedFrom(this->getIdentity());
    }
    this->setIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, version));
    this->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    this->setDisplayId(displayId);
    this->setVersion(version);
    auto subjectId = URIcompliance::extractDisplayId(subject);
    this->setSubject(URIcompliance::createCompliantURI(URIprefix, subjectId, version));
    auto objectId = URIcompliance::extractDisplayId(object);
    this->setObject(URIcompliance::createCompliantURI(URIprefix, objectId, version));
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SequenceConstraint::getComponentDefinition()
{
    return componentDefinition;
}

void org::sbolstandard::core2::SequenceConstraint::setComponentDefinition(ComponentDefinition* componentDefinition)
{
    this->componentDefinition = componentDefinition;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SequenceConstraint::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SequenceConstraint", 41);
    return c;
}

java::lang::Class* org::sbolstandard::core2::SequenceConstraint::getClass0()
{
    return class_();
}


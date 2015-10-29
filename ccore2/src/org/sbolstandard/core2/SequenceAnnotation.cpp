// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SequenceAnnotation.java
#include <org/sbolstandard/core2/SequenceAnnotation.hpp>

#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <java/util/Collection.hpp>
#include <java/util/HashMap.hpp>
#include <java/util/HashSet.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/List.hpp>
#include <java/util/Map.hpp>
#include <java/util/Set.hpp>
#include <org/sbolstandard/core2/AccessType.hpp>
#include <org/sbolstandard/core2/Component.hpp>
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/Cut.hpp>
#include <org/sbolstandard/core2/GenericLocation.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/Location.hpp>
#include <org/sbolstandard/core2/Range.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>
#include <ObjectArray.hpp>
#include <SubArray.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
typedef ::SubArray< ::java::util::Map, ::java::lang::ObjectArray > MapArray;
    } // util
} // java

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

org::sbolstandard::core2::SequenceAnnotation::SequenceAnnotation(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SequenceAnnotation::SequenceAnnotation(::java::net::URI* identity, ::java::util::List* locations) 
    : SequenceAnnotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,locations);
}

org::sbolstandard::core2::SequenceAnnotation::SequenceAnnotation(SequenceAnnotation* sequenceAnnotation) 
    : SequenceAnnotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(sequenceAnnotation);
}

void org::sbolstandard::core2::SequenceAnnotation::init()
{
    componentDefinition = nullptr;
}

void org::sbolstandard::core2::SequenceAnnotation::ctor(::java::net::URI* identity, ::java::util::List* locations)
{
    super::ctor(identity);
    init();
    this->locations = new ::java::util::HashMap();
    this->setLocations(locations);
}

void org::sbolstandard::core2::SequenceAnnotation::ctor(SequenceAnnotation* sequenceAnnotation)
{
    super::ctor(static_cast< Identified* >(sequenceAnnotation));
    init();
    this->locations = new ::java::util::HashMap();
    for (auto _i = npc(npc(sequenceAnnotation)->getLocations())->iterator(); _i->hasNext(); ) {
        Location* location = java_cast< Location* >(_i->next());
        {
            addLocation(npc(location)->deepCopy());
        }
    }
    if(npc(sequenceAnnotation)->isSetComponent()) {
        this->setComponent(npc(sequenceAnnotation)->getComponentURI());
    }
}

void org::sbolstandard::core2::SequenceAnnotation::addGenericLocation(::java::lang::String* displayId)
{
    auto identity = URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion());
    auto genericLocation = new GenericLocation(identity);
    addLocation(genericLocation);
}

void org::sbolstandard::core2::SequenceAnnotation::addGenericLocation(::java::lang::String* displayId, OrientationType* orientation)
{
    auto identity = URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion());
    auto genericLocation = new GenericLocation(identity);
    npc(genericLocation)->setOrientation(orientation);
    addLocation(genericLocation);
}

void org::sbolstandard::core2::SequenceAnnotation::addCut(::java::lang::String* displayId, int32_t at)
{
    auto identity = URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion());
    auto cut = new Cut(identity, at);
    addLocation(cut);
}

void org::sbolstandard::core2::SequenceAnnotation::addCut(::java::lang::String* displayId, int32_t at, OrientationType* orientation)
{
    auto identity = URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion());
    auto cut = new Cut(identity, at);
    npc(cut)->setOrientation(orientation);
    addLocation(cut);
}

void org::sbolstandard::core2::SequenceAnnotation::addRange(::java::lang::String* displayId, int32_t start, int32_t end)
{
    auto identity = URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion());
    auto range = new Range(identity, start, end);
    addLocation(range);
}

void org::sbolstandard::core2::SequenceAnnotation::addRange(::java::lang::String* displayId, int32_t start, int32_t end, OrientationType* orientation)
{
    auto identity = URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion());
    auto range = new Range(identity, start, end);
    npc(range)->setOrientation(orientation);
    addLocation(range);
}

void org::sbolstandard::core2::SequenceAnnotation::addLocation(Location* location)
{
    addChildSafely(location, locations, u"location"_j, new ::java::util::MapArray());
    npc(location)->setSBOLDocument(this->sbolDocument);
}

bool org::sbolstandard::core2::SequenceAnnotation::removeLocation(Location* location)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(npc(locations)->size() == 1 && npc(locations)->containsValue(static_cast< ::java::lang::Object* >(location))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Sequence annotation "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have at least one location."_j)->toString());
    }
    return removeChildSafely(location, locations);
}

org::sbolstandard::core2::Location* org::sbolstandard::core2::SequenceAnnotation::getLocation(::java::lang::String* displayId)
{
    return java_cast< Location* >(npc(locations)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::Location* org::sbolstandard::core2::SequenceAnnotation::getLocation(::java::net::URI* locationURI)
{
    return java_cast< Location* >(npc(locations)->get(static_cast< ::java::lang::Object* >(locationURI)));
}

java::util::Set* org::sbolstandard::core2::SequenceAnnotation::getLocations()
{
    return new ::java::util::HashSet(static_cast< ::java::util::Collection* >(npc(locations)->values()));
}

void org::sbolstandard::core2::SequenceAnnotation::clearLocations()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(locations)->values())->toArray_();
    for(auto location : *npc(valueSetArray_)) {
        removeLocation(java_cast< Location* >(location));
    }
}

void org::sbolstandard::core2::SequenceAnnotation::setLocations(::java::util::List* locations)
{
    clearLocations();
    if(locations == nullptr)
        return;

    for (auto _i = npc(locations)->iterator(); _i->hasNext(); ) {
        Location* location = java_cast< Location* >(_i->next());
        {
            addLocation(location);
        }
    }
}

bool org::sbolstandard::core2::SequenceAnnotation::isSetComponent()
{
    return component != nullptr;
}

java::net::URI* org::sbolstandard::core2::SequenceAnnotation::getComponentURI()
{
    return component;
}

org::sbolstandard::core2::Component* org::sbolstandard::core2::SequenceAnnotation::getComponent()
{
    if(componentDefinition == nullptr)
        return nullptr;

    return npc(componentDefinition)->getComponent(component);
}

void org::sbolstandard::core2::SequenceAnnotation::setComponent(::java::lang::String* displayId)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto componentURI = URIcompliance::createCompliantURI(npc(npc(componentDefinition)->getPersistentIdentity())->toString(), displayId, npc(componentDefinition)->getVersion());
    if(sbolDocument != nullptr && npc(sbolDocument)->isCreateDefaults() && componentDefinition != nullptr && npc(componentDefinition)->getComponent(componentURI) == nullptr) {
        npc(componentDefinition)->createComponent(displayId, AccessType::PUBLIC, displayId, u""_j);
    }
    setComponent(componentURI);
}

void org::sbolstandard::core2::SequenceAnnotation::setComponent(::java::net::URI* componentURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(componentDefinition != nullptr) {
        if(npc(componentDefinition)->getComponent(componentURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(componentURI))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    this->component = componentURI;
}

void org::sbolstandard::core2::SequenceAnnotation::unsetComponent()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    component = nullptr;
}

int32_t org::sbolstandard::core2::SequenceAnnotation::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((component == nullptr) ? int32_t(0) : npc(component)->hashCode());
    result = prime * result + ((locations == nullptr) ? int32_t(0) : npc(locations)->hashCode());
    return result;
}

bool org::sbolstandard::core2::SequenceAnnotation::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< SequenceAnnotation* >(obj);
    if(component == nullptr) {
        if(npc(other)->component != nullptr)
            return false;

    } else if(!npc(component)->equals(static_cast< ::java::lang::Object* >(npc(other)->component)))
        return false;

    if(locations == nullptr) {
        if(npc(other)->locations != nullptr)
            return false;

    } else if(!npc(locations)->equals(static_cast< ::java::lang::Object* >(npc(other)->locations)))
        return false;

    return true;
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::SequenceAnnotation::deepCopy()
{
    return new SequenceAnnotation(this);
}

void org::sbolstandard::core2::SequenceAnnotation::updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    if(!npc(this->getIdentity())->equals(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(URIprefix, displayId, version)))) {
        this->setWasDerivedFrom(this->getIdentity());
    }
    this->setIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, version));
    this->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    this->setDisplayId(displayId);
    this->setVersion(version);
    auto componentId = URIcompliance::extractDisplayId(component);
    this->setComponent(URIcompliance::createCompliantURI(URIprefix, componentId, version));
    auto count = int32_t(0);
    for (auto _i = npc(this->getLocations())->iterator(); _i->hasNext(); ) {
        Location* location = java_cast< Location* >(_i->next());
        {
            if(!npc(location)->isSetDisplayId())
                npc(location)->setDisplayId(::java::lang::StringBuilder().append(u"location"_j)->append(++count)->toString());

            npc(location)->updateCompliantURI(npc(this->getPersistentIdentity())->toString(), npc(location)->getDisplayId(), version);
            this->removeChildSafely(location, java_cast< ::java::util::HashMap* >(this->locations));
            this->addLocation(location);
        }
    }
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::SequenceAnnotation::getComponentDefinition()
{
    if(componentDefinition != nullptr) {
        return npc(npc(componentDefinition)->getComponent(component))->getDefinition();
    }
    return nullptr;
}

void org::sbolstandard::core2::SequenceAnnotation::setComponentDefinition(ComponentDefinition* componentDefinition)
{
    this->componentDefinition = componentDefinition;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SequenceAnnotation::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SequenceAnnotation", 41);
    return c;
}

java::lang::Class* org::sbolstandard::core2::SequenceAnnotation::getClass0()
{
    return class_();
}


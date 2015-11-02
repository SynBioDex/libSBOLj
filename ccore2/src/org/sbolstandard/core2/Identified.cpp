// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Identified.java
#include <org/sbolstandard/core2/Identified.hpp>

#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <java/util/ArrayList.hpp>
#include <java/util/Collection.hpp>
#include <java/util/HashSet.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/List.hpp>
#include <java/util/Map.hpp>
#include <java/util/Set.hpp>
#include <org/sbolstandard/core2/Annotation.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/URIcompliance.hpp>
#include <org/sbolstandard/core2/Version.hpp>
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

org::sbolstandard::core2::Identified::Identified(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Identified::Identified(::java::net::URI* identity) 
    : Identified(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity);
}

org::sbolstandard::core2::Identified::Identified(Identified* identified) 
    : Identified(*static_cast< ::default_init_tag* >(0))
{
    ctor(identified);
}

void org::sbolstandard::core2::Identified::init()
{
    sbolDocument = nullptr;
}

void org::sbolstandard::core2::Identified::ctor(::java::net::URI* identity)
{
    super::ctor();
    init();
    setIdentity(identity);
    this->annotations = new ::java::util::ArrayList();
}

void org::sbolstandard::core2::Identified::ctor(Identified* identified)
{
    super::ctor();
    init();
    this->setIdentity(npc(identified)->getIdentity());
    this->annotations = new ::java::util::ArrayList();
    if(npc(identified)->hasAnnotations()) {
        ::java::util::List* clonedAnnotations = new ::java::util::ArrayList();
        for (auto _i = npc(npc(identified)->getAnnotations())->iterator(); _i->hasNext(); ) {
            Annotation* annotation = java_cast< Annotation* >(_i->next());
            {
                npc(clonedAnnotations)->add(static_cast< ::java::lang::Object* >(npc(annotation)->copy()));
            }
        }
        this->setAnnotations(clonedAnnotations);
    }
    if(npc(identified)->isSetDisplayId()) {
        this->setDisplayId(npc(identified)->getDisplayId());
    }
    if(npc(identified)->isSetVersion()) {
        this->setVersion(npc(identified)->getVersion());
    }
    if(npc(identified)->isSetPersistentIdentity()) {
        this->setPersistentIdentity(::java::net::URI::create(npc(npc(identified)->getPersistentIdentity())->toString()));
    }
    if(npc(identified)->isSetWasDerivedFrom()) {
        this->setWasDerivedFrom(::java::net::URI::create(npc(npc(identified)->getWasDerivedFrom())->toString()));
    }
    if(npc(identified)->isSetName()) {
        this->setName(npc(identified)->getName());
    }
    if(npc(identified)->isSetDescription()) {
        this->setDescription(npc(identified)->getDescription());
    }
}

java::net::URI* org::sbolstandard::core2::Identified::getIdentity()
{
    return identity;
}

void org::sbolstandard::core2::Identified::setIdentity(::java::net::URI* identity)
{
    if(identity == nullptr) {
        throw new ::java::lang::IllegalArgumentException(u"Identity is a required field."_j);
    }
    this->identity = identity;
}

bool org::sbolstandard::core2::Identified::isSetPersistentIdentity()
{
    return persistentIdentity != nullptr;
}

java::net::URI* org::sbolstandard::core2::Identified::getPersistentIdentity()
{
    return persistentIdentity;
}

void org::sbolstandard::core2::Identified::setPersistentIdentity(::java::net::URI* persistentIdentity)
{
    this->persistentIdentity = persistentIdentity;
}

void org::sbolstandard::core2::Identified::unsetPersistentIdentity()
{
    persistentIdentity = nullptr;
}

bool org::sbolstandard::core2::Identified::isSetVersion()
{
    return version != nullptr;
}

bool org::sbolstandard::core2::Identified::isSetWasDerivedFrom()
{
    return wasDerivedFrom != nullptr;
}

java::lang::String* org::sbolstandard::core2::Identified::getVersion()
{
    return version;
}

void org::sbolstandard::core2::Identified::setVersion(::java::lang::String* version)
{
    if(version == nullptr || npc(version)->equals(static_cast< ::java::lang::Object* >(u""_j)))
        return;

    if(!URIcompliance::isVersionCompliant(version)) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Version "_j)->append(version)
            ->append(u" is invalid for `"_j)
            ->append(static_cast< ::java::lang::Object* >(identity))
            ->append(u"'."_j)->toString());
    }
    this->version = version;
}

bool org::sbolstandard::core2::Identified::isSetDisplayId()
{
    return displayId != nullptr;
}

java::lang::String* org::sbolstandard::core2::Identified::getDisplayId()
{
    return displayId;
}

void org::sbolstandard::core2::Identified::setDisplayId(::java::lang::String* displayId)
{
    if(!URIcompliance::isDisplayIdCompliant(displayId)) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Display id "_j)->append(displayId)
            ->append(u" is invalid for `"_j)
            ->append(static_cast< ::java::lang::Object* >(identity))
            ->append(u"'."_j)->toString());
    }
    this->displayId = displayId;
}

void org::sbolstandard::core2::Identified::unsetDisplayId()
{
    displayId = nullptr;
}

java::net::URI* org::sbolstandard::core2::Identified::getWasDerivedFrom()
{
    return wasDerivedFrom;
}

void org::sbolstandard::core2::Identified::setWasDerivedFrom(::java::net::URI* wasDerivedFrom)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    this->wasDerivedFrom = wasDerivedFrom;
}

bool org::sbolstandard::core2::Identified::hasAnnotations()
{
    return !npc(annotations)->isEmpty();
}

org::sbolstandard::core2::Annotation* org::sbolstandard::core2::Identified::createAnnotation(::javax::xml::namespace_::QName* qName, ::java::lang::String* literal)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto annotation = new Annotation(qName, literal);
    addAnnotation(annotation);
    return annotation;
}

org::sbolstandard::core2::Annotation* org::sbolstandard::core2::Identified::createAnnotation(::javax::xml::namespace_::QName* qName, double literal)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto annotation = new Annotation(qName, literal);
    addAnnotation(annotation);
    return annotation;
}

org::sbolstandard::core2::Annotation* org::sbolstandard::core2::Identified::createAnnotation(::javax::xml::namespace_::QName* qName, int32_t literal)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto annotation = new Annotation(qName, literal);
    addAnnotation(annotation);
    return annotation;
}

org::sbolstandard::core2::Annotation* org::sbolstandard::core2::Identified::createAnnotation(::javax::xml::namespace_::QName* qName, bool literal)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto annotation = new Annotation(qName, literal);
    addAnnotation(annotation);
    return annotation;
}

org::sbolstandard::core2::Annotation* org::sbolstandard::core2::Identified::createAnnotation(::javax::xml::namespace_::QName* qName, ::java::net::URI* literal)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto annotation = new Annotation(qName, literal);
    addAnnotation(annotation);
    return annotation;
}

org::sbolstandard::core2::Annotation* org::sbolstandard::core2::Identified::createAnnotation(::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty)
{
    auto annotation = new Annotation(namedProperty);
    addAnnotation(annotation);
    return annotation;
}

org::sbolstandard::core2::Annotation* org::sbolstandard::core2::Identified::createAnnotation(::javax::xml::namespace_::QName* qName, ::javax::xml::namespace_::QName* nestedQName, ::java::net::URI* nestedURI, ::java::util::List* annotations)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto annotation = new Annotation(qName, nestedQName, nestedURI, annotations);
    addAnnotation(annotation);
    return annotation;
}

void org::sbolstandard::core2::Identified::addAnnotation(Annotation* annotation)
{
    if(npc(annotations)->contains(static_cast< ::java::lang::Object* >(annotation))) {
        throw new ::java::lang::IllegalArgumentException(u"Annotation already exists."_j);
    }
    npc(annotations)->add(static_cast< ::java::lang::Object* >(annotation));
}

bool org::sbolstandard::core2::Identified::removeAnnotation(Annotation* annotation)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(annotations)->remove(static_cast< ::java::lang::Object* >(annotation));
}

java::util::List* org::sbolstandard::core2::Identified::getAnnotations()
{
    ::java::util::List* annotations = new ::java::util::ArrayList();
    npc(annotations)->addAll(static_cast< ::java::util::Collection* >(java_cast< ::java::util::List* >(this->annotations)));
    return annotations;
}

void org::sbolstandard::core2::Identified::clearAnnotations()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    npc(annotations)->clear();
}

void org::sbolstandard::core2::Identified::setAnnotations(::java::util::List* annotations)
{
    clearAnnotations();
    for (auto _i = npc(annotations)->iterator(); _i->hasNext(); ) {
        Annotation* annotation = java_cast< Annotation* >(_i->next());
        {
            addAnnotation(annotation);
        }
    }
}

void org::sbolstandard::core2::Identified::unsetWasDerivedFrom()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    wasDerivedFrom = nullptr;
}

void org::sbolstandard::core2::Identified::setSBOLDocument(SBOLDocument* sbolDocument)
{
    this->sbolDocument = sbolDocument;
}

org::sbolstandard::core2::SBOLDocument* org::sbolstandard::core2::Identified::getSBOLDocument()
{
    return sbolDocument;
}

int32_t org::sbolstandard::core2::Identified::hashCode()
{
    auto const prime = int32_t(31);
    auto result = int32_t(1);
    result = prime * result + ((annotations == nullptr) ? int32_t(0) : npc(annotations)->hashCode());
    result = prime * result + ((identity == nullptr) ? int32_t(0) : npc(identity)->hashCode());
    result = prime * result + ((persistentIdentity == nullptr) ? int32_t(0) : npc(persistentIdentity)->hashCode());
    result = prime * result + ((version == nullptr) ? int32_t(0) : npc(version)->hashCode());
    result = prime * result + ((wasDerivedFrom == nullptr) ? int32_t(0) : npc(wasDerivedFrom)->hashCode());
    result = prime * result + ((displayId == nullptr) ? int32_t(0) : npc(displayId)->hashCode());
    result = prime * result + ((description == nullptr) ? int32_t(0) : npc(description)->hashCode());
    result = prime * result + ((name == nullptr) ? int32_t(0) : npc(name)->hashCode());
    return result;
}

bool org::sbolstandard::core2::Identified::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(obj == nullptr)
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< Identified* >(obj);
    if(annotations == nullptr) {
        if(npc(other)->annotations != nullptr)
            return false;

    } else if(!npc(annotations)->containsAll(static_cast< ::java::util::Collection* >(npc(other)->annotations)))
        return false;

    if(identity == nullptr) {
        if(npc(other)->identity != nullptr)
            return false;

    } else if(!npc(identity)->equals(static_cast< ::java::lang::Object* >(npc(other)->identity)))
        return false;

    if(persistentIdentity == nullptr) {
        if(npc(other)->persistentIdentity != nullptr)
            return false;

    } else if(!npc(persistentIdentity)->equals(static_cast< ::java::lang::Object* >(npc(other)->persistentIdentity)))
        return false;

    if(version == nullptr) {
        if(npc(other)->version != nullptr)
            return false;

    } else if(!npc(version)->equals(static_cast< ::java::lang::Object* >(npc(other)->version)))
        return false;

    if(wasDerivedFrom == nullptr) {
        if(npc(other)->wasDerivedFrom != nullptr)
            return false;

    } else if(!npc(wasDerivedFrom)->equals(static_cast< ::java::lang::Object* >(npc(other)->wasDerivedFrom)))
        return false;

    if(description == nullptr) {
        if(npc(other)->description != nullptr)
            return false;

    } else if(!npc(description)->equals(static_cast< ::java::lang::Object* >(npc(other)->description)))
        return false;

    if(displayId == nullptr) {
        if(npc(other)->displayId != nullptr)
            return false;

    } else if(!npc(displayId)->equals(static_cast< ::java::lang::Object* >(npc(other)->displayId)))
        return false;

    if(name == nullptr) {
        if(npc(other)->name != nullptr)
            return false;

    } else if(!npc(name)->equals(static_cast< ::java::lang::Object* >(npc(other)->name)))
        return false;

    return true;
}

/* <I extends Identified> */void org::sbolstandard::core2::Identified::addChildSafely(Identified* child, ::java::util::Map* siblingsMap, ::java::lang::String* typeName, ::java::util::MapArray*/*...*/ maps)
{
    if(URIcompliance::isChildURIformCompliant(this->getIdentity(), npc(child)->getIdentity())) {
        auto persistentId = ::java::net::URI::create(URIcompliance::extractPersistentId(npc(child)->getIdentity()));
        if(URIcompliance::keyExistsInAnyMap(persistentId, maps))
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Instance for identity `"_j)->append(static_cast< ::java::lang::Object* >(npc(child)->identity))
                ->append(u"' and persistent identity `"_j)
                ->append(static_cast< ::java::lang::Object* >(persistentId))
                ->append(u"' exists for a non-"_j)
                ->append(typeName)->toString());

        if(npc(siblingsMap)->containsKey(npc(child)->getIdentity()))
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Instance for identity `"_j)->append(static_cast< ::java::lang::Object* >(npc(child)->identity))
                ->append(u"' and persistent identity `"_j)
                ->append(static_cast< ::java::lang::Object* >(persistentId))
                ->append(u"' exists for a "_j)
                ->append(typeName)->toString());

        npc(siblingsMap)->put(npc(child)->getIdentity(), child);
        auto latest = java_cast< Identified* >(npc(siblingsMap)->get(persistentId));
        if(latest == nullptr) {
            npc(siblingsMap)->put(persistentId, child);
        } else {
            if(Version::isFirstVersionNewer(URIcompliance::extractVersion(npc(child)->getIdentity()), URIcompliance::extractVersion(npc(latest)->getIdentity()))) {
                npc(siblingsMap)->put(persistentId, child);
            }
        }
    } else {
        if(URIcompliance::keyExistsInAnyMap(npc(child)->getIdentity(), maps))
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Instance for identity `"_j)->append(static_cast< ::java::lang::Object* >(npc(child)->identity))
                ->append(u"' exists for a non-"_j)
                ->append(typeName)->toString());

        if(npc(siblingsMap)->containsKey(npc(child)->getIdentity()))
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Instance for identity `"_j)->append(static_cast< ::java::lang::Object* >(npc(child)->identity))
                ->append(u"' exists for a "_j)
                ->append(typeName)->toString());

        npc(siblingsMap)->put(npc(child)->getIdentity(), child);
    }
}

/* <I extends Identified> */bool org::sbolstandard::core2::Identified::removeChildSafely(Identified* identified, ::java::util::Map* siblingsMap)
{
    ::java::util::Set* objectsToRemove = new ::java::util::HashSet();
    npc(objectsToRemove)->add(static_cast< ::java::lang::Object* >(identified));
    return npc(npc(siblingsMap)->values())->removeAll(objectsToRemove);
}

bool org::sbolstandard::core2::Identified::isSetName()
{
    return name != nullptr;
}

java::lang::String* org::sbolstandard::core2::Identified::getName()
{
    return name;
}

void org::sbolstandard::core2::Identified::setName(::java::lang::String* name)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    this->name = name;
}

void org::sbolstandard::core2::Identified::unsetName()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    name = nullptr;
}

bool org::sbolstandard::core2::Identified::isSetDescription()
{
    return description != nullptr;
}

java::lang::String* org::sbolstandard::core2::Identified::getDescription()
{
    return description;
}

void org::sbolstandard::core2::Identified::setDescription(::java::lang::String* description)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    this->description = description;
}

void org::sbolstandard::core2::Identified::unsetDescription()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    description = nullptr;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Identified::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Identified", 33);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Identified::getClass0()
{
    return class_();
}


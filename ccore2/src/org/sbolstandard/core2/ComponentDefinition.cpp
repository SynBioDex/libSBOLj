// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComponentDefinition.java
#include <org/sbolstandard/core2/ComponentDefinition.hpp>

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
#include <java/util/HashMap.hpp>
#include <java/util/HashSet.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/List.hpp>
#include <java/util/Map.hpp>
#include <java/util/Set.hpp>
#include <org/sbolstandard/core2/AccessType.hpp>
#include <org/sbolstandard/core2/Component.hpp>
#include <org/sbolstandard/core2/Cut.hpp>
#include <org/sbolstandard/core2/GenericLocation.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/Location.hpp>
#include <org/sbolstandard/core2/MapsTo.hpp>
#include <org/sbolstandard/core2/OrientationType.hpp>
#include <org/sbolstandard/core2/Range.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>
#include <org/sbolstandard/core2/SBOLValidationException.hpp>
#include <org/sbolstandard/core2/Sequence.hpp>
#include <org/sbolstandard/core2/SequenceAnnotation.hpp>
#include <org/sbolstandard/core2/SequenceConstraint.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>
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

namespace org
{
    namespace sbolstandard
    {
        namespace core2
        {
typedef ::SubArray< ::org::sbolstandard::core2::Identified, ::java::lang::ObjectArray > IdentifiedArray;
        } // core2
    } // sbolstandard
} // org

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

org::sbolstandard::core2::ComponentDefinition::ComponentDefinition(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::ComponentDefinition::ComponentDefinition(::java::net::URI* identity, ::java::util::Set* types) 
    : ComponentDefinition(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,types);
}

org::sbolstandard::core2::ComponentDefinition::ComponentDefinition(ComponentDefinition* componentDefinition) 
    : ComponentDefinition(*static_cast< ::default_init_tag* >(0))
{
    ctor(componentDefinition);
}

java::net::URI*& org::sbolstandard::core2::ComponentDefinition::DNA()
{
    clinit();
    return DNA_;
}
java::net::URI* org::sbolstandard::core2::ComponentDefinition::DNA_;

java::net::URI*& org::sbolstandard::core2::ComponentDefinition::RNA()
{
    clinit();
    return RNA_;
}
java::net::URI* org::sbolstandard::core2::ComponentDefinition::RNA_;

java::net::URI*& org::sbolstandard::core2::ComponentDefinition::PROTEIN()
{
    clinit();
    return PROTEIN_;
}
java::net::URI* org::sbolstandard::core2::ComponentDefinition::PROTEIN_;

java::net::URI*& org::sbolstandard::core2::ComponentDefinition::SMALL_MOLECULE()
{
    clinit();
    return SMALL_MOLECULE_;
}
java::net::URI* org::sbolstandard::core2::ComponentDefinition::SMALL_MOLECULE_;

java::net::URI*& org::sbolstandard::core2::ComponentDefinition::EFFECTOR()
{
    clinit();
    return EFFECTOR_;
}
java::net::URI* org::sbolstandard::core2::ComponentDefinition::EFFECTOR_;

void org::sbolstandard::core2::ComponentDefinition::ctor(::java::net::URI* identity, ::java::util::Set* types)
{
    super::ctor(identity);
    this->types = new ::java::util::HashSet();
    this->roles = new ::java::util::HashSet();
    this->sequences = new ::java::util::HashSet();
    this->components = new ::java::util::HashMap();
    this->sequenceAnnotations = new ::java::util::HashMap();
    this->sequenceConstraints = new ::java::util::HashMap();
    setTypes(types);
}

void org::sbolstandard::core2::ComponentDefinition::ctor(ComponentDefinition* componentDefinition)
{
    super::ctor(static_cast< TopLevel* >(componentDefinition));
    this->types = new ::java::util::HashSet();
    this->roles = new ::java::util::HashSet();
    this->sequences = new ::java::util::HashSet();
    this->components = new ::java::util::HashMap();
    this->sequenceAnnotations = new ::java::util::HashMap();
    this->sequenceConstraints = new ::java::util::HashMap();
    for (auto _i = npc(npc(componentDefinition)->getTypes())->iterator(); _i->hasNext(); ) {
        ::java::net::URI* type = java_cast< ::java::net::URI* >(_i->next());
        {
            this->addType(::java::net::URI::create(npc(type)->toString()));
        }
    }
    for (auto _i = npc(npc(componentDefinition)->getRoles())->iterator(); _i->hasNext(); ) {
        ::java::net::URI* role = java_cast< ::java::net::URI* >(_i->next());
        {
            this->addRole(::java::net::URI::create(npc(role)->toString()));
        }
    }
    for (auto _i = npc(npc(componentDefinition)->getComponents())->iterator(); _i->hasNext(); ) {
        Component* subComponent = java_cast< Component* >(_i->next());
        {
            this->addComponent(npc(subComponent)->deepCopy());
        }
    }
    for (auto _i = npc(npc(componentDefinition)->getSequenceConstraints())->iterator(); _i->hasNext(); ) {
        SequenceConstraint* sequenceConstraint = java_cast< SequenceConstraint* >(_i->next());
        {
            this->addSequenceConstraint(npc(sequenceConstraint)->deepCopy());
        }
    }
    for (auto _i = npc(npc(componentDefinition)->getSequenceAnnotations())->iterator(); _i->hasNext(); ) {
        SequenceAnnotation* sequenceAnnotation = java_cast< SequenceAnnotation* >(_i->next());
        {
            this->addSequenceAnnotation(npc(sequenceAnnotation)->deepCopy());
        }
    }
    this->setSequences(npc(componentDefinition)->getSequenceURIs());
}

bool org::sbolstandard::core2::ComponentDefinition::addType(::java::net::URI* typeURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(npc(typeURI)->equals(static_cast< ::java::lang::Object* >(DNA_)) || npc(typeURI)->equals(static_cast< ::java::lang::Object* >(RNA_)) || npc(typeURI)->equals(static_cast< ::java::lang::Object* >(PROTEIN_))|| npc(typeURI)->equals(static_cast< ::java::lang::Object* >(SMALL_MOLECULE_))) {
        if(this->containsType(DNA_) || this->containsType(RNA_) || this->containsType(PROTEIN_)|| this->containsType(SMALL_MOLECULE_)) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component definition "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
                ->append(u" must have only one type from Table 2 in the specification."_j)->toString());
        }
    }
    return npc(types)->add(static_cast< ::java::lang::Object* >(typeURI));
}

bool org::sbolstandard::core2::ComponentDefinition::removeType(::java::net::URI* typeURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(npc(types)->size() == 1 && npc(types)->contains(static_cast< ::java::lang::Object* >(typeURI))) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component definition "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have at least one type."_j)->toString());
    }
    return npc(types)->remove(static_cast< ::java::lang::Object* >(typeURI));
}

void org::sbolstandard::core2::ComponentDefinition::setTypes(::java::util::Set* types)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(types == nullptr || npc(types)->size() == 0) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component definition "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have at least one type."_j)->toString());
    }
    clearTypes();
    for (auto _i = npc(types)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* type = java_cast< ::java::net::URI* >(_i->next());
        {
            addType(type);
        }
    }
}

java::util::Set* org::sbolstandard::core2::ComponentDefinition::getTypes()
{
    ::java::util::Set* result = new ::java::util::HashSet();
    npc(result)->addAll(static_cast< ::java::util::Collection* >(types));
    return result;
}

bool org::sbolstandard::core2::ComponentDefinition::containsType(::java::net::URI* typeURI)
{
    return npc(types)->contains(static_cast< ::java::lang::Object* >(typeURI));
}

void org::sbolstandard::core2::ComponentDefinition::clearTypes()
{
    npc(types)->clear();
}

bool org::sbolstandard::core2::ComponentDefinition::addRole(::java::net::URI* roleURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(roles)->add(static_cast< ::java::lang::Object* >(roleURI));
}

bool org::sbolstandard::core2::ComponentDefinition::removeRole(::java::net::URI* roleURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(roles)->remove(static_cast< ::java::lang::Object* >(roleURI));
}

void org::sbolstandard::core2::ComponentDefinition::setRoles(::java::util::Set* roles)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    clearRoles();
    if(roles == nullptr)
        return;

    for (auto _i = npc(roles)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* role = java_cast< ::java::net::URI* >(_i->next());
        {
            addRole(role);
        }
    }
}

java::util::Set* org::sbolstandard::core2::ComponentDefinition::getRoles()
{
    ::java::util::Set* result = new ::java::util::HashSet();
    npc(result)->addAll(static_cast< ::java::util::Collection* >(roles));
    return result;
}

bool org::sbolstandard::core2::ComponentDefinition::containsRole(::java::net::URI* roleURI)
{
    return npc(roles)->contains(static_cast< ::java::lang::Object* >(roleURI));
}

void org::sbolstandard::core2::ComponentDefinition::clearRoles()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    npc(roles)->clear();
}

bool org::sbolstandard::core2::ComponentDefinition::addSequence(Sequence* sequence)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getSequence(npc(sequence)->getIdentity()) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Sequence '"_j)->append(static_cast< ::java::lang::Object* >(npc(sequence)->getIdentity()))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    return this->addSequence(npc(sequence)->getIdentity());
}

bool org::sbolstandard::core2::ComponentDefinition::addSequence(::java::net::URI* sequenceURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getSequence(sequenceURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Sequence '"_j)->append(static_cast< ::java::lang::Object* >(sequenceURI))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    return npc(sequences)->add(static_cast< ::java::lang::Object* >(sequenceURI));
}

bool org::sbolstandard::core2::ComponentDefinition::addSequence(::java::lang::String* sequenceId, ::java::lang::String* version)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto sequenceURI = URIcompliance::createCompliantURI(npc(sbolDocument)->getDefaultURIprefix(), TopLevel::SEQUENCE(), sequenceId, version, npc(sbolDocument)->isTypesInURIs());
    return addSequence(sequenceURI);
}

java::util::Set* org::sbolstandard::core2::ComponentDefinition::getSequenceURIs()
{
    return sequences;
}

java::util::Set* org::sbolstandard::core2::ComponentDefinition::getSequences()
{
    if(sbolDocument == nullptr)
        return nullptr;

    ::java::util::Set* resolved = new ::java::util::HashSet();
    for (auto _i = npc(sequences)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* su = java_cast< ::java::net::URI* >(_i->next());
        {
            auto seq = npc(sbolDocument)->getSequence(su);
            if(seq != nullptr) {
                npc(resolved)->add(static_cast< ::java::lang::Object* >(seq));
            }
        }
    }
    return resolved;
}

void org::sbolstandard::core2::ComponentDefinition::setSequences(::java::util::Set* sequences)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    clearSequences();
    if(sequences == nullptr)
        return;

    for (auto _i = npc(sequences)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* sequence = java_cast< ::java::net::URI* >(_i->next());
        {
            addSequence(sequence);
        }
    }
}

bool org::sbolstandard::core2::ComponentDefinition::removeSequence(::java::net::URI* sequenceURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(sequences)->remove(static_cast< ::java::lang::Object* >(sequenceURI));
}

void org::sbolstandard::core2::ComponentDefinition::clearSequences()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    npc(sequences)->clear();
}

bool org::sbolstandard::core2::ComponentDefinition::containsSequence(::java::net::URI* sequenceURI)
{
    return npc(sequences)->contains(static_cast< ::java::lang::Object* >(sequenceURI));
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::createSequenceAnnotation(::java::net::URI* identity, ::java::util::List* locations)
{
    auto sequenceAnnotation = new SequenceAnnotation(identity, locations);
    addSequenceAnnotation(sequenceAnnotation);
    return sequenceAnnotation;
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::createSequenceAnnotation(::java::lang::String* displayId, Location* location)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto URIprefix = npc(this->getPersistentIdentity())->toString();
    auto version = this->getVersion();
    auto newSequenceAnnotationURI = URIcompliance::createCompliantURI(URIprefix, displayId, version);
    ::java::util::List* locations = new ::java::util::ArrayList();
    npc(locations)->add(static_cast< ::java::lang::Object* >(location));
    auto sa = createSequenceAnnotation(newSequenceAnnotationURI, locations);
    npc(sa)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    npc(sa)->setDisplayId(displayId);
    npc(sa)->setVersion(version);
    return sa;
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return createSequenceAnnotation(displayId, locationId, static_cast< OrientationType* >(nullptr));
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, OrientationType* orientation)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto URIprefix = ::java::lang::StringBuilder().append(npc(this->getPersistentIdentity())->toString())->append(u"/"_j)
        ->append(displayId)->toString();
    auto version = this->getVersion();
    auto location = new GenericLocation(URIcompliance::createCompliantURI(URIprefix, locationId, version));
    if(orientation != nullptr)
        npc(location)->setOrientation(orientation);

    npc(location)->setPersistentIdentity(::java::net::URI::create(::java::lang::StringBuilder().append(URIprefix)->append(u"/"_j)
        ->append(locationId)->toString()));
    npc(location)->setDisplayId(locationId);
    npc(location)->setVersion(this->getVersion());
    return createSequenceAnnotation(displayId, static_cast< Location* >(location));
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, int32_t at)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return createSequenceAnnotation(displayId, locationId, at, static_cast< OrientationType* >(nullptr));
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, int32_t at, OrientationType* orientation)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto URIprefix = ::java::lang::StringBuilder().append(npc(this->getPersistentIdentity())->toString())->append(u"/"_j)
        ->append(displayId)->toString();
    auto version = this->getVersion();
    auto location = new Cut(URIcompliance::createCompliantURI(URIprefix, locationId, version), at);
    if(orientation != nullptr)
        npc(location)->setOrientation(orientation);

    npc(location)->setPersistentIdentity(::java::net::URI::create(::java::lang::StringBuilder().append(URIprefix)->append(u"/"_j)
        ->append(locationId)->toString()));
    npc(location)->setDisplayId(locationId);
    npc(location)->setVersion(this->getVersion());
    return createSequenceAnnotation(displayId, static_cast< Location* >(location));
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, int32_t start, int32_t end)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return createSequenceAnnotation(displayId, locationId, start, end, nullptr);
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::createSequenceAnnotation(::java::lang::String* displayId, ::java::lang::String* locationId, int32_t start, int32_t end, OrientationType* orientation)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto URIprefix = ::java::lang::StringBuilder().append(npc(this->getPersistentIdentity())->toString())->append(u"/"_j)
        ->append(displayId)->toString();
    auto version = this->getVersion();
    Location* location = new Range(URIcompliance::createCompliantURI(URIprefix, locationId, version), start, end);
    if(orientation != nullptr)
        npc(location)->setOrientation(orientation);

    npc(location)->setPersistentIdentity(::java::net::URI::create(::java::lang::StringBuilder().append(URIprefix)->append(u"/"_j)
        ->append(locationId)->toString()));
    npc(location)->setDisplayId(locationId);
    npc(location)->setVersion(this->getVersion());
    return createSequenceAnnotation(displayId, location);
}

void org::sbolstandard::core2::ComponentDefinition::addSequenceAnnotation(SequenceAnnotation* sequenceAnnotation)
{
    addChildSafely(sequenceAnnotation, sequenceAnnotations, u"sequenceAnnotation"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(components), static_cast< ::java::util::Map* >(sequenceConstraints)}));
    npc(sequenceAnnotation)->setSBOLDocument(this->sbolDocument);
    npc(sequenceAnnotation)->setComponentDefinition(this);
}

bool org::sbolstandard::core2::ComponentDefinition::removeSequenceAnnotation(SequenceAnnotation* sequenceAnnotation)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return removeChildSafely(sequenceAnnotation, sequenceAnnotations);
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::getSequenceAnnotation(::java::lang::String* displayId)
{
    return java_cast< SequenceAnnotation* >(npc(sequenceAnnotations)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::SequenceAnnotation* org::sbolstandard::core2::ComponentDefinition::getSequenceAnnotation(::java::net::URI* sequenceAnnotationURI)
{
    return java_cast< SequenceAnnotation* >(npc(sequenceAnnotations)->get(static_cast< ::java::lang::Object* >(sequenceAnnotationURI)));
}

java::util::Set* org::sbolstandard::core2::ComponentDefinition::getSequenceAnnotations()
{
    ::java::util::Set* sequenceAnnotations = new ::java::util::HashSet();
    npc(sequenceAnnotations)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->sequenceAnnotations))->values()));
    return sequenceAnnotations;
}

void org::sbolstandard::core2::ComponentDefinition::clearSequenceAnnotations()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(sequenceAnnotations)->values())->toArray_();
    for(auto sequenceAnnotation : *npc(valueSetArray_)) {
        removeSequenceAnnotation(java_cast< SequenceAnnotation* >(sequenceAnnotation));
    }
}

void org::sbolstandard::core2::ComponentDefinition::setSequenceAnnotations(::java::util::List* sequenceAnnotations)
{
    clearSequenceAnnotations();
    for (auto _i = npc(sequenceAnnotations)->iterator(); _i->hasNext(); ) {
        SequenceAnnotation* sequenceAnnotation = java_cast< SequenceAnnotation* >(_i->next());
        {
            addSequenceAnnotation(sequenceAnnotation);
        }
    }
}

org::sbolstandard::core2::Component* org::sbolstandard::core2::ComponentDefinition::createComponent(::java::net::URI* identity, AccessType* access, ::java::net::URI* componentDefinitionURI)
{
    auto component = new Component(identity, access, componentDefinitionURI);
    addComponent(component);
    return component;
}

org::sbolstandard::core2::Component* org::sbolstandard::core2::ComponentDefinition::createComponent(::java::lang::String* displayId, AccessType* access, ::java::lang::String* componentDefinitionId, ::java::lang::String* version)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto componentDefinitionURI = URIcompliance::createCompliantURI(npc(sbolDocument)->getDefaultURIprefix(), TopLevel::COMPONENT_DEFINITION(), componentDefinitionId, version, npc(sbolDocument)->isTypesInURIs());
    return createComponent(displayId, access, componentDefinitionURI);
}

org::sbolstandard::core2::Component* org::sbolstandard::core2::ComponentDefinition::createComponent(::java::lang::String* displayId, AccessType* access, ::java::net::URI* componentDefinitionURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getComponentDefinition(componentDefinitionURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component definition '"_j)->append(static_cast< ::java::lang::Object* >(componentDefinitionURI))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    auto URIprefix = npc(this->getPersistentIdentity())->toString();
    auto version = this->getVersion();
    auto c = createComponent(URIcompliance::createCompliantURI(URIprefix, displayId, version), access, componentDefinitionURI);
    npc(c)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    npc(c)->setDisplayId(displayId);
    npc(c)->setVersion(version);
    return c;
}

void org::sbolstandard::core2::ComponentDefinition::addComponent(Component* component)
{
    addChildSafely(component, components, u"component"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(sequenceAnnotations), static_cast< ::java::util::Map* >(sequenceConstraints)}));
    npc(component)->setSBOLDocument(this->sbolDocument);
    npc(component)->setComponentDefinition(this);
}

bool org::sbolstandard::core2::ComponentDefinition::removeComponent(Component* component)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    for (auto _i = npc(npc(sequenceAnnotations)->values())->iterator(); _i->hasNext(); ) {
        SequenceAnnotation* sa = java_cast< SequenceAnnotation* >(_i->next());
        {
            if(npc(npc(sa)->getComponentURI())->equals(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))) {
                throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))
                    ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
            }
        }
    }
    for (auto _i = npc(npc(sequenceConstraints)->values())->iterator(); _i->hasNext(); ) {
        SequenceConstraint* sc = java_cast< SequenceConstraint* >(_i->next());
        {
            if(npc(npc(sc)->getSubjectURI())->equals(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))) {
                throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))
                    ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
            }
            if(npc(npc(sc)->getObjectURI())->equals(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))) {
                throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))
                    ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
            }
        }
    }
    for (auto _i = npc(npc(components)->values())->iterator(); _i->hasNext(); ) {
        Component* c = java_cast< Component* >(_i->next());
        {
            for (auto _i = npc(npc(c)->getMapsTos())->iterator(); _i->hasNext(); ) {
                MapsTo* mt = java_cast< MapsTo* >(_i->next());
                {
                    if(npc(npc(mt)->getLocalURI())->equals(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))) {
                        throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))
                            ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                    }
                }
            }
        }
    }
    if(sbolDocument != nullptr) {
        for (auto _i = npc(npc(sbolDocument)->getComponentDefinitions())->iterator(); _i->hasNext(); ) {
            ComponentDefinition* cd = java_cast< ComponentDefinition* >(_i->next());
            {
                for (auto _i = npc(npc(cd)->getComponents())->iterator(); _i->hasNext(); ) {
                    Component* c = java_cast< Component* >(_i->next());
                    {
                        for (auto _i = npc(npc(c)->getMapsTos())->iterator(); _i->hasNext(); ) {
                            MapsTo* mt = java_cast< MapsTo* >(_i->next());
                            {
                                if(npc(npc(mt)->getRemoteURI())->equals(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))) {
                                    throw new SBOLValidationException(::java::lang::StringBuilder().append(u"Cannot remove "_j)->append(static_cast< ::java::lang::Object* >(npc(component)->getIdentity()))
                                        ->append(u" since it is in use."_j)->toString(), new IdentifiedArray());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return removeChildSafely(component, components);
}

org::sbolstandard::core2::Component* org::sbolstandard::core2::ComponentDefinition::getComponent(::java::lang::String* displayId)
{
    return java_cast< Component* >(npc(components)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::Component* org::sbolstandard::core2::ComponentDefinition::getComponent(::java::net::URI* componentURI)
{
    return java_cast< Component* >(npc(components)->get(static_cast< ::java::lang::Object* >(componentURI)));
}

java::util::Set* org::sbolstandard::core2::ComponentDefinition::getComponents()
{
    ::java::util::Set* components = new ::java::util::HashSet();
    npc(components)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->components))->values()));
    return components;
}

void org::sbolstandard::core2::ComponentDefinition::clearComponents()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(components)->values())->toArray_();
    for(auto component : *npc(valueSetArray_)) {
        removeComponent(java_cast< Component* >(component));
    }
}

void org::sbolstandard::core2::ComponentDefinition::setComponents(::java::util::List* components)
{
    clearComponents();
    for (auto _i = npc(components)->iterator(); _i->hasNext(); ) {
        Component* component = java_cast< Component* >(_i->next());
        {
            addComponent(component);
        }
    }
}

org::sbolstandard::core2::SequenceConstraint* org::sbolstandard::core2::ComponentDefinition::createSequenceConstraint(::java::net::URI* identity, RestrictionType* restriction, ::java::net::URI* subject, ::java::net::URI* object)
{
    auto sequenceConstraint = new SequenceConstraint(identity, restriction, subject, object);
    addSequenceConstraint(sequenceConstraint);
    return sequenceConstraint;
}

org::sbolstandard::core2::SequenceConstraint* org::sbolstandard::core2::ComponentDefinition::createSequenceConstraint(::java::lang::String* displayId, RestrictionType* restriction, ::java::lang::String* subjectId, ::java::lang::String* objectId)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto subjectURI = URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), subjectId, this->getVersion());
    if(sbolDocument != nullptr && npc(sbolDocument)->isCreateDefaults() && this->getComponent(subjectURI) == nullptr) {
        this->createComponent(subjectId, AccessType::PUBLIC, subjectId, u""_j);
    }
    auto objectURI = URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), objectId, this->getVersion());
    if(sbolDocument != nullptr && npc(sbolDocument)->isCreateDefaults() && this->getComponent(objectURI) == nullptr) {
        this->createComponent(objectId, AccessType::PUBLIC, objectId, u""_j);
    }
    return createSequenceConstraint(displayId, restriction, subjectURI, objectURI);
}

org::sbolstandard::core2::SequenceConstraint* org::sbolstandard::core2::ComponentDefinition::createSequenceConstraint(::java::lang::String* displayId, RestrictionType* restriction, ::java::net::URI* subject, ::java::net::URI* object)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto URIprefix = npc(this->getPersistentIdentity())->toString();
    auto version = this->getVersion();
    auto sc = createSequenceConstraint(URIcompliance::createCompliantURI(URIprefix, displayId, version), restriction, subject, object);
    npc(sc)->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    npc(sc)->setDisplayId(displayId);
    npc(sc)->setVersion(version);
    return sc;
}

void org::sbolstandard::core2::ComponentDefinition::addSequenceConstraint(SequenceConstraint* sequenceConstraint)
{
    npc(sequenceConstraint)->setSBOLDocument(this->sbolDocument);
    npc(sequenceConstraint)->setComponentDefinition(this);
    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sequenceConstraint)->getSubject() == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(npc(sequenceConstraint)->getSubjectURI()))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sequenceConstraint)->getObject() == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(npc(sequenceConstraint)->getObjectURI()))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    addChildSafely(sequenceConstraint, sequenceConstraints, u"sequenceConstraint"_j, new ::java::util::MapArray({static_cast< ::java::util::Map* >(components), static_cast< ::java::util::Map* >(sequenceAnnotations)}));
}

bool org::sbolstandard::core2::ComponentDefinition::removeSequenceConstraint(SequenceConstraint* sequenceConstraint)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return removeChildSafely(sequenceConstraint, sequenceConstraints);
}

org::sbolstandard::core2::SequenceConstraint* org::sbolstandard::core2::ComponentDefinition::getSequenceConstraint(::java::lang::String* displayId)
{
    return java_cast< SequenceConstraint* >(npc(sequenceConstraints)->get(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(npc(this->getPersistentIdentity())->toString(), displayId, this->getVersion()))));
}

org::sbolstandard::core2::SequenceConstraint* org::sbolstandard::core2::ComponentDefinition::getSequenceConstraint(::java::net::URI* sequenceConstraintURI)
{
    return java_cast< SequenceConstraint* >(npc(sequenceConstraints)->get(static_cast< ::java::lang::Object* >(sequenceConstraintURI)));
}

java::util::Set* org::sbolstandard::core2::ComponentDefinition::getSequenceConstraints()
{
    ::java::util::Set* sequenceConstraints = new ::java::util::HashSet();
    npc(sequenceConstraints)->addAll(static_cast< ::java::util::Collection* >(npc(java_cast< ::java::util::HashMap* >(this->sequenceConstraints))->values()));
    return sequenceConstraints;
}

void org::sbolstandard::core2::ComponentDefinition::clearSequenceConstraints()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    auto valueSetArray_ = npc(npc(sequenceConstraints)->values())->toArray_();
    for(auto sequenceConstraint : *npc(valueSetArray_)) {
        removeSequenceConstraint(java_cast< SequenceConstraint* >(sequenceConstraint));
    }
}

void org::sbolstandard::core2::ComponentDefinition::setSequenceConstraints(::java::util::List* sequenceConstraints)
{
    clearSequenceConstraints();
    for (auto _i = npc(sequenceConstraints)->iterator(); _i->hasNext(); ) {
        SequenceConstraint* sequenceConstraint = java_cast< SequenceConstraint* >(_i->next());
        {
            addSequenceConstraint(sequenceConstraint);
        }
    }
}

bool org::sbolstandard::core2::ComponentDefinition::checkDescendantsURIcompliance()
{
    if(!URIcompliance::isTopLevelURIformCompliant(this->getIdentity()))
        return false;

    auto allDescendantsCompliant = true;
    if(!npc(this->getSequenceConstraints())->isEmpty()) {
        for (auto _i = npc(this->getSequenceConstraints())->iterator(); _i->hasNext(); ) {
            SequenceConstraint* sequenceConstraint = java_cast< SequenceConstraint* >(_i->next());
            {
                allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(this, sequenceConstraint);
                if(!allDescendantsCompliant) {
                    return allDescendantsCompliant;
                }
            }
        }
    }
    if(!npc(this->getComponents())->isEmpty()) {
        for (auto _i = npc(this->getComponents())->iterator(); _i->hasNext(); ) {
            Component* component = java_cast< Component* >(_i->next());
            {
                allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(this, component);
                if(!allDescendantsCompliant) {
                    return allDescendantsCompliant;
                }
                if(!npc(npc(component)->getMapsTos())->isEmpty()) {
                    for (auto _i = npc(npc(component)->getMapsTos())->iterator(); _i->hasNext(); ) {
                        MapsTo* mapsTo = java_cast< MapsTo* >(_i->next());
                        {
                            allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(component, mapsTo);
                            if(!allDescendantsCompliant) {
                                return allDescendantsCompliant;
                            }
                        }
                    }
                }
            }
        }
    }
    if(!npc(this->getSequenceAnnotations())->isEmpty()) {
        for (auto _i = npc(this->getSequenceAnnotations())->iterator(); _i->hasNext(); ) {
            SequenceAnnotation* sequenceAnnotation = java_cast< SequenceAnnotation* >(_i->next());
            {
                allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(this, sequenceAnnotation);
                if(!allDescendantsCompliant) {
                    return allDescendantsCompliant;
                }
                auto locations = npc(sequenceAnnotation)->getLocations();
                for (auto _i = npc(locations)->iterator(); _i->hasNext(); ) {
                    Location* location = java_cast< Location* >(_i->next());
                    {
                        if(dynamic_cast< Range* >(location) != nullptr) {
                            allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(sequenceAnnotation, location);
                            if(!allDescendantsCompliant) {
                                return allDescendantsCompliant;
                            }
                        }
                        if(dynamic_cast< Cut* >(location) != nullptr) {
                            allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(sequenceAnnotation, location);
                            if(!allDescendantsCompliant) {
                                return allDescendantsCompliant;
                            }
                        }
                        if(dynamic_cast< GenericLocation* >(location) != nullptr) {
                            allDescendantsCompliant = allDescendantsCompliant && URIcompliance::isChildURIcompliant(sequenceAnnotation, location);
                            if(!allDescendantsCompliant) {
                                return allDescendantsCompliant;
                            }
                        }
                    }
                }
            }
        }
    }
    return allDescendantsCompliant;
}

bool org::sbolstandard::core2::ComponentDefinition::isComplete()
{
    if(sbolDocument == nullptr)
        return false;

    for (auto _i = npc(sequences)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* sequenceURI = java_cast< ::java::net::URI* >(_i->next());
        {
            if(npc(sbolDocument)->getSequence(sequenceURI) == nullptr)
                return false;

        }
    }
    for (auto _i = npc(getComponents())->iterator(); _i->hasNext(); ) {
        Component* component = java_cast< Component* >(_i->next());
        {
            if(npc(component)->getDefinition() == nullptr)
                return false;

        }
    }
    return true;
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::ComponentDefinition::deepCopy()
{
    return new ComponentDefinition(this);
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::ComponentDefinition::copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
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
    auto count = int32_t(0);
    for (auto _i = npc(npc(cloned)->getComponents())->iterator(); _i->hasNext(); ) {
        Component* component = java_cast< Component* >(_i->next());
        {
            if(!npc(component)->isSetDisplayId())
                npc(component)->setDisplayId(::java::lang::StringBuilder().append(u"component"_j)->append(++count)->toString());

            npc(component)->updateCompliantURI(npc(npc(cloned)->getPersistentIdentity())->toString(), npc(component)->getDisplayId(), version);
            npc(cloned)->removeChildSafely(component, npc(cloned)->components);
            npc(cloned)->addComponent(component);
        }
    }
    count = 0;
    for (auto _i = npc(npc(cloned)->getSequenceConstraints())->iterator(); _i->hasNext(); ) {
        SequenceConstraint* sequenceConstraint = java_cast< SequenceConstraint* >(_i->next());
        {
            if(!npc(sequenceConstraint)->isSetDisplayId())
                npc(sequenceConstraint)->setDisplayId(::java::lang::StringBuilder().append(u"sequenceConstraint"_j)->append(++count)->toString());

            npc(sequenceConstraint)->updateCompliantURI(npc(npc(cloned)->getPersistentIdentity())->toString(), npc(sequenceConstraint)->getDisplayId(), version);
            npc(cloned)->removeChildSafely(sequenceConstraint, npc(cloned)->sequenceConstraints);
            npc(cloned)->addSequenceConstraint(sequenceConstraint);
        }
    }
    count = 0;
    for (auto _i = npc(npc(cloned)->getSequenceAnnotations())->iterator(); _i->hasNext(); ) {
        SequenceAnnotation* sequenceAnnotation = java_cast< SequenceAnnotation* >(_i->next());
        {
            if(!npc(sequenceAnnotation)->isSetDisplayId())
                npc(sequenceAnnotation)->setDisplayId(::java::lang::StringBuilder().append(u"sequenceAnnotation"_j)->append(++count)->toString());

            npc(sequenceAnnotation)->updateCompliantURI(npc(npc(cloned)->getPersistentIdentity())->toString(), npc(sequenceAnnotation)->getDisplayId(), version);
            npc(cloned)->removeChildSafely(sequenceAnnotation, npc(cloned)->sequenceAnnotations);
            npc(cloned)->addSequenceAnnotation(sequenceAnnotation);
        }
    }
    return cloned;
}

int32_t org::sbolstandard::core2::ComponentDefinition::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((roles == nullptr) ? int32_t(0) : npc(roles)->hashCode());
    result = prime * result + ((SEQUENCE() == nullptr) ? int32_t(0) : npc(SEQUENCE())->hashCode());
    result = prime * result + ((sequenceAnnotations == nullptr) ? int32_t(0) : npc(sequenceAnnotations)->hashCode());
    result = prime * result + ((sequenceConstraints == nullptr) ? int32_t(0) : npc(sequenceConstraints)->hashCode());
    result = prime * result + ((components == nullptr) ? int32_t(0) : npc(components)->hashCode());
    result = prime * result + ((types == nullptr) ? int32_t(0) : npc(types)->hashCode());
    return result;
}

bool org::sbolstandard::core2::ComponentDefinition::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< ComponentDefinition* >(obj);
    if(roles == nullptr) {
        if(npc(other)->roles != nullptr)
            return false;

    } else if(!npc(roles)->equals(static_cast< ::java::lang::Object* >(npc(other)->roles)))
        return false;

    if(sequences == nullptr) {
        if(npc(other)->sequences != nullptr)
            return false;

    } else if(!npc(sequences)->equals(static_cast< ::java::lang::Object* >(npc(other)->sequences)))
        return false;

    if(sequenceAnnotations == nullptr) {
        if(npc(other)->sequenceAnnotations != nullptr)
            return false;

    } else if(!npc(sequenceAnnotations)->equals(static_cast< ::java::lang::Object* >(npc(other)->sequenceAnnotations)))
        return false;

    if(sequenceConstraints == nullptr) {
        if(npc(other)->sequenceConstraints != nullptr)
            return false;

    } else if(!npc(sequenceConstraints)->equals(static_cast< ::java::lang::Object* >(npc(other)->sequenceConstraints)))
        return false;

    if(components == nullptr) {
        if(npc(other)->components != nullptr)
            return false;

    } else if(!npc(components)->equals(static_cast< ::java::lang::Object* >(npc(other)->components)))
        return false;

    if(types == nullptr) {
        if(npc(other)->types != nullptr)
            return false;

    } else if(!npc(types)->equals(static_cast< ::java::lang::Object* >(npc(other)->types)))
        return false;

    return true;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::ComponentDefinition::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.ComponentDefinition", 42);
    return c;
}

void org::sbolstandard::core2::ComponentDefinition::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        DNA_ = ::java::net::URI::create(u"http://www.biopax.org/release/biopax-level3.owl#DnaRegion"_j);
        RNA_ = ::java::net::URI::create(u"http://www.biopax.org/release/biopax-level3.owl#RnaRegion"_j);
        PROTEIN_ = ::java::net::URI::create(u"http://www.biopax.org/release/biopax-level3.owl#Protein"_j);
        SMALL_MOLECULE_ = ::java::net::URI::create(u"http://www.biopax.org/release/biopax-level3.owl#SmallMolecule"_j);
        EFFECTOR_ = ::java::net::URI::create(u"http://identifiers.org/chebi/CHEBI:35224"_j);
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::ComponentDefinition::getClass0()
{
    return class_();
}


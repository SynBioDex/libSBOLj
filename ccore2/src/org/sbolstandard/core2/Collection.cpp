// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Collection.java
#include <org/sbolstandard/core2/Collection.hpp>

#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <java/util/Collection.hpp>
#include <java/util/HashSet.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/Set.hpp>
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

org::sbolstandard::core2::Collection::Collection(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Collection::Collection(::java::net::URI* identity) 
    : Collection(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity);
}

org::sbolstandard::core2::Collection::Collection(Collection* collection) 
    : Collection(*static_cast< ::default_init_tag* >(0))
{
    ctor(collection);
}

void org::sbolstandard::core2::Collection::ctor(::java::net::URI* identity)
{
    super::ctor(identity);
    this->members = new ::java::util::HashSet();
}

void org::sbolstandard::core2::Collection::ctor(Collection* collection)
{
    super::ctor(static_cast< TopLevel* >(collection));
    this->members = new ::java::util::HashSet();
    ::java::util::Set* newMembers = new ::java::util::HashSet();
    for (auto _i = npc(npc(collection)->getMemberURIs())->iterator(); _i->hasNext(); ) {
        ::java::net::URI* member = java_cast< ::java::net::URI* >(_i->next());
        {
            npc(newMembers)->add(static_cast< ::java::lang::Object* >(member));
        }
    }
    this->setMembers(newMembers);
}

bool org::sbolstandard::core2::Collection::addMember(::java::net::URI* memberURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getTopLevel(memberURI) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Top level '"_j)->append(static_cast< ::java::lang::Object* >(memberURI))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    return npc(members)->add(static_cast< ::java::lang::Object* >(memberURI));
}

bool org::sbolstandard::core2::Collection::removeMember(::java::net::URI* memberURI)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    return npc(members)->remove(static_cast< ::java::lang::Object* >(memberURI));
}

void org::sbolstandard::core2::Collection::setMembers(::java::util::Set* members)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    clearMembers();
    for (auto _i = npc(members)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* member = java_cast< ::java::net::URI* >(_i->next());
        {
            addMember(member);
        }
    }
}

java::util::Set* org::sbolstandard::core2::Collection::getMemberURIs()
{
    ::java::util::Set* result = new ::java::util::HashSet();
    npc(result)->addAll(static_cast< ::java::util::Collection* >(members));
    return result;
}

java::util::Set* org::sbolstandard::core2::Collection::getMembers()
{
    ::java::util::Set* result = new ::java::util::HashSet();
    for (auto _i = npc(members)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* memberURI = java_cast< ::java::net::URI* >(_i->next());
        {
            auto member = npc(sbolDocument)->getTopLevel(memberURI);
            npc(result)->add(static_cast< ::java::lang::Object* >(member));
        }
    }
    return result;
}

bool org::sbolstandard::core2::Collection::containsMember(::java::net::URI* memberURI)
{
    return npc(members)->contains(static_cast< ::java::lang::Object* >(memberURI));
}

void org::sbolstandard::core2::Collection::clearMembers()
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    npc(members)->clear();
}

int32_t org::sbolstandard::core2::Collection::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((members == nullptr) ? int32_t(0) : npc(members)->hashCode());
    return result;
}

bool org::sbolstandard::core2::Collection::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< Collection* >(obj);
    if(members == nullptr) {
        if(npc(other)->members != nullptr)
            return false;

    } else if(!npc(members)->equals(static_cast< ::java::lang::Object* >(npc(other)->members)))
        return false;

    return true;
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::Collection::deepCopy()
{
    return new Collection(this);
}

org::sbolstandard::core2::Collection* org::sbolstandard::core2::Collection::copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
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

bool org::sbolstandard::core2::Collection::checkDescendantsURIcompliance()
{
    return URIcompliance::isTopLevelURIformCompliant(this->getIdentity());
}

bool org::sbolstandard::core2::Collection::isComplete()
{
    if(sbolDocument == nullptr)
        return false;

    for (auto _i = npc(members)->iterator(); _i->hasNext(); ) {
        ::java::net::URI* member = java_cast< ::java::net::URI* >(_i->next());
        {
            if(npc(sbolDocument)->getTopLevel(member) == nullptr)
                return false;

        }
    }
    return true;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Collection::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Collection", 33);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Collection::getClass0()
{
    return class_();
}


// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/MapsTo.java
#include <org/sbolstandard/core2/MapsTo.hpp>

#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <org/sbolstandard/core2/AccessType.hpp>
#include <org/sbolstandard/core2/Component.hpp>
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/ComponentInstance.hpp>
#include <org/sbolstandard/core2/FunctionalComponent.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/Module.hpp>
#include <org/sbolstandard/core2/ModuleDefinition.hpp>
#include <org/sbolstandard/core2/RefinementType.hpp>
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

org::sbolstandard::core2::MapsTo::MapsTo(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::MapsTo::MapsTo(::java::net::URI* identity, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote) 
    : MapsTo(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,refinement,local,remote);
}

org::sbolstandard::core2::MapsTo::MapsTo(MapsTo* mapsTo) 
    : MapsTo(*static_cast< ::default_init_tag* >(0))
{
    ctor(mapsTo);
}

void org::sbolstandard::core2::MapsTo::init()
{
    moduleDefinition = nullptr;
    module = nullptr;
    componentDefinition = nullptr;
    componentInstance = nullptr;
}

void org::sbolstandard::core2::MapsTo::ctor(::java::net::URI* identity, RefinementType* refinement, ::java::net::URI* local, ::java::net::URI* remote)
{
    super::ctor(identity);
    init();
    setRefinement(refinement);
    setLocal(local);
    setRemote(remote);
}

void org::sbolstandard::core2::MapsTo::ctor(MapsTo* mapsTo)
{
    super::ctor(static_cast< Identified* >(mapsTo));
    init();
    this->setRefinement(npc(mapsTo)->getRefinement());
    this->setLocal(npc(mapsTo)->getLocalURI());
    this->setRemote(npc(mapsTo)->getRemoteURI());
}

org::sbolstandard::core2::RefinementType* org::sbolstandard::core2::MapsTo::getRefinement()
{
    return refinement;
}

void org::sbolstandard::core2::MapsTo::setRefinement(RefinementType* refinement)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    this->refinement = refinement;
}

java::net::URI* org::sbolstandard::core2::MapsTo::getLocalURI()
{
    return local;
}

org::sbolstandard::core2::ComponentInstance* org::sbolstandard::core2::MapsTo::getLocal()
{
    if(moduleDefinition != nullptr) {
        return npc(moduleDefinition)->getFunctionalComponent(local);
    } else if(componentDefinition != nullptr) {
        return npc(componentDefinition)->getComponent(local);
    }
    return nullptr;
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::MapsTo::getLocalDefinition()
{
    if(moduleDefinition != nullptr) {
        return npc(npc(moduleDefinition)->getFunctionalComponent(local))->getDefinition();
    } else if(componentDefinition != nullptr) {
        return npc(npc(componentDefinition)->getComponent(local))->getDefinition();
    }
    return nullptr;
}

void org::sbolstandard::core2::MapsTo::setLocal(::java::net::URI* local)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(local == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"MapsTo "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must specify a local component."_j)->toString());
    }
    if(moduleDefinition != nullptr) {
        if(npc(moduleDefinition)->getFunctionalComponent(local) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Functional Component '"_j)->append(static_cast< ::java::lang::Object* >(local))
                ->append(u"' does not exist."_j)->toString());
        }
    } else if(componentDefinition != nullptr) {
        if(npc(componentDefinition)->getComponent(local) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(local))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    this->local = local;
}

java::net::URI* org::sbolstandard::core2::MapsTo::getRemoteURI()
{
    return remote;
}

org::sbolstandard::core2::ComponentInstance* org::sbolstandard::core2::MapsTo::getRemote()
{
    if(module != nullptr) {
        if(npc(module)->getDefinition() == nullptr)
            return nullptr;

        return npc(npc(module)->getDefinition())->getFunctionalComponent(remote);
    } else if(componentInstance != nullptr) {
        if(npc(componentInstance)->getDefinition() == nullptr)
            return nullptr;

        return npc(npc(componentInstance)->getDefinition())->getComponent(remote);
    }
    return nullptr;
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::MapsTo::getRemoteDefinition()
{
    if(module != nullptr) {
        if(npc(module)->getDefinition() == nullptr)
            return nullptr;

        return npc(npc(npc(module)->getDefinition())->getFunctionalComponent(remote))->getDefinition();
    } else if(componentInstance != nullptr) {
        if(npc(componentInstance)->getDefinition() == nullptr)
            return nullptr;

        return npc(npc(npc(componentInstance)->getDefinition())->getComponent(remote))->getDefinition();
    }
    return nullptr;
}

void org::sbolstandard::core2::MapsTo::setRemote(::java::net::URI* remote)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(remote == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"MapsTo "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must specify a remote component."_j)->toString());
    }
    if(module != nullptr) {
        if(npc(module)->getDefinition() != nullptr) {
            if(npc(npc(module)->getDefinition())->getFunctionalComponent(remote) == nullptr) {
                throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Functional Component '"_j)->append(static_cast< ::java::lang::Object* >(remote))
                    ->append(u"' does not exist."_j)->toString());
            }
            if(npc(npc(npc(npc(module)->getDefinition())->getFunctionalComponent(remote))->getAccess())->equals(static_cast< ::java::lang::Object* >(AccessType::PRIVATE))) {
                throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Functional Component '"_j)->append(static_cast< ::java::lang::Object* >(remote))
                    ->append(u"' is private."_j)->toString());
            }
        }
    } else if(componentInstance != nullptr) {
        if(npc(componentInstance)->getDefinition() != nullptr) {
            if(npc(npc(componentInstance)->getDefinition())->getComponent(remote) == nullptr) {
                throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(remote))
                    ->append(u"' does not exist."_j)->toString());
            }
            if(npc(npc(npc(npc(componentInstance)->getDefinition())->getComponent(remote))->getAccess())->equals(static_cast< ::java::lang::Object* >(AccessType::PRIVATE))) {
                throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component '"_j)->append(static_cast< ::java::lang::Object* >(remote))
                    ->append(u"' is private."_j)->toString());
            }
        }
    }
    this->remote = remote;
}

int32_t org::sbolstandard::core2::MapsTo::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + ((local == nullptr) ? int32_t(0) : npc(local)->hashCode());
    result = prime * result + ((refinement == nullptr) ? int32_t(0) : npc(refinement)->hashCode());
    result = prime * result + ((remote == nullptr) ? int32_t(0) : npc(remote)->hashCode());
    return result;
}

bool org::sbolstandard::core2::MapsTo::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< MapsTo* >(obj);
    if(local == nullptr) {
        if(npc(other)->local != nullptr)
            return false;

    } else if(!npc(local)->equals(static_cast< ::java::lang::Object* >(npc(other)->local)))
        return false;

    if(refinement != npc(other)->refinement)
        return false;

    if(remote == nullptr) {
        if(npc(other)->remote != nullptr)
            return false;

    } else if(!npc(remote)->equals(static_cast< ::java::lang::Object* >(npc(other)->remote)))
        return false;

    return true;
}

org::sbolstandard::core2::MapsTo* org::sbolstandard::core2::MapsTo::deepCopy()
{
    return new MapsTo(this);
}

void org::sbolstandard::core2::MapsTo::updateCompliantURI(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    if(!npc(this->getIdentity())->equals(static_cast< ::java::lang::Object* >(URIcompliance::createCompliantURI(URIprefix, displayId, version)))) {
        this->setWasDerivedFrom(this->getIdentity());
    }
    this->setIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, version));
    this->setPersistentIdentity(URIcompliance::createCompliantURI(URIprefix, displayId, u""_j));
    this->setDisplayId(displayId);
    this->setVersion(version);
}

org::sbolstandard::core2::ModuleDefinition* org::sbolstandard::core2::MapsTo::getModuleDefinition()
{
    return moduleDefinition;
}

void org::sbolstandard::core2::MapsTo::setModuleDefinition(ModuleDefinition* moduleDefinition)
{
    this->moduleDefinition = moduleDefinition;
}

org::sbolstandard::core2::Module* org::sbolstandard::core2::MapsTo::getModule()
{
    return module;
}

void org::sbolstandard::core2::MapsTo::setModule(Module* module)
{
    this->module = module;
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::MapsTo::getComponentDefinition()
{
    return componentDefinition;
}

void org::sbolstandard::core2::MapsTo::setComponentDefinition(ComponentDefinition* componentDefinition)
{
    this->componentDefinition = componentDefinition;
}

org::sbolstandard::core2::ComponentInstance* org::sbolstandard::core2::MapsTo::getComponentInstance()
{
    return componentInstance;
}

void org::sbolstandard::core2::MapsTo::setComponentInstance(ComponentInstance* componentInstance)
{
    this->componentInstance = componentInstance;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::MapsTo::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.MapsTo", 29);
    return c;
}

java::lang::Class* org::sbolstandard::core2::MapsTo::getClass0()
{
    return class_();
}


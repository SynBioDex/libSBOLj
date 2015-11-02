// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComponentInstance.java
#include <org/sbolstandard/core2/ComponentInstance.hpp>

#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <org/sbolstandard/core2/AccessType.hpp>
#include <org/sbolstandard/core2/ComponentDefinition.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::ComponentInstance::ComponentInstance(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::ComponentInstance::ComponentInstance(::java::net::URI* identity, AccessType* access, ::java::net::URI* definition) 
    : ComponentInstance(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,access,definition);
}

org::sbolstandard::core2::ComponentInstance::ComponentInstance(ComponentInstance* component) 
    : ComponentInstance(*static_cast< ::default_init_tag* >(0))
{
    ctor(component);
}

void org::sbolstandard::core2::ComponentInstance::ctor(::java::net::URI* identity, AccessType* access, ::java::net::URI* definition)
{
    super::ctor(identity);
    setAccess(access);
    setDefinition(definition);
}

void org::sbolstandard::core2::ComponentInstance::ctor(ComponentInstance* component)
{
    super::ctor(static_cast< Identified* >(component));
    setAccess(npc(component)->getAccess());
    setDefinition(npc(component)->getDefinitionURI());
}

org::sbolstandard::core2::AccessType* org::sbolstandard::core2::ComponentInstance::getAccess()
{
    return access;
}

void org::sbolstandard::core2::ComponentInstance::setAccess(AccessType* access)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(access == nullptr) {
        throw new ::java::lang::IllegalArgumentException(u"Not a valid access type."_j);
    }
    this->access = access;
}

java::net::URI* org::sbolstandard::core2::ComponentInstance::getDefinitionURI()
{
    return definition;
}

org::sbolstandard::core2::ComponentDefinition* org::sbolstandard::core2::ComponentInstance::getDefinition()
{
    if(sbolDocument == nullptr)
        return nullptr;

    return npc(sbolDocument)->getComponentDefinition(definition);
}

void org::sbolstandard::core2::ComponentInstance::setDefinition(::java::net::URI* definition)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(definition == nullptr) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have a definition."_j)->toString());
    }
    if(sbolDocument != nullptr && npc(sbolDocument)->isComplete()) {
        if(npc(sbolDocument)->getComponentDefinition(definition) == nullptr) {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Component definition '"_j)->append(static_cast< ::java::lang::Object* >(definition))
                ->append(u"' does not exist."_j)->toString());
        }
    }
    this->definition = definition;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::ComponentInstance::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.ComponentInstance", 40);
    return c;
}

java::lang::Class* org::sbolstandard::core2::ComponentInstance::getClass0()
{
    return class_();
}


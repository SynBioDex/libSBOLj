// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/AccessType.java
#include <org/sbolstandard/core2/AccessType.hpp>

#include <java/io/Serializable.hpp>
#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/Comparable.hpp>
#include <java/lang/Enum.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <org/sbolstandard/core2/Sbol2Terms.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NamespaceBinding.hpp>
#include <SubArray.hpp>
#include <ObjectArray.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::Enum, ObjectArray, ComparableArray, ::java::io::SerializableArray > EnumArray;
    } // lang
} // java

namespace org
{
    namespace sbolstandard
    {
        namespace core2
        {
typedef ::SubArray< ::org::sbolstandard::core2::AccessType, ::java::lang::EnumArray > AccessTypeArray;
        } // core2
    } // sbolstandard
} // org

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::AccessType::AccessType(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::AccessType::AccessType(::java::lang::String* name, int ordinal, ::java::lang::String* accessType) 
    : AccessType(*static_cast< ::default_init_tag* >(0))
{
    ctor(name, ordinal, accessType);
}

org::sbolstandard::core2::AccessType* org::sbolstandard::core2::AccessType::PUBLIC = new ::org::sbolstandard::core2::AccessType(u"PUBLIC"_j, 0, u"public"_j);
org::sbolstandard::core2::AccessType* org::sbolstandard::core2::AccessType::PRIVATE = new ::org::sbolstandard::core2::AccessType(u"PRIVATE"_j, 1, u"private"_j);
void org::sbolstandard::core2::AccessType::ctor(::java::lang::String* name, int ordinal, ::java::lang::String* accessType)
{
    super::ctor(name, ordinal);
    this->accessType = accessType;
}

org::sbolstandard::core2::AccessType* org::sbolstandard::core2::AccessType::convertToAccessType(::java::net::URI* access)
{
    clinit();
    if(access != nullptr) {
        if(npc(access)->equals(static_cast< ::java::lang::Object* >(publicURI_))) {
            return AccessType::PUBLIC;
        } else if(npc(access)->equals(static_cast< ::java::lang::Object* >(privateURI_))) {
            return AccessType::PRIVATE;
        } else {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Unknown access URI `"_j)->append(static_cast< ::java::lang::Object* >(access))
                ->append(u"'"_j)->toString());
        }
    } else {
        throw new ::java::lang::IllegalArgumentException(u"access URI cannot be null"_j);
    }
}

java::net::URI* org::sbolstandard::core2::AccessType::convertToURI(AccessType* access)
{
    clinit();
    if(access != nullptr) {
        if(npc(access)->equals(static_cast< ::java::lang::Object* >(AccessType::PUBLIC))) {
            return publicURI_;
        } else if(npc(access)->equals(static_cast< ::java::lang::Object* >(AccessType::PRIVATE))) {
            return privateURI_;
        } else {
            return nullptr;
        }
    } else {
        return nullptr;
    }
}

java::lang::String* org::sbolstandard::core2::AccessType::getAccessType()
{
    return accessType;
}

java::lang::String* org::sbolstandard::core2::AccessType::toString()
{
    return accessType;
}

java::net::URI*& org::sbolstandard::core2::AccessType::publicURI()
{
    clinit();
    return publicURI_;
}
java::net::URI* org::sbolstandard::core2::AccessType::publicURI_;

java::net::URI*& org::sbolstandard::core2::AccessType::privateURI()
{
    clinit();
    return privateURI_;
}
java::net::URI* org::sbolstandard::core2::AccessType::privateURI_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::AccessType::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.AccessType", 33);
    return c;
}

void org::sbolstandard::core2::AccessType::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        publicURI_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"public"_j)->toString());
        privateURI_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"private"_j)->toString());
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

org::sbolstandard::core2::AccessType* org::sbolstandard::core2::AccessType::valueOf(::java::lang::String* a0)
{
    if(PRIVATE->toString()->equals(a0))
        return PRIVATE;
    if(PUBLIC->toString()->equals(a0))
        return PUBLIC;
    throw new ::java::lang::IllegalArgumentException(a0);
}

org::sbolstandard::core2::AccessTypeArray* org::sbolstandard::core2::AccessType::values()
{
    return new org::sbolstandard::core2::AccessTypeArray({
        PRIVATE,
        PUBLIC,
    });
}

java::lang::Class* org::sbolstandard::core2::AccessType::getClass0()
{
    return class_();
}


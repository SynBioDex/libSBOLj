// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/OrientationType.java
#include <org/sbolstandard/core2/OrientationType.hpp>

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
typedef ::SubArray< ::org::sbolstandard::core2::OrientationType, ::java::lang::EnumArray > OrientationTypeArray;
        } // core2
    } // sbolstandard
} // org

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::OrientationType::OrientationType(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::OrientationType::OrientationType(::java::lang::String* name, int ordinal, ::java::lang::String* orientationType) 
    : OrientationType(*static_cast< ::default_init_tag* >(0))
{
    ctor(name, ordinal, orientationType);
}

org::sbolstandard::core2::OrientationType* org::sbolstandard::core2::OrientationType::INLINE = new ::org::sbolstandard::core2::OrientationType(u"INLINE"_j, 0, u"inline"_j);
org::sbolstandard::core2::OrientationType* org::sbolstandard::core2::OrientationType::REVERSECOMPLEMENT = new ::org::sbolstandard::core2::OrientationType(u"REVERSECOMPLEMENT"_j, 1, u"reverseComplement"_j);
void org::sbolstandard::core2::OrientationType::ctor(::java::lang::String* name, int ordinal, ::java::lang::String* orientationType)
{
    super::ctor(name, ordinal);
    this->orientationType = orientationType;
}

java::lang::String* org::sbolstandard::core2::OrientationType::getOrientationType()
{
    return orientationType;
}

java::lang::String* org::sbolstandard::core2::OrientationType::toString()
{
    return orientationType;
}

org::sbolstandard::core2::OrientationType* org::sbolstandard::core2::OrientationType::convertToOrientationType(::java::net::URI* orientation)
{
    clinit();
    if(npc(orientation)->equals(static_cast< ::java::lang::Object* >(inline__))) {
        return OrientationType::INLINE;
    } else if(npc(orientation)->equals(static_cast< ::java::lang::Object* >(reverseComplement_))) {
        return OrientationType::REVERSECOMPLEMENT;
    } else {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Unknown orientation URI `"_j)->append(static_cast< ::java::lang::Object* >(orientation))
            ->append(u"'"_j)->toString());
    }
}

java::net::URI* org::sbolstandard::core2::OrientationType::convertToURI(OrientationType* orientation)
{
    clinit();
    if(orientation != nullptr) {
        if(npc(orientation)->equals(static_cast< ::java::lang::Object* >(OrientationType::INLINE))) {
            return inline__;
        } else if(npc(orientation)->equals(static_cast< ::java::lang::Object* >(OrientationType::REVERSECOMPLEMENT))) {
            return reverseComplement_;
        } else {
            return nullptr;
        }
    } else {
        return nullptr;
    }
}

java::net::URI*& org::sbolstandard::core2::OrientationType::inline_()
{
    clinit();
    return inline__;
}
java::net::URI* org::sbolstandard::core2::OrientationType::inline__;

java::net::URI*& org::sbolstandard::core2::OrientationType::reverseComplement()
{
    clinit();
    return reverseComplement_;
}
java::net::URI* org::sbolstandard::core2::OrientationType::reverseComplement_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::OrientationType::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.OrientationType", 38);
    return c;
}

void org::sbolstandard::core2::OrientationType::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        inline__ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"inline"_j)->toString());
        reverseComplement_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"reverseComplement"_j)->toString());
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

org::sbolstandard::core2::OrientationType* org::sbolstandard::core2::OrientationType::valueOf(::java::lang::String* a0)
{
    if(INLINE->toString()->equals(a0))
        return INLINE;
    if(REVERSECOMPLEMENT->toString()->equals(a0))
        return REVERSECOMPLEMENT;
    throw new ::java::lang::IllegalArgumentException(a0);
}

org::sbolstandard::core2::OrientationTypeArray* org::sbolstandard::core2::OrientationType::values()
{
    return new org::sbolstandard::core2::OrientationTypeArray({
        INLINE,
        REVERSECOMPLEMENT,
    });
}

java::lang::Class* org::sbolstandard::core2::OrientationType::getClass0()
{
    return class_();
}


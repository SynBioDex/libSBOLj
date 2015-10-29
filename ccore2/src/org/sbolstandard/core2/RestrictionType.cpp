// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/RestrictionType.java
#include <org/sbolstandard/core2/RestrictionType.hpp>

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
typedef ::SubArray< ::org::sbolstandard::core2::RestrictionType, ::java::lang::EnumArray > RestrictionTypeArray;
        } // core2
    } // sbolstandard
} // org

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::RestrictionType::RestrictionType(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::RestrictionType::RestrictionType(::java::lang::String* name, int ordinal, ::java::lang::String* restrictionType) 
    : RestrictionType(*static_cast< ::default_init_tag* >(0))
{
    ctor(name, ordinal, restrictionType);
}

org::sbolstandard::core2::RestrictionType* org::sbolstandard::core2::RestrictionType::PRECEDES = new ::org::sbolstandard::core2::RestrictionType(u"PRECEDES"_j, 0, u"precedes"_j);
org::sbolstandard::core2::RestrictionType* org::sbolstandard::core2::RestrictionType::SAME_ORIENTATION_AS = new ::org::sbolstandard::core2::RestrictionType(u"SAME_ORIENTATION_AS"_j, 1, u"sameOrienationAs"_j);
org::sbolstandard::core2::RestrictionType* org::sbolstandard::core2::RestrictionType::OPPOSITE_ORIENTATION_AS = new ::org::sbolstandard::core2::RestrictionType(u"OPPOSITE_ORIENTATION_AS"_j, 2, u"oppositeOrienationAs"_j);
void org::sbolstandard::core2::RestrictionType::ctor(::java::lang::String* name, int ordinal, ::java::lang::String* restrictionType)
{
    super::ctor(name, ordinal);
    this->restrictionType = restrictionType;
}

java::lang::String* org::sbolstandard::core2::RestrictionType::getRestrictionType()
{
    return restrictionType;
}

java::lang::String* org::sbolstandard::core2::RestrictionType::toString()
{
    return restrictionType;
}

org::sbolstandard::core2::RestrictionType* org::sbolstandard::core2::RestrictionType::convertToRestrictionType(::java::net::URI* restriction)
{
    clinit();
    if(npc(restriction)->equals(static_cast< ::java::lang::Object* >(precedes_))) {
        return RestrictionType::PRECEDES;
    } else if(npc(restriction)->equals(static_cast< ::java::lang::Object* >(sameOrientationAs_))) {
        return RestrictionType::SAME_ORIENTATION_AS;
    } else if(npc(restriction)->equals(static_cast< ::java::lang::Object* >(oppositeOrientationAs_))) {
        return RestrictionType::OPPOSITE_ORIENTATION_AS;
    } else {
        throw new ::java::lang::IllegalArgumentException(u"Not a valid restriction type."_j);
    }
}

java::net::URI* org::sbolstandard::core2::RestrictionType::convertToURI(RestrictionType* restriction)
{
    clinit();
    if(restriction != nullptr) {
        if(npc(restriction)->equals(static_cast< ::java::lang::Object* >(RestrictionType::PRECEDES))) {
            return precedes_;
        } else if(npc(restriction)->equals(static_cast< ::java::lang::Object* >(RestrictionType::SAME_ORIENTATION_AS))) {
            return sameOrientationAs_;
        } else if(npc(restriction)->equals(static_cast< ::java::lang::Object* >(RestrictionType::OPPOSITE_ORIENTATION_AS))) {
            return oppositeOrientationAs_;
        } else {
            throw new ::java::lang::IllegalArgumentException(u"Not a valid restriction type."_j);
        }
    } else {
        return nullptr;
    }
}

java::net::URI*& org::sbolstandard::core2::RestrictionType::precedes()
{
    clinit();
    return precedes_;
}
java::net::URI* org::sbolstandard::core2::RestrictionType::precedes_;

java::net::URI*& org::sbolstandard::core2::RestrictionType::sameOrientationAs()
{
    clinit();
    return sameOrientationAs_;
}
java::net::URI* org::sbolstandard::core2::RestrictionType::sameOrientationAs_;

java::net::URI*& org::sbolstandard::core2::RestrictionType::oppositeOrientationAs()
{
    clinit();
    return oppositeOrientationAs_;
}
java::net::URI* org::sbolstandard::core2::RestrictionType::oppositeOrientationAs_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::RestrictionType::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.RestrictionType", 38);
    return c;
}

void org::sbolstandard::core2::RestrictionType::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        precedes_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"precedes"_j)->toString());
        sameOrientationAs_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"sameOrientationAs"_j)->toString());
        oppositeOrientationAs_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"oppositeOrientationAs"_j)->toString());
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

org::sbolstandard::core2::RestrictionType* org::sbolstandard::core2::RestrictionType::valueOf(::java::lang::String* a0)
{
    if(OPPOSITE_ORIENTATION_AS->toString()->equals(a0))
        return OPPOSITE_ORIENTATION_AS;
    if(PRECEDES->toString()->equals(a0))
        return PRECEDES;
    if(SAME_ORIENTATION_AS->toString()->equals(a0))
        return SAME_ORIENTATION_AS;
    throw new ::java::lang::IllegalArgumentException(a0);
}

org::sbolstandard::core2::RestrictionTypeArray* org::sbolstandard::core2::RestrictionType::values()
{
    return new org::sbolstandard::core2::RestrictionTypeArray({
        OPPOSITE_ORIENTATION_AS,
        PRECEDES,
        SAME_ORIENTATION_AS,
    });
}

java::lang::Class* org::sbolstandard::core2::RestrictionType::getClass0()
{
    return class_();
}


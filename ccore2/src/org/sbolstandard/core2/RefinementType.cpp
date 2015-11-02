// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/RefinementType.java
#include <org/sbolstandard/core2/RefinementType.hpp>

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
typedef ::SubArray< ::org::sbolstandard::core2::RefinementType, ::java::lang::EnumArray > RefinementTypeArray;
        } // core2
    } // sbolstandard
} // org

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::RefinementType::RefinementType(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::RefinementType::RefinementType(::java::lang::String* name, int ordinal, ::java::lang::String* refinementType) 
    : RefinementType(*static_cast< ::default_init_tag* >(0))
{
    ctor(name, ordinal, refinementType);
}

org::sbolstandard::core2::RefinementType* org::sbolstandard::core2::RefinementType::VERIFYIDENTICAL = new ::org::sbolstandard::core2::RefinementType(u"VERIFYIDENTICAL"_j, 0, u"verifyIdentical"_j);
org::sbolstandard::core2::RefinementType* org::sbolstandard::core2::RefinementType::USELOCAL = new ::org::sbolstandard::core2::RefinementType(u"USELOCAL"_j, 1, u"useLocal"_j);
org::sbolstandard::core2::RefinementType* org::sbolstandard::core2::RefinementType::USEREMOTE = new ::org::sbolstandard::core2::RefinementType(u"USEREMOTE"_j, 2, u"useRemote"_j);
org::sbolstandard::core2::RefinementType* org::sbolstandard::core2::RefinementType::MERGE = new ::org::sbolstandard::core2::RefinementType(u"MERGE"_j, 3, u"merge"_j);
void org::sbolstandard::core2::RefinementType::ctor(::java::lang::String* name, int ordinal, ::java::lang::String* refinementType)
{
    super::ctor(name, ordinal);
    this->refinementType = refinementType;
}

java::lang::String* org::sbolstandard::core2::RefinementType::getRefinementType()
{
    return refinementType;
}

java::lang::String* org::sbolstandard::core2::RefinementType::toString()
{
    return refinementType;
}

org::sbolstandard::core2::RefinementType* org::sbolstandard::core2::RefinementType::convertToRefinementType(::java::net::URI* refinement)
{
    clinit();
    if(npc(refinement)->equals(static_cast< ::java::lang::Object* >(merge_))) {
        return RefinementType::MERGE;
    } else if(npc(refinement)->equals(static_cast< ::java::lang::Object* >(useLocal_))) {
        return RefinementType::USELOCAL;
    } else if(npc(refinement)->equals(static_cast< ::java::lang::Object* >(useRemote_))) {
        return RefinementType::USEREMOTE;
    } else if(npc(refinement)->equals(static_cast< ::java::lang::Object* >(verifyIdentical_))) {
        return RefinementType::VERIFYIDENTICAL;
    } else {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Unknown refinement URI `"_j)->append(static_cast< ::java::lang::Object* >(refinement))
            ->append(u"'"_j)->toString());
    }
}

java::net::URI* org::sbolstandard::core2::RefinementType::convertToURI(RefinementType* refinement)
{
    clinit();
    if(refinement != nullptr) {
        if(npc(refinement)->equals(static_cast< ::java::lang::Object* >(RefinementType::MERGE))) {
            return merge_;
        } else if(npc(refinement)->equals(static_cast< ::java::lang::Object* >(RefinementType::USELOCAL))) {
            return useLocal_;
        } else if(npc(refinement)->equals(static_cast< ::java::lang::Object* >(RefinementType::USEREMOTE))) {
            return useRemote_;
        } else if(npc(refinement)->equals(static_cast< ::java::lang::Object* >(RefinementType::VERIFYIDENTICAL))) {
            return verifyIdentical_;
        } else {
            return nullptr;
        }
    } else {
        return nullptr;
    }
}

java::net::URI*& org::sbolstandard::core2::RefinementType::merge()
{
    clinit();
    return merge_;
}
java::net::URI* org::sbolstandard::core2::RefinementType::merge_;

java::net::URI*& org::sbolstandard::core2::RefinementType::useLocal()
{
    clinit();
    return useLocal_;
}
java::net::URI* org::sbolstandard::core2::RefinementType::useLocal_;

java::net::URI*& org::sbolstandard::core2::RefinementType::useRemote()
{
    clinit();
    return useRemote_;
}
java::net::URI* org::sbolstandard::core2::RefinementType::useRemote_;

java::net::URI*& org::sbolstandard::core2::RefinementType::verifyIdentical()
{
    clinit();
    return verifyIdentical_;
}
java::net::URI* org::sbolstandard::core2::RefinementType::verifyIdentical_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::RefinementType::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.RefinementType", 37);
    return c;
}

void org::sbolstandard::core2::RefinementType::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        merge_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"merge"_j)->toString());
        useLocal_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"useLocal"_j)->toString());
        useRemote_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"useRemote"_j)->toString());
        verifyIdentical_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"verifyIdentical"_j)->toString());
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

org::sbolstandard::core2::RefinementType* org::sbolstandard::core2::RefinementType::valueOf(::java::lang::String* a0)
{
    if(MERGE->toString()->equals(a0))
        return MERGE;
    if(USELOCAL->toString()->equals(a0))
        return USELOCAL;
    if(USEREMOTE->toString()->equals(a0))
        return USEREMOTE;
    if(VERIFYIDENTICAL->toString()->equals(a0))
        return VERIFYIDENTICAL;
    throw new ::java::lang::IllegalArgumentException(a0);
}

org::sbolstandard::core2::RefinementTypeArray* org::sbolstandard::core2::RefinementType::values()
{
    return new org::sbolstandard::core2::RefinementTypeArray({
        MERGE,
        USELOCAL,
        USEREMOTE,
        VERIFYIDENTICAL,
    });
}

java::lang::Class* org::sbolstandard::core2::RefinementType::getClass0()
{
    return class_();
}


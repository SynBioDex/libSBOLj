// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/DirectionType.java
#include <org/sbolstandard/core2/DirectionType.hpp>

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
typedef ::SubArray< ::org::sbolstandard::core2::DirectionType, ::java::lang::EnumArray > DirectionTypeArray;
        } // core2
    } // sbolstandard
} // org

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::DirectionType::DirectionType(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::DirectionType::DirectionType(::java::lang::String* name, int ordinal, ::java::lang::String* directionType) 
    : DirectionType(*static_cast< ::default_init_tag* >(0))
{
    ctor(name, ordinal, directionType);
}

org::sbolstandard::core2::DirectionType* org::sbolstandard::core2::DirectionType::IN = new ::org::sbolstandard::core2::DirectionType(u"IN"_j, 0, u"in"_j);
org::sbolstandard::core2::DirectionType* org::sbolstandard::core2::DirectionType::OUT = new ::org::sbolstandard::core2::DirectionType(u"OUT"_j, 1, u"out"_j);
org::sbolstandard::core2::DirectionType* org::sbolstandard::core2::DirectionType::INOUT = new ::org::sbolstandard::core2::DirectionType(u"INOUT"_j, 2, u"inout"_j);
org::sbolstandard::core2::DirectionType* org::sbolstandard::core2::DirectionType::NONE = new ::org::sbolstandard::core2::DirectionType(u"NONE"_j, 3, u"none"_j);
void org::sbolstandard::core2::DirectionType::ctor(::java::lang::String* name, int ordinal, ::java::lang::String* directionType)
{
    super::ctor(name, ordinal);
    this->directionType = directionType;
}

java::lang::String* org::sbolstandard::core2::DirectionType::getDirectionType()
{
    return directionType;
}

java::lang::String* org::sbolstandard::core2::DirectionType::toString()
{
    return directionType;
}

org::sbolstandard::core2::DirectionType* org::sbolstandard::core2::DirectionType::convertToDirectionType(::java::net::URI* direction)
{
    clinit();
    if(direction != nullptr) {
        if(npc(direction)->equals(static_cast< ::java::lang::Object* >(inout_))) {
            return DirectionType::INOUT;
        } else if(npc(direction)->equals(static_cast< ::java::lang::Object* >(in_))) {
            return DirectionType::IN;
        } else if(npc(direction)->equals(static_cast< ::java::lang::Object* >(none_))) {
            return DirectionType::NONE;
        } else if(npc(direction)->equals(static_cast< ::java::lang::Object* >(out_))) {
            return DirectionType::OUT;
        } else {
            throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Unknown direction URI `"_j)->append(static_cast< ::java::lang::Object* >(direction))
                ->append(u"'"_j)->toString());
        }
    } else {
        throw new ::java::lang::IllegalArgumentException(u"direction URI cannot be null"_j);
    }
}

java::net::URI* org::sbolstandard::core2::DirectionType::convertToURI(DirectionType* direction)
{
    clinit();
    if(direction != nullptr) {
        if(npc(direction)->equals(static_cast< ::java::lang::Object* >(DirectionType::INOUT))) {
            return inout_;
        } else if(npc(direction)->equals(static_cast< ::java::lang::Object* >(DirectionType::IN))) {
            return in_;
        } else if(npc(direction)->equals(static_cast< ::java::lang::Object* >(DirectionType::OUT))) {
            return out_;
        } else if(npc(direction)->equals(static_cast< ::java::lang::Object* >(DirectionType::NONE))) {
            return none_;
        } else {
            return nullptr;
        }
    } else {
        return nullptr;
    }
}

java::net::URI*& org::sbolstandard::core2::DirectionType::in()
{
    clinit();
    return in_;
}
java::net::URI* org::sbolstandard::core2::DirectionType::in_;

java::net::URI*& org::sbolstandard::core2::DirectionType::out()
{
    clinit();
    return out_;
}
java::net::URI* org::sbolstandard::core2::DirectionType::out_;

java::net::URI*& org::sbolstandard::core2::DirectionType::inout()
{
    clinit();
    return inout_;
}
java::net::URI* org::sbolstandard::core2::DirectionType::inout_;

java::net::URI*& org::sbolstandard::core2::DirectionType::none()
{
    clinit();
    return none_;
}
java::net::URI* org::sbolstandard::core2::DirectionType::none_;

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::DirectionType::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.DirectionType", 36);
    return c;
}

void org::sbolstandard::core2::DirectionType::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        in_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"in"_j)->toString());
        out_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"out"_j)->toString());
        inout_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"inout"_j)->toString());
        none_ = ::java::net::URI::create(::java::lang::StringBuilder().append(npc(Sbol2Terms::sbol2())->getNamespaceURI())->append(u"none"_j)->toString());
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

org::sbolstandard::core2::DirectionType* org::sbolstandard::core2::DirectionType::valueOf(::java::lang::String* a0)
{
    if(IN->toString()->equals(a0))
        return IN;
    if(INOUT->toString()->equals(a0))
        return INOUT;
    if(NONE->toString()->equals(a0))
        return NONE;
    if(OUT->toString()->equals(a0))
        return OUT;
    throw new ::java::lang::IllegalArgumentException(a0);
}

org::sbolstandard::core2::DirectionTypeArray* org::sbolstandard::core2::DirectionType::values()
{
    return new org::sbolstandard::core2::DirectionTypeArray({
        IN,
        INOUT,
        NONE,
        OUT,
    });
}

java::lang::Class* org::sbolstandard::core2::DirectionType::getClass0()
{
    return class_();
}


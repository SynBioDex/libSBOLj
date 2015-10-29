// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComparableVersion.java
#include <org/sbolstandard/core2/ComparableVersion_IntegerItem.hpp>

#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/RuntimeException.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/math/BigInteger.hpp>
#include <org/sbolstandard/core2/ComparableVersion_Item.hpp>

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

org::sbolstandard::core2::ComparableVersion_IntegerItem::ComparableVersion_IntegerItem(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::ComparableVersion_IntegerItem::ComparableVersion_IntegerItem() 
    : ComparableVersion_IntegerItem(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

org::sbolstandard::core2::ComparableVersion_IntegerItem::ComparableVersion_IntegerItem(::java::lang::String* str) 
    : ComparableVersion_IntegerItem(*static_cast< ::default_init_tag* >(0))
{
    ctor(str);
}

java::math::BigInteger*& org::sbolstandard::core2::ComparableVersion_IntegerItem::BIG_INTEGER_ZERO()
{
    clinit();
    return BIG_INTEGER_ZERO_;
}
java::math::BigInteger* org::sbolstandard::core2::ComparableVersion_IntegerItem::BIG_INTEGER_ZERO_;

org::sbolstandard::core2::ComparableVersion_IntegerItem*& org::sbolstandard::core2::ComparableVersion_IntegerItem::ZERO()
{
    clinit();
    return ZERO_;
}
org::sbolstandard::core2::ComparableVersion_IntegerItem* org::sbolstandard::core2::ComparableVersion_IntegerItem::ZERO_;

void org::sbolstandard::core2::ComparableVersion_IntegerItem::ctor()
{
    super::ctor();
    this->value = BIG_INTEGER_ZERO_;
}

void org::sbolstandard::core2::ComparableVersion_IntegerItem::ctor(::java::lang::String* str)
{
    super::ctor();
    this->value = new ::java::math::BigInteger(str);
}

int32_t org::sbolstandard::core2::ComparableVersion_IntegerItem::getType()
{
    return INTEGER_ITEM;
}

bool org::sbolstandard::core2::ComparableVersion_IntegerItem::isNull()
{
    return npc(BIG_INTEGER_ZERO_)->equals(static_cast< ::java::lang::Object* >(value));
}

int32_t org::sbolstandard::core2::ComparableVersion_IntegerItem::compareTo(ComparableVersion_Item* item)
{
    if(item == nullptr) {
        return npc(BIG_INTEGER_ZERO_)->equals(static_cast< ::java::lang::Object* >(value)) ? int32_t(0) : int32_t(1);
    }
    switch (npc(item)->getType()) {
    case INTEGER_ITEM:
        return npc(value)->compareTo(npc((java_cast< ComparableVersion_IntegerItem* >(item)))->value);
    case STRING_ITEM:
        return 1;
    case LIST_ITEM:
        return 1;
    default:
        throw new ::java::lang::RuntimeException(::java::lang::StringBuilder().append(u"invalid item: "_j)->append(static_cast< ::java::lang::Object* >(npc(item)->getClass()))->toString());
    }

}

java::lang::String* org::sbolstandard::core2::ComparableVersion_IntegerItem::toString()
{
    return npc(value)->toString();
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::ComparableVersion_IntegerItem::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.ComparableVersion.IntegerItem", 52);
    return c;
}

void org::sbolstandard::core2::ComparableVersion_IntegerItem::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        BIG_INTEGER_ZERO_ = new ::java::math::BigInteger(u"0"_j);
        ZERO_ = new ComparableVersion_IntegerItem();
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::ComparableVersion_IntegerItem::getClass0()
{
    return class_();
}


// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComparableVersion.java
#include <org/sbolstandard/core2/ComparableVersion_StringItem.hpp>

#include <java/io/Serializable.hpp>
#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/CharSequence.hpp>
#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/Comparable.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/RuntimeException.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/util/Arrays.hpp>
#include <java/util/List.hpp>
#include <java/util/Properties.hpp>
#include <org/sbolstandard/core2/ComparableVersion_Item.hpp>
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
typedef ::SubArray< ::java::lang::CharSequence, ObjectArray > CharSequenceArray;
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
typedef ::SubArray< ::java::lang::String, ObjectArray, ::java::io::SerializableArray, ComparableArray, CharSequenceArray > StringArray;
    } // lang
} // java

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

org::sbolstandard::core2::ComparableVersion_StringItem::ComparableVersion_StringItem(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::ComparableVersion_StringItem::ComparableVersion_StringItem(::java::lang::String* value, bool followedByDigit) 
    : ComparableVersion_StringItem(*static_cast< ::default_init_tag* >(0))
{
    ctor(value,followedByDigit);
}

java::lang::StringArray*& org::sbolstandard::core2::ComparableVersion_StringItem::QUALIFIERS()
{
    clinit();
    return QUALIFIERS_;
}
java::lang::StringArray* org::sbolstandard::core2::ComparableVersion_StringItem::QUALIFIERS_;

java::util::List*& org::sbolstandard::core2::ComparableVersion_StringItem::_QUALIFIERS()
{
    clinit();
    return _QUALIFIERS_;
}
java::util::List* org::sbolstandard::core2::ComparableVersion_StringItem::_QUALIFIERS_;

java::util::Properties*& org::sbolstandard::core2::ComparableVersion_StringItem::ALIASES()
{
    clinit();
    return ALIASES_;
}
java::util::Properties* org::sbolstandard::core2::ComparableVersion_StringItem::ALIASES_;

java::lang::String*& org::sbolstandard::core2::ComparableVersion_StringItem::RELEASE_VERSION_INDEX()
{
    clinit();
    return RELEASE_VERSION_INDEX_;
}
java::lang::String* org::sbolstandard::core2::ComparableVersion_StringItem::RELEASE_VERSION_INDEX_;

void org::sbolstandard::core2::ComparableVersion_StringItem::ctor(::java::lang::String* value, bool followedByDigit)
{
    super::ctor();
    if(followedByDigit && npc(value)->length() == 1) {
        switch (npc(value)->charAt(int32_t(0))) {
        case u'a':
            value = u"alpha"_j;
            break;
        case u'b':
            value = u"beta"_j;
            break;
        case u'm':
            value = u"milestone"_j;
            break;
        }

    }
    this->value = npc(ALIASES_)->getProperty(value, value);
}

int32_t org::sbolstandard::core2::ComparableVersion_StringItem::getType()
{
    return STRING_ITEM;
}

bool org::sbolstandard::core2::ComparableVersion_StringItem::isNull()
{
    return (npc(comparableQualifier(value))->compareTo(RELEASE_VERSION_INDEX_) == 0);
}

java::lang::String* org::sbolstandard::core2::ComparableVersion_StringItem::comparableQualifier(::java::lang::String* qualifier)
{
    clinit();
    auto i = npc(_QUALIFIERS_)->indexOf(qualifier);
    return i == -int32_t(1) ? (::java::lang::StringBuilder().append(npc(_QUALIFIERS_)->size())->append(u"-"_j)
        ->append(qualifier)->toString()) : ::java::lang::String::valueOf(i);
}

int32_t org::sbolstandard::core2::ComparableVersion_StringItem::compareTo(ComparableVersion_Item* item)
{
    if(item == nullptr) {
        return npc(comparableQualifier(value))->compareTo(RELEASE_VERSION_INDEX_);
    }
    switch (npc(item)->getType()) {
    case INTEGER_ITEM:
        return -int32_t(1);
    case STRING_ITEM:
        return npc(comparableQualifier(value))->compareTo(comparableQualifier(npc((java_cast< ComparableVersion_StringItem* >(item)))->value));
    case LIST_ITEM:
        return -int32_t(1);
    default:
        throw new ::java::lang::RuntimeException(::java::lang::StringBuilder().append(u"invalid item: "_j)->append(static_cast< ::java::lang::Object* >(npc(item)->getClass()))->toString());
    }

}

java::lang::String* org::sbolstandard::core2::ComparableVersion_StringItem::toString()
{
    return value;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::ComparableVersion_StringItem::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.ComparableVersion.StringItem", 51);
    return c;
}

void org::sbolstandard::core2::ComparableVersion_StringItem::clinit()
{
    super::clinit();
    static bool in_cl_init = false;
struct clinit_ {
    clinit_() {
        in_cl_init = true;
        QUALIFIERS_ = (new ::java::lang::StringArray({
            u"alpha"_j
            , u"beta"_j
            , u"milestone"_j
            , u"rc"_j
            , u"snapshot"_j
            , u""_j
            , u"sp"_j
        }));
        _QUALIFIERS_ = ::java::util::Arrays::asList(QUALIFIERS_);
        ALIASES_ = new ::java::util::Properties();
        {
            npc(ALIASES_)->put(static_cast< ::java::lang::Object* >(u"ga"_j), static_cast< ::java::lang::Object* >(u""_j));
            npc(ALIASES_)->put(static_cast< ::java::lang::Object* >(u"final"_j), static_cast< ::java::lang::Object* >(u""_j));
            npc(ALIASES_)->put(static_cast< ::java::lang::Object* >(u"cr"_j), static_cast< ::java::lang::Object* >(u"rc"_j));
        }
        RELEASE_VERSION_INDEX_ = ::java::lang::String::valueOf(npc(_QUALIFIERS_)->indexOf(u""_j));
    }
};

    if(!in_cl_init) {
        static clinit_ clinit_instance;
    }
}

java::lang::Class* org::sbolstandard::core2::ComparableVersion_StringItem::getClass0()
{
    return class_();
}


// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComparableVersion.java
#include <org/sbolstandard/core2/ComparableVersion.hpp>

#include <java/lang/Character.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/util/Locale.hpp>
#include <java/util/Stack.hpp>
#include <org/sbolstandard/core2/ComparableVersion_IntegerItem.hpp>
#include <org/sbolstandard/core2/ComparableVersion_Item.hpp>
#include <org/sbolstandard/core2/ComparableVersion_ListItem.hpp>
#include <org/sbolstandard/core2/ComparableVersion_StringItem.hpp>

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

org::sbolstandard::core2::ComparableVersion::ComparableVersion(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::ComparableVersion::ComparableVersion(::java::lang::String* version) 
    : ComparableVersion(*static_cast< ::default_init_tag* >(0))
{
    ctor(version);
}

void org::sbolstandard::core2::ComparableVersion::ctor(::java::lang::String* version)
{
    super::ctor();
    parseVersion(version);
}

void org::sbolstandard::core2::ComparableVersion::parseVersion(::java::lang::String* version)
{
    this->value = version;
    items = new ComparableVersion_ListItem();
    version = npc(version)->toLowerCase(::java::util::Locale::ENGLISH());
    auto list = items;
    auto stack = new ::java::util::Stack();
    npc(stack)->push(list);
    auto isDigit = false;
    auto startIndex = int32_t(0);
    for (auto i = int32_t(0); i < npc(version)->length(); i++) {
        auto c = npc(version)->charAt(i);
        if(c == u'.') {
            if(i == startIndex) {
                npc(list)->add(static_cast< ::java::lang::Object* >(ComparableVersion_IntegerItem::ZERO()));
            } else {
                npc(list)->add(static_cast< ::java::lang::Object* >(parseItem(isDigit, npc(version)->substring(startIndex, i))));
            }
            startIndex = i + int32_t(1);
        } else if(c == u'-') {
            if(i == startIndex) {
                npc(list)->add(static_cast< ::java::lang::Object* >(ComparableVersion_IntegerItem::ZERO()));
            } else {
                npc(list)->add(static_cast< ::java::lang::Object* >(parseItem(isDigit, npc(version)->substring(startIndex, i))));
            }
            startIndex = i + int32_t(1);
            if(isDigit) {
                npc(list)->normalize();
                if((i + int32_t(1) < npc(version)->length()) && ::java::lang::Character::isDigit(npc(version)->charAt(i + int32_t(1)))) {
                    npc(list)->add(static_cast< ::java::lang::Object* >(list = new ComparableVersion_ListItem()));
                    npc(stack)->push(list);
                }
            }
        } else if(::java::lang::Character::isDigit(c)) {
            if(!isDigit && i > startIndex) {
                npc(list)->add(static_cast< ::java::lang::Object* >(new ComparableVersion_StringItem(npc(version)->substring(startIndex, i), true)));
                startIndex = i;
            }
            isDigit = true;
        } else {
            if(isDigit && i > startIndex) {
                npc(list)->add(static_cast< ::java::lang::Object* >(parseItem(true, npc(version)->substring(startIndex, i))));
                startIndex = i;
            }
            isDigit = false;
        }
    }
    if(npc(version)->length() > startIndex) {
        npc(list)->add(static_cast< ::java::lang::Object* >(parseItem(isDigit, npc(version)->substring(startIndex))));
    }
    while (!npc(stack)->isEmpty()) {
        list = java_cast< ComparableVersion_ListItem* >(java_cast< ComparableVersion_Item* >(npc(stack)->pop()));
        npc(list)->normalize();
    }
    canonical = npc(items)->toString();
}

org::sbolstandard::core2::ComparableVersion_Item* org::sbolstandard::core2::ComparableVersion::parseItem(bool isDigit, ::java::lang::String* buf)
{
    clinit();
    return isDigit ? static_cast< ComparableVersion_Item* >(new ComparableVersion_IntegerItem(buf)) : static_cast< ComparableVersion_Item* >(new ComparableVersion_StringItem(buf, false));
}

int32_t org::sbolstandard::core2::ComparableVersion::compareTo(ComparableVersion* o)
{
    return npc(items)->compareTo(static_cast< ComparableVersion_Item* >(npc(o)->items));
}

int32_t org::sbolstandard::core2::ComparableVersion::compareTo(::java::lang::Object* o)
{ 
    return compareTo(dynamic_cast< ComparableVersion* >(o));
}

java::lang::String* org::sbolstandard::core2::ComparableVersion::toString()
{
    return value;
}

bool org::sbolstandard::core2::ComparableVersion::equals(::java::lang::Object* o)
{
    return (dynamic_cast< ComparableVersion* >(o) != nullptr) && npc(canonical)->equals(static_cast< ::java::lang::Object* >(npc((java_cast< ComparableVersion* >(o)))->canonical));
}

int32_t org::sbolstandard::core2::ComparableVersion::hashCode()
{
    return npc(canonical)->hashCode();
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::ComparableVersion::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.ComparableVersion", 40);
    return c;
}

java::lang::Class* org::sbolstandard::core2::ComparableVersion::getClass0()
{
    return class_();
}


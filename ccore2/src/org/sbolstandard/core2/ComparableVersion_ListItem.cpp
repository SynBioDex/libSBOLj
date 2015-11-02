// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/ComparableVersion.java
#include <org/sbolstandard/core2/ComparableVersion_ListItem.hpp>

#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/RuntimeException.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/ListIterator.hpp>
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

org::sbolstandard::core2::ComparableVersion_ListItem::ComparableVersion_ListItem(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::ComparableVersion_ListItem::ComparableVersion_ListItem()
    : ComparableVersion_ListItem(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

int32_t org::sbolstandard::core2::ComparableVersion_ListItem::getType()
{
    return LIST_ITEM;
}

bool org::sbolstandard::core2::ComparableVersion_ListItem::isNull()
{
    return (size() == 0);
}

void org::sbolstandard::core2::ComparableVersion_ListItem::normalize()
{
    for (auto *iterator = listIterator(size()); npc(iterator)->hasPrevious(); ) {
        auto item = java_cast< ComparableVersion_Item* >(npc(iterator)->previous());
        if(npc(item)->isNull()) {
            npc(iterator)->remove();
        } else {
            break;
        }
    }
}

int32_t org::sbolstandard::core2::ComparableVersion_ListItem::compareTo(ComparableVersion_Item* item)
{
    if(item == nullptr) {
        if(size() == 0) {
            return 0;
        }
        auto first = java_cast< ComparableVersion_Item* >(get(int32_t(0)));
        return npc(first)->compareTo(nullptr);
    }
    {
        ::java::util::Iterator* left;
        ::java::util::Iterator* right;
        switch (npc(item)->getType()) {
        case INTEGER_ITEM:
            return -int32_t(1);
        case STRING_ITEM:
            return 1;
        case LIST_ITEM:
            left = iterator();
            right = npc((java_cast< ComparableVersion_ListItem* >(item)))->iterator();
            while (npc(left)->hasNext() || npc(right)->hasNext()) {
                auto l = npc(left)->hasNext() ? java_cast< ComparableVersion_Item* >(npc(left)->next()) : static_cast< ComparableVersion_Item* >(nullptr);
                auto r = npc(right)->hasNext() ? java_cast< ComparableVersion_Item* >(npc(right)->next()) : static_cast< ComparableVersion_Item* >(nullptr);
                auto result = l == nullptr ? (r == nullptr ? int32_t(0) : -int32_t(1) * npc(r)->compareTo(l)) : npc(l)->compareTo(r);
                if(result != 0) {
                    return result;
                }
            }
            return 0;
        default:
            throw new ::java::lang::RuntimeException(::java::lang::StringBuilder().append(u"invalid item: "_j)->append(static_cast< ::java::lang::Object* >(npc(item)->getClass()))->toString());
        }
    }

}

java::lang::String* org::sbolstandard::core2::ComparableVersion_ListItem::toString()
{
    auto buffer = new ::java::lang::StringBuilder(u"("_j);
    for (auto *iter = iterator(); npc(iter)->hasNext(); ) {
        npc(buffer)->append(static_cast< ::java::lang::Object* >(java_cast< ComparableVersion_Item* >(npc(iter)->next())));
        if(npc(iter)->hasNext()) {
            npc(buffer)->append(u',');
        }
    }
    npc(buffer)->append(u')');
    return npc(buffer)->toString();
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::ComparableVersion_ListItem::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.ComparableVersion.ListItem", 49);
    return c;
}

java::lang::Class* org::sbolstandard::core2::ComparableVersion_ListItem::getClass0()
{
    return class_();
}


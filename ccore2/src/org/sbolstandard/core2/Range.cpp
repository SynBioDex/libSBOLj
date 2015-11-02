// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Range.java
#include <org/sbolstandard/core2/Range.hpp>

#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <org/sbolstandard/core2/Location.hpp>
#include <org/sbolstandard/core2/OrientationType.hpp>
#include <org/sbolstandard/core2/SBOLDocument.hpp>

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

org::sbolstandard::core2::Range::Range(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Range::Range(::java::net::URI* identity, int32_t start, int32_t end) 
    : Range(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,start,end);
}

org::sbolstandard::core2::Range::Range(Range* range) 
    : Range(*static_cast< ::default_init_tag* >(0))
{
    ctor(range);
}

void org::sbolstandard::core2::Range::init()
{
    start = int32_t(0);
    end = int32_t(0);
}

void org::sbolstandard::core2::Range::ctor(::java::net::URI* identity, int32_t start, int32_t end)
{
    super::ctor(identity);
    init();
    setEnd(end);
    setStart(start);
}

void org::sbolstandard::core2::Range::ctor(Range* range)
{
    super::ctor(static_cast< Location* >(range));
    init();
    this->setEnd(npc(range)->getEnd());
    this->setStart(npc(range)->getStart());
}

void org::sbolstandard::core2::Range::setStart(int32_t value)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(value <= 0) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Range "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have a start greater than zero."_j)->toString());
    }
    if(value > end) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Range "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have a start before the end."_j)->toString());
    }
    start = value;
}

int32_t org::sbolstandard::core2::Range::getStart()
{
    return start;
}

int32_t org::sbolstandard::core2::Range::getEnd()
{
    return end;
}

void org::sbolstandard::core2::Range::setEnd(int32_t value)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(value <= 0) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Range "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have an end greater than zero."_j)->toString());
    }
    if(value < start) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Range "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have a start before the end."_j)->toString());
    }
    end = value;
}

org::sbolstandard::core2::Location* org::sbolstandard::core2::Range::deepCopy()
{
    return new Range(this);
}

int32_t org::sbolstandard::core2::Range::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + end;
    result = prime * result + ((orientation == nullptr) ? int32_t(0) : npc(orientation)->hashCode());
    result = prime * result + start;
    return result;
}

bool org::sbolstandard::core2::Range::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< Range* >(obj);
    if(end != npc(other)->end)
        return false;

    if(orientation != npc(other)->orientation)
        return false;

    return start == npc(other)->start;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Range::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Range", 28);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Range::getClass0()
{
    return class_();
}


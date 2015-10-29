// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Cut.java
#include <org/sbolstandard/core2/Cut.hpp>

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

org::sbolstandard::core2::Cut::Cut(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Cut::Cut(::java::net::URI* identity, int32_t at) 
    : Cut(*static_cast< ::default_init_tag* >(0))
{
    ctor(identity,at);
}

org::sbolstandard::core2::Cut::Cut(Cut* cut) 
    : Cut(*static_cast< ::default_init_tag* >(0))
{
    ctor(cut);
}

void org::sbolstandard::core2::Cut::ctor(::java::net::URI* identity, int32_t at)
{
    super::ctor(identity);
    setAt(at);
}

void org::sbolstandard::core2::Cut::ctor(Cut* cut)
{
    super::ctor(static_cast< Location* >(cut));
    this->setAt(npc(cut)->getAt());
}

int32_t org::sbolstandard::core2::Cut::getAt()
{
    return at;
}

void org::sbolstandard::core2::Cut::setAt(int32_t at)
{
    if(sbolDocument != nullptr)
        npc(sbolDocument)->checkReadOnly();

    if(at < 0) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Cut "_j)->append(static_cast< ::java::lang::Object* >(this->getIdentity()))
            ->append(u" must have a value greater than or equal to zero."_j)->toString());
    }
    this->at = at;
}

org::sbolstandard::core2::Cut* org::sbolstandard::core2::Cut::deepCopy()
{
    return new Cut(this);
}

int32_t org::sbolstandard::core2::Cut::hashCode()
{
    auto const prime = int32_t(31);
    auto result = super::hashCode();
    result = prime * result + at;
    result = prime * result + ((orientation == nullptr) ? int32_t(0) : npc(orientation)->hashCode());
    return result;
}

bool org::sbolstandard::core2::Cut::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(!super::equals(obj))
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< Cut* >(obj);
    if(at != npc(other)->at)
        return false;

    return orientation == npc(other)->orientation;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Cut::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Cut", 26);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Cut::getClass0()
{
    return class_();
}


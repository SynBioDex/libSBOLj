// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLValidationException.java
#include <org/sbolstandard/core2/SBOLValidationException.hpp>

#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <java/util/ArrayList.hpp>
#include <java/util/Arrays.hpp>
#include <java/util/Collection.hpp>
#include <java/util/Collections.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/List.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <ObjectArray.hpp>
#include <SubArray.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace org
{
    namespace sbolstandard
    {
        namespace core2
        {
typedef ::SubArray< ::org::sbolstandard::core2::Identified, ::java::lang::ObjectArray > IdentifiedArray;
        } // core2
    } // sbolstandard
} // org

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

org::sbolstandard::core2::SBOLValidationException::SBOLValidationException(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::SBOLValidationException::SBOLValidationException(::java::lang::String* message, IdentifiedArray*/*...*/ objects) 
    : SBOLValidationException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message,objects);
}

org::sbolstandard::core2::SBOLValidationException::SBOLValidationException(::java::lang::String* message, ::java::util::Collection* objects) 
    : SBOLValidationException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message,objects);
}

org::sbolstandard::core2::SBOLValidationException::SBOLValidationException(::java::lang::String* message, ::java::lang::Throwable* cause, IdentifiedArray*/*...*/ objects) 
    : SBOLValidationException(*static_cast< ::default_init_tag* >(0))
{
    ctor(message,cause,objects);
}

org::sbolstandard::core2::SBOLValidationException::SBOLValidationException(::java::lang::Throwable* cause) 
    : SBOLValidationException(*static_cast< ::default_init_tag* >(0))
{
    ctor(cause);
}

constexpr int64_t org::sbolstandard::core2::SBOLValidationException::serialVersionUID;

void org::sbolstandard::core2::SBOLValidationException::ctor(::java::lang::String* message, IdentifiedArray*/*...*/ objects)
{
    ctor(message, static_cast< ::java::util::Collection* >(::java::util::Arrays::asList(objects)));
}

void org::sbolstandard::core2::SBOLValidationException::ctor(::java::lang::String* message, ::java::util::Collection* objects)
{
    super::ctor(formatMessage(message, objects));
    this->objects = ::java::util::Collections::unmodifiableList(new ::java::util::ArrayList(static_cast< ::java::util::Collection* >(objects)));
}

void org::sbolstandard::core2::SBOLValidationException::ctor(::java::lang::String* message, ::java::lang::Throwable* cause, IdentifiedArray*/*...*/ objects)
{
    super::ctor(message, cause);
    this->objects = ::java::util::Collections::unmodifiableList(::java::util::Arrays::asList(objects));
}

void org::sbolstandard::core2::SBOLValidationException::ctor(::java::lang::Throwable* cause)
{
    super::ctor(cause);
    this->objects = ::java::util::Collections::emptyList();
}

java::util::Collection* org::sbolstandard::core2::SBOLValidationException::getObjects()
{
    return objects;
}

java::lang::String* org::sbolstandard::core2::SBOLValidationException::formatMessage(::java::lang::String* message, ::java::util::Collection* objects)
{
    clinit();
    auto const sb = new ::java::lang::StringBuilder(message);
    if(!npc(objects)->isEmpty()) {
        npc(sb)->append(u": "_j);
        auto first = true;
        for (auto _i = npc(objects)->iterator(); _i->hasNext(); ) {
            Identified* obj = java_cast< Identified* >(_i->next());
            {
                if(first) {
                    first = false;
                } else {
                    npc(sb)->append(u", "_j);
                }
                if(npc(obj)->getIdentity() != nullptr) {
                    npc(sb)->append(static_cast< ::java::lang::Object* >(npc(obj)->getIdentity()));
                }
            }
        }
    }
    return npc(sb)->toString();
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::SBOLValidationException::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.SBOLValidationException", 46);
    return c;
}

java::lang::Class* org::sbolstandard::core2::SBOLValidationException::getClass0()
{
    return class_();
}


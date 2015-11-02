// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Annotation.java
#include <org/sbolstandard/core2/Annotation.hpp>

#include <java/io/PrintStream.hpp>
#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/Boolean.hpp>
#include <java/lang/Class.hpp>
#include <java/lang/ClassCastException.hpp>
#include <java/lang/Double.hpp>
#include <java/lang/Integer.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/lang/System.hpp>
#include <java/net/URI.hpp>
#include <java/util/ArrayList.hpp>
#include <java/util/Iterator.hpp>
#include <java/util/List.hpp>
#include <javax/xml/namespace_/QName.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/SBOLValidationException.hpp>
#include <org/sbolstandard/core2/Sbol2Terms_Identified.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Datatree_NamedProperties.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Datatree.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Literal_BooleanLiteral.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Literal_DoubleLiteral.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Literal_IntegerLiteral.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Literal_StringLiteral.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Literal_UriLiteral.hpp>
#include <uk/ac/ncl/intbio/core/datatree/Literal.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NamedProperty.hpp>
#include <uk/ac/ncl/intbio/core/datatree/NestedDocument.hpp>
#include <uk/ac/ncl/intbio/core/datatree/PropertyValue.hpp>
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

org::sbolstandard::core2::Annotation::Annotation(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::Annotation::Annotation(::javax::xml::namespace_::QName* qName, ::java::lang::String* literal) 
    : Annotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(qName,literal);
}

org::sbolstandard::core2::Annotation::Annotation(::javax::xml::namespace_::QName* qName, int32_t literal) 
    : Annotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(qName,literal);
}

org::sbolstandard::core2::Annotation::Annotation(::javax::xml::namespace_::QName* qName, double literal) 
    : Annotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(qName,literal);
}

org::sbolstandard::core2::Annotation::Annotation(::javax::xml::namespace_::QName* qName, bool literal) 
    : Annotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(qName,literal);
}

org::sbolstandard::core2::Annotation::Annotation(::javax::xml::namespace_::QName* qName, ::java::net::URI* literal) 
    : Annotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(qName,literal);
}

org::sbolstandard::core2::Annotation::Annotation(::javax::xml::namespace_::QName* qName, ::javax::xml::namespace_::QName* nestedQName, ::java::net::URI* nestedURI, ::java::util::List* annotations) 
    : Annotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(qName,nestedQName,nestedURI,annotations);
}

org::sbolstandard::core2::Annotation::Annotation(::uk::ac::ncl::intbio::core::datatree::NamedProperty* value) 
    : Annotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(value);
}

org::sbolstandard::core2::Annotation::Annotation(Annotation* annotation) 
    : Annotation(*static_cast< ::default_init_tag* >(0))
{
    ctor(annotation);
}

void org::sbolstandard::core2::Annotation::ctor(::javax::xml::namespace_::QName* qName, ::java::lang::String* literal)
{
    super::ctor();
    if(npc(npc(npc(qName)->getPrefix())->toString())->equals(static_cast< ::java::lang::Object* >(u"sbol"_j))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(npc(qName)->getLocalPart())->append(u" is an illegal annotation, since annotations cannot be in the SBOL namespace."_j)->toString(), new IdentifiedArray());
    }
    value = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(qName), literal);
}

void org::sbolstandard::core2::Annotation::ctor(::javax::xml::namespace_::QName* qName, int32_t literal)
{
    super::ctor();
    if(npc(npc(npc(qName)->getPrefix())->toString())->equals(static_cast< ::java::lang::Object* >(u"sbol"_j))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(npc(qName)->getLocalPart())->append(u" is an illegal annotation, since annotations cannot be in the SBOL namespace."_j)->toString(), new IdentifiedArray());
    }
    value = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(qName), literal);
}

void org::sbolstandard::core2::Annotation::ctor(::javax::xml::namespace_::QName* qName, double literal)
{
    super::ctor();
    value = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(qName), ::java::lang::Double::valueOf(literal));
}

void org::sbolstandard::core2::Annotation::ctor(::javax::xml::namespace_::QName* qName, bool literal)
{
    super::ctor();
    value = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(qName), ::java::lang::Boolean::valueOf(literal));
}

void org::sbolstandard::core2::Annotation::ctor(::javax::xml::namespace_::QName* qName, ::java::net::URI* literal)
{
    super::ctor();
    if(npc(npc(npc(qName)->getPrefix())->toString())->equals(static_cast< ::java::lang::Object* >(u"sbol"_j))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(npc(qName)->getLocalPart())->append(u" is an illegal annotation, since annotations cannot be in the SBOL namespace."_j)->toString(), new IdentifiedArray());
    }
    value = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(qName), literal);
}

void org::sbolstandard::core2::Annotation::ctor(::javax::xml::namespace_::QName* qName, ::javax::xml::namespace_::QName* nestedQName, ::java::net::URI* nestedURI, ::java::util::List* annotations)
{
    super::ctor();
    if(npc(npc(npc(qName)->getPrefix())->toString())->equals(static_cast< ::java::lang::Object* >(u"sbol"_j))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(npc(qName)->getLocalPart())->append(u" is an illegal annotation, since annotations cannot be in the SBOL namespace."_j)->toString(), new IdentifiedArray());
    }
    if(npc(npc(npc(nestedQName)->getPrefix())->toString())->equals(static_cast< ::java::lang::Object* >(u"sbol"_j))) {
        throw new SBOLValidationException(::java::lang::StringBuilder().append(npc(nestedQName)->getLocalPart())->append(u" is an illegal annotation, since annotations cannot be in the SBOL namespace."_j)->toString(), new IdentifiedArray());
    }
    ::java::util::List* list = new ::java::util::ArrayList();
    for (auto _i = npc(annotations)->iterator(); _i->hasNext(); ) {
        Annotation* a = java_cast< Annotation* >(_i->next());
        {
            npc(list)->add(static_cast< ::java::lang::Object* >(npc(a)->getValue()));
        }
    }
    value = ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperty(static_cast< ::java::lang::Object* >(qName), static_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(::uk::ac::ncl::intbio::core::datatree::Datatree::NestedDocument(nestedQName, nestedURI, ::uk::ac::ncl::intbio::core::datatree::Datatree::NamedProperties(static_cast< ::java::util::List* >(list)))));
}

void org::sbolstandard::core2::Annotation::ctor(::uk::ac::ncl::intbio::core::datatree::NamedProperty* value)
{
    super::ctor();
    if(npc(npc(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(value)->getName()))->getPrefix())->toString())->equals(static_cast< ::java::lang::Object* >(u"sbol"_j))) {
        if(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(value)->getName()))->equals(static_cast< ::java::lang::Object* >(Sbol2Terms_Identified::timeStamp()))) {
            npc(::java::lang::System::out())->println(u"Warning: sbol:timeStamp is deprecated"_j);
        } else {
            throw new SBOLValidationException(::java::lang::StringBuilder().append(npc(java_cast< ::javax::xml::namespace_::QName* >(npc(value)->getName()))->getLocalPart())->append(u" is an illegal annotation, since annotations cannot be in the SBOL namespace."_j)->toString(), new IdentifiedArray());
        }
    }
    this->value = value;
}

void org::sbolstandard::core2::Annotation::ctor(Annotation* annotation)
{
    super::ctor();
    this->setValue(npc(annotation)->getValue());
}

javax::xml::namespace_::QName* org::sbolstandard::core2::Annotation::getQName()
{
    return java_cast< ::javax::xml::namespace_::QName* >(npc(value)->getName());
}

bool org::sbolstandard::core2::Annotation::isBooleanValue()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_BooleanLiteral* >(npc(value)->getValue()) != nullptr) {
        return true;
    }
    return false;
}

java::lang::Boolean* org::sbolstandard::core2::Annotation::getBooleanValue()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_BooleanLiteral* >(npc(value)->getValue()) != nullptr) {
        return npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_BooleanLiteral* >(npc(value)->getValue())))->getValue();
    }
    return nullptr;
}

bool org::sbolstandard::core2::Annotation::isDoubleValue()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_DoubleLiteral* >(npc(value)->getValue()) != nullptr) {
        return true;
    }
    return false;
}

java::lang::Double* org::sbolstandard::core2::Annotation::getDoubleValue()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_DoubleLiteral* >(npc(value)->getValue()) != nullptr) {
        return npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_DoubleLiteral* >(npc(value)->getValue())))->getValue();
    }
    return nullptr;
}

bool org::sbolstandard::core2::Annotation::isIntegerValue()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_IntegerLiteral* >(npc(value)->getValue()) != nullptr) {
        return true;
    }
    return false;
}

java::lang::Integer* org::sbolstandard::core2::Annotation::getIntegerValue()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_IntegerLiteral* >(npc(value)->getValue()) != nullptr) {
        return npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_IntegerLiteral* >(npc(value)->getValue())))->getValue();
    }
    return nullptr;
}

bool org::sbolstandard::core2::Annotation::isStringValue()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_StringLiteral* >(npc(value)->getValue()) != nullptr) {
        return true;
    }
    return false;
}

java::lang::String* org::sbolstandard::core2::Annotation::getStringValue()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_StringLiteral* >(npc(value)->getValue()) != nullptr) {
        return npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal* >(npc(value)->getValue())))->getValue())->toString();
    }
    return nullptr;
}

java::net::URI* org::sbolstandard::core2::Annotation::getURIValue()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_UriLiteral* >(npc(value)->getValue()) != nullptr) {
        return npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_UriLiteral* >(npc(value)->getValue())))->getValue();
    }
    return nullptr;
}

javax::xml::namespace_::QName* org::sbolstandard::core2::Annotation::getNestedQName()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(value)->getValue()) != nullptr) {
        return java_cast< ::javax::xml::namespace_::QName* >(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(value)->getValue())))->getType());
    }
    return nullptr;
}

java::net::URI* org::sbolstandard::core2::Annotation::getNestedIdentity()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(value)->getValue()) != nullptr) {
        return npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(value)->getValue())))->getIdentity();
    }
    return nullptr;
}

bool org::sbolstandard::core2::Annotation::isNestedAnnotations()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(value)->getValue()) != nullptr) {
        return true;
    }
    return false;
}

java::util::List* org::sbolstandard::core2::Annotation::getAnnotations()
{
    if(dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(value)->getValue()) != nullptr) {
        ::java::util::List* annotations = new ::java::util::ArrayList();
        for (auto _i = npc(npc((java_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(value)->getValue())))->getProperties())->iterator(); _i->hasNext(); ) {
            ::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty = java_cast< ::uk::ac::ncl::intbio::core::datatree::NamedProperty* >(_i->next());
            {
                npc(annotations)->add(static_cast< ::java::lang::Object* >(new Annotation(namedProperty)));
            }
        }
        return annotations;
    }
    return nullptr;
}

uk::ac::ncl::intbio::core::datatree::NamedProperty* org::sbolstandard::core2::Annotation::getValue()
{
    return value;
}

void org::sbolstandard::core2::Annotation::setValue(::uk::ac::ncl::intbio::core::datatree::NamedProperty* value)
{
    this->value = value;
}

org::sbolstandard::core2::Annotation* org::sbolstandard::core2::Annotation::deepCopy()
{
    return new Annotation(this);
}

org::sbolstandard::core2::Annotation* org::sbolstandard::core2::Annotation::copy()
{
    return this->deepCopy();
}

int32_t org::sbolstandard::core2::Annotation::hashCode()
{
    auto const prime = int32_t(31);
    auto result = int32_t(1);
    result = prime * result + ((value == nullptr) ? int32_t(0) : npc(value)->hashCode());
    return result;
}

bool org::sbolstandard::core2::Annotation::equals(::java::lang::Object* obj)
{
    if(static_cast< ::java::lang::Object* >(this) == obj)
        return true;

    if(obj == nullptr)
        return false;

    if(static_cast< ::java::lang::Object* >(getClass()) != static_cast< ::java::lang::Object* >(npc(obj)->getClass()))
        return false;

    auto other = java_cast< Annotation* >(obj);
    if(value == nullptr) {
        if(npc(other)->value != nullptr)
            return false;

    } else if(!npc(value)->equals(npc(other)->value)) {
        if(!npc(this->getQName())->equals(static_cast< ::java::lang::Object* >(npc(other)->getQName()))) {
            return false;
        } else if((dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_StringLiteral* >(npc(this->getValue())->getValue()) != nullptr) && (dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_StringLiteral* >(npc(npc(other)->getValue())->getValue()) != nullptr)) {
            if(!npc(this->getStringValue())->equals(static_cast< ::java::lang::Object* >(npc(other)->getStringValue()))) {
                return false;
            }
        } else if((dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_BooleanLiteral* >(npc(this->getValue())->getValue()) != nullptr) && (dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_BooleanLiteral* >(npc(npc(other)->getValue())->getValue()) != nullptr)) {
            if(!npc(this->getBooleanValue())->equals(static_cast< ::java::lang::Object* >(npc(other)->getBooleanValue()))) {
                return false;
            }
        } else if((dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_DoubleLiteral* >(npc(this->getValue())->getValue()) != nullptr) && (dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_DoubleLiteral* >(npc(npc(other)->getValue())->getValue()) != nullptr)) {
            if(!npc(this->getDoubleValue())->equals(static_cast< ::java::lang::Object* >(npc(other)->getDoubleValue()))) {
                return false;
            }
        } else if((dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_IntegerLiteral* >(npc(this->getValue())->getValue()) != nullptr) && (dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_IntegerLiteral* >(npc(npc(other)->getValue())->getValue()) != nullptr)) {
            if(!npc(this->getIntegerValue())->equals(static_cast< ::java::lang::Object* >(npc(other)->getIntegerValue()))) {
                return false;
            }
        } else if((dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_UriLiteral* >(npc(this->getValue())->getValue()) != nullptr) && (dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::Literal_UriLiteral* >(npc(npc(other)->getValue())->getValue()) != nullptr)) {
            if(!npc(this->getURIValue())->equals(static_cast< ::java::lang::Object* >(npc(other)->getURIValue()))) {
                return false;
            }
        } else if((dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(this->getValue())->getValue()) != nullptr) && (dynamic_cast< ::uk::ac::ncl::intbio::core::datatree::NestedDocument* >(npc(npc(other)->getValue())->getValue()) != nullptr)) {
            if(!npc(this->getNestedQName())->equals(static_cast< ::java::lang::Object* >(npc(other)->getNestedQName()))) {
                return false;
            }
            if(!npc(this->getNestedIdentity())->equals(static_cast< ::java::lang::Object* >(npc(other)->getNestedIdentity()))) {
                return false;
            }
            if(npc(this->getAnnotations())->size() != npc(npc(other)->getAnnotations())->size()) {
                return false;
            }
            for (auto _i = npc(this->getAnnotations())->iterator(); _i->hasNext(); ) {
                Annotation* annotation1 = java_cast< Annotation* >(_i->next());
                {
                    auto foundIt = false;
                    for (auto _i = npc(npc(other)->getAnnotations())->iterator(); _i->hasNext(); ) {
                        Annotation* annotation2 = java_cast< Annotation* >(_i->next());
                        {
                            if(npc(annotation1)->equals(static_cast< ::java::lang::Object* >(annotation2))) {
                                foundIt = true;
                                break;
                            }
                        }
                    }
                    if(foundIt == false)
                        break;

                }
            }
        } else {
            return false;
        }
        return true;
    }
    return true;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::Annotation::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.Annotation", 33);
    return c;
}

java::lang::Class* org::sbolstandard::core2::Annotation::getClass0()
{
    return class_();
}


// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Identified.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <uk/ac/ncl/intbio/core/datatree/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
typedef ::SubArray< ::java::util::Map, ::java::lang::ObjectArray > MapArray;
    } // util
} // java

struct default_init_tag;

class org::sbolstandard::core2::Identified
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

public: /* protected */
    ::java::net::URI* identity {  };

private:
    ::java::net::URI* persistentIdentity {  };
    ::java::lang::String* version {  };
    ::java::util::List* annotations {  };
    ::java::net::URI* wasDerivedFrom {  };

public: /* protected */
    ::java::lang::String* displayId {  };
    SBOLDocument* sbolDocument {  };
    ::java::lang::String* name {  };
    ::java::lang::String* description {  };
protected:
    void ctor(::java::net::URI* identity);
    void ctor(Identified* identified);

public:
    virtual ::java::net::URI* getIdentity();

public: /* package */
    void setIdentity(::java::net::URI* identity);

public:
    virtual bool isSetPersistentIdentity();
    virtual ::java::net::URI* getPersistentIdentity();

public: /* package */
    virtual void setPersistentIdentity(::java::net::URI* persistentIdentity);
    virtual void unsetPersistentIdentity();

public:
    virtual bool isSetVersion();
    virtual bool isSetWasDerivedFrom();
    virtual ::java::lang::String* getVersion();

public: /* package */
    virtual void setVersion(::java::lang::String* version);

public:
    virtual bool isSetDisplayId();
    virtual ::java::lang::String* getDisplayId();

public: /* package */
    virtual void setDisplayId(::java::lang::String* displayId);
    virtual void unsetDisplayId();

public:
    virtual ::java::net::URI* getWasDerivedFrom();
    virtual void setWasDerivedFrom(::java::net::URI* wasDerivedFrom);
    virtual bool hasAnnotations();
    virtual Annotation* createAnnotation(::javax::xml::namespace_::QName* qName, ::java::lang::String* literal);
    virtual Annotation* createAnnotation(::javax::xml::namespace_::QName* qName, double literal);
    virtual Annotation* createAnnotation(::javax::xml::namespace_::QName* qName, int32_t literal);
    virtual Annotation* createAnnotation(::javax::xml::namespace_::QName* qName, bool literal);
    virtual Annotation* createAnnotation(::javax::xml::namespace_::QName* qName, ::java::net::URI* literal);

public: /* package */
    virtual Annotation* createAnnotation(::uk::ac::ncl::intbio::core::datatree::NamedProperty* namedProperty);

public:
    virtual Annotation* createAnnotation(::javax::xml::namespace_::QName* qName, ::javax::xml::namespace_::QName* nestedQName, ::java::net::URI* nestedURI, ::java::util::List* annotations);

public: /* package */
    virtual void addAnnotation(Annotation* annotation);

public:
    virtual bool removeAnnotation(Annotation* annotation);
    virtual ::java::util::List* getAnnotations();
    virtual void clearAnnotations();

public: /* package */
    virtual void setAnnotations(::java::util::List* annotations);

public:
    virtual void unsetWasDerivedFrom();

public: /* protected */
    virtual void setSBOLDocument(SBOLDocument* sbolDocument);
    virtual SBOLDocument* getSBOLDocument();
    virtual Identified* deepCopy() = 0;

public:
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    /* <I extends Identified> */void addChildSafely(Identified* child, ::java::util::Map* siblingsMap, ::java::lang::String* typeName, ::java::util::MapArray*/*...*/ maps);
    /* <I extends Identified> */bool removeChildSafely(Identified* identified, ::java::util::Map* siblingsMap);

public:
    virtual bool isSetName();
    virtual ::java::lang::String* getName();
    virtual void setName(::java::lang::String* name);
    virtual void unsetName();
    virtual bool isSetDescription();
    virtual ::java::lang::String* getDescription();
    virtual void setDescription(::java::lang::String* description);
    virtual void unsetDescription();

    // Generated

public: /* package */
    Identified(::java::net::URI* identity);

public: /* protected */
    Identified(Identified* identified);
protected:
    Identified(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

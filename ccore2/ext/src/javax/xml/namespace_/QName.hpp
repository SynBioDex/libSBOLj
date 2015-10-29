// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <javax/xml/namespace_/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/io/Serializable.hpp>

struct default_init_tag;

class javax::xml::namespace_::QName
    : public virtual ::java::lang::Object
    , public virtual ::java::io::Serializable
{

public:
    typedef ::java::lang::Object super;

private:
    static constexpr int64_t compatibleSerialVersionUID { int64_t(4418622981026545151LL) };
    static constexpr int64_t defaultSerialVersionUID { int64_t(-9120448754896609940LL) };
    ::java::lang::String* localPart {  };
    ::java::lang::String* namespaceURI {  };
    ::java::lang::String* prefix {  };
    static int64_t serialVersionUID_;
    static bool useDefaultSerialVersionUID_;

protected:
    void ctor(::java::lang::String* localPart);
    void ctor(::java::lang::String* namespaceURI, ::java::lang::String* localPart);
    void ctor(::java::lang::String* namespaceURI, ::java::lang::String* localPart, ::java::lang::String* prefix);

public:
    bool equals(::java::lang::Object* objectToTest) override;
    virtual ::java::lang::String* getLocalPart();
    virtual ::java::lang::String* getNamespaceURI();
    virtual ::java::lang::String* getPrefix();
    int32_t hashCode() override;
    ::java::lang::String* toString() override;
    static QName* valueOf(::java::lang::String* qNameAsString);

    // Generated
    QName(::java::lang::String* localPart);
    QName(::java::lang::String* namespaceURI, ::java::lang::String* localPart);
    QName(::java::lang::String* namespaceURI, ::java::lang::String* localPart, ::java::lang::String* prefix);
protected:
    QName(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    static int64_t& serialVersionUID();
    static bool& useDefaultSerialVersionUID();
    virtual ::java::lang::Class* getClass0();
};

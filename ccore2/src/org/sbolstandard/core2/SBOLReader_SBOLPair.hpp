// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLReader.java

#pragma once

#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::SBOLReader_SBOLPair
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

private:
    ::java::net::URI* left {  };
    ::java::net::URI* right {  };
protected:
    void ctor(::java::net::URI* left, ::java::net::URI* right);

public:
    virtual ::java::net::URI* getLeft();
    virtual void setLeft(::java::net::URI* left);
    virtual ::java::net::URI* getRight();
    virtual void setRight(::java::net::URI* right);

    // Generated
    SBOLReader_SBOLPair(::java::net::URI* left, ::java::net::URI* right);
protected:
    SBOLReader_SBOLPair(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
    friend class SBOLReader;
};

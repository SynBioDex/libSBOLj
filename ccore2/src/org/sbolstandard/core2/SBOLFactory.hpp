// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/SBOLFactory.java

#pragma once

#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>

struct default_init_tag;

class org::sbolstandard::core2::SBOLFactory
    : public virtual ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;
protected:
    void ctor();

public:
    static SBOLDocument* read(::java::io::InputStream* in) /* throws(IOException, SBOLValidationException) */;
    static void write(SBOLDocument* doc, ::java::io::OutputStream* out) /* throws(IOException, SBOLValidationException) */;

    // Generated

private:
    SBOLFactory();
protected:
    SBOLFactory(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

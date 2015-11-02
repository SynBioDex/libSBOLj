// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Cut.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Location.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Cut
    : public Location
{

public:
    typedef Location super;

private:
    int32_t at {  };
protected:
    void ctor(::java::net::URI* identity, int32_t at);
    void ctor(Cut* cut);

public:
    virtual int32_t getAt();
    virtual void setAt(int32_t at);

public: /* protected */
    Cut* deepCopy() override;

public:
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

    // Generated

public: /* package */
    Cut(::java::net::URI* identity, int32_t at);

private:
    Cut(Cut* cut);
protected:
    Cut(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

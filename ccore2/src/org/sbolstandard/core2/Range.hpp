// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Range.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/Location.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Range
    : public Location
{

public:
    typedef Location super;

private:
    int32_t start {  };
    int32_t end {  };
protected:
    void ctor(::java::net::URI* identity, int32_t start, int32_t end);
    void ctor(Range* range);

public:
    virtual void setStart(int32_t value);
    virtual int32_t getStart();
    virtual int32_t getEnd();
    virtual void setEnd(int32_t value);

public: /* protected */
    Location* deepCopy() override;

public:
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

    // Generated

public: /* package */
    Range(::java::net::URI* identity, int32_t start, int32_t end);

private:
    Range(Range* range);
protected:
    Range(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    void init();
    virtual ::java::lang::Class* getClass0();
};

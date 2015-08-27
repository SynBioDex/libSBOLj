// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/Collection.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>

struct default_init_tag;

class org::sbolstandard::core2::Collection
    : public TopLevel
{

public:
    typedef TopLevel super;

private:
    ::java::util::Set* members {  };
protected:
    void ctor(::java::net::URI* identity);
    void ctor(Collection* collection);

public:
    virtual bool addMember(::java::net::URI* memberURI);
    virtual bool removeMember(::java::net::URI* memberURI);
    virtual void setMembers(::java::util::Set* members);
    virtual ::java::util::Set* getMemberURIs();
    virtual ::java::util::Set* getMembers();
    virtual bool containsMember(::java::net::URI* memberURI);
    virtual void clearMembers();
    int32_t hashCode() override;
    bool equals(::java::lang::Object* obj) override;

public: /* protected */
    Collection* deepCopy() override;

public: /* package */
    Collection* copy(::java::lang::String* URIprefix, ::java::lang::String* displayId, ::java::lang::String* version) override;

public: /* protected */
    bool checkDescendantsURIcompliance() override;
    virtual bool isComplete();

    // Generated

public: /* package */
    Collection(::java::net::URI* identity);

private:
    Collection(Collection* collection);
protected:
    Collection(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();

private:
    virtual ::java::lang::Class* getClass0();
};

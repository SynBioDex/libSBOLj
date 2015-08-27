// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/URIcompliance.java

#pragma once

#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/util/fwd-${project.parent.artifactId}-core2.hpp>
#include <org/sbolstandard/core2/fwd-${project.parent.artifactId}-core2.hpp>
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

class org::sbolstandard::core2::URIcompliance final
    : public ::java::lang::Object
{

public:
    typedef ::java::lang::Object super;

public: /* package */
    static void validateIdVersion(::java::lang::String* displayId, ::java::lang::String* version);
    static ::java::net::URI* createCompliantURI(::java::lang::String* prefix, ::java::lang::String* displayId, ::java::lang::String* version);
    static ::java::net::URI* createCompliantURI(::java::lang::String* prefix, ::java::lang::String* type, ::java::lang::String* displayId, ::java::lang::String* version, bool useType);
    static ::java::lang::String* extractPersistentId(::java::net::URI* objURI);
    static ::java::lang::String* extractURIprefix(::java::net::URI* objURI);
    static ::java::lang::String* extractDisplayId(::java::net::URI* objURI);
    static ::java::lang::String* extractVersion(::java::net::URI* objURI);
    static bool isURIcompliant(Identified* identified);
    static bool isChildURIformCompliant(::java::net::URI* parentURI, ::java::net::URI* childURI);
    static bool isChildURIcompliant(Identified* parent, Identified* child);
    static bool isTopLevelURIformCompliant(::java::net::URI* topLevelURI);
    static bool isTopLevelURIcompliant(TopLevel* topLevel);
    static bool isDisplayIdCompliant(::java::lang::String* newDisplayId);
    static bool isVersionCompliant(::java::lang::String* newVersion);
    static bool isURIprefixCompliant(::java::lang::String* URIprefix);

private:
    static ::java::lang::String* delimiter_;
    static ::java::lang::String* URIprefixPattern_;
    static ::java::lang::String* displayIDpattern_;
    static ::java::lang::String* versionPattern_;
    static ::java::lang::String* genericURIpattern1_;
    static ::java::lang::String* toplevelURIpattern_;

public: /* package */
    static bool keyExistsInAnyMap(::java::net::URI* key, ::java::util::MapArray*/*...*/ maps);

    // Generated

public:
    URIcompliance();
protected:
    URIcompliance(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    static void clinit();

public: /* package */
    static ::java::lang::String*& delimiter();
    static ::java::lang::String*& URIprefixPattern();
    static ::java::lang::String*& displayIDpattern();
    static ::java::lang::String*& versionPattern();
    static ::java::lang::String*& genericURIpattern1();
    static ::java::lang::String*& toplevelURIpattern();

private:
    virtual ::java::lang::Class* getClass0();
};

// Generated from /${project.parent.artifactId}-core2/src/main/java/org/sbolstandard/core2/URIcompliance.java
#include <org/sbolstandard/core2/URIcompliance.hpp>

#include <java/lang/ArrayStoreException.hpp>
#include <java/lang/IllegalArgumentException.hpp>
#include <java/lang/NullPointerException.hpp>
#include <java/lang/Object.hpp>
#include <java/lang/String.hpp>
#include <java/lang/StringBuilder.hpp>
#include <java/net/URI.hpp>
#include <java/util/Map.hpp>
#include <java/util/Set.hpp>
#include <java/util/regex/Matcher.hpp>
#include <java/util/regex/Pattern.hpp>
#include <org/sbolstandard/core2/Identified.hpp>
#include <org/sbolstandard/core2/TopLevel.hpp>
#include <ObjectArray.hpp>
#include <SubArray.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace util
    {
typedef ::SubArray< ::java::util::Map, ::java::lang::ObjectArray > MapArray;
    } // util
} // java

template<typename T>
static T* npc(T* t)
{
    if(!t) throw new ::java::lang::NullPointerException();
    return t;
}

org::sbolstandard::core2::URIcompliance::URIcompliance(const ::default_init_tag&)
    : super(*static_cast< ::default_init_tag* >(0))
{
    clinit();
}

org::sbolstandard::core2::URIcompliance::URIcompliance()
    : URIcompliance(*static_cast< ::default_init_tag* >(0))
{
    ctor();
}

void org::sbolstandard::core2::URIcompliance::validateIdVersion(::java::lang::String* displayId, ::java::lang::String* version)
{
    clinit();
    if(displayId != nullptr && !isDisplayIdCompliant(displayId)) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Display id `"_j)->append(displayId)
            ->append(u"' is not valid."_j)->toString());
    }
    if(version != nullptr && !isVersionCompliant(version)) {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(u"Version `"_j)->append(version)
            ->append(u"' is not valid."_j)->toString());
    }
}

java::net::URI* org::sbolstandard::core2::URIcompliance::createCompliantURI(::java::lang::String* prefix, ::java::lang::String* displayId, ::java::lang::String* version)
{
    clinit();
    validateIdVersion(displayId, version);
    if(!npc(prefix)->endsWith(u"/"_j) && !npc(prefix)->endsWith(u":"_j) && !npc(prefix)->endsWith(u"#"_j)) {
        prefix = ::java::lang::StringBuilder(prefix).append(u"/"_j)->toString();
    }
    if(version == nullptr || npc(version)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
        return ::java::net::URI::create(::java::lang::StringBuilder().append(prefix)->append(displayId)->toString());
    }
    return ::java::net::URI::create(::java::lang::StringBuilder().append(prefix)->append(displayId)
        ->append(u'/')
        ->append(version)->toString());
}

java::net::URI* org::sbolstandard::core2::URIcompliance::createCompliantURI(::java::lang::String* prefix, ::java::lang::String* type, ::java::lang::String* displayId, ::java::lang::String* version, bool useType)
{
    clinit();
    validateIdVersion(displayId, version);
    if(!useType)
        return createCompliantURI(prefix, displayId, version);

    if(!npc(prefix)->endsWith(u"/"_j) && !npc(prefix)->endsWith(u":"_j) && !npc(prefix)->endsWith(u"#"_j)) {
        prefix = ::java::lang::StringBuilder(prefix).append(u"/"_j)->toString();
    }
    if(version == nullptr || npc(version)->equals(static_cast< ::java::lang::Object* >(u""_j))) {
        return ::java::net::URI::create(::java::lang::StringBuilder().append(prefix)->append(type)
            ->append(u'/')
            ->append(displayId)->toString());
    }
    return ::java::net::URI::create(::java::lang::StringBuilder().append(prefix)->append(type)
        ->append(u'/')
        ->append(displayId)
        ->append(u'/')
        ->append(version)->toString());
}

java::lang::String* org::sbolstandard::core2::URIcompliance::extractPersistentId(::java::net::URI* objURI)
{
    clinit();
    auto URIstr = npc(objURI)->toString();
    auto r = ::java::util::regex::Pattern::compile(genericURIpattern1_);
    auto m = npc(r)->matcher(URIstr);
    if(npc(m)->matches()) {
        return npc(m)->group(int32_t(1));
    } else {
        throw new ::java::lang::IllegalArgumentException(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(objURI))->append(u" does not include a valid persistentIdentity."_j)->toString());
    }
}

java::lang::String* org::sbolstandard::core2::URIcompliance::extractURIprefix(::java::net::URI* objURI)
{
    clinit();
    auto URIstr = npc(objURI)->toString();
    auto r = ::java::util::regex::Pattern::compile(genericURIpattern1_);
    auto m = npc(r)->matcher(URIstr);
    if(npc(m)->matches())
        return npc(m)->group(int32_t(2));
    else
        return nullptr;
}

java::lang::String* org::sbolstandard::core2::URIcompliance::extractDisplayId(::java::net::URI* objURI)
{
    clinit();
    auto URIstr = npc(objURI)->toString();
    auto r = ::java::util::regex::Pattern::compile(genericURIpattern1_);
    auto m = npc(r)->matcher(URIstr);
    if(npc(m)->matches()) {
        return npc(m)->group(int32_t(4));
    } else
        return nullptr;
}

java::lang::String* org::sbolstandard::core2::URIcompliance::extractVersion(::java::net::URI* objURI)
{
    clinit();
    auto URIstr = npc(objURI)->toString();
    auto r = ::java::util::regex::Pattern::compile(genericURIpattern1_);
    auto m = npc(r)->matcher(URIstr);
    if(npc(m)->matches() && npc(m)->groupCount() >= 6)
        return npc(m)->group(int32_t(6));
    else
        return nullptr;
}

bool org::sbolstandard::core2::URIcompliance::isURIcompliant(Identified* identified)
{
    clinit();
    if(!npc(identified)->isSetDisplayId())
        return false;

    if(!npc(identified)->isSetPersistentIdentity())
        return false;

    if(!npc(npc(npc(identified)->getPersistentIdentity())->toString())->endsWith(::java::lang::StringBuilder().append(u"/"_j)->append(npc(identified)->getDisplayId())->toString()) && !npc(npc(npc(identified)->getPersistentIdentity())->toString())->endsWith(::java::lang::StringBuilder().append(u"#"_j)->append(npc(identified)->getDisplayId())->toString()) && !npc(npc(npc(identified)->getPersistentIdentity())->toString())->endsWith(::java::lang::StringBuilder().append(u":"_j)->append(npc(identified)->getDisplayId())->toString()))
        return false;

    if(!npc(identified)->isSetVersion()) {
        if(!npc(npc(npc(identified)->identity)->toString())->equals(static_cast< ::java::lang::Object* >(npc(npc(identified)->getPersistentIdentity())->toString())))
            return false;

    } else {
        if(!npc(npc(npc(identified)->identity)->toString())->equals(static_cast< ::java::lang::Object* >(::java::lang::StringBuilder().append(npc(npc(identified)->getPersistentIdentity())->toString())->append(u"/"_j)
            ->append(npc(identified)->getVersion())->toString())))
            return false;

    }
    return true;
}

bool org::sbolstandard::core2::URIcompliance::isChildURIformCompliant(::java::net::URI* parentURI, ::java::net::URI* childURI)
{
    clinit();
    auto parentPersistentId = extractPersistentId(parentURI);
    if(parentPersistentId == nullptr)
        return false;

    auto childDisplayId = extractDisplayId(childURI);
    if(childDisplayId == nullptr)
        return false;

    auto parentVersion = extractVersion(parentURI);
    if(parentVersion == nullptr) {
        return npc(npc(childURI)->toString())->equals(static_cast< ::java::lang::Object* >(::java::lang::StringBuilder().append(parentPersistentId)->append(u"/"_j)
            ->append(childDisplayId)->toString()));
    } else {
        return npc(npc(childURI)->toString())->equals(static_cast< ::java::lang::Object* >(::java::lang::StringBuilder().append(parentPersistentId)->append(u"/"_j)
            ->append(childDisplayId)
            ->append(u"/"_j)
            ->append(parentVersion)->toString()));
    }
}

bool org::sbolstandard::core2::URIcompliance::isChildURIcompliant(Identified* parent, Identified* child)
{
    clinit();
    if(!isURIcompliant(child))
        return false;

    if(!npc(npc(npc(child)->getPersistentIdentity())->toString())->equals(static_cast< ::java::lang::Object* >(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(npc(parent)->getPersistentIdentity()))->append(u"/"_j)
        ->append(npc(child)->getDisplayId())->toString())) && !npc(npc(npc(child)->getPersistentIdentity())->toString())->equals(static_cast< ::java::lang::Object* >(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(npc(parent)->getPersistentIdentity()))->append(u"#"_j)
        ->append(npc(child)->getDisplayId())->toString())) && !npc(npc(npc(child)->getPersistentIdentity())->toString())->equals(static_cast< ::java::lang::Object* >(::java::lang::StringBuilder().append(static_cast< ::java::lang::Object* >(npc(parent)->getPersistentIdentity()))->append(u":"_j)
        ->append(npc(child)->getDisplayId())->toString())))
        return false;

    if(npc(parent)->isSetVersion()) {
        if(!npc(child)->isSetVersion() || !npc(npc(child)->getVersion())->equals(static_cast< ::java::lang::Object* >(npc(parent)->getVersion())))
            return false;

    } else if(npc(child)->isSetVersion()) {
        return false;
    }
    return true;
}

bool org::sbolstandard::core2::URIcompliance::isTopLevelURIformCompliant(::java::net::URI* topLevelURI)
{
    clinit();
    ::java::util::regex::Pattern* r;
    auto URIstr = npc(topLevelURI)->toString();
    r = ::java::util::regex::Pattern::compile(toplevelURIpattern_);
    auto m = npc(r)->matcher(URIstr);
    return (npc(m)->matches());
}

bool org::sbolstandard::core2::URIcompliance::isTopLevelURIcompliant(TopLevel* topLevel)
{
    clinit();
    if(!isTopLevelURIformCompliant(npc(topLevel)->getIdentity()))
        return false;

    return isURIcompliant(topLevel);
}

bool org::sbolstandard::core2::URIcompliance::isDisplayIdCompliant(::java::lang::String* newDisplayId)
{
    clinit();
    auto r = ::java::util::regex::Pattern::compile(displayIDpattern_);
    auto m = npc(r)->matcher(newDisplayId);
    return npc(m)->matches();
}

bool org::sbolstandard::core2::URIcompliance::isVersionCompliant(::java::lang::String* newVersion)
{
    clinit();
    if(newVersion == nullptr) {
        throw new ::java::lang::IllegalArgumentException(u"Version must not be null"_j);
    }
    if(npc(newVersion)->equals(static_cast< ::java::lang::Object* >(u""_j)))
        return true;

    auto r = ::java::util::regex::Pattern::compile(versionPattern_);
    auto m = npc(r)->matcher(newVersion);
    return npc(m)->matches();
}

bool org::sbolstandard::core2::URIcompliance::isURIprefixCompliant(::java::lang::String* URIprefix)
{
    clinit();
    auto r = ::java::util::regex::Pattern::compile(::java::lang::StringBuilder().append(URIprefixPattern_)->append(delimiter_)->toString());
    auto m = npc(r)->matcher(URIprefix);
    return npc(m)->matches();
}

java::lang::String*& org::sbolstandard::core2::URIcompliance::delimiter()
{
    clinit();
    return delimiter_;
}
java::lang::String* org::sbolstandard::core2::URIcompliance::delimiter_;

java::lang::String*& org::sbolstandard::core2::URIcompliance::URIprefixPattern()
{
    clinit();
    return URIprefixPattern_;
}
java::lang::String* org::sbolstandard::core2::URIcompliance::URIprefixPattern_;

java::lang::String*& org::sbolstandard::core2::URIcompliance::displayIDpattern()
{
    clinit();
    return displayIDpattern_;
}
java::lang::String* org::sbolstandard::core2::URIcompliance::displayIDpattern_;

java::lang::String*& org::sbolstandard::core2::URIcompliance::versionPattern()
{
    clinit();
    return versionPattern_;
}
java::lang::String* org::sbolstandard::core2::URIcompliance::versionPattern_;

java::lang::String*& org::sbolstandard::core2::URIcompliance::genericURIpattern1()
{
    clinit();
    return genericURIpattern1_;
}
java::lang::String* org::sbolstandard::core2::URIcompliance::genericURIpattern1_;

java::lang::String*& org::sbolstandard::core2::URIcompliance::toplevelURIpattern()
{
    clinit();
    return toplevelURIpattern_;
}
java::lang::String* org::sbolstandard::core2::URIcompliance::toplevelURIpattern_;

bool org::sbolstandard::core2::URIcompliance::keyExistsInAnyMap(::java::net::URI* key, ::java::util::MapArray*/*...*/ maps)
{
    clinit();
    for(auto map : *npc(maps)) {
        if(npc(npc(map)->keySet())->contains(static_cast< ::java::lang::Object* >(key)))
            return true;

    }
    return false;
}

extern java::lang::Class *class_(const char16_t *c, int n);

java::lang::Class* org::sbolstandard::core2::URIcompliance::class_()
{
    static ::java::lang::Class* c = ::class_(u"org.sbolstandard.core2.URIcompliance", 36);
    return c;
}

void org::sbolstandard::core2::URIcompliance::clinit()
{
struct string_init_ {
    string_init_() {
    delimiter_ = u"[/|#|:]"_j;
    URIprefixPattern_ = u"\\b(?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"_j;
    displayIDpattern_ = u"[a-zA-Z_]+[a-zA-Z0-9_]*"_j;
    versionPattern_ = u"[0-9]+[a-zA-Z0-9_\\.-]*"_j;
    genericURIpattern1_ = ::java::lang::StringBuilder().append(u"(("_j)->append(URIprefixPattern_)
        ->append(u")("_j)
        ->append(delimiter_)
        ->append(u"("_j)
        ->append(displayIDpattern_)
        ->append(u")){1,3})(/("_j)
        ->append(versionPattern_)
        ->append(u"))?"_j)->toString();
    toplevelURIpattern_ = ::java::lang::StringBuilder().append(URIprefixPattern_)->append(delimiter_)
        ->append(displayIDpattern_)
        ->append(u"(/"_j)
        ->append(versionPattern_)
        ->append(u")?"_j)->toString();
    }
};

    static string_init_ string_init_instance;

    super::clinit();
}

java::lang::Class* org::sbolstandard::core2::URIcompliance::getClass0()
{
    return class_();
}

